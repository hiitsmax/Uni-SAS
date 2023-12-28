package catering.tests.kitchentasks;

import java.util.Locale.Category;

import catering.businesslogic.CatERing;
import catering.businesslogic.UseCaseLogicException;
import catering.businesslogic.eventmanagement.event.Event;
import catering.businesslogic.eventmanagement.event.EventManager;
import catering.businesslogic.eventmanagement.menu.Menu;
import catering.businesslogic.eventmanagement.menu.MenuException;
import catering.businesslogic.eventmanagement.menu.section.Section;
import catering.businesslogic.eventmanagement.service.Service;
import catering.businesslogic.eventmanagement.service.ServiceException;
import catering.businesslogic.eventmanagement.service.ServiceInfo;
import catering.businesslogic.kitchenmanagement.kitchen.KitchenManager;
import catering.businesslogic.kitchenmanagement.recipe.Recipe;
import catering.businesslogic.usermanagement.UserException;
import catering.businesslogic.usermanagement.user.User;
import catering.persistence.KitchenPersistence;
import catering.persistence.PersistenceManager;
import javafx.collections.ObservableList;

public class TestCatERing1 {
    public static void main(String[] args) {
        System.out.println("[Catering Test - Kitchentask 1] - Testing connection to DB...");
        PersistenceManager.testSQLConnection();
        CatERing.getInstance().getKitchenManager().addKitchenEventReceiver(new KitchenPersistence());
        System.out.println("[Catering Test - Kitchentask 1] - Start");

        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        User thisUser = CatERing.getInstance().getUserManager().getCurrentUser();

        System.out.println("User logged in: " + thisUser.toString());

        System.out.println("[Catering Test - Kitchentask 1] - Logged in with fake login");

        EventManager em = CatERing.getInstance().getEventManager();
        KitchenManager km = CatERing.getInstance().getKitchenManager();

        System.out.println("[Catering Test - Kitchentask 1] - Got both event and kitchen managers");

        ObservableList<Service> services = em.getServices();
        Service s = services.get(0);

        System.out.println("[Catering Test - Kitchentask 1] - Got first service from event manager");
        System.out.println("[Catering Test - Kitchentask 1] - This is the choosen service: \n" + s.toString());

        try {

            System.out.println(
                    "[Catering Test - Kitchentask 1] - Creating SummarySheet for service with id " + s.getId());
            km.createSummarySheet(s);

            System.out
                    .println("[Catering Test - Kitchentask 1] - Created SummarySheet for service with id " + s.getId());

            System.out.println("[Catering Test - Kitchentask 1] - TEST SUCCESSFULL");
        } catch (UserException | ServiceException e) {

            System.out.println("[Catering Test - Kitchentask 1] - TEST FAILED");
            System.out.println("[Catering Test - Kitchentask 1] - EXCEPTION: " + e);
        }
    }
}

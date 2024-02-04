package catering.tests.kitchentasks;

import catering.businesslogic.CatERing;
import catering.businesslogic.UseCaseLogicException;
import catering.businesslogic.eventmanagement.event.Event;
import catering.businesslogic.eventmanagement.event.EventManager;
import catering.businesslogic.eventmanagement.menu.Menu;
import catering.businesslogic.eventmanagement.menu.MenuException;
import catering.businesslogic.eventmanagement.menu.section.Section;
import catering.businesslogic.eventmanagement.service.Service;
import catering.businesslogic.eventmanagement.service.ServiceException;
import catering.businesslogic.kitchenmanagement.kitchen.KitchenManager;
import catering.businesslogic.kitchenmanagement.recipe.Recipe;
import catering.businesslogic.kitchenmanagement.summarysheet.SummarySheet;
import catering.businesslogic.usermanagement.UserException;
import catering.businesslogic.usermanagement.user.User;
import catering.persistence.KitchenPersistence;
import catering.persistence.PersistenceManager;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class TestCatERing1a {
    public static void main(String[] args) {
         System.out.println("[Catering Test - Kitchentask 1a] - Testing connection to DB...");
        try{
            PersistenceManager.testSQLConnection();
        } catch (Exception e){
            System.out.println("[Catering Test - Kitchentask 1a] - Failed to connect to DB");
            System.out.println("[Catering Test - Kitchentask 1a] - EXCEPTION: " + e);
            return;
        }
        CatERing.getInstance().getKitchenManager().addKitchenEventReceiver(new KitchenPersistence());
        System.out.println("[Catering Test - Kitchentask 1a] - Start");

        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        User thisUser = CatERing.getInstance().getUserManager().getCurrentUser();

        System.out.println("User logged in: " + thisUser.toString());

        System.out.println("[Catering Test - Kitchentask 1a] - Logged in with fake login");

        EventManager em = CatERing.getInstance().getEventManager();
        KitchenManager km = CatERing.getInstance().getKitchenManager();

        System.out.println("[Catering Test - Kitchentask 1a] - Got both event and kitchen managers");

        ObservableList<Service> services = em.getServices();
        Service s = services.get(1);

        System.out.println("[Catering Test - Kitchentask 1a] - Got first service from event manager");
        System.out.println("[Catering Test - Kitchentask 1a] - This is the choosen service: \n" + s.toString());

        try {

            System.out.println(
                    "[Catering Test - Kitchentask 1a] - Opening SummarySheet for service with id " + s.getId());
            km.openSummarySheet(s);

            System.out
                    .println("[Catering Test - Kitchentask 1a] - Opened SummarySheet for service with id " + s.getId());

            System.out.println("[Catering Test - Kitchentask 1a] - TEST SUCCESSFULL");
        } catch (UserException | ServiceException e) {

            System.out.println("[Catering Test - Kitchentask 1a] - TEST FAILED");
            System.out.println("[Catering Test - Kitchentask 1a] - EXCEPTION: " + e);
        }
    }
}

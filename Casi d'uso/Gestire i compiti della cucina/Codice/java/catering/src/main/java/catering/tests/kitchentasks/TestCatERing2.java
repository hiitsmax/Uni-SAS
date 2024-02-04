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
import catering.businesslogic.kitchenmanagement.task.TaskListOrder;
import catering.businesslogic.usermanagement.UserException;
import catering.businesslogic.usermanagement.user.User;
import catering.persistence.KitchenPersistence;
import catering.persistence.PersistenceManager;
import javafx.collections.ObservableList;

import java.util.Arrays;
import java.util.Map;

public class TestCatERing2 {
    public static void main(String[] args) {
         System.out.println("[Catering Test - Kitchentask 2 ] - Testing connection to DB...");
        try{
            PersistenceManager.testSQLConnection();
        } catch (Exception e){
            System.out.println("[Catering Test - Kitchentask 2 ] - Failed to connect to DB");
            System.out.println("[Catering Test - Kitchentask 2 ] - EXCEPTION: " + e);
            return;
        }
        CatERing.getInstance().getKitchenManager().addKitchenEventReceiver(new KitchenPersistence());
        System.out.println("[Catering Test - Kitchentask 2 ] - Start");

        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        User thisUser = CatERing.getInstance().getUserManager().getCurrentUser();

        System.out.println("User logged in: " + thisUser.toString());

        System.out.println("[Catering Test - Kitchentask 2 ] - Logged in with fake login");

        EventManager em = CatERing.getInstance().getEventManager();
        KitchenManager km = CatERing.getInstance().getKitchenManager();

        System.out.println("[Catering Test - Kitchentask 2 ] - Got both event and kitchen managers");

        ObservableList<Service> services = em.getServices();
        Service s = services.get(1);

        System.out.println("[Catering Test - Kitchentask 2 ] - Got first service from event manager");
        System.out.println("[Catering Test - Kitchentask 2 ] - This is the choosen service: \n" + s.toString());

        try {

            System.out.println("[Catering Test - Kitchentask 2 ] - Opening summary sheet for service with id " + s.getId());
            km.openSummarySheet(s);
            System.out.println("[Catering Test - Kitchentask 2 ] - Sorting by difficulty");
            km.orderSummarySheetTasks(TaskListOrder.ByDifficulty);

            System.out.println("[Catering Test - Kitchentask 2 ] - Set sort for service with id " + s.getId());

            System.out.println("[Catering Test - Kitchentask 2 ] - TEST SUCCESSFULL");
        } catch (UserException | ServiceException e) {

            System.out.println("[Catering Test - Kitchentask 2 ] - TEST FAILED");
            System.out.println("[Catering Test - Kitchentask 2 ] - EXCEPTION: " + e);
        }
    }
    
}

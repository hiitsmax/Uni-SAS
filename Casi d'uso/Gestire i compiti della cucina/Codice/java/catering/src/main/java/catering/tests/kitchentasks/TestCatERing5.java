package catering.tests.kitchentasks;
import catering.businesslogic.CatERing;
import catering.businesslogic.UseCaseLogicException;
import catering.businesslogic.eventmanagement.event.Event;
import catering.businesslogic.eventmanagement.menu.Menu;
import catering.businesslogic.eventmanagement.menu.MenuException;
import catering.businesslogic.eventmanagement.menu.section.Section;
import catering.businesslogic.eventmanagement.service.Service;
import catering.businesslogic.eventmanagement.service.ServiceInfo;
import catering.businesslogic.kitchenmanagement.recipe.Recipe;
import catering.businesslogic.kitchenmanagement.summarysheet.SummarySheet;
import catering.businesslogic.kitchenmanagement.task.Task;
import catering.businesslogic.usermanagement.user.User;
import catering.persistence.KitchenPersistence;
import catering.persistence.PersistenceManager;
import javafx.collections.ObservableList;

import java.util.Arrays;
import java.util.Map;

public class TestCatERing5 {
    public static void main(String[] args) {
                System.out.println("[Catering Test - Kitchentask 5 ] - Testing connection to DB...");
        try {
            PersistenceManager.testSQLConnection();
        } catch (Exception e) {
            System.out.println("[Catering Test - Kitchentask 5 ] - Failed to connect to DB");
            System.out.println("[Catering Test - Kitchentask 5 ] - EXCEPTION: " + e);
            return;
        }
        CatERing.getInstance().getKitchenManager().addKitchenEventReceiver(new KitchenPersistence());
        System.out.println("[Catering Test - Kitchentask 5 ] - Start");

        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        User thisUser = CatERing.getInstance().getUserManager().getCurrentUser();

        System.out.println("User logged in: " + thisUser.toString());

        System.out.println("[Catering Test - Kitchentask 5 ] - Logged in with fake login");

        System.out.println(
                "[Catering Test - Kitchentask 5 ] - Getting the first not preparatory service");
        try{
            Service service = CatERing.getInstance().getEventManager().getServices().get(1);
            System.out
                .println("[Catering Test - Kitchentask 5 ] - This is the choosen service: \n" + service.toString());

            System.out.println("[Catering Test - Kitchentask 5 ] - Getting a cook (Marinella)");

            User cook = CatERing.getInstance().getUserManager().getUserByUsername("Marinella");
            System.out.println("[Catering Test - Kitchentask 5 ] - This is the choosen cook: \n" + cook.toString());
            System.out.println("[Catering Test - Kitchentask 5 ] - Assigning her as a support cook");

            CatERing.getInstance().getKitchenManager().assignSupportCookToService(service, cook);
            System.out.println("[Catering Test - Kitchentask 5 ] - Support cook assigned");
            System.out.println("[Catering Test - Kitchentask 5 ] - TEST SUCCESSFUL");
        } catch (Exception e) {
            System.out.println("[Catering Test - Kitchentask 5 ] - TEST FAILED");
            System.out.println("[Catering Test - Kitchentask 5 ] - EXCEPTION: " + e);
        }

    }
}

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

public class TestCatERing4b {
    public static void main(String[] args) {
        System.out.println("[Catering Test - Kitchentask 4 ] - Testing connection to DB...");
        try {
            PersistenceManager.testSQLConnection();
        } catch (Exception e) {
            System.out.println("[Catering Test - Kitchentask 4 ] - Failed to connect to DB");
            System.out.println("[Catering Test - Kitchentask 4 ] - EXCEPTION: " + e);
            return;
        }
        CatERing.getInstance().getKitchenManager().addKitchenEventReceiver(new KitchenPersistence());
        System.out.println("[Catering Test - Kitchentask 4 ] - Start");

        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        User thisUser = CatERing.getInstance().getUserManager().getCurrentUser();

        System.out.println("User logged in: " + thisUser.toString());

        System.out.println("[Catering Test - Kitchentask 4b ] - Logged in with fake login");

        System.out.println(
                "[Catering Test - Kitchentask 4b ] - Getting the first task of the second service's summary sheet");

        try {
            System.out.println(
                    "[Catering Test - Kitchentask 4b ] - Getting the first service from the event manager and Lidia as user");
            Service service = CatERing.getInstance().getEventManager().getServices().get(1);
            System.out
                    .println("[Catering Test - Kitchentask 4b ] - This is the choosen service: \n" + service.toString());
            SummarySheet summarySheet = CatERing.getInstance().getKitchenManager().openSummarySheet(service);
            System.out.println("[Catering Test - Kitchentask 4b ] - This is the choosen summary sheet: \n"
                    + summarySheet.toString());
            Task task;
            try {
                task = summarySheet.getTaskList().get(0);
            } catch (Exception e) {
                System.out.println("[Catering Test - Kitchentask 4b ] - TEST FAILED: No tasks in the summary sheet");
                return;
            }
            System.out.println("[Catering Test - Kitchentask 4b ] - This is the choosen task: \n" + task.toString());

            System.out.println("[Catering Test - Kitchentask 4b ] - Deleting task");
            CatERing.getInstance().getKitchenManager().deleteTask(task);
            System.out.println("[Catering Test - Kitchentask 4b ] - Task deleted");
            System.out.println("[Catering Test - Kitchentask 4b ] - TEST SUCCESSFUL");
        } catch (Exception e) {

            System.out.println("[Catering Test - Kitchentask 4b ] - TEST FAILED");
            System.out.println("[Catering Test - Kitchentask 4b ] - EXCEPTION: " + e);
        }  
    }
}

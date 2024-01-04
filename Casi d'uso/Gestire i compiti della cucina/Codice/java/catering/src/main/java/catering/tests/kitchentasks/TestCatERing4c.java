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
import catering.businesslogic.usermanagement.UserException;
import catering.businesslogic.usermanagement.user.User;
import catering.persistence.KitchenPersistence;
import catering.persistence.PersistenceManager;
import javafx.collections.ObservableList;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

public class TestCatERing4c {
    public static void main(String[] args) {
        System.out.println("[Catering Test - Kitchentask 4c ] - Testing connection to DB...");
        try {
            PersistenceManager.testSQLConnection();
        } catch (Exception e) {
            System.out.println("[Catering Test - Kitchentask 4c ] - Failed to connect to DB");
            System.out.println("[Catering Test - Kitchentask 4c ] - EXCEPTION: " + e);
            return;
        }
        CatERing.getInstance().getKitchenManager().addKitchenEventReceiver(new KitchenPersistence());
        System.out.println("[Catering Test - Kitchentask 4c ] - Start");

        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        User thisUser = CatERing.getInstance().getUserManager().getCurrentUser();

        System.out.println("User logged in: " + thisUser.toString());

        System.out.println("[Catering Test - Kitchentask 4c ] - Logged in with fake login");

        System.out.println(
                "[Catering Test - Kitchentask 4c ] - Getting the first task of the second service's summary sheet");
        try{
            Service service = CatERing.getInstance().getEventManager().getServices().get(1);
            System.out
                .println("[Catering Test - Kitchentask 4c ] - This is the choosen service: \n" + service.toString());
            SummarySheet summarySheet = CatERing.getInstance().getKitchenManager().openSummarySheet(service);
            System.out.println("[Catering Test - Kitchentask 4c ] - This is the choosen summary sheet: \n"
                + summarySheet.toString());
            Task task;
            try {
                task = summarySheet.getTaskList().get(0);
            } catch (Exception e) {
                System.out.println("[Catering Test - Kitchentask 4c ] - TEST FAILED: No tasks in the summary sheet");
                return;
            }

            System.out.println("[Catering Test - Kitchentask 4c ] - This is the choosen task: \n" + task.toString());
            
            task.setNotes("2 portions of homemade pasta available");;
            System.out.println("[Catering Test - Kitchentask 4c ] - Already done preparations: \n" + task.getNotes());
            
            System.out.println("[Catering Test - Kitchentask 4c ] - Updating note");
            CatERing.getInstance().getKitchenManager().modifyTask(task, summarySheet);
            System.out.println("[Catering Test - Kitchentask 4c ] - Note updated");
            System.out.println("[Catering Test - Kitchentask 4c ] - TEST SUCCESSFUL");
        } catch (Exception e) {

            System.out.println("[Catering Test - Kitchentask 4c ] - TEST FAILED");
            System.out.println("[Catering Test - Kitchentask 4c ] - EXCEPTION: " + e);
        }

    }
}

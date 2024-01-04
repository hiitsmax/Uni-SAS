package catering.tests.kitchentasks;

import catering.businesslogic.CatERing;
import catering.businesslogic.UseCaseLogicException;
import catering.businesslogic.eventmanagement.event.Event;
import catering.businesslogic.eventmanagement.menu.Menu;
import catering.businesslogic.eventmanagement.menu.MenuException;
import catering.businesslogic.eventmanagement.menu.section.Section;
import catering.businesslogic.eventmanagement.service.Service;
import catering.businesslogic.eventmanagement.service.ServiceInfo;
import catering.businesslogic.kitchenmanagement.kitchen.KitchenManager;
import catering.businesslogic.kitchenmanagement.recipe.Recipe;
import catering.businesslogic.kitchenmanagement.summarysheet.SummarySheet;
import catering.businesslogic.kitchenmanagement.task.Task;
import catering.businesslogic.usermanagement.shift.Shift;
import catering.businesslogic.usermanagement.shift.ShiftManager;
import catering.businesslogic.usermanagement.user.User;
import catering.persistence.KitchenPersistence;
import catering.persistence.PersistenceManager;
import javafx.collections.ObservableList;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TestCatERing4 {
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

        System.out.println("[Catering Test - Kitchentask 4 ] - Logged in with fake login");

        System.out.println(
                "[Catering Test - Kitchentask 4 ] - Getting the first task of the second service's summary sheet");

        try {
            System.out.println(
                    "[Catering Test - Kitchentask 4 ] - Getting the first service from the event manager and Lidia as user");
            Service service = CatERing.getInstance().getEventManager().getServices().get(1);
            System.out
                    .println("[Catering Test - Kitchentask 4 ] - This is the choosen service: \n" + service.toString());
            SummarySheet summarySheet = CatERing.getInstance().getKitchenManager().openSummarySheet(service);
            // stampa tutti i tasks
            System.out.println(Task.loadedTasks.size());
            System.out.println("[Catering Test - Kitchentask 4 ] - This is the choosen summary sheet: \n"
                    + summarySheet.toString());

            System.out.println("[Catering Test - Kitchentask 4 ] - Creating a new task");

            Task task = new Task();
            task.setStart(new Time(0));
            task.setEnd(new Time(0));
            task.setName("Prep Pane al cioccolato");
            task.setIngredients("Cioccolato, pane, vaniglia");
            task.setStaffInstructions("Preparare il pane al cioccolato");
            task.setRecipe(Recipe.loadRecipeById(10));
            task.setOrder(0);
            task.setImportance(0);
            task.setDifficulty(2);

            CatERing.getInstance().getKitchenManager().createTask(task, summarySheet);

            System.out.println("[Catering Test - Kitchentask 4 ] - This is the created task: \n" + task.toString());
            System.out.println("[Catering Test - Kitchentask 4 ] - Adding the task to the summary sheet");

            User user = User.loadUserById(2);
            System.out.println("[Catering Test - Kitchentask 4 ] - This is the choosen user: \n" + user.toString());

            System.out.println("[Catering Test - Kitchentask 4 ] - Assigning the task to Lidia");
            CatERing.getInstance().getKitchenManager().assignTask(task, user);
            System.out.println("[Catering Test - Kitchentask 4 ] - Assigned the task to Lidia");
            System.out.println("[Catering Test - Kitchentask 4 ] - TEST SUCCESSFUL");
        } catch (Exception e) {
            
            System.out.println("[Catering Test - Kitchentask 4 ] - TEST FAILED");
            System.out.println("[Catering Test - Kitchentask 4 ] - EXCEPTION: " + e);
            e.printStackTrace();
        }
    }
}

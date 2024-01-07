package catering.tests.kitchentasks;

import catering.businesslogic.CatERing;
import catering.businesslogic.UseCaseLogicException;
import catering.businesslogic.eventmanagement.event.Event;
import catering.businesslogic.eventmanagement.menu.Menu;
import catering.businesslogic.eventmanagement.menu.MenuException;
import catering.businesslogic.eventmanagement.menu.section.Section;
import catering.businesslogic.eventmanagement.service.Service;
import catering.businesslogic.eventmanagement.service.ServiceInfo;
import catering.businesslogic.kitchenmanagement.preparation.Preparation;
import catering.businesslogic.kitchenmanagement.recipe.Recipe;
import catering.businesslogic.kitchenmanagement.summarysheet.SummarySheet;
import catering.businesslogic.kitchenmanagement.task.Task;
import catering.businesslogic.usermanagement.UserException;
import catering.businesslogic.usermanagement.user.User;
import catering.persistence.KitchenPersistence;
import catering.persistence.PersistenceManager;
import javafx.collections.ObservableList;

import java.util.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Map;

public class TestCatERing4a {
    public static void main(String[] args) throws UserException {
        System.out.println("[Catering Test - Kitchentask 4a ] - Testing connection to DB...");
        try {
            PersistenceManager.testSQLConnection();
        } catch (Exception e) {
            System.out.println("[Catering Test - Kitchentask 4a ] - Failed to connect to DB");
            System.out.println("[Catering Test - Kitchentask 4a ] - EXCEPTION: " + e);
            return;
        }
        CatERing.getInstance().getKitchenManager().addKitchenEventReceiver(new KitchenPersistence());
        System.out.println("[Catering Test - Kitchentask 4a ] - Start");

        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        User thisUser = CatERing.getInstance().getUserManager().getCurrentUser();

        System.out.println("User logged in: " + thisUser.toString());

        System.out.println("[Catering Test - Kitchentask 4a ] - Logged in with fake login");

        System.out.println(
                "[Catering Test - Kitchentask 4a ] - Getting the first task of the second service's summary sheet");
        try{
            Service service = CatERing.getInstance().getEventManager().getServices().get(1);
            System.out
                .println("[Catering Test - Kitchentask 4a ] - This is the choosen service: \n" + service.toString());
            SummarySheet summarySheet = CatERing.getInstance().getKitchenManager().openSummarySheet(service);
            System.out.println("[Catering Test - Kitchentask 4a ] - This is the choosen summary sheet: \n"
                + summarySheet.toString());
            Task task;
            try {
                task = summarySheet.getTaskList().get(0);
            } catch (Exception e) {
                System.out.println("[Catering Test - Kitchentask 4a ] - TEST FAILED: No tasks in the summary sheet");
                return;
            }

            System.out.println("[Catering Test - Kitchentask 4a ] - This is the choosen task: \n" + task.toString());

            task.setName("task");
            System.out.println("[Catering Test - Kitchentask 4a ] - This is the updated task: \n" + task.getName());
            task.setIngredients("ingredients");
            System.out.println("[Catering Test - Kitchentask 4a ] - This is the updated ingredients: \n" + task.getIngredients());
            task.setStaffInstructions("staffInstructions");
            System.out.println("[Catering Test - Kitchentask 4a ] - This is the updated staff instructions: \n" + task.getStaffInstructions());
            task.setNotes("notes");
            System.out.println("[Catering Test - Kitchentask 4a ] - This is the updated notes: \n" + task.getNotes());
            task.setRecipe(Recipe.loadRecipeById(1));
            System.out.println("[Catering Test - Kitchentask 4a ] - This is the updated recipe: \n" + task.getRecipe());
            task.setPreparation(null);
            System.out.println("[Catering Test - Kitchentask 4a ] - This is the updated preparation: \n" + task.getPreparation());
            
            DateFormat isoFormatter =  new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            Time start = new Time(1000*60*10);
            Time end = new Time(1000*60*20);
            task.setStart(start);
            System.out.println("[Catering Test - Kitchentask 4a ] - This is the updated start date: \n" + task.getStart());
            task.setEnd(end);
            System.out.println("[Catering Test - Kitchentask 4a ] - This is the updated end date: \n" + task.getEnd());

            User cook = User.loadUserById(2);
            //TODO: magari controllare che l'assegnee sia un cuoco (nel KitchenManager) che tanto i task derivano dai summary sheets per la preparazione

            // TODO: Ridondante, già fatto sotto con modify task
            //CatERing.getInstance().getKitchenManager().assignTask(task, cook);  

            task.setAssegnee(cook);
            System.out.println("[Catering Test - Kitchentask 4a ] - This is the updated assegnee: \n" + task.getAssegnee());
            task.setImportance(0);
            System.out.println("[Catering Test - Kitchentask 4a ] - This is the updated importance: \n" + task.getImportance());
            task.setDifficulty(0);
            System.out.println("[Catering Test - Kitchentask 4a ] - This is the updated difficulty: \n" + task.getDifficulty());
            task.setOrder(1);
            System.out.println("[Catering Test - Kitchentask 4a ] - This is the updated order: \n" + task.getOrder());
            
            System.out.println("[Catering Test - Kitchentask 4a ] - Updating task");
            CatERing.getInstance().getKitchenManager().modifyTask(task);
            System.out.println("[Catering Test - Kitchentask 4a ] - Task updated");
            System.out.println("[Catering Test - Kitchentask 4a ] - TEST SUCCESSFUL");
        } catch (Exception e) {

            System.out.println("[Catering Test - Kitchentask 4a ] - TEST FAILED");
            System.out.println("[Catering Test - Kitchentask 4a ] - EXCEPTION: " + e);
        }
    }
}

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
import catering.businesslogic.eventmanagement.service.ServiceInfo;
import catering.businesslogic.kitchenmanagement.kitchen.KitchenManager;
import catering.businesslogic.kitchenmanagement.recipe.Recipe;
import catering.businesslogic.kitchenmanagement.task.TaskListOrder;
import catering.businesslogic.usermanagement.UserException;
import catering.businesslogic.usermanagement.shift.Shift;
import catering.businesslogic.usermanagement.shift.ShiftManager;
import catering.businesslogic.usermanagement.user.User;
import catering.persistence.KitchenPersistence;
import catering.persistence.PersistenceManager;
import javafx.collections.ObservableList;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TestCatERing3 {
    public static void main(String[] args) {
         System.out.println("[Catering Test - Kitchentask 3 ] - Testing connection to DB...");
        try{
            PersistenceManager.testSQLConnection();
        } catch (Exception e){
            System.out.println("[Catering Test - Kitchentask 3 ] - Failed to connect to DB");
            System.out.println("[Catering Test - Kitchentask 3 ] - EXCEPTION: " + e);
            return;
        }
        CatERing.getInstance().getKitchenManager().addKitchenEventReceiver(new KitchenPersistence());
        System.out.println("[Catering Test - Kitchentask 3 ] - Start");

        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        User thisUser = CatERing.getInstance().getUserManager().getCurrentUser();

        System.out.println("User logged in: " + thisUser.toString());

        System.out.println("[Catering Test - Kitchentask 3 ] - Logged in with fake login");

        ShiftManager sm = CatERing.getInstance().getShiftManager();

        System.out.println("[Catering Test - Kitchentask 3 ] - Got the shift manager");

        try {

            DateFormat isoFormatter =  new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            Date startDate = isoFormatter.parse("2020-09-24T15:00:00.000");
            Date endDate = isoFormatter.parse("2020-09-26T15:00:00.000");
            System.out.println("[Catering Test - Kitchentask 3 ] - Getting the shift list from:" + startDate + " to " + endDate);
            List<Shift> shiftsInFrame = sm.getShiftList(startDate, endDate);
            System.out.println("[Catering Test - Kitchentask 3 ] - Got the list of shifts");
            for (Shift s : shiftsInFrame) {
                System.out.println("[Catering Test - Kitchentask 3 ] - Shift: \n" + s.toString());
            }

            System.out.println("[Catering Test - Kitchentask 3 ] - TEST SUCCESSFULL");
        } catch (Exception e) {

            System.out.println("[Catering Test - Kitchentask 3 ] - TEST FAILED");
            System.out.println("[Catering Test - Kitchentask 3 ] - EXCEPTION: " + e);
        }
    }
}

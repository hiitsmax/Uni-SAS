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
import catering.businesslogic.kitchenmanagement.summarysheet.SummarySheet;
import catering.businesslogic.usermanagement.UserException;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class TestCatERing1a {
    public static void main(String[] args) {
        System.out.println("[Catering Test - Kitchentask 1a] - Start");

        CatERing.getInstance().getUserManager().fakeLogin("topchef");

        System.out.println("[Catering Test - Kitchentask 1] - Logged in with fake login");

        
        EventManager em = CatERing.getInstance().getEventManager();
        KitchenManager km = CatERing.getInstance().getKitchenManager();


        System.out.println("[Catering Test - Kitchentask 1] - Got both event and kitchen managers");
        
        ObservableList<Service> services = em.getServices();
        Service s = services.get(0);


        System.out.println("[Catering Test - Kitchentask 1] - Got first service from event manager");

        try{
            
        System.out.println("[Catering Test - Kitchentask 1] - Creating SummarySheet for service with id "+s.getId());
            SummarySheet ss = km.openSummarySheet(s);
            
        System.out.println("[Catering Test - Kitchentask 1] - Got SummarySheet for service with id "+s.getId());
        System.out.println(ss.toString());

        System.out.println("[Catering Test - Kitchentask 1] - TEST SUCCESSFULL");
        } catch (UserException | ServiceException e) {
            System.out.println("Errore di logica nello use case");
        }

    }
}

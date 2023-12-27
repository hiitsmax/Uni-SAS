package catering.tests.kitchentasks;
import catering.businesslogic.CatERing;
import catering.businesslogic.UseCaseLogicException;
import catering.businesslogic.eventmanagement.event.Event;
import catering.businesslogic.eventmanagement.menu.Menu;
import catering.businesslogic.eventmanagement.menu.MenuException;
import catering.businesslogic.eventmanagement.menu.section.Section;
import catering.businesslogic.eventmanagement.service.ServiceInfo;
import catering.businesslogic.kitchenmanagement.kitchen.KitchenManager;
import catering.businesslogic.kitchenmanagement.recipe.Recipe;
import javafx.collections.ObservableList;

import java.util.Arrays;
import java.util.Map;
import java.util.Locale.Category;

public class TestCatERing1 {
    public static void main(String[] args) {
        CatERing.getInstance().getUserManager().fakeLogin("topchef");
        KitchenManager km = CatERing.getInstance().getKitchenManager();


        km.createSummarySheet(null);

        // try {

        // } catch (UseCaseLogicException | MenuException e) {
        //     System.out.println("Errore di logica nello use case");
        // }
    }
}

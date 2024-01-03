package catering.businesslogic;

import java.util.TimeZone;

import catering.businesslogic.eventmanagement.event.EventManager;
import catering.businesslogic.eventmanagement.menu.MenuManager;
import catering.businesslogic.kitchenmanagement.kitchen.KitchenManager;
import catering.businesslogic.kitchenmanagement.recipe.RecipeManager;
import catering.businesslogic.usermanagement.shift.ShiftManager;
import catering.businesslogic.usermanagement.user.UserManager;
import catering.persistence.KitchenPersistence;
import catering.persistence.MenuPersistence;
import catering.persistence.ShiftPersistence;

public class CatERing {
    private static CatERing singleInstance;

    public static CatERing getInstance() {
        if (singleInstance == null) {
            singleInstance = new CatERing();
        }
        return singleInstance;
    }

    private MenuManager menuMgr;
    private RecipeManager recipeMgr;
    private UserManager userMgr;
    private EventManager eventMgr;
    private KitchenManager kitchenMgr;
    private ShiftManager shiftMgr;

    private MenuPersistence menuPersistence;

    private CatERing() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));

        kitchenMgr = new KitchenManager();
        menuMgr = new MenuManager();
        recipeMgr = new RecipeManager();
        userMgr = new UserManager();
        eventMgr = new EventManager();
        shiftMgr = new ShiftManager();

        menuPersistence = new MenuPersistence();
        KitchenPersistence kitchenPersistence = new KitchenPersistence();
        ShiftPersistence shiftPersistence = new ShiftPersistence();

        kitchenMgr.addKitchenEventReceiver(kitchenPersistence);
        menuMgr.addEventReceiver(menuPersistence);
        shiftMgr.addEventReceiver(shiftPersistence);

     
    }

    public ShiftManager getShiftManager() {
        return shiftMgr;
    }

    public KitchenManager getKitchenManager() {
        return kitchenMgr;
    }

    public MenuManager getMenuManager() {
        return menuMgr;
    }

    public RecipeManager getRecipeManager() {
        return recipeMgr;
    }

    public UserManager getUserManager() {
        return userMgr;
    }

    public EventManager getEventManager() { return eventMgr; }

}

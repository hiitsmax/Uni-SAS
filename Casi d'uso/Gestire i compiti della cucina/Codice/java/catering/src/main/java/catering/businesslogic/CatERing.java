package catering.businesslogic;

import catering.businesslogic.eventmanagement.event.EventManager;
import catering.businesslogic.eventmanagement.menu.MenuManager;
import catering.businesslogic.kitchenmanagement.kitchen.KitchenManager;
import catering.businesslogic.kitchenmanagement.recipe.RecipeManager;
import catering.businesslogic.usermanagement.user.UserManager;
import catering.persistence.MenuPersistence;

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

    private MenuPersistence menuPersistence;

    private CatERing() {
        kitchenMgr = new KitchenManager();
        menuMgr = new MenuManager();
        recipeMgr = new RecipeManager();
        userMgr = new UserManager();
        eventMgr = new EventManager();
        menuPersistence = new MenuPersistence();
        menuMgr.addEventReceiver(menuPersistence);
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

package catering.businesslogic.kitchenmanagement.kitchen;

import java.util.ArrayList;

import catering.businesslogic.kitchenmanagement.preparation.Preparation;
import catering.businesslogic.kitchenmanagement.recipe.Recipe;
import catering.businesslogic.kitchenmanagement.summarysheet.SummarySheet;
import catering.businesslogic.kitchenmanagement.task.Task;

/**
 * This class is responsible for managing events related to the kitchen.
 */
public class KitchenEventManager {
    private Kitchen currentKitchen;
    private ArrayList<KitchenEventReceiver> eventReceivers;
    /**
     * Updates the summary sheet when it is created.
     * 
     * @param sh The summary sheet that was created.
     */
    public void notifySummarySheetCreated(SummarySheet sh) {
        for (KitchenEventReceiver er : this.eventReceivers) {
            er.updateSummarySheetCreated(sh);
        }
    }
    
    /**
     * Updates the summary sheet when it is deleted.
     * 
     * @param sh The summary sheet that was deleted.
     */
    public void notifySummarySheetDeleted(SummarySheet sh) {
        for (KitchenEventReceiver er : this.eventReceivers) {
            er.updateSummarySheetDeleted(sh);
        }
    }
    
    /**
     * Updates the task when it is created.
     * 
     * @param t The task that was created.
     */
    public void notifyTaskCreated(Task t, SummarySheet s) {
        for (KitchenEventReceiver er : this.eventReceivers) {
            er.updateTaskCreated(t, s);
        }
    }
    
    /**
     * Updates the task when it is deleted.
     * 
     * @param t The task that was deleted.
     */
    public void notifyTaskDeleted(Task t) {
        for (KitchenEventReceiver er : this.eventReceivers) {
            er.updateTaskDeleted(t);
        }
    }
    
    /**
     * Updates the task when it is modified.
     * 
     * @param t The task that was modified.
     */
    public void notifyTaskModified(Task t, SummarySheet s) {
        for (KitchenEventReceiver er : this.eventReceivers) {
            er.updateTaskModified(t, s);
        }
    }
    
    /**
     * Updates the recipe when it is created.
     * 
     * @param re The recipe that was created.
     */
    public void notifyRecipeCreated(Recipe re) {
        for (KitchenEventReceiver er : this.eventReceivers) {
            er.updateRecipeCreated(re);
        }
    }
    
    /**
     * Updates the recipe when it is deleted.
     * 
     * @param re The recipe that was deleted.
     */
    public void notifyRecipeDeleted(Recipe re) {
        for (KitchenEventReceiver er : this.eventReceivers) {
            er.updateRecipeDeleted(re);
        }    
    }
    
    /**
     * Updates the recipe when it is modified.
     * 
     * @param re The recipe that was modified.
     */
    public void notifyRecipeModified(Recipe re) {
        for (KitchenEventReceiver er : this.eventReceivers) {
            er.updateRecipeModify(re);
        }   
    }
    
    /**
     * Updates the preparation when it is created.
     * 
     * @param p The preparation that was created.
     */
    public void notifyPreparationCreated(Preparation p) {
        for (KitchenEventReceiver er : this.eventReceivers) {
            er.updatePreparationCreated(p);
        }
    }
    
    /**
     * Updates the preparation when it is deleted.
     * 
     * @param p The preparation that was deleted.
     */
    public void notifyPreparationDeleted(Preparation p) {
        for (KitchenEventReceiver er : this.eventReceivers) {
            er.updatePreparationDeleted(p);
        }
    }
    
    /**
     * Updates the preparation when it is modified.
     * 
     * @param p The preparation that was modified.
     */
    public void notifyPreparationModified(Preparation p) {
        for (KitchenEventReceiver er : this.eventReceivers) {
            er.updatePreparationModify(p);
        }
    }
}
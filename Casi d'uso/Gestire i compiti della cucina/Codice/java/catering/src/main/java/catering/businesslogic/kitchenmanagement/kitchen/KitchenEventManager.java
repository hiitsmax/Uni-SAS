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
    public void updateSummarySheetCreated(SummarySheet sh) {
        for (KitchenEventReceiver er : this.eventReceivers) {
            er.updateSummarySheetCreated(this.currentKitchen, sh);
        }
    }
    
    /**
     * Updates the summary sheet when it is deleted.
     * 
     * @param sh The summary sheet that was deleted.
     */
    public void updateSummarySheetDeleted(SummarySheet sh) {
        for (KitchenEventReceiver er : this.eventReceivers) {
            er.updateSummarySheetDeleted(this.currentKitchen, sh);
        }
    }
    
    /**
     * Updates the task when it is created.
     * 
     * @param t The task that was created.
     */
    public void updateTaskCreated(Task t) {
        for (KitchenEventReceiver er : this.eventReceivers) {
            er.updateTaskCreated(this.currentKitchen, sh);
        }
    }
    
    /**
     * Updates the task when it is deleted.
     * 
     * @param t The task that was deleted.
     */
    public void updateTaskDeleted(Task t) {
        for (KitchenEventReceiver er : this.eventReceivers) {
            er.updateTaskDeleted(this.currentKitchen, t);
        }
    }
    
    /**
     * Updates the task when it is modified.
     * 
     * @param t The task that was modified.
     */
    public void updateTaskModified(Task t) {
        for (KitchenEventReceiver er : this.eventReceivers) {
            er.updateTaskModified(this.currentKitchen, t);
        }
    }
    
    /**
     * Updates the recipe when it is created.
     * 
     * @param re The recipe that was created.
     */
    public void updateRecipeCreated(Recipe re) {
        for (KitchenEventReceiver er : this.eventReceivers) {
            er.updateTaskCreated(this.currentKitchen, re);
        }
    }
    
    /**
     * Updates the recipe when it is deleted.
     * 
     * @param re The recipe that was deleted.
     */
    public void updateRecipeDeleted(Recipe re) {
        for (KitchenEventReceiver er : this.eventReceivers) {
            er.updateRecipeDeleted(this.currentKitchen, re);
        }    
    }
    
    /**
     * Updates the recipe when it is modified.
     * 
     * @param re The recipe that was modified.
     */
    public void updateRecipeModified(Recipe re) {
        for (KitchenEventReceiver er : this.eventReceivers) {
            er.updateRecipeModified(this.currentKitchen, re);
        }   
    }
    
    /**
     * Updates the preparation when it is created.
     * 
     * @param p The preparation that was created.
     */
    public void updatePreparationCreated(Preparation p) {
        for (KitchenEventReceiver er : this.eventReceivers) {
            er.updatePreparationCreated(this.currentKitchen, p);
        }
    }
    
    /**
     * Updates the preparation when it is deleted.
     * 
     * @param p The preparation that was deleted.
     */
    public void updatePreparationDeleted(Preparation p) {
        for (KitchenEventReceiver er : this.eventReceivers) {
            er.updatePreparationDeleted(this.currentKitchen, p);
        }
    }
    
    /**
     * Updates the preparation when it is modified.
     * 
     * @param p The preparation that was modified.
     */
    public void updatePreparationModified(Preparation p) {
        for (KitchenEventReceiver er : this.eventReceivers) {
            er.updatePreparationModified(this.currentKitchen, p);
        }
    }
}
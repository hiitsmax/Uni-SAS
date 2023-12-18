package catering.businesslogic.kitchenmanagement.kitchen;

/**
 * The KitchenManager class is responsible for managing the kitchen operations.
 */
public class KitchenManager {
    
    /**
     * Notifies when a summary sheet is created.
     * 
     * @param s The summary sheet that was created.
     */
    public void notifySummarySheetCreated(SummarySheet s) {
        // Implementation goes here
    }
    
    /**
     * Notifies when a summary sheet is deleted.
     * 
     * @param s The summary sheet that was deleted.
     */
    public void notifySummarySheetDeleted(SummarySheet s) {
        // Implementation goes here
    }
    
    /**
     * Notifies when a task is created.
     * 
     * @param t The task that was created.
     */
    public void notifyTaskCreated(Task t) {
        // Implementation goes here
    }
    
    /**
     * Notifies when a task is modified.
     * 
     * @param t The task that was modified.
     */
    public void notifyTaskModified(Task t) {
        // Implementation goes here
    }
    
    /**
     * Notifies when a task is removed.
     * 
     * @param t The task that was removed.
     */
    public void notifyTaskRemoved(Task t) {
        // Implementation goes here
    }
    
    /**
     * Notifies when a recipe is created.
     * 
     * @param re The recipe that was created.
     */
    public void notifyRecipeCreated(Recipe re) {
        // Implementation goes here
    }
    
    /**
     * Notifies when a recipe is modified.
     * 
     * @param re The recipe that was modified.
     */
    public void notifyRecipeModified(Recipe re) {
        // Implementation goes here
    }
    
    /**
     * Notifies when a recipe is removed.
     * 
     * @param re The recipe that was removed.
     */
    public void notifyRecipeRemoved(Recipe re) {
        // Implementation goes here
    }
    
    /**
     * Notifies when a preparation is created.
     * 
     * @param p The preparation that was created.
     */
    public void notifyPreparationCreated(Preparation p) {
        // Implementation goes here
    }
    
    /**
     * Notifies when a preparation is removed.
     * 
     * @param p The preparation that was removed.
     */
    public void notifyPreparationRemoved(Preparation p) {
        // Implementation goes here
    }
    
    /**
     * Notifies when a preparation is modified.
     * 
     * @param p The preparation that was modified.
     */
    public void notifyPreparationModified(Preparation p) {
        // Implementation goes here
    }
    
    /**
     * Notifies when a task is assigned to a summary sheet.
     * 
     * @param t The task that was assigned.
     * @param s The summary sheet to which the task was assigned.
     */
    public void notifyAssignTask(Task t, SummarySheet s) {
        // Implementation goes here
    }
    
    /**
     * Creates a summary sheet for a service.
     * 
     * @param e The service for which the summary sheet is created.
     * @return The created summary sheet.
     */
    public SummarySheet createSummarySheet(Service e) {
        // Implementation goes here
    }
    
    /**
     * Deletes a summary sheet for a service.
     * 
     * @param e The service for which the summary sheet is deleted.
     */
    public void deleteSummarySheet(Service e) {
        // Implementation goes here
    }
    
    /**
     * Creates a task with the given name.
     * 
     * @param name The name of the task.
     * @return The created task.
     */
    public Task createTask(String name) {
        // Implementation goes here
    }
    
    /**
     * Deletes a task with the given name.
     * 
     * @param name The name of the task to delete.
     */
    public void deleteTask(String name) {
        // Implementation goes here
    }
    
    /**
     * Modifies a task with the given name.
     * 
     * @param name The name of the task to modify.
     * @return The modified task.
     */
    public Task modifyTask(String name) {
        // Implementation goes here
    }
    
    /**
     * Creates a recipe with the given name.
     * 
     * @param name The name of the recipe.
     * @return The created recipe.
     */
    public Recipe createRecipe(String name) {
        // Implementation goes here
    }
    
    /**
     * Deletes a recipe with the given name.
     * 
     * @param name The name of the recipe to delete.
     */
    public void deleteRecipe(String name) {
        // Implementation goes here
    }
    
    /**
     * Modifies a recipe with the given name.
     * 
     * @param name The name of the recipe to modify.
     * @return The modified recipe.
     */
    public Recipe modifyRecipe(String name) {
        // Implementation goes here
    }
    
    /**
     * Creates a preparation with the given name.
     * 
     * @param name The name of the preparation.
     * @return The created preparation.
     */
    public Preparation createPreparation(String name) {
        // Implementation goes here
    }
    
    /**
     * Deletes a preparation with the given name.
     * 
     * @param name The name of the preparation to delete.
     */
    public void deletePreparation(String name) {
        // Implementation goes here
    }
    
    /**
     * Modifies a preparation with the given name.
     * 
     * @param name The name of the preparation to modify.
     * @return The modified preparation.
     */
    public Preparation modifyPreparation(String name) {
        // Implementation goes here
    }
    
    /**
     * Retrieves the instructions for a recipe.
     * 
     * @return The task representing the recipe instructions.
     */
    public Task getRecipeInstructions() {
        // Implementation goes here
    }
    
    /**
     * Assigns a task to a user.
     * 
     * @param u The user to whom the task is assigned.
     * @param t The task to assign.
     * @return The assigned task.
     */
    public Task assignTask(User u, Task t) {
        // Implementation goes here
    }
    
    /**
     * Changes the assignment of a task to a cook.
     * 
     * @param t The task to change the assignment for.
     * @param cook The cook to whom the task is assigned.
     */
    public void changeTaskAssignment(Task t, User cook) {
        // Implementation goes here
    }
    
    /**
     * Assigns a cook to a service.
     * 
     * @param s The service to assign the cook to.
     * @param c The cook to assign.
     */
    public void assignCookToService(Service s, User c) {
        // Implementation goes here
    }
}
package catering.businesslogic.kitchenmanagement.kitchen;

import java.util.ArrayList;
import java.util.Date;

import catering.businesslogic.CatERing;
import catering.businesslogic.eventmanagement.event.Event;
import catering.businesslogic.eventmanagement.event.recurrency.Recurrency;
import catering.businesslogic.eventmanagement.service.Service;
import catering.businesslogic.eventmanagement.service.ServiceException;
import catering.businesslogic.kitchenmanagement.preparation.Preparation;
import catering.businesslogic.kitchenmanagement.recipe.Recipe;
import catering.businesslogic.kitchenmanagement.summarysheet.SummarySheet;
import catering.businesslogic.kitchenmanagement.task.Task;
import catering.businesslogic.kitchenmanagement.task.TaskListOrder;
import catering.businesslogic.usermanagement.UserException;
import catering.businesslogic.usermanagement.shift.ShiftManager;
import catering.businesslogic.usermanagement.user.User;

/**
 * The KitchenManager class is responsible for managing the kitchen operations.
 */
// TODO: Bring back notify to private
public class KitchenManager {
    ArrayList<KitchenEventReceiver> receivers = new ArrayList<>();
    private static SummarySheet currentSummarySheet;

    public KitchenManager() {
        Task.loadAllTasks();
        Recurrency.loadAllRecurrency();
        Event.loadAllEvent();
        Service.getAllServices();
    }

    public void addKitchenEventReceiver(KitchenEventReceiver r) {
        receivers.add(r);
    }

    /**
     * Notifies when a summary sheet is created.
     * 
     * @param s The summary sheet that was created.
     */
    public void notifySummarySheetCreated(SummarySheet s) {
        // Implementation goes here
        for (KitchenEventReceiver r : receivers)
            r.updateSummarySheetCreated(s);
    }

    private void notifySummarySheetUpdated(SummarySheet s) {

        for (KitchenEventReceiver r : receivers)
            r.updateSummarySheetUpdated(s);
    }

    /**
     * Notifies when a summary sheet is deleted.
     * 
     * @param s The summary sheet that was deleted.
     */
    public void notifySummarySheetDeleted(SummarySheet s) {
        for (KitchenEventReceiver r : receivers)
            r.updateSummarySheetDeleted(s);
    }

    /**
     * Notifies when a task is created.
     * 
     * @param t The task that was created.
     */
    public void notifyTaskCreated(Task t, SummarySheet s) {
        for (KitchenEventReceiver r : receivers)
            r.updateTaskCreated(t, s);
    }

    /**
     * Notifies when a task is modified.
     * 
     * @param t The task that was modified.
     */
    public void notifyTaskModified(Task t) {
        for (KitchenEventReceiver r : receivers)
            r.updateTaskModified(t, SummarySheet.getSummarySheetOfTask(t));
    }

    /**
     * Notifies when a task is removed.
     * 
     * @param t The task that was removed.
     */
    public void notifyTaskRemoved(Task t) {
        for (KitchenEventReceiver r : receivers)
            r.updateTaskDeleted(t);
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
        for (KitchenEventReceiver r : receivers)
            r.updateTaskModified(t, s);
    }

    /**
     * Creates a summary sheet for a service.
     * 
     * @param e The service for which the summary sheet is created.
     * @return The created summary sheet.
     */
    public SummarySheet createSummarySheet(Service s) throws UserException, ServiceException {

        if (CatERing.getInstance().getUserManager().getCurrentUser() == null)
            throw new UserException("No user logged in");

        if (!CatERing.getInstance().getUserManager().getCurrentUser().isChef())
            throw new UserException("User is not a chef");

        if (s.getSummarySheet() != null)
            throw new ServiceException("Summary sheet already exists");

        SummarySheet newSummarySheet = new SummarySheet(TaskListOrder.ByDifficulty, s);
        notifySummarySheetCreated(newSummarySheet);
        currentSummarySheet = newSummarySheet;
        return newSummarySheet;
    }

    /**
     * Deletes a summary sheet for a service.
     * 
     * @param e The service for which the summary sheet is deleted.
     * @throws UserException
     * @throws ServiceException
     */
    public void deleteSummarySheet(Service s) throws UserException, ServiceException {
        // Ridondante, teorico se è running vuol dire che ha un menù approvato
        // if(s.isRunning())
        // throw new ServiceException("Service is running");
        if (CatERing.getInstance().getUserManager().getCurrentUser() == null)
            throw new UserException("No user logged in");
        if (s.getSummarySheet() == null)
            throw new ServiceException("No summary sheet for this service");
        if (s.getApproved_menu_id() != null)
            throw new ServiceException("Menu for this service is already approved");
        // Insensato, un servizio non vale per più ricorrenze, non ha senso
        // concettualmente controllare questa cosa
        // Un servizio in caso di ricorrenza di evento viene replicata ed il summary
        // sheet di conseguenza
        // if(s.hasUnhappenedInstances())
        // throw new ServiceException("Service has unhappened instances");
        notifySummarySheetDeleted(s.getSummarySheet());
    }

    /**
     * Creates a task with the given name.
     * 
     * @param name The name of the task.
     * @return The created task.
     */
    public Task createTask(String name) {
        // Implementation goes here
        return null;
    }

    /**
     * Deletes a task with the given name.
     * 
     * @param name The name of the task to delete.
     * @throws ServiceException
     * @throws UserException
     */
    public void deleteTask(Task t) throws ServiceException, UserException {
        if (currentSummarySheet == null)
            throw new ServiceException("No summary sheet actually opened");
        if (!currentSummarySheet.getTaskList().contains(t))
            throw new ServiceException("Task is not in the summary sheet");
        if (!currentSummarySheet.getOwners().contains(CatERing.getInstance().getUserManager().getCurrentUser()))
            throw new UserException("User is not an owner of the service");

        notifyTaskRemoved(t);
    }

    /**
     * Modifies a task with the given name.
     * 
     * @param name The name of the task to modify.
     * @return The modified task.
     * @throws ServiceException
     */
    public void modifyTask(Task t) throws ServiceException {
        // Implementation goes here
        if (currentSummarySheet == null)
            throw new ServiceException("No summary sheet actually opened");
        if (!currentSummarySheet.getTaskList().contains(t))
            throw new ServiceException("Task is not in the summary sheet");
    if(!currentSummarySheet.getOwners().contains(CatERing.getInstance().getUserManager().getCurrentUser()))
        throw new ServiceException("User is not an owner of the service");
        if (t.getAssegnee() != null) {
            if (!isUserAvailableForTask(t, t.getAssegnee()))
                throw new ServiceException("User is not available in this time");
        }
        notifyTaskModified(t);
    }

    /**
     * Creates a recipe with the given name.
     * 
     * @param name The name of the recipe.
     * @return The created recipe.
     */
    public Recipe createRecipe(String name) {
        // Implementation goes here
        return null;
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
        return null;
    }

    /**
     * Creates a preparation with the given name.
     * 
     * @param name The name of the preparation.
     * @return The created preparation.
     */
    public Preparation createPreparation(String name) {
        // Implementation goes here
        return null;
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
        return null;
    }

    /**
     * Retrieves the instructions for a recipe.
     * 
     * @return The task representing the recipe instructions.
     */
    public Task getRecipeInstructions() {
        // Implementation goes here
        return null;
    }

    /**
     * Assigns a task to a user.
     * 
     * @param u The user to whom the task is assigned.
     * @param t The task to assign.
     * @return The assigned task.
     * @throws ServiceException
     */

    public Boolean isUserAvailableForTask(Task t, User u) {

        long recipeTime = t.getRecipe().getTimeToPrepare().getTime();
        SummarySheet currentSummarySheet = SummarySheet.getSummarySheetOfTask(t);
        Service currentService = Service.getServiceOfSummarySheet(currentSummarySheet);
        Date taskStart = new Date(currentService.getService_date().getTime() + currentService.getTime_start().getTime()
                + t.getStart().getTime());
        Date taskEnd = new Date(taskStart.getTime() + recipeTime);
        return CatERing.getInstance().getShiftManager().isUserAvailable(u, taskStart, taskEnd);
    }

    public Task assignTask(Task t, User u) throws ServiceException {
        // Let's get the task recepe time

        // TODO: Quando invece uno user ha un altro task assegnato nello stesso frame
        // che si fa?

        if (!isUserAvailableForTask(t, u))
            throw new ServiceException("User is not available in this time");
        if (currentSummarySheet == null)
            throw new ServiceException("No summary sheet actually opened");
            if(!currentSummarySheet.getOwners().contains(CatERing.getInstance().getUserManager().getCurrentUser()))
            throw new ServiceException("User is not an owner of the service");
        if (!currentSummarySheet.getTaskList().contains(t))
            throw new ServiceException("Task is not in the summary sheet");

        t.setAssegnee(u);
        notifyAssignTask(t, currentSummarySheet);
        return t;
    }

    // INFO: Inutile visto che che ricicli assignTask
    // TODO: Capire se ha senso differenziare le due operazioni
    // /**
    // * Changes the assignment of a task to a cook.
    // *
    // * @param t The task to change the assignment for.
    // * @param cook The cook to whom the task is assigned.
    // */
    // public void changeTaskAssignment(Task t, User cook) {
    // // Implementation goes here
    // }

    /**
     * Assigns a cook to a service.
     * 
     * @param s The service to assign the cook to.
     * @param c The cook to assign.
     * @throws ServiceException
     */
    // TODO: Aggiornare nome in DSD
    public void assignSupportCookToService(Service s, User c) throws ServiceException {
        Date start = new Date(s.getService_date().getTime() + s.getTime_start().getTime());
        Date end = new Date(s.getService_date().getTime() + s.getTime_end().getTime());
        boolean userIsAvailable = CatERing.getInstance().getShiftManager().isUserAvailable(c, start, end);

        // TODO: Aggiornare nei DSD che il cuoco deve essere già nello shift e non che
        // lo inserisco quando lo assegno

        if (!userIsAvailable)
            throw new ServiceException("User is not available in this time");
        if (!(c.isCook()))
            throw new ServiceException("User is not a cook");

        // TODO: Aggiungerte in DSD questo metodo
        s.setSupportCook(c);
        notifyAssignSupportCookToService(s, c);
    }

    private void notifyAssignSupportCookToService(Service s, User c) {
        for (KitchenEventReceiver r : receivers)
            r.updateSupportCookAssigned(s, c);
    }

    public SummarySheet openSummarySheet(Service s) throws UserException, ServiceException {
        if (CatERing.getInstance().getUserManager().getCurrentUser() == null)
            throw new UserException("No user logged in");
        if (!CatERing.getInstance().getUserManager().getCurrentUser().isChef())
            throw new UserException("User is not a chef");
        if (s.getSummarySheet() == null)
            throw new ServiceException("No summary sheet for this service");
        if (!s.getSummarySheet().getOwners().contains(CatERing.getInstance().getUserManager().getCurrentUser()))
            throw new UserException("User is not an owner of the service");
        if (s.getApproved_menu_id() != null)
            throw new ServiceException("Menu for this service is already approved");
        currentSummarySheet = s.getSummarySheet();
        return currentSummarySheet;
    }

    public void orderSummarySheetTasks(TaskListOrder order) throws ServiceException, UserException {
        // Implementation goes here
        if (CatERing.getInstance().getUserManager().getCurrentUser() == null)
            throw new UserException("No user logged in");
        if (currentSummarySheet == null)
            throw new ServiceException("No summary sheet actually opened");
        if (currentSummarySheet.getOwners().contains(CatERing.getInstance().getUserManager().getCurrentUser()))
            throw new UserException("User is not an owner of the service");
        // if(currentSummarySheet.getService().getApproved_menu_id()!=null)
        // throw new ServiceException("Menu for this service is already approved");

        currentSummarySheet.setOrder(order);

        switch (order) {
            case ByDifficulty:
                currentSummarySheet.getTaskList().sort(Task.byDifficulty);
                break;
            case ByImportance:
                currentSummarySheet.getTaskList().sort(Task.ByImportance);
                break;
            case Chronological:
                currentSummarySheet.getTaskList().sort(Task.byChrono);
                break;
        }

        notifySummarySheetUpdated(currentSummarySheet);
    }

    public void createTask(Task t, SummarySheet s) throws ServiceException, UserException {
        if (!s.getOwners().contains(CatERing.getInstance().getUserManager().getCurrentUser()))
            throw new UserException("User is not an owner of the service");

        notifyTaskCreated(t, s);
    }
}
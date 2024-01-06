package catering.persistence;

import catering.businesslogic.eventmanagement.service.Service;
import catering.businesslogic.kitchenmanagement.kitchen.KitchenEventReceiver;
import catering.businesslogic.kitchenmanagement.preparation.Preparation;
import catering.businesslogic.kitchenmanagement.recipe.Recipe;
import catering.businesslogic.kitchenmanagement.summarysheet.SummarySheet;
import catering.businesslogic.kitchenmanagement.task.Task;
import catering.businesslogic.usermanagement.user.User;

public class KitchenPersistence implements KitchenEventReceiver{

    @Override
    public void updateSummarySheetCreated(SummarySheet sh) {
        SummarySheet.saveNewSummarySheet(sh);
        //throw new UnsupportedOperationException("Unimplemented method 'updateSummarySheetCreated'");
    }

    @Override
    public void updateSummarySheetDeleted(SummarySheet sh) {
        SummarySheet.deleteSummarySheet(sh);
    }

    @Override
    public void updateTaskCreated(Task t, SummarySheet s) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateTaskCreated'");
    }

    @Override
    public void updateTaskDeleted(Task t) {
        Task.deleteTask(t);
    }

    @Override
    public void updateRecipeCreated(Recipe re) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateRecipeCreated'");
    }

    @Override
    public void updateRecipeDeleted(Recipe re) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateRecipeDeleted'");
    }

    @Override
    public void updateRecipeModify(Recipe re) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateRecipeModify'");
    }

    @Override
    public void updatePreparationCreated(Preparation p) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatePreparationCreated'");
    }

    @Override
    public void updatePreparationDeleted(Preparation p) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatePreparationDeleted'");
    }

    @Override
    public void updatePreparationModify(Preparation p) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatePreparationModify'");
    }

    @Override
    public void updateTaskModified(Task t, SummarySheet s) {
        Task.updateTask(t, s);
    }

    @Override
    public void updateSummarySheetUpdated(SummarySheet s) {
        SummarySheet.updateSummarySheet(s);
    }

    @Override
    public void updateSupportCookAssigned(Service s, User c) {
        Service.updateService(s);
    }
    
}

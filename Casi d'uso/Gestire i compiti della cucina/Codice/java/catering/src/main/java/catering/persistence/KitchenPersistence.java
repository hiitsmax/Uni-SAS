package catering.persistence;

import catering.businesslogic.kitchenmanagement.kitchen.KitchenEventReceiver;
import catering.businesslogic.kitchenmanagement.preparation.Preparation;
import catering.businesslogic.kitchenmanagement.recipe.Recipe;
import catering.businesslogic.kitchenmanagement.summarysheet.SummarySheet;
import catering.businesslogic.kitchenmanagement.task.Task;

public class KitchenPersistence implements KitchenEventReceiver{

    @Override
    public void updateSummarySheetCreated(SummarySheet sh) {
        SummarySheet.saveNewSummarySheet(sh);
        //throw new UnsupportedOperationException("Unimplemented method 'updateSummarySheetCreated'");
    }

    @Override
    public void updateSummarySheetDeleted(SummarySheet sh) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateSummarySheetDeleted'");
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
    
}

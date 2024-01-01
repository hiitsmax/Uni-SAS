package catering.businesslogic.kitchenmanagement.kitchen;

import catering.businesslogic.kitchenmanagement.preparation.Preparation;
import catering.businesslogic.kitchenmanagement.recipe.Recipe;
import catering.businesslogic.kitchenmanagement.summarysheet.SummarySheet;
import catering.businesslogic.kitchenmanagement.task.Task;

public interface KitchenEventReceiver {
    public void updateSummarySheetCreated(SummarySheet sh);

    public void updateSummarySheetDeleted(SummarySheet sh);

    public void updateTaskCreated(Task t);

    public void updateTaskDeleted(Task t);

    public void updateTaskModify(Task t);

    public void updateRecipeCreated(Recipe re);

    public void updateRecipeDeleted(Recipe re);

    public void updateRecipeModify(Recipe re);

    public void updatePreparationCreated(Preparation p);

    public void updatePreparationDeleted(Preparation p);

    public void updatePreparationModify(Preparation p);

    public void updateTaskModified(Task t);

    public void updateSummarySheetUpdated(SummarySheet s);
}

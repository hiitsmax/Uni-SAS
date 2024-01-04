package catering.businesslogic.kitchenmanagement.kitchen;

import catering.businesslogic.eventmanagement.service.Service;
import catering.businesslogic.kitchenmanagement.preparation.Preparation;
import catering.businesslogic.kitchenmanagement.recipe.Recipe;
import catering.businesslogic.kitchenmanagement.summarysheet.SummarySheet;
import catering.businesslogic.kitchenmanagement.task.Task;
import catering.businesslogic.usermanagement.user.User;

public interface KitchenEventReceiver {
    public void updateSummarySheetCreated(SummarySheet sh);

    public void updateSummarySheetDeleted(SummarySheet sh);

    public void updateTaskCreated(Task t, SummarySheet s);

    public void updateTaskDeleted(Task t);

    public void updateRecipeCreated(Recipe re);

    public void updateRecipeDeleted(Recipe re);

    public void updateRecipeModify(Recipe re);

    public void updatePreparationCreated(Preparation p);

    public void updatePreparationDeleted(Preparation p);

    public void updatePreparationModify(Preparation p);

    public void updateTaskModified(Task t, SummarySheet s);

    public void updateSummarySheetUpdated(SummarySheet s);

    public void updateSupportCookAssigned(Service s, User c);
}

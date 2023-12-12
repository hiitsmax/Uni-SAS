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
}

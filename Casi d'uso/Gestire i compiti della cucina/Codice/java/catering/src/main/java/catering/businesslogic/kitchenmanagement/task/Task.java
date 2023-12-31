package catering.businesslogic.kitchenmanagement.task;

import java.sql.Date;
import java.util.Comparator;

import catering.businesslogic.kitchenmanagement.recipe.Recipe;
import catering.businesslogic.usermanagement.user.User;

public class Task {
    private String name;
    private String[] ingredients;
    private String[] staffInstructions;
    private String alreadyDoneNote;
    private Recipe recipe;
    private Date start;
    private Date end;
    private User cook;
    private int id;

    public static Comparator<Task> byDifficulty = (Task t1, Task t2) -> Integer.compare(t1.getRecipe().getDifficulty(), t2.getRecipe().getDifficulty());
    public static Comparator<Task> ByImportance = (Task t1, Task t2) -> Integer.compare(t1.getImportance(), t2.getImportance());
    public static Comparator<Task> byChrono = (Task t1, Task t2) -> t1.getStart().compareTo(t2.getStart());
        
    public Recipe getRecipe() {
        return recipe;
    }

    private int getImportance() {
        return 0;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public User getCook() {
        return cook;
    }

    public void setCook(User cook) {
        this.cook = cook;
    }

    public void setAlreadyDoneNote(String note) {
        this.alreadyDoneNote = note;
    }

}

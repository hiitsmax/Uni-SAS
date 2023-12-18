package catering.businesslogic.kitchenmanagement.task;

import java.sql.Date;

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

    public Recipe getRecipe() {
        return recipe;
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

package catering.businesslogic.kitchenmanagement.task;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import catering.businesslogic.eventmanagement.service.Service;
import catering.businesslogic.kitchenmanagement.preparation.Preparation;
import catering.businesslogic.kitchenmanagement.recipe.Recipe;
import catering.businesslogic.kitchenmanagement.summarysheet.SummarySheet;
import catering.businesslogic.usermanagement.user.User;
import catering.persistence.PersistenceManager;
import catering.persistence.ResultHandler;

public class Task {
    private String name;
    private String ingredients;
    private String staffInstructions;
    private String notes;
    private Recipe recipe;
    private Preparation preparation;
    private SummarySheet summarySheet;
    private Date start;
    private Date end;
    private User assegnee;
    private int importance;
    private int difficulty;
    private int order;
    private int id;

    private static Map<Integer, Task> loadedTasks = new HashMap<>();

    public static Comparator<Task> byDifficulty = (Task t1, Task t2) -> Integer.compare(t1.getRecipe().getDifficulty(), t2.getRecipe().getDifficulty());
    public static Comparator<Task> ByImportance = (Task t1, Task t2) -> Integer.compare(t1.getImportance(), t2.getImportance());
    public static Comparator<Task> byChrono = (Task t1, Task t2) -> t1.getStart().compareTo(t2.getStart());
        
    public static Task getTaskById(int id) {
        Task taskToReturn = loadedTasks.get(id);
        if (taskToReturn == null) {
            loadedTasks.put(id, loadTaskById(id));
        }
        return loadedTasks.get(id);
    }

        public static Task loadTaskById(int id) {
        String query = "SELECT * FROM Tasks WHERE id = " + id;
        Task t = new Task();
        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                t.id = rs.getInt("id");
                //TODO: finish
            }
        });
        return t;
    }

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

    public User getAssegnee() {
        return assegnee;
    }

    public void setAssegnee(User cook) {
        this.assegnee = cook;
    }

    public void setNotes(String note) {
        this.notes = note;
    }

}

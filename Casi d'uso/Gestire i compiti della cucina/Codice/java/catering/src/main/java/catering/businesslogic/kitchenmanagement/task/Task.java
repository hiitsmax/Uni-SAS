package catering.businesslogic.kitchenmanagement.task;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    public static void loadAllTasks(){

        String query = "SELECT * FROM Tasks";
        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                Task t = new Task();
                t.id = rs.getInt("id");
                t.name = rs.getString("name");
                t.ingredients = rs.getString("ingredients");
                t.staffInstructions = rs.getString("staff_instructions");
                t.notes = rs.getString("notes");
                t.importance = rs.getInt("importance_value");
                t.difficulty = rs.getInt("difficulty_value");
                t.order = rs.getInt("order");
                t.start = rs.getDate("start_offset");
                t.end = rs.getDate("end_offset");
                t.recipe = Recipe.loadRecipeById(rs.getInt("recipe_id"));
                t.summarySheet = SummarySheet.getSummarySheetById(rs.getInt("summarysheet_id"));
                loadedTasks.put(t.id, t);
            }
        });
    }

    public static ArrayList<Task> getTasksBySummarySheet(SummarySheet summarySheet) {
        ArrayList<Task> tasks = new ArrayList<>();
        if(loadedTasks.isEmpty()) loadAllTasks();
        for (Task t : loadedTasks.values()) {
            if (t.summarySheet.getId()==summarySheet.getId()) {
                tasks.add(t);
            }
        }
        return tasks;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getStaffInstructions() {
        return staffInstructions;
    }

    public void setStaffInstructions(String staffInstructions) {
        this.staffInstructions = staffInstructions;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Preparation getPreparation() {
        return preparation;
    }

    public void setPreparation(Preparation preparation) {
        this.preparation = preparation;
    }

    public SummarySheet getSummarySheet() {
        return summarySheet;
    }

    public void setSummarySheet(SummarySheet summarySheet) {
        this.summarySheet = summarySheet;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public User getAssegnee() {
        return assegnee;
    }

    public void setAssegnee(User cook) {
        this.assegnee = cook;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getId() {
        return id;
    }

}

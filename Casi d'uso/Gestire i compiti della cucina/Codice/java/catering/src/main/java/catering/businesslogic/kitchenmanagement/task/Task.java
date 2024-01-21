package catering.businesslogic.kitchenmanagement.task;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import catering.businesslogic.eventmanagement.service.Service;
import catering.businesslogic.kitchenmanagement.preparation.Preparation;
import catering.businesslogic.kitchenmanagement.recipe.Recipe;
import catering.businesslogic.kitchenmanagement.summarysheet.SummarySheet;
import catering.businesslogic.usermanagement.user.User;
import catering.persistence.BatchUpdateHandler;
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

    public static Map<Integer, Task> loadedTasks = new HashMap<>();

    public static Comparator<Task> byDifficulty = (Task t1, Task t2) -> Integer.compare(t1.getRecipe().getDifficulty(),
            t2.getRecipe().getDifficulty());
    public static Comparator<Task> ByImportance = (Task t1, Task t2) -> Integer.compare(t1.getImportance(),
            t2.getImportance());
    public static Comparator<Task> byChrono = (Task t1, Task t2) -> t1.getStart().compareTo(t2.getStart());

    public static Task getTaskById(int id) {
        Task taskToReturn = loadedTasks.get(id);
        if (taskToReturn == null) {
            loadedTasks.put(id, loadTaskById(id));
        }
        return loadedTasks.get(id);
    }

    public static void loadAllTasks() {
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
        loadedTasks.put(t.id, t);
            }
        });
    }

    public static ArrayList<Task> getTasksBySummarySheet(SummarySheet summarySheet) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task t : loadedTasks.values()) {
            if (t.summarySheet.getId() == summarySheet.getId()) {
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

@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Task other = (Task) obj;
        if (id != other.id)
            return false;
        return true;
    }

@Override
public String toString() {
    return "{" +
            "\"name\": \"" + name + "\"," +
            "\"ingredients\": \"" + ingredients + "\"," +
            "\"staffInstructions\": \"" + staffInstructions + "\"," +
            "\"notes\": \"" + notes + "\"," +
            "\"recipe\": " + recipe + "," +
            "\"preparation\": " + preparation + "," +
            "\"summarySheet\": " + summarySheet + "," +
            "\"start\": \"" + start + "\"," +
            "\"end\": \"" + end + "\"," +
            "\"assegnee\": " + assegnee + "," +
            "\"importance\": " + importance + "," +
            "\"difficulty\": " + difficulty + "," +
            "\"order\": " + order + "," +
            "\"id\": " + id +
            "}";
}

public static void updateTask(Task t, SummarySheet s) {
    String assegnee_id = t.getAssegnee() == null ? "NULL" : "'" + t.getAssegnee().getId() + "'";
    SimpleDateFormat timeFormatter = new SimpleDateFormat("HHmmss");
    String query = "UPDATE Tasks SET assegnee_id="+assegnee_id+", name = '" + t.getName() + "', ingredients = '" + t.getIngredients() + "', staff_instructions = '" + t.getStaffInstructions() + "', notes = '" + t.getNotes() + "', recipe_id = " + t.getRecipe().getId() + ", start_offset = " + timeFormatter.format(t.getStart()) + ", end_offset = " + timeFormatter.format(t.getEnd()) + ", importance_value = " + t.getImportance() + ", difficulty_value = " + t.getDifficulty() + ", Tasks.order = " + t.getOrder() + ", summarysheet_id = " + s.getId() + " WHERE id = " + t.getId();
    PersistenceManager.executeUpdate(query);
}

public static void deleteTask(Task t) {
    String query = "DELETE FROM Tasks WHERE id = " + t.getId();
    PersistenceManager.executeUpdate(query);
}

public static Task saveNewTask(Task task, SummarySheet summarySheet) {
        String taskNotes = task.getNotes()==null ? " " : task.getNotes();
        String taskInsert = "INSERT INTO Tasks (name, ingredients, staff_instructions, notes, recipe_id, start_offset, end_offset, importance_value, difficulty_value, Tasks.order, summarysheet_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int[] result = PersistenceManager.executeBatchUpdate(taskInsert, 1, new BatchUpdateHandler() {
            @Override
            public void handleBatchItem(PreparedStatement ps, int batchCount) throws SQLException {
                ps.setString(1, task.getName());
                ps.setString(2, task.getIngredients());
                ps.setString(3, task.getStaffInstructions());
                ps.setString(4, taskNotes);
                ps.setInt(5, task.getRecipe().getId());
                SimpleDateFormat timeFormatter = new SimpleDateFormat("HHmmss");
                ps.setString(6, timeFormatter.format(task.getStart()));
                ps.setString(7, timeFormatter.format(task.getEnd()));
                ps.setInt(8, task.getImportance());
                ps.setInt(9, task.getDifficulty());
                ps.setInt(10, task.getOrder());
                ps.setInt(11, summarySheet.getId());
            }

            @Override
            public void handleGeneratedIds(ResultSet rs, int count) throws SQLException {
                // should be only one
                if (count == 0) {
                    task.id=rs.getInt(1);
                }
            }
        });
        summarySheet.getTaskList().add(task);
        return task;
}
}

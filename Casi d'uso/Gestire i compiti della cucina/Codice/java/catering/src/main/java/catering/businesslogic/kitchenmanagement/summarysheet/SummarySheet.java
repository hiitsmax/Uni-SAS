
package catering.businesslogic.kitchenmanagement.summarysheet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import catering.businesslogic.CatERing;
import catering.businesslogic.eventmanagement.event.Event;
import catering.businesslogic.eventmanagement.menu.Menu;
import catering.businesslogic.eventmanagement.menu.menuitem.MenuItem;
import catering.businesslogic.eventmanagement.menu.section.Section;
import catering.businesslogic.eventmanagement.service.Service;
import catering.businesslogic.kitchenmanagement.task.Task;
import catering.businesslogic.kitchenmanagement.task.TaskListOrder;
import catering.businesslogic.usermanagement.user.User;
import catering.persistence.BatchUpdateHandler;
import catering.persistence.PersistenceManager;
import catering.persistence.ResultHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SummarySheet {
    private int id;
    private TaskListOrder order;
    private boolean editable;
    private Service service;
    private Set<User> owners;
    private ArrayList<Task> taskList;

    private static Map<Integer, SummarySheet> loadedSummarySheets = FXCollections.observableHashMap();

    public static SummarySheet getSummarySheetById(int id) {
        SummarySheet summarySheetToReturn = loadedSummarySheets.get(id);
        if (summarySheetToReturn == null) {
            loadedSummarySheets.put(id, loadSummarySheetById(id));
        }
        return loadedSummarySheets.get(id);
    }

    public int getId() {
        return id;
    }

    public static void loadAllSummarySheets(){
        String query = "SELECT * FROM SummarySheets ";
        SummarySheet s = new SummarySheet();
        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                s.id = rs.getInt("id");
                s.order = TaskListOrder.values()[rs.getInt("taskorder")];
                // TODO:This should be computed
                // s.editable = rs.getBoolean("editable");
                loadedSummarySheets.put(s.id, s);
        // ottengo il servizio
        s.service = Service.getServiceBySummarySheetId(s.id);

        //ottengo i tasks
        s.taskList = Task.getTasksBySummarySheet(s);
            }
        });


        // ottiene gli owners
        String query2 = "SELECT * FROM SummarySheetsOwners WHERE summarysheet_id = " + s.id;
        PersistenceManager.executeQuery(query2, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                int uid = rs.getInt("user_id");
                User u = User.loadUserById(uid);
                s.owners.add(u);
            }
        });

    }

    private static ArrayList<Task> getSummarySheetsTaskList(SummarySheet s){
        String query = "SELECT * FROM Tasks WHERE summarysheet_id = " + s.id;
        ArrayList<Task> tasks2 = new ArrayList<>();
        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                int id = rs.getInt("id");
                tasks2.add(Task.loadTaskById(id));
            }
        });
        return tasks2;
    }
    public static SummarySheet getSummarySheetOfTask(Task t){
        for(SummarySheet s : loadedSummarySheets.values()){
            if(s.taskList.contains(t)){
                return s;
            }
        }
        return null;
    }

    public static SummarySheet loadSummarySheetById(int id) {
        String query = "SELECT * FROM SummarySheets WHERE id = " + id;
        SummarySheet s = new SummarySheet();
        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                s.id = rs.getInt("id");
                s.order = TaskListOrder.values()[rs.getInt("taskorder")];
                // TODO:This should be computed
                // s.editable = rs.getBoolean("editable");
            }
        });

        // ottengo il servizio
        s.service = Service.getServiceBySummarySheetId(s.id);

        //ottengo i tasks
        s.taskList = getSummarySheetsTaskList(s);
        // ottiene gli owners
        String query2 = "SELECT * FROM SummarySheetsOwners WHERE summarysheet_id = " + s.id;
        PersistenceManager.executeQuery(query2, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                int uid = rs.getInt("user_id");
                User u = User.loadUserById(uid);
                s.owners.add(u);
            }
        });

        //ottiene tutti i task e li aggiunge alla lista
        
        return s;
    }


    public Set<User> getOwners() {
        return owners;
    }

    public void setOwners(Set<User> owners) {
        this.owners = owners;
    }

    public SummarySheet() {
        this.editable = false;
        this.order = TaskListOrder.Chronological;
        this.service = null;
        this.owners = FXCollections.observableSet();
        this.taskList = new ArrayList<>();
    }

    public SummarySheet(TaskListOrder order, Service service) {
        // this should be coputed
        this.editable = false;
        this.order = order;
        this.service = service;
        this.owners = FXCollections.observableSet();
        this.owners.add(CatERing.getInstance().getUserManager().getCurrentUser());
        this.owners.add(service.getEvent().getOrganizer());
        this.taskList = new ArrayList<>();
        // TODO: Implement chef
        // this.owners.add(service.getChef());
    }

    public static void deleteSummarySheet(SummarySheet s) {
        String query = "DELETE FROM SummarySheets WHERE id = " + s.id;
        PersistenceManager.executeUpdate(query);
        loadedSummarySheets.remove(s.id);
    }

    public static void saveNewSummarySheet(SummarySheet s) {

        String menuInsert = "INSERT INTO SummarySheets (taskorder) VALUES (?);";
        int[] result = PersistenceManager.executeBatchUpdate(menuInsert, 1, new BatchUpdateHandler() {
            @Override
            public void handleBatchItem(PreparedStatement ps, int batchCount) throws SQLException {
                ps.setInt(1, s.order.ordinal());
            }

            @Override
            public void handleGeneratedIds(ResultSet rs, int count) throws SQLException {
                // should be only one
                if (count == 0) {
                    s.id = rs.getInt(1);
                }
            }
        });

        if (result[0] > 0) { // menu effettivamente inserito
            // salva le features
            loadedSummarySheets.put(s.id, s);
            // mette l'id nel servizio
            PersistenceManager.executeUpdate(
                    "UPDATE Services SET summarysheet_id = " + s.id + " WHERE id = " + s.service.getId());
            // salva gli owners
            for (User u : s.owners) {
                String ownerInsert = "INSERT INTO SummarySheetsOwners (summarysheet_id, user_id) VALUES (?, ?);";
                PersistenceManager.executeBatchUpdate(ownerInsert, 1, new BatchUpdateHandler() {
                    @Override
                    public void handleBatchItem(PreparedStatement ps, int batchCount) throws SQLException {
                        ps.setInt(1, s.id);
                        ps.setInt(2, u.getId());
                    }

                    @Override
                    public void handleGeneratedIds(ResultSet rs, int count) throws SQLException {
                        // do nothing
                    }
                });
            }
        }
    }

    public void setOrder(TaskListOrder order) {
        // Method implementation
    }

    public void orderTaskList(TaskListOrder order) {
        // Method implementation
    }

    public void setEditable(boolean flag) {
        // Method implementation
    }

    public void addTaskToSheet(Task t) {
        // Method implementation
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList() {
        // Method implementation
    }

    public void move(int index, Task t) {
        // Method implementation
    }

    public void setService(Service s) {
        this.service = s;
    }

    public static ObservableList<SummarySheet> getAllSummarySheets() {
        String query = "SELECT * FROM SummarySheets WHERE " + true;
        ArrayList<SummarySheet> newSummarySheets = new ArrayList<>();
        ArrayList<Integer> newSids = new ArrayList<>();
        ArrayList<SummarySheet> oldSummarySheet = new ArrayList<>();
        ArrayList<Integer> oldSSids = new ArrayList<>();

        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                int id = rs.getInt("id");
                if (loadedSummarySheets.containsKey(id)) {
                    SummarySheet s = loadedSummarySheets.get(id);
                    s.order = TaskListOrder.valueOf(rs.getString("order"));

                    // TODO:This should be computed
                    // s.editable = rs.getBoolean("editable");

                    oldSSids.add(id);
                    oldSummarySheet.add(s);
                } else {
                    TaskListOrder order = TaskListOrder.valueOf(rs.getString("order"));
                    Service serviceToGet = Service.getServiceBySummarySheetId(id);
                    SummarySheet s = new SummarySheet(order, serviceToGet);
                    s.id = id;

                    // TODO:This should be computed
                    // s.editable = rs.getBoolean("editable");

                    newSids.add(id);
                    newSummarySheets.add(s);
                }
            }
        });

        for (SummarySheet s : newSummarySheets) {
            loadedSummarySheets.put(s.id, s);
            s.service = Service.getServiceBySummarySheetId(s.id);
            //ottiene i tasks
            s.taskList = Task.getTasksBySummarySheet(s);
            // ottiene gli owners
            String query2 = "SELECT * FROM SummarySheetsOwners WHERE summarysheet_id = " + s.id;
            PersistenceManager.executeQuery(query2, new ResultHandler() {
                @Override
                public void handle(ResultSet rs) throws SQLException {
                    int uid = rs.getInt("user_id");
                    User u = User.loadUserById(uid);
                    s.owners.add(u);
                }
            });
        }
        return FXCollections.observableArrayList(loadedSummarySheets.values());

    }

    public static void updateSummarySheet(SummarySheet s) {
        String query = "UPDATE SummarySheets SET  taskorder = ? WHERE id = " + s.id;
        PersistenceManager.executeBatchUpdate(query, 1, new BatchUpdateHandler() {
            @Override
            public void handleBatchItem(PreparedStatement ps, int batchCount) throws SQLException {
                ps.setInt(1, s.order.ordinal());
            }

            @Override
            public void handleGeneratedIds(ResultSet rs, int count) throws SQLException {
                // do nothing
            }
        });
        // salva gli owners
        String ownerDelete = "DELETE FROM SummarySheetsOwners WHERE summarysheet_id=?";
        PersistenceManager.executeBatchUpdate(ownerDelete, 1, new BatchUpdateHandler() {
            @Override
            public void handleBatchItem(PreparedStatement ps, int batchCount) throws SQLException {
                ps.setInt(1, s.id);
            }

            @Override
            public void handleGeneratedIds(ResultSet rs, int count) throws SQLException {
                // do nothing
            }
        });
        for (User u : s.owners) {
            String ownerInsert = "INSERT INTO SummarySheetsOwners (summarysheet_id, user_id) VALUES (?, ?);";
            PersistenceManager.executeBatchUpdate(ownerInsert, 1, new BatchUpdateHandler() {
                @Override
                public void handleBatchItem(PreparedStatement ps, int batchCount) throws SQLException {
                    ps.setInt(1, s.id);
                    ps.setInt(2, u.getId());
                }

                @Override
                public void handleGeneratedIds(ResultSet rs, int count) throws SQLException {
                    // do nothing
                }
            });
        }

        }

        @Override
        public String toString() {
            StringBuilder json = new StringBuilder();
            json.append("{\n");
            json.append("    \"id\": ").append(id).append(",\n");
            json.append("    \"order\": \"").append(order).append("\",\n");
            json.append("    \"editable\": ").append(editable).append(",\n");
            json.append("    \"service\": \"").append(service).append("\",\n");
            json.append("    \"owners\": [\n");
            for (User owner : owners) {
                json.append("        \"").append(owner).append("\",\n");
            }
            if (!owners.isEmpty()) {
                json.deleteCharAt(json.length() - 2); // Remove the trailing comma and newline character
            }
            json.append("    ],\n");
            json.append("    \"taskList\": [\n");
            for (Task task : taskList) {
                json.append("        \"").append(task).append("\",\n");
            }
            if (!taskList.isEmpty()) {
                json.deleteCharAt(json.length() - 2); // Remove the trailing comma and newline character
            }
            json.append("    ]\n");
            json.append("}");
            return json.toString();
        }
}

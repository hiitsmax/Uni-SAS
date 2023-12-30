
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

    private static Map<Integer, SummarySheet> loadedSummarySheets = FXCollections.observableHashMap();

    public static SummarySheet getSummarySheetById(int id) {
        SummarySheet summarySheetToReturn = loadedSummarySheets.get(id);
        if (summarySheetToReturn == null) {
            loadedSummarySheets.put(id, loadSummarySheetById(id));
        }
        return loadedSummarySheets.get(id);
    }

    public static SummarySheet loadSummarySheetById(int id) {
        String query = "SELECT * FROM SummarySheets WHERE id = " + id;
        SummarySheet s = new SummarySheet();
        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                s.id = rs.getInt("id");
                s.order = TaskListOrder.values()[rs.getInt("taskorder")];
                //TODO:This should be computed
                //s.editable = rs.getBoolean("editable");
                s.service = Service.getServiceById(rs.getInt("service_id"));
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
        return s;
    }

    public Set<User> getOwners() {
        return owners;
    }

    public void setOwners(Set<User> owners) {
        this.owners = owners;
    }
    public SummarySheet(){
        this.editable = false;
        this.order = TaskListOrder.Chronological;
        this.service = null;
        this.owners = FXCollections.observableSet();
    }

    public SummarySheet(TaskListOrder order, Service service) {
        // this should be coputed
        this.editable = false;
        this.order = order;
        this.service = service;
        this.owners = FXCollections.observableSet();
        this.owners.add(CatERing.getInstance().getUserManager().getCurrentUser());
        this.owners.add(service.getEvent().getOrganizer());
        //TODO: Implement chef
        //this.owners.add(service.getChef());
    }

    public static void deleteSummarySheet(SummarySheet s) {
        String query = "DELETE FROM SummarySheets WHERE id = " + s.id;
        PersistenceManager.executeUpdate(query);
        loadedSummarySheets.remove(s.id);
    }

    public static void saveNewSummarySheet(SummarySheet s) {

        String menuInsert = "INSERT INTO SummarySheets (service_id, taskorder) VALUES (?, ?);";
        int[] result = PersistenceManager.executeBatchUpdate(menuInsert, 1, new BatchUpdateHandler() {
            @Override
            public void handleBatchItem(PreparedStatement ps, int batchCount) throws SQLException {
                ps.setInt(1, s.service.getId());
                ps.setInt(2, s.order.ordinal());
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
            PersistenceManager.executeUpdate("UPDATE Services SET summarysheet_id = " + s.id + " WHERE id = " + s.service.getId());
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
        // Method implementation
        return null;
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
                    s.service = Service.getServiceById(rs.getInt("service_id"));
                    s.order = TaskListOrder.valueOf(rs.getString("order"));

                    //TODO:This should be computed
                    //s.editable = rs.getBoolean("editable");

                    oldSSids.add(id);
                    oldSummarySheet.add(s);
                } else {
                    Service serviceToGet = Service.getServiceById(rs.getInt("service_id"));
                    TaskListOrder order = TaskListOrder.valueOf(rs.getString("order"));
                    SummarySheet s = new SummarySheet(order, serviceToGet);
                    s.id = id;

                    //TODO:This should be computed
                    //s.editable = rs.getBoolean("editable");
                    
                    newSids.add(id);
                    newSummarySheets.add(s);
                }
            }
        });
        
        for (SummarySheet s: newSummarySheets) {
            loadedSummarySheets.put(s.id, s);
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
}

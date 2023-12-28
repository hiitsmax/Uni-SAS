
package catering.businesslogic.kitchenmanagement.summarysheet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

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

    private static Map<Integer, SummarySheet> loadedSummarySheets = FXCollections.observableHashMap();

    public SummarySheet create(boolean order, boolean editable) {
        // Method implementation
        return null;
    }

    public static void saveNewSummarySheet(SummarySheet s) {

        String menuInsert = "INSERT INTO catering.SummarySheets (service_id, order) VALUES (?, ?);";
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

    public static ObservableList<SummarySheet> getAllServices() {
        String query = "SELECT * FROM Services WHERE " + true;
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
                    s.service = Service.getServiceById(id);
                    s.editable = rs.getBoolean("editable");
                    s.order = TaskListOrder.valueOf(rs.getString("order"));

                    oldSSids.add(id);
                    oldSummarySheet.add(s);
                } else {
                    SummarySheet s = new SummarySheet();
                    s.id = id;

                    s.service = Service.getServiceById(id);
                    s.editable = rs.getBoolean("editable");
                    s.order = TaskListOrder.valueOf(rs.getString("order"));
                    
                    newSids.add(id);
                    newSummarySheets.add(s);
                }
            }
        });
        
        for (SummarySheet s: newSummarySheets) {
            loadedSummarySheets.put(s.id, s);
        }
        return FXCollections.observableArrayList(loadedSummarySheets.values());

    }
}

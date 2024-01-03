package catering.businesslogic.usermanagement.shift;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

import catering.businesslogic.eventmanagement.menu.menuitem.MenuItem;
import catering.businesslogic.eventmanagement.menu.section.Section;
import catering.businesslogic.usermanagement.user.User;
import catering.persistence.BatchUpdateHandler;
import catering.persistence.PersistenceManager;
import catering.persistence.ResultHandler;
import javafx.collections.FXCollections;

public class Shift {
    private int id;
    private Date start;
    private Date end;
    private ShiftType type;
    private int limit;
    private ArrayList<User> attendances;

    private static Map<Integer, Shift> shiftMap = FXCollections.observableHashMap();
    
    public Shift(Date start, Date end, ShiftType type, int limit) {
        this.id = 0;
        this.start = start;
        this.end = end;
        this.type = type;
        this.limit = limit;
        this.attendances = new ArrayList<>();
    }

    public static Map<Integer, Shift> getLoadedShifts() {
        return shiftMap;
    }

    public static void loadAllShifts() {
        String shiftQuery = "SELECT * FROM Shifts;";
        PersistenceManager.executeQuery(shiftQuery, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                    ShiftType type = rs.getInt("type") == 0 ? ShiftType.PREPARATORY : ShiftType.SERVICE;
                    Shift sh = new Shift(new Date(rs.getTimestamp("start").getTime()), new Date(rs.getTimestamp("end").getTime()), type, rs.getInt("limit"));
                    sh.id = rs.getInt("id");
                    shiftMap.put(sh.id, sh);
                    // now we have to get all attendences in list from ShiftAttendances table
                    String attendancesQuery = "SELECT * FROM ShiftAttendances WHERE shift_id = " + sh.id + ";";
                    PersistenceManager.executeQuery(attendancesQuery, new ResultHandler() {
                        @Override
                        public void handle(ResultSet rs) throws SQLException {
                            int user_id = rs.getInt("user_id");
                            User u = User.loadUserById(user_id);
                            sh.attendances.add(u);
                        }
                    });
            }
        });
    }
    
    private int getId() {
        return id;
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
    public ShiftType getType() {
        return type;
    }
    public void setType(ShiftType type) {
        this.type = type;
    }
    public int getLimit() {
        return limit;
    }
    public void setLimit(int limit) {
        this.limit = limit;
    }
    public boolean isInTimeframe(java.util.Date startDate, java.util.Date endDate) {
        return (start.compareTo(startDate) >= 0 && end.compareTo(endDate) <= 0);
    }

    // STATIC METHODS FOR PERSISTENCE

    public static void saveNewShift(Shift sh) {
        String shiftInsert = "INSERT INTO Shifts (start, end, type, limit) VALUES (?, ?, ?, ?);";
        int[] result = PersistenceManager.executeBatchUpdate(shiftInsert, 1, new BatchUpdateHandler() {
            @Override
            public void handleBatchItem(PreparedStatement ps, int batchCount) throws SQLException {
                ps.setDate(1, sh.start);
                ps.setDate(2, sh.end);
                ps.setString(3, PersistenceManager.escapeString(sh.type.toString()));
                ps.setInt(4, sh.limit);
            }

            @Override
            public void handleGeneratedIds(ResultSet rs, int count) throws SQLException {
                // should be only one
                if (count == 0) {
                    sh.id = rs.getInt(1);
                }
            }
        });
    }

    public static void deleteShift(Shift sh) {
        String delSh = "DELETE FROM Shifts WHERE id = " + sh.id;
        PersistenceManager.executeUpdate(delSh);
    }

    public static void saveShift(Shift sh) {
        String upd = "UPDATE Shifts SET start = '" + sh.getStart() +
        "' AND end = '" + sh.getEnd() + "' AND type = '" + PersistenceManager.escapeString(sh.getType().toString()) +
        "' AND limit = " + sh.getLimit() + " WHERE id = " + sh.getId();
        PersistenceManager.executeUpdate(upd);
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuilder sb = new StringBuilder();
        sb.append("{\n")
                .append("    \"id\": ").append(id).append(",\n")
                .append("    \"start\": \"").append(dateFormat.format(start)).append("\",\n")
                .append("    \"end\": \"").append(dateFormat.format(end)).append("\",\n")
                .append("    \"type\": \"").append(type).append("\",\n")
                .append("    \"limit\": ").append(limit).append(",\n")
                .append("    \"attendances\": [");
        for (User user : attendances) {
            sb.append("\n        ").append(user.toString()).append(",");
        }
        if (!attendances.isEmpty()) {
            sb.delete(sb.length() - 1, sb.length());
            sb.append("\n    ");
        }
        sb.append("\n    ]\n}");
        return sb.toString();
    }

    public ArrayList<User> getAttendances() {
        return attendances;
    }
}

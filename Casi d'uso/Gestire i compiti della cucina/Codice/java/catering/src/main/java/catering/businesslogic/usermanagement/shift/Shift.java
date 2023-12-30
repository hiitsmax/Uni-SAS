package catering.businesslogic.usermanagement.shift;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import catering.businesslogic.eventmanagement.menu.menuitem.MenuItem;
import catering.businesslogic.eventmanagement.menu.section.Section;
import catering.persistence.BatchUpdateHandler;
import catering.persistence.PersistenceManager;

public class Shift {
    private int id;
    private Date start;
    private Date end;
    private ShiftType type;
    private int limit;


    
    public Shift(Date start, Date end, ShiftType type, int limit) {
        this.id = 0;
        this.start = start;
        this.end = end;
        this.type = type;
        this.limit = limit;
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
    public boolean isInTimeframe(Date startDate, Date endDate) {
        return false;
    }

    // STATIC METHODS FOR PERSISTENCE

    public static void saveNewShift(Shift sh) {
        String shiftInsert = "INSERT INTO catering.Shifts (start, end, type, limit) VALUES (?, ?, ?, ?);";
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
}

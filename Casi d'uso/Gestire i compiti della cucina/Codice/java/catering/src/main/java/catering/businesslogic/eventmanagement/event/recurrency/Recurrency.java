package catering.businesslogic.eventmanagement.event.recurrency;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import catering.businesslogic.eventmanagement.event.Event;
import catering.businesslogic.eventmanagement.event.penal.Penal;
import catering.persistence.BatchUpdateHandler;
import catering.persistence.PersistenceManager;

public class Recurrency {
    private Event event;
    private java.sql.Date startRecurrency;
    private java.sql.Date endRecurrency;
    private int recurrencyCount;

    public Recurrency(Event event, Date startRecurrency, Date endRecurrency, int recurrencyCount,
            String recurrencyType,
            int occurrency) {
        this.event = event;
        this.startRecurrency = startRecurrency;
        this.endRecurrency = endRecurrency;
        this.recurrencyCount = recurrencyCount;
    }

    public java.sql.Date getStartRecurrency() {
        return startRecurrency;
    }

    public void setStartRecurrency(java.sql.Date startRecurrency) {
        this.startRecurrency = startRecurrency;
    }

    public java.sql.Date getEndRecurrency() {
        return endRecurrency;
    }

    public void setEndRecurrency(java.sql.Date endRecurrency) {
        this.endRecurrency = endRecurrency;
    }

    public int getRecurrencyCount() {
        return recurrencyCount;
    }

    public void setRecurrencyCount(int recurrencyCount) {
        this.recurrencyCount = recurrencyCount;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    // Persistence methods

    public static void saveRecurrency(Recurrency r) throws SQLException {
        String recurrencyInsert = "INSERT INTO catering.Recurrency (startRecurrency, endRecurrency, recurrencyCount) VALUES (?, ?, ?);";

        int[] result = PersistenceManager.executeBatchUpdate(recurrencyInsert, 1, new BatchUpdateHandler() {
            @Override
            public void handleBatchItem(PreparedStatement ps, int batchCount) throws SQLException {
                ps.setDate(1, r.startRecurrency);
                ps.setDate(2, r.endRecurrency);
                ps.setInt(3, r.recurrencyCount);
            }

            @Override
            public void handleGeneratedIds(ResultSet rs, int count) throws SQLException {
                if (count == 0) {
                    throw new SQLException("No rows affected!");
                }
                if (rs.next()) {
                    r.setId(rs.getInt(1));
                }
            }
        });

        if (result.length != 1) {
            throw new SQLException("Could not insert Recurrency!");
        }
    }


    public static void updatePenal(Penal p) {
        String penalUpdate = "UPDATE catering.Penals SET sum = "+p.sum+", description = "+p.description+" WHERE id = "+p.id+";";

        int result = PersistenceManager.executeUpdate(penalUpdate);

        if(result != 1) {
            throw new RuntimeException("Error updating penal");
        }
    }

}

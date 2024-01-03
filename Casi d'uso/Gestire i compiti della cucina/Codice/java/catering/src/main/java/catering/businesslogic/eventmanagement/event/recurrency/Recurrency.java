package catering.businesslogic.eventmanagement.event.recurrency;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import catering.businesslogic.eventmanagement.event.Event;
import catering.businesslogic.eventmanagement.event.penal.Penal;
import catering.businesslogic.eventmanagement.service.Service;
import catering.businesslogic.usermanagement.user.User;
import catering.persistence.BatchUpdateHandler;
import catering.persistence.PersistenceManager;
import catering.persistence.ResultHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Recurrency {
    private int id;
    private Event event;
    private java.sql.Date date;
    private int recurrencyCount;

    private static Map<Integer, Recurrency> loadedRecurrencies = FXCollections.observableHashMap();

    public Recurrency(Event event, Date date, int recurrencyCount) {
        this.event = event;
        this.date = date;
        this.recurrencyCount = recurrencyCount;
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

    public static void saveNewRecurrency(Recurrency r) throws SQLException {
        String recurrencyInsert = "INSERT INTO catering.Recurrency (event_id, date, occurrence) VALUES (?, ?, ?);";

        int[] result = PersistenceManager.executeBatchUpdate(recurrencyInsert, 1, new BatchUpdateHandler() {
            @Override
            public void handleBatchItem(PreparedStatement ps, int batchCount) throws SQLException {
                ps.setInt(1, r.event.getId());
                ps.setDate(2, r.date);
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
        String penalUpdate = "UPDATE catering.Penals SET sum = "+p.getSum()+", description = "+p.getDescription()+" WHERE id = "+p.getId()+";";

        int result = PersistenceManager.executeUpdate(penalUpdate);

        if(result != 1) {
            throw new RuntimeException("Error updating penal");
        }
    }

    public static ObservableList<Recurrency> loadAllRecurrency(){
                ObservableList<Recurrency> all = FXCollections.observableArrayList();
        String query = "SELECT * FROM Recurrences WHERE true";
        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                Event eventOfRecuEvent = Event.getEventById(rs.getInt("event_id"));
                Date dateOfRecuEvent = rs.getDate("date");
                int recurrencyCountOfRecuEvent = rs.getInt("occurrence");

                Recurrency r = new Recurrency(eventOfRecuEvent, dateOfRecuEvent, recurrencyCountOfRecuEvent);

                all.add(r);
            }
        });

        for (Recurrency r : all) {
            //e.services = Service.loadServiceInfoForEvent(e.id);
            loadedRecurrencies.put(r.id, r);
        }

        return all;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static void saveRecurrency(Recurrency r) {
    }

    public static void deleteRecurrency(Recurrency r) {
    }



    public java.sql.Date getDate() {
        return date;
    }



    public void setDate(java.sql.Date date) {
        this.date = date;
    }



    public static ObservableList<Recurrency> getRecurrenciesOfEvent(int id) {
        loadAllRecurrency();
        ObservableList<Recurrency> recurrencies = FXCollections.observableArrayList();
        for(Recurrency r : loadedRecurrencies.values()) {
            if(r.event.getId() == id) {
                recurrencies.add(r);
            }
        }
        return recurrencies;
    }

}

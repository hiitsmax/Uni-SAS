package catering.businesslogic.eventmanagement.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import catering.businesslogic.eventmanagement.event.EventInfo;
import catering.businesslogic.usermanagement.user.User;
import catering.persistence.PersistenceManager;
import catering.persistence.ResultHandler;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Set;

public class ServiceInfo implements EventInfo {
    private int id;
    private String name;
    private Date date;
    private Time timeStart;
    private Time timeEnd;
    private int participants;
    private Set<User> Owners;
    private boolean isElegant;
    private boolean isPrivate;

    public ServiceInfo(String name) {
        this.name = name;
    }

    public void setElegant(boolean isElegant) {
        this.isElegant = isElegant;
    }

    public void setPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public String toString() {
        return name + ": " + date + " (" + timeStart + "-" + timeEnd + "), " + participants + " pp, " + isElegant + "elegant, " + isPrivate +"private.";
    }

    // STATIC METHODS FOR PERSISTENCE

    public static ObservableList<ServiceInfo> loadServiceInfoForEvent(int event_id) {
        ObservableList<ServiceInfo> result = FXCollections.observableArrayList();
        String query = "SELECT id, name, service_date, time_start, time_end, expected_participants, elegant, private " +
                "FROM Services WHERE event_id = " + event_id;
        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                String s = rs.getString("name");
                ServiceInfo serv = new ServiceInfo(s);
                serv.id = rs.getInt("id");
                serv.date = rs.getDate("service_date");
                serv.timeStart = rs.getTime("time_start");
                serv.timeEnd = rs.getTime("time_end");
                serv.participants = rs.getInt("expected_participants");
                serv.isElegant = rs.getBoolean("elegant");
                serv.isPrivate = rs.getBoolean("private");
                result.add(serv);
            }
        });

        return result;
    }
}

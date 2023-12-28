package catering.businesslogic.eventmanagement.event;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import catering.businesslogic.eventmanagement.menu.section.Section;
import catering.businesslogic.eventmanagement.service.Service;
import catering.businesslogic.eventmanagement.service.ServiceInfo;
import catering.businesslogic.usermanagement.user.User;
import catering.persistence.BatchUpdateHandler;
import catering.persistence.PersistenceManager;
import catering.persistence.ResultHandler;

public class Event implements EventInfo {
    private int id;
    private String name;
    private Date dateStart;
    private Date dateEnd;
    private int participants;
    private User organizer;
    private RecurrencyInfo recurrency;

    private ObservableList<Service> services;


    private static Map<Integer, Event> loadedEvents = FXCollections.observableHashMap();

    public Event(User organizer, Event e) {
        this.id = 0;
        this.name = e.name;
        this.dateStart = e.dateStart;
        this.dateEnd = e.dateEnd;
        this.organizer = organizer;
        this.participants = e.participants;
        this.recurrency = e.recurrency;
        this.services = FXCollections.observableArrayList();
        // Vedere come fare la deep copy
        // ed anche se la facciamo poi teoricamente copia gli id, avrebbe senso??
        // for (Service original: e.services) {
        //     this.services.add(original);
        // }
    }

    public Event(String name){
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public int getParticipants() {
        return participants;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }

    public User getOrganizer() {
        return organizer;
    }

    public void setOrganizer(User organizer) {
        this.organizer = organizer;
    }

    public RecurrencyInfo getRecurrency() {
        return recurrency;
    }

    public void setRecurrency(RecurrencyInfo recurrency) {
        this.recurrency = recurrency;
    }

    public Service addServices(String name) {
        Service serv = new Service(name, this);
        this.services.add(serv);
        return serv;
    }

    public ObservableList<Service> getServices() {
        return FXCollections.unmodifiableObservableList(this.services);
    }

    public String toString() {
        return name + ": " + dateStart + "-" + dateEnd + ", " + participants + " pp. (" + organizer.getUserName() + ")";
    }

    public boolean isOrganizer(User u) {
        return u.getId() == this.organizer.getId();
    }

    public static Event getEventById(int id) {
        return loadedEvents.get(id);
    }

    // STATIC METHODS FOR PERSISTENCE

    public static void saveNewEvent(Event e) {
        String eventInsert = "INSERT INTO catering.Events (name, date_start, date_end, expected_participants, organizer_id) VALUES (?, ?, ?, ?, ?);";
        int[] result = PersistenceManager.executeBatchUpdate(eventInsert, 1, new BatchUpdateHandler() {
            @Override
            public void handleBatchItem(PreparedStatement ps, int batchCount) throws SQLException {
                ps.setString(1, PersistenceManager.escapeString(e.name));
                ps.setDate(2, e.dateStart);
                ps.setDate(3, e.dateEnd);
                ps.setInt(4, e.participants);
                ps.setInt(5, e.organizer.getId());
            }

            @Override
            public void handleGeneratedIds(ResultSet rs, int count) throws SQLException {
                if (count == 0) {
                    e.id = rs.getInt(1);
                }
            }
        });

            if (result[0] > 0) {
                if(e.services.size() > 0) {
                    Service.saveAllNewServices(e.id, e.services);
                }
                loadedEvents.put(e.id, e);
            }
    }

    public static ObservableList<Event> loadAllEvent() {
        ObservableList<Event> all = FXCollections.observableArrayList();
        String query = "SELECT * FROM Events WHERE true";
        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                String n = rs.getString("name");
                Event e = new Event(n);
                e.id = rs.getInt("id");
                e.dateStart = rs.getDate("date_start");
                e.dateEnd = rs.getDate("date_end");
                e.participants = rs.getInt("expected_participants");
                int org = rs.getInt("organizer_id");
                e.organizer = User.loadUserById(org);
                all.add(e);
            }
        });

        for (Event e : all) {
            //e.services = Service.loadServiceInfoForEvent(e.id);
            loadedEvents.put(e.id, e);
        }

        return all;
    }
}

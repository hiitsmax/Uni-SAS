package catering.businesslogic.eventmanagement.service;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Map;

import catering.businesslogic.eventmanagement.event.Event;
import catering.businesslogic.eventmanagement.event.RecurrencyInfo;
import catering.businesslogic.eventmanagement.event.recurrency.Recurrency;
import catering.businesslogic.eventmanagement.menu.Menu;
import catering.businesslogic.eventmanagement.menu.menuitem.MenuItem;
import catering.businesslogic.eventmanagement.menu.section.Section;
import catering.businesslogic.kitchenmanagement.summarysheet.SummarySheet;
import catering.businesslogic.usermanagement.user.User;
import catering.persistence.PersistenceManager;
import catering.persistence.ResultHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Service {
    private static Map<Integer, Service> loadedServices = FXCollections.observableHashMap();

    private int id;
    private Event event;
    private String name;
    private Menu proposed_menu_id;
    private Menu approved_menu_id;
    private Date service_date;
    private Time time_start;
    private Time time_end;
    private int expected_participants;
    private SummarySheet summarySheet;
    private User supportCook;

    public static Service getServiceById(int id) {
        //TODO: implementare il fatto che se non c'è il servizio lo carica (almeno ci prova)
        return loadedServices.get(id);
    }
    public Service() {
        this.id = -1;
        this.event = null;
        this.name = "";
        this.proposed_menu_id = null;
        this.approved_menu_id = null;
        this.service_date = null;
        this.time_start = null;
        this.time_end = null;
        this.expected_participants = 0;
        this.supportCook = null;
    }

    public static Service getServiceOfSummarySheet(SummarySheet s){
        for(Service service : loadedServices.values()){
            if(service.summarySheet == null) continue;
            if(service.summarySheet.getId() == s.getId()){
                return service;
            }
        }
        return null;
    }

    public String toString() {
        String summarySheetId = (summarySheet != null) ? String.valueOf(summarySheet.getId()) : "null";
        String supportCookDetails = (supportCook != null) ? supportCook.toString() : "null";
        return "{\n" +
                "  \"id\": " + id + ",\n" +
                "  \"event\": " + event + ",\n" +
                "  \"name\": \"" + name + "\",\n" +
                "  \"proposed_menu_id\": " + proposed_menu_id + ",\n" +
                "  \"approved_menu_id\": " + approved_menu_id + ",\n" +
                "  \"service_date\": " + service_date + ",\n" +
                "  \"time_start\": " + time_start + ",\n" +
                "  \"time_end\": " + time_end + ",\n" +
                "  \"expected_participants\": " + expected_participants + ",\n" +
                "  \"summarySheet_id\": " + summarySheetId + ",\n" +
                "  \"supportCook\": " + supportCookDetails + "\n" +
                "}";
    }

    public Service(String name, Event event) {
        this.name = name;
        this.event = event;
    }

    public Service(int id, Event event, String name, Menu proposed_menu_id, Menu approved_menu_id, Date service_date,
            Time time_start, Time time_end, int expected_participants) {
        this.id = id;
        this.event = event;
        this.name = name;
        this.proposed_menu_id = proposed_menu_id;
        this.approved_menu_id = approved_menu_id;
        this.service_date = service_date;
        this.time_start = time_start;
        this.time_end = time_end;
        this.expected_participants = expected_participants;
    }

    public static Map<Integer, Service> getLoadedServices() {
        return loadedServices;
    }

    public static void setLoadedServices(Map<Integer, Service> loadedServices) {
        Service.loadedServices = loadedServices;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Menu getProposed_menu_id() {
        return proposed_menu_id;
    }

    public void setProposed_menu_id(Menu proposed_menu_id) {
        this.proposed_menu_id = proposed_menu_id;
    }

    public Menu getApproved_menu_id() {
        return approved_menu_id;
    }

    public void setApproved_menu_id(Menu approved_menu_id) {
        this.approved_menu_id = approved_menu_id;
    }

    public Date getService_date() {
        return service_date;
    }

    public void setService_date(Date service_date) {
        this.service_date = service_date;
    }

    public Time getTime_start() {
        return time_start;
    }

    public void setTime_start(Time time_start) {
        this.time_start = time_start;
    }

    public Time getTime_end() {
        return time_end;
    }

    public void setTime_end(Time time_end) {
        this.time_end = time_end;
    }

    public int getExpected_participants() {
        return expected_participants;
    }

    public void setExpected_participants(int expected_participants) {
        this.expected_participants = expected_participants;
    }

    public static Service saveNewService(Service s) {
        String query = "INSERT INTO Services (name, event_id, proposed_menu_id, approved_menu_id, service_date, time_start, time_end, expected_participants";
        if (s.supportCook != null) {
            query += ", support_cook_id";
        }
        query += ") VALUES ("
                + "'" + s.name + "', "
                + s.event.getId() + ", "
                + s.proposed_menu_id.getId() + ", "
                + s.approved_menu_id.getId() + ", "
                + "'" + s.service_date + "', "
                + "'" + s.time_start + "', "
                + "'" + s.time_end + "', "
                + s.expected_participants;
        if (s.supportCook != null) {
            query += ", " + s.supportCook.getId();
        }
        query += ")";
        PersistenceManager.executeUpdate(query);
        s.id = PersistenceManager.getLastId();
        return s;
    }

    public static Service getServiceBySummarySheetId(int id){
        for(Service s : loadedServices.values()){
            if(s.summarySheet.getId() == id){
                return s;
            }
        }
        return null;
    }

    //TODO: dividere caricamento da get all
    public static ObservableList<Service> getAllServices() {
        String query = "SELECT * FROM Services WHERE " + true;
        ArrayList<Service> newServices = new ArrayList<>();
        ArrayList<Integer> newSids = new ArrayList<>();
        ArrayList<Service> oldServices = new ArrayList<>();
        ArrayList<Integer> oldSids = new ArrayList<>();

        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                int id = rs.getInt("id");
                if (loadedServices.containsKey(id)) {
                    Service s = loadedServices.get(id);
                    s.name = rs.getString("name");
                    s.event = Event.getEventById(rs.getInt("event_id"));
                    s.proposed_menu_id = Menu.getMenuById(rs.getInt("proposed_menu_id"));
                    s.approved_menu_id = Menu.getMenuById(rs.getInt("approved_menu_id"));
                    s.service_date = rs.getDate("service_date");
                    s.time_start = rs.getTime("time_start");
                    s.time_end = rs.getTime("time_end");
                    s.expected_participants = rs.getInt("expected_participants");
                    int summarySheetId = rs.getInt("summarysheet_id");
                    s.summarySheet = rs.wasNull()?null:SummarySheet.getSummarySheetById(summarySheetId);

                    oldSids.add(id);
                    oldServices.add(s);
                } else {
                    Service s = new Service();
                    s.id = id;
                    s.name = rs.getString("name");
                    s.event = Event.getEventById(rs.getInt("event_id"));
                    s.proposed_menu_id = Menu.getMenuById(rs.getInt("proposed_menu_id"));
                    s.approved_menu_id = Menu.getMenuById(rs.getInt("approved_menu_id"));
                    s.service_date = rs.getDate("service_date");
                    s.time_start = rs.getTime("time_start");
                    s.time_end = rs.getTime("time_end");
                    s.expected_participants = rs.getInt("expected_participants");
                    int summarySheetId = rs.getInt("summarysheet_id");
                    s.summarySheet = rs.wasNull()?null:SummarySheet.getSummarySheetById(summarySheetId);
                    int supportCookId = rs.getInt("support_cook_id");
                    s.supportCook = rs.wasNull()?null:User.loadUserById(supportCookId);

                    newSids.add(id);
                    newServices.add(s);
                }
            }
        });

        // Aggiungop eventuali cuochi di supporto


        for (Service s : newServices) {
            loadedServices.put(s.id, s);
        }
        return FXCollections.observableArrayList(loadedServices.values());

    }



    public static void updateService(Service s) {
        String proposed_menu_id = (s.getProposed_menu_id() != null) ? String.valueOf(s.getProposed_menu_id().getId()) : "0";
        String approved_menu_id = (s.getApproved_menu_id() != null) ? String.valueOf(s.getApproved_menu_id().getId()) : "0";
            String query = "UPDATE Services SET "
                    + "name = '" + s.getName() + "', "
                    + "event_id = " + s.getEvent().getId() + ", "
                    + "proposed_menu_id = " + proposed_menu_id + ", "
                    + "approved_menu_id = " + approved_menu_id + ", "
                    + "service_date = '" + s.getService_date() + "', "
                    + "time_start = '" + s.getTime_start() + "', "
                    + "time_end = '" + s.getTime_end() + "', "
                    + "expected_participants = " + s.getExpected_participants();
    
            if (s.getSupportCook() != null) {
                query += ", support_cook_id = " + s.getSupportCook().getId();
            }
    
            query += " WHERE id = " + s.getId();
    
            PersistenceManager.executeUpdate(query);
    }

    private User getSupportCook() {
        return supportCook;
    }
    public static void deleteService() {
    }

    public SummarySheet getSummarySheet() {
        return this.summarySheet;
    }

    public void setSummarySheet(SummarySheet summarySheet) {
        this.summarySheet = summarySheet;
    }

    public boolean isRunning() {
        Date now = new Date(System.currentTimeMillis());
        long serviceStartOffset = this.time_start.getTime();
        long serviceEndOffset = this.time_end.getTime();
        
        for(Recurrency r : this.event.getRecurrences()) {
            Date startRecurrency = new Date(r.getDate().getTime());
            Date endRecurrency =  new Date(r.getDate().getTime());

            startRecurrency.setTime(startRecurrency.getTime() + serviceStartOffset);
            endRecurrency.setTime(endRecurrency.getTime() + serviceEndOffset);
            if(now.after(startRecurrency) && now.before(endRecurrency)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasUnhappenedEvents() {
        Date now = new Date(System.currentTimeMillis());
        long serviceStartOffset = this.time_start.getTime();
        long serviceEndOffset = this.time_end.getTime();
        
        for(Recurrency r : this.event.getRecurrences()) {
            Date startRecurrency = new Date(r.getDate().getTime());
            Date endRecurrency =  new Date(r.getDate().getTime());

            startRecurrency.setTime(startRecurrency.getTime() + serviceStartOffset);
            endRecurrency.setTime(endRecurrency.getTime() + serviceEndOffset);
            if(now.before(startRecurrency)) {
                return true;
            }
        }

        return false;
    }

	public static ObservableList<Service> getServicesOfEvent(int eventId) {
        getAllServices();
        ObservableList<Service> services = FXCollections.observableArrayList();
        for(Service s : loadedServices.values()) {
            if(s.event.getId() == eventId) {
                services.add(s);
            }
        }
        return services;
	}
    public void setSupportCook(User c) {
        supportCook=c;
    }

}

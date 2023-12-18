package catering.businesslogic.eventmanagement.event;

import javafx.collections.ObservableList;

public class EventManager {
    //event sender methods
    public void notifyEventCreated(Event e) {
        // TODO: Implement method body
    }

    public void notifyEventModified(Event e) {
        // TODO: Implement method body
    }

    public void notifyEventDeleted(Event e) {
        // TODO: Implement method body
    }

    public void notifyRecurrencyCreated(Recurrency r) {
        // TODO: Implement method body
    }

    public void notifyRecurrencyModified(Recurrency r) {
        // TODO: Implement method body
    }

    public void notifyRecurrencyDeleted(Recurrency r) {
        // TODO: Implement method body
    }

    public void notifyServiceCreated(Service serv) {
        // TODO: Implement method body
    }

    public void notifyServiceDeleted(Service serv) {
        // TODO: Implement method body
    }

    public void notifyApproveMenu(Event e) {
        // TODO: Implement method body
    }

    //operations methods
    public void setCurrentEvent(Event e) {
        // TODO: Implement method body
    }

    public void setCurrentRecurrency(Recurrency r) {
        // TODO: Implement method body
    }

    public Event createEvent(String title) {
        // TODO: Implement method body
        return null;
    }

    public void deleteEvent(String title) {
        // TODO: Implement method body
    }

    public Event modifyEvent(String title) {
        // TODO: Implement method body
        return null;
    }

    public Recurrency createRecurrency(String startRec, String endRec, int countRec, String typeRec, int occurrency) {
        // TODO: Implement method body
        return null;
    }

    public Recurrency modifyRecurrency(String startRec, String endRec, int countRec, String typeRec, int occurrency, int[] offset) {
        // TODO: Implement method body
        return null;
    }

    public void deleteRecurrency(String startRec, String endRec, int countRec, String typeRec, int occurrency) {
        // TODO: Implement method body
    }

    public Service createService(String title) {
        // TODO: Implement method body
        return null;
    }

    public void deleteService(String title) {
        // TODO: Implement method body
    }

    public Service modifyService(String title) {
        // TODO: Implement method body
        return null;
    }

    public void approveMenu() {
        // TODO: Implement method body
    }

    public Menu getMenu() {
        // TODO: Implement method body
        return null;
    }

    public ArrayList<Service> getServices() {
        // TODO: Implement method body
        return null;
    }

    public SummarySheet getSummarySheet() {
        // TODO: Implement method body
        return null;
    }

    public Service getCurrentService() {
        // TODO: Implement method body
        return null;
    }
    public ObservableList<Event> getEventInfo() {
        return Event.loadAllEventInfo();
    }

    
}

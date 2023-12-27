package catering.businesslogic.eventmanagement.event;

import java.util.ArrayList;

import catering.businesslogic.eventmanagement.event.recurrency.Recurrency;
import catering.businesslogic.eventmanagement.service.Service;
import catering.businesslogic.kitchenmanagement.summarysheet.SummarySheet;
import javafx.collections.ObservableList;

public class EventManager {
    private Event currentEvent;
    private ArrayList<EventEventReceiver> eventReceivers;

    public EventManager() {
        eventReceivers = new ArrayList<>();
    }

    //event sender methods
    private void notifyEventCreated(Event e) {
        for (EventEventReceiver er : this.eventReceivers) {
            er.updateEventCreated(this.currentEvent, e);
        }
    }

    private void notifyEventModified(Event e) {
        for (EventEventReceiver er : this.eventReceivers) {
            er.updateEventModified(this.currentEvent, e);
        }
    }

    private void notifyEventDeleted(Event e) {
        for (EventEventReceiver er : this.eventReceivers) {
            er.updateEventDeleted(this.currentEvent, e);
        }
    }

    private void notifyRecurrencyCreated(Recurrency r) {
        for (EventEventReceiver er : this.eventReceivers) {
            er.updateRecurrencyCreated(this.currentEvent, r);
        }
    }

    private void notifyRecurrencyModified(Recurrency r) {
        for (EventEventReceiver er : this.eventReceivers) {
            er.updateRecurrencyModified(this.currentEvent, r);
        }
    }

    private void notifyRecurrencyDeleted(Recurrency r) {
        for (EventEventReceiver er : this.eventReceivers) {
            er.updateRecurrencyDeleted(this.currentEvent, r);
        }
    }

    private void notifyServiceCreated(Service serv) {
        for (EventEventReceiver er : this.eventReceivers) {
            er.updateServiceCreated(this.currentEvent, serv);
        }
    }

    private void notifyServiceDeleted(Service serv) {
        for (EventEventReceiver er : this.eventReceivers) {
            er.updateServiceDeleted(this.currentEvent, serv);
        }
    }

    private void notifyApproveMenu(Event e) {
        // TODO: Implement method body
    }

    //operations methods
    public void setCurrentEvent(Event e) {
        this.currentEvent = e;
    }

    public void setCurrentRecurrency(Recurrency r) {
        this.currentEvent = e;
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

package catering.businesslogic.eventmanagement.event;

import java.util.ArrayList;

import catering.businesslogic.eventmanagement.event.recurrency.Recurrency;
import catering.businesslogic.eventmanagement.menu.Menu;
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
            er.updateEventCreated(e);
        }
    }

    private void notifyEventModified(Event e) {
        for (EventEventReceiver er : this.eventReceivers) {
            er.updateEventModified(e);
        }
    }

    private void notifyEventDeleted(Event e) {
        for (EventEventReceiver er : this.eventReceivers) {
            er.updateEventDeleted(e);
        }
    }

    private void notifyRecurrencyCreated(Recurrency r) {
        for (EventEventReceiver er : this.eventReceivers) {
            er.updateRecurrencyCreated(r);
        }
    }

    private void notifyRecurrencyModified(Recurrency r) {
        for (EventEventReceiver er : this.eventReceivers) {
            er.updateRecurrencyModified(r);
        }
    }

    private void notifyRecurrencyDeleted(Recurrency r) {
        for (EventEventReceiver er : this.eventReceivers) {
            er.updateRecurrencyDeleted(r);
        }
    }

    private void notifyServiceCreated(Service serv) {
        for (EventEventReceiver er : this.eventReceivers) {
            er.updateServiceCreated(serv);
        }
    }

    private void notifyServiceDeleted(Service serv) {
        for (EventEventReceiver er : this.eventReceivers) {
            er.updateServiceDeleted(serv);
        }
    }

    private void notifyApproveMenu(Event e) {
        // TODO: Implement method body
    }

    //operations methods
    public void setCurrentEvent(Event e) {
        this.currentEvent = e;
    }

    // capire cazzo farci
    // public void setCurrentRecurrency(Recurrency r) {
    //     this.currentEvent = e;
    // }

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

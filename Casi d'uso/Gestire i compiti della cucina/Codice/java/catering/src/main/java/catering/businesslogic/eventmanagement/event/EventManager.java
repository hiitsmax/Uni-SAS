package catering.businesslogic.eventmanagement.event;

import java.util.ArrayList;

import catering.businesslogic.CatERing;
import catering.businesslogic.UseCaseLogicException;
import catering.businesslogic.eventmanagement.event.recurrency.Recurrency;
import catering.businesslogic.eventmanagement.menu.Menu;
import catering.businesslogic.eventmanagement.service.Service;
import catering.businesslogic.eventmanagement.service.ServiceException;
import catering.businesslogic.kitchenmanagement.summarysheet.SummarySheet;
import catering.businesslogic.usermanagement.UserException;
import catering.businesslogic.usermanagement.user.User;
import javafx.collections.ObservableList;

public class EventManager {
    private Event currentEvent;
    private ArrayList<EventEventReceiver> eventReceivers;

    public EventManager() {
        eventReceivers = new ArrayList<>();
        Service.getLoadedServices();
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

    public Event createEvent(Event event) throws UseCaseLogicException {
        User user = CatERing.getInstance().getUserManager().getCurrentUser();

        if (!user.isOrganizer()) {
            throw new UseCaseLogicException();
        }

        Event e = new Event(user, event);
        this.setCurrentEvent(e);
        this.notifyEventCreated(e);

        return e;
    }

    public void deleteEvent(Event e) throws EventException, UserException {
        // TODO: Implement method body
        if(CatERing.getInstance().getUserManager().getCurrentUser() == null)
            throw new UserException("No user logged in");
        if(e==null)
            throw new EventException("Event not existent");
    }

    public Event modifyEvent(Event e) {
        // TODO: Implement method body
        return null;
    }

    public Recurrency createRecurrency(Recurrency r) {
        // TODO: Implement method body
        return null;
    }

    public Recurrency modifyRecurrency(Recurrency r) {
        // TODO: Implement method body
        return null;
    }

    public void deleteRecurrency(Recurrency r) {
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

    public ObservableList<Service> getServices() {
        return Service.getAllServices();
        
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
        return Event.loadAllEvent();
    }

    
}

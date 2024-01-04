package catering.persistence;

import java.sql.SQLException;

import catering.businesslogic.eventmanagement.event.Event;
import catering.businesslogic.eventmanagement.event.EventEventReceiver;
import catering.businesslogic.eventmanagement.event.recurrency.Recurrency;
import catering.businesslogic.eventmanagement.service.Service;

public class EventPersistence implements EventEventReceiver {
    
    @Override
    public void updateEventCreated(Event e) {
        Event.saveNewEvent(e);
    }

    @Override
    public void updateEventDeleted(Event e) {
        Event.deleteEvent(e);
    }

    @Override
    public void updateEventModified(Event e) {
        Event.saveEvent(e);
    }

    @Override
    public void updateRecurrencyCreated(Recurrency r) {
        try {
            Recurrency.saveNewRecurrency(r);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void updateRecurrencyDeleted(Recurrency r) {
        Recurrency.deleteRecurrency(r);
    }

    @Override
    public void updateRecurrencyModified(Recurrency r) {
        Recurrency.saveRecurrency(r); 
    }

    @Override
    public void updateServiceCreated(Service s) {
        Service.saveNewService(s);
    }

    @Override
    public void updateServiceModified(Service s) {
        Service.updateService(s);
    }

    @Override
    public void updateServiceDeleted(Service s) {
        Service.deleteService();
    }

}

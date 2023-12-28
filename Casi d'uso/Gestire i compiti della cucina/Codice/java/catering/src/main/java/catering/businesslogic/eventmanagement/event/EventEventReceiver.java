package catering.businesslogic.eventmanagement.event;

import java.sql.SQLException;

import catering.businesslogic.eventmanagement.event.recurrency.Recurrency;
import catering.businesslogic.eventmanagement.service.Service;

public interface EventEventReceiver {
    public void updateEventCreated(Event e);
    public void updateEventDeleted(Event e);
    public void updateEventModified(Event e);
    public void updateRecurrencyCreated(Recurrency r);
    public void updateRecurrencyDeleted(Recurrency r);
    public void updateRecurrencyModified(Recurrency r);
    public void updateServiceCreated(Service s);
    public void updateServiceDeleted(Service s);
    public void updateServiceModified(Service s);
}

package catering.businesslogic.eventmanagement.event;

public class EventEventReceiver {
     public void updateEventCreated(Event e);

    public void updateEventDeleted(Event e);

    public void updateEventModified(Event e);

    public void updateRecurrencyCreated(Recurrency r);

    public void updateRecurrencyDeleted(Recurrency r);

    public void updateRecurrencyModified(Recurrency r);
    
    public void updateServiceCreated(Service serv);

    public void updateServiceDeleted(Service serv);

    public void updateServiceModified(Service serv);
}

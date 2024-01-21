package catering.businesslogic.eventmanagement.event;


public interface EventInfo {
    
    boolean isElegant = false;
    boolean isPrivate = false;
    
    void setElegant(boolean isElegant);
    
    void setPrivate(boolean isPrivate);

    boolean isElegant();
    boolean isPrivate();

}


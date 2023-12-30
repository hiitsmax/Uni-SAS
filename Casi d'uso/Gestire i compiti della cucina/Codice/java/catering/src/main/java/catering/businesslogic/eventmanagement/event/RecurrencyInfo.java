package catering.businesslogic.eventmanagement.event;

import java.sql.Date;

public class RecurrencyInfo {
    private RecurrencyType type;
    private Date start;
    private Date end;

    public RecurrencyType getType() {
        return type;
    }
    public void setType(RecurrencyType type) {
        this.type = type;
    }
    public Date getStart() {
        return start;
    }
    public void setStart(Date start) {
        this.start = start;
    }
    public Date getEnd() {
        return end;
    }
    public void setEnd(Date end) {
        this.end = end;
    }
    
}

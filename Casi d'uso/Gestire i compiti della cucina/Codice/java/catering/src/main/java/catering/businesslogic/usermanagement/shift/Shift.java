package catering.businesslogic.usermanagement.shift;

import java.sql.Date;

public class Shift {
    private Date start;
    private Date end;
    private ShiftType type;
    private int limit;


    
    public Shift(Date start, Date end, ShiftType type, int limit) {
        this.start = start;
        this.end = end;
        this.type = type;
        this.limit = limit;
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
    public ShiftType getType() {
        return type;
    }
    public void setType(ShiftType type) {
        this.type = type;
    }
    public int getLimit() {
        return limit;
    }
    public void setLimit(int limit) {
        this.limit = limit;
    }
    public boolean isInTimeframe(Date startDate, Date endDate) {
        return false;
    }

  
}

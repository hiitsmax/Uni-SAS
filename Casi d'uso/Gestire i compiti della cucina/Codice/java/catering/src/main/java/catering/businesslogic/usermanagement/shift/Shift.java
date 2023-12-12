package catering.businesslogic.usermanagement.shift;

public class Shift {
    private String start;
    private String end;
    private String type;
    private int limit;

    public Shift(String start, String end, String type, int limit) {
        this.start = start;
        this.end = end;
        this.type = type;
        this.limit = limit;
    }
    
    public void setStart(String start) {
        this.start = start;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }
}

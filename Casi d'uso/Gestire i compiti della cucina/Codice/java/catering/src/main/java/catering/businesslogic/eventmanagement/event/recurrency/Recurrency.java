package catering.businesslogic.eventmanagement.event.recurrency;

import java.sql.Date;

public class Recurrency {
    private java.sql.Date startRecurrency;
    private java.sql.Date endRecurrency;
    private int recurrencyCount;
    private String recurrencyType; //????
    private int occurrency; //????

    

    public Recurrency(Date startRecurrency, Date endRecurrency, int recurrencyCount, String recurrencyType,
            int occurrency) {
        this.startRecurrency = startRecurrency;
        this.endRecurrency = endRecurrency;
        this.recurrencyCount = recurrencyCount;
        this.recurrencyType = recurrencyType;
        this.occurrency = occurrency;
    }

    
    public java.sql.Date getStartRecurrency() {
        return startRecurrency;
    }
    public void setStartRecurrency(java.sql.Date startRecurrency) {
        this.startRecurrency = startRecurrency;
    }
    public java.sql.Date getEndRecurrency() {
        return endRecurrency;
    }
    public void setEndRecurrency(java.sql.Date endRecurrency) {
        this.endRecurrency = endRecurrency;
    }
    public int getRecurrencyCount() {
        return recurrencyCount;
    }
    public void setRecurrencyCount(int recurrencyCount) {
        this.recurrencyCount = recurrencyCount;
    }
    public String getRecurrencyType() {
        return recurrencyType;
    }
    public void setRecurrencyType(String recurrencyType) {
        this.recurrencyType = recurrencyType;
    }
    public int getOccurrency() {
        return occurrency;
    }
    public void setOccurrency(int occurrency) {
        this.occurrency = occurrency;
    }

    
}

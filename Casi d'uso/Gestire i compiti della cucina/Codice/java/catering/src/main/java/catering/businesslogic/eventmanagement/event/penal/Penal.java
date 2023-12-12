package catering.businesslogic.eventmanagement.event.penal;

public class Penal {
    private int sum;
    private String description;

    public Penal(int sum, String description) {
        this.sum = sum;
        this.description = description;
    }
    
    public int getSum() {
        return sum;
    }
    public void setSum(int sum) {
        this.sum = sum;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    
}

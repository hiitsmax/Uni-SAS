package catering.businesslogic.eventmanagement.service;
import catering.businesslogic.eventmanagement.menu.Menu;
import catering.businesslogic.kitchenmanagement.summarysheet.SummarySheet;

public class Service {
    private String title;
    private String offset;
    private boolean confirmed;
    private Menu menu;
    private SummarySheet sheet;
    private int id;

    public Service(String title,String offset,boolean confirmed, Menu menu, SummarySheet sheet) {
        this.title = title;
        this.offset = offset;
        this.confirmed = confirmed;
        this.menu = menu;
        this.sheet = sheet;
    }

    public Service(String offset) {
        this.offset = offset;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public SummarySheet getSheet() {
        return sheet;
    }

    public void setSheet(SummarySheet sheet) {
        this.sheet = sheet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
}

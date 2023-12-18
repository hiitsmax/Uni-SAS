package catering.businesslogic.eventmanagement.service;
import catering.businesslogic.eventmanagement.menu.Menu;
import catering.businesslogic.kitchenmanagement.summarysheet.SummarySheet;

public class Service {
    private String title;
    private String offset;
    private boolean confirmed;
    private Menu menu;
    private SummarySheet sheet;

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
}

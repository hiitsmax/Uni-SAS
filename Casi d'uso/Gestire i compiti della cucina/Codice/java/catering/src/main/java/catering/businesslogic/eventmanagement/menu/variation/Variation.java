package catering.businesslogic.eventmanagement.menu.variation;

import catering.businesslogic.eventmanagement.menu.menuitem.MenuItem;

public class Variation {
    private MenuItem item; // da cambiare in dcd
    private VariationType type; // da cambiare in dcd
    private boolean isAccepted; // da cambiare in dcd

    public Variation(MenuItem item, VariationType type) {
        this.item = item;
        this.type = type;
        this.isAccepted = false;
    }

    public MenuItem getItem() {
        return item;
    }
    public void setItem(MenuItem item) {
        this.item = item;
    }
    public VariationType getType() {
        return type;
    }
    public void setType(VariationType type) {
        this.type = type;
    }
    public boolean isAccepted() {
        return isAccepted;
    }
    public void setAccepted(boolean isAccepted) {
        this.isAccepted = isAccepted;
    }

    public static void saveNewVariation(Variation v) {
        
    }
    
}

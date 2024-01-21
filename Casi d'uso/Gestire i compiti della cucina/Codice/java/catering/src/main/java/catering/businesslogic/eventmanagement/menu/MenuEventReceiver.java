package catering.businesslogic.eventmanagement.menu;

import catering.businesslogic.eventmanagement.menu.menuitem.MenuItem;
import catering.businesslogic.eventmanagement.menu.section.Section;
import catering.businesslogic.eventmanagement.menu.variation.Variation;

public interface MenuEventReceiver {
    // Dobbiamo aggiungere tutti i metodi interessanti al DCD
    public void updateMenuCreated(Menu m);

    public void updateVariationCreated(Variation v);


    public void updateSectionAdded(Menu m, Section sec);

    public void updateMenuItemAdded(Menu m, MenuItem mi);

    public void updateMenuFeaturesChanged(Menu m);

    public void updateMenuTitleChanged(Menu m);

    public void updateMenuPublishedState(Menu m);

    public void updateMenuDeleted(Menu m);

    public void updateSectionDeleted(Menu m, Section s, boolean itemsDeleted);

    public void updateSectionChangedName(Menu m, Section s);

    public void updateSectionsRearranged(Menu m);

    public void updateFreeItemsRearranged(Menu m);

    public void updateSectionItemsRearranged(Menu m, Section s);

    public void updateItemSectionChanged(Menu m, Section s, MenuItem mi);

    public void updateItemDescriptionChanged(Menu m, MenuItem mi);

    public void updateItemDeleted(Menu m, Section sec, MenuItem mi);
}

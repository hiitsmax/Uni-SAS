package catering.businesslogic.eventmanagement.service;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import catering.businesslogic.eventmanagement.menu.Menu;
import catering.businesslogic.eventmanagement.menu.menuitem.MenuItem;
import catering.businesslogic.eventmanagement.menu.section.Section;
import catering.businesslogic.kitchenmanagement.summarysheet.SummarySheet;
import catering.businesslogic.usermanagement.user.User;
import catering.persistence.PersistenceManager;
import catering.persistence.ResultHandler;
import javafx.collections.FXCollections;

public class Service {
    private String title;
    private String offset;
    private boolean confirmed;
    private Menu menu;
    private SummarySheet sheet;
    private int id;
    private static Map<Integer, Service> loadedServices = FXCollections.observableHashMap();

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

    public static ArrayList<Service> getAllServices() {
        String query = "SELECT * FROM Services WHERE " + true;
        ArrayList<Menu> newServices = new ArrayList<>();
        ArrayList<Integer> newSids = new ArrayList<>();
        ArrayList<Menu> oldServices = new ArrayList<>();
        ArrayList<Integer> oldSids = new ArrayList<>();

        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                int id = rs.getInt("id");
                if (loadedServices.containsKey(id)) {
                    Service m = loadedServices.get(id);
                    m.title = rs.getString("title");
                    m.published = rs.getBoolean("published");
                    oldSids.add(rs.getInt("owner_id"));
                    oldServices.add(m);
                } else {
                    Menu m = new Menu();
                    m.id = id;
                    m.title = rs.getString("title");
                    m.published = rs.getBoolean("published");
                    newSids.add(rs.getInt("owner_id"));
                    newServices.add(m);
                }
            }
        });

        for (int i = 0; i < newServices.size(); i++) {
            Menu m = newServices.get(i);
            m.owner = User.loadUserById(newSids.get(i));

            // load features
            String featQ = "SELECT * FROM MenuFeatures WHERE menu_id = " + m.id;
            PersistenceManager.executeQuery(featQ, new ResultHandler() {
                @Override
                public void handle(ResultSet rs) throws SQLException {
                    m.featuresMap.put(rs.getString("name"), rs.getBoolean("value"));
                }
            });

            // load sections
            m.sections = Section.loadSectionsFor(m.id);

            // load free items
            m.freeItems = MenuItem.loadItemsFor(m.id, 0);

            // find if "in use"
            String inuseQ = "SELECT * FROM Services WHERE approved_menu_id = " + m.id;
            PersistenceManager.executeQuery(inuseQ, new ResultHandler() {
                @Override
                public void handle(ResultSet rs) throws SQLException {
                    // se c'è anche un solo risultato vuol dire che il menù è in uso
                    m.inUse = true;
                }
            });
        }

        for (int i = 0; i < oldServices.size(); i++) {
            Menu m = oldServices.get(i);
            m.owner = User.loadUserById(oldSids.get(i));

            // load features
            m.featuresMap.clear();
            String featQ = "SELECT * FROM MenuFeatures WHERE menu_id = " + m.id;
            PersistenceManager.executeQuery(featQ, new ResultHandler() {
                @Override
                public void handle(ResultSet rs) throws SQLException {
                    m.featuresMap.put(rs.getString("name"), rs.getBoolean("value"));
                }
            });

            // load sections
            m.updateSections(Section.loadSectionsFor(m.id));

            // load free items
            m.updateFreeItems(MenuItem.loadItemsFor(m.id, 0));

            // find if "in use"
            String inuseQ = "SELECT * FROM Services WHERE approved_menu_id = " + m.id +
                    " OR " +
                    "proposed_menu_id = "+ m.id;
            PersistenceManager.executeQuery(inuseQ, new ResultHandler() {
                @Override
                public void handle(ResultSet rs) throws SQLException {
                    // se c'è anche un solo risultato vuol dire che il menù è in uso
                    m.inUse = true;
                }
            });
        }
        for (Menu m: newServices) {
            loadedServices.put(m.id, m);
        }
        return FXCollections.observableArrayList(loadedServices.values());

        return null;
    }

    
}

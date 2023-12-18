package catering.businesslogic.eventmanagement.event.penal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import catering.businesslogic.eventmanagement.menu.menuitem.MenuItem;
import catering.businesslogic.eventmanagement.menu.section.Section;
import catering.persistence.BatchUpdateHandler;
import catering.persistence.PersistenceManager;

public class Penal {
    private int id;
    private float sum;
    private String description;

    public Penal(int sum, String description) {
        this.sum = sum;
        this.description = description;
    }

    public float getSum() {
        return sum;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Persistence methods

    public static void savePenal(Penal p) {
        String penalInsert = "INSERT INTO catering.Penals (sum, description) VALUES (?, ?);";

        int[] result = PersistenceManager.executeBatchUpdate(penalInsert, 1, new BatchUpdateHandler() {
            @Override
            public void handleBatchItem(PreparedStatement ps, int batchCount) throws SQLException {
                ps.setFloat(1, p.sum);
                ps.setString(2, PersistenceManager.escapeString(p.description));
            }

            @Override
            public void handleGeneratedIds(ResultSet rs, int count) throws SQLException {
                if (count == 0) {
                    p.id = rs.getInt(1);
                }
            }
        });

        if(result[0] != 1) {
            throw new RuntimeException("Error inserting new penal");
        }
    }

    public static void updatePenal(Penal p) {
        String penalUpdate = "UPDATE catering.Penals SET sum = "+p.sum+", description = "+p.description+" WHERE id = "+p.id+";";

        int result = PersistenceManager.executeUpdate(penalUpdate);

        if(result != 1) {
            throw new RuntimeException("Error updating penal");
        }
    }

    public static void deletePenal(Penal p) {
        String penalDelete = "DELETE FROM catering.Penals WHERE id = "+p.id+";";


        int result = PersistenceManager.executeUpdate(penalDelete);

        if(result != 1) {
            throw new RuntimeException("Error updating penal");
        }
    }

}

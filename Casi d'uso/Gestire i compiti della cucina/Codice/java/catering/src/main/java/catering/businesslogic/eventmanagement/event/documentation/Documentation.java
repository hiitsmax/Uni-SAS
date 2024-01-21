package catering.businesslogic.eventmanagement.event.documentation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import catering.persistence.PersistenceManager;
import catering.persistence.ResultHandler;

public class Documentation {
    private java.sql.Date created_on;
    private int id;
    private byte[] file;
    private int eventID;

    private static HashMap<Integer, Documentation> documentationIDCorrespondance = new HashMap<Integer, Documentation>();
    private static HashMap<Integer, ArrayList<Documentation>> documentationEventCorrespondance = new HashMap<Integer, ArrayList<Documentation>>();


    public Documentation(java.sql.Date created_on, int id, byte[] file, int eventID) {
        this.created_on = created_on;
        this.id = id;
        this.file = file;
        this.eventID = eventID;
    }

    public int getEventID(){
        return eventID;
    }

    public void setEventID(int eventID){
        this.eventID = eventID;
    }

    public java.sql.Date getCreated_on() {
        return created_on;
    }

    public void setCreated_on(java.sql.Date created_on) {
        this.created_on = created_on;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public static HashMap<Integer, ArrayList<Documentation>> getDocumentationEventCorrespondance() {
        return documentationEventCorrespondance;
    }

    public static void setDocumentationEventCorrespondance(
            HashMap<Integer, ArrayList<Documentation>> documentationEventCorrespondance) {
        Documentation.documentationEventCorrespondance = documentationEventCorrespondance;
    }

    public static ArrayList<Documentation> getDocumentationByEventID(int eventID) {
        return documentationEventCorrespondance.get(eventID);
    }

    public static void setDocumentationByEventID(int eventID, ArrayList<Documentation> documentation) {
        documentationEventCorrespondance.put(eventID, documentation);
    }

    public static void loadAllDocumentation() {
        String query = "SELECT * FROM Documentations";
        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                while (rs.next()) {
                    Documentation doc = new Documentation(rs.getDate("created_on"), rs.getInt("id"),
                            rs.getBytes("file"), rs.getInt("event_id"));

                    if (documentationEventCorrespondance.get(rs.getInt("event_id")) == null) {
                        documentationEventCorrespondance.put(rs.getInt("event_id"), new ArrayList<Documentation>());
                    }
                    documentationEventCorrespondance.get(rs.getInt("event_id")).add(doc);

                    if(!documentationIDCorrespondance.containsKey(doc.getId())){
                        Documentation docToUpdate = documentationIDCorrespondance.get(doc.getId());
                        docToUpdate.setCreated_on(doc.getCreated_on());
                        docToUpdate.setFile(doc.getFile());
                        docToUpdate.setEventID(doc.getEventID());
                    }else{
                        documentationIDCorrespondance.put(doc.getId(), doc);
                    }
                }

            }
        });
    }

}

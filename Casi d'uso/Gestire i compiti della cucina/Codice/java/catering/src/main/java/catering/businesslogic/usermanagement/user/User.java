package catering.businesslogic.usermanagement.user;

import javafx.collections.FXCollections;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import catering.persistence.PersistenceManager;
import catering.persistence.ResultHandler;
import java.util.HashMap;
import java.util.Map;

public class User {

    private static Map<Integer, User> loadedUsers = FXCollections.observableHashMap();

    public static enum Role {SERVIZIO, CUOCO, CHEF, ORGANIZZATORE};

    private int id;
    private String username;
    private Set<Role> roles;

    public User() {
        id = 0;
        username = "";
        this.roles = new HashSet<>();
    }

    public boolean isChef() {
        return roles.contains(Role.CHEF);
    }

    public String getUserName() {
        return username;
    }

    public int getId() {
        return this.id;
    }

    public String toString() {
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"id\":").append(id).append(",");
        json.append("\"username\":\"").append(username).append("\",");
        json.append("\"roles\":[");
        boolean firstRole = true;
        for (Role role : roles) {
            if (!firstRole) {
                json.append(",");
            }
            json.append("\"").append(role.name()).append("\"");
            firstRole = false;
        }
        json.append("]}");

        return json.toString();
    }

    // STATIC METHODS FOR PERSISTENCE

    public static User loadUserById(int uid) {
        if (loadedUsers.containsKey(uid)) return loadedUsers.get(uid);

        User load = new User();
        String userQuery = "SELECT * FROM Users WHERE id='"+uid+"'";
        PersistenceManager.executeQuery(userQuery, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                load.id = rs.getInt("id");
                load.username = rs.getString("username");
            }
        });
        if (load.id > 0) {
            loadedUsers.put(load.id, load);
            String roleQuery = "SELECT * FROM UserRoles WHERE user_id=" + load.id;
            PersistenceManager.executeQuery(roleQuery, new ResultHandler() {
                @Override
                public void handle(ResultSet rs) throws SQLException {
                    String role = rs.getString("role_id");
                    switch (role.charAt(0)) {
                        case 'c':
                            load.roles.add(User.Role.CUOCO);
                            break;
                        case 'h':
                            load.roles.add(User.Role.CHEF);
                            break;
                        case 'o':
                            load.roles.add(User.Role.ORGANIZZATORE);
                            break;
                        case 's':
                            load.roles.add(User.Role.SERVIZIO);
                    }
                }
            });
        }
        return load;
    }

    public static User loadUser(String username) {
        User u = new User();
        String userQuery = "SELECT * FROM Users WHERE username='"+username+"'";
        PersistenceManager.executeQuery(userQuery, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                u.id = rs.getInt("id");
                u.username = rs.getString("username");
            }
        });
        if (u.id > 0) {
            loadedUsers.put(u.id, u);
            String roleQuery = "SELECT * FROM UserRoles WHERE user_id=" + u.id;
            PersistenceManager.executeQuery(roleQuery, new ResultHandler() {
                @Override
                public void handle(ResultSet rs) throws SQLException {
                    String role = rs.getString("role_id");
                    switch (role.charAt(0)) {
                        case 'c':
                            u.roles.add(User.Role.CUOCO);
                            break;
                        case 'h':
                            u.roles.add(User.Role.CHEF);
                            break;
                        case 'o':
                            u.roles.add(User.Role.ORGANIZZATORE);
                            break;
                        case 's':
                            u.roles.add(User.Role.SERVIZIO);
                    }
                }
            });
        }
        return u;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof User) {
            User u = (User) o;
            return u.id == this.id;
        }
        return false;
    }
}

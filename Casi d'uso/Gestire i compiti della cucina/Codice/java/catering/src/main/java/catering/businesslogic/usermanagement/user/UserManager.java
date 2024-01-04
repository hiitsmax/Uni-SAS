package catering.businesslogic.usermanagement.user;

public class UserManager {
    private User currentUser;

    public void fakeLogin(String username) //TODO: bisogna implementare il login vero!
    {
        this.currentUser = User.loadUser(username);
    }

    public User getCurrentUser() {
        return this.currentUser;
    }

    public void logout() {
        this.currentUser = null;
    }

    public static User getUserByUsername(String username) {
        return User.loadUser(username);
    }

    public static User getUserById(int id) {
        return User.loadUserById(id);
    }
}

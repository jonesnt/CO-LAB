import java.util.ArrayList;
import java.util.UUID;

public class UserManager {
    private static UserManager instance;
    private ArrayList<User> userList;

    private UserManager() {
        // TODO READ DATA FROM USERS JSON
    }

    public static UserManager getInstance() {
        if(instance == null)
            instance = new UserManager();
        return instance;
    }

    public boolean createNewAccount(String newUser, String firstName,
    String lastName, String newPass) {
        User newUser = new User(newUser, firstName, lastName, newPass);
        // TODO WRITE NEW USER TO JSON IF SUCCESSFUL INSTANTIATION
        // TODO RETURN SUCCESS OF USER CREATION
        return false;
    }

    public User loginAttempt(String user, String pass) {
        // TODO SEARCH JSON FOR USERNAME AND ATTEMPT PASS MATCH
        // RETURN NULL IF INVALID PASS OR NO USER FOUND
        return null;
    }

    public boolean editUserInfo(User currentUser, String newFirstName,
    String newLastName, String newUsername, String newPassword) {
        // TODO REPLY ON FUNCTIONALITY IN USER CLASS
        // WHAT IS NOT BEING EDITED WILL BE PASSED AS A NULL
        // ARGUMENT AND NOT CHANGED
        // (this makes it so I don't have to write like 70 methods
        // method edits something different)
        return false;
    }

    public UUID getUserID(String userName) {
        // TODO CHECK IF A USER EXISTS WITHIN THE DATABASE
        // IF THEY DO, RETURN THE UUID FOR ASSIGNMENT
        // IF NOT, RETURN NULL
        return null;
    }
}

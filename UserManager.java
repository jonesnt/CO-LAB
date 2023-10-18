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

    public int createNewAccount(String newUser, String firstName,
    String lastName, String newPass) {
        // Check to see if the attempted username is in the database already
        if(findUser(newUser) != null)
            return 1;  // FLAG 1: username taken
        // Check to see if the password meets requirements (RegEx probably...)
        if(!checkPassword(newPass))
            return 2; // FLAG 2: password invalid
        if(firstName == null || lastName == null)
            return 3;  // FLAG 3: users must provide a first and last name

        // Construct valid user
        User constructedUser = new User(newUser, firstName, lastName, newPass);
        userList.add(constructedUser);
        // TODO When the program is told to stop, write all chagnes to userList to the Users JSON
        return 0;  // FLAG 0: User created successfully, please log in.
    }

    public User loginAttempt(String userName, String pass) {

        for(User specificUser : userList) {
            if(specificUser.equals(userName) && specificUser.attemptPassword(pass)) {
                return specificUser;
            }
        }
        return null;
    }

    public boolean editUserInfo(User currentUser, String newFirstName,
    String newLastName, String newUsername, String newPassword) {
        // WHAT IS NOT BEING EDITED WILL BE PASSED AS A NULL
        // Implement functinality to edit each parameter
        boolean completionFlag = false;  // assume false by default

        if(currentUser == null)  // avoid nullptr exception
            return false;
        if(newFirstName != null) {
            currentUser.setFirstName(newFirstName);
            completionFlag = true;
        }
        if(newLastName != null) {
            currentUser.setLastName(newLastName);
            completionFlag = true;
        }
        if(newUsername != null && findUser(newUsername) == null) {
            currentUser.setUsername(newUsername);
            completionFlag = true;
        }
        if(newPassword != null && checkPassword(newPassword)) {
            currentUser.setPassword(newPassword);
            completionFlag = true;
        }

        // DANGER: user-deletion block
        // TODO Implementing class must immediately log user out after a return of true
        if(currentUser != null &&
        newFirstName == null &&
        newLastName == null &&
        newUsername == null &&
        newPassword == null) {
            completionFlag = userList.remove(currentUser);
        }

        return completionFlag;
    }

    public UUID getUserID(String userName) {
        User specificUser = findUser(userName);
        if(specificUser != null)
            return specificUser.getUserID();
        else
            return null;
        return null;
    }

    /**
     * Helper method to search the userList for a specific
     * username.
     * @param username String representing the desired username.
     * @return User matching the desired username.
     */
    public User findUser(String username) {
        for(User specificUser : userList) {
            if(specificUser.equals(username))
                return specificUser;
        }
        return null;
    }

    // TODO password validity checker
    private boolean checkPassword(String password) {
        // if(REGEX)
        //   return true;
        // else return false;
        return true;  // TEMPORARY
    }
}

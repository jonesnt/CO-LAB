import java.util.ArrayList;
import java.util.UUID;

/**
 * This class is a Singleton which will be implementable
 * by all other classes within the program.
 * This class is effectively a facade through which all
 * requests relating to Users will shall be passed.
 */
public class UserManager {
    private static UserManager instance;
    private ArrayList<User> userList;

    /**
     * This constructor is private because the 
     * class is meant to be a singleton
     */
    private UserManager() {
        userList = DataReader.getInstance().getUsers();
    }

    /**
     * This method returns the active instance of
     * the UserManager class.
     * @return UserManger class reference
     */
    public static UserManager getInstance() {
        if(instance == null)
            instance = new UserManager();
        return instance;
    }

    /**
     * This method creates a new User object.
     * @param newUser String containing a username
     * @param firstName String containing a frist name
     * @param lastName String containing a last name
     * @param newPass String containing a new password
     * @return int represening the success or failure of this method.
     * FLAG 0: SUCCESS
     * FLAG 1: USERNAME TAKEN
     * FLAG 2: PASSWORD INVALID
     * FLAG 3: MISSING INFORMATION
     */
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

    /**
     * This method is the method key to the login security
     * of this program. All login requests are validated through
     * this method. If a login request is valid, a reference
     * to the corresponding user will be returned. Otherwise,
     * null is returned.
     * @param userName String containing a username
     * @param pass String containing a password
     * @return User object corresponding to the logged-in user
     */
    public User loginAttempt(String userName, String pass) {

        for(User specificUser : userList) {
            if(specificUser.equals(userName) && specificUser.attemptPassword(pass)) {
                return specificUser;
            }
        }
        return null;
    }

    /**
     * This method is used to edit a previously-created User.
     * currentUser must not be null. There must be either
     * exactly one or zero newX arguments where X represents
     * the element to be chaged.
     * WARNING: PASSING ALL newX ARGUMENTS AS NULL WILL RESULT
     * IN USER DELETION
     * @param currentUser User to be edited
     * @param newFirstName String for a new first name
     * @param newLastName String for a new last name
     * @param newUsername String for a new username
     * @param newPassword String for a new password
     * @return boolean representing the success of this method
     */
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

    /**
     * This method returns the UUID representing a user
     * based on a search of the userList for their corresponding
     * username.
     * @param userName String representing a username.
     * @return UUID corresponding to an existing user.
     */
    public UUID getUserID(String userName) {
        User specificUser = findUser(userName);
        if(specificUser != null)
            return specificUser.getUserID();
        else
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

    public User findUser(UUID userID) {
        for(User specUser : userList) {
            if(specUser.getUserID() == userID)
            return specUser;
        }
        return null;
    }

    // TODO password validity checker
    /**
     * This method compares a password attempt to the
     * program's requirements and returns a boolean
     * as to whether the password is acceptable.
     * @param password String representing a password attempt
     * @return boolean representing whether the password is acceptable
     */
    private boolean checkPassword(String password) {
        if(password.length() >= 7)
            return true;
        return false;
    }

    public String getUserName(UUID userID) {
        for(User specUser : userList) {
            if (specUser.getUserID() == userID)
                return specUser.getUsername();
        }
        return null;
    }

    public void exit() {
        DataWriter.getInstance().saveUsers(userList);
    }

    public ArrayList<User> getUserList() {
        return userList;
    }
}

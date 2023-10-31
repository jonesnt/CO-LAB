import java.util.UUID;

/**
 * This class represents a service User when instantiated.
 * @author jonesnt
 */
public class User {
    private UUID userID;
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    /**
     * This constructor instantiates a new User with the passed parameters.
     * The parameters are verified ahead of time by the implementing class.
     * @param username String representing a new username
     * @param firstName String representing a new first name
     * @param lastName String representing a new last name
     * @param password String representing a new password
     */
    public User(String username, String firstName, String lastName,
    String password) {
        userID = UUID.randomUUID();
        setUsername(username);
        setFirstName(firstName);
        setLastName(lastName);
        setPassword(password);
    }

    /**
     * This method gets the user's first name
     * @return String representing the user's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * This method gets the user's last name
     * @return String representing the user's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * This method sets a new username.
     * @param username String representing a username
     */
    public void setUsername(String username) {
        // no need to check for username duplication
        // Implementing class UserManger checks for
        // duplication in already-instantiated userList
        username = username;
    }

    /**
     * This method sets the user's first name
     * @param firstName String representing the user's first name
     */
    public void setFirstName(String firstName) {
        firstName = firstName;
    }

    public void setLastName(String lastName) {
        lastName = lastName;
    }

    /**
     * This method sets the user's password
     * @param password String representing a password
     */
    public void setPassword(String password) {
        // no need to check password requirements
        // Implementing class UserManger checks requirements
        password = password;
    }

    /**
     * This method checks to see if a username matches the current
     * user's username. 
     * @param comparison Checks whether a username matches
     * the instantiated User.
     * @return boolean signifying whether the compared
     * username matches the compared username
     */
    public boolean equals(String comparison) {
        return username == comparison;
    }

    /**
     * This method checks to see if a password matches
     * the User's password and returns a boolean for
     * the validity of a login attempt.
     * @param passAttempt String representing a password attempt
     * @return boolean representing whether the password was correct
     */
    public boolean attemptPassword(String passAttempt) {
        return passAttempt == password;
    }

    /**
     * This method returns the UUID of the instantiated.
     * @return UUID assigned to the instantiated user.
     */
    public UUID getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }
}

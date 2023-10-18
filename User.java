import java.util.UUID;

/**
 * @author jonesnt
 */
public class User {
    private UUID userID;
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    public User(String username, String firstName, String lastName,
    String password) {
        userID = UUID.randomUUID();
        setUsername(username);
        setFirstName(firstName);
        setLastName(lastName);
        setPassword(password);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setUsername(String username) {
        // no need to check for username duplication
        // Implementing class UserManger checks for
        // duplication in already-instantiated userList
        username = username;
    }

    public void setFirstName(String firstName) {
        firstName = firstName;
    }

    public void setLastName(String lastName) {
        lastName = lastName;
    }

    public void setPassword(String password) {
        // no need to check password requirements
        // Implementing class UserManger checks requirements
        password = password;
    }

    /**
     * 
     * @param comparison Checks whether a username matches
     * the instantiated User.
     * @return boolean signifying whether the compared
     * username matches the compared username
     */
    public boolean equals(String comparison) {
        return username == comparison;
    }

    public boolean attemptPassword(String passAttempt) {
        return passAttempt == password;
    }

    public UUID getUserID() {
        return userID;
    }
}

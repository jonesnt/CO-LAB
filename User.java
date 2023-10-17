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
        // TODO find out if there is a way to reject instantiation
        // in the even that an improper user/password is given
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean setUsername(String username) {
        // TODO MUST CHECK DATABASE AGAINST TAKEN USERNAMES
        return false;
    }

    public void setFirstName(String firstName) {
        firstName = firstName;
    }

    public void setLastName(String lastName) {
        lastName = lastName;
    }

    public boolean setPassword(String password) {
        // TODO check against password requirements
        return false;
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

    // TODO getUserID() 
}

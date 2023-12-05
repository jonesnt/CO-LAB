import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * This class represents an event occuring which
 * changed the status or form of a Task.
 * @author jonesnt
 */
public class TaskEvent {
    private ZonedDateTime eventTime;
    private String eventName;
    private UUID relatedUser;
    private UserManager instance;

    /**
     * This constructor accepts the name of the event
     * and the User involved with said event.
     * @param eventName String representing the event name
     * @param involvedUser User representing the involved user
     */
    public TaskEvent(String eventName, User involvedUser) {
        this.eventTime = ZonedDateTime.now();
        this.eventName = eventName;
        this.relatedUser = involvedUser.getUserID();
    }

    public TaskEvent(ZonedDateTime eventTime, String eventName, UUID involvedUser) {
        this.eventTime = eventTime;
        this.eventName = eventName;
        this.relatedUser = involvedUser;
    }

    /**
     * This method returns the name of the event
     * @return String representing the event name
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * This method returns the first and last name
     * of the user invovled in the event
     * @return String representing the first and last name
     * of the user involved with the event
     */
    public User getInvolvedUser() {
        instance = UserManager.getInstance();
        User currentUser = instance.findUser(relatedUser);
        if(currentUser != null) {
            return currentUser;
        }

        return new User("none", "deleted", "user", "");
        
    }

    public ZonedDateTime getEventTime() {
        return eventTime;
    }
}

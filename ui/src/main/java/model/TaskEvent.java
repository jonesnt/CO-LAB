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
    //  CHANGE THIS TO A TYPE USER!!!!
    private User relatedUser;
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
        //  CHANGE THIS TO A TYPE USER!!!!
        this.relatedUser = involvedUser;
    }

    //  CHANGE THE PARAMETER TO TYPE USER!!!
    public TaskEvent(ZonedDateTime eventTime, String eventName, User involvedUser) {
        this.eventTime = eventTime;
        this.eventName = eventName;
        //  CHANGE THIS TO A TYPE USER!!!
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
        if(relatedUser != null) {
            return relatedUser;
        }

        return new User("none", "deleted", "user", "");
        
    }

    public ZonedDateTime getEventTime() {
        return eventTime;
    }
}

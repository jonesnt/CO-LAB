import java.time.ZonedDateTime;

/**
 * This class represents an event occuring which
 * changed the status or form of a Task.
 * @author jonesnt
 */
public class TaskHistory {
    private ZonedDateTime eventTime;
    private String eventName;
    private User involvedUser;

    /**
     * This constructor accepts the name of the event
     * and the User involved with said event.
     * @param eventName String representing the event name
     * @param involvedUser User representing the involved user
     */
    public TaskEvent(String eventName, User involvedUser) {
        eventTime = ZonedDateTime.now();
        eventName = eventName;
        involvedUser = involvedUser;
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
    public String getInvolvedUser() {
        return involvedUser.getFirstName + " "
        involvedUser.getLastName;
    }
}

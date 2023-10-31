import java.util.UUID;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.awt.Color;

/**
 * @author jonesnt
 */
public class Task {
  private UUID taskID;
  private String name;
  private String description;
  private LinkedList<TaskEvent> history;
  private ArrayList<ToDo> todos;
  private ArrayList<Comment> comments;
  private ArrayList<UUID> assignedUsers;
  private boolean isCompleted;
  private Color bgColor;
  private Color fgColor;
  private String columnTag;

  public Task(String name, String description, String columnTag) {
    taskID =     
  }

  public String getColumnTag() {
    return columnTag;
  }
}
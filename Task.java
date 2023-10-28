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
    taskID = UUID.randomUUID();
    name = name;
    description = description;
    history = null;
    todos = null;
    comments = null;
    assignedUsers = null;
    isCompleted = false;
    bgColor = null;
    fgColor = null;
    columnTag = columnTag;
  }

  public Task(UUID taskID, String name, String description, LinkedList<TaskEvent> history, 
  ArrayList<ToDo> todos, ArrayList<Comment> comments, ArrayList<UUID> assignedUsers, 
  boolean isCompleted, Color bgColor, Color fgColor, String columnTag) {
    taskID = taskID;
    name = name;
    description = description;
    history = history;
    todos = todos;
    comments = comments;
    assignedUsers = assignedUsers;
    isCompleted = isCompleted;
    bgColor = bgColor;
    fgColor = fgColor;
    columnTag = columnTag;
  }

  public boolean assignUser(UUID userID) {
    for(UUID specificUser : assignedUsers) {
      if(specificUser.equals(userID))
        return false;
    }
    assignedUsers.add(userID);
    return true;
  }

  public boolean unassignUser(UUID userID) {
    for(UUID specificUser : assignedUsers) {
      if(specificUser.equals(userID)) {
        assignedUsers.remove(userID);
        return true;
      }
    }
    return false;
  }

  public boolean editTask(String newName, String newDescription) {
    if(newName == null || newDescription == null)
      return false;
    if(newName != null)
      name = newName;
    if(newDescription != null)
      description = newDescription;
    return true;
  }

  public void changeCompletionStatus() {
    isCompleted = !isCompleted;
  }

  // TODO: FIGURE OUT APPROPRIATE ERROR CHECKING
  public boolean changeColor(Color bgColor, Color fgColor) {
    bgColor = bgColor;
    fgColor = fgColor;
    return true;
  }

  public boolean addComment(Comment newComment) {
    comments.add(newComment);
    return true;
  }

  public boolean addToDo(ToDo newToDo)  

}
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

  public Task(String name, String description, String columnTag, User currentUser) {
    this.taskID = UUID.randomUUID();
    this.name = name;
    this.description = description;
    this.history = null;
    this.todos = null;
    this.comments = null;
    this.assignedUsers = null;
    this.isCompleted = false;
    this.bgColor = null;
    this.fgColor = null;
    this.columnTag = columnTag;
    addEvent(currentUser, "created this task.");
  }

  public Task(UUID taskID, String name, String description, LinkedList<TaskEvent> history, 
  ArrayList<ToDo> todos, ArrayList<Comment> comments, ArrayList<UUID> assignedUsers, 
  boolean isCompleted, String columnTag) {
    this.taskID = taskID;
    this.name = name;
    this.description = description;
    this.history = history;
    this.todos = todos;
    this.comments = comments;
    this.assignedUsers = assignedUsers;
    this.isCompleted = isCompleted;
    this.columnTag = columnTag;
  }

  public boolean assignUser(UUID userID, User currentUser) {
    for(UUID specificUser : assignedUsers) {
      if(specificUser.equals(userID))
        return false;
    }
    assignedUsers.add(userID);
    addEvent(currentUser, "assigned user " +
    UserManager.getInstance().getUserName(userID));
    return true;
  }

  public boolean unassignUser(UUID userID, User currentUser) {
    for(UUID specificUser : assignedUsers) {
      if(specificUser.equals(userID)) {
        assignedUsers.remove(userID);
        addEvent(currentUser, "removed user " +
        UserManager.getInstance().getUserName(userID));
        return true;
      }
    }
    return false;
  }

  public boolean editTask(String newName, String newDescription, User currentUser) {
    if(newName == null || newDescription == null)
      return false;
    if(newName != null)
      name = newName;
    if(newDescription != null)
      description = newDescription;
    addEvent(currentUser, "changed task name to \"" + newName + 
    "\" and task description to \"" + newDescription + "\"");
    return true;
  }

  public void changeCompletionStatus(User currentUser) {
    isCompleted = !isCompleted;
    addEvent(currentUser, "changed completion status to " + isCompleted);
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

  public boolean addToDo(ToDo newToDo, User cuerrentUser) { 
    if(iterate(newToDo.getName())) {
      todos.add(newToDo);
      addEvent(cuerrentUser, "added ToDo of name " +
      newToDo.getName());
      return true;
    }
    return false;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public ArrayList<UUID> getAssignedUsers() {
    return assignedUsers;
  }

  public UUID getID() {
    return taskID;
  }

  public LinkedList<TaskEvent> getHistory() {
    return history;
  }

  private void addEvent(User currentUser, String eventDescription) {
    history.add(new TaskEvent(eventDescription, currentUser));
  }
  public ArrayList<ToDo> getTodo() {
    return todos;
  }
  public ArrayList<Comment> getComments(){
    return comments;
  }
  public boolean isCompleted(){
    return isCompleted;
  }


  public String getColumnTag() {
    return columnTag;
  }

  public boolean iterate(String attempt) {
    for(ToDo specificToDo : todos) {
      if(specificToDo.equals(attempt))
        return false;
    }
    return true;
  }

  public boolean equals(String nameAttempt) {
    return name == nameAttempt;
  }

  public boolean removeTopLevelComment(int specComment, UUID user) {
    int choice = specComment - 1;
    if(choice >= 0 && choice < comments.size()) {
      if(comments.get(choice).getAuthor() == user) {
        comments.remove(choice);
        return true;
      }
    }
    return false;
  }
}
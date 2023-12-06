import java.util.UUID;

// TODO: Revise documentation!
/**
 * @author hadleya, jonesnt
 * This class holds the data for ToDo, which is a subset of a Task.
 * ToDos are essentially subtasks, in which there are multiple ToDos for one
 * task.
 * These are arranged in an ArrayList in a Task.
 */
public class ToDo {
  private UUID toDoID;
  private String name;
  private UUID assignedUser;
  private boolean isCompleted;

  /**
   * Initializes a ToDo Instance.
   * Requires the following:
   * @param toDoName String to call the specific ToDo
   * @param task The actual task needed on hand
   * @param completion Whether the task is completed or not
   * @param priority The integer level of the priority of the task
   * @param demarkation TODO: Write
   */
  public ToDo(String name) {
    this.toDoID = UUID.randomUUID();
    this.name = name;
    this.assignedUser = null;
    this.isCompleted = false;
  }

  public ToDo(UUID toDoID, String name, UUID assignedUser, boolean isCompleted) {
    this.toDoID = toDoID;
    this.name = name;
    this.assignedUser = assignedUser;
    this.isCompleted = isCompleted;
  }
  
  public boolean assignUser(UUID userID) {
    if(userID == null)
      return false;
    assignedUser = userID;
    return true;
  }

  public String getName() {
    return name;
  }

  public UUID getAssignedUser() {
    return assignedUser;
  }

  public boolean getCompletion() {
    return isCompleted;
  }

  public void changeCompletion() {
    isCompleted = !isCompleted;
  }
  public UUID getID() {
    return toDoID;
  }

  public boolean editToDo(String newName) {
    if(newName == null)
      return false;
    name = newName;
    return true;
  }

  public boolean equals(String nameAttempt) {
    return name == nameAttempt;
  }
}
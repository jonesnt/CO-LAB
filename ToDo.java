import java.util.UUID;

/**
 * @author hadleya
 * This class holds the data for ToDo, which is a subset of a Task.
 * ToDos are essentially subtasks, in which there are multiple ToDos for one
 * task.
 * These are arranged in an ArrayList in a Task.
 */
public class ToDo {
  private String task;
  private boolean completion;
  private String toDoID;
  private String toDoName;
  private int priority;
  private String demarkation;

  /**
   * Initializes a ToDo Instance.
   * Requires the following:
   * @param toDoName String to call the specific ToDo
   * @param task The actual task needed on hand
   * @param completion Whether the task is completed or not
   * @param priority The integer level of the priority of the task
   * @param demarkation TODO: Write
   */
  public ToDo(String toDoName, String task, boolean completion, int priority, 
              String demarkation) {
    toDoID = UUID.randomUUID();
    setToDoName(toDoName);
    setTask(task);
    setCompletion(completion);
    setPriority(priority);
    setDemarkation(demarkation);
  }


  //  put in proper setters and getters for manipulation
  
  /**
   * Sets the ToDoName.
   * @param toDoName The name of the ToDo
   */
  public void setToDoName(String toDoName) {
    toDoName = toDoName;
  }

  /**
   * Sets the Task
   * @param task The task needed for this ToDo
   */
  public void setTask(String task) {
    task = task;
  }

  /**
   * Sets whether the ToDo ic completed or not
   * @param completion Whather the task is completed
   */
  public void setCompletion(boolean completion) {
    completion = completion;
  }

  /**
   * Sets the priority of the task
   * @param priority the level of priority needed
   */
  public void setPriority(int priority) {
    priority = priority;
    //  TODO: How do I make sure that the 
  }

  /**
   * Sets the demarkation
   * @param demarkation the column it should be located in
   */
  public void setDemarkation(String demarkation) {
    demarkation = demarkation;
    //  TODO: should there be a call here for the change of demarkation?
  }

  /**
   * Returns the name of the ToDo
   * @return the name of the ToDo
   */
  public String getToDoName() {
    return toDoName;
  }

  /**
   * Returns the task description
   * @return the task description
   */
  public String getTask() {
    return task;
  }

  /**
   * Returns whether the task is completed
   * @return whether the task is completed
   */
  public boolean getCompletion() {
    return completion;
  }

  /**
   * Returns the priority of the task
   * @return the priority of the task
   */
  public int getPriority() {
    return priority;
  }

  /**
   * Returns the demarkation
   * @return the demarkation it's located in
   */
  public String getDemarkation() {
    return demarkation;
  }
}
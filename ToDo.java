import java.util.UUID;


public class ToDo {
  private String task;
  private boolean completion;
  private String toDoID;
  private String toDoName;
  private int priority;
  private String demarkation;

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
  
  public void setToDoName(String toDoName) {
    toDoName = toDoName;
  }

  public void setTask(String task) {
    task = task;
  }

  public void setCompletion(boolean completion) {
    completion = completion;
  }

  public void setPriority(int priority) {
    priority = priority;
  }

  public void setDemarkation(String demarkation) {
    demarkation = demarkation;
  }

  public String getToDoName() {
    return toDoName;
  }

  public String getTask() {
    return task;
  }

  public boolean getCompletion() {
    return completion;
  }

  public int getPriority() {
    return priority;
  }

  public String getDemarkation() {
    return demarkation;
  }
}

public class ToDo {
  private String task;
  private boolean completion;
  private String toDoID;
  // NOT from UML, but needed.
  private String toDoName;
  private int priority;
  private String demarkation;

  public ToDo(String toDoName, int priority, String demarkation) {
    // TODO
  }

  public void changeCompletion() {
    completion = !completion;
  }

  public void editToDo(String newToDo) {
    this.task = newToDo;
  }

  public boolean changePriority(int priority) {
    // TODO
  }
}
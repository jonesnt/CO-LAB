import java.awt.*;  //  This may need to be changed in the future

public class Facade {
  protected User currentUser;

  public Facade() {
    // TODO
  }

  public void addTask(Task task) {
    // TODO
  }

  public boolean removeTask(Task task) {
    // TODO
  }

  public boolean addToDo(Task task, ToDo todo) {
    // TODO
  }

  public void createProject(Project project) {
    // TODO
  }

  public boolean removeProject(Project project) {
    // TODO
  }

  public boolean addComment(Task task, Comment comment) {
    // TODO
  }

  public boolean removeComment(Task task, Comment comment) {
    // TODO
  }

  public boolean addReply(Task task, Comment parentComment, Comment childComment) {
    // TODO
  }

  public boolean removeReply(Task task, Comment parentComment, Comment childComment) {
    // TODO
  }

  public boolean logInUser(User user, String password) {
    // TODO
  }

  public void logOutUser() {
    // TODO
  }

  public void changeBG(Color color) {
    // TODO
  }

  public boolean changeTaskColor(Color color, Task task) {
    // TODO
  }
}

import java.awt.*;  //  This may need to be changed in the future

public class Facade {
  protected User currentUser;

  public Facade() {
    //  empty, as we don't need to do anything
    //  should the be a singleton?
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

  /**
   * Attempts to log in the user. Returns true if the log in was successful,
   * and false if it was not.
   * @param user The username attempt
   * @param password The password attempt
   * @return whether the login was successful
   */
  public boolean logInUser(User user, String password) {
    //  Load in a user attempt
    User testUser = UserManager.loginAttempt(user, password);
    //  check if the login was successful
    if(testUser == null) {
      // wasn't successful
      return false;
    }
    return true;

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

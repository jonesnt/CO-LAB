import java.awt.*;  //  This may need to be changed in the future

public class Facade {
  protected User currentUser;

  public Facade() {
    //  empty, as we don't need to do anything
    //  should the be a singleton?
  }

  /**
   * Adds a task to a project
   * @param project The project you want to add a task to
   * @param task The task you want to add
   * @return Whether it was successful
   */
  public boolean addTask(Project project, Task task) {
    /**
     * changes from uml:
     * make it a boolean
     * add a project parameter
     */
    // check if the project exists
    //  TODO: does this have to deal with the datareader?
    // if it does, add the task
  }

  public boolean removeTask(Project project, Task task) {
    /**
     * changes from UML
     * add project parameter
     */
  }

  public boolean addToDo(Task task, ToDo todo) {
    // TODO
  }

  /**
   * Adds a project to the list
   * @param project The project wanting to be added
   */
  public void createProject(Project project) {
    ProjectManager.addProject(project);
  }

  public boolean removeProject(Project project) {
    ProjectManager.removeProject(project);
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
   * 
   * and false if it was not.
   * @param user The username attempt
   * @param password The password attempt
   * @return whether the login was successful
   */
  public boolean logInUser(User user, String password) {
    //  Load in a user attempt
    currentUser = UserManager.loginAttempt(user, password);
    //  check if the login was successful
    if(currentUser == null) {
      // wasn't successful
      return false;
    }
    return true;
  }

  /**
   * Logs out user
   */
  public void logOutUser() {
    currentUser = null;
  }

  /**
   * Chacnges the color of the background
   * @param color The color we want to change
   */
  public void changeBG(Color color) {
    Customizer.changeBG(color);
  }

  public boolean changeTaskColor(Color color, Task task) {
    // TODO
  }
}

import java.awt.*;  //  This may need to be changed in the future

/**
 * @author hadleya
 * This class is a facade, designed for the UI to have a single spot to talk
 * to all of the underlying classes. This simplifies the UI section by having
 * a single place to interact withall funcionality of the system.
 * 
 * WARNING: THIS IS NONFUNCTIONAL! Current KNOWN broken classes include:
 * addTask
 * addToDo
 * changeTaskColor
 */
public class Facade {
  protected User currentUser;

  /**
   * Initializes the Facade Class
   */
  public Facade() {
    //  initialize singletons
    ProjectManager.getInstance();
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
    // try/catch with task
    // this will not work unless Project has an addTask(Task task) in it
    try {
       project.createTask(task);
       return true;
    } catch(Exception e) {
      // unsuccessful
      return false;
    }
  }

  /**
   * Removes a task from a specific project.
   * @param project The project to remove the task from
   * @param task The task to remove
   * @return Whether the removal was successful
   */
  public boolean removeTask(Project project, Task task) {
    /**
     * changes from UML
     * add project parameter
     */
    //  try/catch with project and task
    try {
      project.removeTask(task);
      return true;
    } catch(Exception e) {
      //  unsuccessful
      return false;
    }
  }
  
  /**
   * Adds a ToDo to a task.
   * @param project The project to get the task
   * @param task The task to add a ToDo to
   * @param todo The todo to add
   * @return Whather the removal was successful
   */
  public boolean addToDo(Project project, Task task, ToDo todo) {
    /**
     * changed from UML
     * add project parameter
     */
    // try/catch with project and task
    try {
      //  same problem as addTask, there's not a function implemented yet
      project.task.addToDo(todo);
      return true;
    } catch(Exception e) {
      //  unsuccessful
      return false;
    }
  }

  /**
   * Adds a project to the list
   * @param project The project wanting to be added
   */
  public void createProject(Project project) {
    ProjectManager.addProject(project);
  }

  /**
   * Removes a project
   * @param project The project to be removed
   * @return Whether the removal was successful
   */
  public boolean removeProject(Project project) {
    ProjectManager.removeProject(project);
  }

  /**
   * Adds a comment to a task.
   * @param project The project to get the task
   * @param task The task to add a comment to
   * @param comment The comment to add
   * @return Whether the addition was successful
   */
  public boolean addComment(Project project, Task task, Comment comment) {
    /**
     * Changes from UML:
     * add project parameter
     */
    // TODO
  }

  /**
   * Removes a comment from a task
   * @param project The project to get the task
   * @param task The task to add a comment to
   * @param comment The comment to remove
   * @return Whether the removal was successful
   */
  public boolean removeComment(Project project, Task task, Comment comment) {
    /**
     * Changes from UML:
     * add project parameter
     */
    // try/catch with project and task
    try {
      project.task.
      return true;
    } catch(Exception e) {
      return false;
    }
  }

  /**
   * Adds a reply to a comment
   * @param project The project to get a task
   * @param task The task to get a the parent commetn
   * @param parentComment The parent comment to add a reply to
   * @param childComment The reply to add
   * @return Whether the reply was successful
   */
  public boolean addReply(Project project, Task task, Comment parentComment, Comment childComment) {
    /**
     * Changes from UML
     * add project parameter
     */
    // try/catch with project, task, and parentcomment
    try {
      project.task.parentComment.addComment(childComment);
      //  would this work? can i access .parentComment, or would it need to be something else
      return true;
    } catch(Exception e) {
      return false;
    }
  }

  /**
   * Adds a reply to a comment
   * @param project The project to get a task
   * @param task The task to get a the parent commetn
   * @param parentComment The parent comment to remove the reply from
   * @param childComment The reply to remove
   * @return Whether the removal was successful
   */
  public boolean removeReply(Project project, Task task, Comment parentComment, Comment childComment) {
    /**
     * Changes from UML
     * add project parameter
     */
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

  /**
   * Changes the color of a specific task
   * @param color The color to change the task to
   * @param task The task who's color is going to change
   * @return Whether the change was successful
   */
  public boolean changeTaskColor(Color color, Task task) {
    // TODO
  }
}

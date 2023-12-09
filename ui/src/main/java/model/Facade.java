package model;
import java.util.ArrayList;
import java.util.UUID;

/**
 * This class serves as the sole operation manager
 * between the front end and the back end for
 * the CO-LAB program. It is a singleton.
 */
public class Facade {
  public static Facade facade;
  public Manager manager;

  /**
   * This is a private constructor for the singleton
   */
  private Facade() {
    manager = Manager.getInstance();
  }

  /**
   * This is the Singleton's access method.
   * @return an instance of the singleton
   */
  public static Facade getInstance() {
    if (facade == null) {
      facade = new Facade();
    }
    return facade;
  }

  /**
   * This method logs in the user and returns a boolean
   * representing whether or not the login was sucessful
   * @param user A username
   * @param pass A password
   * @return A boolean representing the user's login state
   */
  public boolean logInUser(String user, String pass) {
    return manager.logInUser(user, pass);
  }

  /**
   * This logs the user out and triggers
   * the back end's save procedure.
   */
  public void logOutUser() {
    manager.logOutUser();
  }

  /**
   * This method changes the current project
   */
  public boolean changeCurrentProject(int projNum) {
    return manager.changeCurrentProject(projNum);
  }

  /**
   * This method changs the current task based on the user
   * choice.
   * @param taskNum integer representing user choice
   * @return boolean representing whether change was sucessful
   */
  public boolean changeCurrentTask(int taskNum) {
    return manager.changeCurrentTask(taskNum);
  }

  /**
   * This method changes the current toDo based on the user
   * choice.
   * @param toDoNum integer representing user choice
   * @return a boolean representing whether the change was 
   * successful
   */
  public boolean changeCurrentToDo(int toDoNum) {
    return manager.changeCurrentToDo(toDoNum);
  }

  /**
   * This method changes the current comment being rendered
   * @param commentNum integer represting the chocie of comment
   * @return boolean representing whether the change was successful
   */
  public boolean changeCurrentComment(int commentNum) {
    return manager.changeCurrentComment(commentNum);
  }

  /**
   * This method changes the current column choice for a
   * specific task
   * @param task instantiated Task object to be reassigned
   * @param columnChoice integer representing which column
   * the task is supposed to be placed in.
   * @return a boolean representing whether the change was made
   */
  public boolean changeColumn(Task task, int columnChoice) {
    return manager.changeColumn(task, columnChoice);
  }

  /**
   * This method adds a new project to a user's account
   * @param newProject instantiated project to be added
   * @return boolean representing whether the addition
   * was successful
   */
  public boolean addProject(Project newProject) {
    return manager.addProject(newProject);
  }

  /**
   * This method adds a new task to a specific Project
   * @param newTask instantiated Task to be added
   * @return boolean representing whether the Task
   * was added
   */
  public boolean addTask(Task newTask) {
    return manager.addTask(newTask);
  }

  /**
   * This method adds a new toDo to a task
   * @param newToDo instantiated ToDo object to be
   * added to a Task
   * @return boolean representing whether the addition
   * was successful
   */
  public boolean addToDo(ToDo newToDo) {
    return manager.addToDo(newToDo);
  }

  /**
   * This method adds a new comment to a Task
   * @param newComment instantiated Comment to be added
   * to the Task
   * @return boolean representing whether the addition was
   * a success
   */
  public boolean addComment(Comment newComment) {
    return manager.addComment(newComment);
  }

  /**
   * This method adds a reply to an existing comment
   * @param reply String of reply to be added
   * @return boolean representing whether the reply
   * was added
   */
  public boolean addReply(String reply) {
    return manager.addReply(reply);
  }

  /**
   * This method adds a new column to a
   * pre-existing project
   * @param columnName String for a column name
   * @return boolean repesenting whether the addition
   * was successful
   */
  public boolean addColumn(String columnName) {
    return manager.addColumn(columnName);
  }

  /**
   * This method removes a pre-existing project
   * @param projNum integer representing the project to
   * be removed
   * @return boolean representing whether the project
   * has been removed
   */
  public boolean removeProject(int projNum) {
    return manager.removeProject(projNum);
  }

  /**
   * This method removes a task given a user's chocie
   * @param taskNum integer representing the task to be removed
   * @return boolean representing whether the task was removed
   */
  public boolean removeTask(int taskNum) {
    return manager.removeTask(taskNum);
  }

  /**
   * This method removes a comment given a user's choice
   * @param commentNum integer representing the comment to
   * be removed
   * @return boolean representing whether the comment
   * has been removed
   */
  public boolean removeComment(int commentNum) {
    return manager.removeComment(commentNum);
  }

  /**
   * This method removes a pre-existing column given
   * a user choice
   * @param columnChoice integer representing which column
   * should be removed
   * @return boolean representing whether the column has
   * been removed
   */
  public boolean removeColumn(int columnChoice) {
    return manager.removeColumn(columnChoice);
  }

  /**
   * This method edits a project given user-defined params
   * @param name String representing a new name
   * @param description String representing a new description
   * @return boolean representing whether editing was a success
   */
  public boolean editProject(String name, String description) {
    return manager.editProject(name, description);
  }

  /**
   * This method edits a task
   * @param name String representing a name
   * @param description String representing a description
   * @return boolean representing sucess for editing
   */
  public boolean editTask(String name, String description) {
    return manager.editTask(name, description);
  }

  /**
   * This method edits a todo based on user specifications
   * @param name String representing a ToDo name
   * @param assignedUser User representing an assigned user
   * @param completion boolean representing a completion state
   * @return boolean representing change success
   */
  public boolean editToDo(String name, User assignedUser, boolean completion) {
    return manager.editToDo(name, assignedUser, completion);
  }

  /**
   * This method edits a comment
   * @param newComment User-defined new comment message
   * @return boolean representing change success
   */
  public boolean editComment(String newComment) {
    return manager.editComment(newComment);
  }

  /**
   * This method assigns a user to a project
   * @param UserID UUID of user
   * @return boolean representing assignment success
   */
  public boolean assignProjectUser(UUID UserID) {
    return manager.assignProjectUser(UserID);
  }

  /**
   * This method unassigns a user from a project
   * @param UserID UUID of user
   * @return boolean representing assignment success
   */
  public boolean unassignProjectUser(UUID UserID) {
    return manager.unassignProjectUser(UserID);
  }

  /**
   * This method assignes a user to a task
   * @param UserID UUID representing a user
   * @return boolean representing task assignment
   * success
   */
  public boolean assignTaskUser(UUID UserID) {
    return manager.assignTaskUser(UserID);
  }

  /**
   * This method unassigns a user from a task
   * @param UserID UUID representing a user
   * @return boolean representing task unassignment
   * success
   */
  public boolean unassignTaskUser(UUID UserID) {
    return manager.unassignTaskUser(UserID);
  }

  public void render() {
    // TODO
  }

  /**
   * This method finds a user based on a username
   * @param username String of a username
   * @return instantiated User matching a username
   */
  public User findUser(String username) {
    return manager.findUser(username);
  }

  /**
   * This method gets the master Project list
   * @return ArrayList of all Projects
   */
  public ArrayList<Project> getMasterList() {
    return manager.getMasterList();
  }

  /**
   * This method gets the current User
   * @return User object of current user
   */
  public User getCurrentUser() {
    return manager.getCurrentUser();
  }

  /**
   * This method gets the current project
   * @return instantiated Project reference
   */
  public Project getCurrentProject() {
    return manager.getCurrentProject();
  }

  /**
   * This method gets the current task
   * @return instantaited Task object reference
   */
  public Task getCurrentTask() {
    return manager.getCurrentTask();
  }

  /**
   * This method gets the current ToDo
   * @return instantiated ToDo object reference
   */
  public ToDo getCurrentToDo() {
    return manager.getCurrentToDo();
  }

  /**
   * This method gets the current Comment
   * @return instantiated reference to the current Comment
   */
  public Comment getCurrentComment() {
    return manager.getCurrentComment();
  }

  /**
   * This method gets the column list
   * @return an ArrayList of all possible Columns
   */
  public ArrayList<String> getColumnList() {
    return manager.getColumnList();
  }

  /**
   * This method gets the project list
   * @return an ArrayList of all Projects
   */
  public ArrayList<Project> getProjectList() {
    return manager.getProjectList();
  }

  /**
   * This method gets the task list
   * @return an ArrayList of all Tasks
   */
  public ArrayList<Task> getTaskList() {
    return manager.getTaskList();
  }

  /**
   * This method gets the ToDo list
   * @return an ArrayList of all ToDo objects
   */
  public ArrayList<ToDo> getToDoList() {
    return manager.getToDoList();
  }

  /**
   * This method gets the CommentList
   * @return an ArrayList of all Comments in
   * a Task
   */
  public ArrayList<Comment> getCommentList() {
    return manager.getCommentList();
  }

  /**
   * This method gets a list of all Users
   * @return an ArrayList of all Users
   */
  public ArrayList<User> getUserList() {
    return manager.getuserList();
  }
}
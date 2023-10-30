
import java.util.ArrayList;
import java.util.UUID;

/**
 * @author hadleya
 * This class is a facade, designed for the UI to have a single spot to talk
 * to all of the underlying classes. This simplifies the UI by having
 * a single place to interact withall funcionality of the system.
 * This is a revision or the original class, to increase functionality and talk
 */
public class Facade {
  private User currentUser;
  private Project currentProject;
  private ArrayList<String> currenTaskList;
  private Task currentTask;
  private ArrayList<String> currentToDoList;
  private ToDo currentToDo;
  private ArrayList<String> currentCommentList;
  private Comment currentComment;
  //  ArrayLists might need to be changed

  //  singleton design
  public Facade getInstance() {
    //  TODO
  }

  public User logInUser(String user, String pass) {
    //  TODO
  }

  public void logOutUser() {
    //  TODO
  }

  public boolean changeCurrentProject(Project newProject) {
    //  TODO
  }

  public boolean changeCurrentTask(Task newTask) {
    //  TODO
  }

  public boolean changeCurrentToDo(ToDo newToDo) {
    //  TODO
  }

  public boolean changeCurrentComment(Comment newComment) {
    //  TODO
  }

  public boolean addProject(Project newProject) {
    //  TODO
  }

  public boolean addTask(Task newTask) {
    //  TODO
  }

  public boolean addToDo(ToDo newToDo) {
    //  TODO
  }

  public boolean addComment(Comment newComment) {
    //  TODO
  }

  public boolean addReply(String reply) {
    //  is this needed, or can this be folded into addComment?
    //  TODO
  }

  public boolean removeProject(Project project) {
    //  TODO
  }

  public boolean removeTask(Task task) {
    //  TODO
  }

  public boolean removeToDo(ToDo toDo) {
    //  TODO
  }

  public boolean removeComment(Comment comment) {
    //  TODO
  }

  //  do we need to add removeReply too?

  public boolean editProject(String name, String description) {
    //  TODO
  }

  public boolean editTask(String name, String description) {
    //  TODO
  }

  public boolean editToDo(String name, User assignedUser) {
    //  or should we have a assgin/unassign user class?
    //  TODO
  }

  public boolean editComment(String newComment) {
    //  TODO
  }

  public boolean addColumn(String columnName) {
    //  TODO
  }

  public boolean removeColumn(int ColumnChoice) {
    //  not String?
    //  TODO
  }

  public boolean assignProjectUser(UUID UserID) {
    //  TODO
  }

  public boolean unassignProjectUser(UUID UserID) {
    //  TODO
  }

  public boolean assignTaskUser(UUID UserID) {
    //  TODO
  }

  public boolean unassignTaskUser(UUID UserID) {
    //  TODO
  }

  public void render() {
    //  should this be void? or something else...
    //  TODO
  }
}
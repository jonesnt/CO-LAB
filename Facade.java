
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

  private Facade facade;
  private User currentUser;
  private ArrayList<Project> currentProjectList;
  private Project currentProject;
  private ArrayList<Task> currentTaskList;
  private Task currentTask;
  private ArrayList<ToDo> currentToDoList;
  private ToDo currentToDo;
  private ArrayList<String> currentCommentList;
  private Comment currentComment;
  //  ArrayLists might need to be changed
  //  After looking in DataReader, I think they need to be the type
  //  It's better to ask for forgiveness than permission

  //  singleton design
  private Facade() {
    //  What needs to be in here?
    //  Waterfall method - This function makes it stream down to make 
    //  everything else null,
    //  Project -> Task -> ToDo & Comment
    currentProjectList = null;
    changeCurrentProject(null);
    //  Initialize the UserManager
    //  This is implemented yet!!
    UserManager.getInstance();
    //  Initialize the DataReader and DataWriter
    //  Like above, this isn't implemented yet
    DataReader.getInstance();
    DataWriter.getInstance();

    //  Get a list of the Users to log in
    DataReader.getUsers();
  }

  public Facade getInstance() {
    if (facade == null) {
      facade = new Facade();
    }
    return facade;
  }

  public boolean logInUser(String user, String pass) {
    //  If it wasn't possible, let them know!
    currentUser = userManager.loginAttempt(user, pass);
    if (currentUser == null) {
      //  reset all variables to null, no user = no access
      currentProjectList = null;
      changeCurrentProject(null);
      return false;
    }
    //  invisible else function, they have access, get them their projects
    //  this function is actively being implemented by rene
    currentProjectList = DataReader.getProjects(currentUser);
    return true;
  }

  public void logOutUser() {
    //  clear everything 
    currentUser = null;
    currentProjectList = null;
    changeCurrentProject(null);
  }

  public boolean changeCurrentProject(Project newProject) {
    // first, check and see if it was requested to become null:
    if (newProject == null) {
      currentTaskList = null;
      changeCurrentTask(null);
    }
    if (currentProjectList.contains(newProject)) {
      //  this is is for security, it can be changed to just:
      //  currentProject = newproject
      currentProject = currentProjectList.get(
        currentProjectList.indexOf(newProject));
      //  generate new list of tasks
      //  this should work? im not entirely sure
      currentTaskList = DataReader.getTasks(currentProject);
      return true;
    }
    //  else
    return false;
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

  public ArrayList<Project> getProjectList() {
    //  return a string function, should be right
    String output = null;
    //  There's an ArrayList forEach option, idk if that's relevant here
    //  Might speed up the code, not sure, anyways:
    for(int i = 0; i < currentProjectList.size(); ++i) {
      //  new line deliminated as of right now
      output += "\n" + currentProjectList.get(i);
    }
  }

  public ArrayList<Task> getTaskList() {
    // TODO
  }

  public ArrayList<ToDo> getToDoList() {
    // TODO
  }

  public ArrayList<Comment> getCommentList() {
    // TODO
  }
}

import java.util.ArrayList;
import java.util.HashMap;
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
  
  private ArrayList<String> currentAvailableTasks;
  private HashMap<String, ArrayList<Task>> currentTaskList;  //  does this need to be a hashmap?
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
    //  There used to be a getUsers() datareader function here but I removed it.
    //  It should be implemented in UserManager
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

//  change functions
  public boolean changeCurrentProject(Project newProject) {
    //  first, write the project to the dataWriter:
    //  this isn't implemented yet, let's hope it's right
    DataWriter.saveProject(currentProject);
    //  now, check and see if it was requested to become null:
    if (newProject == null) {
      currentTaskList = null;
      changeCurrentTask(null);
      return true;
    }
    if (currentProjectList.contains(newProject)) {
      //  this is is for security, it can be changed to just:
      //  currentProject = newproject
      currentProject = currentProjectList.get(
        currentProjectList.indexOf(newProject));
      //  generate new list of tasks
      //  this should work? im not entirely sure
      //  currentTaskList = DataReader.getTasks(currentProject);
      //  ^ commented out because i think it should be like this:
      
      return true;
    }
    //  else
    return false;
  }

  public boolean changeCurrentTask(Task newTask) {
    //  push changes
    DataWriter.saveProject(currentProject);
    //  null check
    if (newTask == null) {
      currentTask = null;
      //  waterfall method
      currentToDoList = null;
      currentCommentList = null;
      changeCurrentToDo(null);
      changeCurrentComment(null);
      return true;
    }
    //  invisible else
    //  does it exist?
    if (current)
    return false;
  }

  public boolean changeCurrentToDo(ToDo newToDo) {
    //  push changes
    DataWriter.saveProject(currentProject);
    //  null check
    if (newToDo == null) {
      currentToDo = null;
      return true;
    }
    //  invisible else
    //  check to see if it exists
    if (currentToDoList.contains(newToDo)) {
      // it does, make it the current one
      currentToDo = newToDo;
      return true;
    }
    //placeholder
    return false;
  }

  public boolean changeCurrentComment(Comment newComment) {
    //  push changes
    DataWriter.saveProject(currentProject);
    //  check if it's null
    if (newComment == null) {
      currentComment = null;
      return true;
    }
    //  How would i focus a reply - or should i even do that? or just heads
    //  TODO
    //  placeholder
    return false;
  }

//  add functions
  public boolean addProject(Project newProject) {
    //  I'm adding a saveProject feature here, since it needs to be added
    currentProjectList.add(newProject);
    DataWriter.saveProject(newProject);
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
//  remove functions
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
//  edit functions
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
//  asssign functions
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
// render
  public void render() {
    //  should this be void? or something else...
    //  TODO
  }
//  getters
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
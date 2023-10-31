
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
    //  exit system
    exit();
  }

//  change functions
  public boolean changeCurrentProject(int projNum) {
    //  WARNING: THIS IS ONE INDEXED!
    //  If a -1 is put in, it will CLEAR and make the currentProject NULL!!
    //  CHECK THIS IN THE UI!
    //  -1 = close it down
    if (projNum == -1) {
      currentProject = null;
      //  clear tasklist and currenttask
      currentTaskList = null;
      currentAvailableTasks = null;
      changeCurrentTask(-1);
      return true;
    }
    // check and make sure that it isn't less than 0, and error if it is
    // also check and it isn't larger than the arraylist
    if (projNum < 0 || projNum > (currentProjectList.size() + 1))
      return false;
    // set currentProject to the index - 1
    currentProject = currentProjectList.get(projNum - 1);
    //  update the tasklist

    //placeholder
    return false;
  }

  public boolean changeCurrentTask(int taskNum) {
    //  WARNING: THIS IS ONE INDEXED!
    //  If a -1 is put in, it will CLEAR and make the currentProject NULL!!
    //  CHECK THIS IN THE UI!
    //  -1 = make it null
    if (taskNum == -1) {
      currentTask = null;
      // clear levels below
      currentToDoList = null;
      currentCommentList = null;
      changeCurrentToDo(-1);
      changeCurrentComment(-1);
      return true;
    }
    //  TODO
    //placeholder
    return false;
  }

  public boolean changeCurrentToDo(int toDoNum) {
    //  WARNING: THIS IS ONE INDEXED!
    //  If a -1 is put in, it will CLEAR and make the currentProject NULL!!
    //  CHECK THIS IN THE UI!
    //  -1 = make it null
    if (toDoNum == -1) {
      currentToDo = null;
      return true;
    }
    //  TODO
    //placeholder
    return false;
  }

  public boolean changeCurrentComment(int commentNum) {
    //  WARNING: THIS IS ONE INDEXED!
    //  If a -1 is put in, it will CLEAR and make the currentProject NULL!!
    //  CHECK THIS IN THE UI!
    //  -1 = make it null
    if (commentNum == -1) {
      currentComment = null;
      return true;
    }
    //  TODO
    //  placeholder
    return false;
  }

//  add functions
  public boolean addProject(Project newProject) {
    //  placeholder
    return false;
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
// exit function
  private void exit() {
    //  Write each project to file
    for (Project project : currentProjectList) {
      DataWriter.saveProject(project);
    }
  }
}
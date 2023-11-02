
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

  private DataWriter dW;
  private DataReader dR;
  private UserManager uM;

  private static Facade facade;
  private User currentUser;
  private UUID currentUserID;

  private ArrayList<Project> masterProjectList;
  private ArrayList<Project> currentProjectList;
  private Project currentProject;
  
  private ArrayList<Task> currentTaskList;
  private Task currentTask;
  
  private ArrayList<ToDo> currentToDoList;
  private ToDo currentToDo;
  
  private ArrayList<Comment> currentCommentList;
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
    changeCurrentProject(0);
    //  Initialize the UserManager
    //  This is implemented yet!!
    uM = UserManager.getInstance();
    //  Initialize the DataReader and DataWriter
    //  Like above, this isn't implemented yet
    dR = DataReader.getInstance();
    dW = DataWriter.getInstance();
    //  There used to be a getUsers() datareader function here but I removed it.
    //  It should be implemented in UserManager
    currentProjectList = new ArrayList<Project>();
    currentTaskList = new ArrayList<Task>();
    currentToDoList = new ArrayList<ToDo>();
    currentCommentList = new ArrayList<Comment>();
    masterProjectList = dR.getProjects();
  }

  public static Facade getInstance() {
    if (facade == null) {
      facade = new Facade();
    }
    return facade;
  }

  public boolean logInUser(String user, String pass) {
    //  If it wasn't possible, let them know!
    currentUser = uM.loginAttempt(user, pass);
    if (currentUser == null) {
      //  reset all variables to null, no user = no access
      currentProjectList = null;
      changeCurrentProject(0);
      return false;
    }
    currentUserID = currentUser.getUserID();
    //  invisible else function, they have access, get them their projects
    //  this function is actively being implemented by rene
    currentProjectList = dR.getProjectsByUser(currentUser);
    return true;
  }

  public void logOutUser() {
    //  SAVE
    save();
    //  clear everything 
    currentUser = null;
    currentProjectList = null;
    changeCurrentProject(0);
    
    
  }

//  change functions
  public boolean changeCurrentProject(int projNum) {
    //  WARNING: THIS IS ONE INDEXED!
    //  If a 0 is put in, it will CLEAR and make the currentProject NULL!!
    //  CHECK THIS IN THE UI!
    //  check the number
    if (projNum < 0 || currentProjectList == null || projNum > (currentProjectList.size() + 1))
      return false;
    // clear the items below (waterfall method)
    currentTaskList = null;
    changeCurrentTask(0);
    //  see if it's supposed to clear
    if (projNum == 0) {
      currentProject = null;
    } else {
      // set the current project to the index - 1
      currentProject  = currentProjectList.get(projNum - 1);
      currentTaskList = currentProject.getTasks();
    }
    return true;
  }

  public boolean changeCurrentTask(int taskNum) {
    //  WARNING: THIS IS ONE INDEXED!
    //  If a 0 is put in, it will CLEAR and make the currentTask NULL!!
    //  CHECK THIS IN THE UI!
    //  check the number
    if (currentTaskList == null || taskNum < 0 || taskNum > (currentTaskList.size() + 1))
      return false;
    // clear items below (waterfall)
    currentToDoList = new ArrayList<ToDo>();
    currentCommentList = new ArrayList<Comment>();
    changeCurrentToDo(0);
    changeCurrentComment(0);
    //  see if it's supposed to clear
    if (taskNum == 0) {
      currentTask = null;
    } else {
      currentTask = currentTaskList.get(taskNum - 1);
      currentToDoList = currentTask.getTodo();
      currentCommentList = currentTask.getComments();
    }
    return true;
  }

  public boolean changeCurrentToDo(int toDoNum) {
    //  WARNING: THIS IS ONE INDEXED!
    //  If a 0 is put in, it will CLEAR and make the currentToDo NULL!!
    //  CHECK THIS IN THE UI!
    //  check the number
    if (toDoNum < 0 || toDoNum > (currentToDoList.size() + 1))
      return false;
    //  see if it's supposed to clear
    if (toDoNum == 0) {
      currentToDo = null;
    } else {
      currentToDo = currentToDoList.get(toDoNum - 1);
    }
    return true;
  }
  
  public boolean changeCurrentComment(int commentNum) {
    //  this one is different because it can see all comments past it, and
    //  can go back an edge. it can't see ones on the same layer
    //  WARNING: THIS IS ONE INDEXED!
    //  If a 0 is put in, it will GO BACK a LAYER if possible
    //  CHECK THIS IN THE UI!
    //  check the number
    if (commentNum < 0 || commentNum > (currentCommentList.size() + 1))
      return false;
    // see if it's supposed to go back
    if (commentNum == 0) {
      //  Right now, getBackEdge() returns a UUID? why
      if (currentComment == null || currentComment.getBackEdge() == null)
        return false;
      currentComment = currentComment.getBackEdge();
    } else {
      currentComment = currentCommentList.get(commentNum - 1);
    }
    currentCommentList = currentComment.getReplies();
    return true;
  }

//  add functions
  public boolean addProject(Project newProject) {
    //  first, check and see that it's not null && if that project already 
      //  exists
    if (newProject == null)
      return false;
    if(currentProjectList != null) {
      for (Project proj : currentProjectList) {
        //  getname() doesn't exist? what
        if (proj.getName().equals(newProject.getName()))
          return false;
      }
    }
    //  add it to the list
    currentProjectList.add(newProject);
    masterProjectList.add(newProject);
    return true;
  }

  public boolean addTask(Task newTask) {
    // make sure everything exists
    if (newTask == null || currentProject == null || currentProject.getColumnList().isEmpty())
      return false;
    //  WILL NOT WORK: im requesting the ColumnChoice to be a String
    currentTaskList.add(newTask);
    return currentProject.addTask(newTask, newTask.getColumnTag());
  }

  public boolean addToDo(ToDo newToDo) {
    //  make sure everything exists
    if (newToDo == null || currentTask == null)
      return false;
    //  this should be pretty easy, let the task handle it
    return currentTask.addToDo(newToDo, currentUser);
  }

  public boolean addComment(Comment newComment) {
    //  This is for a head comment, NOT a reply!
    //  existience check
    if (currentComment != null)
      addReply(newComment.getCommentData());
    if (newComment == null || currentTask == null)
      return false;
    boolean worked = currentTask.addComment(newComment);
    // if (worked) 
      // currentCommentList.add(newComment);
    return worked;
  }

  public boolean addReply(String reply) {
    //  begin me questioning if i exist
    if (reply == null || currentComment == null)
      return false;    
    return currentComment.addReply(reply, currentUser.getUserID());  // or UserID?
  }

  public boolean addColumn(String columnName) {
    //  check and make sure it's null
    if (columnName == null)
      return false;
    //  dude death grips is playing i can't focus
    return currentProject.addColumn(columnName);
  }

  //  remove functions

  public boolean removeProject(int projNum) {
    //  one indexed!!
    //  check and make sure it's a valid number, and isn't in a project
    if ( projNum < 1 || projNum > (currentProjectList.size() + 1) ||
      currentProject != null)
      return false;
    //  go ahead and remove it
    currentProjectList.remove(projNum - 1);
    return true;
  }

//  START OF SUB FUNCTION, FIX ABOVE ^ (if you have time)
  public boolean removeTask(int taskNum) {
    //  check if the current task is null, and it's in bounds
    int num = sub(taskNum);
    if (currentTask != null || num > currentTaskList.size() || num < 0) 
      return false;
    // it's not, call to remove it
    boolean result = currentProject.removeTask(currentTaskList.get(num));
    //  make sure it went through
    if (!result)
      return false;
    currentTaskList.remove(num);
    return result;
  }

  public boolean removeComment(int commentNum) {
    //  remove ONLY it's replies, in order to remove top-level comments,
    //  it needs to have currentComment = null (i think)
    int num = sub(commentNum);
    //  check that it won't fail
    if (num < 0 || num > currentCommentList.size())
      return false;
    //  let's remove it
    //  if it's the head comment, remove that one only
    //  neither of these are implemented yet !!
    boolean result;
    if (currentComment.getBackEdge() == null) {
      result = currentTask.removeTopLevelComment(num, currentUserID);
    } else { // this means it's a comment
      result = currentComment.removeReply(num, currentUserID);
    }
    if(!result)
      return false;
    currentCommentList.remove(num);
    return false;
  }

  public boolean removeColumn(int columnChoice) {
    //  make sure that it's valid
    int num = sub(columnChoice);
    if (num < 0)
      return false;
    return currentProject.removeColumn(num);
  }
  //  do we need to add removeReply too? NO

//  edit functions
  public boolean editProject(String name, String description) {
    // check and make sure everything isn't null
    if (description == null)
      return false;
    //  make sure there aren't any with the same name
    for (Project proj : currentProjectList) {
      if (proj.getName().toLowerCase().equals(name.toLowerCase()))
        return false;
    }
    //  send it, i think it might need to be expanded
    currentProject.editProject(name, description);
    return true;
  }

  public boolean editTask(String name, String description) {
    //  check and make sure everything isn't null
    if (description == null)
      return false;
    //  make sure there aren't any tasks with the same name
    for (Task task: currentTaskList) {
      if (task.getName().toLowerCase().equals(name.toLowerCase()))
        return false;
    }
    //  send it
    return currentTask.editTask(name, description, currentUser);
  }

  public boolean editToDo(String name, User assignedUser, boolean completion) {
    //  this is an all around for edit the ToDo
    //  make sure the name is good
    if (name != null && currentToDo != null) {
      for (ToDo cToDo : currentToDoList) {
        if (cToDo.getName().toLowerCase().equals(name.toLowerCase()))
          return false;
      }
      currentToDo.editToDo(name);
    }
    //  see if we need to change the user
    //  this is a mess
    if ( assignedUser != null) {
      boolean test = false;
      for (User u : uM.getUserList()) {
        if (u.getUserID().equals(assignedUser.getUserID())) {
          test = true;
          currentToDo.assignUser(assignedUser.getUserID());
          continue;
        }
        if (!test) {
          return test;
        }
      }
    }
    //  finally, change the completion if needed
    if (currentToDo.getCompletion() != completion) {
      currentToDo.changeCompletion();
    } else {
      return false;
    }
    //  everything worked!
    return true;
  }

  public boolean editComment(String newComment) {
    return currentComment.editComment(newComment);
  }

//  asssign functions
  public boolean assignProjectUser(UUID UserID) {
    //  make sure that the user exists
    if (UserID == null)
      return false;
    //  check that the user exists
    for (User u : uM.getUserList()) {
      if (u.getUserID().equals(UserID)) {
        //  real, assign user
        currentProject.assignUser(UserID);
        return true;
      }
    }
    return false;
  }

  public boolean unassignProjectUser(UUID UserID) {
    //  make sure that the user exists
    if (UserID == null)
      return false;
    //  check that the user is in the project
    if (currentProject.getAssignedUsers().contains(UserID)) {
      currentProject.unassignUser(UserID);
      return true;
    }
    return false;
  }

  public boolean assignTaskUser(UUID UserID) {
    //  make sure that the user exists
    if (UserID == null)
      return false;
    //  check that the user exists
    for (User u : uM.getUserList()) {
      if (u.getUserID().equals(UserID)) {
        //  real, assign user
        currentTask.assignUser(UserID, currentUser);
        return true;
      }
    }
    return false;
  }

  public boolean unassignTaskUser(UUID UserID) {
    //  make sure that the user exists
    if (UserID == null)
      return false;
    //  check that the user is in the task
    if (currentTask.getAssignedUsers().contains(UserID)) {
      currentTask.unassignUser(UserID, currentUser);
      return true;
    }
    return false;
  }
// render
  public void render() {
    //  should this be void? or something else...
    //  TODO
  }
//  getters
  public ArrayList<Project> getMasterList() {
    return masterProjectList;
  }
  public User getCurrentUser() {
    return currentUser;
  }
  public Project getCurrentProject() {
    return currentProject;
  }

  public Task getCurrentTask() {
    return currentTask;
  }

  public ToDo getCurrentToDo() {
    return currentToDo;
  }

  public Comment getCurrentComment() {
    return currentComment;
  }

  public ArrayList<Project> getProjectList() {
    return currentProjectList;
  }

  public ArrayList<Task> getTaskList() {
    return currentTaskList;
  }

  public ArrayList<ToDo> getToDoList() {
    return currentToDoList;
  }

  public ArrayList<Comment> getCommentList() {
    return currentCommentList;
  }

  public ArrayList<User> getuserList() {
    return uM.getUserList();
  }
// exit function
  private void save() {
    //  overwrite masterlist
    for (Project project : currentProjectList) {
      for (int i = 0; i < masterProjectList.size(); ++i) {
        if (masterProjectList.get(i).getUUID().equals(project.getUUID())) {
          masterProjectList.remove(i);
          masterProjectList.add(i, project);
        }
      }
    }
    dW.saveProjects();
  }
  //  helper function to remove 1
  private int sub(int remove) {
    return remove - 1;
  }
}
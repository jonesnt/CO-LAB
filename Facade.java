import java.util.ArrayList;
import java.util.UUID;

public class Facade {
  public static Facade facade;
  public Manager manager;

  public Facade() {
    manager = Manager.getInstance();
  }

  public static Facade getInstance() {
    if (facade == null) {
      facade = new Facade();
    }
    return facade;
  }

  public boolean logInUser(String user, String pass) {
    return manager.logInUser(user, pass);
  }

  public void logOutUser() {
    manager.logOutUser();
  }

  public boolean changeCurrentProject(int projNum) {
    return manager.changeCurrentProject(projNum);
  }

  public boolean changeCurrentTask(int taskNum) {
    return manager.changeCurrentTask(taskNum);
  }

  public boolean changeCurrentToDo(int toDoNum) {
    return manager.changeCurrentToDo(toDoNum);
  }

  public boolean changeCurrentComment(int commentNum) {
    return manager.changeCurrentComment(commentNum);
  }

  public boolean changeColumn(Task task, int columnChoice) {
    return manager.changeColumn(task, columnChoice);
  }

  public boolean addProject(Project newProject) {
    return manager.addProject(newProject);
  }

  public boolean addTask(Task newTask) {
    return manager.addTask(newTask);
  }

  public boolean addToDo(ToDo newToDo) {
    return manager.addToDo(newToDo);
  }

  public boolean addComment(Comment newComment) {
    return manager.addComment(newComment);
  }

  public boolean addReply(String reply) {
    return manager.addReply(reply);
  }

  public boolean addColumn(String columnName) {
    return manager.addColumn(columnName);
  }

  public boolean removeProject(int projNum) {
    return manager.removeProject(projNum);
  }

  public boolean removeTask(int taskNum) {
    return manager.removeTask(taskNum);
  }

  public boolean removeComment(int commentNum) {
    return manager.removeComment(commentNum);
  }

  public boolean removeColumn(int columnChoice) {
    return manager.removeColumn(columnChoice);
  }

  public boolean editProject(String name, String description) {
    return manager.editProject(name, description);
  }

  public boolean editTask(String name, String description) {
    return manager.editTask(name, description);
  }

  public boolean editToDo(String name, User assignedUser, boolean completion) {
    return manager.editToDo(name, assignedUser, completion);
  }

  public boolean editComment(String newComment) {
    return manager.editComment(newComment);
  }

  public boolean assignProjectUser(UUID UserID) {
    return manager.assignProjectUser(UserID);
  }

  public boolean unassignProjectUser(UUID UserID) {
    return manager.unassignProjectUser(UserID);
  }

  public boolean assignTaskUser(UUID UserID) {
    return manager.assignTaskUser(UserID);
  }

  public boolean unassignTaskUser(UUID UserID) {
    return manager.unassignTaskUser(UserID);
  }

  public void render() {
    // TODO
  }

  public User findUser(String username) {
    return manager.findUser(username);
  }

  public ArrayList<Project> getMasterList() {
    return manager.getMasterList();
  }

  public User getCurrentUser() {
    return manager.getCurrentUser();
  }

  public Project getCurrentProject() {
    return manager.getCurrentProject();
  }

  public Task getCurrentTask() {
    return manager.getCurrentTask();
  }

  public ToDo getCurrentToDo() {
    return manager.getCurrentToDo();
  }

  public Comment getCurrentComment() {
    return manager.getCurrentComment();
  }

  public ArrayList<String> getColumnList() {
    return manager.getColumnList();
  }

  public ArrayList<Project> getProjectList() {
    return manager.getProjectList();
  }

  public ArrayList<Task> getTaskList() {
    return manager.getTaskList();
  }

  public ArrayList<ToDo> getToDoList() {
    return manager.getToDoList();
  }

  public ArrayList<Comment> getCommentList() {
    return manager.getCommentList();
  }

  public ArrayList<User> getUserList() {
    return manager.getuserList();
  }
}

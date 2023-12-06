
/**
 * @author hadleya
 * Designed to test the DataReader
 */


import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DataReaderTest {
  private ArrayList<User> users;
  private ArrayList<Project> projects;
	private ArrayList<Task> tasks;
  private ArrayList<ToDo> todos;

	@BeforeEach
	public void setup() {
    //  DEBUG
    // System.out.println("START");
    Manager.getInstance();
    users = new ArrayList<User>();
    projects = new ArrayList<Project>();
    tasks = new ArrayList<Task>();
    todos = new ArrayList<ToDo>();
    //  these users exist in the JSON file
    users.add(new User("aMadden", "Atticus", "Madden", "1234"));
    users.add(new User("jBlum", "Jeff", "Goldblum", "password"));
    users.add(new User("aFinch", "Atticus", "Finch", "asdf"));
    //  save them just in case
    DataWriter.saveUsers(users);
    //  make projects for this case
    projects.add(new Project("test1", "This is the first test"));
    projects.add(new Project("test2", "Second test"));
    projects.add(new Project("3test", "This is the third test"));
    
    //  add a task, todo, and comment test
    for (Project project : projects) {
      //  DEBUG
      project.addColumn("testing");
      Task toAdd = new Task("test for " + project.getName(), "test task", "testing", users.get(0));
      ToDo todo = new ToDo("ToDo Test");
      //TODO: test columns and test comments
      todo.assignUser(users.get(0).getUserID());
      toAdd.addToDo(todo, users.get(0));
      project.addTask(toAdd, "testing");
      
      tasks.add(toAdd);

    }
    //  test columns
    // Project p = projects.get(0);
    // p.addColumn("second column");
    // Task add = new Task("Second Column", " ", "second column", users.get(0));
    // p.addTask(add, "second column");
    // Task add2 = new Task("column 1 task 2", " ", "testing", users.get(0));
    // Task add3 = new Task("2nd col 2nd task", "", "second column", users.get(0));
    // Task add4 = new Task("2nd col 3rd task", " ", "second column", users.get(0));
    // p.addTask(add2, "testing");
    // p.addTask(add3, "second column");
    // p.addTask(add4, "second column");
    /*
    laid out like this:
    testing | second columns
    test for| second column
    col1 t2 | 2nd col 2nd task
            | 2nd col 3rd task
     */
    DataWriter.saveProjects(projects);
    DataWriter.saveTasks(tasks);
    // DEBUG
    // System.out.println("Save Projects");

	}
	
	@AfterEach
	public void tearDown() {

	}

  @Test
  void testRead() {
    DataReader.getProjects();
  }
	@Test
	void testGetUsers() {

    // Load the users from the JSON file
    ArrayList<User> usersReceived = DataReader.getUsers();
    // assertEquals(users, usersReceived); try again
    for (int i = 0; i < usersReceived.size(); ++i) {
      assertEquals(usersReceived.get(i).getUserID(), usersReceived.get(i).getUserID());
    }
	}

  @Test
  void testGetProjects() {
    ArrayList<Project> projectsReceived = DataReader.getProjects();
    for (int i = 0; i < projectsReceived.size(); ++i) {
      // System.out.println(projectsReceived.get(i).getUUID() + "<- received");
      // System.out.println(projects.get(i).getUUID() + "<- expected");
      assertEquals(projectsReceived.get(i).getUUID(), projects.get(i).getUUID());
    }
  }

  @Test
  void testGetTasks() {
    ArrayList<Task> tasksReceived = DataReader.getTasks();
    for (int i = 0; i < tasks.size(); i++){
      assertEquals(tasksReceived.get(i).getID(), tasks.get(i).getID());
    }
  }

  @Test
  void testColumns() {
    ArrayList<Project> ps = DataReader.getProjects();
    ArrayList<String> trueCols = new ArrayList<>();
    trueCols.add("testing");
    trueCols.add("second column");
    Project p = ps.get(0);
    ArrayList<String> cs = p.getColumnList();
    for (int i = 0; i < cs.size(); ++i) {
      assertEquals(cs.get(i), trueCols.get(i));
    }
  }

  @Test
  void testTaskColumns() {
    /*
    laid out like this:
    testing | second columns
    test for| second column
    col1 t2 | 2nd col 2nd task
            | 2nd col 3rd task
    */
    ArrayList<Project> ps = DataReader.getProjects();
    ArrayList<Task> trueTs = projects.get(0).getTasks();
    ArrayList<Task> rTs = ps.get(0).getTasks();
    for (int i = 0; i < rTs.size(); ++i) {
      assertEquals(trueTs.get(i).getID(), rTs.get(i).getID());
    }
  }

  //  remove GetProjectsByUser, don't need it
  //  remove GetTaskByProject, don't need it
  //  remove getToDosByTask, don't need it
  //  remove getCommentsByTask, don't need it
  //  remove getUserByUUID
  //  remove getProjectByUUID
  //  remove getTaskByUUID
  //  remove getUserUUIDByUsername
  //  remove getProjectUUIDByName
  //  remove getTaskUUIDByName
  //  remove getToDoUUIDByName
}

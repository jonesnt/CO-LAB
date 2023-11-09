
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
      project.addColumn("testing");
      Task toAdd = new Task("test for " + project.getName(), "test task", "testing", users.get(0));
      ToDo todo = new ToDo("ToDo Test");
      //unable to test comments because we are not able to make a new comment
      toAdd.addToDo(todo, users.get(0));
      project.addTask(toAdd, "testing");
      
      tasks.add(toAdd);

    }
    DataWriter.saveProjects();

	}
	
	@AfterEach
	public void tearDown() {

	}

	@Test
	void testGetUsers() {
    ArrayList<User> usersReceived = DataReader.getUsers();
    assertEquals(users, usersReceived);
	}

  @Test
  void testGetProjects() {
    ArrayList<Project> projectsReceived = DataReader.getProjects();
    assertEquals(projects, projectsReceived);
  }

  @Test
  void testGetTasks() {
    ArrayList<Task> tasksReceived = DataReader.getTasks();
    assertEquals(tasks, tasksReceived);
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

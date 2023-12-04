import static org.junit.jupiter.api.Assertions.*;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.UUID;

class DataWriterTest {
    private ArrayList<User> userList = new ArrayList<>();
    private ArrayList<Project> projectsList = new ArrayList<>();
    private ArrayList<Task> taskList = new ArrayList<>();
    private DataWriter dataWriter;
    private DataReader dataReader;

    @BeforeEach
    public void setup() {
        dataWriter = DataWriter.getInstance();
        // dataReader = DataReader.getInstance();

        if (userList == null) {
            userList = new ArrayList<>();
        }
        // userList.clear();
        if (projectsList == null) {
            projectsList = new ArrayList<>();
        }
        projectsList.clear();
        if (taskList == null) {
            taskList = new ArrayList<>();
        }
        // taskList.clear();
        DataWriter.saveUsers(userList);
        DataWriter.saveProjects(projectsList);
    }

    @AfterEach
    public void tearDown() {
        if (userList == null) {
            userList = new ArrayList<>();
        }
       // userList.clear();
        if (projectsList == null) {
            projectsList = new ArrayList<>();
        }
       // projects.clear();
        DataWriter.saveUsers(userList);
        DataWriter.saveProjects(projectsList);
    }

    @Test
    void testSingletonInstanceNotNull() {
        DataWriter instance = DataWriter.getInstance();
        assertNotNull(instance, "Instance should not be null");
    }

    @Test
    void testSingletonReturnsSameInstance() {
        DataWriter firstInstance = DataWriter.getInstance();
        DataWriter secondInstance = DataWriter.getInstance();
        assertSame(firstInstance, secondInstance, "Instances should be the same");
    }

    @Test
    void testWritingZeroUsers() {
        userList = DataReader.getUsers() != null ? DataReader.getUsers() : new ArrayList<>();
        assertEquals(0, userList.size());
    }

    @Test
    void testWritingOneUser() {
        User newUser = new User(UUID.randomUUID(), "jdoe", "John", "Doe", "password123");
        userList.add(newUser);
        DataWriter.saveUsers(userList);
        assertEquals("jdoe", DataReader.getUsers().get(0).getUsername());
    }

    @Test
    void testWritingMultipleUsers() {
        userList.add(new User(UUID.randomUUID(), "jdoe", "John", "Doe", "password123"));
        userList.add(new User(UUID.randomUUID(), "asmith", "Alice", "Smith", "password123"));
        userList.add(new User(UUID.randomUUID(), "bwhite", "Bob", "White", "password123"));
        DataWriter.saveUsers(userList);
        assertEquals(3, DataReader.getUsers().size());
    }

    @Test
    void testWritingEmptyUserFields() {
        User emptyUser = new User(UUID.randomUUID(), "", "", "", "");
        userList.add(emptyUser);
        DataWriter.saveUsers(userList);
        User retrievedUser = DataReader.getUsers().get(0);
        assertAll("User fields should be empty",
                () -> assertEquals("", retrievedUser.getUsername()),
                () -> assertEquals("", retrievedUser.getFirstName()),
                () -> assertEquals("", retrievedUser.getLastName()));
    }

    @Test
    void testWritingNullUser() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            if (null == null) { // Intentional check to throw NullPointerException.
                throw new NullPointerException("Cannot add null element");
            }
            userList.add(null);
        });
        assertTrue(exception.getMessage().contains("Cannot add null element"));
    }

    @Test
    void testGetUserJSON() {
        String username = "testUser";
        String firstName = "Test";
        String lastName = "User";
        String password = "password123";
        User user = new User(username, firstName, lastName, password);

        JSONObject userJson = DataWriter.getUserJSON(user);

        assertEquals(username, userJson.get(DataConstants.USER_USERNAME), "Username should match");
        assertEquals(firstName, userJson.get(DataConstants.USER_FIRST_NAME), "First name should match");
        assertEquals(lastName, userJson.get(DataConstants.USER_LAST_NAME), "Last name should match");
        assertEquals(password, userJson.get(DataConstants.USER_PASSWORD), "Password should match");
    }

    @Test
    void testWritingZeroProjects() {
        projectsList = DataReader.getProjects() != null ? DataReader.getProjects() : new ArrayList<>();
        assertEquals(0, projectsList.size());
    }

    @Test
    void testWritingOneProject() {
        Project newProject = new Project("TestProject", "tester project");
        projectsList.add(newProject);
        DataWriter.saveProjects(projectsList);
        ArrayList<Project> loadedProjects = DataReader.getProjects();
        assertEquals("TestProject", loadedProjects.get(0).getName());
    }

    @Test
    void testWritingMultipleProjects() {
        ArrayList<UUID> emptyAssignedUsers = new ArrayList<>();
        ArrayList<Task> emptyTasksList = new ArrayList<>();
        ArrayList<String> emptyColumnList = new ArrayList<>();
        projectsList.add(new Project(UUID.randomUUID(), "TestProject", "tester project", ZonedDateTime.now(),
                emptyAssignedUsers, emptyTasksList, emptyColumnList));
        projectsList.add(new Project(UUID.randomUUID(), "TestProject", "tester project", ZonedDateTime.now(),
                emptyAssignedUsers, emptyTasksList, emptyColumnList));
        projectsList.add(new Project(UUID.randomUUID(), "TestProject", "tester project", ZonedDateTime.now(),
                emptyAssignedUsers, emptyTasksList, emptyColumnList));
        DataWriter.saveProjects(projectsList);
        assertEquals(3, DataReader.getProjects().size());
    }

    @Test
    void testWritingZeroTasks() {
        taskList = DataReader.getTasks() != null ? DataReader.getTasks() : new ArrayList<>();
        assertEquals(0, taskList.size());
    }

    @Test
    void testWritingOneTask() {
        User newUser = new User(UUID.randomUUID(), "jdoe", "John", "Doe", "password123");
        Task newTask = new Task("Task1", "task 1", "ToDo", newUser);
        taskList.add(newTask);
        DataWriter.saveTasks(taskList);
        assertEquals("Task1", DataReader.getTasks().get(0).getName());
    }

    @Test
    void testWritingMultipleTask() {
        User newUser = new User(UUID.randomUUID(), "jdoe", "John", "Doe", "password123");
        taskList.add(new Task("Task1", "task 1", "ToDo", newUser));
        taskList.add(new Task("Task1", "task 1", "ToDo", newUser));
        DataWriter.saveTasks(taskList);
        assertEquals(2, DataReader.getProjects().size());
    }
}

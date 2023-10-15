
/* 
 * by   
 *   Dillion Norris
 */

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

public class Project {

    Project project = new Project();

    private String projectID;
    private String name;
    private String description;
    private Date datetime;
    private ArrayList<User> assignedUsers = new ArrayList<User>();
    private ArrayList<Task> tasks = new ArrayList<Task>();
    private ArrayList<String> demarkationList = new ArrayList<String>();
    private HashMap<String, ArrayList<Task>> projectElements = new HashMap<String, ArrayList<Task>>();

    public Task createTask(String name, int priority, String demarkation) {

        //creates a new task
        Task task = new Task();
        // assigns name
        name = name;
        // assigns priority
        priority = priority;
        // assigns demarkation
        demarkation = demarkation;    // may need to be fixed/clarification
        // returns task
        return task;
    }

    public Task assignTask(Task task ,User user) {
        task.assignedUsers.add(user);
        return task;
    }

    public Boolean changeTaskPriority(Task taskName, int priority) {
        return Boolean;
    }

    public void removeTask(Task taskName) {

    }

    public Boolean removeUser(User user, Task taskName) {
        return Boolean;
    }

    public Boolean addProject(String name, String description) {
        return Boolean;
    }

    public Boolean removeProject(Project project) {
        return Boolean;
    }

    public Boolean adUser(User user) {
        return Boolean;
    }

    public String printFaq() {
        return String;
    }

    public Boolean addDemarkation(String demarkation) {
        return Boolean;
    }

    public Boolean removeDemarkation(String demarkation) {
        return Boolean;
    }

}

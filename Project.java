
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
        // changes task priority
        taskName.setPriority(priority);
        //cheks to see if the task has the same priority as the one passed
        if (taskName.getPriority == priority)
        return true;
        else return false;
    }

    public void removeTask(Task task) {
        // removes task
        task = null;
    }

    public Boolean removeUser(User user, Task task) {
        // if user exists in the user array then remove it
        for (int i = 0; i < task.assignedUsers.size(); i++) {
            if (task.assignedUsers.get(i) == user) {
                task.assignedUsers.remove(i);
                return true;
            }
        }
        return false;
    }

    public Boolean addProject(String name, String description) {
        // create instance of project
        project = new Project();
        // assign name
        name = name;
        // assign description
        description = description;
        
        
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

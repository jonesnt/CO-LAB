
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

        // creates a new task
        Task task = new Task();
        // assigns name
        name = name;
        // assigns priority
        priority = priority;
        // assigns demarkation
        demarkation = demarkation; // may need to be fixed/clarification
        // returns task
        return task;
    }

    public Task assignTask(Task task, User user) {
        task.assignedUsers.add(user);
        return task;
    }

    public Boolean changeTaskPriority(Task taskName, int priority) {
        // changes task priority
        taskName.setPriority(priority);
        // cheks to see if the task has the same priority as the one passed
        if (taskName.getPriority == priority)
            return true;
        else
            return false;
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
        // if project has the same name as the one passed if not then make a new project
        if (name == project.name)
            return false;
        else {
            // create instance of project
            project = new Project();
            // assign name
            name = name;
            // assign description
            description = description;
            return true;
        }
    }

    public Boolean removeProject(Project project) {
        // remove project
        project = null;
        return true;
    }

    public Boolean addUser(User user) {
        // if user does not exist in the user array then add it
        boolean exists = false;

        // iterates through the user array and if the user is in the user array cange
        // exists to true
        for (int i = 0; i < assignedUsers.size(); i++) {
            if (assignedUsers[i].contains(user)) {
                exists = true;
            }
        }
        // if exists then add it to the user array and return true
        if (!exists) {
            assignedUsers.add(user);
            return true;
        } else
            return false;

    }
    // returns faq to the termninal
    public String printFaq() {
        String faq = " No freqently asked questions yet"; // FILL WITH A FAQ!!!!!
        return faq;
    }

    public Boolean addDemarkation(String demarkation) {
        demarkationList.add(demarkation);
        return true;
    }

    public Boolean removeDemarkation(String demarkation) {
        demarkationList.remove(demarkation);
        return true;
    }

    //getters and setters

    public String getProjectID() {
        return projectID;
    }
    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Date getDatetime() {
        return datetime;
    }
    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
    
}

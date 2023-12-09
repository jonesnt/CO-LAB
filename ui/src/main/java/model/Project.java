package model;
// by Dillion Norris

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.Queue;
import java.util.LinkedList;

public class Project {

    private UUID projectID;
    private String name;
    private String description;
    private ZonedDateTime time;// is this suposed to be the due date or the current time?
    private ArrayList<UUID> assignedUsers;
    private ArrayList<Task> tasks; // isnt this suppoed to be a UUID?
    private ArrayList<String> columnList;
    // private HashMap<String, Queue<Task>> columns;// the array list may need a instance variable??
    private HashMap<String, ArrayList<Task>> columns;

    /*
     * // constructor for project
     * Project(String ProjectName, String ProjectDescription,int yearDue,int
     * monthDue,int dayDue){
     * projectId = UUID.randomUUID();
     * name = ProjectName;
     * description = ProjectDescription;
     * 
     * //time = new ZonedDateTime().now() // for current time
     * 
     * // this will be the due date
     * //zoneId = new ZoneDateTime().getZoneId();
     * //time = new ZonedDateTime().of(yearDue,monthDue, dayDue, 0, 0, 0, 0,);
     * 
     * assignedUsers = new ArrayList<UUID>();
     * tasks = new ArrayList<String>();
     * columnList = new ArrayList<String>();
     * columns = new HashMap<String, ArrayList<Task>();
     * }
     */

    public Project(UUID projectID, String name, String description,
            ZonedDateTime time, ArrayList<UUID> assignedUsers,
            ArrayList<Task> tasks, ArrayList<String> columnList) {
        this.projectID = projectID;
        this.name = name;
        this.description = description;
        this.time = time;
        this.assignedUsers = assignedUsers;
        this.tasks = tasks;
        this.columnList = columnList;
        // this.columns = new HashMap<String, Queue<Task>>();
        this.columns = new HashMap<>();

        // if(columnList != null) {

        //     for (String specificColumn : columnList) {
        //         // Queue<Task> tempQueue = new LinkedList<Task>();
        //         columns.put(specificColumn, tempQueue);
        //     }

        //     for (int i = 0; i < tasks.size(); ++i) {
        //         String specificColumn = tasks.get(i).getColumnTag();
        //         columns.get(specificColumn).add(tasks.get(i));
        //     }
        // }
        if(columnList != null) {
            for (String specificColumn : columnList) {
                ArrayList<Task> list = new ArrayList<>();
                columns.put(specificColumn, list);
            }

            for (int i = 0; i < tasks.size(); ++i) {
                String specificColumn = tasks.get(i).getColumnTag();
                columns.get(specificColumn).add(tasks.get(i));
            }
        }
    }

    public Project(String name, String description) {
        this.projectID = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.time = ZonedDateTime.now();
        // this.columns = new HashMap<String, Queue<Task>>();
        this.columns = new HashMap<>();
        this.assignedUsers = new ArrayList<UUID>();
        this.tasks = new ArrayList<Task>();
        this.columnList = new ArrayList<String>();
    }

    //  overload time babey 
    public Project(String name, String description, User user) {
        this.projectID = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.time = ZonedDateTime.now();
        // this.columns = new HashMap<String, Queue<Task>>();
        this.columns = new HashMap<>();
        this.assignedUsers = new ArrayList<UUID>();
        assignedUsers.add(user.getUserID());
        this.tasks = new ArrayList<Task>();
        this.columnList = new ArrayList<String>();
    }
    // adds column within project for the tasks to be catergorized
    public boolean addColumn(String columnName) {
        if (!columnList.contains(columnName)){
            columnList.add(columnName);
            // Queue<Task> tempList = new LinkedList<Task>();
            ArrayList<Task> tempList = new ArrayList<>();
            columns.put(columnName, tempList);
            return true;
        }
        return false;
    }

    //  adds user
    public boolean addUser(User user) {
        for (UUID u : assignedUsers) {
            if (u.equals(user.getUserID()))
                return false;
        }
        assignedUsers.add(user.getUserID());
        return true;
    }

    // ads task to "columns" HashMap with column as the key and Task as the value
    // does not need to create task (I hope)
    public boolean addTask(Task newTask, String columnChoice) {
        if (columnList.contains(columnChoice)) {
            if (!iterateTasks(tasks, newTask.getName())) {
                return false;
            }
            columns.get(columnChoice).add(newTask);
            return true;
        }
        return false;
    }

    // removes column
    public boolean removeColumn(int columnChoice) {
        if(columnChoice >= 0 && columnChoice < columnList.size()) {
            String deleteKey = columnList.get(columnChoice);
            columnList.remove(columnChoice);
            columns.remove(deleteKey);
            return true;
        }
        return false;
    }

    // removes task from task array list
    public boolean removeTask(Task removeTask) {

        // HashMap<String, Queue<Task>> tempColumns = columns;
        if (tasks.contains(removeTask)) {
            for (String key : columnList) {
                if (columns.get(key).contains(removeTask)) {
                    columns.get(key).remove(removeTask);
                    tasks.remove(removeTask);
                    return true;
                }
            }

        }
        return false;
    }

    // assigns users UUID to Project
    public boolean assignUser(UUID user) {
        // if user exists then false
        // else add em
        if (assignedUsers.contains(user))
            return false;
        else {
            assignedUsers.add(user);
            return true;
        }

    }

    // unassins user form the project
    public boolean unassignUser(UUID user) {
        // if user exists then remove
        // else return false
        if (assignedUsers.contains(user)) {
            assignedUsers.add(user);
            return true;
        } else
            return false;
    }

    // moves task to different column
    public boolean changeColumn(Task changeTask, int columnChoice) {
        //make cure it's within bounds
        if (columnChoice > columnList.size() || columnChoice < 0) {
            return false;
        }
        //if task and column exitst 
        if(tasks.contains(changeTask) && columnList.contains(columnList.get(columnChoice))){
        
        Task tempTask  = changeTask;
        removeTask(changeTask);
        addTask(tempTask, columnList.get(columnChoice));
        return true;
        }
        return false;
    }

    // eddits atributes of the project
    public boolean editProject(String newName, String newDescription) {
        if( newName == null)
            newName = name;
        if (newDescription == null)
            newDescription = description;

        name = newName;
        description = newDescription;
        return true;
    }

    // getters and setters

    public UUID getUUID() {
        return projectID;
    }

    public String getDescription() {
        return description;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public ArrayList<UUID> getAssignedUsers() {
        return assignedUsers;
    }
 /*
    public ArrayList<Task> getTasks() {
        //  for the love of god why are we using queues
        if (columns.isEmpty())
            return null;
        tasks = new ArrayList<Task>();
        HashMap<String, Queue<Task>> tempColumns = ;
        Queue<Task> tempQueue = null;
        ArrayList<String> tempList = new ArrayList<String>();
        for(String specificString : columnList)
            tempList.add(specificString);
        
        while(!tempList.isEmpty()) {
            for(String specificColumn : tempList) {
                if(tempColumns.get(specificColumn).isEmpty()) {
                    tempList.remove(specificColumn);
                    break;
                }
                tasks.add(tempColumns.get(specificColumn).remove());
            }
        }

        return tasks;
    }
*/
    public ArrayList<Task> getTasks() {
        if (columns.isEmpty())
            return null;
        tasks = new ArrayList<>();
        for (String s : columnList) {
            for (Task t : columns.get(s)) {
                t.getAssignedUsers();
                tasks.add(t);
            }
        }
        return tasks;
    }
    public ArrayList<String> getColumnList() {
        return columnList;
    }

    public Task[] getColumn(String columnName) {
        //  check if the column exists
        if (columnName == null || !columns.containsKey(columnName)) {
            return null;
        }
        //  i have to do this workaround becuase for SOME reason the toArray makes
        //  an Object?????? why
        // Queue<Task> tasks = columns.get(columnName);
        ArrayList<Task> tasks = columns.get(columnName);
        Task[] toCopy = tasks.toArray(new Task[tasks.size()]);
        Task[] result = new Task[toCopy.length];
        for (int i = 0; i < result.length; ++i) {
            // result[i] = (Task)toCopy[i];
        }
        return result;
    }

    public boolean iterate(Task attempt) {
        // iterate through the list of tasks to see if a task name matches any of the
        // ones that already exist.
        if (tasks.contains(attempt))
            return true;
        else
            return false;
    }
    public String getName(){
        return name;
    }

    // sees if the project = another project
    public boolean equals(Project projectAttempt) {
        if (projectID == projectAttempt.getUUID())
            return true;
        else
            return false;
    }

    public boolean iterateTasks(ArrayList<Task> tasks, String name) {
        for (Task specTask : tasks) {
            if (specTask.equals(name)) {
                return false;
            }
        }
        return true;
    }

}


// by Dillion Norris

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.Queue;

public class Project {

    private UUID projectID;
    private String name;
    private String description;
    private ZonedDateTime time;// is this suposed to be the due date or the current time?
    private ArrayList<UUID> assignedUsers;
    private ArrayList<Task> tasks;    // isnt this suppoed to be a UUID?
    private ArrayList<String> columnList;
    private HashMap<String, Queue<Task>> columns;// the array list may need a instance variable?? 
    

    /*
    // constructor for project
    Project(String ProjectName, String ProjectDescription,int yearDue,int monthDue,int dayDue){
        projectId = UUID.randomUUID();
        name = ProjectName;
        description = ProjectDescription;
    
        //time = new ZonedDateTime().now() // for current time 

        // this will be the due date
        //zoneId = new ZoneDateTime().getZoneId();
        //time = new ZonedDateTime().of(yearDue,monthDue, dayDue, 0, 0, 0, 0,);

     assignedUsers = new ArrayList<UUID>();
     tasks = new ArrayList<String>();
     columnList = new ArrayList<String>();
     columns = new HashMap<String, ArrayList<Task>();
    }   
    */

    public Project(UUID projectID, String name, String description,
        ZonedDateTime time, ArrayList<UUID> assignedUsers,
        ArrayList<Task> tasks, ArrayList<String> columnList) {
            projectID = projectID;
            name = name;
            description = description;
            time = time;
            assignedUsers = assignedUsers;
            tasks = tasks;
            columnList = columnList;

            for(String specificColumn : columnList) {
                Queue<Task> tempQueue = new Queue<Task>();
                columns.put(description, tempQueue);
            }

            for(int i = 0; i < tasks.size(); ++i) {
                Queue tempQueue = columns.get(tasks.get(i).getColumnTag());
                tempQueue.add(tasks.get(i));
            }
        }

    // adds column within project for the tasks to be catergorized
    public boolean addColumn(String columnName) { // may need to be changed later
        columnList.add(columnName);
        return true;
    }

    // ads task to "columns" HashMap with column as the key and Task as the value
    // does not need to create task (I hope)
    public boolean addTask(Task newTask, int columnChoice){
        return columns.putIfAbsent(columnList[columnList],ArrayList<Task>().add(newTask));// weird error idk
    }

    // removes column 
    public boolean removeColumn(int columnChoice) {
        return columns.remove(columnList[columnChoice]);// refer to Line 22
    }
    //removes task from task array list 
    public boolean removeTask(Task removeTask){
       return tasks.remove(removeTask);
    }
    // assigns users UUID to Project
    public boolean assignUser(UUID user){
        //if user exists then false
        // else add em
        if ( assignedUsers.contains(user))
        return false;
        else{
            assignedUsers.add(user);
            return true;
        }
        
    } 
    //unassins user form the project 
    public boolean unassignUser(UUID user){
        //if user exists then remove 
        // else return false
        if ( assignedUsers.contains(user)){
           assignedUsers.add(user);
            return true; 
        }else return false;
    }

    // moves task to different column
    public boolean changeColumn(Task changeTask, int columnChoice){
        if (columns.containsvalue(Arraylist.get(changeTask))){
     // delete the other one 
        removeTask(changeTask);
     // add task to column 
        addTask(changeTask, columnChoice);
        return true;
      }else return false;
    }
    // eddits atributes of the project
    public boolean editProject(String newName, String newDescription){
        name = newName;
        description = newDescription;
        return true;
    }

    // getters and setters 

    public UUID getUUID(){
        return projectId;
    }

    public String getDescription(){
        return description;
    }

    public ZonedDateTime getTime(){
        return time;
    }

    public ArrayList<UUID> getAssignedUsers(){
        return assignedUsers;
    }

    public ArrayList<Task> getTasks(){
        tasks = new ArrayList<Task>();
        HashMap<String, Queue<Task>> tempColumns = columns;
        Queue<Task> tempQueue = null;
        while (!tempColumns.isEmpty()) {
            for(String specificString : columnList) {
                if(tempColumns.get(specificString) != null) {
                    tasks.add(tempColumns.get(specificString).remove());
                    if(tempColumns.get(specificString).isEmpty())
                        tempColumns.remove(specificString);
                }
            }
        }
        return tasks;
    }
    public ArrayList<String> getColumnList(){
        return columnList;
    }


    public boolean iterate(String attempt){
        //iterate through the list of tasks to see if a task name matches any of the ones that already exist.
        if(tasks.contains(attempt))
        return true;
        else return false;
    }

    // sees if the project = another project
    public boolean equals(Project projectAttempt){
        if (projectId == projectAttempt.getUUID())
        return true;
        else return false;
    }

}




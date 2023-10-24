
/*
 * by 
 *  Dillion Norris
 */

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;

public class Task {

    private String taskID;
    private String taskName;
    private Boolean completionStatus = false;
    private int priority;
    private Date dueDate;
    private ArrayList<User> assignedUsers = null;
    private String color;
    private ArrayList<ToDo> toDoList = null;
    private ArrayList<Comment> comments = null;
    private HashMap<String, ArrayList<ToDo>> taskElements = null;
    private ArrayList<ToDo> demarkationList= null;
    private LinkedList<TaskEvent> taskHistory = null;
    
    



    Task(User user ,String taskID, String taskName, int priority,){
        this.taskID = taskID;
        this.taskName = taskName;
        this.priority = priority;
        this.dueDate = Date.getDate();// figure out a new way of getting the date 
        this.assignedUsers = new ArrayList<User>();
        this.demarkationList= new ArrayList<ToDo>(); // idek why its not working
        this.taskElements = new HashMap<String,ArrayList<ToDo>>();
        this.comments = new ArrayList<Comment>();
        this.taskHistory = new LinkedList<TaskEvent>();

        //adds the user who created the task to the list of assigned users
        this.assignedUsers.add(user);
        appendEvent("Task Created", user, Date);



    }

    // returns proximity to deadline 
    public String proxToDeadline(){
 
        //days hours min until deadline
        

        int min;
        int hour;
        int day;

        return min + ":" + hour + ":" + day;

    }

    // changes color of the task
    public void ChangeColor(String color){
        // pass into customizer class to change color
        // deffinetly will need to be edited at some point

        customizer = new Customizer();
        customizer.changeBG(color);


    }
    //assigns a user to the task
    public Boolean AssignUser(User user){
        if(assignedUsers.contains(user))
            return false;
        else{
            assignedUsers.add(user);
            return true;
        }
        
    }
    //removes a user from the task
    public Boolean RemoveUser(User user){
        if(assignedUsers.contains(user)){
            assignedUsers.remove(user);
            return true;
        }else return false;
        
    }
    // adds demarkation
    public boolean addDemarkation(String demarkation){
        // checks if demarkation is a dupulicate
        if(taskElements.containsKey(demarkation))
        return false;
        else{
            // adds demarkation
            taskElements.put(demarkation,Todo);
            return true;
        }
    }
    // removes demarkation
    public boolean removeDemarkation(String demarkation){
        //sees if demarkation does not exist 
        // if not return false
        // if there are todos in the demarkation then return false

        // if demarkation is empty and exists then delete and return true 
        if(taskElements.containsKey(demarkation)&&taskElements<demarkation,demarkationList.isEmpty()>){
            taskElements.remove(demarkation);
            return true;
        }
        else return false;
    }
    // adds todo
    public boolean addToDo(String toDoName,int priority,String demarkation){
        // add todo to the demarkation list in the hashmap 
        // ignore duplicates
        taskElements.put(demarkation,demarkationList.add(new task()));
        return true;
    }

    // removes todo from task
    public boolean removeToDo(String toDoName, User user){
        if(toDoList.contains(toDoName)){
            toDoList.remove(toDoName);
            appendEvent("Task Removed", user, Date);
            return true;
        }else return false;
    }

    public boolean addComment(User user,String desc){
        //appends a new comment to the array list 
        //takes in author and comment data
            this.comments.add(new Comment(desc,user);
    }

    // logs task event and adds it to the task history
    public void appendEvent(String eventName, User involvedUser, Date dateTime){
        // adds a task event to the task history list
        taskHistory.add(taskEvent(eventName,involvedUser,dateTime));
    }

    // getters an setters

    public String getTaskID() {
        return taskID;
    }
    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }
    public String getTaskName() {
        return taskName;
    }
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    public Boolean getCompletionStatus() {
        return completionStatus;
    }
    public void setCompletionStatus(Boolean completionStatus) {
        this.completionStatus = completionStatus;
    }
    public int getPriority() {
        return priority;
    }
    public void setPriority(int priority) {
        this.priority = priority;
    }
    public string getColor(){
        return color;
    }
    public void setColor(String color){
        this.color = color;
    }






}

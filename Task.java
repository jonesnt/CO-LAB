
/*
 * by 
 *  Dillion Norris
 */

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Task {

    public Task task = null;
    private String taskID;
    private String taskName;
    private Boolean completionStatus;
    private int priority;
    private Date dateTime;
    private Date dueDate;
    private ArrayList<User> assignedUsers = null;
    private Color color;
    private ArrayList<ToDo> toDoList = null;
    private ArrayList<Comment> comments = null;
    private HashMap<String, ArrayList<ToDo>> taskElements = null;
    private ArrayList<ToDo> demarkationList= null;
    private LinkedList<TaskEvent> taskHistory = null;
    
    // returns proximity to deadline 
    public String proxToDeadline(){

        // puts date time into mm/dd/yyyy format
        this.dateTime = calender.get(Calender.MONTH)+//


    }

    // changes color of the task
    public void ChangeColor(Color color){
    }
    //assigns a user to the task
    public Boolean AssignUser(User user){
        if(assignedUsers.contains(user))
            return false
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
            return true
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
        else return false
    }
    // adds todo
    public boolean addToDo(String toDoName,int priority,String demarkation){
        // add todo to the demarkation list in the hashmap 
        // ignore duplicates
        taskElements.put(demarkation,demarkationList.add(new task()));
        return true;
    }

    // logs task event and adds it to the task history
    public boolean appendEvent(String eventName, User involvedUser, Date dateTime){
        // adds a task event to the task history list
        taskHistory.add(taskEvent(eventName,involvedUser,dateTime));
    }
}

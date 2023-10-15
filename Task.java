
/*
 * by 
 *  Dillion Norris
 */

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Task {

    public Task task = new Task();
    private String taskID;
    private String taskName;
    private Boolean completionStatus;
    private int priority;
    private Date dateTime;
    private Date dueDate;
    private ArrayList<User> assignedUsers = new ArrayList<User>();
    private Color color;
    private ArrayList<ToDo> toDoList = new ArrayList<ToDo>();
    private ArrayList<Comment> comments = new ArrayList<Comment>();
    private HashMap<String, ArrayList<ToDo>> taskElements = new HashMap<String, ArrayList<ToDo>>();
    private ArrayList<ToDo> demarkationList= new ArrayList<ToDo>();
    private LinkedList<TaskEvent> taskHistory = new LinkedList<TaskEvent>();  
    
    // returns proximity to deadline 
    public String proxToDeadline(){
    }

    // changes color of the task
    public void ChangeColor(Color color){
    }
    //assigns a user to the task
    public Boolean AssignUser(User user){
    }
    //removes a user from the task
    public Boolean RemoveUser(User user){
    }
    // adds demarkation
    public boolean addDemarkation(String demarkation){
    }
    // removes demarkation
    public boolean removeDemarkation(String demarkation){
    }
    // adds todo
    public boolean addToDo(String toDoName,int priority,String demarkation){
    }
    // logs task event and adds it to the task history
    public boolean appendEvent(String eventName, User involvedUser, Date dateTime){
    }
}

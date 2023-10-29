import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
// TODO Make sure to add JSON Simple

// TODO Might change/redo methods similar to how 
// shown in dropbox
public class DataWriter {
    
    private DataWriter()  {
    }

    private static DataWriter getInstance()  {
        // TODO Implement singleton logic
        return new DataWriter();
    }

    public String getNextElementID(DataConstants dbName)  {
        // TODO
        return null;
    }

    public String getFirstElementID(DataConstants dbName)  {
        // TODO
        return null;
    }
<<<<<<< Updated upstream
}
=======

    /**
     * Converts a Project object to a JSONObject representation.
     *
     * @param project the Project object to convert
     * @return a JSONObject representing the project
     */
    public static JSONObject getProjectJSON(Project project) {
        JSONObject projectDetails = new JSONObject();
        projectDetails.put(PROJECT_ID, project.getId().toString());
        projectDetails.put(PROJECT_NAME, project.getProjectName());
        projectDetails.put(PROJECT_DESCRIPTION, project.getDescription());
        projectDetails.put(PROJECT_DATE_TIME, project.getDateTime());

        JSONArray assignedUsers = new JSONArray(project.getAssignedUsers());
        projectDetails.put(PROJECT_ASSIGNED_USERS, assignedUsers);

        JSONArray tasks = new JSONArray(project.getTasks());
        projectDetails.put(PROJECT_TASKS, tasks);

        JSONArray demarkationList = new JSONArray(project.getDemarkationList());
        projectDetails.put(PROJECT_DEMARKATION_LIST, demarkationList);

        JSONObject projectElements = new JSONObject();
        projectElements.put(PROJECT_ELEMENTS_TODO, project.getProjectElements().get(PROJECT_ELEMENTS_TODO));
        projectElements.put(PROJECT_ELEMENTS_IN_PROGRESS,
                project.getProjectElements().get(PROJECT_ELEMENTS_IN_PROGRESS));
        projectElements.put(PROJECT_ELEMENTS_COMPLETED, project.getProjectElements().get(PROJECT_ELEMENTS_COMPLETED));

        projectDetails.put(PROJECT_ELEMENTS, projectElements);

        return projectDetails;
    }

    /**
     * Converts a Task object to a JSONObject representation.
     *
     * @param task the Task object to convert
     * @return a JSONObject representing the task
     */
    public static JSONObject getTaskJSON(Task task) {
        JSONObject taskDetails = new JSONObject();
        taskDetails.put(TASK_ID, task.getTaskID().toString());
        taskDetails.put(TASK_NAME, task.getTaskName());
        taskDetails.put(TASK_DESCRIPTION, task.getDescription());
        taskDetails.put(TASK_DATE_TIME, task.getDateTime());
        taskDetails.put(TASK_COLOR, task.getColor());

        JSONArray assignedUsers = new JSONArray();
        for (UUID userID : task.getAssignedUsers()) {
            assignedUsers.add(userID.toString());
        }
        taskDetails.put(PROJECT_ASSIGNED_USERS, assignedUsers);

        taskDetails.put(TASK_TODO_LIST, getToDosJSON(task.getToDoList()));
        taskDetails.put(TASK_COMMENTS, getCommentsJSON(task.getComments()));
        taskDetails.put(TASK_DEMARKATION_LIST, new JSONArray(task.getDemarkationList()));
        taskDetails.put(TASK_TASK_ELEMENTS, getTaskElementsJSON(task.getTaskElements()));
        taskDetails.put(TASK_PRIORITY, task.getPriority());
        taskDetails.put(TASK_COMPLETION_STATUS, task.isCompletionStatus());
        taskDetails.put(TASK_TASK_HISTORY, getTaskHistoriesJSON(task.getTaskHistory()));

        return taskDetails;
    }

    /**
     * Converts a list of ToDo objects to a JSONArray representation.
     *
     * @param toDoList the list of ToDo objects to convert
     * @return a JSONArray representing the list of ToDos
     */
    public static JSONArray getToDosJSON(ArrayList<ToDo> toDoList) {
        JSONArray toDoArray = new JSONArray();
        for (ToDo toDo : toDoList) {
            JSONObject obj = new JSONObject();
            obj.put(TODO_ID, toDo.getToDoID().toString());
            obj.put(TODO_TODO, toDo.getToDo());
            obj.put(TODO_COMPLETION, toDo.isCompletion());
            toDoArray.add(obj);
        }
        return toDoArray;
    }

    /**
     * Converts a list of Comment objects to a JSONArray representation.
     *
     * @param comments the list of Comment objects to convert
     * @return a JSONArray representing the list of Comments
     */
    public static JSONArray getCommentsJSON(ArrayList<Comment> comments) {
        JSONArray commentArray = new JSONArray();
        for (Comment comment : comments) {
            JSONObject obj = new JSONObject();
            obj.put(COMMENT_ID, comment.getCommentID().toString());
            obj.put(COMMENT_LIST, new JSONArray(comment.getCommentList())); // Assuming commentList is a list of strings
            obj.put(COMMENT_DATA, comment.getCommentData());
            obj.put(COMMENT_DATE, comment.getCommentDate());
            obj.put(COMMENT_AUTHOR, comment.getAuthor().toString());
            commentArray.add(obj);
        }
        return commentArray;
    }

    /**
     * Converts a map of task elements to a JSONObject representation.
     *
     * @param taskElements the map of task elements to convert
     * @return a JSONObject representing the map of task elements
     */
    public static JSONObject getTaskElementsJSON(HashMap<String, ArrayList<UUID>> taskElements) {
        JSONObject obj = new JSONObject();
        obj.put(TASK_ELEMENTS_TODO, new JSONArray(taskElements.get(TASK_ELEMENTS_TODO)));
        obj.put(TASK_ELEMENTS_IN_PROGRESS, new JSONArray(taskElements.get(TASK_ELEMENTS_IN_PROGRESS)));
        obj.put(TASK_ELEMENTS_COMPLETED, new JSONArray(taskElements.get(TASK_ELEMENTS_COMPLETED)));
        return obj;
    }

    /**
     * Converts a list of TaskHistory objects to a JSONArray representation.
     *
     * @param taskHistories the list of TaskHistory objects to convert
     * @return a JSONArray representing the list of TaskHistories
     */
    public static JSONArray getTaskHistoriesJSON(ArrayList<TaskHistory> taskHistories) {
        JSONArray taskHistArray = new JSONArray();
        for (TaskHistory taskHistory : taskHistories) {
            JSONObject obj = new JSONObject();
            obj.put(TASK_HISTORY_EVENT_TIME, taskHistory.getEventTime());
            obj.put(TASK_HISTORY_EVENT_NAME, taskHistory.getEventName());
            obj.put(TASK_HISTORY_INVOLVED_USER, taskHistory.getInvolvedUser().toString());
            taskHistArray.add(obj);
        }
        return taskHistArray;
    }

    // TODO add way to update/overload specific data

    // TODO update save methods, Facade -> Project -> Task -> ToDo

    // TODO use the datareader to read json file than change what you need in data writer then upadte

}
>>>>>>> Stashed changes

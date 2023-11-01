import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Handles writing data objects to their corresponding JSON files.
 */
public class DataWriter extends DataConstants {
    private static DataWriter dataWriter;
    private DataWriter()  {
    }

    public static DataWriter getInstance() {
        if (dataWriter == null) {
            dataWriter = new DataWriter();
        }
        return dataWriter;
    }

    /**
     * Converts a list of users to a JSONArray and saves it to a file.
     */
    public static void saveUsers() {
        UserManager userManager = UserManager.getInstance();
        ArrayList<User> userList = userManager.getUsers();

        JSONArray jsonUsers = new JSONArray();

        for (User user : userList) {
            jsonUsers.add(getUserJSON(user));
        }

        try (FileWriter file = new FileWriter(USER_FILE_NAME)) {
            file.write(jsonUsers.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Converts a list of projects to a JSONArray and saves it to a file.
     */
    public static void saveProjects() {
        ProjectManager projectManager = ProjectManager.getInstance();
        ArrayList<Project> projects = projectManager.getProjects();

        JSONArray jsonProjects = new JSONArray();

        for (Project project : projects) {
            jsonProjects.add(getProjectJSON(project));
        }

        try (FileWriter file = new FileWriter(PROJECT_FILE_NAME)) {
            file.write(jsonProjects.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Converts a list of tasks to a JSONArray and saves it to a file.
     */
    public static void saveTasks() {
        Task tasks = new Task(User user ,String taskID, String taskName, int priority);
        ArrayList<Task> taskList = tasks.getTasks();
        JSONArray jsonTasks = new JSONArray();

        for(Task task : taskList) {
            jsonTasks.add(getTaskJSON(task));
        }

        try (FileWriter file = new FileWriter(TASK_FILE_NAME)) {
            file.write(jsonTasks.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Converts a User object to a JSONObject representation.
     *
     * @param user the User object to convert
     * @return a JSONObject representing the user
     */
    public static JSONObject getUserJSON(User user) {
        JSONObject userDetails = new JSONObject();
        userDetails.put(USER_ID, user.getUserID().toString());
        userDetails.put(USER_USERNAME, user.getUsername());
        userDetails.put(USER_FIRST_NAME, user.getFirstName());
        userDetails.put(USER_LAST_NAME, user.getLastName());
        userDetails.put(USER_PASSWORD, user.getPassword());

        return userDetails;
    }

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

}
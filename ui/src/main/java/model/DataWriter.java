package model;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingDeque;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Handles writing data objects to their corresponding JSON files.
 */
public class DataWriter extends DataConstants {
    private static DataWriter dataWriter;

    private DataWriter() {
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
    public static void saveUsers(ArrayList<User> userList) {

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
    public static void saveProjects(ArrayList<Project> projectsList) {

        JSONArray jsonProjects = new JSONArray();
        ArrayList<Task> allTasks = new ArrayList<>();
        for (Project project : projectsList) {
            jsonProjects.add(getProjectJSON(project));
            for (Task t : project.getTasks()) {
                System.out.println(t.getName());
                allTasks.add(t);
            }
        }
        saveTasks(allTasks);
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
    public static void saveTasks(ArrayList<Task> taskList) {
        JSONArray jsonTasks = new JSONArray();

        for (Task task : taskList) {
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
        projectDetails.put(PROJECT_ID, project.getUUID().toString());
        projectDetails.put(PROJECT_NAME, project.getName());
        projectDetails.put(PROJECT_DESCRIPTION, project.getDescription());
        projectDetails.put(PROJECT_DATE_TIME, project.getTime().toString());

        JSONArray columnListJSON = new JSONArray();
        JSONObject columnsJSON = new JSONObject();
        // for (String column : project.getColumnList()) {
        //     System.out.println("Processing column: " + column + " in " + project.getName()); // Debugging statement
        //     // Add column name to columnList JSON Array
        //     columnListJSON.add(column);

        //     // Create a JSON Array for the tasks in the current column
        //     JSONArray tasksJSON = new JSONArray();
        //     Task[] tasksInColumn = project.getColumn(column); // need to add a getColumns to project, maybe
        //     if (tasksInColumn != null) {
        //         for (Task task : tasksInColumn) {
        //             // System.out.println("Adding task to " + column + ": " + task.getID().toString()); // Debugging statement
        //             tasksJSON.add(task.getID().toString());
        //         }
        //     }
        //     // Add the tasks JSON Array to the corresponding column in columns JSON
        //     columnsJSON.put(column, tasksJSON);
        // }
        //  attempt 2
        ArrayList<Task> allTasks = project.getTasks();
        ArrayList<String> cols = project.getColumnList();
        //  add each string
        for (String col : cols) {
            columnListJSON.add(col);
            JSONArray tasksJSON = new JSONArray();
            for (Task task : allTasks) {
                if (task.getColumnTag().equals(col)) {
                    tasksJSON.add(task.getID().toString());
                }
            }
            columnsJSON.put(col, tasksJSON);
        }
        JSONArray userJSONS = new JSONArray();
        ArrayList<UUID> projectUsers = project.getAssignedUsers();
        for (UUID id : projectUsers) {
            userJSONS.add(id.toString());   
        }
        projectDetails.put(PROJECT_ASSIGNED_USERS, userJSONS);

        // System.out.println("Final columnsJSON: " + columnsJSON.toString()); // Debugging statement
        projectDetails.put(PROJECT_COLUMN_LIST, columnListJSON);
        projectDetails.put(PROJECT_COLUMNS, columnsJSON);

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
        taskDetails.put(TASK_ID, task.getID().toString());
        taskDetails.put(TASK_NAME, task.getName());
        taskDetails.put(TASK_DESCRIPTION, task.getDescription());
        // taskDetails.put(TASK_BG_COLOR, task.getbgColor());
        // taskDetails.put(TASK_FG_COLOR, task.getfgColor());

        JSONArray assignedUsers = new JSONArray();
        for (UUID userID : task.getAssignedUsers()) {
            assignedUsers.add(userID.toString());
        }
        taskDetails.put(PROJECT_ASSIGNED_USERS, assignedUsers);

        taskDetails.put(TASK_TODOS, getToDosJSON(task.getTodo()));
        taskDetails.put(TASK_COMMENTS, getCommentsJSON(task.getComments()));
        taskDetails.put(TASK_COMPLETION_STATUS, task.isCompleted());
        taskDetails.put(TASK_HISTORY, getTaskHistoriesJSON(task.getHistory()));
        taskDetails.put(TASK_COLUMN_TAG, task.getColumnTag());

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
            obj.put(TODO_ID, toDo.getID().toString());
            obj.put(TODO_NAME, toDo.getName());
            obj.put(TODO_COMPLETION, toDo.getCompletion());
            obj.put(TODO_ASSIGNED_USER, toDo.getAssignedUser().toString());
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
            obj.put(COMMENT_ID, comment.getID().toString());

            // Convert list of reply IDs to JSONArray
            JSONArray repliesArray = new JSONArray();
            for (Comment reply : comment.getReplies()) {
                JSONObject replyObj = new JSONObject();
                replyObj.put(COMMENT_ID, reply.getID().toString());
                // Assuming replies don't have further nested replies
                // If they do, you might need recursively call getCommentsJSON or similar
                replyObj.put(COMMENT_DATA, reply.getCommentData());
                replyObj.put(COMMENT_TIME, reply.getTime().toString());
                replyObj.put(COMMENT_AUTHOR, reply.getAuthor().toString());
                repliesArray.add(replyObj);
            }

            obj.put(COMMENT_REPLIES, repliesArray);
            obj.put(COMMENT_REPLIES, repliesArray);
            obj.put(COMMENT_DATA, comment.getCommentData());
            obj.put(COMMENT_TIME, comment.getTime().toString());
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
    public static JSONArray getTaskHistoriesJSON(LinkedList<TaskEvent> taskHistories) {
        JSONArray taskHistArray = new JSONArray();
        for (TaskEvent taskHistory : taskHistories) {
            JSONObject obj = new JSONObject();
            obj.put(TASK_EVENT_TIME, taskHistory.getEventTime().toString());
            obj.put(TASK_EVENT_NAME, taskHistory.getEventName());
            obj.put(TASK_EVENT_INVOLVED_USER, taskHistory.getInvolvedUser().getUserID().toString());
            taskHistArray.add(obj);
        }
        return taskHistArray;
    }

}

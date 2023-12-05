import java.awt.Color;
import java.io.FileReader;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.UUID;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataReader extends DataConstants {
    private static DataReader dataReader;

    private DataReader() {
    }

    public static DataReader getInstance() {
        if (dataReader == null) {
            dataReader = new DataReader();
        }
        return dataReader;
    }

    /**
     * Reads users from a JSON file and returns a list of User objects.
     *
     * @return An ArrayList of User objects, or null if an exception occurs.
     */
    public static ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<User>();

        try {
            FileReader reader = new FileReader(USER_FILE_NAME);
            JSONArray usersJSON = (JSONArray) new JSONParser().parse(reader);

            for (Object aUserJSON : usersJSON) {
                JSONObject userJSON = (JSONObject) aUserJSON;
                UUID userID = UUID.fromString((String) userJSON.get(USER_ID));
                String username = (String) userJSON.get(USER_USERNAME);
                String password = (String) userJSON.get(USER_PASSWORD);
                String firstName = (String) userJSON.get(USER_FIRST_NAME);
                String lastName = (String) userJSON.get(USER_LAST_NAME);

                users.add(new User(userID, username, firstName, lastName, password));
            }

            return users;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Reads projects from a JSON file and returns a list of Project objects.
     *
     * @return An ArrayList of Project objects, or null if an exception occurs.
     */
    public static ArrayList<Project> getProjects() {
        ArrayList<Project> projects = new ArrayList<>();

        try {
            FileReader reader = new FileReader(PROJECT_FILE_NAME);
            JSONArray projectsJSON = (JSONArray) new JSONParser().parse(reader);

            for (Object aProjectsJSON : projectsJSON) {
                JSONObject projectJSON = (JSONObject) aProjectsJSON;
                UUID projectID = UUID.fromString((String) projectJSON.get(PROJECT_ID));
                String name = (String) projectJSON.get(PROJECT_NAME);
                String description = (String) projectJSON.get(PROJECT_DESCRIPTION);
                ZonedDateTime time = ZonedDateTime.parse((String) projectJSON.get(PROJECT_DATE_TIME));
                ArrayList<UUID> assignedUsers = convertToUUIDList((JSONArray) projectJSON.get(PROJECT_ASSIGNED_USERS));

                JSONArray taskUUIDsJSON = (JSONArray) projectJSON.get(PROJECT_TASKS);
                ArrayList<Task> tasks = new ArrayList<>();
                if (taskUUIDsJSON != null) {
                    for (Object taskUUIDObj : taskUUIDsJSON) {
                        String taskUUIDStr = (String) taskUUIDObj;
                        UUID taskUUID = UUID.fromString(taskUUIDStr);
                        Task task = getTaskByUUID(taskUUID);
                        if (task != null) {
                            tasks.add(task);
                        }
                    }
                }
                ArrayList<String> columnList = (ArrayList<String>) projectJSON.get(PROJECT_COLUMN_LIST);
                JSONObject projectColumnsJSON = (JSONObject) projectJSON.get(PROJECT_COLUMNS);
                HashMap<String, ArrayList<UUID>> columns = new HashMap<>();
                for (String columnName : columnList) {
                    columns.put(columnName, convertToUUIDList((JSONArray) projectColumnsJSON.get(columnName)));
                }
                Project toAdd = new Project(projectID, name, description, time, assignedUsers, tasks, columnList);
                projects.add(toAdd);
            }

            return projects;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Reads tasks from a JSON file and returns a list of Task objects.
     *
     * @return An ArrayList of Task objects, or null if an exception occurs.
     */
    public static ArrayList<Task> getTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            FileReader reader = new FileReader(TASK_FILE_NAME);
            JSONArray tasksJSON = (JSONArray) new JSONParser().parse(reader);
            for (Object aTasksJSON : tasksJSON) {
                JSONObject taskJSON = (JSONObject) aTasksJSON;
                UUID taskID = UUID.fromString((String) taskJSON.get(TASK_ID));
                String name = (String) taskJSON.get(TASK_NAME);
                String description = (String) taskJSON.get(TASK_DESCRIPTION);
                // Color bgColor = Color.decode((String) taskJSON.get(TASK_BG_COLOR));
                // Color fgColor = Color.decode((String) taskJSON.get(TASK_FG_COLOR));
                String columnTag = (String) taskJSON.get(TASK_COLUMN_TAG);
                ArrayList<UUID> assignedUsers = (ArrayList<UUID>) taskJSON.get(PROJECT_ASSIGNED_USERS);
                boolean isCompleted = (boolean) taskJSON.get(TASK_COMPLETION_STATUS);
                ArrayList<ToDo> todos = getToDos((JSONArray) taskJSON.get(TASK_TODOS));
                ArrayList<Comment> comments = getComments((JSONArray) taskJSON.get(TASK_COMMENTS));
                LinkedList<TaskEvent> history = getHistory((JSONArray) taskJSON.get(TASK_HISTORY));
                tasks.add(new Task(taskID, name, description, history, todos, comments,
                        assignedUsers, isCompleted, columnTag));
            }

            return tasks;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Task getTaskByUUID(UUID taskUUID) {
        ArrayList<Task> allTasks = getTasks();
        if (allTasks != null) {
            for (Task task : allTasks) {
                if (task.getID().equals(taskUUID)) {
                    return task;
                }
            }
        }
        return null;
    }

    /**
     * Converts a JSONArray of ToDo items into an ArrayList of ToDo objects.
     *
     * @param toDoArrayJSON The JSONArray containing ToDo items.
     * @return An ArrayList of ToDo objects.
     */
    private static ArrayList<ToDo> getToDos(JSONArray toDoArrayJSON) {
        ArrayList<ToDo> toDoList = new ArrayList<>();
        for (Object aToDoArrayJSON : toDoArrayJSON) {
            JSONObject toDoJSON = (JSONObject) aToDoArrayJSON;
            UUID toDoID = UUID.fromString((String) toDoJSON.get(TODO_ID));
            UUID assignedUser = UUID.fromString((String) toDoJSON.get(TODO_ASSIGNED_USER));
            String name = (String) toDoJSON.get(TODO_NAME);
            boolean isCompleted = (boolean) toDoJSON.get(TODO_COMPLETION);

            toDoList.add(new ToDo(toDoID, name, assignedUser, isCompleted));
        }
        return toDoList;
    }

    /**
     * Converts a JSONArray of comments into an ArrayList of Comment objects.
     *
     * @param commentsArrayJSON The JSONArray containing comments.
     * @return An ArrayList of Comment objects.
     */
    private static ArrayList<Comment> getComments(JSONArray commentsArrayJSON) {
        ArrayList<Comment> commentsList = new ArrayList<>();
        for (Object aCommentsJSON : commentsArrayJSON) {
            JSONObject commentJSON = (JSONObject) aCommentsJSON;
            UUID commentID = UUID.fromString((String) commentJSON.get(COMMENT_ID));
            ArrayList<Comment> replies = ((ArrayList<Comment>) commentJSON.get(COMMENT_REPLIES));
            String commentData = (String) commentJSON.get(COMMENT_DATA);
            ZonedDateTime time = ZonedDateTime.parse((String) commentJSON.get(COMMENT_TIME));
            UUID commentBackEdge = UUID.fromString((String) commentJSON.get(COMMENT_BACK_EDGE));
            UUID author = UUID.fromString((String) commentJSON.get(COMMENT_AUTHOR));
            commentsList.add(new Comment(commentID, commentData, author, replies, time, commentBackEdge));
        }
        return commentsList;
    }

    /**
     * Converts a JSONArray of task histories into an ArrayList of TaskHistory
     * objects.
     *
     * @param taskHistoriesJSON The JSONArray containing task histories.
     * @return An ArrayList of TaskHistory objects.
     */
    private static LinkedList<TaskEvent> getHistory(JSONArray taskHistoriesJSON) {
        LinkedList<TaskEvent> taskHistoriesList = new LinkedList<>();
        for (Object aTaskHistory : taskHistoriesJSON) {
            JSONObject taskHistoryJSON = (JSONObject) aTaskHistory;
            ZonedDateTime eventTime = ZonedDateTime.parse((String) taskHistoryJSON.get(TASK_EVENT_TIME));
            String eventName = (String) taskHistoryJSON.get(TASK_EVENT_NAME);
            UUID involvedUserID = UUID.fromString((String) taskHistoryJSON.get(TASK_EVENT_INVOLVED_USER));
            User involvedUser = getUserFromUUID(involvedUserID);
            taskHistoriesList.add(new TaskEvent(eventTime, eventName, involvedUser));
        }
        return taskHistoriesList;
    }

    /**
     * Finds a User based on a UUID. If no User matches the UUID, return nuull.
     * 
     * @param id The UUID we have
     * @return the User that has it, or no user at all
     */
    private static User getUserFromUUID(UUID id) {
        ArrayList<User> users = getUsers();
        for (User user : users) {
            if (user.getUserID().equals(id)) {
                return user;
            }
        }
        //  Make sure there is a null pointer check!
        return null;
    }

    /**
     * Converts a JSONArray of strings to an ArrayList of UUIDs.
     *
     * @param array JSONArray of UUIDs in string format.
     * @return An ArrayList of UUIDs.
     */
    private static ArrayList<UUID> convertToUUIDList(JSONArray array) {
        ArrayList<UUID> uuidList = new ArrayList<>();
        if (array != null) {
            for (Object obj : array) {
                uuidList.add(UUID.fromString((String) obj));
            }
        }
        return uuidList;
    }

}
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;;

public class DataReader extends DataConstants {
    private DataReader() {
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

                users.add(new User(userID, username, password, firstName, lastName));
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
                UUID id = UUID.fromString((String) projectJSON.get(PROJECT_ID));
                String projectName = (String) projectJSON.get(PROJECT_NAME);
                String description = (String) projectJSON.get(PROJECT_DESCRIPTION);
                String dateTime = (String) projectJSON.get(PROJECT_DATE_TIME);
                ArrayList<User> assignedUsers = (ArrayList<User>) projectJSON.get(PROJECT_ASSIGNED_USERS);
                ArrayList<Task> tasks = (ArrayList<Task>) projectJSON.get(PROJECT_TASKS);
                ArrayList<String> demarkationList = (ArrayList<String>) projectJSON.get(PROJECT_DEMARKATION_LIST);

                JSONObject projectElementsJSON = (JSONObject) projectJSON.get(PROJECT_ELEMENTS);
                ArrayList<UUID> toDoList = convertToUUIDList(
                        (JSONArray) projectElementsJSON.get(PROJECT_ELEMENTS_TODO));
                ArrayList<UUID> inProgressList = convertToUUIDList(
                        (JSONArray) projectElementsJSON.get(PROJECT_ELEMENTS_IN_PROGRESS));
                ArrayList<UUID> completedList = convertToUUIDList(
                        (JSONArray) projectElementsJSON.get(PROJECT_ELEMENTS_COMPLETED));

                // Constructing the projectElements HashMap
                HashMap<String, ArrayList<UUID>> projectElements = new HashMap<>();
                projectElements.put(PROJECT_ELEMENTS_TODO, toDoList);
                projectElements.put(PROJECT_ELEMENTS_IN_PROGRESS, inProgressList);
                projectElements.put(PROJECT_ELEMENTS_COMPLETED, completedList);

                projects.add(new Project(id, projectName, description, dateTime, assignedUsers, tasks,
                        demarkationList, projectElements));
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
                String taskName = (String) taskJSON.get(TASK_NAME);
                String description = (String) taskJSON.get(TASK_DESCRIPTION);
                String dateTime = (String) taskJSON.get(TASK_DATE_TIME);
                String color = (String) taskJSON.get(TASK_COLOR);
                ArrayList<User> assignedUsers = (ArrayList<User>) taskJSON.get(PROJECT_ASSIGNED_USERS);
                int priority = ((Long) taskJSON.get(TASK_PRIORITY)).intValue();
                boolean completionStatus = (boolean) taskJSON.get(TASK_COMPLETION_STATUS);

                ArrayList<ToDo> toDoList = getToDos((JSONArray) taskJSON.get(TASK_TODO_LIST));
                ArrayList<Comment> comments = getComments((JSONArray) taskJSON.get(TASK_COMMENTS));
                ArrayList<String> demarkationList = (ArrayList<String>) taskJSON.get(TASK_DEMARKATION_LIST);
                ArrayList<TaskHistory> taskHistory = getTaskHistories((JSONArray) taskJSON.get(TASK_TASK_HISTORY));

                JSONObject taskElementsJSON = (JSONObject) taskJSON.get(TASK_TASK_ELEMENTS);
                ArrayList<UUID> toDoElementList = convertToUUIDList(
                        (JSONArray) taskElementsJSON.get(TASK_ELEMENTS_TODO));
                ArrayList<UUID> inProgressList = convertToUUIDList(
                        (JSONArray) taskElementsJSON.get(TASK_ELEMENTS_IN_PROGRESS));
                ArrayList<UUID> completedList = convertToUUIDList(
                        (JSONArray) taskElementsJSON.get(TASK_ELEMENTS_COMPLETED));

                HashMap<String, ArrayList<UUID>> taskElements = new HashMap<>();
                taskElements.put(TASK_ELEMENTS_TODO, toDoElementList);
                taskElements.put(TASK_ELEMENTS_IN_PROGRESS, inProgressList);
                taskElements.put(TASK_ELEMENTS_COMPLETED, completedList);

                tasks.add(new Task(taskID, taskName, description, dateTime, color, assignedUsers, toDoList, comments,
                        demarkationList, taskElements, priority, completionStatus, taskHistory));
            }

            return tasks;

        } catch (Exception e) {
            e.printStackTrace();
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
            String toDo = (String) toDoJSON.get(TODO_TODO);
            boolean completion = (boolean) toDoJSON.get(TODO_COMPLETION);
            toDoList.add(new ToDo(toDoID, toDo, completion));
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
            ArrayList<Comment> commentList = ((ArrayList<Comment>) commentJSON.get(COMMENT_LIST));
            String commentData = (String) commentJSON.get(COMMENT_DATA);
            String commentDate = (String) commentJSON.get(COMMENT_DATE);
            UUID author = UUID.fromString((String) commentJSON.get(COMMENT_AUTHOR));
            commentsList.add(new Comment(commentID, commentList, commentData, commentDate, author));
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
    private static ArrayList<TaskHistory> getTaskHistories(JSONArray taskHistoriesJSON) {
        ArrayList<TaskHistory> taskHistoriesList = new ArrayList<>();
        for (Object aTaskHistory : taskHistoriesJSON) {
            JSONObject taskHistoryJSON = (JSONObject) aTaskHistory;
            String eventTime = (String) taskHistoryJSON.get(TASK_HISTORY_EVENT_TIME);
            String eventName = (String) taskHistoryJSON.get(TASK_HISTORY_EVENT_NAME);
            UUID involvedUser = UUID.fromString((String) taskHistoryJSON.get(TASK_HISTORY_INVOLVED_USER));
            taskHistoriesList.add(new TaskHistory(eventTime, eventName, involvedUser));
        }
        return taskHistoriesList;
    }

    /**
     * Converts a JSONArray of strings to an ArrayList of UUIDs.
     *
     * @param array JSONArray of UUIDs in string format.
     * @return An ArrayList of UUIDs.
     */
    private static ArrayList<UUID> convertToUUIDList(JSONArray array) {
        ArrayList<UUID> uuidList = new ArrayList<>();
        for (Object obj : array) {
            uuidList.add(UUID.fromString((String) obj));
        }
        return uuidList;
    }

    // main method for testing
    public static void main(String[] args) {
        // Test for getting Users
        ArrayList<User> users = getUsers();
        if (users != null) {
            System.out.println("---- Users ----");
            for (User user : users) {
                System.out.println(user);
            }
        } else {
            System.out.println("Failed to retrieve users or user list is empty.");
        }

        // Test for getting Projects
        ArrayList<Project> projects = getProjects();
        if (projects != null) {
            System.out.println("\n---- Projects ----");
            for (Project project : projects) {
                System.out.println(project);
            }
        } else {
            System.out.println("Failed to retrieve projects or project list is empty.");
        }
    }

}


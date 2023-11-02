public abstract class DataConstants {
    // USER JSON Constants
    protected static final String USER_FILE_NAME = "./jsons/User.json";
    protected static final String USER_ID = "userID";
    protected static final String USER_USERNAME = "username";
    protected static final String USER_PASSWORD = "password";
    protected static final String USER_FIRST_NAME = "firstName";
    protected static final String USER_LAST_NAME = "lastName";

    // PROJECT JSON Constants
    protected static final String PROJECT_FILE_NAME = "./jsons/Project.json";
    protected static final String PROJECT_ID = "projectID";
    protected static final String PROJECT_NAME = "name";
    protected static final String PROJECT_DESCRIPTION = "description";
    protected static final String PROJECT_DATE_TIME = "time";
    protected static final String PROJECT_ASSIGNED_USERS = "assignedUsers";
    protected static final String PROJECT_TASKS = "tasks";
    protected static final String PROJECT_COLUMN_LIST = "columnList";
    protected static final String PROJECT_COLUMNS = "columns";

    // TASK JSON Constants
    protected static final String TASK_FILE_NAME = "./jsons/Task.json";
    protected static final String TASK_ID = "taskID";
    protected static final String TASK_NAME = "name";
    protected static final String TASK_DESCRIPTION = "description";
    protected static final String TASK_HISTORY = "history";
    protected static final String TASK_TODOS = "todos";
    protected static final String TASK_COMMENTS = "comments";
    protected static final String TASK_ASSIGNED_USERS = "assignedUsers";
    protected static final String TASK_COMPLETION_STATUS = "isCompleted";
    protected static final String TASK_BG_COLOR = "bgColor";
    protected static final String TASK_FG_COLOR = "fgColor";
    protected static final String TASK_COLUMN_TAG = "columnTag";
    
    // TODOS JSON Constants
    protected static final String TODO_ID = "toDoID";
    protected static final String TODO_NAME = "name";
    protected static final String TODO_ASSIGNED_USER = "assignedUser";
    protected static final String TODO_COMPLETION = "isCompleted";

    // COMMENT JSON Constants
    protected static final String COMMENT_ID = "commentID";
    protected static final String COMMENT_REPLIES = "replies";
    protected static final String COMMENT_DATA = "commentData";
    protected static final String COMMENT_TIME = "time";
    protected static final String COMMENT_AUTHOR = "author";
    protected static final String COMMENT_BACK_EDGE = "commentBackEdge";

    // TASK EVENT JSON Constants
    protected static final String TASK_EVENT_TIME = "eventTime";
    protected static final String TASK_EVENT_NAME = "eventName";
    protected static final String TASK_EVENT_INVOLVED_USER = "involvedUser";
}

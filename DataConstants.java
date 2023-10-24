public abstract class DataConstants {
    // USER JSON Constants
    protected static final String USER_FILE_NAME = "CO-LAB/jsons/User.json";
    protected static final String USER_ID = "userID";
    protected static final String USER_USERNAME = "username";
    protected static final String USER_PASSWORD = "password";
    protected static final String USER_FIRST_NAME = "firstName";
    protected static final String USER_LAST_NAME = "lastName";

    // PROJECT JSON Constants
    protected static final String PROJECT_FILE_NAME = "CO-LAB/jsons/Project.json";
    protected static final String PROJECT_ID = "projectID";
    protected static final String PROJECT_NAME = "projectName";
    protected static final String PROJECT_DESCRIPTION = "description";
    protected static final String PROJECT_DATE_TIME = "dateTime";
    protected static final String PROJECT_ASSIGNED_USERS = "assignedUsers";
    protected static final String PROJECT_TASKS = "tasks";
    protected static final String PROJECT_DEMARKATION_LIST = "demarkationList";
    protected static final String PROJECT_ELEMENTS = "projectElements";

    // PROJECT ELEMENTS Constants
    protected static final String PROJECT_ELEMENTS_TODO = "ToDo";
    protected static final String PROJECT_ELEMENTS_IN_PROGRESS = "In_Progress";
    protected static final String PROJECT_ELEMENTS_COMPLETED = "Completed";

    // TASK JSON Constants
    protected static final String TASK_FILE_NAME = "CO-LAB/jsons/Task.json";
    protected static final String TASK_ID = "taskID";
    protected static final String TASK_NAME = "taskName";
    protected static final String TASK_DESCRIPTION = "description";
    protected static final String TASK_DATE_TIME = "dateTime";
    protected static final String TASK_COLOR = "color";
    protected static final String TASK_ASSIGNED_USERS = "assignedUsers";
    protected static final String TASK_TODO_LIST = "toDoList";
    protected static final String TASK_COMMENTS = "comments";
    protected static final String TASK_DEMARKATION_LIST = "demarkationList";
    protected static final String TASK_TASK_ELEMENTS = "taskElements";
    protected static final String TASK_PRIORITY = "priority";
    protected static final String TASK_COMPLETION_STATUS = "completionStatus";
    protected static final String TASK_TASK_HISTORY = "TaskHistory";
    
    // TODO JSON Constants
    protected static final String TODO_ID = "toDoID";
    protected static final String TODO_TODO = "toDo";
    protected static final String TODO_COMPLETION = "completion";

    // COMMENT JSON Constants
    protected static final String COMMENT_ID = "commentID";
    protected static final String COMMENT_LIST = "commentList";
    protected static final String COMMENT_DATA = "commentData";
    protected static final String COMMENT_DATE = "commentDate";
    protected static final String COMMENT_AUTHOR = "author";

    // TASK ELEMENTS Constants
    protected static final String TASK_ELEMENTS_TODO = "ToDo";
    protected static final String TASK_ELEMENTS_IN_PROGRESS = "In_Progress";
    protected static final String TASK_ELEMENTS_COMPLETED = "Completed";

    // TASK HISTORY Constants
    protected static final String TASK_HISTORY_EVENT_TIME = "eventTime";
    protected static final String TASK_HISTORY_EVENT_NAME = "eventName";
    protected static final String TASK_HISTORY_INVOLVED_USER = "involvedUser";
}

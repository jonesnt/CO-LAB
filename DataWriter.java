import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Handles writing data objects to their corresponding JSON files.
 */
public class DataWriter {

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
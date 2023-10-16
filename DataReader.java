import java.io.FileReader;
import java.util.ArrayList;
import java.util.UUID;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;;
// TODO Make sure to add JSON Simple

// TODO Might change/redo methods similar to how 
// shown in dropbox
public class DataReader {
    private DataReader()  {
    }

    public static DataReader getInstance()  {
        // TODO Implement singleton logic
        return new DataReader();
    }

    private String getLastElementID(DataConstants dbName)  {
        // TODO Logic to get the last element ID from the JSON file
        return null;
    }

    private String getFirstElementID(DataConstants dbName)  {
        // TODO Logic to get the first element ID from the JSON file
        return null;
    }

    public ArrayList<Object> getElements(DataConstants dbName)  {
        // TODO Logic to get all elements from the JSON file
        return new ArrayList<>();
    }

    public Object getSpecificElement(DataConstants dbName, String objectID)  {
        // TODO Logic to get a specific element from the JSON file
        return null;
    }
}


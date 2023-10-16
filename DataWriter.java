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
}
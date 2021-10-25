package persistence;

import model.ExpenseList;
import model.TravelingPartnerList;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Represents a writer that writes JSON representation of userExpenseList
// and userTravelingPartnerList to "./data/myFile/.txt"
public class JsonWriter {
    private static final int TAB = 4; //an indentFactor when writes JSON to file, this
                                      // insert tab between each object been written into file
    private PrintWriter writer;
    private String destination;

    //EFFECTS: constructs a writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    //MODIFIES: this
    //EFFECTS: open writer, throes FileNotFoundException (in method PrintWriter(); FileNotFoundException extends
    //         IOException) if destination file cannot be opened for writing
    public void openWriter() throws FileNotFoundException {
        File destinationFile = new File(destination);
        writer = new PrintWriter(destinationFile);
    }

    //MODIFIES: this
    //EFFECTS: writes the JSON representation of userExpenseList to file
    public void writeUserExpenseList(ExpenseList expenseList) {
        JSONObject json = expenseList.toJson();   //calls ExpenseList Class method toJson()
        String toSave = json.toString(TAB);       //converts json from type JSONObject to type String
        writer.print(toSave);                     //write String toSave to file
    }

    //MODIFIES: this
    //EFFECTS: writes the JSON representation of userTravelingPartnerList to file
    public void writeUserTravelingPartnerList(TravelingPartnerList tpList) {
        JSONObject json = tpList.toJson();   //calls TravelingPartnerList Class method toJson()
        String toSave = json.toString(TAB);
        writer.print(toSave);
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }






}

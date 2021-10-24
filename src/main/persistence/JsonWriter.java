package persistence;

import java.io.PrintWriter;

// Represents a writer that writes JSON representation of userExpenseList and
// userTravelingPartnerList to "./data/myFile.txt"
public class JsonWriter {
    private static final int TAB = 4; //when writes JSON to file, this
                                      // insert tab between each object been written into file
    private PrintWriter writer;
    private String destination;

    //EFFECTS: constructs a writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }


}

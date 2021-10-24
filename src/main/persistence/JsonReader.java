package persistence;

// Represents a reader that reads userExpenseList and userTravelingPartnerList
// from JSON data stored in "./data/myFile.txt"
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }
}

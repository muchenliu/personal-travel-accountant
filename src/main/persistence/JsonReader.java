package persistence;

import model.Expense;
import model.ExpenseList;
import model.TravelingPartner;
import model.TravelingPartnerList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads expense list and traveling partner list
// from JSON data stored in  "./data/myFile/.txt"
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads expense list from file and returns it,
    //          throws IOException if an error occurs reading data from file
    public ExpenseList readExpenseList() throws IOException {
        String expenseListData = readFile(source);               // returns data stored in "./data/myFile.txt" as string
        JSONObject jsonObject = new JSONObject(expenseListData); // from type String to type JSONObject
        return parseExpenseList(jsonObject);
    }


    // EFFECTS: reads tp list from file and returns it,
    //          throws IOException if an error occurs reading data from file
    public TravelingPartnerList readTPList() throws IOException {
        String tpListData = readFile(source);                // returns data stored in "./data/myFile.txt" as string
        JSONObject jsonObject = new JSONObject(tpListData);  //from type String to type JSONObject
        return parseTPList(jsonObject);
    }


    // method readFile() copied from the JsonSerializationDemo project from CPSC210 GitHub
    //https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses expense list from JSON object and returns it
    private ExpenseList parseExpenseList(JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("expense list");
        ExpenseList expenseList = new ExpenseList();

        for (Object json : jsonArray) {
            JSONObject nextExpense = (JSONObject) json;  //we do not create a new JASONObject here
            addExpense(expenseList, nextExpense);
        }

        return expenseList;
    }

    // MODIFIES: expenseList
    // EFFECTS: parses expense from jsonObject and add it to expenseList
    private void addExpense(ExpenseList expenseList, JSONObject jsonObject) {
        Double amount = jsonObject.getDouble("amount");
        String category = jsonObject.getString("category");
        Expense expense = new Expense(amount, category);
        expenseList.addExpense(expense);
    }

    // EFFECTS: parses tp list from JSON object and returns it
    private TravelingPartnerList parseTPList(JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("traveling partners");
        TravelingPartnerList tpList = new TravelingPartnerList();

        for (Object json : jsonArray) {
            JSONObject nextTP = (JSONObject) json;   //we do not create a new JASONObject here
            addTravelingPartner(tpList, nextTP);
        }

        return tpList;
    }

    // MODIFIES: tpList
    // EFFECTS: parses traveling partner from jsonObject and add it to tpList
    private void addTravelingPartner(TravelingPartnerList tpList, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Double amountOwedToMe = jsonObject.getDouble("amount owed to me");
        Double amountIBorrowed = jsonObject.getDouble("amount I borrowed");
        TravelingPartner travelingPartner = new TravelingPartner(name);
        travelingPartner.addAmountOwedToMe(amountOwedToMe);
        travelingPartner.addAmountIBorrowed(amountIBorrowed);
        tpList.addTravelingPartner(travelingPartner);
    }
}

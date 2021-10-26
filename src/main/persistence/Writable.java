package persistence;

import org.json.JSONObject;

//Writable Interface copied from the JsonSerializationDemo project from CPSC210 GitHub
//https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public interface Writable {
    //EFFECTS: returns this as JSON object
    JSONObject toJson();
}

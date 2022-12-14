package model;

import org.json.JSONObject;
import persistence.Writable;

// Creates a TravelingPartner having a name, amount that owe to the user, and amount that the user has borrowed from
// (in dollars)
public class TravelingPartner implements Writable {
    private String name;              // first name and abbreviation of middle name if necessary of the TravelingPartner
    protected double amountOwedToMe;  // amount the TravelingPartner has owed to the user
    protected double amountIBorrowed; // amount the user has borrowed from the TravelingPartner

    //REQUIRES: name has a non-zero length, if distinct TravelingPartner have identical firstname, the lately input one
    //          has to include both firstname and middle name abbreviation (single capital letter) in the following
    //          template "FirstnameM."
    //EFFECTS: creates a TravelingPartner with given name, set both amountOwedToMe and amountIBorrowed to 0
    public TravelingPartner(String name) {
        this.name = name;
        amountOwedToMe = 0;
        amountIBorrowed = 0;
    }

    //getter
    public String getName() {
        return name;
    }

    //getter
    public double getAmountOwedToMe() {
        return amountOwedToMe;
    }

    //getter
    public double getAmountIBorrowed() {
        return amountIBorrowed;
    }

    //MODIFIES: this
    //EFFECTS: add the input amount on current amountOwedToMe
    public void addAmountOwedToMe(double amount) {
        amountOwedToMe = amountOwedToMe + amount;
    }

    //MODIFIES: this
    //EFFECTS: add the input amount on current amountIBorrowed
    public void addAmountIBorrowed(double amount) {
        amountIBorrowed = amountIBorrowed + amount;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("amount owed to me", amountOwedToMe);
        json.put("amount I borrowed", amountIBorrowed);
        return json;
    }
}

package model;

// Creates a TravelingPartner having a name, amount that owe to the user, and amount that the user has borrowed from
// (in dollars)
public class TravelingPartner {
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
}

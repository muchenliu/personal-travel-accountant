package model;

// Creates an Expense log includes amount (in dollars), category, isCash?, and isSplit?
public class Expense {
    private double amount;     // the amount of the Expense
    private final String category;   // the category the Expense belongs to
    protected boolean isCash;  // represents whether the Expense is a cash Expense
    protected boolean isSplit; // represents whether the Expense is a Expense splits between TravelingPartners

    //REQUIRES: if distinct expenses are under the same category (e.g. launch expense, dinner expense, treat expense,
    //          and afternoon tea expense are all under the "Food" category; each hotel/Airbnb expense are under the
    //          "Living" category), their category name must match to each other.
    //EFFECTS: creates an Expense with given amount and category, set the isCash and isSplit boolean as false
    public Expense(double amount, String cname) {
        this.amount = amount;
        category = cname;
        isCash = false;
        isSplit = false;
    }

    //getter
    public double getAmount() {
        return amount;
    }

    //getter
    public String getCategory() {
        return category;
    }

    //MODIFIES: this
    //EFFECTS: set isCash to true to represent this expense is a cash expense
    public void setCash() {
        isCash = true;
    }

    //REQUIRES: the size of TravelingPartnerList must > 0 when the method is called
    //MODIFIES: this
    //EFFECTS: set the split Expense to the correct amount by dividing this amount by the size of TravelingPartnerList
    //         add 1 (to include the user) and change isSplit to true
    public void setSplitAmount() {
        amount = amount / (TravelingPartnerList.count() + 1);
        isSplit = true;
    }

}

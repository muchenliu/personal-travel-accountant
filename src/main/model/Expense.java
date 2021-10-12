package model;

// Creates an Expense log includes amount (in dollars), category, isCash?, and isSplit?
public class Expense {
    private double amount;     // the amount of the Expense
    private String category;   // the category the Expense belongs to
    protected boolean isCash;  // represents whether the Expense is a cash Expense
    protected boolean isSplit; // represents whether the Expense is a Expense splits between TravelingPartners

}

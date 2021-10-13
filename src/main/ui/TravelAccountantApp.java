package ui;

import model.Expense;
import model.ExpenseList;
import model.TravelingPartner;
import model.TravelingPartnerList;

import java.util.Locale;
import java.util.Scanner;

//travel accountant application
public class TravelAccountantApp {
    private double budget;
    private double cash;
    private Scanner input;
    private ExpenseList userExpenses = new ExpenseList();
    private TravelingPartnerList userTravelingPartnerList = new TravelingPartnerList();

    //EFFECTS: runs the travel accountant application
    public TravelAccountantApp() {
        runTravelAccountant();
    }

    //MODIFIES: this
    //EFFECTS: process user inputs of the main page of the app
    private void runTravelAccountant() {
        System.out.println("Welcome to your Personal Traveling Accountant!");
        System.out.println("We keeps eye on your expense and help you manage your trip more easier!");
        System.out.println("Please enter your Budget : ");
        budget = input.nextDouble();
        System.out.println("Please enter your current Cash amount : ");
        cash = input.nextDouble();

        boolean keepGoing = true;
        String command = null;


        System.out.println("See you next time! Have a wonderful trip :)");
    }

    // EFFECTS: displays main menu of options to user
    private void displayMainMenu() {
        System.out.println("\nWhat are you planning to do?");
        System.out.println("\nPlease select from the following categories:");
        System.out.println("\te ->  expense");
        System.out.println("\ttp -> traveling partner");
        System.out.println("\tc ->  cash");
        System.out.println("\tq ->  quit");
    }

    // MODIFIES: this
    // EFFECTS: processes main user command
    private void processMainCommand(String command) {
        if (command.equals("e")) {
            runExpense();
        } else if (command.equals("tp")) {
            runTravelingPartner();
        } else if (command.equals("c")) {
            runCash();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    //MODIFIES: this
    //EFFECTS: process user inputs of the Expense page of the app
    private void runExpense() {
        //stub
    }

    //MODIFIES: this
    //EFFECTS: process user inputs of the TravelingPartner page of the app
    private void runTravelingPartner() {
        //stub
    }

    //MODIFIES: this
    //EFFECTS: process user inputs of the Cash page of the app
    private void runCash() {
        //stub
    }

    //EFFECTS: display expense menu to user
    private void displayExpenseMenu() {
        System.out.println("\nPlease select from the following categories:");
        System.out.println("\ta ->  record an expense");
        System.out.println("\tb ->  remove an expense");
        System.out.println("\tc ->  check total expense");
        System.out.println("\td ->  check total expense in different expense categories");
        System.out.println("\te ->  check how much budget left");
    }

    //MODIFIES: this
    //EFFECTS: process expense user command
    private void processExpenseCommand(String command) {
        if (command.equals("a")) {
            recordExpense();
        } else if (command.equals("b")) {
            removeExpense();
        } else if (command.equals("c")) {
            printTotalExpense();
        } else if (command.equals("d")) {
            printCategoryExpense();
        } else if (command.equals("e")) {
            printBudgetLeft();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    //EFFECTS: display tp menu to user
    private void displayTravelingPartnerMenu() {
        System.out.println("\nPlease select from the following categories:");
        System.out.println("\ta ->  add a traveling partner");
        System.out.println("\tb ->  remove a traveling partner");
        System.out.println("\tc ->  check amount other owed to you during this trip");
        System.out.println("\td ->  check amount you borrowed from other during this trip");
    }

    //MODIFIES: this
    //EFFECTS: process tp user command
    private void processTravelingPartnerCommand(String command) {
        if (command.equals("a")) {
            addTP();
        } else if (command.equals("b")) {
            removeTP();
        } else if (command.equals("c")) {
            checkAmountOwed();
        } else if (command.equals("d")) {
            checkAmountBorrowed();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    //EFFECTS: display cash menu to user
    private void displayCashMenu() {
        System.out.println("\nPlease select from the following categories:");
        System.out.println("\ta ->  record cash income");
        System.out.println("\tb ->  check cash balance");
    }

    //MODIFIES: this
    //EFFECTS: process cash user command
    private void processCashCommand(String command) {
        if (command.equals("a")) {
            cashIn();
        } else if (command.equals("b")) {
            cashLeft();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    //MODIFIES: this
    //EFFECTS: record an Expense input from user
    private void recordExpense() {
        double amount;
        String category;
        Expense newExpense;

        System.out.println("Please enter the amount ant the category of this expense :");
        System.out.println("Amount : ");
        amount = input.nextDouble();
        System.out.println("Category : ");
        category = input.next();

        newExpense = new Expense(amount, category);
        userExpenses.addExpense(newExpense);
        System.out.println("The Expense has ben added");
    }

    //MODIFIES: this
    //EFFECTS: remove correspond Expense if match the amount and category input of user input
    private void removeExpense() {
        double amount;
        String category;
        Expense wasteExpense;

        System.out.println("Please enter the amount ant the category of expense you want to remove:");
        System.out.println("Amount : ");
        amount = input.nextDouble();
        System.out.println("Category : ");
        category = input.next();

        wasteExpense = new Expense(amount, category);
        if (userExpenses.removeExpense(wasteExpense)) {
            System.out.println("The expense has been removed");
        }
        System.out.println("This expense does not exist");
    }

    //EFFECTS: print out total expense amount
    private void printTotalExpense() {
        double totalExp = userExpenses.totalExpense();
        System.out.println("Your total expense is : $" + totalExp);
    }

    //EFFECTS: print out expense in the category that user input
    private void printCategoryExpense() {
        String cname;
        double categoryTotal;

        System.out.println("Please enter the expense category name which you want to check : ");

        cname = input.next();
        categoryTotal = userExpenses.totalExpenseInCategory(cname);

        System.out.println("Your total expense in category " + cname.toUpperCase() + " is " + categoryTotal);
    }

    //EFFECTS: print out the budget left
    private void printBudgetLeft() {
        double budgetLeft = budget - userExpenses.totalExpense();
        System.out.println("You still have $" + budgetLeft + " left");
    }

    //MODIFIES: this
    //EFFECTS: add a TravelingPartner input from user
    private void addTP() {
        String givenName;
        TravelingPartner newTP;

        System.out.println("Please enter the name of your traveling partner : ");
        givenName = input.next();
        newTP = new TravelingPartner(givenName);
        userTravelingPartnerList.addTravelingPartner(newTP);

        System.out.println("Your traveling partner, " + givenName + " has been added");
    }

    //MODIFIES: this
    //EFFECTS: remove correspond tp if match the name given by the user
    private void removeTP() {
        String givenName;
        TravelingPartner removeTP;

        System.out.println("Please enter the name of the traveling partner that you want to remove : ");
        givenName = input.next();
        removeTP = new TravelingPartner(givenName);

        if (userTravelingPartnerList.removeTravelingPartner(removeTP)) {
            System.out.println("The traveling partner has been removed");
        }
        System.out.println("This traveling partner does not exist");
    }


}



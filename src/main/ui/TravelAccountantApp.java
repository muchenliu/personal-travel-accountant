package ui;

import model.*;

import javax.swing.*;
import java.util.Objects;
import java.util.Scanner;

//travel accountant application
public class TravelAccountantApp {
    private double budget;
    private double cash;
    private final Scanner input;
    private ExpenseList userExpenses = new ExpenseList();
    private TravelingPartnerList userTravelingPartnerList = new TravelingPartnerList();

    private final LoadAndSaveDataManager loadAndSaveDataManager;
    private final PopUpGifManager popUpGifManager;
    private TravelingPartnerListPanel travelingPartnerListPanel;

    private final JFrame loadDataFrame;
    private final JFrame saveDataFrame;
    private final JFrame travelingPartnerListFrame;

    //EFFECTS: runs the travel accountant application
    public TravelAccountantApp() {
        input = new Scanner(System.in);
        budget = 0;
        cash = 0;
        loadAndSaveDataManager = new LoadAndSaveDataManager();
        popUpGifManager = new PopUpGifManager();
        loadDataFrame = new JFrame();
        saveDataFrame = new JFrame();
        travelingPartnerListFrame = new JFrame("Traveling Partners");
        runTravelAccountant();
    }

    //MODIFIES: this
    //EFFECTS: process user inputs of the main page of the app, calls display image methods,
    //         calls processSaveFile() and print out EventLog on console when the user choose to quit the app
    private void runTravelAccountant() {
        popUpGifManager.displayWelcomeImage();
        System.out.println("Welcome to your Personal Traveling Accountant!");
        System.out.println("We keeps eye on your expense and help you manage your trip more easier!");
        processLoadFile();

        boolean keepGoing = true;
        while (keepGoing) {
            displayMainMenu();
            System.out.println("Please enter the command : ");
            String command = input.next();

            if (command.equals("q")) {
                processSaveFile();
                System.out.println("\n");
                for (Event e : EventLog.getInstance()) {
                    System.out.println(e.toString());
                }
                keepGoing = false;
            } else {
                processMainCommand(command);
            }
        }

        popUpGifManager.displayGoodbyeImage();
        System.out.println("\nSee you next time! Have a wonderful trip :)");
    }

    // EFFECTS: displays main menu of options to user
    private void displayMainMenu() {
        System.out.println("\nPlease select from the following categories:");
        System.out.println("\te ->  expense");
        System.out.println("\tt ->  traveling partner");
        System.out.println("\tc ->  cash");
        System.out.println("\tq ->  quit");
    }

    //MODIFIES: this
    //EFFECTS: processes main user command
    private void processMainCommand(String command) {
        if (command.equals("e")) {
            runExpense();
        } else if (command.equals("t")) {
            runTravelingPartner();
        } else if (command.equals("c")) {
            runCash();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    //MODIFIES: this
    //EFFECTS: displays load file option to user and processes the event
    private void processLoadFile() {
        loadDataFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        int n = JOptionPane.showConfirmDialog(loadDataFrame,
                "Would you like to load the data which you saved last time?",
                "Load Data?",
                JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            loadAndSaveDataManager.loadTellerAccountantData();
            budget = loadAndSaveDataManager.getBudget();
            cash = loadAndSaveDataManager.getCash();
            userExpenses = loadAndSaveDataManager.getUserExpenses();
            userTravelingPartnerList = loadAndSaveDataManager.getUserTravelingPartnerList();
        } else if (n == JOptionPane.NO_OPTION) {
            System.out.println("Please enter your Budget : ");
            budget = input.nextDouble();
            System.out.println("Please enter your current Cash amount : ");
            cash = input.nextDouble();
        } else {
            System.out.println("Selection not valid...");
            processLoadFile();
        }
    }

    //EFFECTS: displays save file option to user and processes the event
    private void processSaveFile() {
        //saveDataFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        int n = JOptionPane.showConfirmDialog(saveDataFrame,
                "Would you like to save what you have so far?",
                "Save Data?",
                JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            loadAndSaveDataManager.saveTellerAccountantData(budget, cash, userExpenses, userTravelingPartnerList);
        }
    }

    //MODIFIES: this
    //EFFECTS: process user inputs of the Expense page of the app
    private void runExpense() {
        displayExpenseMenu();
        String command = input.next();
        processExpenseCommand(command);
    }

    //MODIFIES: this
    //EFFECTS: process user inputs of the TravelingPartner page of the app
    private void runTravelingPartner() {
        displayTravelingPartnerMenu();
        String command = input.next();
        processTravelingPartnerCommand(command);
    }

    //MODIFIES: this
    //EFFECTS: process user inputs of the Cash page of the app
    private void runCash() {
        displayCashMenu();
        String command = input.next();
        processCashCommand(command);
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
            System.out.println("Please re-enter the input");
            runExpense();
        }
    }

    //EFFECTS: display tp menu to user
    private void displayTravelingPartnerMenu() {
        System.out.println("\nPlease select from the following categories:");
        System.out.println("\ta ->  add or remove traveling partners");
        System.out.println("\tb ->  check amount other owed to you during this trip");
        System.out.println("\tc ->  check amount you borrowed from other during this trip");
    }

    //MODIFIES: this
    //EFFECTS: process tp user command
    private void processTravelingPartnerCommand(String command) {
        if (command.equals("a")) {
            setUpAndShowTravelingPartnerListFrame();
            //update userTravelingPartnerList
            userTravelingPartnerList = travelingPartnerListPanel.getTPList();
        } else if (command.equals("b")) {
            printAmountOwed();
        } else if (command.equals("c")) {
            printAmountBorrowed();
        } else {
            System.out.println("Selection not valid...");
            System.out.println("Please re-enter the input");
            runTravelingPartner();
        }
    }

    //MODIFIES: this
    //EFFECTS: set up travelingPartnerListFrame
    private void setUpAndShowTravelingPartnerListFrame() {
        travelingPartnerListFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        travelingPartnerListPanel = new TravelingPartnerListPanel(userTravelingPartnerList);
        JComponent travelingPartnerListContentPane = travelingPartnerListPanel;
        travelingPartnerListContentPane.setOpaque(true);
        travelingPartnerListFrame.setContentPane(travelingPartnerListPanel);
        travelingPartnerListFrame.pack();
        travelingPartnerListFrame.setVisible(true);
    }

    //EFFECTS: display cash menu to user
    private void displayCashMenu() {
        System.out.println("\nPlease select from the following categories:");
        System.out.println("\ta ->  record cash income");
        System.out.println("\tb ->  record cash expense");
        System.out.println("\tc ->  check cash balance");
    }

    //MODIFIES: this
    //EFFECTS: process cash user command
    private void processCashCommand(String command) {
        if (command.equals("a")) {
            cashIn();
        } else if (command.equals("b")) {
            cashOut();
        } else if (command.equals("c")) {
            cashLeft();
        } else {
            System.out.println("Selection not valid...");
            System.out.println("Please re-enter the input");
            runCash();
        }
    }

    //MODIFIES: this
    //EFFECTS: record an Expense input from user
    private void recordExpense() {
        double amount;
        String category;

        System.out.println("Please enter the amount ant the category of this expense :");
        System.out.println("Amount : ");
        amount = input.nextDouble();
        System.out.println("Category : ");
        category = input.next();

        doSplitAndAddExpense(amount, category);
    }

    //MODIFIES: this
    //EFFECTS: process the input, split the expense to correspond person/people if needed and add the expense to the
    //         list
    private void doSplitAndAddExpense(double amount, String category) {
        Expense newExpense = new Expense(amount, category);
        System.out.println("Is this a split expense?");
        System.out.println("Please enter y for yes and n for no");
        String isSplit = input.next();
        boolean invalidInput = true;
        while (invalidInput) {
            if (Objects.equals(isSplit, "y")) {
                newExpense.setSplitAmount();
                userExpenses.addExpense(newExpense);
                addExpenseToTP(newExpense.getAmount());
                invalidInput = false;
            } else if (Objects.equals(isSplit, "n")) {
                userExpenses.addExpense(newExpense);
                invalidInput = false;
                System.out.println("The expense has ben added");
            } else {
                System.out.println("invalid input...");
                System.out.println("Please re-enter y for split expense or n for un-split expense : ");
                isSplit = input.next();
            }
        }
    }

    //MODIFIES: this
    //EFFECT: add the split amount to correspond person/people depends on who paid the bill
    private void addExpenseToTP(double amount) {
        System.out.println("Who paid for the bill?");
        System.out.println("\ta -> I paid the bill");
        System.out.println("\tb -> One of my traveling partner paid the bill");
        String whoPaid = input.next();
        boolean invalidInput = true;
        while (invalidInput) {
            if (Objects.equals(whoPaid, "a")) {
                userTravelingPartnerList.addSplitExpenseAmountOwedToMe(amount);
                System.out.println("The split expense has ben added");
                invalidInput = false;
            } else if (Objects.equals(whoPaid, "b")) {
                System.out.println("Please enter the name of the traveling partner who paid for the bill : ");
                String tpName = input.next();
                userTravelingPartnerList.addSplitExpenseAmountIBorrowed(tpName, amount);
                System.out.println("The split expense has ben added");
                invalidInput = false;
            } else {
                System.out.println("invalid input...");
                System.out.println("Please re-enter a or b : ");
                whoPaid = input.next();
            }
        }
    }

    //REQUIRES: If the expense is a split amount, the input amount should be the amount that is split
    //MODIFIES: this
    //EFFECTS: remove correspond Expense if match the amount and category input of user input
    private void removeExpense() {
        double amount;
        String category;
        Expense wasteExpense;

        System.out.println("Please enter the amount and the category of expense you want to remove:");
        System.out.println("Amount : ");
        amount = input.nextDouble();
        System.out.println("Category : ");
        category = input.next();

        wasteExpense = new Expense(amount, category);
        if (userExpenses.removeExpense(wasteExpense)) {
            System.out.println("The expense has been removed");
        } else {
            System.out.println("This expense does not exist");
        }
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

    //EFFECTS: print out the amount each tp owed to user
    private void printAmountOwed() {
        System.out.println("The amount each traveling partner owed to you are as following :");
        for (TravelingPartner next : userTravelingPartnerList.getTravelingPartnerList()) {
            System.out.println(next.getName() + " : $" + next.getAmountOwedToMe());
        }
    }

    //EFFECTS: print out the amount user borrowed from user
    private void printAmountBorrowed() {
        System.out.println("The amount you have borrowed from each traveling partner are as the following : ");
        for (TravelingPartner next : userTravelingPartnerList.getTravelingPartnerList()) {
            System.out.println(next.getName() + " : " + next.getAmountIBorrowed());
        }
    }

    //EFFECTS: add the cash income to cash amount and print out the cash balance after added the cash gain
    private void cashIn() {
        double cashGain;
        System.out.println("Please enter the cash amount you gained : $");
        cashGain = input.nextDouble();
        cash = cash + cashGain;
        System.out.println("You now have $" + cash + " cash");
    }

    //EFFECTS: subtract the cash expense to cash amount and print out the cash balance after added the cash subtracted
    private void cashOut() {
        double cashOut;
        System.out.println("Please enter the cash amount you expended : $");
        cashOut = input.nextDouble();
        cash = cash - cashOut;
        System.out.println("You now have $" + cash + " cash");
    }

    //EFFECTS: display the amount of cash left
    private void cashLeft() {
        System.out.println("You still have $" + cash + " cash left");
    }
}



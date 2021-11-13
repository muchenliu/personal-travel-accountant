package ui;

import model.ExpenseList;
import model.TravelingPartnerList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

// Manage the loading and saving data events
public class LoadAndSaveDataManager {
    private double budget;
    private double cash;
    private ExpenseList userExpenses;
    private TravelingPartnerList userTravelingPartnerList;

    private static final String JSON_EXPENSE_LIST_STORE = "./data/myFile/expenseList.txt";
    private static final String JSON_TRAVELING_PARTNER_LIST_STORE = "./data/myFile/travelingPartnerList.txt";
    private static final String JSON_BUDGET_STORE = "./data/myFile/budget.txt";
    private static final String JSON_CASH_STORE = "./data/myFile/cash.txt";
    private final JsonWriter userExpenseListJsonWriter;
    private final JsonReader userExpenseListJsonReader;
    private final JsonWriter userTravelingPartnerListJsonWriter;
    private final JsonReader userTravelingPartnerListJsonReader;
    private final JsonWriter userBudgetJsonWriter;
    private final JsonReader userBudgetJsonReader;
    private final JsonWriter userCashJsonWriter;
    private final JsonReader userCashJsonReader;

    //getters
    public double getBudget() {
        return budget;
    }

    public double getCash() {
        return cash;
    }

    public ExpenseList getUserExpenses() {
        return userExpenses;
    }

    public TravelingPartnerList getUserTravelingPartnerList() {
        return userTravelingPartnerList;
    }

    //EFFECTS: sets budget and cash fields to zero; initiates all necessary fields
    public LoadAndSaveDataManager() {
        budget = 0;
        cash = 0;
        userExpenses = new ExpenseList();
        userTravelingPartnerList = new TravelingPartnerList();

        userExpenseListJsonWriter = new JsonWriter(JSON_EXPENSE_LIST_STORE);
        userExpenseListJsonReader = new JsonReader(JSON_EXPENSE_LIST_STORE);
        userTravelingPartnerListJsonWriter = new JsonWriter(JSON_TRAVELING_PARTNER_LIST_STORE);
        userTravelingPartnerListJsonReader = new JsonReader(JSON_TRAVELING_PARTNER_LIST_STORE);
        userBudgetJsonWriter = new JsonWriter(JSON_BUDGET_STORE);
        userBudgetJsonReader = new JsonReader(JSON_BUDGET_STORE);
        userCashJsonWriter = new JsonWriter(JSON_CASH_STORE);
        userCashJsonReader = new JsonReader(JSON_CASH_STORE);
    }

    //MODIFIES: this
    //EFFECTS: load expenseList, travelingPartnerList, cash, budget data from file
    public void loadTellerAccountantData() {
        try {
            userExpenses = userExpenseListJsonReader.readExpenseList();
            System.out.println("Loaded expense list from " + JSON_EXPENSE_LIST_STORE);

            userTravelingPartnerList = userTravelingPartnerListJsonReader.readTPList();
            System.out.println("Loaded traveling partner list from " + JSON_TRAVELING_PARTNER_LIST_STORE);

            budget = userBudgetJsonReader.readAndParseBudget();
            System.out.println("Loaded budget amount from " + JSON_BUDGET_STORE);

            cash = userCashJsonReader.readAndParseCash();
            System.out.println("Loaded cash amount from " + JSON_CASH_STORE);

        } catch (IOException e) {
            System.out.println("Sorry, unable to read from file.");
        }
    }

    //EFFECTS: save expenseList, travelingPartnerList, cash, budget data to file
    public void saveTellerAccountantData(double budget, double cash, ExpenseList userExpenses,
                                         TravelingPartnerList userTravelingPartnerList) {
        try {
            userExpenseListJsonWriter.openWriter();
            userExpenseListJsonWriter.writeUserExpenseList(userExpenses);
            userExpenseListJsonWriter.close();
            System.out.println("Saved expense list to " + JSON_EXPENSE_LIST_STORE);

            userTravelingPartnerListJsonWriter.openWriter();
            userTravelingPartnerListJsonWriter.writeUserTravelingPartnerList(userTravelingPartnerList);
            userTravelingPartnerListJsonWriter.close();
            System.out.println("Saved traveling partner list to " + JSON_TRAVELING_PARTNER_LIST_STORE);

            userBudgetJsonWriter.openWriter();
            userBudgetJsonWriter.writeUserBudget(budget);
            userBudgetJsonWriter.close();
            System.out.println("Saved budget amount to " + JSON_BUDGET_STORE);

            userCashJsonWriter.openWriter();
            userCashJsonWriter.writeUserCash(cash);
            userCashJsonWriter.close();
            System.out.println("Saved cash amount to " + JSON_CASH_STORE);


        } catch (FileNotFoundException e) {
            System.out.println("Sorry, unable to write to file.");
        }

    }
}
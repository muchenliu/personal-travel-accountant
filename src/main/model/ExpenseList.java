package model;

import java.util.LinkedList;
import java.util.List;

// Creates a list of Expense
public class ExpenseList {
    private List<Expense> expenseList;

    public ExpenseList() {
        expenseList = new LinkedList<>();
    }

    //REQUIRES: e must be an existed Expense
    //MODIFIES: this
    //EFFECTS: add the given Expense to this expenseList
    public void addExpense(Expense e) {
        expenseList.add(e);
    }

    //REQUIRES: e must be in the expenses
    //MODIFIES: this
    //EFFECTS: removes the given Expense from this expenseList
    public void removeExpense(Expense e) {
        expenseList.remove(e);
    }

    //EFFECTS: count up total amount of Expenses on this expenseList
    public int count() {
        return expenseList.size();
    }


    //EFFECTS: return the sum of the total expense in this expenseList
    public double totalExpense() {
        double total = 0;
        for (Expense next : expenseList) {
            total = total + next.getAmount();
        }
        return total;
    }

    //REQUIRES: the given cname must match with at least one of the category of existed Expense in this expenseList
    //EFFECTS: return the total expense in this expenseList of the given category
    public double totalExpenseInCategory(String cname) {
        double total = 0;
        for (Expense next : expenseList) {
            if (next.getCategory() == cname) {
                total = total + next.getAmount();
            }
        }
        return total;
    }

    //EFFECTS: return the total cash expense in this expenseList
    public double totalCashExpense() {
        double total = 0;
        for (Expense next : expenseList) {
            if (next.isCash) {
                total = total + next.getAmount();
            }
        }
        return total;
    }

}

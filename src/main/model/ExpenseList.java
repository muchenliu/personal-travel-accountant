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
    //EFFECTS: add the given Expense to this expenseList, if the given Expense is already in the list, then do nothing
    public void addExpense(Expense e) {
        if (!expenseList.contains(e)) {
            expenseList.add(e);
        }
    }

    //REQUIRES: e must be in the expenses
    //MODIFIES: this
    //EFFECTS: removes the given Expense from this expenseList, return false if the expense is not in the list
    public boolean removeExpense(Expense e) {
        if (expenseList.contains(e)) {
            expenseList.remove(e);
            return true;
        }
        return false;
    }

    //EFFECTS: count up total amount of Expenses on this expenseList
    public int count() {
        return expenseList.size();
    }

    //REQUIRES: this must not be an empty list
    //EFFECTS: return the Expense in the list which corresponds to the input position
    public Expense getExpense(int n) {
        return expenseList.get(n);
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

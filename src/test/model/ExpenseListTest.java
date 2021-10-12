package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpenseListTest {
    ExpenseList testSet;
    Expense expenseA = new Expense(38.5, "Souvenirs");
    Expense expenseB = new Expense(7, "Food");
    Expense expenseC = new Expense(40, "Living");
    Expense expenseD = new Expense(88.73, "Living");
    Expense expenseE = new Expense(58, "Food");

    @BeforeEach
    public void setup() {
        testSet = new ExpenseList();
    }

    @Test
    void testAddExpense() {
        // add one expense to empty expenseList
        testSet.addExpense(expenseA);

        assertEquals(1, testSet.count());

        // add multiple expenses to expenseList
        testSet.addExpense(expenseB);
        testSet.addExpense(expenseC);

        assertEquals(3, testSet.count());
    }

    @Test
    void testRemoveExpense() {
        testSet.addExpense(expenseA);
        testSet.addExpense(expenseB);
        testSet.addExpense(expenseC);

        // remove one expense from testSet of size 3
        testSet.removeExpense(expenseA);

        assertEquals(2, testSet.count());

        // remove one expense from testSet of size 2
        testSet.removeExpense(expenseB);

        assertEquals(1, testSet.count());

        // remove one expense from testSet of size 1
        testSet.removeExpense(expenseC);

        assertEquals(0, testSet.count());
    }

    @Test
    void testTotalExpenseWithEmptyExpenseList() {
        double total = testSet.totalExpense();

        assertEquals(0, total);
    }

    @Test
    void testTotalExpenseWithNonEmptyExpenseList() {
        testSet.addExpense(expenseA);
        testSet.addExpense(expenseB);
        testSet.addExpense(expenseC);
        double total = testSet.totalExpense();

        assertEquals(expenseA.getAmount() + expenseB.getAmount() + expenseC.getAmount(), total);
    }

    @Test
    void testTotalExpenseInCategory() {
        testSet.addExpense(expenseA);
        testSet.addExpense(expenseB);
        testSet.addExpense(expenseC);
        testSet.addExpense(expenseD);
        testSet.addExpense(expenseE);
        double totalSouvenirs = testSet.totalExpenseInCategory("Souvenirs");
        double totalFood = testSet.totalExpenseInCategory("Food");
        double totalLiving = testSet.totalExpenseInCategory("Living");

        assertEquals(expenseA.getAmount(), totalSouvenirs);
        assertEquals(expenseB.getAmount() + expenseE.getAmount(), totalFood);
        assertEquals(expenseC.getAmount() + expenseD.getAmount(), totalLiving);
    }

    @Test
    void testTotalCashExpenseWithZeroCashExpense() {
        assertEquals(0, testSet.totalCashExpense());
    }

    @Test
    void testTotalCashExpenseWithSomeCashExpense() {
        expenseA.setCash();
        expenseC.setCash();
        expenseE.setCash();
        testSet.addExpense(expenseA);
        testSet.addExpense(expenseB);
        testSet.addExpense(expenseC);
        testSet.addExpense(expenseD);
        testSet.addExpense(expenseE);
        double totalCashExp = testSet.totalCashExpense();

        assertEquals(expenseA.getAmount() + expenseC.getAmount() + expenseE.getAmount(), totalCashExp);
    }
}

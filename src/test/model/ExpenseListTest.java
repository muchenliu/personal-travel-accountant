package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonTest;

import static org.junit.jupiter.api.Assertions.*;

public class ExpenseListTest {
    ExpenseList testExpenseList;
    Expense expenseA = new Expense(38.5, "Souvenirs");
    Expense expenseB = new Expense(7, "Food");
    Expense expenseC = new Expense(40, "Living");
    Expense expenseD = new Expense(88.73, "Living");
    Expense expenseE = new Expense(58, "Food");
    Expense expenseF = new Expense(58, "Living");
    Expense expenseG = new Expense(8, "Food");


    @BeforeEach
    public void setup() {
        testExpenseList = new ExpenseList();
    }

    @Test
    public void testAddExpense() {
        // add one expense to empty expenseList
        testExpenseList.addExpense(expenseA);

        assertEquals(1, testExpenseList.count());
        assertEquals(expenseA, testExpenseList.getExpense(0));

        // add multiple expenses to expenseList
        testExpenseList.addExpense(expenseB);
        testExpenseList.addExpense(expenseC);

        assertEquals(3, testExpenseList.count());
        assertEquals(expenseA, testExpenseList.getExpense(0));
        assertEquals(expenseB, testExpenseList.getExpense(1));
        assertEquals(expenseC, testExpenseList.getExpense(2));

        //add duplicate expense to the expenseList
        testExpenseList.addExpense(expenseA);

        assertEquals(3, testExpenseList.count());
        assertEquals(expenseA, testExpenseList.getExpense(0));
        assertEquals(expenseB, testExpenseList.getExpense(1));
        assertEquals(expenseC, testExpenseList.getExpense(2));
    }

    @Test
    public void testRemoveExpenseReturnTrue() {
        testExpenseList.addExpense(expenseA);
        testExpenseList.addExpense(expenseB);
        testExpenseList.addExpense(expenseC);

        // remove one expense from testSet of size 3
        boolean removeA = testExpenseList.removeExpense(expenseA);

        assertTrue(removeA);
        assertEquals(2, testExpenseList.count());
        assertEquals(expenseB, testExpenseList.getExpense(0));
        assertEquals(expenseC, testExpenseList.getExpense(1));

        // remove one expense from testSet of size 2
        testExpenseList.removeExpense(expenseB);

        assertEquals(1, testExpenseList.count());
        assertEquals(expenseC, testExpenseList.getExpense(0));

        // remove one expense from testSet of size 1
        testExpenseList.removeExpense(expenseC);

        assertEquals(0, testExpenseList.count());
    }

    @Test
    public void testRemoveExpenseReturnFalse() {
        //remove expense from an empty list
        assertFalse(testExpenseList.removeExpense(expenseA));

        //remove expense not in the list
        testExpenseList.addExpense(expenseE);
        boolean removeB = testExpenseList.removeExpense(expenseB);

        assertFalse(removeB);
        assertEquals(1, testExpenseList.count());
        assertEquals(expenseE, testExpenseList.getExpense(0));

        //remove expense not in the list but has same getAmount() with one Expense in the list
        boolean removeF = testExpenseList.removeExpense(expenseF);

        assertFalse(removeF);

        //remove expense not in the list but have same getCategory() with ine Expense in the list
        boolean removeG = testExpenseList.removeExpense(expenseG);

        assertFalse(removeG);
    }

    @Test
    public void testTotalExpenseWithEmptyExpenseList() {
        double total = testExpenseList.totalExpense();

        assertEquals(0, total);
    }

    @Test
    public void testTotalExpenseWithNonEmptyExpenseList() {
        testExpenseList.addExpense(expenseA);
        testExpenseList.addExpense(expenseB);
        testExpenseList.addExpense(expenseC);
        double total = testExpenseList.totalExpense();

        assertEquals(expenseA.getAmount() + expenseB.getAmount() + expenseC.getAmount(), total);
    }

    @Test
    public void testTotalExpenseInCategory() {
        testExpenseList.addExpense(expenseA);
        testExpenseList.addExpense(expenseB);
        testExpenseList.addExpense(expenseC);
        testExpenseList.addExpense(expenseD);
        testExpenseList.addExpense(expenseE);
        double totalSouvenirs = testExpenseList.totalExpenseInCategory("Souvenirs");
        double totalFood = testExpenseList.totalExpenseInCategory("Food");
        double totalLiving = testExpenseList.totalExpenseInCategory("Living");

        assertEquals(expenseA.getAmount(), totalSouvenirs);
        assertEquals(expenseB.getAmount() + expenseE.getAmount(), totalFood);
        assertEquals(expenseC.getAmount() + expenseD.getAmount(), totalLiving);
    }

    @Test
    public void testToJson() {
        testExpenseList.addExpense(expenseA);
        testExpenseList.addExpense(expenseB);


        JSONObject jsonObject = testExpenseList.toJson();
        JSONArray jsonArray = jsonObject.getJSONArray("expense list");
        JSONObject jsonA = jsonArray.getJSONObject(0);
        JSONObject jsonB = jsonArray.getJSONObject(1);

        assertEquals(expenseA.getAmount(), jsonA.getDouble("amount"));
        assertEquals(expenseA.getCategory(), jsonA.getString("category"));
        assertEquals(expenseB.getAmount(), jsonB.getDouble("amount"));
        assertEquals(expenseB.getCategory(), jsonB.getString("category"));
    }

}

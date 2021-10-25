package persistence;

import model.Expense;
import model.TravelingPartner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    protected void checkExpense(double amount, String category, Expense expense) {
        assertEquals(amount, expense.getAmount());
        assertEquals(category, expense.getCategory());
    }

    protected void checkTravelingPartner
            (String name, double amountOwedToMe, double amountIBorrowed, TravelingPartner tp) {
        assertEquals(name, tp.getName());
        assertEquals(amountOwedToMe, tp.getAmountOwedToMe());
        assertEquals(amountIBorrowed, tp.getAmountIBorrowed());
    }
}

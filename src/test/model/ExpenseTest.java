package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpenseTest {
    Expense testExpense;

    @BeforeEach
    void setup() {
        testExpense = new Expense(19.75, "Food");
    }

    @Test
    void testExpense() {
        assertEquals(19.75, testExpense.getAmount());
        assertEquals("Food", testExpense.getCategory());
        assertFalse(testExpense.isCash);
    }

    @Test
    void testSetSplitAmount() {
        TravelingPartner tp1 = new TravelingPartner("Annie");
        TravelingPartner tp2 = new TravelingPartner("Ben");
        TravelingPartnerList tpList = new TravelingPartnerList();
        tpList.addTravelingPartner(tp1);
        tpList.addTravelingPartner(tp2);
        testExpense.setSplitAmount();

        assertEquals(19.75 / 3, testExpense.getAmount());
    }
}
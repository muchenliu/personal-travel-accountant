package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExpenseTest {
    Expense testExpense;

    @BeforeEach
    public void setup() {
        testExpense = new Expense(19.75, "Food");
    }

    @Test
   public void testExpense() {
        assertEquals(19.75, testExpense.getAmount());
        assertEquals("Food", testExpense.getCategory());
    }

    @Test
    public void testSetSplitAmount() {
        TravelingPartner tp1 = new TravelingPartner("Annie");
        TravelingPartner tp2 = new TravelingPartner("Ben");
        TravelingPartnerList tpList = new TravelingPartnerList();
        tpList.addTravelingPartner(tp1);
        tpList.addTravelingPartner(tp2);
        testExpense.setSplitAmount();

        assertEquals(19.75 / 3, testExpense.getAmount());
    }

    @Test
    public void testToJson() {
        JSONObject json = testExpense.toJson();

        assertEquals(testExpense.getAmount(), json.getDouble("amount"));
        assertEquals(testExpense.getCategory(), json.getString("category"));
    }
}
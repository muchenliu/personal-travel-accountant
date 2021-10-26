package persistence;

import model.ExpenseList;
import model.TravelingPartnerList;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    public void testReaderNotExistedFile() {
        JsonReader reader = new JsonReader("./data/testReaderNotExistedFile.txt");
        try {
            ExpenseList expenseList = reader.readExpenseList();
            fail("expect to catch IOException");
            TravelingPartnerList tpList = reader.readTPList();
            fail("expect to catch IOException");
            double cash = reader.readAndParseCash();
            fail("expect to catch IOException");
            double budget = reader.readAndParseBudget();
            fail("expect to catch IOException");

        } catch (IOException e) {
            //test pass
        }
    }

    @Test
    public void testReaderEmptyExpenseList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyExpenseList.txt");
        try {
            ExpenseList expenseList = reader.readExpenseList();

            assertEquals(0, expenseList.count());
            //test pass

        } catch (IOException e) {
            fail("not expected to throw IOException");
        }

    }

    @Test
    public void testReaderEmptyTravelingPartnerList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyTravelingPartnerList.txt");
        try {
            TravelingPartnerList tpList = reader.readTPList();

            assertEquals(0, tpList.count());
            //test pass

        } catch (IOException e) {
            fail("not expected to throw IOException");
        }
    }

    @Test
    public void testReaderGeneralExpenseList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralExpenseList.txt");

        try {
            ExpenseList expenseList = reader.readExpenseList();

            assertEquals(2, expenseList.count());
            checkExpense(35.75, "Game", expenseList.getExpense(0));
            checkExpense(1000, "Living", expenseList.getExpense(1));
            //test pass

        } catch (IOException e) {
            fail("not expected to throw IOException");
        }
    }

    @Test
    public void testReaderGeneralTravelingPartnerList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralTravelingPartnerList.txt");

        try {
            TravelingPartnerList tpList = reader.readTPList();

            assertEquals(2, tpList.count());
            checkTravelingPartner
                    ("Ken", 5, 15.5, tpList.getTravelingPartner(0));
            checkTravelingPartner
                    ("Doris", 44, 300, tpList.getTravelingPartner(1));
            //test pass

        } catch (IOException e) {
            fail("not expected to throw IOException");
        }
    }

    @Test
    public void testReaderUserCashZeroDollar() {
        JsonReader reader = new JsonReader("./data/testReaderUserCashZeroDollar.txt");

        try {
            double cash = reader.readAndParseCash();

            assertEquals(0, cash);
            //test pass

        } catch (IOException e) {
            fail("not expected to throw IOException");
        }
    }

    @Test
    public void testReaderUserBudgetZeroDollar() {
        JsonReader reader = new JsonReader("./data/testReaderUserBudgetZeroDollar.txt");

        try {
            double budget = reader.readAndParseBudget();

            assertEquals(0, budget);
            //test pass

        } catch (IOException e) {
            fail("not expected to throw IOException");
        }
    }

    @Test
    public void testReaderGeneralUserCash() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralUserCash.txt");

        try {
            double cash = reader.readAndParseCash();

            assertEquals(29.99, cash);
            //test pass

        } catch (IOException e) {
            fail("not expected to throw IOException");
        }
    }

    @Test
    public void testReaderGeneralUserBudget() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralUserBudget.txt");

        try {
            double budget = reader.readAndParseBudget();

            assertEquals(3780, budget);
            //test pass

        } catch (IOException e) {
            fail("not expected to throw IOException");
        }
    }

}

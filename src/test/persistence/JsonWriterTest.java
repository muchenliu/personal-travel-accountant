package persistence;

import model.Expense;
import model.ExpenseList;
import model.TravelingPartner;
import model.TravelingPartnerList;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {

    @Test
    public void testWriterInvalidFile() {
        JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.txt");
        try {
            writer.openWriter();
            fail("expect to catch IOException");

        } catch (IOException e) {
            //test pass
        }
    }

    @Test
    public void testWriterEmptyExpenseList() {
        try {
            ExpenseList expenseList = new ExpenseList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyExpenseList.txt");
            writer.openWriter();
            writer.writeUserExpenseList(expenseList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyExpenseList.txt");
            expenseList = reader.readExpenseList();

            assertEquals(0, expenseList.count());
            //test pass

        } catch (IOException e) {
            fail("not expected to throw IOException");
        }
    }

    @Test
    public void testWriterEmptyTravelingPartnerList() {
        try {
            TravelingPartnerList tpList = new TravelingPartnerList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyTravelingPartnerList.txt");
            writer.openWriter();
            writer.writeUserTravelingPartnerList(tpList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyTravelingPartnerList.txt");
            tpList = reader.readTPList();

            assertEquals(0, tpList.count());
            //test pass

        } catch (IOException e) {
            fail("not expected to throw IOException");
        }
    }

    @Test
    public void testWriterGeneralExpenseList() {
        try {
            ExpenseList expenseList = new ExpenseList();
            Expense a = new Expense(100, "Food");
            Expense b = new Expense(33, "Living");
            expenseList.addExpense(a);
            expenseList.addExpense(b);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralExpenseList.txt");
            writer.openWriter();
            writer.writeUserExpenseList(expenseList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralExpenseList.txt");
            expenseList = reader.readExpenseList();

            assertEquals(2, expenseList.count());
            checkExpense(100, "Food", expenseList.getExpense(0));
            checkExpense(33, "Living", expenseList.getExpense(1));
            //test pass

        } catch (IOException e) {
            fail("not expected to throw IOException");
        }
    }

    @Test
    public void testWriterGeneralTravelingPartnerList() {
        try {
            TravelingPartnerList tpList = new TravelingPartnerList();
            TravelingPartner jenny = new TravelingPartner("Jenny");
            TravelingPartner david = new TravelingPartner("David");
            jenny.addAmountOwedToMe(300);
            jenny.addAmountIBorrowed(15.5);
            david.addAmountOwedToMe(10);
            david.addAmountIBorrowed(54);
            tpList.addTravelingPartner(jenny);
            tpList.addTravelingPartner(david);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralTravelingPartnerList.txt");
            writer.openWriter();
            writer.writeUserTravelingPartnerList(tpList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralTravelingPartnerList.txt");
            tpList = reader.readTPList();

            assertEquals(2, tpList.count());
            checkTravelingPartner
                    ("Jenny", 300, 15.5, tpList.getTravelingPartner(0));
            checkTravelingPartner
                    ("David", 10, 54, tpList.getTravelingPartner(1));
            //test pass

        } catch (IOException e) {
            fail("not expected to throw IOException");
        }


    }
}

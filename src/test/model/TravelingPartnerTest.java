package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TravelingPartnerTest {
    TravelingPartner testTP;

    @BeforeEach
    public void setup() {
        testTP = new TravelingPartner("Danny");
    }

    @Test
    public void testTravelingPartner() {
        assertEquals("Danny", testTP.getName());
        assertEquals(0, testTP.getAmountOwedToMe());
        assertEquals(0, testTP.getAmountIBorrowed());
    }

    @Test
    public void testAddAmountOwedToMe() {
        //add from zero
        testTP.addAmountOwedToMe(30);

        assertEquals(30, testTP.getAmountOwedToMe());

        //add from non-zero
        testTP.addAmountOwedToMe(58.7);

        assertEquals(88.7, testTP.getAmountOwedToMe());
    }

    @Test
    public void testAddAmountIBorrowed() {
        //add from zero
        testTP.addAmountIBorrowed(99);

        assertEquals(99, testTP.getAmountIBorrowed());

        //add form non-zero
        testTP.addAmountIBorrowed(38.5);

        assertEquals(137.5, testTP.getAmountIBorrowed());
    }

    @Test
    public void testToJson() {
        testTP.addAmountOwedToMe(13.75);
        testTP.addAmountIBorrowed(45);

        JSONObject json = testTP.toJson();

        assertEquals(testTP.getName(), json.getString("name"));
        assertEquals(testTP.getAmountOwedToMe(), json.getDouble("amount owed to me"));
        assertEquals(testTP.getAmountIBorrowed(), json.getDouble("amount I borrowed"));
    }
}

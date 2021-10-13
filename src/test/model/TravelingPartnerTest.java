package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TravelingPartnerTest {
    TravelingPartner testTP;

    @BeforeEach
    void setup() {
        testTP = new TravelingPartner("Danny");
    }

    @Test
    void testTravelingPartner() {
        assertEquals("Danny", testTP.getName());
        assertEquals(0, testTP.amountOwedToMe);
        assertEquals(0, testTP.amountIBorrowed);
    }

    @Test
    void testAddAmountOwedToMe() {
        //add from zero
        testTP.addAmountOwedToMe(30);

        assertEquals(30, testTP.amountOwedToMe);

        //add from non-zero
        testTP.addAmountOwedToMe(58.7);

        assertEquals(88.7, testTP.amountOwedToMe);
    }

    @Test
    void testAddAmountIBorrowed() {
        //add from zero
        testTP.addAmountIBorrowed(99);

        assertEquals(99, testTP.amountIBorrowed);

        //add form non-zero
        testTP.addAmountIBorrowed(38.5);

        assertEquals(137.5, testTP.amountIBorrowed);
    }
}

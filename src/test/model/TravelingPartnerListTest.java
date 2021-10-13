package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TravelingPartnerListTest {
    TravelingPartnerList testTPList;
    TravelingPartner tp1 = new TravelingPartner("Benny");
    TravelingPartner tp2 = new TravelingPartner("Emily");
    TravelingPartner tp3 = new TravelingPartner("Ken");


    @BeforeEach
    void setup() {
        testTPList = new TravelingPartnerList();
    }

    @Test
    void testAddTravelingPartner() {
        //add one tp to an empty travelingPartnerList
        testTPList.addTravelingPartner(tp1);

        assertEquals(1, TravelingPartnerList.count());
        assertEquals(tp1, testTPList.getTravelingPartner(0));

        //add multiple tps to travelingPartnerList
        testTPList.addTravelingPartner(tp2);
        testTPList.addTravelingPartner(tp3);

        assertEquals(3, TravelingPartnerList.count());
        assertEquals(tp1, testTPList.getTravelingPartner(0));
        assertEquals(tp2, testTPList.getTravelingPartner(1));
        assertEquals(tp3, testTPList.getTravelingPartner(2));

        //add duplicate tp to the list
        testTPList.addTravelingPartner(tp1);

        assertEquals(3, TravelingPartnerList.count());
        assertEquals(tp1, testTPList.getTravelingPartner(0));
        assertEquals(tp2, testTPList.getTravelingPartner(1));
        assertEquals(tp3, testTPList.getTravelingPartner(2));
    }

    @Test
    void testRemoveTravelingPartner() {
        testTPList.addTravelingPartner(tp1);
        testTPList.addTravelingPartner(tp2);
        testTPList.addTravelingPartner(tp3);

        //remove tp from testTPList of size 3
        testTPList.removeTravelingPartner(tp1);

        assertEquals(2, TravelingPartnerList.count());
        assertEquals(tp2, testTPList.getTravelingPartner(0));
        assertEquals(tp3, testTPList.getTravelingPartner(1));

        //remove tp from testTPList of size 2
        testTPList.removeTravelingPartner(tp2);

        assertEquals(1, TravelingPartnerList.count());
        assertEquals(tp3, testTPList.getTravelingPartner(0));

        //remove tp from testTPList of size 1
        testTPList.removeTravelingPartner(tp3);

        assertEquals(0, TravelingPartnerList.count());
    }

    @Test
    void testAddSplitExpenseAmountOwedToMeFromNoAmountOwed() {
        testTPList.addTravelingPartner(tp1);
        testTPList.addTravelingPartner(tp2);
        testTPList.addTravelingPartner(tp3);

        //add amount to multiple tps that field amountOwedToMe = 0
        testTPList.addSplitExpenseAmountOwedToMe(33.75);
        double tp1AmountOwedToMe = testTPList.getTravelingPartner(0).amountOwedToMe;
        double tp2AmountOwedToMe = testTPList.getTravelingPartner(1).amountOwedToMe;
        double tp3AmountOwedToMe = testTPList.getTravelingPartner(2).amountOwedToMe;

        assertEquals(33.75, tp1AmountOwedToMe);
        assertEquals(33.75, tp2AmountOwedToMe);
        assertEquals(33.75, tp3AmountOwedToMe);
    }

    @Test
    void testAddSplitExpenseAmountOwedToMeFromRandomAmountOwed() {
        tp1.addAmountOwedToMe(10.98);
        tp2.addAmountOwedToMe(3);
        tp3.addAmountOwedToMe(1000);
        testTPList.addTravelingPartner(tp1);
        testTPList.addTravelingPartner(tp2);
        testTPList.addTravelingPartner(tp3);
        testTPList.addSplitExpenseAmountOwedToMe(10);
        double tp1AmountOwedToMe = testTPList.getTravelingPartner(0).amountOwedToMe;
        double tp2AmountOwedToMe = testTPList.getTravelingPartner(1).amountOwedToMe;
        double tp3AmountOwedToMe = testTPList.getTravelingPartner(2).amountOwedToMe;

        assertEquals(20.98, tp1AmountOwedToMe);
        assertEquals(13, tp2AmountOwedToMe);
        assertEquals(1010, tp3AmountOwedToMe);
    }

    @Test
    void testAddSplitExpenseAmountIBorrowedFromNoAmountBorrowed() {
        testTPList.addTravelingPartner(tp1);
        testTPList.addTravelingPartner(tp2);
        testTPList.addTravelingPartner(tp3);
        testTPList.addSplitExpenseAmountIBorrowed("Ken", 5);
        double tp1AmountIBorrowed = testTPList.getTravelingPartner(0).amountIBorrowed;
        double tp2AmountIBorrowed = testTPList.getTravelingPartner(1).amountIBorrowed;
        double tp3AmountIBorrowed = testTPList.getTravelingPartner(2).amountIBorrowed;

        assertEquals(0, tp1AmountIBorrowed);
        assertEquals(0, tp2AmountIBorrowed);
        assertEquals(5, tp3AmountIBorrowed);
    }

    @Test
    void testAddSplitExpenseAmountIBorrowedFromRandomAmountBorrowed() {
        tp1.addAmountIBorrowed(10.98);
        tp2.addAmountIBorrowed(3);
        tp3.addAmountIBorrowed(1000);
        testTPList.addTravelingPartner(tp1);
        testTPList.addTravelingPartner(tp2);
        testTPList.addTravelingPartner(tp3);
        testTPList.addSplitExpenseAmountIBorrowed("Benny", 30.77);
        testTPList.addSplitExpenseAmountIBorrowed("Emily", 100);
        double tp1AmountIBorrowed = testTPList.getTravelingPartner(0).amountIBorrowed;
        double tp2AmountIBorrowed = testTPList.getTravelingPartner(1).amountIBorrowed;
        double tp3AmountIBorrowed = testTPList.getTravelingPartner(2).amountIBorrowed;

        assertEquals(30.77 + 10.98, tp1AmountIBorrowed);
        assertEquals(103, tp2AmountIBorrowed);
        assertEquals(tp3.amountIBorrowed, tp3AmountIBorrowed);
    }


}

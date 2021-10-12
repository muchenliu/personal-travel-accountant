package model;

import java.util.LinkedList;
import java.util.List;

// Creates a list of TravelingPartner
public class TravelingPartnerList {
    private static List<TravelingPartner> travelingPartners;

    public TravelingPartnerList() {
        travelingPartners = new LinkedList<>();
    }

    //REQUIRES: tp must be an existed TravelingPartner
    //MODIFIES: this
    //EFFECTS: add the given TravelingPartner to this travelingPartners
    public void addTravelingPartner(TravelingPartner tp) {
        travelingPartners.add(tp);
    }

    //REQUIRES: tp must be in this travelingPartners
    //MODIFIES: this
    //EFFECTS: removes the given TravelingPartner from this travelingPartners
    public void removeTravelingPartner(TravelingPartner tp) {
        travelingPartners.remove(tp);
    }

    //EFFECTS: counts the size of the travelingPartners list
    public static int count() {
        return travelingPartners.size();
    }


    //MODIFIES: this
    //EFFECTS: add correspond amount of split Expense that each tp in the travelingPartners has owed the user
    public void addSplitExpenseAmountOwedToMe(double amount) {
        for (TravelingPartner next : travelingPartners) {
            next.addAmountOwedToMe(amount);
        }
    }

    //REQUIRES: name must match with one of the existed TravelingPartners in this travelingPartners
    //MODIFIES: this
    //EFFECTS: add correspond amount of split Expense that the TravelingPartners with the given name that the user has
    //         borrowed from
    public void addSplitExpenseAmountIBorrowed(String name, double amount) {
        for (TravelingPartner next : travelingPartners) {
            if (next.getName() == name) {
                next.addAmountIBorrowed(amount);
            }
        }
    }
}

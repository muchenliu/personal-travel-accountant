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
    //EFFECTS: add the given TravelingPartner to this travelingPartners, if the given tp is already in the list,
    //         then do nothing
    public void addTravelingPartner(TravelingPartner tp) {
        if (!travelingPartners.contains(tp)) {
            travelingPartners.add(tp);
        }
    }

    //REQUIRES: tp must be in this travelingPartners
    //MODIFIES: this
    //EFFECTS: removes the given TravelingPartner from this travelingPartners
    public boolean removeTravelingPartner(TravelingPartner tp) {
        if (travelingPartners.contains(tp)) {
            travelingPartners.remove(tp);
            return true;
        }
        return false;
    }

    //EFFECTS: counts the size of the travelingPartners list
    public static int count() {
        return travelingPartners.size();
    }

    //REQUIRES: this must not be an empty list
    //EFFECTS: return the tp in the list which corresponds to the input position
    public TravelingPartner getTravelingPartner(int n) {
        return travelingPartners.get(n);
    }


    //MODIFIES: this
    //EFFECTS: add correspond amount of split Expense that each tp in the travelingPartners has owed the user
    public void addSplitExpenseAmountOwedToMe(double owedAmount) {
        for (TravelingPartner next : travelingPartners) {
            next.addAmountOwedToMe(owedAmount);
        }
    }

    //REQUIRES: name must match with one of the existed TravelingPartners in this travelingPartners
    //MODIFIES: this
    //EFFECTS: add correspond amount of split Expense that the TravelingPartners with the given name that the user has
    //         borrowed from
    public void addSplitExpenseAmountIBorrowed(String name, double borrowedAmount) {
        for (TravelingPartner next : travelingPartners) {
            if (next.getName().equals(name)) {
                next.addAmountIBorrowed(borrowedAmount);
            }
        }
    }
}

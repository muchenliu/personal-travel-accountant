package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.LinkedList;
import java.util.List;

// Creates a list of TravelingPartner
public class TravelingPartnerList implements Writable {
    private static List<TravelingPartner> travelingPartnerList;

    public TravelingPartnerList() {
        travelingPartnerList = new LinkedList<>();
    }

    //REQUIRES: tp must be an existed TravelingPartner
    //MODIFIES: this
    //EFFECTS: add the given TravelingPartner to this travelingPartners, if the given tp is already in the list,
    //         then do nothing
    public void addTravelingPartner(TravelingPartner tp) {
        if (!travelingPartnerList.contains(tp)) {
            travelingPartnerList.add(tp);
        }
    }

    //REQUIRES: tp must be in this travelingPartners
    //MODIFIES: this
    //EFFECTS: removes the given TravelingPartner from this travelingPartners
    public boolean removeTravelingPartner(String givenName) {
        for (TravelingPartner next : travelingPartnerList) {
            if (next.getName().equals(givenName)) {
                travelingPartnerList.remove(next);
                return true;
            }
        }
        return false;
    }

    //getter
    public List<TravelingPartner> getTravelingPartnerList() {
        return travelingPartnerList;
    }

    //EFFECTS: counts the size of the travelingPartners list
    public static int count() {
        return travelingPartnerList.size();
    }

    //REQUIRES: this must not be an empty list
    //EFFECTS: return the tp in the list which corresponds to the input position
    public TravelingPartner getTravelingPartner(int n) {
        return travelingPartnerList.get(n);
    }


    //MODIFIES: this
    //EFFECTS: add correspond amount of split Expense that each tp in the travelingPartners has owed the user
    public void addSplitExpenseAmountOwedToMe(double owedAmount) {
        for (TravelingPartner next : travelingPartnerList) {
            next.addAmountOwedToMe(owedAmount);
        }
    }

    //REQUIRES: name must match with one of the existed TravelingPartners in this travelingPartners
    //MODIFIES: this
    //EFFECTS: add correspond amount of split Expense that the TravelingPartners with the given name that the user has
    //         borrowed from
    public void addSplitExpenseAmountIBorrowed(String name, double borrowedAmount) {
        for (TravelingPartner next : travelingPartnerList) {
            if (next.getName().equals(name)) {
                next.addAmountIBorrowed(borrowedAmount);
            }
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("traveling partners", travelingPartnerListToJson());
        return json;
    }

    //EFFECTS: returns traveling partners in this travelingPartners as a Json
    private JSONArray travelingPartnerListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (TravelingPartner tp : travelingPartnerList) {
            jsonArray.put(tp.toJson());
        }

        return jsonArray;
    }
}

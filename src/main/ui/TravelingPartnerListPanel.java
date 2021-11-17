package ui;

import model.TravelingPartner;
import model.TravelingPartnerList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

// manages the gui of adding and removing travelingPartner from the travelingPartnerList
public class TravelingPartnerListPanel extends JPanel implements ListSelectionListener {

    private TravelingPartnerList tpList;

    private JList list;
    private DefaultListModel listModel;


    public TravelingPartnerListPanel(TravelingPartnerList travelingPartnerList) {
        super(new BorderLayout());

        tpList = travelingPartnerList;

        listModel = new DefaultListModel();
        for (TravelingPartner next : tpList.getTravelingPartnerList()) {
            listModel.addElement(next.getName());
        }

        list = new JList(listModel);
//TODO

    }

    // getter
    public TravelingPartnerList getTPList() {
        return tpList;
    }

    public void valueChanged(ListSelectionEvent e) {

    }
}

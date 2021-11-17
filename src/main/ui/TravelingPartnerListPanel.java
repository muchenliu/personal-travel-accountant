package ui;

import model.TravelingPartner;
import model.TravelingPartnerList;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// event handling and list panel components are referenced ListDemoProject from ORACLE JAVA Tutorials
// https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
// manages the gui of adding and removing travelingPartner from the travelingPartnerList
public class TravelingPartnerListPanel extends JPanel implements ListSelectionListener {

    private TravelingPartnerList tpList;

    private JList list;
    private DefaultListModel listModel;

    private String addString = "Add";
    private String removeString = "Remove";
    private JButton addButton;
    private JButton removeButton;
    private JTextField travelingPartnerName;

    // EFFECTS: import userTravelingPartnerList from the TravelAccountantApp class when called,
    //          set up listScrollPane and buttonPane and pack them into the TravelingPartnerListPanel
    public TravelingPartnerListPanel(TravelingPartnerList travelingPartnerList) {
        super(new BorderLayout());

        tpList = travelingPartnerList;
        listModel = new DefaultListModel();
        for (TravelingPartner next : tpList.getTravelingPartnerList()) {
            listModel.addElement(next.getName());
        }

        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.setVisibleRowCount(5);
        list.addListSelectionListener(this);
        JScrollPane listScrollPane = new JScrollPane(list);

        addButton = new JButton(addString);
        AddListener addListener = new AddListener(addButton);
        addButton.setActionCommand(addString);
        addButton.addActionListener(addListener);
        addButton.setEnabled(false);

        removeButton = new JButton(removeString);
        RemoveListener removeListener = new RemoveListener();
        removeButton.setActionCommand(removeString);
        removeButton.addActionListener(removeListener);

        travelingPartnerName = new JTextField(10);
        travelingPartnerName.addActionListener(addListener);
        travelingPartnerName.getDocument().addDocumentListener(addListener);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.add(removeButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(travelingPartnerName);
        buttonPane.add(addButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        buttonPane.setBackground(Color.gray);

        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
    }

    // the action listener which handle the event when the addButton is clicked by the user
    // this listener is shared by the travelingPartnerName JTextField and the addButton JButton
    class AddListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private JButton button;

        public AddListener(JButton button) {
            this.button = button;
        }

        @Override
        // from ActionListener interface
        public void actionPerformed(ActionEvent e) {

        }

        @Override
        // from DocumentListener interface
        public void insertUpdate(DocumentEvent e) {

        }

        @Override
        // from DocumentListener interface
        public void removeUpdate(DocumentEvent e) {

        }

        @Override
        // from DocumentListener interface
        public void changedUpdate(DocumentEvent e) {

        }
    }

    // the action listener which handle the event when the removeButton is clicked by the user
    class RemoveListener implements ActionListener {

        @Override
        // from ActionListener interface
        public void actionPerformed(ActionEvent e) {

        }
    }

    @Override
    // from ListSelectionListener interface
    public void valueChanged(ListSelectionEvent e) {

    }

    // getter
    public TravelingPartnerList getTPList() {
        return tpList;
    }
}

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

// event handling and list panel components are referenced from ListDemoProject file from ORACLE JAVA Tutorials
// https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
// manages the gui of adding and removing travelingPartner from the travelingPartnerList
public class TravelingPartnerListPanel extends JPanel implements ListSelectionListener {

    private final TravelingPartnerList tpList;

    private JList list;
    private DefaultListModel listModel;

    private final String addString = "Add";
    private final String removeString = "Remove";
    private JButton addButton;
    private JButton removeButton;
    private JTextField travelingPartnerName;

    // EFFECTS: import userTravelingPartnerList from the TravelAccountantApp class when called,
    //          set up listScrollPane and buttonPane and pack them into the TravelingPartnerListPanel
    public TravelingPartnerListPanel(TravelingPartnerList travelingPartnerList) {
        super(new BorderLayout());

        tpList = travelingPartnerList;

        setUpList();
        JScrollPane listScrollPane = new JScrollPane(list);

        setUpButtonPaneElements();
        JPanel buttonPane = setButtonPanel();

        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
    }

    // MODIFIES: this
    // EFFECTS: set up list and listModel of TravelingPartnerListPanel
    private void setUpList() {
        listModel = new DefaultListModel();
        for (TravelingPartner next : tpList.getTravelingPartnerList()) {
            listModel.addElement(next.getName());
        }

        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.setVisibleRowCount(5);
        list.addListSelectionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: set up the elements (addButton, removeButton, JTextField) of buttonPane
    private void setUpButtonPaneElements() {
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
    }

    // EFFECTS: set up the local field buttonPane of TravelingPartnerListPanel
    private JPanel setButtonPanel() {
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
        return buttonPane;
    }

    // getter
    public TravelingPartnerList getTPList() {
        return tpList;
    }

    // the action listener which handle the event when the addButton is clicked by the user
    // this listener is shared by the travelingPartnerName JTextField and the addButton JButton
    class AddListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private final JButton button;

        public AddListener(JButton button) {
            this.button = button;
        }

        @Override
        // from ActionListener interface
        // MODIFIES: this
        // EFFECTS: if user types in an appropriate name, then insert the typed-in name to listModel at index, creates
        //          a new traveling partner with the  typed-in name and add the traveling partner to tpList
        public void actionPerformed(ActionEvent e) {
            String name = travelingPartnerName.getText();

            if (name.equals("") || listModel.contains(name)) {
                inValidInput();
            } else {

                int index = list.getSelectedIndex();            //get selected index
                if (index == -1) {                              //no selection, so insert at beginning
                    index = 0;
                } else {                                        //add after the selected item
                    index++;
                }

                String nameAdded = travelingPartnerName.getText();
                listModel.insertElementAt(nameAdded, index);

                //update the tpList which will be called by the TravelAccountantApp class later
                TravelingPartner tp = new TravelingPartner(nameAdded);
                tpList.addTravelingPartner(tp);

                //Reset the text field.
                travelingPartnerName.requestFocusInWindow();
                travelingPartnerName.setText("");

                //Select the new item and make it visible.
                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
        }

        // EFFECTS: if user types in name that is already contained in listModel, or types in non-text, then pop up an
        //          ok dialog and setSelectedIndex to 0
        private void inValidInput() {
            Toolkit.getDefaultToolkit().beep();
            //reset text field
            travelingPartnerName.requestFocusInWindow();
            travelingPartnerName.setText("");
            //select the index 0 item on the list
            list.setSelectedIndex(0);
            list.ensureIndexIsVisible(0);
            //pop up an ok dialog
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "Please type in an unique name...");
        }

        @Override
        // from DocumentListener interface
        // MODIFIES: this
        // EFFECTS: enable addButton
        public void insertUpdate(DocumentEvent e) {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        @Override
        // from DocumentListener interface
        // EFFECTS: calls handleEmptyTextField()
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        @Override
        // from DocumentListener interface
        //EFFECTS: if travelingPartnerName JTextField is not empty, then enable addButton
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                if (!alreadyEnabled) {
                    button.setEnabled(true);
                }
            }
        }

        // MODIFIES: this
        // EFFECTS: disable addButton and set to false if travelingPartnerName JTextField is empty, then return true
        //          return false if travelingPartnerName JTextField is not empty
        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }

    // the action listener which handle the event when the removeButton is clicked by the user
    class RemoveListener implements ActionListener {

        @Override
        // from ActionListener interface
        // MODIFIES: this
        // EFFECTS: remove whatever is selected and remove correspond tp from tpList
        //          if match the name selected by the user.
        //          When Nobody's left, disable removeButton.
        //          When item in the last position is selected and removed, index-- to ensure the index is visible
        public void actionPerformed(ActionEvent e) {
            int index = list.getSelectedIndex();

            String name = listModel.getElementAt(index).toString();
            listModel.remove(index);
            TravelingPartner removeTP = new TravelingPartner(name);
            tpList.removeTravelingPartner(removeTP);

            int size = listModel.getSize();
            if (size == 0) {
                removeButton.setEnabled(false);
            } else {
                if (index == size) {
                    index--;
                }

                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
        }
    }

    @Override
    // from ListSelectionListener interface
    // MODIFIES: this
    // EFFECTS: when the selected value is not adjusting, disable removeButton when nothing is selected; enable the
    //          removeButton when something is selected
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {

            if (list.getSelectedIndex() == -1) {
                //No selection, disable fire button.
                removeButton.setEnabled(false);

            } else {
                //Selection, enable the fire button.
                removeButton.setEnabled(true);
            }
        }
    }
}

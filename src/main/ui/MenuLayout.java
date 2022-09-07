package ui;

import model.Event;
import model.EventLog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//referenced to Oracle Java Documentation FlowLayoutDemo.java file
//https://docs.oracle.com/javase/tutorial/uiswing/examples/layout/FlowLayoutDemoProject/src/layout/FlowLayoutDemo.java
public class MenuLayout extends JFrame {
    JButton expenseButton;
    JButton travelingPartnerButton;
    JButton cashButton;
    JButton quitButton;
    FlowLayout menuLayout = new FlowLayout();

    TravelAccountantApp current;
    boolean wantQuit = false;
    public MenuLayout(String name, TravelAccountantApp toBeModified) {
        super(name);
        current = toBeModified;
    }

    public void setUpMenuPane(Container pane) {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(menuLayout);
        menuLayout.setAlignment(FlowLayout.TRAILING);

        //set up buttons and set up action
        expenseButton = new JButton("expense");
        expenseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                current.runExpense();
            }
        });
        travelingPartnerButton = new JButton("traveling partner");
        travelingPartnerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                current.runTravelingPartner();
            }
        });
        cashButton = new JButton("cash");
        cashButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                current.runCash();
            }
        });
        quitButton = new JButton("quit");
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                current.processSaveFile();
                PopUpGifManager pop = new PopUpGifManager();
                pop.displayGoodbyeImage();

                System.out.println("\n");
                for (Event eventLog : EventLog.getInstance()) {
                    System.out.println(eventLog.toString());
                }
                JFrame textFrame = new JFrame("Click on Exit to end application");
                JButton exitButton = new JButton("Exit");
                exitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                });
                JPanel panel = new JPanel();
                panel.add(exitButton, BorderLayout.CENTER);
                textFrame.add(panel);
                textFrame.setSize(300, 100);
                textFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                textFrame.setVisible(true);
            }
        });

        menuPanel.add(expenseButton);
        menuPanel.add(travelingPartnerButton);
        menuPanel.add(cashButton);
        menuPanel.add(quitButton);
        menuPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        pane.add(menuPanel, BorderLayout.CENTER);
    }


    public static void createAndShowMenu(TravelAccountantApp toBeModified) {
        MenuLayout frame = new MenuLayout("Please select from the following categories:", toBeModified);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUpMenuPane(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);

        //keep showing menu until user click on quit
        while (!frame.wantQuit) {

        }
    }

}

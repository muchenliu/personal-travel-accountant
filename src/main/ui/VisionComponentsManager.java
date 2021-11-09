package ui;

import java.awt.*;
import javax.swing.*;

// Manage the welcome and goodbye pop-up gif when application runs
public class VisionComponentsManager extends JFrame {
    private ImageIcon welcomeImage;
    private JLabel welcomeLabel;
    private ImageIcon goodbyeImage;
    private JLabel goodbyeLabel;

    //EFFECTS: set fields to correct image files and attach JLabel to ImageIcon (load images)
    public VisionComponentsManager() {
        String sep = System.getProperty("file.separator");
        welcomeImage = new ImageIcon(System.getProperty("user.dir") + sep + "gif"
                + sep + "Welcome Minions.gif");
        goodbyeImage = new ImageIcon(System.getProperty("user.dir") + sep + "gif"
                + sep + "Goodbye Minions.gif");

        welcomeLabel = new JLabel(welcomeImage);
        goodbyeLabel = new JLabel(goodbyeImage);
    }

    //MODIFIES: this
    //EFFECTS: Layouts the welcomeImage and set pop-up window's title
    public void displayWelcomeImage() {
        setLayout(new FlowLayout());
        add(welcomeLabel);
        setFrame("Welcome Back!!");
    }

    //MODIFIES: this
    //EFFECTS: First remove the welcomeImage then layouts the goodbyeImage and set pop-up window's title
    public void displayGoodbyeImage() {
        setLayout(new FlowLayout());
        remove(welcomeLabel);
        add(goodbyeLabel);
        setFrame("Bye bye!!");
    }

    //MODIFIES: this
    //EFFECTS: set up and display the window
    private void setFrame(String title) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        pack();
        setTitle(title);
    }

}

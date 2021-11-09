package ui;

import java.awt.*;
import javax.swing.*;

// Manage the welcome and goodbye pop-up gif when application runs
public class VisionComponentsManager extends JFrame {
    private ImageIcon welcomeImage;
    private JLabel welcomeLabel;
    private ImageIcon goodbyeImage;
    private JLabel goodbyeLabel;

    public VisionComponentsManager() {
        String sep = System.getProperty("file.separator");
        welcomeImage = new ImageIcon(System.getProperty("user.dir") + sep + "gif"
                + sep + "Welcome Minions.gif");
        goodbyeImage = new ImageIcon(System.getProperty("user.dir") + sep + "gif"
                + sep + "Goodbye Minions.gif");

        welcomeLabel = new JLabel(welcomeImage);
        goodbyeLabel = new JLabel(goodbyeImage);
    }

    public void displayWelcomeImage() {
        setLayout(new FlowLayout());
        add(welcomeLabel);
        setFrame("Welcome Back!!");
    }

    public void displayGoodbyeImage() {
        setLayout(new FlowLayout());
        remove(welcomeLabel);
        add(goodbyeLabel);
        setFrame("Bye bye!!");
    }

    private void setFrame(String title) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        pack();
        setTitle(title);
    }

}

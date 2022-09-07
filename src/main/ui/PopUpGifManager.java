package ui;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;

// Manage the welcome and goodbye pop-up gif when application runs
public class PopUpGifManager extends JFrame {

    private String sep = System.getProperty("file.separator");
    private ImageIcon welcomeImage = new ImageIcon(System.getProperty("user.dir") + sep + "gif"
            + sep + "Welcome Minions.gif");
    private ImageIcon goodbyeImage = new ImageIcon(System.getProperty("user.dir") + sep + "gif"
            + sep + "byebye.jpeg");

    //EFFECTS: set fields to correct image files and attach JLabel to ImageIcon (load images)
    public PopUpGifManager() {

    }

    //MODIFIES: this
    //EFFECTS: Layouts the welcomeImage and set pop-up window's title
    public void displayWelcomeImage() {
        setLayout(new FlowLayout());
        JLabel welcomeLabel = new JLabel(welcomeImage);
        add(welcomeLabel);
        setFrame("Welcome Back!!");
    }

    //MODIFIES: this
    //EFFECTS: Displays the goodbyeImage and set pop-up window's title
    public void displayGoodbyeImage() {
        setLayout(new FlowLayout());
        JLabel goodbyeLabel = new JLabel(goodbyeImage);
        add(goodbyeLabel);
        setFrame("Bye bye!!");
    }

    //MODIFIES: this
    //EFFECTS: set up and display the window
    private void setFrame(String title) {
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setVisible(true);
        pack();
        setTitle(title);
    }

}

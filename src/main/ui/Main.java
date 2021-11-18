package ui;

public class Main {
    public static void main(String[] args) {
        new TravelAccountantApp();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted Exception is caught");
        }
        System.exit(0);
    }
}


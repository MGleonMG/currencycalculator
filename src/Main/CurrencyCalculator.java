package Main;

import GUI.GUI;
import Menu.Menu;

public class CurrencyCalculator {
    public static void main(String[] args) {
        System.out.println("\n\nStarting...  " + GUI.title + " (" + GUI.version + ")");
        GUI.createGUI();
        new Menu();
    }
}
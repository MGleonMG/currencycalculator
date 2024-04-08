package Main;

import java.util.Locale;
import java.util.Scanner;

import Data.Calculations;
import GUI.GUI;

public class CurrencyCalculator {
    public static void main(String[] args) {
        System.out.println("\n\nStarting...  " + GUI.title + " (" + GUI.version + ")");
        GUI.drawGUI();

        testCurrencyBackend();
    }

    private static void testCurrencyBackend() {
        @SuppressWarnings("resource")
        Scanner input = new Scanner(System.in).useLocale(Locale.US);
        double amount;
        String cur1, cur2;

        // while (true) {
        System.out.println("\nWelche Währung möchtest du konvertieren?  (zur Zeit Möglich: EUR, USD)");
        cur1 = input.nextLine();

        System.out.println("..und zu welcher Währung?");
        cur2 = input.nextLine();

        System.out.println("Wieviel " + cur1 + " möchtest du konvertieren? (z.B. '20.30')");
        amount = input.nextDouble();

        System.out.println("next double is " + amount);

        Calculations.convertCurrencies(cur1, cur2, amount);
        // }
    }
}
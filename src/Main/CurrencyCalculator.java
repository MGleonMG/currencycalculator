package Main;

import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

import GUI.GUI;
import Utils.Utils;
import Utils.Data.Calculations;

public class CurrencyCalculator {
    public static void main(String[] args) {
        System.out.println("\n\nStarting...  " + GUI.title + " (" + GUI.version + ")");
        GUI.drawGUI();

        for (Map.Entry<String, String> entry : Utils.getAllCurrencies()) {
            String key = entry.getKey();
            String value = entry.getValue();

            System.out.println(value + " (" + key + ")");
        }
    }

    @SuppressWarnings("unused")
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
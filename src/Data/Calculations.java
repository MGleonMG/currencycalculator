package Data;

import Data.Utils.Utils;

public class Calculations {
    public static void convertCurrencies(String baseCur, String targetCur, double amount) {
        ExchangeRateFetcher.fetchExchangeRate(baseCur, targetCur);
        
        if (ExchangeRateFetcher.latestRate != 0.0) {
            System.out.println("\n========================================================================\n" + 
            amount + " " + baseCur + " sind " +
            Utils.convertDoubleToTwoDecimals(amount * ExchangeRateFetcher.latestRate, 2) + " " + targetCur + 
            " bei einem Wechselkurs von " + ExchangeRateFetcher.latestRate + 
            "\n========================================================================");

        } else {
            System.err.println("Ein Fehler ist aufgetreten...\nProgramm wird beendet.");
            System.exit(0);
        }
    }
}

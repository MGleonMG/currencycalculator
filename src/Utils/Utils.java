package Utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import GUI.GUI;
import Utils.Data.Calculations;
import Utils.Data.ExchangeRateFetcher;

import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

public class Utils {

    public static double adjustDecimal(double x, int decimalPlaces) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');

        StringBuilder pattern = new StringBuilder("#.");
        for (int i = 0; i < decimalPlaces; i++) {
            pattern.append("#");
        }

        DecimalFormat dfConverted = new DecimalFormat(pattern.toString(), symbols);

        return Double.parseDouble(dfConverted.format(x));
    }

    public static Set<Entry<String, String>> getAllCurrencies() {
        Map<String, String> currencies = new HashMap<>();

        for (Currency currency : Currency.getAvailableCurrencies()) {
            String currencyCode = currency.getCurrencyCode();
            String displayName = currency.getDisplayName();
            currencies.put(currencyCode, displayName);
        }

        return currencies.entrySet();
    }

    public static void runCalcThread() {
        // lambda funktion in der runCalcThread() funktion um asynchrones ausführen zu
        // ermöglichen (=> GUI kann sich dadurch updaten)
        Thread thread = new Thread(() -> {
            GUI.displayAsLoading(true);

            /*
             * 
             * TODO: Code Optimization (GUI.result etc.)
             * 
             */
            String resultAsString = ""
                    + Calculations.convertCurrencies(GUI.baseCurResult, GUI.targetCurResult, GUI.inputValueAsDouble);

            GUI.setOuput("Ergebnis ist " + resultAsString.replace('.', ',') +
                    "<br>Web fetch time: " + ExchangeRateFetcher.getLastFetchTime() + "ms");

            GUI.displayAsLoading(false);
        });

        thread.start();
    }

    public static void copyToClipboard() {
        double umrechnung = Calculations.calculation;
        if (umrechnung != 0.0) {
            String myString = String.valueOf(umrechnung);
            StringSelection stringSelection = new StringSelection(myString);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
        }
    }
}

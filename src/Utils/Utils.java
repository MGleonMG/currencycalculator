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

            String resultAsString = "" + Calculations.convertCurrencies("EUR", "USD", 29.99);
            GUI.setOuput("Ergebnis ist " + resultAsString.replace('.', ',') +
                    "<br>Web fetch time: " + ExchangeRateFetcher.getLastFetchTime() + "ms");

            GUI.displayAsLoading(false);
        });

        thread.start();
    }
}

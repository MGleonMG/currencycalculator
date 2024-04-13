package Utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;

import java.util.Set;

import GUI.GUI;
import GUI.Errors.ErrorDisplay;
import GUI.GUI.Theme;
import Utils.Data.Calculations;
import Utils.Data.ExchangeRateFetcher;
import Utils.Data.UserSettings.Settings;
import netscape.javascript.JSException;

import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.FileWriter;
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

            String resultAsString = "" + Calculations.convertCurrencies("EUR", "USD", 29.99);
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

    public static void runFirstTimeSetupCheck() {
        File settingsFile = new File(Settings.getFilePath());
        if (!settingsFile.exists()) {
            try {
                new File(Settings.getFolderPath()).mkdir();
                settingsFile.createNewFile();
                Gson configHandler = new GsonBuilder().create();
                HashMap<String, String> setting = new HashMap<>();
                setting.put("AppTheme", /* GUI.Theme.DARK_MODE */ "test");
                FileWriter writer = new FileWriter(Settings.getFilePath());
                configHandler.toJson(setting, writer);

            } catch (Exception e) {
                e.printStackTrace();
                ErrorDisplay.throwErrorPopup(e.getMessage());
            }
        }
    }
}

package Utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import GUI.GUI;

import java.util.Set;

import Utils.Data.Calculations;

import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

/*
 * In dieser Klasse sind alle Tools (Werkzeuge) verfügbar
 * Diese werden in anderen Klassen verwendet
 */
public class Utils {

    public static boolean failed;

    /*
     * Diese Methode stellt das End ergebnis auf zwei (nach) Kommastellen um
     * 
     * bsp: 12.04405
     * -> 12.04
     */
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

    /*
     * Ein Set von Einträgen mit jeweils 2 Strings als Paaren
     * werden zurück gegeben welche immer pro Eintrag eine
     * Währung und dessen 3 stelligen ISO Code enthalten
     */
    public static Set<Entry<String, String>> getAllCurrencies() {
        Map<String, String> currencies = new HashMap<>();

        for (Currency currency : Currency.getAvailableCurrencies()) {
            String currencyCode = currency.getCurrencyCode();
            String displayName = currency.getDisplayName();
            currencies.put(currencyCode, displayName);
        }

        return currencies.entrySet();
    }

    @SuppressWarnings("unchecked")
    public static void refreshCurrencyDropdowns() {
        GUI.getComboBoxes()[0].removeAllItems();
        GUI.getComboBoxes()[1].removeAllItems();

        for (Map.Entry<String, String> currency : getAllCurrencies()) {
            String isoCode = currency.getKey();
            String currencyName = currency.getValue();

            GUI.getComboBoxes()[0].addItem(currencyName + " (" + isoCode + ")");
            GUI.getComboBoxes()[1].addItem(currencyName + " (" + isoCode + ")");
        }
    }

    /*
     * Diese Methode kopiert das Ergebnis in den Clipboard des Benutzers
     * siehe src\GUI\GUI.java\addCopyOutputButton()
     */
    public static void copyToClipboard() {
        double umrechnung = Calculations.finalResult;
        if (umrechnung != 0.0) {
            String myString = String.valueOf(umrechnung);
            StringSelection stringSelection = new StringSelection(myString);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
        }
    }

    /*
     * Diese Methode überprüft, ob es in der Klammer Zahlen gibt.
     * Je nach Inhalt gibt dies einen Wert zurück.
     */
    public static boolean containsDigit(String str) {
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    public static boolean getFailed() {

        return failed;
    }
}

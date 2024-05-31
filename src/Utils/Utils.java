package Utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import GUI.Components.InputOutput;
import Utils.Data.Calculations;
import Utils.Data.Filter;

import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

/*
 * In dieser Klasse befinden sich Hilfsfunktionen
 */
public class Utils {

    /*
     * Diese Methode stellt das Endergebnis auf
     * eine beliebige Anzahl an (nach) Kommastellen um
     * Bsp: 12.04405 wird 12.04
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

            if (!Filter.getCurrencyFilter().contains(currencyCode)) {
                currencies.put(currencyCode, displayName);
            }
        }

        return currencies.entrySet();
    }

    /*
     * Diese Methode leert die Dropdowns und befüllt sie wieder mit allen Währungen
     * damit bei einer Sprachänderung diese auch in der korrekten Sprache angegeben
     * sind
     */
    @SuppressWarnings("unchecked")
    public static void refreshCurrencyDropdowns() {
        InputOutput.getComboBoxes()[0].removeAllItems();
        InputOutput.getComboBoxes()[1].removeAllItems();

        for (Map.Entry<String, String> currency : getAllCurrencies()) {
            String isoCode = currency.getKey();
            String currencyName = currency.getValue();

            InputOutput.getComboBoxes()[0].addItem(currencyName + " (" + isoCode + ")");
            InputOutput.getComboBoxes()[1].addItem(currencyName + " (" + isoCode + ")");
        }
    }

    /*
     * Diese Methode kopiert das Ergebnis in das Clipboard des Benutzers
     * siehe src\GUI\GUI.java\addCopyOutputButton()
     */
    public static void copyToClipboard() {
        if (Calculations.finalResult != 0.0) {
            String myString = String.valueOf(Calculations.finalResult);
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
}

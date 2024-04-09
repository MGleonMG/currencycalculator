package Main;

import java.util.Map;
import GUI.GUI;
import Utils.Utils;
import Utils.Data.Calculations;
import Utils.Data.ExchangeRateFetcher;
import javax.swing.JComboBox;

public class CurrencyCalculator {

    // Erstellt eine Dropdown Liste der Currency
    // spezifisch fuer einen String -> vermeidet somit Fehler
    static JComboBox<String> dropdown = new JComboBox<>();

    public static void main(String[] args) {
        GUI.drawGUI();

        for (Map.Entry<String, String> currency : Utils.getAllCurrencies()) {
            String isoCode = currency.getKey();
            String currencyName = currency.getValue();

            dropdown.addItem(currencyName + " (" + isoCode + ")");
        }

        // TODO: remove later
        // am besten nochmal gemeinsam klaeren, wie wir das durchsetzen
        // Daten sind Temporaer da
        String baseCurrency = "USD";
        String targetCurrency = "EUR";
        double amount = 10.00;

        Calculations.convertCurrencies(baseCurrency, targetCurrency, amount);
        ExchangeRateFetcher.getLastFetchTime();
    }
}
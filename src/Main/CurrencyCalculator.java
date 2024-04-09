package Main;

import GUI.GUI;
import Utils.Data.Calculations;
import Utils.Data.ExchangeRateFetcher;

public class CurrencyCalculator {

    // Erstellt eine Dropdown Liste der Currency
    // spezifisch fuer einen String -> vermeidet somit Fehler

    public static void main(String[] args) {
        GUI.drawGUI();


        //ExchangeRateFetcher.getLastFetchTime();  In GUI bei ausgabe von wert

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
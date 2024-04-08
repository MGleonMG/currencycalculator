package Main;

import GUI.GUI;
import Utils.Data.ExchangeRateFetcher;

public class CurrencyCalculator {

    public static void main(String[] args) {
        GUI.drawGUI();
        System.out.println("\n");
        ExchangeRateFetcher.fetchExchangeRate("EUR", "");

        // TODO: für @Jonas
        // for (Map.Entry<String, String> currency : Utils.getAllCurrencies()) {
        // String isoCode = currency.getKey();
        // String currencyName = currency.getValue();

        // dropdown.addItem(currencyName + " (" + isoCode + ")");
        // }

        // TODO: für @Jonas
        // Calculations.convertCurrencies(baseCurrency, targetCurrency, amount);
        // ExchangeRateFetcher.getLastFetchTime();
    }
}
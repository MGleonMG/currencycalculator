package Main;

import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JComboBox;

import GUI.GUI;
import Utils.Utils;
import Utils.Data.Calculations;

@SuppressWarnings("unused")
public class CurrencyCalculator {

    public static void main(String[] args) {
        GUI.drawGUI();

        // TODO: für @Jonas
        // for (Map.Entry<String, String> currency : Utils.getAllCurrencies()) {
        // String isoCode = currency.getKey();
        // String currencyName = currency.getValue();

        // dropdown.addItem(currencyName + " (" + isoCode + ")");
        // }

        // TODO: für @Jonas
        // Calculations.convertCurrencies(baseCurrency, targetCurrency, amount);
    }
}
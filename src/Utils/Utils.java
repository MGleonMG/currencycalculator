package Utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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

}

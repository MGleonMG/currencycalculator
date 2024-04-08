package Utils.Data;

import Utils.Utils;

public class Calculations {
    public static double convertCurrencies(String baseCur, String targetCur, double amount) {
        ExchangeRateFetcher.fetchExchangeRate(baseCur, targetCur);

        if (ExchangeRateFetcher.latestRate != 0.0) {
            return Utils.adjustDecimal(amount * ExchangeRateFetcher.latestRate, 2);
        } else {
            return 0.0;
        }
    }
}

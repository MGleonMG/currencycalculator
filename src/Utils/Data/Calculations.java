package Utils.Data;

import Utils.Utils;

public class Calculations {

    // @TODO: Leon possibly Cleanup Code

    public static double calculation;

    public static double convertCurrencies(String baseCur, String targetCur, double amount) {
        ExchangeRateFetcher.fetchExchangeRate(baseCur, targetCur);

        calculation = Utils.adjustDecimal(amount * ExchangeRateFetcher.latestRate, 2);
        
        if (ExchangeRateFetcher.latestRate != 0.0) {
            return calculation;
        } else {
            return 0.0;
        }
    }
}

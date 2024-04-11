package Utils.Data;

import Utils.Utils;

public class Calculations {

    public static double rechnung;

    public static double convertCurrencies(String baseCur, String targetCur, double amount) {
        ExchangeRateFetcher.fetchExchangeRate(baseCur, targetCur);

        rechnung = Utils.adjustDecimal(amount * ExchangeRateFetcher.latestRate, 2);
        
        if (ExchangeRateFetcher.latestRate != 0.0) {
            return rechnung;
        } else {
            return 0.0;
        }
    }
}

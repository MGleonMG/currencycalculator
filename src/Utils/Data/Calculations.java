package Utils.Data;

import Utils.Utils;

/*
 * Diese Klasse ist für die Rechnung zuständig 
 */
public class Calculations {
    public static double finalResult;

    /*
     * Diese Methode rechnet die Währungen aus und gibt gegebenfalls das Endergebnis
     * zurück
     */
    public static double convertCurrencies(String baseCur, String targetCur, double amount) {
        ExchangeRateFetcher.fetchExchangeRate(baseCur, targetCur);

        finalResult = Utils.adjustDecimal(amount * ExchangeRateFetcher.getLatestExchangeRate(), 2);

        if (ExchangeRateFetcher.getLatestExchangeRate() != 0.0) {
            return finalResult;
        } else {
            return 0.0;
        }
    }
}

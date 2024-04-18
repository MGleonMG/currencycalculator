package Utils.Data;

import Utils.Utils;

/*
 * Diese Klasse ist für die Rechnung zuständig 
 */
public class Calculations {

    // @TODO: Leon possibly Cleanup Code

    public static double finalResult;

    /*
     * Diese Methode rechnet die Währungen aus und gibt gegebenfalls das Endergebnis
     * zurück
     */
    public static double convertCurrencies(String baseCur, String targetCur, double amount) {
        ExchangeRateFetcher.fetchExchangeRate(baseCur, targetCur);

        finalResult = Utils.adjustDecimal(amount * ExchangeRateFetcher.latestRate, 2);

        if (ExchangeRateFetcher.latestRate != 0.0) {
            return finalResult;
        } else {
            return 0.0;
        }
    }
}

package Utils.Data;

import Utils.Utils;

/*
 * Diese Klasse ist für die Rechnung zu ständig 
 */
public class Calculations {

    // @TODO: Leon possibly Cleanup Code

    public static double endErgebnis;

    /*
     * Diese Methode rechnet die Währungen aus
     */
    public static double convertCurrencies(String baseCur, String targetCur, double amount) {
        ExchangeRateFetcher.fetchExchangeRate(baseCur, targetCur);

        endErgebnis = Utils.adjustDecimal(amount * ExchangeRateFetcher.latestRate, 2);

        if (ExchangeRateFetcher.latestRate != 0.0) {
            return endErgebnis;
        } else {
            return 0.0;
        }
    }
}

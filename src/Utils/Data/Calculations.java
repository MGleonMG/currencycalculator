package Utils.Data;

import GUI.Components.InputOutput;
import Utils.Utils;
import lang.Language;

/*
 * Diese Klasse ist für Berechnungen zuständig 
 */
public class Calculations {
    public static double finalResult;

    /*
     * Diese Methode rechnet den Betrag mit dem
     * Wechselkurs aus und gibt das Endergebnis zurück
     */
    private static double convertCurrencies(String baseCur, String targetCur, double amount) {
        ExchangeRateFetcher.fetchExchangeRate(baseCur, targetCur);

        finalResult = Utils.adjustDecimal(amount * ExchangeRateFetcher.getLatestExchangeRate(), 2);

        if (ExchangeRateFetcher.getLatestExchangeRate() != 0.0) {
            return finalResult;
        } else {
            return 0.0;
        }
    }

    /*
     * Diese Methode ist der Hauptprozess für die Rechnung
     */
    public static void runThreadedCalculation() {
        /*
         * Berechnung wird asynchron in einem Thread ausgeführt
         * um Änderungen im GUI zuzulassen
         */
        Thread thread = new Thread(() -> {
            InputOutput.displayAsLoading(true);

            convertCurrencies(InputOutput.getBaseCur(), InputOutput.getTargetCur(), InputOutput.getAmount());

            if (ExchangeRateFetcher.hasFailed() == false) {
                InputOutput.setOutput(Language.getLangStringByKey("outputComponent1") + " " + InputOutput.getAmount()
                        + " "
                        + InputOutput.getBaseCur()
                        + "\n" +
                        Language.getLangStringByKey("outputComponent2") + " " + finalResult + " "
                        + InputOutput.getTargetCur()
                        + "\n"
                        +
                        Language.getLangStringByKey("outputComponent3") + " "
                        + ExchangeRateFetcher.getLatestExchangeRate()
                        + "\n" +
                        Language.getLangStringByKey("outputComponent4") + " " + ExchangeRateFetcher.getLastFetchTime()
                        + "ms");
            } else {
                ExchangeRateFetcher.clearDataOnError();
            }

            InputOutput.displayAsLoading(false);
            ExchangeRateFetcher.clearFailedStatus();
        });

        thread.start();
    }
}
package Utils.Data;

import javax.swing.Popup;

import GUI.GUI;
import GUI.Popups.PopupDisplay;
import Utils.Utils;

/*
 * Diese Klasse ist für Rechnung zuständig 
 */
public class Calculations {
    public static double finalResult;

    // Diese Methode rechnet die Währungen aus und gibt das Endergebnis zurück
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
        // lambda funktion in der runCalcThread() funktion um asynchrones ausführen zu
        // ermöglichen (=> GUI kann sich dadurch updaten)
        Thread thread = new Thread(() -> {
            GUI.displayAsLoading(true);

            convertCurrencies(GUI.getBaseCur(), GUI.getTargetCur(), GUI.getAmount());

            if (ExchangeRateFetcher.hasFailed() == false) {
                GUI.setOutput("Eingetippt: " + GUI.getAmount() + " " + GUI.getBaseCur() + "\n" +
                        "Das Ergebnis ist " + finalResult + " " + GUI.getTargetCur() + "\n" +
                        "Wechselkurs: " + ExchangeRateFetcher.getLatestExchangeRate() + "\n" +
                        "Wechselkurs herausgefunden in " + ExchangeRateFetcher.getLastFetchTime() + "ms");
            } else {
                ExchangeRateFetcher.clearDataOnError();
            }
            GUI.displayAsLoading(false);
        });

        thread.start();
    }
}

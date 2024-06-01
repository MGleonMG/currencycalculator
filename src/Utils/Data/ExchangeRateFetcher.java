package Utils.Data;

import java.net.URL;
import java.net.UnknownHostException;
import java.util.Scanner;

import GUI.Components.InputOutput;
import GUI.Popups.PopupDisplay;
import Utils.Utils;
import lang.Language;

/*
 * Diese Klasse ruft die Wechselkurse von Google Finances ab
 */
public class ExchangeRateFetcher {
    private static double latestExchangerate;
    private static long lastStartMillis, lastEndMillis;
    private static boolean hasFailed = false;

    // "Webscraper" um Daten von 'google.com/finance/' zu nutzen
    @SuppressWarnings("deprecation")
    public static void fetchExchangeRate(String baseCur, String targetCur) {
        try {
            lastStartMillis = System.currentTimeMillis();

            // Roher HTML code als String von 'google.com/finance/' ..
            Scanner webScanner = new Scanner(
                    new URL("https://www.google.com/finance/quote/" + baseCur + "-" + targetCur).openStream(), "UTF-8");
            String htmlOutput = webScanner.useDelimiter("\\A").next();

            /*
             * ..stelle finden an der der Wechselkurs angegeben ist
             * und den String darauf zuschneiden
             */
            htmlOutput = htmlOutput.substring(htmlOutput.indexOf("YMlKec fxKbKc\">"));
            htmlOutput = htmlOutput.substring(15, htmlOutput.indexOf("<"));

            lastEndMillis = System.currentTimeMillis();

            latestExchangerate = Utils.adjustDecimal(Double.parseDouble(htmlOutput), 4);

            webScanner.close();

            // Die Catch-Blöcke geben dem Nutzer die jeweilige Fehlermeldung zurück.
        } catch (UnknownHostException uhExc) {
            PopupDisplay.throwErrorPopup(Language.getLangStringByKey("error_exchangeratefetcher_uhe"),
                    uhExc.getMessage());
            clearDataOnError();

        } catch (StringIndexOutOfBoundsException oobExc) {
            PopupDisplay.throwErrorPopup(
                    Language.getLangStringByKey("error_exchangeratefetcher_oobexc"),
                    Language.getLangStringByKey("error_exchangeratefetcher_oobexc_code"));
            clearDataOnError();

        } catch (Exception exc) {
            PopupDisplay.throwErrorPopup(Language.getLangStringByKey("error_exchangeratefetcher_unknown"),
                    exc.getMessage());
            clearDataOnError();
        }
    }

    // Berechnet die letzte fetch time von google finances und gibt diese zurück
    public static long getLastFetchTime() {
        return lastEndMillis - lastStartMillis;
    }

    // Gibt den zu letzt gespeicherten Wechselkurs zurück
    public static double getLatestExchangeRate() {
        return latestExchangerate;
    }

    /*
     * Diese Methode setzt bei einer Fehlermeldung die
     * Daten zurück damit keine 0 ausgegeben werden
     */
    public static void clearDataOnError() {
        hasFailed = true;
        latestExchangerate = 0.0;
        lastStartMillis = 0;
        lastEndMillis = 0;
        InputOutput.setOutput("");
    }

    public static boolean hasFailed() {
        return hasFailed;
    }

    public static void clearFailedStatus() {
        hasFailed = false;
    }
}
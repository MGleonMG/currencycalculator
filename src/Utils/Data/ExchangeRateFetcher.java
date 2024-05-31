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
    private static double latestRate;
    private static long lastStartMillis, lastEndMillis;
    private static Object getLastFetchTime;
    private static boolean hasFailed = false;

    // "Webscraper" um Daten von 'google.com/finance/' zu nutzen
    @SuppressWarnings("deprecation")
    public static void fetchExchangeRate(String baseCur, String targetCur) {
        try {
            lastStartMillis = System.currentTimeMillis();

            // Roher HTML code als String von 'google.com/finance/'
            Scanner webScanner = new Scanner(
                    new URL("https://www.google.com/finance/quote/" + baseCur + "-" + targetCur).openStream(), "UTF-8");
            String htmlOutput = webScanner.useDelimiter("\\A").next();

            // Stelle finden an der der Wechselkurs angegeben ist
            // und den String darauf zuschneiden
            htmlOutput = htmlOutput.substring(htmlOutput.indexOf("YMlKec fxKbKc\">"));
            htmlOutput = htmlOutput.substring(15, htmlOutput.indexOf("<"));

            lastEndMillis = System.currentTimeMillis();

            latestRate = Utils.adjustDecimal(Double.parseDouble(htmlOutput), 4);

            webScanner.close();

            /*
             * Die Catch Methoden geben dem Endnutzer die jeweilige Fehlermeldung zurück.
             * Auf genauere Beschreibung wird verzichtet da die Strings
             * für die Popup Message eine ausreichende Bezeichnung bieten.
             */
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

    public static String getLastFetchTimeAsString() {
        String fetchTimeAsString = String.valueOf(getLastFetchTime);
        return fetchTimeAsString;
    }

    // Gibt den zu letzt gespeicherten Wechselkurs zurück
    public static double getLatestExchangeRate() {
        return latestRate;
    }

    /*
     * Diese Methode setzt bei einer Fehlermeldung die
     * Daten zurück damit keine Nullen ausgegeben werden
     */
    public static void clearDataOnError() {
        hasFailed = true;
        latestRate = 0.0;
        lastStartMillis = 0;
        lastEndMillis = 0;
        InputOutput.setOutput("");
    }

    public static boolean getFailed() {
        return hasFailed;
    }

    public static void setFailed() {
        hasFailed = false;
    }
}
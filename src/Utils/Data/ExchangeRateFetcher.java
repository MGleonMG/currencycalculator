package Utils.Data;

import java.net.URL;
import java.net.UnknownHostException;
import java.util.Scanner;

import GUI.Popups.PopupDisplay;
import Utils.Utils;

/*
 * Diese Klasse ruft die Wechselkurse von Google Finances ab
 */
public class ExchangeRateFetcher {
    private static double latestRate;
    private static long lastStartMillis, lastEndMillis;
    private static Object getLastFetchTime;

    /*
     * Um die Währungen auszurechnen, braucht die Methode die Währungen die der
     * Enduser umwandeln möchte
     */
    // Webscraper um Daten von 'google.com/finance/' zu nutzen
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
             * Die catch Methoden geben dem Enduser die jeweilige Fehlermeldung zurück
             */
        } catch (UnknownHostException uhExc) {
            PopupDisplay.throwErrorPopup("Es konnte keine Verbindung zum Server hergestellt werden");
            uhExc.printStackTrace();
            clearDataOnError();

        } catch (StringIndexOutOfBoundsException oobExc) {
            // Fehler aufgetreten.
            PopupDisplay.throwErrorPopup(
                    "Einer der Währungen scheint nicht zu existieren oder es ist ein Fehler beim fetchen der Daten aufgetreten");
            oobExc.printStackTrace();
            clearDataOnError();

        } catch (Exception exc) {
            System.err.print("[UNKNOWN ERROR] ");
            PopupDisplay.throwErrorPopup("Ein unbekannter Fehler ist aufgetreten");
            exc.printStackTrace();
            clearDataOnError();

        }
    }

    public static long getLastFetchTime() {
        return lastEndMillis - lastStartMillis;
    }

    public static String getLastFetchTimeAsString() {
        String fetchTimeAsString = String.valueOf(getLastFetchTime);
        return fetchTimeAsString;
    }

    public static double getLatestExchangeRate() {
        return latestRate;
    }

    /*
     * Diese Methode setzt bei einer Fehlermeldung die Daten zurück
     */
    private static void clearDataOnError() {
        latestRate = 0.0;
        lastStartMillis = 0;
        lastEndMillis = 0;
    }
}

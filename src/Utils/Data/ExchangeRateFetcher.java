package Utils.Data;

import java.net.URL;
import java.net.UnknownHostException;
import java.util.Scanner;

import GUI.ErrorDisplay;
import Utils.Utils;

public class ExchangeRateFetcher {
    public static double latestRate;
    private static long lastStartMillis, lastEndMillis;

    // Webscraper um Daten von 'google.com/finance/' zu nutzen
    // TODO: @Leon close scanner
    @SuppressWarnings({ "resource" })
    public static void fetchExchangeRate(String baseCur, String targetCur) {
        try {
            System.out.println("Fetching Exchange rate...");
            lastStartMillis = System.currentTimeMillis();
            // Roher HTML code als String von 'google.com/finance/'
            String htmlOutput = new Scanner(
                    new URL("https://www.google.com/finance/quote/" + baseCur + "-" + targetCur).openStream(), "UTF-8")
                    .useDelimiter("\\A").next();

            // Stelle finden an der der Wechselkurs angegeben ist und den String darauf
            // zuschneiden
            htmlOutput = htmlOutput.substring(htmlOutput.indexOf("YMlKec fxKbKc\">"));
            htmlOutput = htmlOutput.substring(15, htmlOutput.indexOf("<"));

            lastEndMillis = System.currentTimeMillis();

            System.out.println("Exchange rate fetched within " + (getLastFetchTime()) + "ms");
            latestRate = Utils.adjustDecimal(Double.parseDouble(htmlOutput), 4);

        } catch (UnknownHostException uhExc) {
            // TODO: Kein Internet ODER Internet Proleme bei User
            ErrorDisplay.throwErrorPopup("Es konnte keine Verbindung zum Server hergestellt werden");
            uhExc.printStackTrace();
            latestRate = 0.0;

        } catch (StringIndexOutOfBoundsException oobExc) {
            // TODO: Währung existiert nicht (mehr) oder beim fetchen der Daten ist ein Fehler aufgetreten.
            ErrorDisplay.throwErrorPopup("Einer der Währungen scheint nicht zu existieren oder es ist ein Fehler beim fetchen der Daten aufgetreten");
            oobExc.printStackTrace();
            latestRate = 0.0;

        } catch (Exception exc) {
            // TODO: Irgendwas ist schief gelaufen aber keine Ahnung lmao
            System.err.print("[UNKNOWN ERROR] ");
            ErrorDisplay.throwErrorPopup("Ein unbekannter Fehler ist aufgetreten");
            exc.printStackTrace();
            latestRate = 0.0;

        }
    }

    public static long getLastFetchTime() {
        return lastEndMillis - lastStartMillis;
    }
}

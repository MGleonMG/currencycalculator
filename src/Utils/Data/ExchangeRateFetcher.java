package Utils.Data;

import java.net.URL;
import java.util.Scanner;

import Utils.Utils;

public class ExchangeRateFetcher {
    public static double latestRate;
    private static long lastStartMillis, lastEndMillis;

    // Webscraper um Daten von 'google.com/finance/' zu nutzen
    @SuppressWarnings({ "resource" })
    public static void fetchExchangeRate(String baseCur, String targetCur) {
        try {
            System.out.println("Fetching Exchange rate...");
            lastStartMillis = System.currentTimeMillis();
            // Roher HTML code als String von 'google.com/finance/'
            String out = new Scanner(
                    new URL("https://www.google.com/finance/quote/" + baseCur + "-" + targetCur).openStream(), "UTF-8")
                    .useDelimiter("\\A").next();

            // Stelle finden an der der Wechselkurs angegeben ist und den String darauf
            // zuschneiden
            out = out.substring(out.indexOf("YMlKec fxKbKc\">"));
            out = out.substring(15, out.indexOf("<"));

            lastEndMillis = System.currentTimeMillis();

            System.out.println("Exchange rate fetched within " + (getLastFetchTime()) + "ms:");
            latestRate = Utils.adjustDecimal(Double.parseDouble(out), 4);

        } catch (Exception e) {
            System.err.print("[ERROR]");
            e.printStackTrace();
            latestRate = 0.0;
        }
    }

    public static long getLastFetchTime() {
        return lastEndMillis - lastStartMillis;
    }
}

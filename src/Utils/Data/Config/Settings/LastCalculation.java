package Utils.Data.Config.Settings;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;

import GUI.Popups.PopupDisplay;
import Utils.Data.Config.Config;

public class LastCalculation {
    String baseCur, targetCur, amount, lastFetchTime;
    // JSON keywords um die korrekten Einstellungen auszulesen
    private static final String KEY_LASTBASECUR = "lastBaseCurrency",
            KEY_LASTTARGETCUR = "lastTargetCurrency",
            KEY_LASTAMOUNT = "lastAmount";

    public LastCalculation(String baseCur, String targetCur, String amount) {
        this.baseCur = baseCur;
        this.targetCur = targetCur;
        this.amount = amount;
    }

    // Diese Methode ruft die zu letzt gespeicherte Daten ab
    public static String[] getConfigLastCalc() {
        String[] calcInfo = new String[3];

        try {
            JsonObject newConfig = Config.gson.fromJson(new FileReader(Config.getFilePath()), JsonObject.class);
            calcInfo[0] = newConfig.get(KEY_LASTBASECUR).toString();
            calcInfo[1] = newConfig.get(KEY_LASTTARGETCUR).toString();
            calcInfo[2] = newConfig.get(KEY_LASTAMOUNT).toString();

            return calcInfo;

        } catch (NullPointerException npe) {
            PopupDisplay.throwErrorPopup(
                    "Es gab scheinbar ein Problem beim abrufen deiner Einstellungen!",
                    "Du hast scheinbar noch keine letzte Rechnung abgespeichert");
            return null;
        } catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {

            PopupDisplay.throwErrorPopup(
                    "Es gab scheinbar ein Problem beim abrufen deiner Einstellungen!",
                    e.getMessage());
            return null;
        }

    }

    /*
     * Diese Methode speichert die Werte, die der Benutzer eingegeben hat
     */
    public static void setConfigLastCalc(String baseCurResult, String targetCurResult, String inputValue) {
        try (JsonReader jsonReader = new JsonReader(new FileReader(Config.getFilePath()))) {
            JsonObject newConfig = JsonParser.parseReader(jsonReader).getAsJsonObject();

            newConfig.addProperty(KEY_LASTBASECUR, baseCurResult);
            newConfig.addProperty(KEY_LASTTARGETCUR, targetCurResult);
            newConfig.addProperty(KEY_LASTAMOUNT, inputValue);

            FileWriter writer = new FileWriter(Config.getFilePath());
            Config.gson.toJson(newConfig, writer);
            writer.close();

        } catch (JsonIOException | JsonSyntaxException | IOException e) {

            PopupDisplay.throwErrorPopup(
                    "Es gab scheinbar ein Problem beim speichern deiner Einstellungen!",
                    e.getMessage());
        }
    }
}

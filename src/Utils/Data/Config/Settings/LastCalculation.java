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
    private static final String KEY_LASTBASECUR = "lastBaseCurrency",
            KEY_LASTTARGETCUR = "lastTargetCurrency",
            KEY_LASTAMOUNT = "lastAmount",
            KEY_LASTFETCHTIME = "lastFetchTime";

    public LastCalculation(String baseCur, String targetCur, String amount, String lastFetchTime) {
        this.baseCur = baseCur;
        this.targetCur = targetCur;
        this.amount = amount;
        this.lastFetchTime = lastFetchTime;
    }

    public static String[] getConfigLastCalc() {
        String[] calcInfo = new String[4];

        try {
            JsonObject newConfig = Config.gson.fromJson(new FileReader(Config.getFilePath()), JsonObject.class);
            calcInfo[0] = newConfig.get(KEY_LASTBASECUR).toString();
            calcInfo[1] = newConfig.get(KEY_LASTTARGETCUR).toString();
            calcInfo[2] = newConfig.get(KEY_LASTAMOUNT).toString();
            calcInfo[3] = newConfig.get(KEY_LASTFETCHTIME).toString();

            return calcInfo;

        } catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {

            PopupDisplay.throwErrorPopup(
                    "Es gab scheinbar ein Problem beim abrufen deiner Einstellungen! \nFehlermeldung:\n"
                            + e.getMessage());
            return null;
        }

    }

    public static void setConfigLastCalc(String baseCur, String targetCur, String amount, String lastFetchTime) {
        try (JsonReader jsonReader = new JsonReader(new FileReader(Config.getFilePath()))) {
            JsonObject newConfig = JsonParser.parseReader(jsonReader).getAsJsonObject();

            newConfig.addProperty(KEY_LASTBASECUR, baseCur);
            newConfig.addProperty(KEY_LASTTARGETCUR, targetCur);
            newConfig.addProperty(KEY_LASTAMOUNT, amount);
            newConfig.addProperty(KEY_LASTFETCHTIME, lastFetchTime);

            FileWriter writer = new FileWriter(Config.getFilePath());
            Config.gson.toJson(newConfig, writer);
            writer.close();

        } catch (JsonIOException | JsonSyntaxException | IOException e) {

            PopupDisplay.throwErrorPopup(
                    "Es gab scheinbar ein Problem beim speichern deiner Einstellungen! \nFehlermeldung:\n "
                            + e.getMessage());
        }
    }
}

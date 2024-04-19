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

import GUI.Errors.ErrorDisplay;
import Utils.Data.Config.Config;

public class LastCalculation {
    String baseCur, targetCur, amount, lastFetchTime;
    private static final String keyLastBaseCur = "lastBaseCurrency",
            keyLastTargetCur = "lastTargetCurrency",
            keyLastAmount = "lastAmount";
           // keyLastFetchTime = "lastFetchTime";

    public LastCalculation(String baseCur, String targetCur, String amount) {
        this.baseCur = baseCur;
        this.targetCur = targetCur;
        this.amount = amount;
        //this.lastFetchTime = lastFetchTime;
    }

    public static String[] getConfigLastCalc() {
        String[] calcInfo = new String[3];

        try {
            JsonObject newConfig = Config.gson.fromJson(new FileReader(Config.getFilePath()), JsonObject.class);
            calcInfo[0] = newConfig.get(keyLastBaseCur).toString();
            calcInfo[1] = newConfig.get(keyLastTargetCur).toString();
            calcInfo[2] = newConfig.get(keyLastAmount).toString();
           // calcInfo[3] = newConfig.get(keyLastFetchTime).toString();

            return calcInfo;

        } catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {

            ErrorDisplay.throwErrorPopup(
                    "Es gab scheinbar ein Problem beim abrufen deiner Einstellungen! \nFehlermeldung:\n"
                            + e.getMessage());
            return null;
        }

    }

    public static void setConfigLastCalc(String baseCur, String targetCur, String amount) {
        try (JsonReader jsonReader = new JsonReader(new FileReader(Config.getFilePath()))) {
            JsonObject newConfig = JsonParser.parseReader(jsonReader).getAsJsonObject();

            newConfig.addProperty(keyLastBaseCur, baseCur);
            newConfig.addProperty(keyLastTargetCur, targetCur);
            newConfig.addProperty(keyLastAmount, amount);
           // newConfig.addProperty(keyLastFetchTime, lastFetchTime);

            FileWriter writer = new FileWriter(Config.getFilePath());
            Config.gson.toJson(newConfig, writer);
            writer.close();

        } catch (JsonIOException | JsonSyntaxException | IOException e) {

            ErrorDisplay.throwErrorPopup(
                    "Es gab scheinbar ein Problem beim speichern deiner Einstellungen! \nFehlermeldung:\n "
                            + e.getMessage());
        }
    }
}

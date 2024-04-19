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

public class AppTheme {
    String appTheme;
    private static final String key = "appTheme";

    public AppTheme(String appTheme) {
        this.appTheme = appTheme;
    }

    public enum Theme {
        LIGHT_MODE,
        DARK_MODE
    }

    // Gibt einen Wert von der Auflistung (enum) "Theme" je nach Config-Eintrag
    public static Theme getConfigAppTheme() {

        try {
            JsonObject jsonconfig = Config.gson.fromJson(new FileReader(Config.getFilePath()), JsonObject.class);

            if (jsonconfig.get(key).toString().contains("DARK")) {
                return Theme.DARK_MODE;
            } else {
                return Theme.LIGHT_MODE;
            }

        } catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {

            PopupDisplay.throwErrorPopup(
                    "Es gab scheinbar ein Problem beim abrufen deiner AppTheme Einstellungen! \nFehlermeldung:\n"
                            + e.getMessage());
            return null;
        }

    }

    public static void setConfigAppTheme(Theme newTheme) {
        try (JsonReader jsonReader = new JsonReader(new FileReader(Config.getFilePath()))) {
            JsonObject jsonconfig = JsonParser.parseReader(jsonReader).getAsJsonObject();

            jsonconfig.addProperty(key, newTheme.toString());

            FileWriter writer = new FileWriter(Config.getFilePath());
            Config.gson.toJson(jsonconfig, writer);
            writer.close();

        } catch (JsonIOException | JsonSyntaxException | IOException e) {

            PopupDisplay.throwErrorPopup(
                    "Es gab scheinbar ein Problem beim speichern deiner AppTheme Einstellungen! \nFehlermeldung:\n "
                            + e.getMessage());
        }
    }
}

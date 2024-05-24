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
import lang.Language;
import lang.Language.Languages;

public class AppLanguage {
    Languages appLanguage;
    // JSON keywords um die korrekten Einstellungen auszulesen
    private static final String KEY_APPLANG = "appLanguage";

    // Konstruktor für das AppLanguage Objekt
    public AppLanguage(Languages appLanguage) {
        this.appLanguage = appLanguage;
    }

    // Gibt einen Wert von der Auflistung (enum) "Languages" je nach Config-Eintrag
    public static Languages getConfigAppLanguage() {

        try {
            JsonObject jsonconfig = Config.gson.fromJson(new FileReader(Config.getFilePath()), JsonObject.class);

            return Languages.valueOf(jsonconfig.get(KEY_APPLANG).getAsString());

        } catch (JsonSyntaxException | JsonIOException | FileNotFoundException | IllegalArgumentException
                | NullPointerException e) {

            PopupDisplay.throwErrorPopup(
                    Language.getLangStringByKey("error_lang_set") + "\n"
                            + Language.getLangStringByKey("error_info") + "\n"
                            + Config.getFilePath(), /* TODO @Leon: remove this? */
                    e.getMessage());
            return null;
        }

    }

    public static void setConfigAppLanguage(Languages newLanguage) {
        try (JsonReader jsonReader = new JsonReader(new FileReader(Config.getFilePath()))) {
            JsonObject jsonconfig = JsonParser.parseReader(jsonReader).getAsJsonObject();

            jsonconfig.addProperty(KEY_APPLANG, newLanguage.toString());

            FileWriter writer = new FileWriter(Config.getFilePath());
            Config.gson.toJson(jsonconfig, writer);
            writer.close();
            jsonReader.close();

        } catch (JsonIOException | JsonSyntaxException | IOException e) {

            PopupDisplay.throwErrorPopup(
                    Language.getLangStringByKey("error_lang_set"),
                    e.getMessage());
        }
    }
}

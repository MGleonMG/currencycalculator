package Utils.Data.Config;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.FileSystems;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import GUI.Errors.ErrorDisplay;
import Utils.Data.Config.Settings.AppLanguage;
import Utils.Data.Config.Settings.AppTheme;
import Utils.Data.Config.Settings.LastCalculation; // TODO: @Leon remove this
import lang.Language.Languages;

public class Config {
    private static final String FOLDER_PATH = System.getProperty("user.home") +
            FileSystems.getDefault().getSeparator()
            + "currencycalculator";
    private static final String FILE_PATH = FOLDER_PATH +
            FileSystems.getDefault().getSeparator() + "settings.json";
    public static Gson gson = new Gson();

    public static String getFolderPath() {
        return FOLDER_PATH;
    }

    public static String getFilePath() {
        return FILE_PATH;
    }

    // Überprüft ob config folder und datei vorhanden sind
    public static void runFirstTimeSetupCheck() {
        File settingsFile = new File(getFilePath());

        if (!settingsFile.exists()) {
            new File(getFolderPath()).mkdir();

            try (FileWriter writer = new FileWriter(getFilePath())) {
                settingsFile.createNewFile();

                ConfigDefaults defaults = new ConfigDefaults();
                gson.toJson(defaults.getAllConfigDefaults(), writer);

                writer.flush();
                writer.close();

            } catch (Exception e) {
                e.printStackTrace();
                ErrorDisplay.throwErrorPopup(e.getMessage());
            }

        }
    }
}

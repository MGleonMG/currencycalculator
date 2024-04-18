package Utils.Data.Config;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.FileSystems;

import com.google.gson.Gson;

import GUI.Errors.ErrorDisplay;
import Utils.Data.Config.Settings.AppTheme;

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
            System.out.println("\n[INFO] Running first time setup..."); // debug / indev. TODO: remove in the future
            new File(getFolderPath()).mkdir();

            try (FileWriter writer = new FileWriter(getFilePath())) {
                settingsFile.createNewFile();

                AppTheme obj = new AppTheme(AppTheme.Theme.DARK_MODE.toString());
                gson.toJson(obj, writer);

                writer.flush();
                writer.close();

            } catch (Exception e) {
                e.printStackTrace();
                ErrorDisplay.throwErrorPopup(e.getMessage());
            }

        } else {

            System.out.println("\nFirst time setup not necessary. Skipping.."); // debug / indev. TODO: remove in the future
        }
    }
}

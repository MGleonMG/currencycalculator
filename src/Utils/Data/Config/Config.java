package Utils.Data.Config;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.FileSystems;

import com.google.gson.Gson;

import GUI.Popups.PopupDisplay;
import lang.Language;

/*
 * Diese Klasse stellt einfache Config Funktionen und Variabeln zur Verfügung
 */
public class Config {
    // static final vars
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

    /*
     * Überprüft ob der Konfigurations Ordner mit der Datei schon existieren
     * und versucht gegebenenfalls diese zu erstellen.
     * 
     * Das Programm endet mit dem vorherigem Popup,
     * falls ein Fehler auftreten sollte.
     */
    public static void runFirstTimeSetupCheck() {
        File settingsFile = new File(getFilePath());

        if (!settingsFile.exists()) {
            new File(getFolderPath()).mkdir(); // mkdir() = "make directory"

            try (FileWriter writer = new FileWriter(getFilePath())) {
                settingsFile.createNewFile();

                // Lädt ConfigDefaults, und speichert diese mit Hilfe Gson lib
                ConfigDefaults defaults = new ConfigDefaults();
                gson.toJson(defaults.getAllConfigDefaults(), writer);

                // "Best practice" - readers und writers schließen
                writer.flush();
                writer.close();

            } catch (Exception e) {
                e.printStackTrace();
                PopupDisplay.throwErrorPopup(
                        Language.getLangStringByKey("error_config_fatal"),
                        e.getMessage());
                System.exit(1);
            }

        }
    }
}

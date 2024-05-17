package lang;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Properties;

import GUI.GUI;
import GUI.Popups.PopupDisplay;
import Utils.Data.Config.Settings.AppLanguage;

public class Language {
    private static Locale locale;
    private static Properties langBundle;
    private static String fileName;
    private static Properties properties = new Properties();

    // Auflistung aller unterstützten Sprachen
    public enum Languages {
        GERMAN,
        ENGLISH,
        SPANISH,
        DANISH
    }

    /*
     * Funktion um die Anzeige-Sprache zu ändern.
     * - "language" nimmt ein eine Auflistung namens Languages entgegen
     * - "updateConfig" nimmt einen boolean entgegen der bestimmt ob eine Änderung
     * in der config Datei des benutzers nötig ist
     */
    @SuppressWarnings("deprecation")
    public static void setAppLanguage(Languages language, boolean updateConfig, boolean onStartup) {
        fileName = "/resources/languages/lang_" + language.name().toLowerCase() + ".properties";

        try (InputStream inputStream = Language.class.getResourceAsStream(fileName)) {
            try (InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
                properties.load(reader);
            }
        } catch (IOException e) {
            PopupDisplay.throwErrorPopup("Es ist ein Fehler beim Laden der Spracheinstellungen aufgetreten",
                    e.getMessage());
            System.exit(1);
        }
        langBundle = properties;

        switch (language) {
            case ENGLISH:
                locale = Locale.ENGLISH;
                break;

            case GERMAN:
                locale = new Locale("de", "DE");
                break;

            case SPANISH:
                locale = new Locale("es", "ES");
                break;

            case DANISH:
                locale = new Locale("da", "DK");
                break;
        }

        if (updateConfig) {
            AppLanguage.setConfigAppLanguage(language);
            PopupDisplay.throwInfoPopup("App Sprache", "Spracheinstellungen wurden geändert");
        }

        Locale.setDefault(locale);

        if (!onStartup) {
            try {
                GUI.updateDisplayedLanguage(/* true */);
            } catch (NullPointerException npe) {
                // tu nichts.
            }
            PopupDisplay.throwInfoPopup("Sprache",
                    "Spracheinstellungen geändert zu " + language.toString().toLowerCase());
        }
    }

    // Gibt den jeweiligen Inhalt nach key aus der gewünschten properties Datei aus
    public static String getLangStringByKey(String key) {
        return langBundle.getProperty(key);
    }
}

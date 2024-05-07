package lang;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;

import Utils.Data.Config.Settings.AppLanguage;

public class Language {
    private static Locale locale;
    private static Properties langBundle;
    private static String fileName;
    private static InputStream inputStream;
    private static Properties properties;

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
    public static void setAppLanguage(Languages language, boolean updateConfig) throws IOException {
        fileName = "/resources/languages/lang_" + language.name().toLowerCase() + ".properties";
        inputStream = Language.class.getResource(fileName).openStream();
        properties = new Properties();

        properties.load(inputStream);

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

        Locale.setDefault(locale);

        if (updateConfig) {
            AppLanguage.setConfigAppLanguage(language);
        }

        langBundle = properties;
    }

    // Gibt den jeweiligen Inhalt nach key aus der gewünschten properties Datei aus
    public static String getLangStringByKey(String key) {
        return langBundle.getProperty(key);
    }
}

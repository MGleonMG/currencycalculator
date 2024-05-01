package lang;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import Utils.Data.Config.Settings.AppLanguage;

public class Language {
    private static Locale locale;
    private static ResourceBundle langBundle;

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
    public static void setAppLanguage(Languages language, boolean updateConfig) {
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
        }

        Locale.setDefault(locale);
        System.out.println("Default locale: " + Locale.getDefault());

        String bundleName = "lang_" + language.name().toLowerCase() + ".properties";
        System.out.println("Bundle name: " + bundleName);

        langBundle = ResourceBundle.getBundle(bundleName, locale);

        try {
            langBundle = ResourceBundle.getBundle(bundleName, locale);
            System.out.println("Bundle loaded successfully!");
        } catch (MissingResourceException e) {
            System.err.println("Failed to load bundle: " + e.getMessage());
        }
    }

    public static String getLangStringByKey(String key) {
        // langBundle.

        return "ABC";
    }
}

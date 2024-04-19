package lang;

import java.util.Locale;

import Utils.Data.Config.Settings.AppLanguage;

public class Language {
    private static Locale locale;

    // Auflistung aller unterstützten Sprachen
    public enum Languages {
        GERMAN,
        ENGLISH
    }

    /*
     * Funktion um die Anzeige-Sprache zu ändern.
     * "language" nimmt ein eine Auflistung namens Languages entgegen
     * "updateConfig" nimmt einen boolean entgegen der bestimmt ob eine Änderung in
     * der config Datei des benutzers nötig ist
     */
    public static void setAppLanguage(Languages language, boolean updateConfig) {
        switch (language) {
            case ENGLISH:
                locale = Locale.US;
                Locale.setDefault(locale);
                if (updateConfig) {
                    AppLanguage.setConfigAppLanguage(Languages.ENGLISH);
                }
                break;

            case GERMAN:
                locale = Locale.GERMANY;
                Locale.setDefault(locale);
                AppLanguage.setConfigAppLanguage(Languages.GERMAN);
                if (updateConfig) {
                    AppLanguage.setConfigAppLanguage(Languages.GERMAN);
                }
                break;
        }
    }
}

package lang;

import java.util.Locale;

import Utils.Data.Config.Settings.AppLanguage;

public class Language {
    private static Locale locale;
    // ..
    // Auflistung aller unterstützten Sprachen
    public enum Languages {
        GERMAN,
        ENGLISH,
        SPANISH,
        DANISH
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

            case SPANISH:
                // TODO: Spanish missing in Locale class? Check later..
                // locale = Locale.;
                Locale.setDefault(locale);
                AppLanguage.setConfigAppLanguage(Languages.SPANISH);
                if (updateConfig) {
                    AppLanguage.setConfigAppLanguage(Languages.SPANISH);
                }
                break;

            case DANISH:
                // TODO: same here as above??
                // locale = Locale.DANISH;
                Locale.setDefault(locale);
                AppLanguage.setConfigAppLanguage(Languages.DANISH);
                if (updateConfig) {
                    AppLanguage.setConfigAppLanguage(Languages.DANISH);
                }
                break;
        }
    }
}

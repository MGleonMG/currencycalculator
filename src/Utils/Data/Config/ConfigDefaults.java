package Utils.Data.Config;

import GUI.Popups.PopupDisplay;
import Utils.Data.Config.Settings.AppLanguage;
import Utils.Data.Config.Settings.AppTheme;
import Utils.Data.Config.Settings.LastCalculation;
import Utils.Data.Config.Settings.AppTheme.Theme;
import lang.Language.Languages;

public class ConfigDefaults {
    public static class Container {
        AppTheme appTheme;
        AppLanguage appLanguage;
        // TODO @Leon: add default umrechnung wie z.B. 5 euro in USD?
    }

    @SuppressWarnings("unused")
    private Theme appTheme;
    @SuppressWarnings("unused")
    private Languages appLanguage;

    public ConfigDefaults getAllConfigDefaults() {
        ConfigDefaults defaults = new ConfigDefaults();
        defaults.appTheme = AppTheme.Theme.DARK_MODE;
        defaults.appLanguage = Languages.GERMAN;

        return defaults;
    }

    public static void restoreAllDefaults() {
        AppLanguage.setConfigAppLanguage(Languages.GERMAN);
        AppTheme.setConfigAppTheme(Theme.DARK_MODE);
        LastCalculation.setConfigLastCalc("EUR", "USD", "5.0");

        PopupDisplay.throwInfoPopup("Config reset",
                "Das Program schließt sich nun. Beim erneuten Öffnen sind alle Einstellungen zurückgesetzt.");
        System.exit(0);
    }
}

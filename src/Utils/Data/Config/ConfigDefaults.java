package Utils.Data.Config;

import java.io.IOException;

import GUI.Popups.PopupDisplay;
import Utils.RestartHelper;
import Utils.Data.Config.Settings.AppLanguage;
import Utils.Data.Config.Settings.AppTheme;
import Utils.Data.Config.Settings.LastCalculation;
import Utils.Data.Config.Settings.AppTheme.Theme;
import lang.Language;
import lang.Language.Languages;

public class ConfigDefaults {

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

    /*
     * Setzt die Benutzereinstellungen zurück
     */
    public static void restoreAllDefaults() {
        AppLanguage.setConfigAppLanguage(Languages.GERMAN);
        AppTheme.setConfigAppTheme(Theme.DARK_MODE);
        LastCalculation.setConfigLastCalc("EUR", "USD", "5.0");

        PopupDisplay.throwInfoPopup(Language.getLangStringByKey("popup_restore_default_header"),
                Language.getLangStringByKey("popup_restore_default"));
        try {
            RestartHelper.restartApplication();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}

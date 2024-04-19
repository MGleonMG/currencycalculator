package Utils.Data.Config;

import Utils.Data.Config.Settings.AppLanguage;
import Utils.Data.Config.Settings.AppTheme;
import Utils.Data.Config.Settings.AppTheme.Theme;
import lang.Language.Languages;

public class ConfigDefaults {
    public static class Container {
        AppTheme appTheme;
        AppLanguage appLanguage;
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
}

package Utils.Data.Config;

import java.io.File;
import java.util.Currency;
import java.util.Scanner;

import javax.swing.Popup;

import GUI.Popups.PopupDisplay;
import Main.CurrencyCalculator;
import Utils.Data.Config.Settings.AppLanguage;
import Utils.Data.Config.Settings.AppTheme;
import Utils.Data.Config.Settings.AppTheme.Theme;
import lang.Language.Languages;

public class ConfigDefaults {
    public static class Container {
        AppTheme appTheme;
        AppLanguage appLanguage;
        // TODO: add default umrechnung wie z.B. 5 euro in USD?
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

    public static void resetAllDefaults() {
        System.out.print("Reset to defaults?  ->");
        Scanner in = new Scanner(System.in);
        if (in.nextLine().contains("yes")) {
            System.out.println("deleting..");
            try {
                File configFile = new File(Config.getFilePath());
                configFile.delete();

            } catch (Exception e) {
                System.err.println("error!");
                PopupDisplay.throwErrorPopup("Es ist ein Fehler aufgetreten!", e.getMessage());
            }
        }
        in.close();

        PopupDisplay.throwInfoPopup("Config reset",
                "Das Program schließt sich nun. Beim erneuten Öffnen sollten alle Einstellungen zurückgesetzt sein");
        System.exit(0);
    }
}

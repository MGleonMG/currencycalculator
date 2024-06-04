package Main;

import GUI.GUI;
import Utils.Data.Config.Config;
import Utils.Data.Config.Settings.AppLanguage;
import lang.Language;

/*
 * Die Hauptklasse des Währungsrechners
 */
public class CurrencyCalculator {
    private static final String VERSION = "v1.0.0";
    private static final String GITHUB_URL = "https://github.com/MGleonMG/currencycalculator/";

    public static void main(String[] args) {
        Config.runFirstTimeSetupCheck();
        Language.setAppLanguage(AppLanguage.getConfigAppLanguage(), false, true);
        GUI.drawGUI();
    }

    public static String getAppVersion() {
        return VERSION;
    }

    public static String getRepoURL() {
        return GITHUB_URL;
    }
}
package Main;

import GUI.GUI;
import Utils.Data.Config.Config;

/*
 * Dies ist die HauptKlasse
 */
public class CurrencyCalculator {

    private static final String VERSION = "1.0_indev";

    public static void main(String[] args) {
        Config.runFirstTimeSetupCheck();
        // TODO: remove?
        // Language.setAppLanguage(AppLanguage.getConfigAppLanguage(), false);
        GUI.drawGUI();
    }

    public static String getAppVersion() {
        return VERSION;
    }
}
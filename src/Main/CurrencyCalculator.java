package Main;

import GUI.GUI;
import Utils.Data.Config.Config;
import Utils.Data.Config.Settings.AppLanguage;
import lang.Language;

/*
 * Dies ist die HauptKlasse
 */
public class CurrencyCalculator {

    /*
     * ver. x.y.z
     * x: große Veränderungen / Überarbeitungen
     * y: neue Features
     * z: Bugfixes
     */
    private static final String VERSION = "v1.1.0";

    public static void main(String[] args) {
        Config.runFirstTimeSetupCheck();
        Language.setAppLanguage(AppLanguage.getConfigAppLanguage(), false, true);
        GUI.drawGUI();
    }

    public static String getAppVersion() {
        return VERSION;
    }
}
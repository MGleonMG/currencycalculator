package Main;

import GUI.GUI;
import Utils.Data.Config.Config;
import Utils.Data.Config.Settings.AppLanguage;
import lang.Language;

/*
 * Dies ist die HauptKlasse
 */
public class CurrencyCalculator {

    public static void main(String[] args) {
        Config.runFirstTimeSetupCheck();
        Language.setAppLanguage(AppLanguage.getConfigAppLanguage(), false);
        GUI.drawGUI();
    }
}
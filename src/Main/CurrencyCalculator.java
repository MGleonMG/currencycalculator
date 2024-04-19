package Main;

import GUI.GUI;
import Utils.Data.Config.Config;

/*
 * Dies ist die HauptKlasse
 */
public class CurrencyCalculator {

    public static void main(String[] args) {
        Config.runFirstTimeSetupCheck();
        // Language.switchLanguage(Lang.Language.GERMAN); TODO: implement config read and language set from config
        GUI.drawGUI();
    }
}
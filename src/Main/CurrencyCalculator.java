package Main;

import GUI.GUI;
import GUI.Popups.PopupDisplay;
import Utils.Data.Config.Config;

/*
 * Dies ist die HauptKlasse
 */
public class CurrencyCalculator {

    public static void main(String[] args) {
        Config.runFirstTimeSetupCheck();
        GUI.drawGUI();
    }
}
package Main;

import GUI.GUI;
import Utils.Data.Config.Config;
import Utils.Data.Config.Settings.LastCalculation;

/*
 * Dies ist die HauptKlasse
 */
public class CurrencyCalculator {

    public static void main(String[] args) {
        Config.runFirstTimeSetupCheck();
        GUI.drawGUI();
        LastCalculation.setConfigLastCalc("EUR", "USD", "2.50", "6969ms");
        //System.out.println(LastCalculation.getConfigLastCalc());
    }
}
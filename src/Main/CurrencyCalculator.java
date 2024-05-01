package Main;

import java.io.IOException;

import GUI.GUI;
import Utils.Data.Config.Config;
import Utils.Data.Config.Settings.AppLanguage;
import lang.Language;
import lang.Language.Languages;

/*
 * Dies ist die HauptKlasse
 */
public class CurrencyCalculator {

    public static void main(String[] args) throws IOException {
        Config.runFirstTimeSetupCheck();
        // Language.setAppLanguage(AppLanguage.getConfigAppLanguage(), false);
        Language.setAppLanguage(Languages.SPANISH, false);
        GUI.drawGUI();
        System.out.println("\"" + Language.getLangStringByKey("title") + "\"");
    }
}
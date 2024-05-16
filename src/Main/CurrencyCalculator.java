package Main;

import java.util.Scanner;

import GUI.GUI;
import Utils.Data.Config.Config;
import Utils.Data.Config.Settings.AppLanguage;
import lang.Language;
import lang.Language.Languages;

/*
 * Dies ist die HauptKlasse
 */
public class CurrencyCalculator {

    private static final String VERSION = "1.0_indev";

    public static void main(String[] args) {
        Config.runFirstTimeSetupCheck();
        // TODO @Leon: remove this or refactor to somewhere else?
        Language.setAppLanguage(AppLanguage.getConfigAppLanguage(), false);
        GUI.drawGUI();

        // ----
        // debugging
        Scanner in = new Scanner(System.in);
        System.out.print("update lang?  ->");
        if (in.nextLine().contains("y")) {
            System.out.println("updating..");
            Language.setAppLanguage(Languages.SPANISH, false);
        }
        System.out.println("\n");

        in.reset();
        in.close();
        // ----
    }

    public static String getAppVersion() {
        return VERSION;
    }
}
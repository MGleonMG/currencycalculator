package Main;

import java.io.FileNotFoundException;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import GUI.GUI;
import Utils.Data.Config.Config;
import Utils.Data.Config.Settings.AppTheme;

public class CurrencyCalculator {

    public static void main(String[] args) throws JsonSyntaxException, JsonIOException, FileNotFoundException {
        Config.runFirstTimeSetupCheck();
        AppTheme.getConfigAppTheme();
        GUI.drawGUI();
    }
}
package Main;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;

import GUI.GUI;
import Utils.Data.Config.Config;
import Utils.Data.Config.SavedConfigs;

public class CurrencyCalculator {

    public static void main(String[] args) {
        GUI.drawGUI();

        // String filePath = System.getProperty("user.home") + FileSystems.getDefault().getSeparator()
        //         + "currencycalculator" + FileSystems.getDefault().getSeparator() + "settings.json";

        // System.out.println(filePath);

        // try {
        //     if (filePath == null) {
        //         new File(filePath).createNewFile();
        //     }

        //     String json = Config.readJSONFile(filePath);
        //     String key = SavedConfigs.parseKey(json);
        //     String value = SavedConfigs.parseValue(json);
        //     System.out.println("Key: " + key);
        //     System.out.println("Value: " + value);

        // } catch (IOException e) {

        //     e.printStackTrace();
        // }
    }
}
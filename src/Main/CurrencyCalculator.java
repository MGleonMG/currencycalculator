package Main;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;

import GUI.GUI;
import GUI.Errors.ErrorDisplay;
import Utils.Data.Config.Config;
import Utils.Data.Config.SavedConfigs;

public class CurrencyCalculator {
    public static String folderPath = System.getProperty("user.home") + FileSystems.getDefault().getSeparator()
            + "currencycalculator";
    public static String filePath = folderPath + FileSystems.getDefault().getSeparator() + "settings.json";

    public static void main(String[] args) {
        GUI.drawGUI();

        try {
            new File(folderPath).mkdir();
            new File(filePath).createNewFile();

            SavedConfigs.addToJson("testKey", "testValue");

            String json = Config.readJSONFile(filePath);
            String key = SavedConfigs.parseKey(json);
            String value = SavedConfigs.parseValue(json);
            System.out.println("Key: " + key);
            System.out.println("Value: " + value);

        } catch (IOException e) {
            e.printStackTrace();
            ErrorDisplay.throwErrorPopup(e.getMessage());
            // TODO: write a proper error
        }
    }
}
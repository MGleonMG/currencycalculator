package Utils.Data.Config;

import java.io.IOException;

import Main.CurrencyCalculator;

public class SavedConfigs {

    public static String parseKey(String json) {
        int startIndex = json.indexOf('"') + 1;
        int endIndex = json.indexOf('"', startIndex);
        return json.substring(startIndex, endIndex);
    }

    public static String parseValue(String json) {
        int startIndex = json.indexOf('"', json.indexOf('"') + 1) + 1;
        int endIndex = json.indexOf('"', startIndex);
        return json.substring(startIndex, endIndex);
    }

    public static String addToJson(String key, String value) throws IOException {
        // Remove the last '}' from JSON
        String json = Config.readJSONFile(CurrencyCalculator.filePath).substring(0, Config.readJSONFile(CurrencyCalculator.filePath).length() - 1);

        // Add new key-value pair
        json += ", \"" + key + "\": \"" + value + "\"}";

        return json;
    }


    // public static Config loadConfig(String pathString) /* throws some Exc??*/ {
    // return null; // temp
    // }
}

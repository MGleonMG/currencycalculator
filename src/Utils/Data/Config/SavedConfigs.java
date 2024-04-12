package Utils.Data.Config;

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
    
    // public static Config loadConfig(String pathString) /* throws some Exc??*/ {
    //     return null; // temp
    // }
}

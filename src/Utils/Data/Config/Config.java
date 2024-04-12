package Utils.Data.Config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Config {

    public static String readJSONFile(String filePath) throws IOException {
        StringBuilder json = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                json.append(line);
            }
        }
        return json.toString();
    }
    
    // private String setting;
    // private String value;

    // public String getConfigSetting() {
    //     return setting;
    // }

    // public void setConfigSetting(String setting) { /* saveSetting() instead?? */
    //     this.setting = setting;
    // }

    // public String getConfigValue() {
    //     return value;
    // }

    // public void setConfigValue(String value) {/* saveSetting() instead?? */
    //     this.value = value;
    // }
}

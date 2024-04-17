package Utils.Data.Config;

import java.nio.file.FileSystems;

public class Config {
    private static final String FOLDER_PATH = System.getProperty("user.home") +
            FileSystems.getDefault().getSeparator()
            + "currencycalculator";
    private static String FILE_PATH = FOLDER_PATH +
            FileSystems.getDefault().getSeparator() + "settings.json";

    public static String getFolderPath() {
        return FOLDER_PATH;
    }

    public static String getFILE_PATH() {
        return FILE_PATH;
    }

    
}

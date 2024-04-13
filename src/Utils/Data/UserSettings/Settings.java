package Utils.Data.UserSettings;

import java.nio.file.FileSystems;

public class Settings {
    private static String folderPath = System.getProperty("user.home") +
            FileSystems.getDefault().getSeparator()
            + "._currencycalculator";
    private static String filePath = folderPath +
            FileSystems.getDefault().getSeparator() + "settings.json";

    // private Theme theme;
    // TODO: @Leon add more..

    public static String getFolderPath() {
        return folderPath;
    }

    public static String getFilePath() {
        return filePath;
    }

    // public Theme getTheme() {
    // return theme;
    // }

    // public void setTheme(Theme theme) {
    // this.theme = theme;
    // }
}

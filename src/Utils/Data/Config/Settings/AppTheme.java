package Utils.Data.Config.Settings;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import Utils.Data.Config.Config;

public class AppTheme {
    String appTheme;

    public AppTheme(String appTheme) {
        this.appTheme = appTheme;
    }

    public enum Theme {
        LIGHT_MODE,
        DARK_MODE
    }

    public static Theme getConfigAppTheme() throws JsonSyntaxException, JsonIOException, FileNotFoundException {
        JsonObject jsonObject = Config.gson.fromJson(new FileReader(Config.getFilePath()), JsonObject.class);
        // TODO: @Leon adjust jsonObject var name here, possibly make it static?

        System.out.println("config readout: " + jsonObject.get("appTheme").getAsString());
        if (jsonObject.get("appTheme").getAsString() == "LIGHT") {
            return Theme.LIGHT_MODE;
        } else {
            return Theme.DARK_MODE;
        }
    }

    public static void setConfigAppTheme(Theme newTheme) {
        JsonObject jsonObject = Config.gson.fromJson(new FileReader(Config.getFilePath()), JsonObject.class);
    }
}

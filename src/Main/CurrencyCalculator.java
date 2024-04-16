package Main;

import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

import GUI.GUI;
import Utils.Data.UserSettings.Settings;

public class CurrencyCalculator {

    public static void main(String[] args) {
        // Utils.runFirstTimeSetupCheck();
        GUI.drawGUI();

        // Create a Gson object
        Gson gson = new Gson();

        // Create an object to be serialized to JSON
        Settings obj = new Settings("John", "deine massive fette mutter");

        try (FileWriter writer = new FileWriter("output.json")) {
            // Convert the object to JSON and write it to a file
            gson.toJson(obj, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
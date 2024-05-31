package Utils;

import java.io.IOException;

import Main.CurrencyCalculator;

public class RestartHelper {

    public static void restartApplication() throws IOException {
        String javaBin = System.getProperty("java.home") + "/bin/java";
        String classPath = System.getProperty("java.class.path");
        String className = CurrencyCalculator.class.getName();

        String[] command = { javaBin, "-cp", classPath, className };

        ProcessBuilder builder = new ProcessBuilder(command);
        builder.start();

        System.exit(0);
    }

}

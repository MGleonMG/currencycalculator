package Utils;

import java.io.IOException;

import Main.CurrencyCalculator;

/*
 * Durch diese Klasse kann das Programm sich neustarten
 */
public class RestartHelper {

    /*
     * Diese Methode öffnet im Hintergrund eine neue Instanz und schließt die offene
     */
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

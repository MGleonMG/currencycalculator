package GUI.Errors;

import javax.swing.JOptionPane;

/*
 * Diese Klasse erstellt eine Fehlermeldung und gibt dies dem Enduser zurück
 */
public class ErrorDisplay {
    public static void throwErrorPopup(String errorMsg) {
        JOptionPane.showMessageDialog(null, errorMsg, "Ein Fehler ist aufgetreten", JOptionPane.ERROR_MESSAGE);
    }
}

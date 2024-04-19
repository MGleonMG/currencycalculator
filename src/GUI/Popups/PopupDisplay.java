package GUI.Popups;

import javax.swing.JOptionPane;

/*
 * Diese Klasse erstellt eine Fehlermeldung und gibt dies dem Enduser zurück
 */
public class PopupDisplay { //TODO: @Leon refactor class name to something more fitting
    public static void throwErrorPopup(String errorMsg) {
        JOptionPane.showMessageDialog(null, errorMsg, "Ein Fehler ist aufgetreten", JOptionPane.ERROR_MESSAGE);
    }

    public static void throwInfosPopup(String title, String msg) { // TODO: @Leon check if looks good
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }
}

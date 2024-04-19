package GUI.Popups;

import javax.swing.JOptionPane;

/*
 * Diese Klasse erstellt eine Fehlermeldungs Popups und Info Popups 
 * und gibt dies dem Enduser zurück
 */
public class PopupDisplay {
    public static void throwErrorPopup(String errorMsg) {
        JOptionPane.showMessageDialog(null, errorMsg, "Ein Fehler ist aufgetreten", JOptionPane.ERROR_MESSAGE);
    }

    public static void throwInfoPopup(String title, String msg) {
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }
}

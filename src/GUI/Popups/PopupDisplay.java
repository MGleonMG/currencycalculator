package GUI.Popups;

import javax.swing.JOptionPane;

/*
 * Diese Klasse stellt zwei Funktionen bereit um 
 * Info- und Fehler Popups für den Endnutzer darzustellen
 */
public class PopupDisplay {
    public static void throwErrorPopup(String popupErrorMessage, String errorCode) {
        JOptionPane.showMessageDialog(null, popupErrorMessage + "\n\n\"" + errorCode + "\"",
                "Ein Fehler ist aufgetreten", JOptionPane.ERROR_MESSAGE);
    }

    public static void throwErrorPopup(String popupErrorMessage) {
        JOptionPane.showMessageDialog(null, popupErrorMessage,
                "Ein Fehler ist aufgetreten", JOptionPane.ERROR_MESSAGE);
    }

    public static void throwInfoPopup(String popupTitle, String popupMessage) {
        JOptionPane.showMessageDialog(null, popupMessage, popupTitle, JOptionPane.INFORMATION_MESSAGE);
    }
}

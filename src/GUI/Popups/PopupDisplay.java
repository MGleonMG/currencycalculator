package GUI.Popups;

import javax.swing.JOptionPane;

import lang.Language;

/*
 * Diese Klasse stellt zwei Funktionen bereit um 
 * Info- und Fehler Popups für den Endnutzer darzustellen
 */
public class PopupDisplay {
    /*
     * "Method Overloading" genutzt um die throwErrorPopup Methode
     * mit und ohne "errorCode" parameter nutzen zu können
     */
    public static void throwErrorPopup(String popupErrorMessage, String errorCode) {
        JOptionPane.showMessageDialog(null, popupErrorMessage + "\n\n\"" + errorCode + "\"",
                Language.getLangStringByKey("error_standard"), JOptionPane.ERROR_MESSAGE);
    }

    public static void throwErrorPopup(String popupErrorMessage) {
        JOptionPane.showMessageDialog(null, popupErrorMessage,
                Language.getLangStringByKey("error_standard"), JOptionPane.ERROR_MESSAGE);
    }

    public static void throwInfoPopup(String popupTitle, String popupMessage) {
        JOptionPane.showMessageDialog(null, popupMessage, popupTitle, JOptionPane.INFORMATION_MESSAGE);
    }
}

package GUI.Popups;

import javax.swing.JOptionPane;

import lang.Language;

/*
 * Diese Klasse stellt Funktionen bereit um den Nutzer einen
 * Bestätigung-, Info- und Fehler Popups darzustellen
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

    /*
     * Gibt dem Nutzer einen Error Popup
     */
    public static void throwErrorPopup(String popupErrorMessage) {
        JOptionPane.showMessageDialog(null, popupErrorMessage,
                Language.getLangStringByKey("error_standard"), JOptionPane.ERROR_MESSAGE);
    }

    /*
     * Gibt dem Nutzer einen Info Popup
     */
    public static void throwInfoPopup(String popupTitle, String popupMessage) {
        JOptionPane.showMessageDialog(null, popupMessage, popupTitle, JOptionPane.INFORMATION_MESSAGE);
    }

    /*
     * Gibt dem Nutzer einen Bestätigungs Popup, worauf man "Ja" und "Nein" drücken
     * kann.
     */
    public static void throwUserConfirmPopup(String popupTitle, String popupMessage, Runnable runnableConfirmAction) {
        int userChoice = JOptionPane.showConfirmDialog(null, popupMessage, popupTitle, JOptionPane.YES_NO_OPTION);

        if (userChoice == JOptionPane.YES_OPTION) {
            runnableConfirmAction.run();
        }
    }
}

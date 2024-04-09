package GUI;

import javax.swing.JOptionPane;

public class ErrorDisplay {
    public static void throwErrorPopup(String errorMsg) {
        JOptionPane.showMessageDialog(null, errorMsg, "Ein Fehler ist aufgetreten", JOptionPane.ERROR_MESSAGE);
    }
}

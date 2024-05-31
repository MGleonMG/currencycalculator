package GUI.Animations;

import javax.swing.ImageIcon;
//import javax.swing.JComponent;
import javax.swing.JLabel;

public class ClickAnimation extends Thread {

    public static void runCustomClickAnimation(JLabel guiComponent, ImageIcon icon) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int originalWidth = icon.getIconWidth(), originalHeight = icon.getIconHeight();
                    int newWidth, newHeight;

                    for (int i = 0; i < 3; i++) {
                        newWidth = (int) (originalWidth * 0.9);
                        newHeight = (int) (originalHeight * 0.9);

                        // icon.setSize();
                        // guiComponent.setIcon(icon);
                        // icon.setSize(newWidth, newHeight);
                        Thread.sleep(50);
                    }

                    guiComponent.setSize(originalWidth, originalHeight);

                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                    // Kein Popup for User nötig
                    // TODO: ..
                }
            }
        }).start(); // Animation wird gestartet
    }
}
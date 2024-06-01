package GUI.Animations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClickAnimation {
    private static boolean isAnimationBlocked = false;
    private static final float scaleFactor = 0.85F;
    private static final int animationSpeedInMs = 180;

    public static void runCustomClickAnimation(JLabel label, Runnable runnable) {
        if (isAnimationBlocked) {
            /*
             * Abfrage ob eine Animation schon läuft und
             * breche die Funktion ab falls dies der Fall ist
             */
            return;
        }

        // Blocke weitere Animationen
        isAnimationBlocked = true;

        Icon originalIcon = label.getIcon();
        ImageIcon originalImageIcon = (ImageIcon) originalIcon;
        Image originalImage = originalImageIcon.getImage();

        // Skalierung des Icons auf dem Label
        int newWidth = (int) (originalImageIcon.getIconWidth() * scaleFactor);
        int newHeight = (int) (originalImageIcon.getIconHeight() * scaleFactor);
        Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(scaledImage));

        /*
         * Timer um nach x Millisekunden wieder auf die Originalgröße
         * zu wechseln und Animationen wieder zuzulassen
         */
        Timer revertScalingTimer = new Timer(animationSpeedInMs, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setIcon(originalImageIcon);
                isAnimationBlocked = false;
                ((Timer) e.getSource()).stop();

                // Im zweiten Parameter kann beliebiger Code ausgeführt werden
                runnable.run();
            }
        });

        revertScalingTimer.setRepeats(false); // Der Timer soll sich nicht wiederholen
        revertScalingTimer.start(); // Der Timer wird hier gestartet
    }
}
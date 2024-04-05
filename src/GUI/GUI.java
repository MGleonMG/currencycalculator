package GUI;

import javax.swing.*;
import java.awt.*;

public class GUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Währungsumrechner"); // Titel setzen
        frame.setSize(1000, 1000); // Größe setzen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Beenden beim Schließen

        // JPanel erstellen
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JButton button = new JButton("Button"); // Button erstellen
        // Setzen der bevorzugten Größe des Buttons auf 100x100 Pixel
        button.setPreferredSize(new Dimension(100, 100));
        // Hintergrundfarbe des Buttons auf Schwarz setzen
        button.setBackground(Color.BLACK);
        // Den Button zum Panel hinzufügen
        panel.add(button);

        // Das Panel zum Frame hinzufügen
        frame.getContentPane().add(panel);

        frame.setVisible(true); // Frame sichtbar machen
    }
}

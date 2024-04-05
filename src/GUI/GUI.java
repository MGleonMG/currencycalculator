package GUI;

import javax.swing.*;
import java.awt.*;

public class GUI {
    public static final String title = "Währungsrechner";
    public static final String version = "1.0_alpha";

    public static void createGUI() {
        JFrame frame = new JFrame(title + " " + version);
        frame.setSize(900, 600);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // JPanel erstellen
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JButton button = new JButton("Button"); // Button erstellen
        // Setzen der bevorzugten Größe des Buttons auf 100x100 Pixel
        button.setPreferredSize(new Dimension(100, 100));
        // Hintergrundfarbe des Buttons auf Schwarz setzen
        button.setBackground(Color.BLACK);
        button.setFocusable(false);
        // Den Button zum Panel hinzufügen
        panel.add(button);

        // Das Panel zum Frame hinzufügen
        frame.getContentPane().add(panel);

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }
}

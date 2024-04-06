package GUI.Menu;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.GUI;

public class Menu {
    private JFrame frame;
    public JButton backButton;

    public Menu() {
        frame = new JFrame("Menü");
        frame.setLayout(null); 
        frame.setResizable(false);

        backButton = new JButton("Zurück"); // Hier initialisieren
        backButton.setBounds(10,10, 50,50);
        backButton.setSize(new Dimension(100,100));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Gui Klasse bei clicken
                GUI.drawGUI();
                frame.dispose(); // Schließe Menüfenster
            }
        });
        frame.add(backButton);

        createMenu(); // Rufen Sie die Methode createMenu() hier auf
    }

    public void createMenu() {
        frame.setSize(GUI.width - 300, GUI.height - 300);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            @SuppressWarnings("unused") // TODO: @Jonas kannst die zeile später einfach entfernen, ist unwichtig
            Menu menu = new Menu();
        });
    }
}

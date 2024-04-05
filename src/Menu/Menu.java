package Menu;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.GUI;

public class Menu {
    private JFrame frame;
    public JButton backButton;

    public Menu() {
        frame = new JFrame("Menü");
        frame.setLayout(new FlowLayout()); 
        frame.setResizable(false);

        backButton = new JButton("Zurück"); // Hier initialisieren
        backButton.setBounds(100,1000,10,10); // ->>>>>>>>>>>>>>>>>>>>>>>>>> Kann nicht verschoben werden idk
        backButton.setSize(new Dimension(100,100));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Gui Klasse bei clicken
                GUI.createGUI();
                frame.dispose(); // Schließe Menüfenster
            }
        });
        frame.add(backButton);

        createMenu(); // Rufen Sie die Methode createMenu() hier auf
    }

    public void createMenu() {
        frame.setSize(500, 500);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Menu menu = new Menu();
        });
    }
}

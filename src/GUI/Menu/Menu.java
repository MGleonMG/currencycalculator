// Menu.java
package GUI.Menu;

import javax.swing.*;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.GUI;

public class Menu {
    private JFrame frame;
    public JButton backButton;
    boolean isDarkMode = true;

    public Menu() {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame = new JFrame("Menü");
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setLocation(600,300);
        

        JButton lightButton = new JButton("Heller Modus");
        lightButton.setBounds(10, 220, 105, 30);
        lightButton.setBackground(Color.WHITE);
        lightButton.setForeground(Color.BLACK);

        lightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    UIManager.setLookAndFeel(new FlatLightLaf()); // Setze Look-and-Feel auf Lightmode
                    SwingUtilities.updateComponentTreeUI(frame); // Aktualisiere das UI des Frames
                    GUI.setLookAndFeel(false); // Übertrage das Theme auf die GUI-Klasse
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }); 
        frame.add(lightButton);

        JButton darkButton = new JButton("Dunkler Modus");
        darkButton.setBounds(120, 220, 115, 30);
        darkButton.setSize(new Dimension(112, 30));
        darkButton.setBackground(Color.decode("#5A5A5A"));
        darkButton.setForeground(Color.WHITE);

        darkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    UIManager.setLookAndFeel(new FlatDarkLaf()); // Setze Look-and-Feel auf Darkmode
                    SwingUtilities.updateComponentTreeUI(frame); // Aktualisiere das UI des Frames
                    GUI.setLookAndFeel(true); // Übertrage das Theme auf die GUI-Klasse
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }); 
        frame.add(darkButton);

        backButton = new JButton("Zurück");
        backButton.setBounds(475, 220, 100, 30);
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.decode("#00CCCC"));
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
            @SuppressWarnings("unused") 
            Menu menu = new Menu();
        });
    }
}

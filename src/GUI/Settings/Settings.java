// Menu.java
package GUI.Settings;

import javax.swing.*;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import GUI.GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Settings {
    private static JFrame settingsFrame;
    private static JButton backButton; 
    static boolean isDarkMode = true; // TODO: move to own class with getCurrentTheme() ??
    private static JButton darkButton = new JButton("Dunkler Modus");
    private static JButton lightButton = new JButton("Heller Modus");

    public Settings() {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        settingsFrame = new JFrame();
        GUI.updateTitle(settingsFrame, "Einstellungen");
        settingsFrame.setLayout(null);
        settingsFrame.setResizable(false);
        settingsFrame.setLocation(600, 300);

        addThemeButtons();

        addBackButton();

        createMenu();
    }

    public void createMenu() {
        settingsFrame.setSize(GUI.FRAME_WIDTH - 300, GUI.FRAME_HEIGHT - 300);
        settingsFrame.setVisible(true);
    }

    private static void addThemeButtons(){
        lightButton.setBounds(10, 220, 105, 30);
        lightButton.setBackground(Color.WHITE);
        lightButton.setForeground(Color.BLACK);
        settingsFrame.add(lightButton);

        darkButton.setBounds(120, 220, 112, 30);
        darkButton.setBackground(Color.decode("#5A5A5A"));
        darkButton.setForeground(Color.WHITE);
        settingsFrame.add(darkButton);
        
        lightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    UIManager.setLookAndFeel(new FlatLightLaf()); // Setze Look-and-Feel auf Lightmode
                    SwingUtilities.updateComponentTreeUI(settingsFrame); // Aktualisiere das UI des Frames
                    GUI.setTheme(false); // Übertrage das Theme auf die GUI-Klasse
                    lightButton.setEnabled(false);
                    darkButton.setEnabled(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        darkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    UIManager.setLookAndFeel(new FlatDarkLaf()); // Setze Look-and-Feel auf Darkmode
                    SwingUtilities.updateComponentTreeUI(settingsFrame); // Aktualisiere das UI des Frames
                    GUI.setTheme(true); // Übertrage das Theme auf die GUI-Klasse
                    lightButton.setEnabled(true);
                    darkButton.setEnabled(false);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private static void addBackButton(){
        backButton = new JButton("Zurück");
        backButton.setBounds(475, 220, 100, 30);
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.decode("#00CCCC"));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // GUI Klasse bei clicken
                GUI.drawGUI();
                settingsFrame.dispose(); // Schließe Menüfenster
            }
        });

        settingsFrame.add(backButton);
    }

    public static JButton getBackButton(){
        return backButton;
    }
}

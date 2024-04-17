package GUI.Settings;

import javax.swing.*;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import GUI.GUI;

import java.awt.Color;
import java.awt.event.*;

/*
 * Diese Klasse erstellt eine GUI für die Einstellungen 
 */
public class Settings {
    private static JFrame settingsFrame = new JFrame();
    private static JButton backBtn;
    private static JButton darkBtn = new JButton("Dunkler Modus");
    private static JButton lightBtn = new JButton("Heller Modus");
    public static boolean isDarkMode = true;

    /*
     * Diese Methode führt andere Methoden aus und fügt dadurch die einzelnen
     * Objekten hinzu
     */
    public static void drawSettingsGUI() {
        setBasicFrameProps();

        addThemeButtons();
        addBackButton();

        settingsFrame.requestFocus();
        settingsFrame.setVisible(true);
    }

    /*
     * Diese Methode erstellt das Fenster
     */
    private static void setBasicFrameProps() {
        GUI.updateTitle(settingsFrame, "Einstellungen");
        settingsFrame.setSize(GUI.FRAME_WIDTH - 300, GUI.FRAME_HEIGHT - 300);
        settingsFrame.setLayout(null);
        settingsFrame.setResizable(false);
        settingsFrame.setLocation(600, 300);
        settingsFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                GUI.drawGUI();
                settingsFrame.dispose();
            }
        });
    }

    /*
     * Diese Methode fügt die "Theme" Knöpfe hinzu, sodass man die einzelnen Themes
     * ändern kann
     */
    private static void addThemeButtons() {
        lightBtn.setBounds(10, 220, 105, 30);
        lightBtn.setBackground(Color.WHITE);
        lightBtn.setForeground(Color.BLACK);
        settingsFrame.add(lightBtn);

        darkBtn.setBounds(120, 220, 112, 30);
        darkBtn.setBackground(Color.decode("#5A5A5A"));
        darkBtn.setForeground(Color.WHITE);
        settingsFrame.add(darkBtn);

        lightBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    UIManager.setLookAndFeel(new FlatLightLaf()); // Setze Look-and-Feel auf Lightmode
                    SwingUtilities.updateComponentTreeUI(settingsFrame); // Aktualisiere das UI des Frames
                    GUI.setTheme(false); // Übertrage das Theme auf die GUI-Klasse
                    lightBtn.setEnabled(false);
                    darkBtn.setEnabled(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        darkBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    UIManager.setLookAndFeel(new FlatDarkLaf()); // Setze Look-and-Feel auf Darkmode
                    SwingUtilities.updateComponentTreeUI(settingsFrame); // Aktualisiere das UI des Frames
                    GUI.setTheme(true); // Übertrage das Theme auf die GUI-Klasse
                    lightBtn.setEnabled(true);
                    darkBtn.setEnabled(false);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    /*
     * Diese Methode fügt einen Knopf hinzu, womit man in das Hauptfenster
     * zurückkehrt
     */
    private static void addBackButton() {
        backBtn = new JButton("Zurück");
        backBtn.setBounds(475, 220, 100, 30);
        backBtn.setForeground(Color.WHITE);
        backBtn.setBackground(Color.decode("#00CCCC"));
        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // GUI Klasse bei clicken
                GUI.drawGUI();
                settingsFrame.dispose(); // Schließe Menüfenster
            }
        });

        settingsFrame.add(backBtn);
    }

    public static JButton getBackBtn() {
        return backBtn;
    }
}
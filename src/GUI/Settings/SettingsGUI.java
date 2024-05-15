package GUI.Settings;

import javax.swing.*;

import GUI.GUI;
import Utils.Data.Config.ConfigDefaults;
import Utils.Data.Config.Settings.AppTheme;
import Utils.Data.Config.Settings.AppTheme.Theme;

import java.awt.Color;
import java.awt.event.*;

/*
 * Diese Klasse erstellt eine GUI für die Einstellungen 
 */
public class SettingsGUI {
    private static JFrame settingsFrame = new JFrame();
    private static JButton configResetBtn = new JButton("<html>Einstellungen<br>zurücksetzen</html>");
    private static JButton darkBtn = new JButton("Dunkler Modus");
    private static JButton lightBtn = new JButton("Heller Modus");
    private static JLabel btnThemeSwitch = new JLabel(new ImageIcon());
    private static final ImageIcon darkmodeDM = new ImageIcon("resources/buttons/Darkmode_DM.png"),
            darkmodeLM = new ImageIcon("resources/buttons/Darkmode_LM.png"),
            lightmodeLM = new ImageIcon("resources/buttons/lightmode_LM.png"),
            lightmodeDM = new ImageIcon("resources/buttons/lightmode_DM.png");
    private static Theme currentTheme = AppTheme.getConfigAppTheme();
    // public static boolean isDarkMode = true; // TODO: @Jonas brauchen wir das
    // noch??
    private static JButton backBtn = new JButton("Zurück");

    /*
     * Diese Methode führt andere Methoden aus und fügt dadurch die einzelnen
     * Objekten hinzu
     */
    public static void drawSettingsGUI() {
        setBasicFrameProps();
        addConfigDefaultsButton();
        addThemeButtons();
        addBackButton();

        settingsFrame.requestFocus();
        settingsFrame.setVisible(true);
    }

    /*
     * Erstellt das Einstellungs Fenster indem es
     * einige props (Properties => Eigenschaften) zuweist
     */
    private static void setBasicFrameProps() {
        GUI.updateTitle(settingsFrame, "Einstellungen");
        settingsFrame.setSize(GUI.FRAME_WIDTH - 300, GUI.FRAME_HEIGHT - 20);
        settingsFrame.setLayout(null);
        settingsFrame.setResizable(false);
        settingsFrame.setLocationRelativeTo(null);
        GUI.setAppIcon(settingsFrame);
        settingsFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                GUI.drawGUI();
                settingsFrame.dispose();
            }
        });
    }

    /*
     * Diese Methode fügt einen Knopf hinzu, womit man Config Einstellungen auf die
     * Standardeinstellungen zurücksetzen kann
     */
    private static void addConfigDefaultsButton() {
        configResetBtn.setBounds(465, 410, 110, 45);
        configResetBtn.setBackground(Color.WHITE);
        configResetBtn.setForeground(Color.BLACK);
        configResetBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ConfigDefaults.restoreAllDefaults();
            }
        });

        settingsFrame.add(configResetBtn);
    }

    /*
     * Diese Methode fügt die "Theme" Knöpfe hinzu, sodass man die einzelnen Themes
     * ändern kann
     */
    private static void addThemeButtons() {
        lightBtn.setBounds(10, 500, 105, 30);
        lightBtn.setBackground(Color.WHITE);
        lightBtn.setForeground(Color.BLACK);

        if (AppTheme.getConfigAppTheme() == Theme.LIGHT_MODE) {
            lightBtn.setEnabled(false);
        }

        settingsFrame.add(lightBtn);

        darkBtn.setBounds(120, 500, 112, 30);
        darkBtn.setBackground(Color.decode("#5A5A5A"));
        darkBtn.setForeground(Color.WHITE);
        if (AppTheme.getConfigAppTheme() == Theme.DARK_MODE) {
            darkBtn.setEnabled(false);
        }

        settingsFrame.add(darkBtn);

        lightBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    GUI.setAppTheme(Theme.LIGHT_MODE); // Übertrage das Theme auf die GUI-Klasse
                    SwingUtilities.updateComponentTreeUI(settingsFrame); // Aktualisiere das UI des Frames
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
                    GUI.setAppTheme(Theme.DARK_MODE); // Übertrage das Theme auf die GUI-Klasse
                    SwingUtilities.updateComponentTreeUI(settingsFrame); // Aktualisiert das UI des Frames
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
        backBtn.setBounds(475, 500, 100, 30);
        backBtn.setForeground(Color.WHITE);
        backBtn.setBackground(Color.decode("#00CCCC"));
        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // GUI Klasse bei clicken
                GUI.redrawMain();
                settingsFrame.dispose(); // Schließe Menüfenster
            }
        });

        settingsFrame.add(backBtn);

    }
}
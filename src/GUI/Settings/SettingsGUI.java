package GUI.Settings;

import javax.swing.*;

import GUI.GUI;
import Utils.Data.Config.Settings.AppTheme;
import Utils.Data.Config.Settings.AppTheme.Theme;

import java.awt.Color;
import java.awt.event.*;

/*
 * Diese Klasse erstellt eine GUI für die Einstellungen 
 */
public class SettingsGUI {
    private static JFrame settingsFrame = new JFrame();
    private static JButton backBtn;
    private static JButton darkBtn = new JButton("Dunkler Modus");
    private static JButton lightBtn = new JButton("Heller Modus");
    public static boolean isDarkMode = true;
    public static JLabel changeLog = new JLabel();
    private static JLabel changeLogHeader = new JLabel();

    /*
     * Diese Methode führt andere Methoden aus und fügt dadurch die einzelnen
     * Objekten hinzu
     */
    public static void drawSettingsGUI() {
        setBasicFrameProps();
        addChangeLog();
        addChangeLogHeader();
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
        settingsFrame.setSize(GUI.FRAME_WIDTH - 300, GUI.FRAME_HEIGHT - 20);
        settingsFrame.setLayout(null);
        settingsFrame.setResizable(false);
        settingsFrame.setLocationRelativeTo(null);
        settingsFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                GUI.drawGUI();
                settingsFrame.dispose();
            }
        });
    }

    private static void addChangeLogHeader() {
        changeLogHeader.setBounds(170, -60, 170, 300);
        changeLogHeader.setFont(changeLogHeader.getFont().deriveFont(30f));
        changeLogHeader.setText("Changelog");
        settingsFrame.add(changeLogHeader);
    }

    private static void addChangeLog() {
        changeLog.setForeground(Color.GRAY);
        changeLog.setText("<html>" +
                "GUI & Elemente hinzugefügt <br> "
                + "Java Bibliothek für Währungen implementiert <br> "
                + "Google Werte Implementierung <br>"
                + "Einstellungsmenü mit Changelog und Farbpräferenz-Knöpfen hinzugefügt <br>"
                + "</html>");
        changeLog.setBounds(170, 1, 500, 300);
        settingsFrame.add(changeLog);
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
                    SwingUtilities.updateComponentTreeUI(settingsFrame); // Aktualisiere das UI des Frames
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

    public static JButton getBackBtn() {
        return backBtn;
    }
}
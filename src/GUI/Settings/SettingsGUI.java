package GUI.Settings;

import javax.swing.*;

import GUI.GUI;
import Utils.Data.Config.ConfigDefaults;
import Utils.Data.Config.Settings.AppTheme;
import Utils.Data.Config.Settings.AppTheme.Theme;
import lang.Language.Languages;

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
    private static JButton backBtn = new JButton("Zurück");
    private static JComboBox<String> dropdownLangSelection = new JComboBox<String>();
    private static JLabel langHeader = new JLabel("Sprache auswählen"); // muss auf die current language angepasst
                                                                        // werden

    /*
     * Diese Methode führt andere Methoden aus und
     * fügt dadurch die einzelnen Objekte hinzu
     */
    public static void drawSettingsGUI() {
        setBasicFrameProps();
        addConfigDefaultsButton();
        addThemeButtons();
        addBackButton();
        addDropdownLangSelection();
        showLangHeader();

        settingsFrame.requestFocus();
        settingsFrame.setVisible(true);
    }

    /*
     * Erstellt das Einstellungs Fenster indem es
     * einige props (Properties => Eigenschaften) zuweist
     */
    private static void setBasicFrameProps() {
        GUI.updateTitle(settingsFrame, "Einstellungen");
        settingsFrame.setSize(GUI.FRAME_WIDTH - 300, GUI.FRAME_HEIGHT - 220);
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

    private static void showLangHeader() {
        langHeader.setBounds(20, 40, 150, 30);
        langHeader.setBackground(Color.WHITE);

        settingsFrame.add(langHeader);
    }

    private static void addDropdownLangSelection() {
        // position vom Dropdown -> Kann noch dynamisch gemacht werden
        int dropdownWidth = 150;
        int dropdownHeight = 30;
        int frameWidth = settingsFrame.getWidth();
        int frameHeight = settingsFrame.getHeight();
        int xPosition = (frameWidth - dropdownWidth) / 2;
        int yPosition = (frameHeight - dropdownHeight) / 2;

        dropdownLangSelection.setBounds(xPosition, yPosition, dropdownWidth, dropdownHeight);

        for (Languages lang : Languages.values()) {
            dropdownLangSelection.addItem(lang.toString());
        }

        settingsFrame.add(dropdownLangSelection);
    }

    /*
     * Diese Methode fügt einen Knopf hinzu, womit man Config Einstellungen
     * auf die Standardeinstellungen zurücksetzen kann
     */
    private static void addConfigDefaultsButton() {
        configResetBtn.setBounds(465, 210, 110, 45);
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
     * Diese Methode fügt die "Theme" Knöpfe hinzu,
     * sodass man die einzelnen Themes ändern kann
     */
    private static void addThemeButtons() {
        lightBtn.setBounds(10, 300, 105, 30);
        lightBtn.setBackground(Color.WHITE);
        lightBtn.setForeground(Color.BLACK);
        if (AppTheme.getConfigAppTheme() == Theme.LIGHT_MODE) {
            lightBtn.setEnabled(false);
        }

        settingsFrame.add(lightBtn);

        darkBtn.setBounds(120, 300, 112, 30);
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
        backBtn.setBounds(475, 300, 100, 30);
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

    public static JButton getConfigResetBtn() {
        return configResetBtn;
    }

    public static JButton getDarkBtn() {
        return darkBtn;
    }

    public static JButton getLightBtn() {
        return lightBtn;
    }

    public static JButton getBackBtn() {
        return backBtn;
    }

}
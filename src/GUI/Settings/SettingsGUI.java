package GUI.Settings;

import javax.swing.*;

import GUI.GUI;
import Utils.Data.Config.ConfigDefaults;
import Utils.Data.Config.Settings.AppLanguage;
import Utils.Data.Config.Settings.AppTheme;
import Utils.Data.Config.Settings.AppTheme.Theme;
import lang.Language;
import lang.Language.Languages;

import java.awt.Color;
import java.awt.event.*;

/*
 * Diese Klasse erstellt eine GUI für die Einstellungen 
 */
public class SettingsGUI {
    private static JFrame settingsFrame = new JFrame();
    private static JButton configResetBtn = new JButton(Language.getLangStringByKey("reset"));
    private static JButton themeBtn = new JButton("X"/* <= remove later */);
    private static JButton backBtn = new JButton(Language.getLangStringByKey("back"));
    private static JComboBox<String> dropdownLangSelection = new JComboBox<String>();
    private static JLabel langHeader = new JLabel(Language.getLangStringByKey("select_language"));
    private static final ImageIcon darkmodeDM = new ImageIcon("resources/buttons/themes/button_darkmode_dark.png"),
            darkmodeLM = new ImageIcon("resources/buttons/themes/button_darkmode_light.png"),
            lightmodeDM = new ImageIcon("resources/buttons/themes/button_lightmode_dark.png"),
            lightmodeLM = new ImageIcon("resources/buttons/themes/button_lightmode_light.png");
    private static Theme currentTheme = AppTheme.getConfigAppTheme();

    /*
     * Diese Methode führt andere Methoden aus und
     * fügt dadurch die einzelnen Objekte hinzu
     */
    public static void drawSettingsGUI() {
        setBasicFrameProps();
        addLangHeader();
        addDropdownLangSelection();
        addConfigDefaultsButton();
        addThemeSwitchButton();
        addBackButton();

        settingsFrame.requestFocus();
        settingsFrame.setVisible(true);
    }

    /*
     * Erstellt das Einstellungs Fenster indem es
     * einige props (Properties => Eigenschaften) zuweist
     */
    private static void setBasicFrameProps() {
        GUI.updateTitle(settingsFrame, Language.getLangStringByKey("settings"));
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

    private static void addLangHeader() {
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

        if (dropdownLangSelection.getItemCount() == 0) {
            for (Languages lang : Languages.values()) {
                dropdownLangSelection.addItem(lang.toString());
            }
        }

        dropdownLangSelection.setSelectedItem(AppLanguage.getConfigAppLanguage());

        dropdownLangSelection.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Language.setAppLanguage(Languages.valueOf(dropdownLangSelection.getSelectedItem().toString()),
                            true,
                            false);
                }
            }
        });

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
    private static void addThemeSwitchButton() {
        themeBtn.setBounds(120, 300, 80, 80);
        themeBtn.setBackground(Color.decode("#5A5A5A"));
        // themeBtn.setForeground(Color.WHITE);
        if (AppTheme.getConfigAppTheme() == Theme.DARK_MODE) {
            themeBtn.setEnabled(false);
        }

        themeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    GUI.setAppTheme(Theme.DARK_MODE); // Übertrage das Theme auf die GUI-Klasse
                    SwingUtilities.updateComponentTreeUI(settingsFrame); // Aktualisiert das UI des Frames
                    lightBtn.setEnabled(true);
                    themeBtn.setEnabled(false);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        settingsFrame.add(themeBtn);
    }

    /*
     * Diese Methode fügt einen Knopf hinzu, womit man in das Hauptfenster
     * zurückkehrt
     */
    private static void addBackButton() {
        backBtn = new JButton(Language.getLangStringByKey("back"));
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

    public static JButton getThemeBtn() {
        return themeBtn;
    }

    public static JButton getLightBtn() {
        return lightBtn;
    }

    public static JButton getBackBtn() {
        return backBtn;
    }

}
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
import java.awt.Image;
import java.awt.event.*;

/*
 * Diese Klasse erstellt eine GUI für die Einstellungen 
 */
public class SettingsGUI {
    private static JFrame settingsFrame = new JFrame();
    private static JButton configResetBtn = new JButton(Language.getLangStringByKey("reset"));
    private static JButton backBtn = new JButton(Language.getLangStringByKey("back"));
    private static JComboBox<String> dropdownLangSelection = new JComboBox<String>();
    private static JLabel langHeader = new JLabel(Language.getLangStringByKey("select_language"));
    private static JLabel themeLblBtn = new JLabel();
    private static final ImageIcon darkmodeIcon = new ImageIcon(
            new ImageIcon(SettingsGUI.class.getResource("/resources/buttons/themes/button_darkmode_dark.png"))
                    .getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)),
            lightmodeIcon = new ImageIcon(
                    new ImageIcon(SettingsGUI.class.getResource("/resources/buttons/themes/button_lightmode_light.png"))
                            .getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));

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
        themeLblBtn.setBounds(20, 300, 25, 25);
        setThemeIcon();

        themeLblBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Theme currenTheme = AppTheme.getConfigAppTheme();

                    if (currenTheme == Theme.DARK_MODE) {
                        GUI.setAppTheme(Theme.LIGHT_MODE);
                    } else if (currenTheme == Theme.LIGHT_MODE) {
                        GUI.setAppTheme(Theme.DARK_MODE);
                    }

                    setThemeIcon();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        settingsFrame.add(themeLblBtn);
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

    private static void setThemeIcon() {
        Theme currenTheme = AppTheme.getConfigAppTheme();

        if (currenTheme == Theme.DARK_MODE) {
            themeLblBtn.setIcon(lightmodeIcon);
        } else if (currenTheme == Theme.LIGHT_MODE) {
            themeLblBtn.setIcon(darkmodeIcon);
        }
    }

    public static JFrame getSettingsFrame() {
        return settingsFrame;
    }

    public static JButton getConfigResetBtn() {
        return configResetBtn;
    }

    public static JButton getBackBtn() {
        return backBtn;
    }

}
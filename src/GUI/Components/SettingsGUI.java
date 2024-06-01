package GUI.Components;

import javax.swing.*;

import GUI.GUI;
import GUI.Animations.ClickAnimation;
import GUI.Popups.PopupDisplay;
import Utils.RestartHelper;
import Utils.Data.Config.ConfigDefaults;
import Utils.Data.Config.Settings.AppLanguage;
import Utils.Data.Config.Settings.AppTheme;
import Utils.Data.Config.Settings.AppTheme.Theme;
import lang.Language;
import lang.Language.Languages;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.*;
import java.io.IOException;

/*
 * Diese Klasse erstellt eine GUI für die Einstellungen 
 */
public class SettingsGUI {
    // Komponenten
    private static JLabel settingsLblBtn = new JLabel();
    private static JLabel settingsSliderLbl;
    private static JLabel themeLblBtn = new JLabel();
    private static JButton configResetBtn = new JButton();
    private static JComboBox<String> languageDropdown = new JComboBox<String>();

    // Icons
    private static final ImageIcon darkmodeIcon = new ImageIcon(
            new ImageIcon(SettingsGUI.class.getResource("/resources/buttons/themes/button_darkmode_dark.png"))
                    .getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)),
            lightmodeIcon = new ImageIcon(
                    new ImageIcon(SettingsGUI.class.getResource("/resources/buttons/themes/button_lightmode_light.png"))
                            .getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));

    /*
     * Diese Methode fügt einzelne Objekte in der GUI hinzu
     */
    public static void addAllComponents() {
        // Komponenten
        addThemeSwitchButton();
        addConfigDefaultsButton();
        addLanguageDropdown();

        addSettingsLblBtn();
        addSettingsSlider();

        // Komponenten skalieren und platzieren
        setComponentBounds();

        // Slider für die Einstellungen soll am Anfang geschlossen sein
        minimizeSettingsSlider();
    }

    /*
     * Diese Methode setzt die Größen und Positionen der jeweiligen Komponenten
     */
    private static void setComponentBounds() {
        settingsLblBtn.setBounds(GUI.getWindowWidth() - 80, GUI.getWindowHeight() - 95, 50, 50);
        languageDropdown.setBounds(GUI.getWindowWidth() - 260, settingsLblBtn.getY() + 12,
                150, 30);
        configResetBtn.setBounds(GUI.getWindowWidth() - 370, settingsLblBtn.getY() + 6,
                100, 38);
        themeLblBtn.setBounds(GUI.getWindowWidth() - 410, settingsLblBtn.getY() + 12, 25, 25);
        settingsSliderLbl.setBounds(GUI.getWindowWidth() - 425, settingsLblBtn.getY() - 2, 400, 55);
    }

    /*
     * Fügt das Dropdown für die Sprachauswahl hinzu
     */
    private static void addLanguageDropdown() {
        for (Languages language : Languages.values()) {
            languageDropdown.addItem(formattedLanguageToString(language));
        }

        languageDropdown.setSelectedItem(formattedLanguageToString(AppLanguage.getConfigAppLanguage()));

        languageDropdown.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Language.setAppLanguage(
                            Languages.valueOf(languageDropdown.getSelectedItem().toString().toUpperCase()),
                            true,
                            false);

                    PopupDisplay.throwUserConfirmPopup(Language.getLangStringByKey("restart"),
                            Language.getLangStringByKey("recommended"), () -> {
                                try {
                                    RestartHelper.restartApplication();
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                            });
                }
            }
        });

        GUI.getAppWindow().add(languageDropdown);
    }

    private static String formattedLanguageToString(Languages language) {
        return language.toString().toLowerCase().substring(0, 1).toUpperCase()
                + language.toString().toLowerCase().substring(1);
    }

    /*
     * Diese Methode erstellt einen Knopf, womit man die Konfigurationen
     * zurücksetzen kann
     */
    private static void addConfigDefaultsButton() {
        configResetBtn.setText(Language.getLangStringByKey("reset"));

        configResetBtn.setBackground(Color.WHITE);
        configResetBtn.setForeground(Color.BLACK);
        configResetBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PopupDisplay.throwUserConfirmPopup(Language.getLangStringByKey("confirm"),
                        Language.getLangStringByKey("confirmText"), () -> {
                            ConfigDefaults.restoreAllDefaults();
                        });

            }
        });

        GUI.getAppWindow().add(configResetBtn);
    }

    /*
     * Diese Methode fügt den "Theme" Knopf hinzu.
     * Dadurch kann man zwischen dem Light- und Darkmode wechseln
     */
    private static void addThemeSwitchButton() {
        setThemeIcon();

        themeLblBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    ClickAnimation.runCustomClickAnimation(themeLblBtn, () -> {

                        Theme currentTheme = AppTheme.getConfigAppTheme();

                        if (currentTheme == Theme.DARK_MODE) {
                            GUI.setAppTheme(Theme.LIGHT_MODE);
                        } else if (currentTheme == Theme.LIGHT_MODE) {
                            GUI.setAppTheme(Theme.DARK_MODE);
                        }

                        setThemeIcon();
                    });

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        GUI.getAppWindow().add(themeLblBtn);
    }

    /*
     * Diese Methode gibt dem Theme Label ein Icon
     */
    private static void setThemeIcon() {
        Theme currentTheme = AppTheme.getConfigAppTheme();

        if (currentTheme == Theme.DARK_MODE) {
            themeLblBtn.setIcon(lightmodeIcon);
        } else if (currentTheme == Theme.LIGHT_MODE) {
            themeLblBtn.setIcon(darkmodeIcon);
        }
    }

    /*
     * Erstellt ein klickbares Label mit Icon
     * das als Button für das Einstellungs Menu agiert
     */
    private static void addSettingsLblBtn() {
        ImageIcon originalIcon = new ImageIcon(GUI.class.getResource("/resources/buttons/button_settings.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        settingsLblBtn.setIcon(scaledIcon);

        settingsLblBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ClickAnimation.runCustomClickAnimation(settingsLblBtn, () -> {

                    if (settingsSliderLbl.isVisible()) {
                        minimizeSettingsSlider();
                    } else {
                        extendSettingsSlider();
                    }
                });
            }
        });

        GUI.getAppWindow().add(settingsLblBtn);
    }

    /*
     * Zeigt die Einstellungen mithilfe eines Sliders an
     */
    private static void addSettingsSlider() {
        settingsSliderLbl = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2Graphics = (Graphics2D) g;
                g2Graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2Graphics.setColor(new Color(125, 125, 125, 125));
                g2Graphics.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
                g2Graphics.setColor(Color.BLACK);
                g2Graphics.drawString(getText(), 10, 30);
            }
        };

        settingsSliderLbl.setOpaque(true);

        GUI.getAppWindow().add(settingsSliderLbl);
    }

    /*
     * "Öffnet" die Einstellungen
     */
    private static void extendSettingsSlider() {
        settingsSliderLbl.setVisible(true);

        themeLblBtn.setVisible(true);
        configResetBtn.setVisible(true);
        languageDropdown.setVisible(true);

        GUI.getAppWindow().revalidate();
        GUI.getAppWindow().repaint();
    }

    /*
     * "Schließt" die Einstellungen
     */
    private static void minimizeSettingsSlider() {
        settingsSliderLbl.setVisible(false);

        themeLblBtn.setVisible(false);
        configResetBtn.setVisible(false);
        languageDropdown.setVisible(false);

        GUI.getAppWindow().revalidate();
        GUI.getAppWindow().repaint();
    }

    public static JButton getConfigResetBtn() {
        return configResetBtn;
    }
}
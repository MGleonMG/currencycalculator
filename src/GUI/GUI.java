package GUI;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import GUI.Components.InputOutput;
import GUI.Components.SettingsGUI;
import GUI.Popups.PopupDisplay;
import Main.CurrencyCalculator;
import Utils.Utils;
import Utils.Data.Calculations;
import Utils.Data.Config.Settings.AppTheme;
import Utils.Data.Config.Settings.LastCalculation;
import Utils.Data.Config.Settings.AppTheme.Theme;
import lang.Language;

/*
 * Diese Klasse erstellt das "Graphical User Interface"
 * Dadurch muss der Enduser nicht auf dem Terminal arbeiten
 */
public class GUI {

    // Grund Infos das das Haupt JFrame
    public static String title;
    private static final int FRAME_WIDTH = 900, FRAME_HEIGHT = 600;
    private static final ImageIcon icon = new ImageIcon(GUI.class.getResource("/resources/app_icon/app_icon.png"));

    // Komponenten
    public static JFrame frame = new JFrame(); // stays
    private static JButton saveBtn = new JButton(); // misc class
    private static JButton loadBtn = new JButton(); // misc class
    private static JLabel presetLabel = new JLabel(); // misc class
    private static JLabel fadeLabel = new JLabel(); // misc class
    public static JLabel outputLabel = new JLabel("", SwingConstants.CENTER); // IO class
    private static JLabel headlineLabel = new JLabel("blankblankblankblankblank"); // stays
    private static JLabel authorLabel = new JLabel(CurrencyCalculator.getAppVersion() + " by Leon, Jonas, Ewin"); // misc class
    private static JLabel clipboardLblBtn = new JLabel(); // misc class

    // Diese Variablen speichern den Betrag des Nutzers
    private static String inputValue;
    private static double inputValueResult;

    // Diese Variablen speichern die ISO-codes von den Währungen
    private static String baseCurResult, targetCurResult;

    // Diese Variabel wird für das Headline benutzt.
    private static int textWidth;

    public static JFrame getAppWindow() {
        return frame;
    }

    public static int getWindowWidth() {
        return FRAME_WIDTH;
    }

    public static int getWindowHeight() {
        return FRAME_HEIGHT;
    }

    /*
     * Diese Methode führt andere Methoden aus
     * und fügt dadurch die einzelnen Komponenten hinzu
     */
    public static void drawGUI() {
        setBasicFrameProps();

        SettingsGUI.addAllComponents();
        InputOutput.addAllComponents();
        // TODO @Ewin yallah deine Misc add all comp function hier dann bist du chillend auf funktion fickend ok_hand_emoticon
        // .addAllComponents();

        addCopyOutputLblBtn();
        addPresetLabel();
        addSaveCalculationButton();
        addLoadLastCalculationButton();
        addFadeLabel();
        addFooter();

        setAppTheme(AppTheme.getConfigAppTheme());
        updateDisplayedLanguage(true);

        frame.requestFocus();
        frame.setVisible(true);
    }

    // Bringt das Hauptfenster zurück
    public static void openMainWindow() {
        frame.setVisible(true);
    }

    public static void updateTitle(JFrame jframe) {
        jframe.setTitle(Language.getLangStringByKey("title") + " " + CurrencyCalculator.getAppVersion());
    }

    public static void updateTitle(JFrame jframe, String rawTitleAddition) {
        String titleAddition = " - " + rawTitleAddition;
        jframe.setTitle(Language.getLangStringByKey("title") + " " + CurrencyCalculator.getAppVersion()
                + (rawTitleAddition != "" ? titleAddition : ""));
    }

    public static void setAppIcon(JFrame jframe) {
        jframe.setIconImage(icon.getImage());
    }

    /*
     * Erstellt das Hauptfenster für die GUI
     */
    private static void setBasicFrameProps() {
        updateTitle(frame, "");
        setAppIcon(frame);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);

        headlineLabel.setFont(headlineLabel.getFont().deriveFont(30f));

        // Durch den Inhalt wird das Label mittig gesetzt
        headlineLabel.setBounds((FRAME_WIDTH - getTextWidth()) / 2, 25, getTextWidth(), 50);

        frame.add(headlineLabel);
    }

    /*
     * Erstellt einen "Kopier" Knopf
     * 
     * Es nimmt das Ergebnis und steckt es in den Clipboard
     */
    private static void addCopyOutputLblBtn() {
        clipboardLblBtn.setBounds(420, 405, 100, 30);

        /*
         * Nimmt das originale .png und skaliert es runter zu der angegebenen Auflösung
         * Scale_Smooth hinterlässt dem Bild einen AA (Anti Aliasing) Effekt
         */
        ImageIcon originalIcon = new ImageIcon(GUI.class.getResource("/resources/buttons/button_copy.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(55, 55, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        clipboardLblBtn.setIcon(scaledIcon);
        clipboardLblBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (targetCurResult != null) {
                    Utils.copyToClipboard();
                    runCustomFadeLabel("Kopiert!", clipboardLblBtn.getX() + 50, clipboardLblBtn.getY(), 70, 25);
                } else {
                    PopupDisplay.throwErrorPopup("Derzeit liegt kein Ergebnis zum kopieren vor.");
                }
            }
        });

        frame.add(clipboardLblBtn);
    }

    private static void addFooter() {
        authorLabel.setBounds(15, FRAME_HEIGHT - 60, 200, 20);
        authorLabel.setForeground(Color.GRAY);

        frame.add(authorLabel);
    }

    /*
     * Diese Methode setzt den Theme fest
     * 
     * Je nachdem, wie es der Enduser mag, wechselt es sich zwischen dem Light- und
     * Darkmode
     */
    public static void setAppTheme(Theme theme) {
        try {
            if (theme == Theme.DARK_MODE) {
                UIManager.setLookAndFeel(new FlatDarkLaf());
                AppTheme.setConfigAppTheme(Theme.DARK_MODE);
            } else if (theme == Theme.LIGHT_MODE) {
                UIManager.setLookAndFeel(new FlatLightLaf());
                AppTheme.setConfigAppTheme(Theme.LIGHT_MODE);
            }
            SwingUtilities.updateComponentTreeUI(frame);

        } catch (Exception e) {
            PopupDisplay.throwErrorPopup(Language.getLangStringByKey("error_theme_get") + "\n" +
                    Language.getLangStringByKey("error_theme_look"), e.getMessage());
        }
    }

    /*
     * Diese Methode erstellt einen Knopf, um die Daten zu speichern
     */
    private static void addSaveCalculationButton() {
        saveBtn.setBounds(50, 450, 100, 25);
        saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        if (inputValue == null || baseCurResult == null || targetCurResult == null) {
                            PopupDisplay.throwErrorPopup(
                                    Language.getLangStringByKey("error_lastcalc_isnull"));
                        } else {
                            LastCalculation.setConfigLastCalc(baseCurResult, targetCurResult, inputValue);
                            // TODO: Add Lang support below
                            runCustomFadeLabel("Gespeichert", 200, 450, 100, 25);
                        }
                    }
                });
            }
        });

        frame.add(saveBtn);
    }

    /*
     * Diese Methode erstellt einen Knopf, um gespeicherte Daten zu laden
     */
    private static void addLoadLastCalculationButton() {
        loadBtn.setBounds(50, 480, 100, 25);
        loadBtn.addActionListener((e) -> {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    String[] config = LastCalculation.getConfigLastCalc();
                    // Es löscht die "" im String
                    config[0] = config[0].replace("\"", "");
                    config[1] = config[1].replace("\"", "");
                    config[2] = config[2].replace("\"", "");

                    baseCurResult = (config[0]);
                    targetCurResult = (config[1]);
                    inputValue = (config[2]);

                    inputValueResult = Double.parseDouble(GUI.inputValue);
                    Calculations.runThreadedCalculation();

                }
            });

        });

        frame.add(loadBtn);
    }

    private static void addPresetLabel() {
        presetLabel.setBounds(50, 420, 100, 25);

        frame.add(presetLabel);
    }

    private static void addFadeLabel() {
        fadeLabel.setBounds(200, 450, 100, 25);
        fadeLabel.setVisible(false);

        frame.add(fadeLabel);
    }

    /*
     * Diese Methode erstellt ein Label, dass dem Benutzer zurückgibt,
     * dass die eingegebenen Daten gespeichert sind.
     */
    public static void runCustomFadeLabel(String text, int locactionX, int locactionY, int width, int height) {
        fadeLabel.setText(text);
        fadeLabel.setBounds(locactionX, locactionY, width, height);

        fadeLabel.setVisible(true);
        Timer timer = new Timer(50, new ActionListener() {
            private float opacity = 1.0f; // opacity = transparenz

            /*
             * Nachdem der User die Daten abgespeichert hat,
             * erscheint das Label für ein paar Sekunden
             * und verschwindet wieder.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                // Die opacity wird mit jedem Durchlauf verringert
                // ..erreicht sie 0,00, so wird das Label "entfernt"
                opacity -= 0.02f;
                if (opacity <= 0.0f) {
                    ((Timer) e.getSource()).stop();
                    fadeLabel.setVisible(false);
                }
            }
        });
        timer.start();
    }

    /*
     * Updatet die dargestellte Sprache auf den GUI Komponenten
     */
    public static void updateDisplayedLanguage(boolean calledOnStartup) {
        // update Programm Titel
        title = Language.getLangStringByKey("title");
        updateTitle(frame);

        // update alle Hauptkomponenten
        headlineLabel.setText(Language.getLangStringByKey("title"));
        InputOutput.getSearchBarBaseCur().setText(Language.getLangStringByKey("searchBar"));
        InputOutput.getSearchBarTargetCur().setText(Language.getLangStringByKey("searchBar"));
        InputOutput.getCalculateButton().setText(Language.getLangStringByKey("calculateBtn"));
        presetLabel.setText(Language.getLangStringByKey("presetLabel"));
        saveBtn.setText(Language.getLangStringByKey("saveBtn"));
        loadBtn.setText(Language.getLangStringByKey("loadBtn"));
        fadeLabel.setText(Language.getLangStringByKey("fadeLabel"));

        if (!calledOnStartup) {
            System.out.println("\nhere!\n");
            Utils.refreshCurrencyDropdowns();
        }

        SettingsGUI.getConfigResetBtn().setText(Language.getLangStringByKey("reset"));
    }

    public static double getAmount() {
        return inputValueResult;
    }

    public static String getBaseCur() {
        return baseCurResult;
    }

    public static String getTargetCur() {
        return targetCurResult;
    }

    /*
     * Diese Methode nimmt die Länge der HeadlineLabel raus
     * und setzt es in eine Variable. Dies wird verwendet, um den Label
     * mittig zu halten.
     */
    private static int getTextWidth() {

        FontMetrics fontMetrics = headlineLabel.getFontMetrics(headlineLabel.getFont());
        textWidth = fontMetrics.stringWidth(headlineLabel.getText());
        return textWidth;
    }
}

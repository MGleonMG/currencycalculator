package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Map;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import GUI.Popups.PopupDisplay;
import GUI.Settings.SettingsGUI;
import Utils.Utils;
import Utils.Data.Calculations;
import Utils.Data.Config.Settings.AppTheme;
import Utils.Data.Config.Settings.LastCalculation;
import Utils.Data.Config.Settings.AppTheme.Theme;

/*
 * Diese Klasse erstellt das "Graphical User Interface"
 * Dadurch muss der Enduser nicht auf dem Terminal arbeiten
 */
public class GUI {

    // static final vars
    public static final String TITLE = "Währungsrechner", VERSION = "1.0_indev";
    public static final int FRAME_WIDTH = 900, FRAME_HEIGHT = 600;
    private static final ImageIcon icon = new ImageIcon(GUI.class.getResource("/resources/app_icon/app_icon.png"));

    // Components
    private static JFrame frame = new JFrame();
    private static JTextField inputField = new JTextField();
    public static JTextField searchBarBaseCur = new JTextField("Nach Währung Filtern"),
            searchBarTargetcur = new JTextField("Nach Währung Filtern");
    private static JComboBox<String> dropdownBaseCur;
    private static JComboBox<String> dropdownTargetCur;
    private static JButton calculateBtn = new JButton("Umrechnen");
    private static JButton clipboardBtn = new JButton();
    private static JButton saveBtn = new JButton("Speichern");
    private static JButton loadBtn = new JButton("Laden");
    private static JLabel presetLabel = new JLabel("Letzte Rechnung");
    private static JLabel fadeLabel = new JLabel("Gespeichert!");
    private static JLabel outputLabel = new JLabel("", SwingConstants.CENTER);
    private static JLabel headlineLabel = new JLabel("Währungsrechner");
    private static JLabel authorLabel = new JLabel(VERSION + " by Leon, Jonas, Ewin");
    private static JLabel settingsLblBtn = new JLabel();
    private static JLabel loadingGIF = new JLabel();

    /*
     * Diese Variablen speichern den Betrag des Nutzers
     */
    private static String inputValue;
    private static double inputValueResult;

    /*
     * Diese Variablen speichern die ISO-codes von den Währungen
     */
    private static String baseCurResult, targetCurResult;

    /*
     * Dieser Variabel wird für das Headline benutzt.
     */
    private static int textWidth;

    /*
     * Diese Methode führt andere Methoden aus
     * und fügt dadurch die einzelnen Komponenten hinzu
     */
    public static void drawGUI() {
        setBasicFrameProps();

        addCalculateButton();
        addDropdownWithFilters();
        addInputOutput();
        addLoadingCircleGIF();
        addCopyOutputButton();
        addPresetLabel();
        addSaveCalculationButton();
        addLoadCalculationButton();
        addSettingsLblBtn();
        addFadeLabel();
        addFooter();

        setAppTheme(AppTheme.getConfigAppTheme());

        frame.requestFocus();
        frame.setVisible(true);
    }

    // Bringt das Hauptfenster zurück
    public static void redrawMain() {
        frame.setVisible(true);
    }

    public static void updateTitle(JFrame jframe, String rawTitleAddition) {
        String titleAddition = " - " + rawTitleAddition;
        jframe.setTitle(TITLE + " " + VERSION + (rawTitleAddition != "" ? titleAddition : ""));
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
     * Erstellt eine Suchleiste
     * 
     * Mit dieser Methode kann man eine Währung suchen und dadurch schnell finden
     */
    private static void addSearchBars() {

        // Erstellt links eine Suchleiste
        searchBarBaseCur.setBounds(50, 215, 290, 20);
        searchBarBaseCur.setHighlighter(null);
        searchBarBaseCur.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchBarBaseCur.getText().equals("Nach Währung Filtern")) {
                    searchBarBaseCur.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchBarBaseCur.getText().isEmpty()) {
                    searchBarBaseCur.setText("Nach Währung Filtern");
                }
            }
        });

        searchBarBaseCur.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String searchText = searchBarBaseCur.getText().toLowerCase();
                DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) dropdownBaseCur.getModel();
                model.removeAllElements();

                for (Map.Entry<String, String> currency : Utils.getAllCurrencies()) {
                    String isoCode = currency.getKey();
                    String currencyName = currency.getValue();

                    if (currencyName.toLowerCase().contains(searchText) || isoCode.toLowerCase().contains(searchText)) {
                        model.addElement(currencyName + " (" + isoCode + ")");
                    }
                }

                // Dropdown öffnen bei Eingabe in search bars
                dropdownBaseCur.showPopup();
            }
        });

        /*
         * Erstellt rechts eine Suchleiste
         */
        searchBarTargetcur.setBounds(FRAME_WIDTH - 340, 215, 290, 20);
        searchBarTargetcur.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchBarTargetcur.getText().equals("Nach Währung Filtern")) {
                    searchBarTargetcur.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchBarTargetcur.getText().isEmpty()) {
                    searchBarTargetcur.setText("Nach Währung Filtern");
                }
            }
        });

        searchBarTargetcur.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String searchText = searchBarTargetcur.getText().toLowerCase();
                DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) dropdownTargetCur.getModel();
                model.removeAllElements();

                for (Map.Entry<String, String> currency : Utils.getAllCurrencies()) {
                    String isoCode = currency.getKey();
                    String currencyName = currency.getValue();

                    if (currencyName.toLowerCase().contains(searchText) || isoCode.toLowerCase().contains(searchText)) {
                        model.addElement(currencyName + " (" + isoCode + ")");
                    }
                }

                // Dropdown öffnen bei Eingabe in search bars
                dropdownTargetCur.showPopup();
            }
        });
    }

    /*
     * Erstellt zwei Dropdowns, mit denen man Währungen auswählen kann.
     * 
     * Dabei nimmt es den Namen der Währung, mit dem jeweiligen
     * ISO Code auf. Es wird verwendet für den Empfang des Wechselskurses.
     */
    private static void addDropdownWithFilters() {
        addSearchBars();

        dropdownBaseCur = new JComboBox<>();
        dropdownBaseCur.setBounds(50, 250, 290, 50);

        dropdownTargetCur = new JComboBox<>();
        dropdownTargetCur.setBounds(FRAME_WIDTH - 340, 250, 290, 50);

        for (Map.Entry<String, String> currency : Utils.getAllCurrencies()) {
            String isoCode = currency.getKey();
            String currencyName = currency.getValue();

            dropdownBaseCur.addItem(currencyName + " (" + isoCode + ")");
            dropdownTargetCur.addItem(currencyName + " (" + isoCode + ")");
        }

        /*
         * Ein ItemListener reagiert auf Änderungen bezüglich der Auswahl
         * und extrahiert mit regex syntax regeln den ISO Code aus der ausgewählten
         * Währung
         */
        dropdownBaseCur.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    baseCurResult = (String) dropdownBaseCur.getSelectedItem(); // Erfasst die Ausgewählte Währung
                    baseCurResult = baseCurResult.split("\\(")[1].replace(")", "").trim();
                    String[] parts = baseCurResult.split("\\)"); // Speichert den Inhalt der Klammer
                    for (String part : parts) { // Überprüft, ob es in der Klammer zahlen gibt.
                        if (Utils.containsDigit(part)) {
                            PopupDisplay.throwErrorPopup("Die angegebene Währung wird nicht mehr benutzt");
                        }
                    }
                }
            }
        });

        dropdownTargetCur.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    targetCurResult = (String) dropdownTargetCur.getSelectedItem();
                    targetCurResult = targetCurResult.split("\\(")[1].replace(")", "").trim();
                    String[] parts = targetCurResult.split("\\)");
                    for (String part : parts) {
                        if (Utils.containsDigit(part)) {
                            PopupDisplay.throwErrorPopup("Die angegebene Währung wird nicht mehr benutzt");
                        }
                    }
                }
            }
        });

        /*
         * Initialisert die Methode, um mit Pfeiltasten zu navigieren
         */
        addArrowKeyNavigationToComboBox(dropdownBaseCur);
        addArrowKeyNavigationToComboBox(dropdownTargetCur);

        frame.add(searchBarBaseCur);
        frame.add(searchBarTargetcur);

        frame.add(dropdownBaseCur);
        frame.add(dropdownTargetCur);
    }

    /*
     * Diese Methode erstellt einen "Rechner" Knopf
     * Es nimmt den Betrag auf und wird in der Umwandlung der Währung verrechnet
     */
    private static void addCalculateButton() {
        calculateBtn.setBounds(400, 250, 100, 25);
        calculateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        inputValue = inputField.getText();
                        inputValueResult = Double.parseDouble(GUI.inputValue);

                        Calculations.runThreadedCalculation();
                    }
                });
            }
        });

        frame.add(calculateBtn);
    }

    /*
     * Erstellt einen "Kopier" Knopf
     * 
     * Es nimmt das Ergebnis und steckt es in den Clipboard
     */
    private static void addCopyOutputButton() {
        clipboardBtn.setBounds(400, 405, 100, 30);

        /*
         * Nimmt das originale .png und skaliert es runter zu der angegebenen Auflösung
         * Scale_Smooth hinterlässt dem Bild einen AA (Anti Aliasing) Effekt
         */
        ImageIcon originalIcon = new ImageIcon(GUI.class.getResource("/resources/buttons/icon_copy-button-dark.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        clipboardBtn.setIcon(scaledIcon);
        clipboardBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        Utils.copyToClipboard();
                    }
                });
            }
        });

        frame.add(clipboardBtn);
    }

    /*
     * Fügt Komponenten hinzu die für input des users und output verantwortlich sind
     */
    private static void addInputOutput() {
        outputLabel.setBounds(300, 285, 300, 150);
        setOutput("Bitte wähle Währungen aus und gib einen Betrag ein.");
        inputField.setBounds(405, 290, 90, 30);

        frame.add(inputField);
        frame.add(outputLabel);
    }

    /*
     * Erstellt ein klickbares Label mit Icon
     * das als Button für das Einstellungs Menu agiert
     */
    private static void addSettingsLblBtn() {
        ImageIcon originalIcon = new ImageIcon(GUI.class.getResource("/resources/buttons/settings_button.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        settingsLblBtn.setBounds(GUI.FRAME_WIDTH - 80, GUI.FRAME_HEIGHT - 95, 50, 50);
        settingsLblBtn.setIcon(scaledIcon);

        settingsLblBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SettingsGUI.drawSettingsGUI();
                frame.setVisible(false);
            }
        });

        frame.add(settingsLblBtn);
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
            PopupDisplay.throwErrorPopup("Es ist ein Fehler beim setzen des Themes aufgetreten.\n" +
                    "Das Programm wird möglicherweise etwas anders aussehen als sonst!", e.getMessage());
        }
    }

    /*
     * Diese Methode erstellt die Möglichkeit im Dropdown Menü mit Pfeiltasten zu
     * navigieren
     */
    public static void addArrowKeyNavigationToComboBox(JComboBox<String> comboBox) {
        InputMap inputMap = comboBox.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        ActionMap actionMap = comboBox.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "UpAction");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "DownAction");

        actionMap.put("UpAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = comboBox.getSelectedIndex();
                if (index > 0) {
                    comboBox.setSelectedIndex(index - 1);
                }
            }
        });

        actionMap.put("DownAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = comboBox.getSelectedIndex();
                if (index < comboBox.getItemCount() - 1) {
                    comboBox.setSelectedIndex(index + 1);
                }
            }
        });
    }

    /*
     * Funktion im den Output auf das Label zu setzten
     */
    public static void setOutput(String output) {
        // JLabels akzeptueren kein normales \n als Line break, deshalb benutzen wir
        // hier HTML formatierung mit dem <br> Tag
        outputLabel.setText("<html>" + output.replaceAll("\n", "<br>") + "</html>");
    }

    /*
     * Diese Methode zeigt dem Endnutzer, dass das Programm am Arbeiten ist und den
     * Wechselkurs herausfindet
     */
    public static void displayAsLoading(boolean isLoading) {
        if (isLoading) {
            calculateBtn.setEnabled(false);
            setOutput("Lädt...");
            loadingGIF.setVisible(true);
            calculateBtn.setText("Lädt...");
        } else {
            calculateBtn.setEnabled(true);
            loadingGIF.setVisible(false);
            calculateBtn.setText("Umrechnen");
        }
    }

    /*
     * Diese Methode fügt ein GIF hinzu,
     * sobald der User auf "umrechnen" gedrückt hat
     */
    private static void addLoadingCircleGIF() {
        ImageIcon originalIcon = new ImageIcon(GUI.class.getResource("/resources/buttons/button_loading.gif"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(150, 100, Image.SCALE_FAST);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        // Image.SCALE_FAST damit es das GIF anzeigt
        loadingGIF.setIcon(scaledIcon);
        loadingGIF.setBounds(420, 260, 200, 200);
        frame.add(loadingGIF);
        loadingGIF.setVisible(false);
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
                                    "Es wurde noch keine Rechnung durchgeführt die gespeichert werden könnte.");
                        } else {
                            LastCalculation.setConfigLastCalc(baseCurResult, targetCurResult, inputValue);
                            runFadeLabel();
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
    private static void addLoadCalculationButton() {
        loadBtn.setBounds(50, 480, 100, 25);
        loadBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
            }
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
    public static void runFadeLabel() {
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
                opacity -= 0.05f;
                if (opacity <= 0.0f) {
                    ((Timer) e.getSource()).stop();
                    fadeLabel.setVisible(false);
                }
            }
        });
        timer.start();
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

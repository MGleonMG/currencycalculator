package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Map;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import GUI.Errors.ErrorDisplay;
import GUI.Settings.Settings;
import Utils.Utils;

/*
 * Diese Klasse erstellt einen "Graphical User Interface"
 * 
 * Dadurch muss der Enduser nicht auf dem Terminal arbeiten
 */
public class GUI {
    // static final vars
    public static final String TITLE = "Währungsrechner", VERSION = "1.0_alpha";
    public static final int FRAME_WIDTH = 900, FRAME_HEIGHT = 600;
    private static ImageIcon icon = new ImageIcon(GUI.class.getResource("/resources/app_icon/app_icon.png"));

    // Helpers
    private static boolean isDarkMode = true;

    // Components
    private static JFrame frame = new JFrame();
    private static JLabel headlineLabel = new JLabel("Währungsrechner");
    private static JTextField searchBarBaseCur = new JTextField("Nach Währung Filtern"),
            searchBarTargetcur = new JTextField("Nach Währung Filtern");
    private static JComboBox<String> dropdownBaseCur, dropdownTargetCur;
    private static JButton calculateBtn = new JButton("Umrechnen");
    private static JButton clipboardBtn = new JButton();
    private static JTextField inputField = new JTextField();
    private static JLabel outputLabel = new JLabel("", SwingConstants.CENTER);
    private static JButton menuBtn = new JButton("Einstellungen");
    private static JLabel authorLabel = new JLabel(VERSION + " by Leon, Jonas, Ewin");
    private static JLabel menuBtnTest = new JLabel(new ImageIcon("src/resources/buttons/settings_button.png"));

    /*
     * TODO Code Optimization
     */
    private static String inputValue;
    private static double inputValueAsDouble;

    private static String baseCur;
    private static String targetCur;

    private static String baseCurResult;
    private static String targetCurResult;

    /*
     * Diese Methode fügt die einzelnen Objekte in der GUI hinzu
     */
    public static void drawGUI() {
        setBasicFrameProps();
        drawMenuBtn();

        addCopyOutputButton();
        addCalculateButton();
        addInputOutput();
        addDropdownWithFilters();
        addFooter();

        // TODO: Die Zeilen hier drunter sortieren. @Ewin oder @Jonas??
        frame.add(headlineLabel);

        frame.add(calculateBtn);
        frame.add(clipboardBtn);

        frame.add(inputField);
        frame.add(outputLabel);

        frame.add(searchBarBaseCur);
        frame.add(searchBarTargetcur);

        frame.add(dropdownBaseCur);
        frame.add(dropdownTargetCur);

        frame.add(authorLabel);
        // frame.add(menuBtn);
        frame.add(menuBtnTest);

        setTheme(isDarkMode);

        frame.requestFocus();
        frame.setVisible(true);
    }

    // TODO: @Leon optimize this function
    public static void updateTitle(JFrame jframe, String rawTitleAddition) {
        String titleAddition = " - " + rawTitleAddition;
        jframe.setTitle(TITLE + " " + VERSION + (rawTitleAddition != "" ? titleAddition : ""));
    }

    /*
     * Erstellt das Hauptfenster für die GUI
     */
    private static void setBasicFrameProps() {
        updateTitle(frame, "");
        frame.setIconImage(icon.getImage());
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);

        headlineLabel.setFont(headlineLabel.getFont().deriveFont(30f));
        headlineLabel.setBounds(335, 25, GUI.FRAME_WIDTH, 50);
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
        searchBarTargetcur.setBounds(530, 215, 290, 20);
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
     * Erstellt einen Dropdown, mit dem man Währungen auswählen kann.
     * Dies wird auf den beiden Seiten erstellt.
     * 
     * Dabei nimmt es den Namen der Währung, mit dem jeweiligen
     * Isocodes. Dies verwendet man für den Empfang des Wechselskurses
     */
    private static void addDropdownWithFilters() {
        addSearchBars();

        dropdownBaseCur = new JComboBox<>();
        dropdownBaseCur.setBounds(50, 250, 290, 50);

        dropdownTargetCur = new JComboBox<>();
        dropdownTargetCur.setBounds(530, 250, 290, 50);

        for (Map.Entry<String, String> currency : Utils.getAllCurrencies()) {
            String isoCode = currency.getKey();
            String currencyName = currency.getValue();

            dropdownBaseCur.addItem(currencyName + " (" + isoCode + ")");
            dropdownTargetCur.addItem(currencyName + " (" + isoCode + ")");
        }

        /*
         * TODO Code Optimization
         * 
         * Die ItemListener nehmen den isocode raus und werden in der
         * jeweiligen Variable zugewiesen
         */
        dropdownBaseCur.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    baseCur = (String) dropdownBaseCur.getSelectedItem();
                    baseCurResult = baseCur.split("\\(")[1].replace(")", "").trim();
                }
            }
        });

        /*
         * TODO Code Optimization
         */
        dropdownTargetCur.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    targetCur = (String) dropdownTargetCur.getSelectedItem();
                    targetCurResult = targetCur.split("\\(")[1].replace(")", "").trim();
                }
            }
        });

        /*
         * Initialisert die Methode, um mit Pfeiltasten zu navigieren
         */
        addArrowKeyNavigationToComboBox(dropdownBaseCur);
        addArrowKeyNavigationToComboBox(dropdownTargetCur);

        frame.add(dropdownBaseCur);
        frame.add(dropdownTargetCur);
    }

    /*
     * Erstellt einen Rechnerknopf
     * 
     * Es nimmt den Betrag auf und wird in der Umwandlung der Währung verrechnet
     */
    private static void addCalculateButton() {
        calculateBtn.setBounds(380, 250, 100, 25);
        calculateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        /*
                         * TODO Code Optimization
                         * 
                         * inputValue muss drinne bleiben, damit er in dem Moment die eingegebenen Daten
                         * entzieht
                         * InputValueAsDouble könnte im Utils geschrieben werden
                         */
                        inputValue = inputField.getText();
                        inputValueAsDouble = Double.parseDouble(GUI.inputValue);

                        Utils.runCalcThread();
                    }
                });
            }
        });
    }

    /*
     * Erstellt einen Kopier Knopf
     * 
     * Nimmt das Ergebnis und steckt es in den Clipboard
     */
    private static void addCopyOutputButton() {
        clipboardBtn.setBounds(490, 290, 30, 30);

        // Nimmt das originale .png und skaliert das ganze runter zu dem bestimmten
        // Auflösung
        // ...Scale_Smooth hinterlässt dem Bild einen AA (Anti Aliasing) Effekt zu dem
        // Bild
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
    }

    private static void addInputOutput() {
        outputLabel.setBounds(250, 280, 300, 150);
        setOuput("Bitte wähle Währungen aus und gib einen Betrag ein.");

        inputField.setBounds(385, 290, 90, 30);
    }

    /*
     * Erstellt einen "Einstellungsknopf"
     * 
     * Mit dem Knopf kann man in die Einstellungen wechseln
     */
    private static void drawMenuBtn() {
        ImageIcon originalIcon = new ImageIcon(("src/resources/buttons/settings_button.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        menuBtnTest.setBounds(GUI.FRAME_WIDTH - 80, GUI.FRAME_HEIGHT - 95, 50, 50);
        menuBtnTest.setIcon(scaledIcon);

        menuBtnTest.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Settings.drawSettingsGUI();
                frame.setVisible(false);
            }
        });

    }

    /*
     * TODO: Kommentar
     */
    private static void addFooter() {
        authorLabel.setBounds(15, FRAME_HEIGHT - 60, 200, 20);
        authorLabel.setForeground(Color.GRAY);

        menuBtn.setBounds(745, 520, 110, 30);
        menuBtn.setBackground(Color.decode("#00CCCC"));
        menuBtn.setForeground(Color.WHITE);

        menuBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Settings.drawSettingsGUI();
                frame.setVisible(false);
            }
        });
    }

    /*
     * Setzt den Theme fest
     * 
     * Je nachdem, wie es der Enduser mag, wechselt es sich zwischen dem Light- und
     * Darkmode
     */
    public static void setTheme(boolean darkMode) {
        try {
            if (darkMode) {
                UIManager.setLookAndFeel(new FlatDarkLaf());
            } else {
                UIManager.setLookAndFeel(new FlatLightLaf());
            }
            SwingUtilities.updateComponentTreeUI(frame);
            isDarkMode = darkMode;

        } catch (Exception e) {
            ErrorDisplay.throwErrorPopup("Es ist ein Fehler beim setzen des Themes aufgetreten.\n" +
                    "Das Programm wird möglicherweise etwas anders aussehen als sonst!");
            e.printStackTrace();
        }
    }

    // Mit pfeiltasten Yallan
    /*
     * Erstellt die Möglichkeit im Dropdown Menü mit Pfeiltasten zu navigieren
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
     * TODO Kommentar
     */
    public static void setOuput(String output) {
        // Using HTML formatting here as JLabels dont accept a simple line break (\n)
        outputLabel.setText("<html>" + output.replaceAll("\n", "<br>") + "</html>");
    }

    /*
     * Zeigt dem Enduser, dass das Programm läuft
     * 
     * TODO Idee: Könnte man vielleicht einen drehenden Rad implementieren, sodass es anzeigt,
     * dass das Programm am arbeiten ist?
     */
    public static void displayAsLoading(boolean isLoading) {
        if (isLoading) {
            calculateBtn.setEnabled(false);
            setOuput("Lädt...");
            calculateBtn.setText("Lädt...");

        } else {

            calculateBtn.setEnabled(true);
            calculateBtn.setText("Umrechnen");
        }
    }

    public static double getValue() {

        return inputValueAsDouble;
    }

    public static String getBaseCur() {

        return baseCurResult;
    }

    public static String getTargetCur() {

        return targetCurResult;
    }

}

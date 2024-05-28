package GUI.Components;

import java.util.Map;

import GUI.GUI;
import GUI.Popups.PopupDisplay;
import Utils.Utils;
import Utils.Data.Calculations;
import lang.Language;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class InputOutput {

    private static JTextField inputField = new JTextField();
    public static JTextField searchBarBaseCur = new JTextField(Language.getLangStringByKey("searchBar")),
            searchBarTargetCur = new JTextField(Language.getLangStringByKey("searchBar"));
    private static JComboBox<String> dropdownBaseCur;
    private static JComboBox<String> dropdownTargetCur;
    private static JButton calculateBtn = new JButton();
    private static JLabel outputLabel = new JLabel("", SwingConstants.CENTER);
    private static JLabel loadingGIF = new JLabel();

    // Diese Variablen speichern den Betrag des Nutzers
    private static String inputValue;
    private static double inputValueResult;

    // Diese Variablen speichern die ISO-codes von den Währungen
    private static String baseCurResult, targetCurResult;

    public static void addAllComponents() {
        addCalculateButton();
        addDropdownWithFilters();
        addInputOutput();
        addLoadingCircleGIF();
        addSearchBars();

        setComponentBounds();
    }

    private static void setComponentBounds() {
        calculateBtn.setBounds(400, 250, 100, 25);
        dropdownBaseCur.setBounds(50, 250, 290, 50);
        dropdownTargetCur.setBounds(GUI.getWindowWidth() - 340, 250, 290, 50);
        searchBarBaseCur.setBounds(50, 215, 290, 20);
        searchBarTargetCur.setBounds(GUI.getWindowWidth() - 340, 215, 290, 20);

        outputLabel.setBounds(300, 285, 300, 150);
        inputField.setBounds(405, 290, 90, 30);

        loadingGIF.setBounds(420, 260, 200, 200);
    }

    /*
     * Diese Methode erstellt einen "Rechner" Knopf
     * Es nimmt den Betrag auf und wird in der Umwandlung der Währung verrechnet
     */
    private static void addCalculateButton() {
        calculateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        inputValue = inputField.getText();
                        inputValueResult = Double.parseDouble(InputOutput.inputValue);

                        Calculations.runThreadedCalculation();
                    }
                });
            }
        });

        GUI.frame.add(calculateBtn);
    }

    private static void addDropdownWithFilters() {
        addSearchBars();

        dropdownBaseCur = new JComboBox<>();

        dropdownTargetCur = new JComboBox<>();

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
                            PopupDisplay.throwErrorPopup(Language.getLangStringByKey("error_currency_unused"));
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
                            PopupDisplay.throwErrorPopup(Language.getLangStringByKey("error_currency_unused"));
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

        GUI.frame.add(searchBarBaseCur);
        GUI.frame.add(searchBarTargetCur);

        GUI.frame.add(dropdownBaseCur);
        GUI.frame.add(dropdownTargetCur);
    }

    /*
     * Erstellt eine Suchleiste
     * 
     * Mit dieser Methode kann man eine Währung suchen und dadurch schnell finden
     */
    private static void addSearchBars() {

        // Erstellt links eine Suchleiste
        searchBarBaseCur.setHighlighter(null);
        searchBarBaseCur.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchBarBaseCur.getText().equals(Language.getLangStringByKey("searchBar"))) {
                    searchBarBaseCur.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchBarBaseCur.getText().isEmpty()) {
                    searchBarBaseCur.setText(Language.getLangStringByKey("searchBar"));
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
        searchBarTargetCur.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchBarTargetCur.getText().equals(Language.getLangStringByKey("searchBar"))) {
                    searchBarTargetCur.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchBarTargetCur.getText().isEmpty()) {
                    searchBarTargetCur.setText(Language.getLangStringByKey("searchBar"));
                }
            }
        });

        searchBarTargetCur.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String searchText = searchBarTargetCur.getText().toLowerCase();
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
     * Diese Methode erstellt die Möglichkeit im Dropdown Menü mit Pfeiltasten zu
     * navigieren
     */
    private static void addArrowKeyNavigationToComboBox(JComboBox<String> comboBox) {
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
     * Fügt Komponenten hinzu die für input des users und output verantwortlich sind
     */
    private static void addInputOutput() {
        setOutput(Language.getLangStringByKey("outputLabel"));

        GUI.frame.add(inputField);
        GUI.frame.add(outputLabel);
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
        GUI.frame.add(loadingGIF);
        loadingGIF.setVisible(false);
    }

    /*
     * Setzt den formatierten Output (inklusive Endergebnis)
     * mit richtigen Zeilenumbrüchen
     */
    public static void setOutput(String output) {
        /*
         * JLabels akzeptieren kein normales \n als Line break, deshalb benutzen wir
         * hier HTML formatierung mit dem <br> Tag
         */
        outputLabel.setText("<html>" + output.replaceAll("\n", "<br>") + "</html>");
    }

    /*
     * Diese Methode zeigt dem Endnutzer, dass das Programm am Arbeiten ist und den
     * Wechselkurs herausfindet
     */
    public static void displayAsLoading(boolean isLoading) {
        if (isLoading) {
            calculateBtn.setEnabled(false);
            setOutput(Language.getLangStringByKey("loading"));
            loadingGIF.setVisible(true);
            calculateBtn.setText(Language.getLangStringByKey("loading"));
        } else {
            calculateBtn.setEnabled(true);
            loadingGIF.setVisible(false);
            calculateBtn.setText(Language.getLangStringByKey("calculateBtn"));
        }
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

    @SuppressWarnings("rawtypes")
    public static JComboBox[] getComboBoxes() {
        JComboBox[] comboBoxes = { dropdownBaseCur, dropdownTargetCur };
        return comboBoxes;
    }
}

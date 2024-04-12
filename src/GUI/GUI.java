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

    public static void drawGUI() {
        setBasicFrameProps();

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
        frame.add(menuBtn);
        
        setTheme(isDarkMode);

        frame.requestFocus();
        frame.setVisible(true);
    }

    // TODO: @Leon optimize this function
    public static void updateTitle(JFrame jframe, String rawTitleAddition) {
        String titleAddition = " - " + rawTitleAddition;
        jframe.setTitle(TITLE + " " + VERSION + (rawTitleAddition != "" ? titleAddition : ""));
    }

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

    private static void addSearchBars() {
        // Bar 1
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

        // Bar 2
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

        // Pfeiltasten zu den Dropdowns adden
        addArrowKeyNavigationToComboBox(dropdownBaseCur);
        addArrowKeyNavigationToComboBox(dropdownTargetCur);

        frame.add(dropdownBaseCur);
        frame.add(dropdownTargetCur);
    }

    private static void addCalculateButton() {
        calculateBtn.setBounds(380, 250, 100, 25);
        calculateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        Utils.runCalcThread();
                    }
                });
            }
        });
    }

    private static void addCopyOutputButton() {
        clipboardBtn.setBounds(490, 290, 30, 30);

        //Nimmt das originale .png und skaliert das ganze runter zu dem bestimmten Auflösung
        // ...Scale_Smooth hinterlässt dem Bild einen AA (Anti Aliasing) Effekt zu dem Bild
        ImageIcon originalIcon = new ImageIcon(GUI.class.getResource("/resources/buttons/icon_copy-button-dark.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        clipboardBtn.setIcon (scaledIcon);
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

    public static void setOuput(String output) {
        // Using HTML formatting here as JLabels dont accept a simple line break (\n)
        outputLabel.setText("<html>" + output.replaceAll("\n", "<br>") + "</html>");
    }

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
}

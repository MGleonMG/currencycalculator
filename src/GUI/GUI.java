package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Map;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import GUI.Menu.Menu;
import Utils.Utils;

public class GUI {
    public static final String title = "Währungsrechner", version = "1.0_alpha";
    public static final int width = 900, height = 600;
    private static Menu menuInstance;
    private static boolean isDarkMode = true;
    private static JFrame frame;
    private static JComboBox<String> dropdownChoose;

    public static void drawGUI() {
        frame = new JFrame(title + " " + version);
        frame.setSize(width, height);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        try {
            setLookAndFeel(isDarkMode);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel label = new JLabel("Währungsrechner");
        label.setFont(label.getFont().deriveFont(30f));
        label.setBounds(335, 25, 1000, 50);

        JButton menuBtn = new JButton("Menü");
        menuBtn.setBounds(755, 520, 100, 30);
        menuBtn.setBackground(Color.decode("#00CCCC"));
        menuBtn.setForeground(Color.WHITE);


        JTextField searchSelectBar = new JTextField("Nach Währung Filtern");
        searchSelectBar.setBounds(530, 215, 300, 20);
        searchSelectBar.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchSelectBar.getText().equals("Nach Währung Filtern")) {
                    searchSelectBar.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchSelectBar.getText().isEmpty()) {
                    searchSelectBar.setText("Nach Währung Filtern");
                }
            }
        });


        JTextField searchBar = new JTextField("Nach Währung Suchen");
        searchBar.setBounds(50, 215, 300, 20);
        searchBar.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchBar.getText().equals("Nach Währung Suchen")) {
                    searchBar.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchBar.getText().isEmpty()) {
                    searchBar.setText("Nach Währung Suchen");
                }
            }
        });

        searchBar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String searchText = searchBar.getText().toLowerCase();
                DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) dropdownChoose.getModel();
                model.removeAllElements();

                for (Map.Entry<String, String> currency : Utils.getAllCurrencies()) {
                    String isoCode = currency.getKey();
                    String currencyName = currency.getValue();

                    if (currencyName.toLowerCase().contains(searchText) || isoCode.toLowerCase().contains(searchText)) {
                        model.addElement(currencyName + " (" + isoCode + ")");
                    }
                }
            }
        });

        dropdownChoose = new JComboBox<>();
        dropdownChoose.setBounds(50, 250, 150, 30);
        dropdownChoose.setSize(new Dimension(300, 40));

        JComboBox<String> dropdownSelect = new JComboBox<>();
        dropdownSelect.setBounds(530, 250, 150, 30);
        dropdownSelect.setSize(new Dimension(300, 40));

        for (Map.Entry<String, String> currency : Utils.getAllCurrencies()) {
            String isoCode = currency.getKey();
            String currencyName = currency.getValue();

            dropdownChoose.addItem(currencyName + " (" + isoCode + ")");
            dropdownSelect.addItem(currencyName + " (" + isoCode + ")");
        }

        menuBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (menuInstance == null) {
                    menuInstance = new Menu();
                }
                menuInstance.createMenu();
                frame.setVisible(false);
            }
        });

        JLabel authorLabel = new JLabel(version + " by Leon, Jonas, Ewin");
        authorLabel.setBounds(10, height - 100, 200, 100);
        authorLabel.setForeground(Color.GRAY);

        frame.add(authorLabel);
        frame.add(label);
        frame.add(menuBtn);
        frame.add(dropdownChoose);
        frame.add(dropdownSelect);
        frame.add(searchBar);
        frame.add(searchSelectBar);

        frame.setVisible(true);
    }

    public static void setLookAndFeel(boolean darkMode) {
        try {
            if (darkMode) {
                UIManager.setLookAndFeel(new FlatDarkLaf());
            } else {
                UIManager.setLookAndFeel(new FlatLightLaf());
            }
            SwingUtilities.updateComponentTreeUI(frame);
            isDarkMode = darkMode;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            drawGUI();
        });
    }
}

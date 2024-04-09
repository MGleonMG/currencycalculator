package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import GUI.Menu.Menu;

public class GUI {
    private static final String TITLE = "Währungsrechner", VERSION = "1.0_alpha";
    public static final int FRAME_WIDTH = 900, FRAME_HEIGHT = 600;
    private static Menu menu;
    private static boolean isDarkMode = true;
    private static JFrame frame;

    public static void drawGUI() {
        frame = new JFrame(TITLE + " " + VERSION);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLayout(new FlowLayout());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);

        try {
            setTheme(isDarkMode); // Setze das Look-and-Feel basierend auf dem aktuellen Modus
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel label = new JLabel("Währungsrechner");
        label.setFont(label.getFont().deriveFont(30f));
        label.setBounds(335, 25, GUI.FRAME_WIDTH, 50);

        JButton menuBtn = new JButton("Menü");
        menuBtn.setBounds(755, 520, 100, 30);
        menuBtn.setBackground(Color.decode("#00CCCC"));
        menuBtn.setForeground(Color.WHITE);

        menuBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (menu == null) {
                    menu = new Menu();
                }
                menu.createMenu();
                frame.setVisible(false);
            }
        });

        JLabel authorLabel = new JLabel(VERSION + " by Leon, Jonas, Ewin");
        authorLabel.setBounds(10, FRAME_HEIGHT - 100, 200, 100);
        authorLabel.setForeground(Color.GRAY);

        frame.add(authorLabel);
        frame.add(label);
        frame.add(menuBtn);

        frame.setVisible(true);
    }

    // Methode zum Setzen des Look-and-Feel im Hauptframe
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
            // TODO: Error popup once @Leon done with error class
            e.printStackTrace();
        }
    }
}

// GUI.java
package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import GUI.Menu.Menu;

public class GUI {
    public static final String title = "Währungsrechner", version = "1.0_alpha";
    public static final int width = 900, height = 600;
    private static Menu menuInstance; 
    private static boolean isDarkMode = true; 
    private static JFrame frame; 

    public static void drawGUI() {
        frame = new JFrame(title + " " + version);
        frame.setSize(width, height); 
        frame.setLayout(new FlowLayout());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setResizable(false);

        try {
            setLookAndFeel(isDarkMode); // Setze das Look-and-Feel basierend auf dem aktuellen Modus
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel label = new JLabel("Währungsrechner");
        label.setSize(new Dimension(100, 30));
        label.setFont(label.getFont().deriveFont(30f));
        label.setBounds(335, 25, 1000, 50);

        JButton menuBtn = new JButton("Menü");
        menuBtn.setSize(new Dimension(10, 10));
        menuBtn.setBounds(755, 520, 100, 30);
        menuBtn.setBackground(Color.decode("#00CCCC"));
        menuBtn.setForeground(Color.WHITE);

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

        frame.setLayout(null);
        frame.add(authorLabel);
        frame.add(label);
        frame.add(menuBtn);

        frame.setVisible(true);
    }

    // Methode zum Setzen des Look-and-Feel im Hauptframe
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

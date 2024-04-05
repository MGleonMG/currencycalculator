//author: Hauptsächlich Jonas lmao
package GUI;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.formdev.flatlaf.FlatDarkLaf;

import GUI.Menu.Menu;

public class GUI {
    public static final String title = "Währungsrechner", version = "1.0_alpha";
    public static final int width = 900, height = 600;
    private static Menu menuInstance; // Variable, um die Instanz von Menu zu speichern

    public static void drawGUI() {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        JFrame frame = new JFrame(title + " " + version);
        frame.setSize(width, height); // Größe setzen
        frame.setLayout(new FlowLayout());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Beenden beim Schließen
        frame.setResizable(false);

        JLabel label = new JLabel("Währungsrechner");
        label.setSize(new Dimension(100, 30));
        label.setFont(label.getFont().deriveFont(30f));
        label.setBounds(370, 25, 1000, 50);

        JButton menuBtn = new JButton("Menü");
        menuBtn.setSize(new Dimension(10, 10));
        menuBtn.setBounds(10, 10, 100, 30);

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

        // Das Panel zum Frame hinzufügen
        frame.setLayout(null);
        frame.add(authorLabel);
        frame.add(label);
        frame.add(menuBtn);

        frame.setVisible(true); // Frame sichtbar machen
    }
}

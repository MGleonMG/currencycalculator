package GUI;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import com.formdev.*;
import com.formdev.flatlaf.FlatDarkLaf;


public class GUI {
    private static final String title = "Währungsrechner";
    private static final String version = "1.0_alpha";
    @SuppressWarnings("unused") // temp, remove this line later
    private static JFrame frame = new JFrame(title + " " + version);
    
    
    public static void main(String[] args) {
    try{
        UIManager.setLookAndFeel(new FlatDarkLaf());
    }   catch (Exception e){
        e.printStackTrace();
    }

        JFrame frame = new JFrame("Währungsumrechner"); // Titel setzen
        frame.setSize(1000, 1000); // Größe setzen
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Beenden beim Schließen
        frame.setResizable(false);

     
        


        JLabel label = new JLabel("Währungsrechner");
        label.setSize(new Dimension (100,30));
        label.setFont(label.getFont().deriveFont(30f));
        label.setBounds(350,25,1000,50);
       

        JButton MenuBtn = new JButton("Menü");
        MenuBtn.setSize(new Dimension(10, 10));
        MenuBtn.setBounds(860,920,100,30);

        JLabel build = new JLabel("v1.0 alpha by Leon, Jonas, Ewin" );
        build.setBounds(10,900,500,100);
      
        build.setForeground(Color.GRAY);
        
      

       
        
        

        // Das Panel zum Frame hinzufügen
    
        frame.setLayout(null);
        frame.add(build);
        frame.add(label);
        frame.add(MenuBtn);
        

        frame.setVisible(true); // Frame sichtbar machen
    }
}
package Snakegame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
//import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
//import javax.swing.Timer;
import javax.swing.ImageIcon;
import java.awt.*;
//import java.awt.event.*;
import javax.swing.*;

public class Modepanel extends JPanel {
    private ImageIcon hometitle = new ImageIcon(getClass().getResource("hometitle.png"));
    
   // private ImageIcon abouttext = new ImageIcon(getClass().getResource("abouttext.png"));

    private JButton easyButton; 
    private JButton hardButton;
    
    
    public Modepanel() {
        setLayout(null);

        // Initialize and start the timer
        
        easyButton = new JButton("Easy mode");
        easyButton.setBounds(478, 200, 230, 52);
        easyButton.setFocusPainted(false);
        easyButton.setForeground(Color.WHITE);
        easyButton.setFont(new Font("Arial", Font.BOLD, 30));
        easyButton.setBackground(new Color(99, 120, 62));
        easyButton.setVisible(false); // Initially hide the button
        easyButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                easymode();
            }
        });
        add(easyButton);
        
        
        hardButton = new JButton("Hard mode");
        hardButton.setBounds(478, 270, 230, 52);
        hardButton.setFocusPainted(false);
        hardButton.setForeground(Color.WHITE);
        hardButton.setFont(new Font("Arial", Font.BOLD, 30));
        hardButton.setBackground(new Color(99, 120, 62));
        hardButton.setVisible(false); // Initially hide the button
        hardButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                hardmode();
            }

        });
        add(hardButton);
        
        
        
         JButton closeButton = new JButton("Back");
closeButton.setBounds(520, 350, 150, 50);
closeButton.setBorderPainted(false);
closeButton.setFocusPainted(false);
closeButton.setForeground(Color.WHITE);
closeButton.setFont(new Font("Arial", Font.BOLD, 30));
closeButton.setBackground(new Color(121, 153, 64));
closeButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Get the top-level ancestor, which is the JFrame containing Modepanel
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(Modepanel.this);

        // Dispose of the JFrame, which effectively closes it
        topFrame.dispose(); // deletes the top mode panel
    }
    
});

closeButton.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseEntered(MouseEvent e) {
        closeButton.setBackground(new Color(150, 183, 77));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        closeButton.setBackground(new Color(121, 153, 64));
    }
});

add(closeButton);



       
    }

    

    @Override
     protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //setPreferredSize(new Dimension(905, 300)); // Adjusted height

        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
        hometitle.paintIcon(this, g, 0, 0);

        easyButton.setVisible(true);
        hardButton.setVisible(true);
    }

    

    private void hardmode() {
        JFrame frame = new JFrame("Hard Mode");
        frame.setBounds(10, 10, 905, 700);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Hardmode hardmode = new Hardmode();
        hardmode.setBackground(Color.DARK_GRAY);
        frame.add(hardmode);

        frame.setVisible(true);
        
        
        
            }

    private void easymode() {
        JFrame frame = new JFrame("Easy Mode");
        frame.setBounds(10, 10, 905, 700);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GamePanel gamepanel = new GamePanel();
        gamepanel.setBackground(Color.DARK_GRAY);
        frame.add(gamepanel);

        frame.setVisible(true);
        
    }

    

    public static void main(String[] args) {
        /*JFrame Modepanel = new JFrame("Mode panel");
        Modepanel.setBounds(10, 10, 905, 420);
        Modepanel.setResizable(false);
        Modepanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Modepanel modePanel = new Modepanel();
        Modepanel.add(modePanel);
        Modepanel.setVisible(true);*/
    }

}



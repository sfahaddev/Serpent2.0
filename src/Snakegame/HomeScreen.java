package Snakegame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.ImageIcon;
//import java.awt.*;
//import java.awt.event.*;
import javax.swing.*;

public class HomeScreen extends JPanel {
    private ImageIcon hometitle = new ImageIcon(getClass().getResource("hometitle.png"));
    private ImageIcon downborder = new ImageIcon(getClass().getResource("downborder.PNG"));
    private ImageIcon loadpic = new ImageIcon(getClass().getResource("loadpic.jpeg"));
    private ImageIcon loadscreen = new ImageIcon(getClass().getResource("loadscreen.gif"));
    //private ImageIcon  howtoplay= new ImageIcon(getClass().getResource("howtoplay.jpeg"));
   // private ImageIcon abouttext = new ImageIcon(getClass().getResource("abouttext.jpeg"));
    private Timer timer;

    private JButton startButton;
    private JButton exitButton;
    private JButton aboutus;
    private JButton inst;
    private AboutTextDialog aboutTextDialog;
    private howtoplayDialog howtoplayDialog;
        //private boolean showLoadingScreen;


    public HomeScreen() {
        setLayout(null);

        // Initialize and start the timer
        timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showHomeScreen();
            }
        });
        timer.setRepeats(false); // Set to false to run the ActionListener only once
        timer.start();

        startButton = new JButton("Start Game");
        startButton.setBounds(478, 200, 230, 52);
        startButton.setFocusPainted(false);
        startButton.setForeground(Color.WHITE);
        startButton.setFont(new Font("Arial", Font.BOLD, 30));
        startButton.setBackground(new Color(99, 120, 62));
        startButton.setVisible(false); // Initially hide the button
        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                startGame();
            }
        });
        add(startButton);

        exitButton = new JButton("Exit");
        exitButton.setBounds(478, 380, 230, 52);
        exitButton.setFocusPainted(false);
        exitButton.setForeground(Color.WHITE);
        exitButton.setFont(new Font("Arial", Font.BOLD, 30));
        exitButton.setBackground(new Color(121, 153, 64));
        exitButton.setVisible(false); // Initially hide the button
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });
        add(exitButton);

        aboutus = new JButton("About us");
        aboutus.setBounds(478, 320, 230, 52);
        aboutus.setFocusPainted(false);
        aboutus.setForeground(Color.WHITE);
        aboutus.setFont(new Font("Arial", Font.BOLD, 30));
        aboutus.setBackground(new Color(121, 153, 64));
        aboutus.setVisible(false);

        aboutus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAboutText();
            }
        });
        add(aboutus);

         inst = new JButton("How to play");
        inst.setBounds(478, 260, 230, 52);
        inst.setFocusPainted(false);
        inst.setForeground(Color.WHITE);
        inst.setFont(new Font("Arial", Font.BOLD, 30));
        inst.setBackground(new Color(121, 153, 64));
        inst.setVisible(false);
        inst.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showhowtoplayText();  // Corrected method name
            }
        });
        add(inst);

        aboutTextDialog = new AboutTextDialog();
        howtoplayDialog = new howtoplayDialog();  // Corrected class name
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Display the loadpic and loadscreen for the first 11 seconds
        if (timer.isRunning()) {
            super.paintComponent(g);
            int loadScreenWidth = getWidth(); // Set the width of loadscreen to the width of the panel
            int loadScreenHeight = getHeight(); // Set the height of loadscreen to the height of the panel
            loadpic.paintIcon(this, g, 0, 0);

            // Adjust the parameters for cropping along x-axis and y-axis
            int cropX = 232; // X-coordinate for the top-left corner of the cropped area
            int cropY = 100; // Y-coordinate for the top-left corner of the cropped area
            int cropWidth = 690; // Width of the cropped area
            int cropHeight = 450; // Height of the cropped area

            // Draw the cropped image
            g.drawImage(loadscreen.getImage(), 400, 290, 100 + cropWidth, 50 + cropHeight,
                    cropX, cropY, cropX + cropWidth, cropY + cropHeight, this);
        } else {
            // Display the home screen after the delay
            super.paintComponent(g);
            g.setColor(Color.black);
            g.fillRect(0, 0, getWidth(), getHeight());
            hometitle.paintIcon(this, g, 0, 0);
            //downborder.paintIcon(this, g, 0, 580);
            //downborder.paintIcon(this, g, 5, 580);
            

            // Make buttons visible after the delay
            startButton.setVisible(true);
            exitButton.setVisible(true);
            aboutus.setVisible(true);
            inst.setVisible(true);
            
        }
    }

    private void showHomeScreen() {
        // Stop the timer
        timer.stop();

        // Update the UI to display the home screen
        repaint();
        revalidate();
    }

    private void startGame() {
        JFrame frame = new JFrame("Mode Panel");
        frame.setBounds(10, 10, 905, 620);
        frame.setResizable(false);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Modepanel panel = new Modepanel();
        panel.setBackground(Color.DARK_GRAY);
        frame.add(panel);

        frame.setVisible(true);
    }

    private void showAboutText() {
        aboutTextDialog.setVisible(true);
    }
     private void showhowtoplayText() {
        howtoplayDialog.setVisible(true);
    }

    public static void main(String[] args) {
        JFrame homeFrame = new JFrame("Home Screen");
        homeFrame.setBounds(10, 10, 905, 620);
        homeFrame.setResizable(false);
       // homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        HomeScreen homePanel = new HomeScreen();
        homeFrame.add(homePanel);
        homeFrame.setVisible(true);
    }
    
    
    
    

  public class AboutTextDialog extends JDialog {
    private ImageIcon abouttext = new ImageIcon(getClass().getResource("abouttext.jpeg"));

    public AboutTextDialog() {
        setLayout(null);
        setBounds(10, 10, 910, 640); // Adjust the position and size here
        setResizable(false);

        JLabel aboutTextLabel = new JLabel(abouttext);
        // Increase or decrease the yOffset value to move the abouttext.gif up or down
        int yOffset = 30; // Adjust this value to control the vertical shift
        aboutTextLabel.setBounds(0, 30 - yOffset, abouttext.getIconWidth(), abouttext.getIconHeight());
        add(aboutTextLabel);

         JButton closeButton = new JButton("      ");
closeButton.setBounds(37, 433, 55, 60);
closeButton.setBorderPainted(false);
closeButton.setFocusPainted(false);
closeButton.setForeground(Color.WHITE);
closeButton.setFont(new Font("Arial", Font.BOLD, 30));

// Set the background color to be fully transparent
closeButton.setContentAreaFilled(false);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        // Add mouse listener for highlighting effect
        /*closeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                closeButton.setBackground(new Color(150, 183, 77)); // Highlight color
            }

            @Override
            public void mouseExited(MouseEvent e) {
                closeButton.setBackground(new Color(121, 153, 64)); // Default color
            }
        });*/

        add(closeButton);
    }

}
  
  
  
  
  
  
  public class howtoplayDialog extends JDialog {
    private ImageIcon  howtoplay= new ImageIcon(getClass().getResource("howtoplay.jpeg"));

    public howtoplayDialog() {
        setLayout(null);
        setBounds(10, 10, 910, 640); // Adjust the position and size here
        setResizable(false);

        JLabel howtoplayLabel = new JLabel(howtoplay);
        // Increase or decrease the yOffset value to move the abouttext.gif up or down
        int yOffset = 30; // Adjust this value to control the vertical shift
        howtoplayLabel.setBounds(0, 30 - yOffset, howtoplay.getIconWidth(), howtoplay.getIconHeight());
        add(howtoplayLabel);
        
        JButton closeButton = new JButton("      ");
closeButton.setBounds(37, 433, 55, 60);
closeButton.setBorderPainted(false);
closeButton.setFocusPainted(false);
closeButton.setForeground(Color.WHITE);
closeButton.setFont(new Font("Arial", Font.BOLD, 30));

// Set the background color to be fully transparent
closeButton.setContentAreaFilled(false);

closeButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
    }
});

// Add mouse listener for highlighting effect
/*closeButton.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseEntered(MouseEvent e) {
        closeButton.setBackground(new Color(150, 183, 77, 50)); // Highlight color with alpha value for transparency
    }

    @Override
    public void mouseExited(MouseEvent e) {
        closeButton.setBackground(new Color(0, 0, 0, 0)); // Fully transparent color
    }
});*/

add(closeButton);

JButton nextButton = new JButton("    ");

        nextButton.setBounds(805, 425, 55, 60); // Adjust the position here
        nextButton.setBorderPainted(false);
        nextButton.setFocusPainted(false);
        nextButton.setForeground(Color.WHITE);
        nextButton.setFont(new Font("Arial", Font.BOLD, 30));
        //closeButton.setBackground(new Color(121, 153, 64));
        nextButton.setContentAreaFilled(false);

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
        JFrame frame = new JFrame("Mode Panel");
        frame.setBounds(10, 10, 905, 620);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Modepanel panel = new Modepanel();
        panel.setBackground(Color.DARK_GRAY);
        frame.add(panel);

        frame.setVisible(true);
                
            }
        });

        // Add mouse listener for highlighting effect
      /*  nextButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                nextButton.setBackground(new Color(150, 183, 77)); // Highlight color
            }

            @Override
            public void mouseExited(MouseEvent e) {
                nextButton.setBackground(new Color(121, 153, 64)); // Default color
            }
        });*/

        add(nextButton);
    }
    }

}



    /*@Override
    public void paint(Graphics g) {
        super.paint(g);
        int yOffset = 30; // Adjust this value to control the vertical shift
        abouttext.paintIcon(this, g, 0, yOffset);
    }
}*/





package Snakegame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
public class Hardmode extends JPanel implements ActionListener, KeyListener {
     private int [] snakexlenght= new int [750]; // snake ki lenght
    private int [] snakeylenght= new int [750];
    
    private int lenghtofsnake =3;
 // this is for spawning food in random places this is the location for food
private int[] xPos={25,50,75,100,125,150, 175, 200, 225, 250, 275, 300, 325, 350,375,400,425,450,
    475,500,525,550,575, 600, 625,650,675,700,725,750,775,800,825,850};
private int[] yPos={100,125,150, 175, 200, 225, 250, 275, 300, 325, 350, 375,400,425 ,450,475,500,
    525,550,575,600,625};
    
    private Random random = new Random();
    private int enemyX,enemyY;
   
    private boolean left = false;
    private boolean right = true; // snake will face right when game start
    private boolean up = false;
    private boolean down = false;
    
    private int moves=0;
    private int score= 0;
    private boolean gameOver=false;
    
    private ImageIcon snaketitle =new ImageIcon(getClass().getResource("snaketitle.jpeg"));
    private ImageIcon leftmouth =new ImageIcon(getClass().getResource("leftmouth.png"));
    private ImageIcon rightmouth =new ImageIcon(getClass().getResource("rightmouth.png"));
    private ImageIcon upmouth =new ImageIcon(getClass().getResource("upmouth.png"));
    private ImageIcon downmouth =new ImageIcon(getClass().getResource("downmouth.png"));
    private ImageIcon snakeimage =new ImageIcon(getClass().getResource("body.png"));
    private ImageIcon enemy =new ImageIcon(getClass().getResource("enemy.png"));
    private ImageIcon background =new ImageIcon(getClass().getResource("background.png"));
    
    private Timer timer;
    private int delay = 100;
    
    
    Hardmode(){
        addKeyListener(this); // for the program to understand when we press keys
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
    timer = new Timer(delay,this);
    timer.start();
    
    newEnemy(); // prints new food
    
    
    }
    
    @Override
    public void paint(Graphics g) { // is panel se graphic draw hon ge
        super.paint(g); 
        g.setColor(Color.white); // boders ka color
        g.drawRect(24, 10, 851, 55);     

        snaketitle.paintIcon(this, g, 25, 11); // humara title
        background.paintIcon(this, g, 0, 75); // background image for snake
        g.setColor(Color.white); // boders ka color
        g.drawRect(23, 100, 837, 540);
       
        
        
        //g.setColor(Color.GRAY);       
       
       //g.fillRect(25, 75, 850, 575); // snake jaha hoga waha ka size
        
        if (moves == 0) { // when the game starts
        snakexlenght[0] = 100; // this is snake head
        snakexlenght[1] = 75;
        snakexlenght[2] = 50;

        // Adjust the initial y-coordinate values to spawn the snake lower
        snakeylenght[0] = 150;
        snakeylenght[1] = 150;
        snakeylenght[2] = 150;
    }
        if(left){
            leftmouth.paintIcon(this, g, snakexlenght[0], snakeylenght[0]);
        }
        if(right){
            rightmouth.paintIcon(this, g, snakexlenght[0], snakeylenght[0]);
        }
        if(up){
            upmouth.paintIcon(this, g, snakexlenght[0], snakeylenght[0]);
        }
        if(down){
            downmouth.paintIcon(this, g, snakexlenght[0], snakeylenght[0]);
        }
        for(int i=1;i<lenghtofsnake;i++){ // for the increment of snake tail
            snakeimage.paintIcon(this, g, snakexlenght[i], snakeylenght[i]);

        }
        
        enemy.paintIcon(this, g, enemyX, enemyY);
        if(gameOver){
            g.setColor(new Color(104, 123, 74));
            g.setFont(new Font("Arial", Font.BOLD,50));
            g.drawString("GAME OVER", 300, 360);
            g.setColor(new Color(104, 123, 74));
            g.setColor(new Color(56, 43, 20));
            g.setFont(new Font("Arial", Font.PLAIN,20));
            g.drawString("Press SPACE or ENTER to restart", 300, 380);
            
        }
        // setting score and length
        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.BOLD,14));
        g.drawString("SCORE: "+score, 750, 30);
        g.drawString("LENGTH: "+lenghtofsnake, 750, 50);

        g.dispose();
        
    }
    

    @Override
public void actionPerformed(ActionEvent e) {
    if (!gameOver) {
        for (int i = lenghtofsnake - 1; i > 0; i--) {
            snakexlenght[i] = snakexlenght[i - 1];
            snakeylenght[i] = snakeylenght[i - 1];
        }

        if (left) {
            snakexlenght[0] = snakexlenght[0] - 25;
        }
        if (right) {
            snakexlenght[0] = snakexlenght[0] + 25;
        }
        if (up) {
            snakeylenght[0] = snakeylenght[0] - 25;
        }
        if (down) {
            snakeylenght[0] = snakeylenght[0] + 25;
        }

        if (snakexlenght[0] >= 826 || snakexlenght[0] <= 30 || snakeylenght[0] >= 620 || snakeylenght[0] <= 100) {
            timer.stop(); // snake die as soon as it touch the walls
            gameOver = true;
        }

        colliedsWithEnemy();
        colliedsWithBody();
        repaint();
    }
}


// for snake movement
    @Override
    public void keyPressed(KeyEvent e) {
        //if(gameOver=true && e.getKeyCode()==KeyEvent.VK_SPACE ||e.getKeyCode()==KeyEvent.VK_ENTER){
        if(gameOver && (e.getKeyCode()==KeyEvent.VK_SPACE ||e.getKeyCode()==KeyEvent.VK_ENTER)){//restarts only when the game is over
            restart();
        }
        
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            moves++;
            if(!right){
                left=true;
            }
            else{
                left=false;
                right=true;
            }
            up= false;
            down=false;    

        }
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
             moves++;
            if(!left){
                right=true;
            }
            else{
                left=true;
                right=false;
            }
            up= false;
            down=false;
          

        }
        if(e.getKeyCode()==KeyEvent.VK_UP ){
            moves++;
            if(!down){
                up=true;
            }
            else{
                up=false;
                down=true;
            }
            right= false;
            left=false;
          

        }
        if(e.getKeyCode()==KeyEvent.VK_DOWN){
            moves++;
            if(!up){
                down=true;
            }
            else{
                up=true;
                down=false;
            }
            right= false;
            left=false;
          

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    private void newEnemy() { // tell the location for food generate
        enemyX=xPos[random.nextInt(33)];
        enemyY=yPos[random.nextInt(22)];
        
        for(int i=lenghtofsnake-1;i>=0;i--){ // for not letting the food spawn inside snake
         if(snakexlenght[i]==enemyX && snakeylenght[i] == enemyY){
             newEnemy();
           }
             

        }
        
    }
// when snakes eats the food and to generate new food
    private void colliedsWithEnemy() {
    // Adjust the proximity range based on your needs
    int proximityRange = 5;

    if (Math.abs(snakexlenght[0] - enemyX) < proximityRange && 
        Math.abs(snakeylenght[0] - enemyY) < proximityRange) {
        newEnemy();
        lenghtofsnake++;
        score++;
    }
}

// when snake collieds with its body for game over
    private void colliedsWithBody() {
        for(int i=lenghtofsnake-1;i>0;i--){
            if(snakexlenght[i]==snakexlenght[0] && snakeylenght[i]==snakeylenght[0]){
                timer.stop();
                gameOver= true;
                
                
            }
            
        }
    }
    private void colliedsWithBorder() {
    // Adjust the proximity range based on your needs
    int proximityRange = 5;

    // Check if the head of the snake is too close to the border
    if (snakexlenght[0] < 23 + proximityRange || snakexlenght[0] > 837 - proximityRange ||
        snakeylenght[0] < 100 + proximityRange || snakeylenght[0] > 625 - proximityRange) {
        timer.stop();
        gameOver = true;
    }
}
// to restart the game of press space or enter
    private void restart() {
        gameOver=false;
        moves=0;
        score=0;
        lenghtofsnake=3;
        
        left=false;
        right=true;
        up=false;
        down=false;
        timer.start();
        repaint();
        
    }
   
    
}

    


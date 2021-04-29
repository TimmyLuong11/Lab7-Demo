import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * This is the Driver Class containing 
 * the main method that runs the game
 * 
 * Use left and right arrow keys to switch between queue (left)
 * and stack (right) respectively.
 * Use spacebar to push and delete-key to pop
 * 
 * @author MoSho
 * @version March 7, 2019
 * Lab 7 Demonstration of list data structures
 */
public class Driver
{
    /**
     * main method
     * 
     * @param args - command line arguments (not used)
     * @throws InterruptedException Thrown when a thread is 
     *      waiting, sleeping, or otherwise occupied, and the thread is 
     *      interrupted, either before or during the activity. 
     */
    public static void main(String[] args) throws InterruptedException
    {
        // Load the Marvel-DC image
        Image mdc = Toolkit.getDefaultToolkit().getImage("resources/mdc.png");

        // Create the array of button choices
        //String[] buttons = { "Batman", "Superman", "Spiderman", "Captain America" };
        String[] buttons = { "BEGIN!!", "Quit" };

        // Set-up for the switch statement
        Superhero[] superheros = new Superhero[4];

        // Switch on the button the user selects
        switch (JOptionPane.showOptionDialog(null, new ImageIcon(mdc), "Are you ready?!",
                JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, buttons, buttons[0]))
        {
            case 0: // First button
                superheros[0] = new Superhero("Batman", "dc");
                superheros[1] = new Superhero("Superman", "dc");
                superheros[2] = new Superhero("Spiderman", "marvel");
                superheros[3] = new Superhero("CaptainAmerica", "marvel");
                break;
            
            default: // Second button
                System.exit(0);
        }

        // Base Game frame speed which the game will run at.
        int gameSpeed = 500;
        // Width of screen in pixels
        int width = 800;
        // Height of screen in pixels
        int height = 600;

        // Initialize the game frame
        String instructions = "Use left and right arrow keys to \n" +
				  "switch between the queue (left)\n"+
				  "and the stack (right), respectively.\n" +
				  "Use spacebar to push and delete-key to pop";
        JOptionPane.showMessageDialog(null, instructions);
        GameFrame gameFrame = new GameFrame(superheros, width, height);

        // Controls the dialog pop up
        Thread gameOverThread = new Thread(new Runnable() 
        {
            @Override
            public void run()
            {
                // Check if the player has caught the villain yet
                System.out.println("gameOverThread:: Running");
                while (!gameFrame.getPanel().getGame().playerHasWon());
                JOptionPane.showMessageDialog(null, "Congratulations, you caught the bad guy!");
                System.exit(0);
            }            
        });
        gameOverThread.start();
        
        // until game over
        while (true)
        {            
            // Repaint gameFrame
            gameFrame.repaint();

            // is the player dead?
            if (gameFrame.getPanel().getGame().playerIsDead(0))
            {
                // Notify the player that the villain has fled
                JOptionPane.showMessageDialog(null, "Oh no! The bad guy got away!", "GAME OVER!", 0, new ImageIcon(mdc));
                // Quit
                break;
            }
            
            // controls the game speed
            Thread.sleep(gameSpeed);
        }

        // Quit
        System.exit(0);
    }
}
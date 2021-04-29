import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Creates the drawing panel, listens for the key event, 
 * and is the window the game is observed in
 * 
 * @author MoSho
 * @version October 18, 2018
 * Lab 8 Demonstration of list data structures
 */
public class GameFrame extends JFrame
{
    /** Useful for serialization purposes (coming soon!) */
    private static final long serialVersionUID = 1L; 

    /** panel where the game is drawn */
    private GamePanel panel;

    /**
     * constructor for the window to observe the game
     * Listens for the key presses.
     * Spacebar pushes to list; Delete-key pops from list
     * Use left arrow to manipulate the queue.
     * Use right arrow to manipulate stack
     * 
     * @param superhero Reference to the current superhero being played
     * @param width Width of window in pixels
     * @param height Height of window in pixels
     */
    //public GameFrame(Superhero superhero, int width, int height)
    public GameFrame(Superhero[] superheros, int width, int height)
    {
        panel = new GamePanel(width, height, superheros);
        add(panel);

        // Set information about the JFrame
        getContentPane().setPreferredSize(new Dimension(width, height));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Super Lists");
        pack();
        setVisible(true);

        // Listen for key input
        addKeyListener(new KeyAdapter()
        {
            /**
             * action event when right, left, up, or down key is pressed
             * 
             * @param e key event
             */
            public void keyPressed(KeyEvent e)
            {                
                // Move the player based on the key pressed
                int keyCode = e.getKeyCode();
                switch (keyCode)
                {
	                case KeyEvent.VK_LEFT:
	                    panel.getGame().switchToQueue();
	                    break;
	                case KeyEvent.VK_RIGHT:
	                	panel.getGame().switchToStack();
	                	break;
	                case KeyEvent.VK_SPACE:
		                panel.getGame().pushPlayer();
		                break;
	                case KeyEvent.VK_DELETE:
	                    panel.getGame().popPlayer();
                }
                // redraw the player
                repaint();
            }
        });
    }

    /**
     * get the panel with the game objects
     * 
     * @return panel GamePanel that draws the game objects
     */
    public GamePanel getPanel() { return panel; }
}

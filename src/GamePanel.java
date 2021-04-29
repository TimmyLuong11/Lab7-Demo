import javax.swing.*;
import java.awt.*;

/**
 * The Game Panel, constructs the GameView by specifying a size, width and length in pixels
 * of the game frame, the number of tiles in each direction, and draws the view
 * 
 * @author MoSho
 * @version October 18, 2018
 * Lab 8 Demonstration of list data structures   
 */
public class GamePanel extends JPanel
{
    /** Useful for serialization purposes (coming soon!) */
    private static final long serialVersionUID = 1L;

    /** the collection of components that make the game */
    private GameView model; 

    /**
     * game panel, constructs the GameView and organizes it's drawing
     * There are 8 tiles across and 6 top to bottom
     * 
     * @param width panel width in pixels
     * @param height panel height in pixels
     * @param superhero Superhero that will save the day
     */
    //public GamePanel(int width, int height, Superhero superhero)
    public GamePanel(int width, int height, Superhero[] superhero)
    {
        model = new GameView(8, 6, width, height, superhero);
        this.setSize(width, height);
    }

    /**
     * This method draws the current view
     * 
     * @param graphics A Graphics object used for drawing the view
     */
    protected void paintComponent(Graphics graphics) { model.draw(graphics); }

    /**
     * Gets the current game view
     * 
     * @return model GameView object that contains/maintains all game objects
     */
    public GameView getGame() { return model; }
}

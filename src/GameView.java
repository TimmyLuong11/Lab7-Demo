import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * This class creates the game display and setups locations of the game objects
 * 
 * @author MoSho
 * @version October 18, 2018
 * Lab 8 Demonstration of list data structures 
 */
public class GameView
{
	/** Number of grid blocks wide */
	private int nBlocksWide;
	/** Number of grid blocks tall */
	private int nBlocksTall;

	/** Width in pixels of a game frame */
	private int nPixelsWide;
	/** Height in pixels of a game frame */
	private int nPixelsTall;

	private int heroIdx = 0;
	private int villianIdx = 0;

	private boolean isQ = true;

	/** the player's current position (in blocks) */
	Stack<Integer> villians;
	/** the villain's current position (in blocks) */
	Queue<Integer> heroes;

	/** the length of the game in frames */
	private int gameLength;
	/** Has the game ended */
	private volatile boolean isGameOver;

	/** The background image */
	private Image background;

	private Superhero[] heroesRef;

	/**
	 * Constructor for the game display, determining the frame and tile sizes
	 * 
	 * @param nBlocksWide Width in number of blocks (or tiles)
	 * @param nBlocksTall Height in number of blocks (or tiles)
	 * @param nPixelsWide Width in pixels of the frame
	 * @param nPixelsTall Height in pixels of the frame
	 * @param heros Superhero to be save the day
	 */
	//public GameView(int nBlocksWide, int nBlocksTall, int nPixelsWide, int nPixelsTall, Superhero ahero)
	public GameView(int nBlocksWide, int nBlocksTall, int nPixelsWide, int nPixelsTall, Superhero[] heros)
	{
		villians = new Stack<Integer>();
		heroes = new LinkedList<Integer>();

		heroesRef = heros;

		// set nBlocksWide and nBlocksTall
		this.nBlocksWide = nBlocksWide;
		this.nBlocksTall = nBlocksTall;

		// Pixel size
		this.nPixelsWide = nPixelsWide;
		this.nPixelsTall = nPixelsTall;

		isGameOver = false;

		background = heros[0].getBattleCity();
	}

	/**
	 * draw the game
	 * 
	 * @param graphics A Graphics object used to draw the hero, villain, and obstacles
	 */
	public void draw(Graphics graphics)
	{
		// draw the background image first
		graphics.drawImage(background, 0, 0, nPixelsWide, nPixelsTall, null);

		// how big is each 'block'?
		int blockWidth = nPixelsWide / nBlocksWide;
		int blockHeight = nPixelsTall / nBlocksTall;

		int v = 0; // villian position in game
		for (int i : villians)
			drawSpirte(false, v++, blockWidth, blockHeight, graphics, heroesRef[i].getVillain());
		
		int h = 0; // hero position in game
		for (int i : heroes)
			drawSpirte(true, h++, blockWidth, blockHeight, graphics, heroesRef[i].getBattleImage());
		
		// Draw cursor underneath selected list
		int x = (isQ ? 2 : 5 ) * blockWidth;
		int y = 5 * blockWidth;
		graphics.setColor(Color.ORANGE);
		graphics.fillRoundRect(x, y, blockWidth, blockHeight >> 2, 20, 20);
	}

	/**
	 * draw hero or villain image
	 * 
	 * @param blockWidth nBlocksWide of the player image in pixels
	 * @param blockHeight nBlocksTall of the player image in pixels
	 * @param graphics A Graphics object used for drawing the hero/villain
	 */
	private void drawSpirte(boolean isHero, int pos, int blockWidth, int blockHeight, Graphics graphics, Image img)
	{
		int x = (isHero ? 2 : 5 ) * blockWidth;
		int y = (isHero ? pos + 1 : villians.size() - pos) * blockHeight;
		graphics.drawImage(img, x, y, blockWidth, blockHeight, null);
	}

	public void switchToQueue() { isQ = true; }

	public void switchToStack() { isQ = false; }

	public void pushPlayer()
	{
		if (isQ && heroes.size() < heroesRef.length) 
		{ 
			heroes.add(heroIdx++);
			heroIdx %= heroesRef.length;
		}
		else if (!isQ && villians.size() < heroesRef.length) 
		{
			villians.push(villianIdx++);
			villianIdx %= heroesRef.length;
		}
	}

	public void popPlayer()
	{
		if (isQ && !heroes.isEmpty()) heroes.remove();
		else if (!isQ && !villians.isEmpty()) villianIdx = villians.pop();
	}

	/**
	 * check if the game is over
	 * 
	 * @param framenum  the current frame of the game 
	 * @return true if player is dead i.e. we have reached the 
	 *          last frame and the villain has not been caught
	 */
	public boolean playerIsDead(int framenum)
	{
		return framenum == gameLength - 1;
	}

	/**
	 * check if the player has won
	 * 
	 * @return true once the player is in the same space as the villain
	 */
	public boolean playerHasWon()
	{
		return isGameOver;
	}
}

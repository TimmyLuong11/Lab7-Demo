import java.awt.Image;
import java.awt.Toolkit;

/**
 * Class for Superhero representation, specifying the
 * hero's name, publisher, villain, city, and loads the
 * corresponding images
 * 
 * @author MoSho
 * @version October 18, 2018
 * Lab 8 Demonstration of list data structures
 */
public class Superhero
{
    /** Name of the superhero - used for file retrieval */
    private String name;
    
    /** Game image for the hero */
    private Image battleIcon;
    /** Game image for the hero's city to protect */
    private Image city;
    /** Game image for the hero's villain to capture */
    private Image villain;

    /**
     * Constructor for Superhero
     * 
     * @param name Name of the superhero
     * @param pub Publisher of the superhero (Marvel or DC)
     * @param fightLength how many frames the fight is
     * @param difficultyLevel how quickly should the frame redraw
     *          and the max quantity of obstacles per row (0 to 3)
     */
    public Superhero(String name, String pub)
    {
        this.name = name;
        
        battleIcon = Toolkit.getDefaultToolkit().getImage("resources/heroes/" + name + ".png");
        city = Toolkit.getDefaultToolkit().getImage("resources/cities/" + pub + ".png");
        
        // Get the villain image
        switch (name)
        {
            case "Batman":
                villain = Toolkit.getDefaultToolkit().getImage("resources/villains/joker.png");
                break;
            case "Superman":
                villain = Toolkit.getDefaultToolkit().getImage("resources/villains/bizzarro.png");
                break;
            case "Spiderman":
                villain = Toolkit.getDefaultToolkit().getImage("resources/villains/venom.png");
                break;
            case "CaptainAmerica":
                villain = Toolkit.getDefaultToolkit().getImage("resources/villains/redskull.png");
                break;
            default:
                break;
        }
    }

    /**
     * Image representation of the hero
     * 
     * @return Image of the hero
     */
    public Image getBattleImage()
    {
        return battleIcon;
    }

    /**
     * Image of the city the hero must protect
     * 
     * @return Image of the hero's city
     */
    public Image getBattleCity()
    {
        return city;
    }

    /**
     * Image of the hero's villain he must fight
     * 
     * @return Image of the hero's villain
     */
    public Image getVillain()
    {
        return villain;
    }

    /**
     * String representation of the hero
     * 
     * @return name of the hero
     */
    public String getName()
    {
        return name;
    }
}

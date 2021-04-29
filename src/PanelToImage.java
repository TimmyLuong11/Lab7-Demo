import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Support code for writing JPanels out to image files
 * 
 * @author Andrew H. Fagg
 * @version 2016-10-15
 *
 */
public class PanelToImage
{
    /**
     * Write a JPanel out to an image file
     * 
     * http://stackoverflow.com/questions/11272938/how-to-save-panel-as-image-in-swing
     * 
     * @param panel Panel to write out to a file
     * @param fileName Name of output file
     * @param fileType Type of image ("png")
     * @throws IOException 
     */
    public static void makePanelImage(Component panel, String fileName, String fileType) throws IOException
    {
        // Create the image
        Dimension size = panel.getSize();
        BufferedImage image = new BufferedImage(
                size.width, size.height 
                , BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();

        // Copy the panel out to the image
        panel.paint(g2);

        // Write the file

        ImageIO.write(image, fileType, new File(fileName));

    }

    /**
     * Generate a PNG image file from a panel
     * 
     * @param panel Panel to write out to a file
     * @param fileName Name of output file
     * @throws IOException 
     */
    public static void makePanelPNGImage(Component panel, String fileName) throws IOException
    {
        makePanelImage(panel, fileName, "png");
    }
}

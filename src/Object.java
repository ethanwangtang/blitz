import java.awt.*;          // access to Container
import java.awt.event.*;    // access to WindowAdapter, WindowEvent
import javax.swing.*;       // access to JFrame and Jcomponents
import javax.swing.event.*;     // access to JSlider events
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.net.URL;
import java.util.ArrayList;
/**
 * Write a description of class Objects here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class Object
{
    protected int posX, posY, width, height;
    public static double SCALE = 5;
    //meaning pictureWidth, etc
    protected Image sprite;
    protected String id;
    protected Image scaledImage;
    public Object(String imgName, int x, int y, int w, int h)
    {
        try
        {
            sprite = ImageIO.read(new File(imgName));
        }
        catch (IOException missingResource)
        {
            missingResource.printStackTrace();
        }
        posX = x;
        posY = y;
        width = w;
        height = h;
    }
    
    public void generateScaledImage()
    {
        int tempWidth = (int) ((double) (width / SCALE));
        int tempHeight = (int) ((double) (height / SCALE));
        scaledImage = (sprite.getScaledInstance(tempWidth, tempHeight, Image.SCALE_SMOOTH));
    }

    public Image getScaledImage()
    {
        return scaledImage;
    }
    
    public int getScaledXPos()
    {
        return (int) ((double) (posX / SCALE));
    }

    public int getScaledYPos()
    {
        return (int) ((double) (posY / SCALE));
    }

    public int getXPos()
    {
        return posX;
    }

    public int getYPos()
    {
        return posY;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public Image getSprite()
    {
        return sprite;
    }

    public String getId()
    {
        return id;
    }

    public boolean isOfType(String type)
    {
        if (id.equals(type))
        {
            return true;
        }
        return false;
    }
}

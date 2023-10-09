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
import javafx.scene.shape.*;

/**
 * Write a description of class StatisField here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class StasisField extends Object
{
    private double radius;
    private Circle hitBox;
    public StasisField(Map mapImp, int x, int y, int w, int h)
    {
        super("statis_field.png", x, y, w, h);
        double centerX = posX + ((double) (width) /2);
        double centerY = posY + ((double) (height) /2);
        radius = 50;    
        
        hitBox = new Circle(centerX, centerY, radius);
    }
    
    public Circle getHitBox()
    {
        return hitBox;
    }
}

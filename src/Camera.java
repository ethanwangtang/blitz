import java.awt.*;          // access to Container
import java.awt.event.*;    // access to WindowAdapter, WindowEvent
import javax.naming.ldap.Control;
import javax.swing.*;       // access to JFrame and Jcomponents
import javax.swing.event.*;     // access to JSlider event
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.scene.shape.*;

/**
 * Write a description of class Camera here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Camera extends JPanel implements ActionListener
{
    private Map mapRef;
    private JFrame fRef;
    private int width, height;
    private Timer renderClock;
    private Swordsman swordsmanRef;
    private Archer archerRef;
    public Camera(Map mapImp)
    {
        mapRef = mapImp;
        width = 1000;
        height = 1000;
        JFrame f = new JFrame("Blitz");
        constructWindow(f);
        renderClock = new Timer(15, this);
        renderClock.start();
        swordsmanRef = mapRef.getSwordsman();
        archerRef = mapRef.getArcher();
        sTracker = 500;
    }

    public void constructWindow(JFrame newFrame)
    {
        //making our JFrame an entire class variable instead of instance variable
        fRef = newFrame;
        //starting bullshit in between here:
        //-----------------------------------
        fRef.setLayout(new BorderLayout(5, 5));
        fRef.add(this);

        setFocusable(true);
        requestFocusInWindow();
        fRef.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fRef.setSize(width, height);
        fRef.setVisible(true);
        //-----------------------------------
        repaint();
    }

    public void addController(Controller gameController)
    {
        addKeyListener(gameController.getController());
        addMouseListener(gameController.getMouseListener());
    }
    //Clockwork below
    public void actionPerformed(ActionEvent evt)
    {
        repaint();
    }
    public int centerX;// = 500;
    public int centerY;// = 500;
    private int sX, sY, aX, aY;
    private final int OFFSET = -450;
    private int sTracker;
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        sX = swordsmanRef.getScaledXPos();
        sY = swordsmanRef.getScaledYPos();
        aX = archerRef.getScaledXPos();
        aY = archerRef.getScaledYPos();

        
        
        centerX = -(OFFSET + sX - ((sX - aX)/2));

        //ok so this just sends the swordsman into the shadowrealm???
        /*if (centerX + swordsmanRef.getScaledXPos() < 100)
        {
            swordsmanRef.setX(archerRef.getXPos() - 5000);
        }*/
        //g.drawImage(swordsmanRef.getScaledImage(), centerX + sX, centerY + sX, this);

        for (Entity a : mapRef.getEntities())
        {
            if (true)
            {
                g.drawImage(a.getScaledImage(), centerX + a.getScaledXPos(), centerY + a.getScaledYPos(), this);
            }

            //g.drawImage(a.getSprite(), a.getXPos(), a.getYPos(), this);
        }

        for (Object a : mapRef.getTerrain())
        {
            g.drawImage(a.getScaledImage(), centerX + a.getScaledXPos(), centerY + a.getScaledYPos(), this);
            //g.drawImage(a.getSprite(), a.getXPos(), a.getYPos(), this);
        }
    }

}

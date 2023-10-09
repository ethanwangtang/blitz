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
 * Write a description of class Entity here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Entity extends Object
{
    protected boolean onGround;
    protected int velX, velY;
    protected Map mapRef;
    
    //no ghost entities allowed!
    public Entity(String imgName, Map mapImp, int x, int y, int w, int h)
    {
        super(imgName, x, y, w, h);
        id = "";
        mapRef = mapImp;
        mapRef.addEntity(this);
        allTerrain = mapRef.getTerrain();
        generateScaledImage();
    }
    //return value methods here:
    public int getVelX()
    {
        return velX;
    }

    public int getVelY()
    {
        return velY;
    }

    public boolean getGroundState()
    {
        return onGround;
    }

    public void setGroundState(boolean a)
    {
        onGround = a;
    }
    //pos and id methods moved to object class

    //Manipulation of properties below here
    public void set(int x, int y)
    {
        posX = x;
        posY = y;
    }
    
    public void setY(int y)
    {
        posY = y;
    }
    
    public void setX(int x)
    {
        posX = x;
    }

    public void move(int x, int y)
    {
        posX += x;
        posY += y;
    }
    private ArrayList<Object> allTerrain;
    public void control(int x, int y)
    {
        if (x > 0)
        {
            for (int i = 0; i < x; i++)
            {
                for (Object b : allTerrain)
                {
                    if (collidesWith(b))
                    {
                        terrainInteraction(b);
                    }
                }
                posX++;
            }
        }
        else if (x < 0)
        {
            for (int i = 0; i < -x; i++)
            {
                for (Object b : allTerrain)
                {
                    if (collidesWith(b))
                    {
                        terrainInteraction(b);
                    }
                }
                posX--;
            }
        }
        if (y > 0)
        {
            for(int i = 0; i < y; i++)
            {
                for (Object b : allTerrain)
                {
                    if (collidesWith(b))
                    {
                        terrainInteraction(b);
                    }
                }
                posY++;
            }  
        }
        else if (y < 0)
        {
            for(int i = 0; i < -y; i++)
            {
                for (Object b : allTerrain)
                {
                    if (collidesWith(b))
                    {
                        terrainInteraction(b);
                    }
                }
                posY--;
            }  
        }
    }
    
    public void force(int x, int y)
    {
        velX = x;
        velY = y;
    }

    public void push(int x, int y)
    {
        velX += x;
        velY += y;
    }
    //Interactions
    public void applyPhysics(ArrayList<Object> allTerrain)
    {
        onGround = false;
        if (velX > 0)
        {
            for (int i = 0; i < velX; i++)
            {
                for (Object b : allTerrain)
                {
                    if (collidesWith(b))
                    {
                        terrainInteraction(b);
                    }
                }
                posX++;
            }
        }
        else if (velX < 0)
        {
            for (int i = 0; i < -velX; i++)
            {
                for (Object b : allTerrain)
                {
                    if (collidesWith(b))
                    {
                        terrainInteraction(b);
                    }
                }
                posX--;
            }
        }
        if (velY > 0)
        {
            for(int i = 0; i < velY; i++)
            {
                for (Object b : allTerrain)
                {
                    if (collidesWith(b))
                    {
                        terrainInteraction(b);
                    }
                }
                posY++;
            }  
        }
        else if (velY < 0)
        {
            for(int i = 0; i < -velY; i++)
            {
                for (Object b : allTerrain)
                {
                    if (collidesWith(b))
                    {
                        terrainInteraction(b);
                    }
                }
                posY--;
            }  
        }
    }

    public boolean collidesWith(Object other)
    {
        Rectangle a = new Rectangle(posX, posY, width, height);

        Rectangle b = new Rectangle(other.getXPos(), other.getYPos(), other.getWidth(), other.getHeight());
        if (a.intersects(b))
        {
            return true;
        }
        return false;
    }

    public void terrainInteraction(Object a)
    {
        //How deep the entity can go into the terrain and still be repelled out of it
        int buffer;
        //buffer set here
        buffer = 2;
        //set variables for higher clarity
        int terrainTopSide, terrainBotSide, terrainLeftSide, terrainRightSide;
        int entityTopSide, entityBotSide, entityLeftSide, entityRightSide;
        //Entities sides defined here:
        entityTopSide = posY;
        entityBotSide = posY + height;
        entityLeftSide = posX;
        entityRightSide = posX + width;
        //whereas terrain is defined here because we go through all of it.
        terrainTopSide = a.getYPos();
        terrainBotSide = a.getYPos() + a.getHeight();
        terrainLeftSide = a.getXPos();
        terrainRightSide = a.getXPos() + a.getWidth();
        if (entityBotSide >= terrainTopSide && entityBotSide <= terrainTopSide + buffer)
        {
            onGround = true;
            move(0, -1);
            force(0, 0);
        }
        if (entityTopSide <= terrainBotSide && entityTopSide >= terrainBotSide - buffer)
        {
            move(0, 1);
            force(0, 0);
        }
        if (entityRightSide >= terrainLeftSide && entityRightSide <= terrainLeftSide + buffer)
        {
            move(-1, 0);
        }
        if (entityLeftSide <= terrainRightSide && entityLeftSide >= terrainRightSide - buffer)
        {
            move(1, 0);
        }
    }
}

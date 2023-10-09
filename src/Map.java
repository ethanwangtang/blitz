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
import java.util.ArrayList;
import java.net.URL;
/**
 * Write a description of class Map here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Map implements ActionListener
{
    private int[] terrainGen;
    private ArrayList<Entity> allEntities;
    private ArrayList<Object> allTerrain;

    private ArrayList<Object> allArrows;
    private ArrayList<StasisField> allFields;
    //gameClock has to be less than the fps god damnit
    private Timer gameClock;
    private Swordsman swordsmanRef;
    private Archer archerRef;
    public Map()
    {
        gameClock = new javax.swing.Timer(15, this);
        gameClock.start();
        allEntities = new ArrayList<Entity>();
        allTerrain = new ArrayList<Object>();
        allArrows = new ArrayList<Object>();
        allFields = new ArrayList<StasisField>();
    }
    //Plugging in entities and players and stuff
    
    public void addEntity(Entity toAdd)
    {
        allEntities.add(toAdd);
    }

    public void addTerrain(Terrain toAdd)
    {
        allTerrain.add(toAdd);
    }    
    
    public void addArcher(Archer a)
    {
        archerRef = a;
    }

    public void addArrow(Arrow a) { allArrows.add(a); }
    public void addSwordsman(Swordsman a)
    {
        swordsmanRef = a;
    }
    //return methods down here
    public Swordsman getSwordsman()
    {
        return swordsmanRef;
    }

    public Archer getArcher()
    {
        return archerRef;
    }
    
    public ArrayList<Entity> getEntities()
    {
        return allEntities;
    }

    public ArrayList<Object> getTerrain()
    {
        return allTerrain;
    }
    //Clock cycle stuff
    public void actionPerformed(ActionEvent evt)
    {
        physicsRun();
    }
    public void physicsRun()
    {
        for (Entity a : allEntities)
        {
            //gravity sim
            if (a.getGroundState() == false)
            {
                if (a.getVelY() < 50)
                {
                    a.push(0, 2);
                }
            }
            else
            {
                a.force(0, 1);
            }
            a.applyPhysics(allTerrain);
            a.applyPhysics(allArrows);
        }
    }
}

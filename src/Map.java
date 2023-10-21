import javafx.util.Pair;

import java.awt.event.*;    // access to WindowAdapter, WindowEvent
import javax.swing.*;       // access to JFrame and Jcomponents
import java.util.ArrayList;

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
    private ArrayList<Block> allBlocks;

    private ArrayList<Arrow> allArrows;

    private ArrayList<Object> removalQueue;
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
        allArrows = new ArrayList<Arrow>();
        allBlocks = new ArrayList<Block>();
        removalQueue = new ArrayList<Object>();
    }
    //Plugging in entities and players and stuff

    public void addBlock(Block toAdd) {allBlocks.add(toAdd);}

    public void addToRemovalQueue(Object toAdd) {removalQueue.add(toAdd);}
    
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
    public ArrayList<Arrow> getArrows() { return allArrows; }

    public ArrayList<Block> getBlocks() { return allBlocks; }
    //Clock cycle stuff
    public void actionPerformed(ActionEvent evt)
    {
        physicsRun();
    }
    public void physicsRun()
    {
        for (Entity a : allEntities)
        {
            a.applyPhysics();
        }
        for(Object a : removalQueue)
        {
            allEntities.remove(a);
        }
        removalQueue.clear();
    }
}


/**
 * Write a description of class Terrain here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Terrain extends Object
{
    protected String id;
    public Terrain(Map mapRef, int x, int y, int w, int h)
    {
        super("terrain.png", x, y, w, h);
        id = "terrain";
        mapRef.addTerrain(this);
        generateScaledImage();
    }
    
    //Return methods in abstract class objects now
}

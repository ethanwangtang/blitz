
/**
 * Write a description of class Archer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Archer extends Entity
{
    public Archer(Map mapImp)
    {
        super("archer.png", mapImp, 50, 0, 50, 100);
        id = "archer";
        
        mapRef.addArcher(this);
    }
}
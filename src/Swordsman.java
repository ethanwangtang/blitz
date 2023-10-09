
/**
 * Write a description of class Swordsman here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Swordsman extends Entity
{
    public Swordsman(Map mapImp)
    {
        super("swordsman.png", mapImp, 0, 0, 50, 100);
        id = "swordsman";
        
        mapRef.addSwordsman(this);
    }
}


/**
 * Write a description of class Archer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Archer extends Entity
{
    private int cooldown;
    public Archer(Map mapImp)
    {
        super("archer.png", mapImp, 50, 0, 50, 100);
        id = "archer";
        
        mapRef.addArcher(this);
        cooldown = 0;
    }

    @Override public void applyPhysics()
    {
        super.applyPhysics();
        if (cooldown > 0)
        {
            cooldown--;
        }
    }

    public boolean onCooldown()
    {
        if(cooldown > 0)
        {
            return true;
        }
        return false;
    }

    public void applyCooldown()
    {
        cooldown = 60;
    }
}
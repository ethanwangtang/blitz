
/**
 * Write a description of class Swordsman here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Swordsman extends Entity
{
    private int blockCooldown, attackCooldown;
    public int movementDir;

    private Arrow arrowOn = null;
    public Swordsman(Map mapImp)
    {
        super("swordsman.png", mapImp, 0, 0, 50, 100);
        id = "swordsman";
        
        mapRef.addSwordsman(this);
        blockCooldown = 0;
        attackCooldown = 0;
        movementDir = 0;
        //we'll say 0 is up, 1 is down, 2 is left, and 3 is right
    }

    public boolean getBlockCooldown() {
        return (blockCooldown > 0);
    }

    public void applyBlockCooldown(int cooldown){
        blockCooldown = cooldown;
    }

    public boolean getAttackCooldown() {
        return (attackCooldown > 0);
    }

    public void applyAttackCooldown(int cooldown){
        attackCooldown = cooldown;
    }

    public void applyPhysics(){
        super.applyPhysics();
        if (getBlockCooldown())
        {
            blockCooldown--;
        }
        if (getAttackCooldown())
        {
            attackCooldown--;
        }
    }
}

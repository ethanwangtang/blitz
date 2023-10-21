public class Arrow extends Entity{

    /**
     * Write a description of class Archer here.
     *
     * @author (your name)
     * @version (a version number or a date)
     */

    public Arrow(Map mapImp, int x, int y)
    {
        super("terrain.png", mapImp, x, y, 200, 200);
        id = "arrow";

        mapImp.addArrow(this);
    }

    @Override public void arrowInteraction(Arrow a)
    {
        if(true)
        {
            return; //we are just gonna skip arrow to arrow interactions for now.
        }
        if(a == this)
        {
            return; //avoid self interactions.
        }
        //I think the best approach for this is to have arrows interact on other arrows than vice versa,
        //where they just receive interactions from other arrows,
        //How deep the entity can go into the terrain and still be repelled out of it
        int buffer;
        //buffer set here
        buffer = 25;
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
        //something about collisions causes massive lag, i assume it's due to both
        //of them trying to interact with each other at the same time.

        //perhaps an "active" mode would fix that, but for now im gonna skip that.
        //at best I will implement stacking them on top of each other.
        if (entityBotSide >= terrainTopSide && entityBotSide <= terrainTopSide + buffer)
        {
            onGround = true;
            move(0, -1);
        }
        if (entityTopSide <= terrainBotSide && entityTopSide >= terrainBotSide - buffer)
        {
            move(0, 1);
            force(velX, 0);
        }
        if (entityRightSide >= terrainLeftSide && entityRightSide <= terrainLeftSide + buffer)
        {
            move(-1, 0);
            a.push(velX, velY);
        }
        if (entityLeftSide <= terrainRightSide && entityLeftSide >= terrainRightSide - buffer)
        {
            move(1,0);
            a.push(velX, velY);
        }
    }

    @Override
    public void applyPhysics()
    {
        super.applyPhysics();
        Swordsman sm = mapRef.getSwordsman();
        if (collidesWith(sm))
        {
            sm.force(velX, velY);
            sm.setY(posY-sm.height);
            sm.onGround = true;
        }
    }
}

public class Arrow extends Entity{

    /**
     * Write a description of class Archer here.
     *
     * @author (your name)
     * @version (a version number or a date)
     */

    public Arrow(Map mapImp, int x, int y)
    {
        super("terrain.png", mapImp, x, y, 100, 100);
        id = "arrow";

        mapImp.addArrow(this);
    }

    @Override public void arrowInteraction(Arrow a)
    {
        if(a == this)
        {
            return;
        }
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
        if (entityBotSide >= terrainTopSide && entityBotSide <= terrainTopSide + buffer)
        {
            onGround = true;
            move(0, -1);
            //absorb velocity on object below
            force(a.velX, velY);
        }
        if (entityTopSide <= terrainBotSide && entityTopSide >= terrainBotSide - buffer)
        {
            //a.force(velX, velY);//top one takes velocity
        }
        //something below about these types of collision causes massive lag.
        if (entityRightSide >= terrainLeftSide && entityRightSide <= terrainLeftSide + buffer)
        {
            //force(velX/2, velY);
            //a.push(velX/2, a.getVelY());
        }
        if (entityLeftSide <= terrainRightSide && entityLeftSide >= terrainRightSide - buffer)
        {
            //force(velX/2, velY);
            //a.push(velX/2, 0);
        }
    }
}

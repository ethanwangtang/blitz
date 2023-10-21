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
        //you have to put it BEFORE terrain interaction, I guess that makes sense?
        this.force((a.getVelX() + this.getVelX()) / 2, (a.getVelY() + this.getVelY()) / 2);
        a.force((a.getVelX() + this.getVelX()) / 2, (a.getVelY() + this.getVelY()) / 2);
        super.arrowInteraction(a);
        /*methodology here is to have arrows interact like they're just terrain,
        but since we are limiting arrows to impacting ones further down the line
            (aka earlier spawned ones have precedence)
        then in this scenario I believe we should apply interactions in reverse order
        e.g. we should receive input from blocks that we are checking with.
         */
    }

    private boolean checkAllArrows()
    {
        int startHere = allArrows.indexOf(this) + 1;
        for (int i = startHere; i < allArrows.size(); i++)
        {
            Arrow b = allArrows.get(i);
            if (collidesWith(b))
            {
                arrowInteraction(b);
                return true;
            }
        }
        return false;
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

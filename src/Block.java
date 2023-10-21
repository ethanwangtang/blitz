import javafx.geometry.Bounds;
import javafx.scene.shape.*;

import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * Write a description of class Block here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Block extends Entity
{
    private int lifeTime;
    public Block(Map mapInp, int x, int y, int w, int h, int lifeTimeInp)
    {
        super("stasis_field.png", mapInp, x, y, w, h);
        id = "block";
        lifeTime = lifeTimeInp;
    }

    @Override
    public void applyPhysics() {
        if(lifeTime == 0)
        {
            mapRef.addToRemovalQueue(this);
        }
        for (Arrow a : mapRef.getArrows())
        {
            if (collidesWith(a))
            {
                a.force(0,0);
            }
        }
        set(mapRef.getSwordsman().getXPos()-25, mapRef.getSwordsman().getYPos()-25);
        lifeTime--;
    }
}

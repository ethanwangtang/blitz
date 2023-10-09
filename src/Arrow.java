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
}

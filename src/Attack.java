public class Attack extends Entity{
    private int lifeTime;
    private int dir;
    static int attackForce = 50;
    public Attack(Map mapInp, int x, int y, int w, int h, int lifeTimeInp, int dir)
    {
        super("attack.png", mapInp, x, y, w, h);
        id = "attack";
        lifeTime = lifeTimeInp;
        this.dir = dir;
    }

    public void setLocation()
    {
        Swordsman sm = mapRef.getSwordsman();
        switch(dir)
        {
            case 0: set(sm.getXPos()-25, sm.getYPos()-25);
                    break;
            case 1: set(sm.getXPos()-25, sm.getYPos());
                    break;
            case 2: set(sm.getXPos()-50, sm.getYPos()+5);
                    break;
            case 3: set(sm.getXPos()+50, sm.getYPos()+5);
                    System.out.println("This case");
                    break;
            default: System.out.println("Error: No direction");
        }
    }

    @Override
    public void applyPhysics() {
        if(lifeTime == 0)
        {
            mapRef.addToRemovalQueue(this);
        }
        setLocation();
        for (Arrow a : mapRef.getArrows())
        {
            if(collidesWith(a))
            {
                switch (dir) {
                    case 0: a.force(0, -attackForce);
                            break;
                    case 1: a.force(0, attackForce);
                            break;
                    case 2: a.force(-attackForce, -attackForce/2);
                            break;
                    case 3: a.force(attackForce, -attackForce/2);
                            break;
                }
            }
        }
        lifeTime--;
    }
}

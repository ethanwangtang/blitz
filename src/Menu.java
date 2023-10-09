
/**
 * Write a description of class Menu here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Menu
{
    public static void main(String[] args)
    {
        Map gameSpace = new Map();
        
        Swordsman player1 = new Swordsman(gameSpace);
        Archer player2 = new Archer(gameSpace);
        Camera gameViewer = new Camera(gameSpace);
        
        Controller playerControls = new Controller(player1, player2, gameSpace, gameViewer);
        gameViewer.addController(playerControls);
        gameViewer.addMouseListener(playerControls);
        
        Terrain terrain1 = new Terrain(gameSpace, 0, 2000, 1000, 500);
        Terrain terrain2 = new Terrain(gameSpace, -500, 2200, 100000, 500);
        Terrain terrain3 = new Terrain(gameSpace, 1000, 1500, 2500, 300);
        Terrain terrain4 = new Terrain(gameSpace, -500, -50, 50, 10000);
    }
}

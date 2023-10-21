import java.awt.*;          // access to Container
import java.awt.event.*;    // access to WindowAdapter, WindowEvent

import javax.swing.*;       // access to JFrame and Jcomponents
import javax.swing.event.*;     // access to JSlider events

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.util.ArrayList;
import java.net.URL;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;

/**
 * Write a description of class Controller here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Controller implements ActionListener, MouseListener
{
    private KeyListener controls;


    private MouseListener mouseListener;
    private Swordsman swordsmanRef;
    private Archer archerRef;

    private Map mapRef;
    private Camera viewer;
    //boolean references to keys (if they are pressed);
    private boolean w, a, s, d, up, down, left, right, t, y, space;
    public Controller(Swordsman player1, Archer player2, Map mapInp, Camera viewer)
    {
        mapRef = mapInp;
        this.viewer = viewer;
        swordsmanRef = player1;
        archerRef = player2;
        Timer controlTimer = new Timer(10, this);
        controlTimer.start();
        controls = new KeyListener()
        {
            public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_W)
                {
                    w = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_A)
                {
                    a = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_D)
                {
                    d = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_S)
                {
                    s = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_T)
                {
                    t = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_Y)
                {
                    y = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE)
                {
                    space = true;
                }

                
                if (e.getKeyCode() == KeyEvent.VK_UP)
                {
                    up = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN)
                {
                    down = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT)
                {
                    left = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT)
                {
                    right = true;
                }
            }
            public void keyReleased(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_W)
                {
                    w = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_A)
                {
                    a = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_D)
                {
                    d = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_S)
                {
                    s = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_T)
                {
                    t = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_Y)
                {
                    y = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE)
                {
                    space = false;
                }
                
                if (e.getKeyCode() == KeyEvent.VK_UP)
                {
                    up = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN)
                {
                    down = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT)
                {
                    left = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT)
                {
                    right = false;
                }
            }
            public void keyTyped(KeyEvent e)
            {
                
            }
        };
    }
    
    public KeyListener getController()
    {
        return controls;
    }
    public MouseListener getMouseListener() { return this; }
    
    public void actionPerformed(ActionEvent evt)
    {
        if (a == true)
        {
            swordsmanRef.control(-25, 0);
            swordsmanRef.movementDir = 2;
        }
        if (d == true)
        {
            swordsmanRef.control(25, 0);
            swordsmanRef.movementDir = 3;
        }
        if (w == true)
        {
            swordsmanRef.movementDir = 0;
        }
        if (s == true)
        {
            swordsmanRef.force(0, 50);
            swordsmanRef.movementDir = 1;
        }
        if (t == true)
        {
            if (!swordsmanRef.getAttackCooldown())
            {
                switch(mapRef.getSwordsman().movementDir){
                    case 0: new Attack(mapRef, swordsmanRef.getXPos()-25, swordsmanRef.getYPos()-25, 100, 50, 5, 0);
                            break;
                    case 1: new Attack(mapRef, swordsmanRef.getXPos()-25, swordsmanRef.getYPos(), 100, 125, 5, 1);
                        break;
                    case 2: new Attack(mapRef, swordsmanRef.getXPos()-50, swordsmanRef.getYPos()+5, 50, 100, 5, 2);
                        break;
                    case 3: new Attack(mapRef, swordsmanRef.getXPos()+50, swordsmanRef.getYPos()+5, 50, 100, 5, 3);
                        break;
                    default: System.out.println("Error: No direction");
                }
                swordsmanRef.applyAttackCooldown(25);
            }
        }
        if (y == true)
        {
            if (swordsmanRef.getBlockCooldown())
            {

            }
            else {
                new Block(mapRef, swordsmanRef.getXPos() - 25, swordsmanRef.getYPos() - 25, 100, 150, 10);
                swordsmanRef.applyBlockCooldown(50);
            }
        }
        if (space && swordsmanRef.getGroundState() == true)
        {
            swordsmanRef.force(0, -50);
            swordsmanRef.setGroundState(false);
        }
        
        if (left == true)
        {
            archerRef.control(-50, 0);
        }
        if (right == true)
        {
            archerRef.control(50, 0);
        }
        if (up == true && archerRef.getGroundState() == true)
        {
            archerRef.force(0, -50);
            archerRef.setGroundState(false);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(archerRef.onCooldown())
        {
            //do nothing
        }
        else
        {
            //true x and y location calculated in relation to the camera and such.
            int x = (int)((e.getX() - viewer.centerX)*Object.SCALE);
            int y = (int)((e.getY() - viewer.centerY)*Object.SCALE);

            Arrow arrow = new Arrow(mapRef, archerRef.posX,archerRef.posY-100);
            //calculate trajectory
            double x_relative = x - archerRef.posX;
            double y_relative = y - archerRef.posY;

            double angle = Math.atan2(y_relative, x_relative);
            double force = 100;
            arrow.force((int)(force * Math.cos(angle)), (int)(force * Math.sin(angle)));
            archerRef.applyCooldown();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

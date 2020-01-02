package untitled5;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Movement implements MouseListener, MouseMotionListener
{

    private int X,Y;

    public Movement(Component... pns)
    {


         for(Component panel : pns)
        {
            panel.addMouseListener(this);
            panel.addMouseMotionListener(this);



        }



    }
    {



    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //e.getComponent().setLocation((e.getX()+e.getComponent().getX())-X,(e.getY()+e.getComponent().getY())-Y);
        int x2=(e.getX()+e.getComponent().getX())-X;
        if(x2<0) x2=0;
        if(x2>400) x2=400;

        e.getComponent().setLocation(x2,70);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        X= e.getX();
        Y=e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

}



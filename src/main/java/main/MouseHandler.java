package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {
    public boolean mPressed, mReleased;

    @Override
    public void mousePressed(MouseEvent e) {
        mPressed = true;
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        mReleased = true;
    }
    @Override
    public void mouseEntered(MouseEvent e) {

    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }
}

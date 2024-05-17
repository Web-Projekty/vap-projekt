package me.projekt.game.gamestates;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface StateMethods {

    void update();

    void draw(Graphics g);

    void mouseClicked(MouseEvent e);

    void mousePressed(MouseEvent e);

    void mouseReleased(MouseEvent e);

    void mouseMoved(MouseEvent e);

    void keyPressed(KeyEvent e);

    void keyReleased(KeyEvent e);
}

package me.projekt.game.main;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class GameWindow extends JFrame {

    public GameWindow(String windowName, GamePanel gamePanel) {
        super(windowName);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.add(gamePanel);

        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                gamePanel.getGame().windowFocusLost();
            }
        });
    }
}

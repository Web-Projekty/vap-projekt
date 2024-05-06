package me.projekt.game.input;

import me.projekt.game.gamestates.GameState;
import me.projekt.game.main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInput implements KeyListener {

    private GamePanel gamePanel;

    public KeyboardInput(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (GameState.getState()) {
            case MENU:
                gamePanel.getGame().getMenu().keyPressed(e);
                break;
            case OPTIONS:
                gamePanel.getGame().getOptions().keyPressed(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().keyPressed(e);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (GameState.getState()) {
            case MENU:
                gamePanel.getGame().getMenu().keyReleased(e);
                break;
            case OPTIONS:
                gamePanel.getGame().getOptions().keyReleased(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().keyReleased(e);
                break;
        }
    }
}

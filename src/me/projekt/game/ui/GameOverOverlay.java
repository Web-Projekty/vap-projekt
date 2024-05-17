package me.projekt.game.ui;

import me.projekt.game.gamestates.GameState;
import me.projekt.game.gamestates.Playing;
import me.projekt.game.main.Game;
import me.projekt.game.player.Player;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GameOverOverlay {
    private Playing playing;

    public GameOverOverlay(Playing playing) {
        this.playing = playing;
    }

    public void draw(Graphics g) {
        g.setColor(new Color(0,0,0,200));
        g.fillRect(0,0, Game.GAME_WIDTH, Game.GAME_HEIGHT);

        g.setColor(Color.WHITE);
        g.drawString("Game Over", Game.GAME_WIDTH/2, 150);
        g.drawString("Press Esc to continue", Game.GAME_WIDTH/2, 300);

    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            playing.resetAll();
            GameState.state = GameState.MENU;
        }
    }
}

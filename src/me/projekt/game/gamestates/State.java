package me.projekt.game.gamestates;

import me.projekt.game.ui.buttons.MenuButton;
import me.projekt.game.main.Game;

import java.awt.event.MouseEvent;

public class State {

    protected Game game;

    public State(Game game) {
        this.game = game;
    }

    public boolean isIn(MouseEvent e, MenuButton mb) {
        return mb.getBounds().contains(e.getX(), e.getY());
    }

    public Game getGame() {
        return this.game;
    }
}

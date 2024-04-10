package me.projekt.game.gamestates;

import me.projekt.game.ui.MenuButton;
import me.projekt.game.main.Game;

import java.awt.event.MouseEvent;

public class State {

    protected Game game;

    public boolean IsIn(MouseEvent e, MenuButton mb){
         return mb.getBounds().contains(e.getX(), e.getY());
    }


    public State(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return this.game;
    }
}

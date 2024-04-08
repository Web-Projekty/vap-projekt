package me.florixak.game.gamestates;

import me.florixak.game.main.Game;

public class State {

    protected Game game;

    public State(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return this.game;
    }
}

package me.projekt.game.gamestates;

public enum GameState {

    MENU,
    OPTIONS,
    PLAYING,
    QUIT;

    public static GameState state = MENU;

    public static GameState getState() {
        return state;
    }

    public static void setState(GameState newState) {
        state = newState;
    }
}

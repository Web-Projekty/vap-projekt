package me.projekt.game.enemies;

import me.projekt.game.gamestates.Playing;

public class Enemy extends Entity {
    public Enemy(Playing playing, float x, float y, int width, int height) {
        super(playing, x, y, width, height);
    }
}

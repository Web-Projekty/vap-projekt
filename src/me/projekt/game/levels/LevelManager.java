package me.projekt.game.levels;

import me.projekt.game.main.Game;
import me.projekt.game.utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import static me.projekt.game.main.Game.TILES_SIZE;

public class LevelManager {

    private Game game;
    private BufferedImage[] levelSprite;
    private Level levelOne;

    public LevelManager(Game game) {
        this.game = game;
        importSprites();
        levelOne = new Level(LoadSave.getLevelData());
    }

    private void importSprites() {
        BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.LEVEL_ATLAS);

        levelSprite = new BufferedImage[48]; // 4x12 spritů v sheetu
        for (int j = 0; j < 4; j++) { // projde řádky
            for (int i = 0; i < 12; i++) { // projde sloupce
                int index = j * 12 + i; // vypočítá index pro sprite
                levelSprite[index] = img.getSubimage(i * 32, j * 32, 32, 32);
            }
        }
    }

    public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {

        for (int j = 0; j < levelOne.getLevelData().length; j++) {
            for (int i = 0; i < levelOne.getLevelData()[0].length; i++) {
                int index = levelOne.getSpriteIndex(i, j);
                g.drawImage(levelSprite[index], TILES_SIZE * i - xLvlOffset, TILES_SIZE * j - yLvlOffset, TILES_SIZE, TILES_SIZE, null);
            }
        }
    }

    public void update() {

    }

    public Level getCurrentLevel() {
        return levelOne;
    }
}
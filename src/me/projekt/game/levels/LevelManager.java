package me.projekt.game.levels;

import me.projekt.game.gamestates.GameState;
import me.projekt.game.gamestates.Playing;
import me.projekt.game.main.Game;
import me.projekt.game.utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static me.projekt.game.main.Game.TILES_SIZE;

public class LevelManager {

    private Playing playing;
    private BufferedImage[] levelSprite;
    private ArrayList<Level> levels;
    private int levelIndex = 0;

    public LevelManager(Playing playing) {
        this.playing = playing;
        importSprites();
        levels = new ArrayList<>();
        setupLevels();
    }

    private void setupLevels() {
        BufferedImage[] allLevels = LoadSave.getLevels();

        for (BufferedImage img : allLevels) {
            levels.add(new Level(img));
        }
    }

    public void loadNextLevel() {
        levelIndex++;
        if (levelIndex >= levels.size()) {
            levelIndex = 0;
            System.out.println("No more levels! Game completed!");
            GameState.setState(GameState.MENU);
        }

        Level newLevel = levels.get(levelIndex);
        // game.getPlaying().getEnemyManager().loadEnemies(newLevel);
        playing.getPlayer().loadLevelData(newLevel.getLevelData());
        playing.setMaxLevelOffsets(newLevel.getMaxLvlOffsetX(), newLevel.getMaxLvlOffsetY());
        playing.getObjectManager().loadObjects(newLevel);
    }

    private void importSprites() {
        BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.LEVEL_ATLAS);

        int spritesInSheet = 48;
        int rows = 4;
        int columns = 12;

        levelSprite = new BufferedImage[spritesInSheet]; // 4x12 spritů v sheetu
        for (int j = 0; j < rows; j++) { // projde řádky
            for (int i = 0; i < columns; i++) { // projde sloupce
                int index = j * columns + i; // vypočítá index pro sprite
                levelSprite[index] = img.getSubimage(i * 32, j * 32, 32, 32);
            }
        }
    }

    public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {

        for (int j = 0; j < levels.get(levelIndex).getLevelData().length; j++) {
            for (int i = 0; i < levels.get(levelIndex).getLevelData()[0].length; i++) {
                int index = levels.get(levelIndex).getSpriteIndex(i, j);
                g.drawImage(levelSprite[index], TILES_SIZE * i - xLvlOffset, TILES_SIZE * j - yLvlOffset, TILES_SIZE, TILES_SIZE, null);
            }
        }
    }

    public void update() {
        // animace třeba vody
    }

    public void setCurrentLevel(int levelIndex) {
        this.levelIndex = levelIndex;
    }

    public Level getCurrentLevel() {
        return levels.get(levelIndex);
    }

    public int getAmountOfLevels() {
        return levels.size();
    }
}
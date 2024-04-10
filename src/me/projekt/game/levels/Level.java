package me.projekt.game.levels;

import me.projekt.game.main.Game;
import me.projekt.game.utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

import static me.projekt.game.utils.Utils.getPlayerSpawn;

public class Level {

    private BufferedImage img;
    private int[][] lvlData;
//    private ArrayList<Slime> slimes;

    private int lvlTilesWide;
    private int maxTilesOffsetX;
    private int maxLvlOffsetX;

    private int lvlTilesHigh;
    private int maxTilesOffsetY;
    private int maxLvlOffsetY;

    private Point spawn;

    public Level(BufferedImage img) {
        this.img = img;
        setLevelData();
        //addEmemies();
        setLevelOffsets();
        setPlayerSpawn();
    }


    private void setLevelData() {
        this.lvlData = Utils.getLevelData(this.img);
    }

    private void setPlayerSpawn() {
        this.spawn = getPlayerSpawn(img);
    }

    private void addEmemies() {

    }

    private void setLevelOffsets() {
        lvlTilesWide = img.getWidth();
        maxTilesOffsetX = lvlTilesWide - Game.TILES_IN_WIDTH;
        maxLvlOffsetX = Game.TILES_SIZE * maxTilesOffsetX;

        lvlTilesHigh = img.getHeight();
        maxTilesOffsetY = lvlTilesHigh - Game.TILES_IN_HEIGHT;
        maxLvlOffsetY = Game.TILES_SIZE * maxTilesOffsetY;
    }

    public int getSpriteIndex(int x, int y) {
        return lvlData[y][x];
    }

    public int[][] getLevelData() {
        return this.lvlData;
    }

    public int getMaxLvlOffsetX() {
        return this.maxLvlOffsetX;
    }

    public int getMaxLvlOffsetY() {
        return this.maxLvlOffsetY;
    }

    public Point getSpawn() {
        return this.spawn;
    }
}

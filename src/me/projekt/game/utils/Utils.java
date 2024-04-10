package me.projekt.game.utils;

import me.projekt.game.main.Game;

import java.awt.geom.Rectangle2D;

public class Utils {

    public static boolean canMoveTo(float x, float y, float width, float height, int[][] lvlData) {

        if (!isSolid(x, y, lvlData)) {
            if (!isSolid(x + width, y + height, lvlData)) {
                if (!isSolid(x + width, y, lvlData)) {
                    if (!isSolid(x, y + height, lvlData)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean isSolid(float x, float y, int[][] lvlData) {
        int maxWidth = lvlData[0].length * Game.TILES_SIZE;
        int maxHeight = lvlData.length * Game.TILES_SIZE;
        if (x < 0 || x >= maxWidth) // pokud by chtěl hráč vyjít z okna doprava nebo doleva, je mu to zakázáno
            return true;
        if (y < 0 || y >= maxHeight) // pokud by chtěl hráč vyjít z okna nahoru nebo dolu, je mu to zakázáno
            return true;

        float xIndex = x / Game.TILES_SIZE;
        float yIndex = y / Game.TILES_SIZE;

        int value = lvlData[(int) yIndex][(int) xIndex];

        if (value >= 48 || value < 0 || value != 11) {
            // pokud se na indexech nachází jeden z tilů, tak hráč narazí na kolizi, neprojde
            return true;
        }
        return false;
    }

    public static float getXNextToWall(Rectangle2D.Float hitbox, float xSpeed) {
        int currentTile = (int) (hitbox.x / Game.TILES_SIZE);
        if (xSpeed > 0) {
            // Right
            int tileXPos = currentTile * Game.TILES_SIZE;
            int xOffset = (int) (Game.TILES_SIZE - hitbox.width);
            return tileXPos + xOffset - 1; // bez -1 bychom byli v tilu
        }
        // Left
        return currentTile * Game.TILES_SIZE;
    }

    public static float getYUndRoofOrAboFloor(Rectangle2D.Float hitbox, float airSpeed) {
        int currentTile = (int) (hitbox.y / Game.TILES_SIZE);
        if (airSpeed > 0) {
            // Falling - touching floor
            int tileYPos = currentTile * Game.TILES_SIZE;
            int yOffset = (int) (Game.TILES_SIZE - hitbox.height);
            return tileYPos + yOffset - 1; // bez -1 bychom byli v tilu
        }
        return currentTile * Game.TILES_SIZE;
    }

    public static boolean isOnFloor(Rectangle2D.Float hitbox, int[][] lvlData) {
        // check the pixel below bottomleft and bottomright
        if (!isSolid(hitbox.x, hitbox.y + hitbox.height + 1, lvlData))
            if (!isSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, lvlData)) return false;

        return true;
    }
}

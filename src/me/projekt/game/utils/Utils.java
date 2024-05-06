package me.projekt.game.utils;

import me.projekt.game.main.Game;
import me.projekt.game.objects.destroyable.Box;
import me.projekt.game.objects.pickable.Potion;
import me.projekt.game.objects.pickable.Soul;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static me.projekt.game.objects.ObjectType.*;

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

        if (value != 17) { // pokud se na indexech nachází jeden ze solid tilů, tak hráč narazí na kolizi, neprojde
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

    public static int[][] getLevelDataFromImage(BufferedImage img) {
        int[][] lvlData = new int[img.getHeight()][img.getWidth()];

        for (int j = 0; j < img.getHeight(); j++) { // projedeme loopem každý pixel v okně
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getRed();
                if (value >= Constants.Map.SPRITES_IN_SHEET-2 || isTileTransparent(color)) { // pokud se přesáhne počet tilů ve spritesheetu pro červenou barvu
                    value = 17;
                }
                lvlData[j][i] = value;
            }
        }
        return lvlData;
    }

    private static boolean isTileTransparent(Color color) {
        if (color.getRed() == 0 && color.getGreen() == 0 && color.getBlue() == 0) return true;
        return false;
    }

    public static Point getPlayerSpawnFromImage(BufferedImage img) {
        for (int j = 0; j < img.getHeight(); j++) {
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getRed();
                if (value == 100) {
                    return new Point(i * Game.TILES_SIZE, j * Game.TILES_SIZE);
                }
            }
        }
        return new Point(1 * Game.TILES_SIZE, 1 * Game.TILES_SIZE);
    }

    public static ArrayList<Potion> getPotionsFromImage(BufferedImage img) {
        ArrayList<Potion> list = new ArrayList<>();
        for (int j = 0; j < img.getHeight(); j++)
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getRed();
                if (value == RED_POTION.getRedValue() || value == BLUE_POTION.getRedValue())
                    list.add(new Potion(i * Game.TILES_SIZE, j * Game.TILES_SIZE, getObjectByValue(value)));

            }
        return list;
    }

    public static ArrayList<Soul> getSoulsFromImage(BufferedImage img) {
        ArrayList<Soul> list = new ArrayList<>();
        for (int j = 0; j < img.getHeight(); j++)
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getRed();
                if (value == SOUL.getRedValue())
                    list.add(new Soul(i * Game.TILES_SIZE, j * Game.TILES_SIZE));
            }
        return list;
    }

    public static ArrayList<Box> getContainersFromImage(BufferedImage img) {
        ArrayList<Box> list = new ArrayList<>();
        for (int j = 0; j < img.getHeight(); j++)
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getRed();
                if (value == BOX.getRedValue() || value == BARREL.getRedValue())
                    list.add(new Box(i * Game.TILES_SIZE, j * Game.TILES_SIZE, getObjectByValue(value)));
            }
        return list;
    }
}
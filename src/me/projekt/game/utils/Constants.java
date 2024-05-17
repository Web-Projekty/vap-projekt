package me.projekt.game.utils;

import me.projekt.game.main.Game;

public class Constants {

    public static class EnemyConstants {
        public static final int CRABBY = 101;
        public static final int GHOST = 111;
        public static final int SLIME = 121;

        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int ATTACK = 2;
        public static final int HIT = 3;
        public static final int DEAD = 4;

        public static final int CRABBY_WIDTH_DEFAULT = 72;
        public static final int CRABBY_HEIGHT_DEFAULT = 32;
        public static final int GHOST_WIDTH_DEFAULT = 72;
        public static final int GHOST_HEIGHT_DEFAULT = 32;
        public static final int SLIME_WIDTH_DEFAULT = 72;
        public static final int SLIME_HEIGHT_DEFAULT = 32;


        public static final int CRABBY_WIDTH = (int)(CRABBY_WIDTH_DEFAULT*Game.SCALE);
        public static final int CRABBY_HEIGHT = (int)(CRABBY_HEIGHT_DEFAULT*Game.SCALE);
        public static final int GHOST_WIDTH = (int)(GHOST_WIDTH_DEFAULT*Game.SCALE);
        public static final int GHOST_HEIGHT = (int)(GHOST_HEIGHT_DEFAULT*Game.SCALE);
        public static final int SLIME_WIDTH = (int)(SLIME_WIDTH_DEFAULT*Game.SCALE);
        public static final int SLIME_HEIGHT = (int)(SLIME_HEIGHT_DEFAULT*Game.SCALE);


        public static final int CRABBY_DRAWOFFSETX_X = (int)(26 * Game.SCALE);
        public static final int CRABBY_DRAWOFFSETX_Y = (int)(9 * Game.SCALE);
        public static final int GHOST_DRAWOFFSETX_X = (int)(26 * Game.SCALE);
        public static final int GHOST_DRAWOFFSETX_Y = (int)(9 * Game.SCALE);
        public static final int SLIME_DRAWOFFSETX_X = (int)(26 * Game.SCALE);
        public static final int SLIME_DRAWOFFSETX_Y = (int)(9 * Game.SCALE);



        public static int GetSpriteAmount(int enemyType, int enemyState) {
            switch (enemyType) {
                case CRABBY:
                    switch (enemyState) {
                        case IDLE:
                            return 9;
                        case RUNNING:
                            return 6;
                        case ATTACK:
                            return 7;
                        case HIT:
                            return 4;
                        case DEAD:
                            return 5;
                    }
            }
            return 0;
        }

        public static int GetMaxHealth(int enemyType) {
            switch (enemyType) {
                case CRABBY:
                    return 10;
                case GHOST:
                    return 150;
                default:
                    return 0;
            }
        }

        public static int GetEnemyDmg(int enemyType) {
            switch (enemyType) {
                case CRABBY:
                    return 20;
                case GHOST:
                    return 100;
                default:
                    return 0;
            }
        }
    }

    public static class Map {
        public static final int SPRITES_IN_SHEET = 20;
        public static final int COLUMNS = 4;
        public static final int ROWS = 5;
    }

    public static class Entities {
        public static final float PLAYER_SPEED = 1.2f * Game.SCALE;
        public static final float GRAVITY = 0.04f * Game.SCALE;
    }

    public static class Animations {
        public static final int DEFAULT_SPEED = 25; // čím menší, tím bude rychlejší animace
        public static final int MOVING_SPEED = 13; // čím menší, tím bude rychlejší animace
    }

    public static class Buttons {

        public static int B_WIDTH_DEFAULT = 140;
        public static int B_HEIGHT_DEFAULT = 56;
        public static int B_WIDTH = (int) (B_WIDTH_DEFAULT * Game.SCALE);
        public static int B_HEIGHT = (int) (B_HEIGHT_DEFAULT * Game.SCALE);

        public static int URM_SIZE_DEFAULT = 56;
        public static int URM_SIZE = (int) (URM_SIZE_DEFAULT * Game.SCALE);

        public static int SOUND_SIZE_DEFAULT = 42;
        public static int SOUND_SIZE = (int) (SOUND_SIZE_DEFAULT * Game.SCALE);
    }

    public static class Background {

        public static final int BIG_CLOUD_WIDTH_DEFAULT = 448;
        public static final int BIG_CLOUD_HEIGHT_DEFAULT = 101;

        public static final int SMALL_CLOUD_WIDTH_DEFAULT = 74;
        public static final int SMALL_CLOUD_HEIGHT_DEFAULT = 24;

        public static final int BIG_CLOUD_WIDTH = (int) (BIG_CLOUD_WIDTH_DEFAULT * Game.SCALE);
        public static final int BIG_CLOUD_HEIGHT = (int) (BIG_CLOUD_HEIGHT_DEFAULT * Game.SCALE);

        public static final int SMALL_CLOUD_WIDTH = (int) (SMALL_CLOUD_WIDTH_DEFAULT * Game.SCALE);
        public static final int SMALL_CLOUD_HEIGHT = (int) (SMALL_CLOUD_HEIGHT_DEFAULT * Game.SCALE);
    }

    public static class Directions {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }
}

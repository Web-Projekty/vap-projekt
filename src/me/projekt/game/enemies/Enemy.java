package me.projekt.game.enemies;

import me.projekt.game.gamestates.Playing;
import me.projekt.game.enemies.Entity;
import me.projekt.game.main.Game;
import me.projekt.game.levels.Level;

import static me.projekt.game.utils.Constants.EnemyConstants.*;
import static me.projekt.game.utils.Utils.*;
import static me.projekt.game.utils.Constants.Directions.*;


public abstract class Enemy extends Entity {
    protected int aniIndex, enemyState, enemyType;
    protected int aniTick, aniSpeed = 25;
    protected boolean firstUpdate = true;
    protected boolean inAir;
    protected float fallSpeed;
    protected float gravity = 0.04f * Game.SCALE;
    protected float walkSpeed = 0.35f * Game.SCALE;
    protected int walkDir = LEFT;

    public Enemy(Playing playing, float x, float y, int width, int height, int enemyType) {
        super(playing, x, y, width, height);
        this.enemyType = enemyType;
        initHitbox(width, height);
    }

    protected void updateAnimationTick() {
        aniTick ++;
        if(aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= GetSpriteAmount(enemyType, enemyState)) {
                aniIndex = 0;
            }
        }
    }


    protected void changeWalkDir() {
        if(walkDir == LEFT)
            walkDir = RIGHT;
        else
            walkDir = LEFT;
    }

    public int getAniIndex() {
        return  aniIndex;
    }
    public int getEnemyState() {
        return enemyState;
    }
}

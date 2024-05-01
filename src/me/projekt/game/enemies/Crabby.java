package me.projekt.game.enemies;

import me.projekt.game.gamestates.Playing;
import me.projekt.game.main.Game;

import static me.projekt.game.utils.Constants.Directions.LEFT;
import static me.projekt.game.utils.Constants.EnemyConstants.*;
import static me.projekt.game.utils.Utils.*;

public class Crabby extends Enemy {

    public Crabby(float x, float y) {
        super(null, x, y, CRABBY_WIDTH, CRABBY_HEIGHT, CRABBY);
        initHitbox((int)(22* Game.SCALE), (int) (19*Game.SCALE));
    }

    public void update(int[][] lvlData) {
        updateMove(lvlData);
        updateAnimationTick();
    }

    private void updateMove(int[][] lvlData) {
        if (firstUpdate) {
            if (!isOnFloor(hitbox, lvlData))
                inAir = true;
            firstUpdate = false;
        }

        if (inAir) {
            if (canMoveTo(hitbox.x, hitbox.y + fallSpeed, hitbox.width, hitbox.height, lvlData)) {
                hitbox.y += fallSpeed;
                fallSpeed += gravity;
            } else {
                inAir = false;
                hitbox.y = getYUndRoofOrAboFloor(hitbox, fallSpeed);
            }
        } else {
            switch (enemyState) {
                case IDLE:
                    enemyState = RUNNING;
                    break;
                case RUNNING:
                    float xSpeed = 0;

                    if (walkDir == LEFT)
                        xSpeed = -walkSpeed;
                    else
                        xSpeed = walkSpeed;

                    if (canMoveTo(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData))
                        if (isFloor(hitbox, xSpeed, lvlData)) {
                            hitbox.x += xSpeed;
                            return;
                        }

                    changeWalkDir();

                    break;
            }
        }

    }
}

package me.projekt.game.enemies;

import me.projekt.game.gamestates.Playing;
import me.projekt.game.main.Game;
import me.projekt.game.player.Player;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static me.projekt.game.utils.Constants.Directions.LEFT;
import static me.projekt.game.utils.Constants.Directions.RIGHT;
import static me.projekt.game.utils.Constants.EnemyConstants.*;
import static me.projekt.game.utils.Utils.*;

public class Crabby extends Enemy {

    //AttackBox
    private Rectangle2D.Float attackBox;
    private int attackBoxOffsetX;

    public Crabby(float x, float y) {
        super(null, x, y, CRABBY_WIDTH, CRABBY_HEIGHT, CRABBY);
        initHitbox(x, y, (int)(22 * Game.SCALE), (int)(17 * Game.SCALE)); // Správně by mělo být 22 a 17 ale hitbox se jinak špatně načte. A hitbox je spatne udelělán je nutná oprava.
        initAttackBox();
    }

    private void initAttackBox() {
        attackBox = new Rectangle2D.Float(x,(int)(y+CRABBY_DRAWOFFSETX_Y),(int)(74 * Game.SCALE), (int)(17 * Game.SCALE));
        attackBoxOffsetX = (int)(Game.SCALE * 30);
    }

    public void update(int[][] lvlData, Player player) {
        updateBehaviour(lvlData, player);
        updateAnimationTick();
        updateAttackBox();
    }

    private void updateAttackBox() {
        attackBox.x = hitbox.x - attackBoxOffsetX;
        attackBox.y = hitbox.y;
    }

    private void updateBehaviour(int[][] lvlData, Player player) {
        if (firstUpdate) {
            firstUpdateCheck(lvlData);
        }

        if (inAir) {
            updateInAir(lvlData);
        } else {
            switch (enemyState) {
                case IDLE:
                    newState(RUNNING);
                    break;
                case RUNNING:
                    if (canSeePlayer(lvlData, player))
                        turnTowardsPlayer(player);
                    if(isPlayerCloseForAttack(player))
                        newState(ATTACK);

                    move(lvlData);
                    break;
                case ATTACK:
                    if(aniIndex == 0)
                        attackChecked = false;
                    if(aniIndex == 3 && !attackChecked)
                        checkPlayerHit(attackBox, player);
                    break;
                case HIT:
                    break;
                case DEAD:
                    break;
            }
        }

    }

    public void drawAttackBox(Graphics g, int xLvlOffset, int yLvlOffset) {
        g.setColor(Color.RED);
        g.drawRect((int)(attackBox.x - xLvlOffset), (int)(attackBox.y - yLvlOffset + CRABBY_DRAWOFFSETX_Y), (int)attackBox.width, (int)attackBox.height);
    }

    public int flipX() {
        if (walkDir == RIGHT)
            return width;
        else
            return 0;
    }
    public int flipW() {
        if(walkDir == RIGHT)
            return -1;
        else
            return 1;
    }
}

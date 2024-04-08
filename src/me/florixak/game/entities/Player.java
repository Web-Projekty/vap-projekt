package me.florixak.game.entities;

import me.florixak.game.main.Game;
import me.florixak.game.utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static me.florixak.game.utils.Constants.PlayerConstants.*;
import static me.florixak.game.utils.HelpMethods.*;

public class Player extends Entity {

    // Animations
    private BufferedImage[][] animations;
    private int animTick, animIndex, animSpeed = 25; // čím menší, tím bude rychlejší animace
    private int playerAction = IDLE;

    // Moving
    private boolean moving = false, attacking = false;
    private boolean left, up, right, down, jump;
    private final float playerSpeed = 2.0f;
    private int[][] lvlData;
    private float xDrawOffset = 21 * Game.SCALE;
    private float yDrawOffset = 4 * Game.SCALE;

    // Jumping / Gravity
    private float airSpeed = 0f;
    private float gravity = 0.04f * Game.SCALE;
    private float jumpSpeed = -2.25f * Game.SCALE;
    private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
    private boolean inAir = false;

    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimations();
        initHitbox(x, y, 20 * Game.SCALE, 27 * Game.SCALE);
    }

    public void update() {
        updatePosition();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g) {
        g.drawImage(animations[playerAction][animIndex], (int) (hitbox.x - xDrawOffset), (int) (hitbox.y - yDrawOffset), width, height, null);
//        drawHitbox(g);
    }

    private void updateAnimationTick() {
        animTick++;
        if (animTick >= animSpeed) {
            animTick = 0;
            animIndex++;
            if (animIndex >= getSpriteAmount(playerAction)) {
                animIndex = 0;
                attacking = false;
            }
        }
    }

    private void setAnimation() {

        int startAnim = playerAction;

        if (moving) playerAction = RUNNING;
        else playerAction = IDLE;

        if (inAir) {
            if (airSpeed < 0) {
                playerAction = JUMP;
            } else {
                playerAction = FALLING;
            }
        }

        if (attacking) playerAction = ATTACK_1;

        if (startAnim != playerAction)
            // pokud se animace změnila, tak ji resetujeme, aby se mohla přehrát nová
            resetAnimTick();
    }

    private void resetAnimTick() {
        animTick = 0;
        animIndex = 0;
    }

    private void updatePosition() {
        moving = false;
        if (jump) {
            jump();
        }
        if (!left && !right && !inAir) {
            return;
        }

        float xSpeed = 0;

        if (left) xSpeed -= playerSpeed;
        if (right) xSpeed += playerSpeed;

        if (!inAir) {
            if (!isEntityOnFloor(hitbox, lvlData)) {
                inAir = true;
            }
        }

        if (inAir) {
            if (canMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvlData)) {
                hitbox.y += airSpeed;
                airSpeed += gravity;
                updateXPos(xSpeed);
            } else {
                hitbox.y = getEntityYPosUnderRoofOrAboveFloor(hitbox, airSpeed);
                if (airSpeed > 0) {
                    resetInAir();
                } else {
                    airSpeed = fallSpeedAfterCollision;
                }
                updateXPos(xSpeed);
            }
        } else {
            updateXPos(xSpeed);
        }
        moving = true;
    }

    private void jump() {
        if (inAir) {
            return;
        }
        inAir = true;
        airSpeed = jumpSpeed;
    }

    private void resetInAir() {
        inAir = false;
        airSpeed = 0;
    }

    private void updateXPos(float xSpeed) {
        if (canMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData)) {
            hitbox.x += xSpeed;
        } else {
            hitbox.x = getEntityXPosNextToWall(hitbox, xSpeed);
        }
    }

    private void loadAnimations() {
        BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.PLAYER_ATLAS);

        animations = new BufferedImage[9][6];
        for (int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = img.getSubimage(i * 64, j * 40, 64, 40);
            }
        }
    }

    public void loadLevelData(int[][] lvlData) {
        this.lvlData = lvlData;
        if (!isEntityOnFloor(hitbox, lvlData)) {
            inAir = true;
        }
    }

    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }
}

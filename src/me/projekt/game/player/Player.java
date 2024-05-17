package me.projekt.game.player;

import me.projekt.game.enemies.Entity;
import me.projekt.game.gamestates.Playing;
import me.projekt.game.main.Game;
import me.projekt.game.utils.Constants;
import me.projekt.game.utils.LoadSave;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import static me.projekt.game.player.PlayerAction.*;
import static me.projekt.game.utils.Constants.Entities.*;
import static me.projekt.game.utils.Constants.Animations.*;
import static me.projekt.game.utils.Utils.*;

public class Player extends Entity {

    // Animations
    private BufferedImage[][] animations;

    // Moving
    private boolean moving = false, attacking = false;
    private boolean left, right, jump;
    private int[][] lvlData;
    private float xDrawOffset = 18 * Game.SCALE; // umístění borderu na spritu hráče na ose x
    private float yDrawOffset = 4 * Game.SCALE; // umístění borderu na spritu hráče na ose y
    private int flipX = 0, flipW = 1;

    // Jumping / Gravity
    private float jumpSpeed = -3f * Game.SCALE; // rychlost skoku
    private float fallSpeedAfterCollision = 0.5f * Game.SCALE; // rychlost pádu po dotyku kolize

    //StatusBarUI
    private BufferedImage statusBarImg;

    private int statusBarWidth = (int)(192 * Game.SCALE);
    private int statusBarHeight = (int)(58 * Game.SCALE);
    private int statusBarX = (int)(10 * Game.SCALE);
    private int statusBarY = (int)(10 * Game.SCALE);

    private int healthBarWidth = (int)(150 * Game.SCALE);
    private int healthBarHeight = (int)(4 * Game.SCALE);
    private int healtBarXStart = (int)(34 * Game.SCALE);
    private int healtBarYStart = (int)(14 * Game.SCALE);

    private int maxHealth = 100;
    private int currentHealth = maxHealth;
    private int healthWidth = healthBarWidth;

    //AttackBox
    private Rectangle2D.Float attackBox;
    private boolean attackChecked = false;

    public Player(Playing playing, float x, float y, int width, int height) {
        super(playing, x, y, width, height);

        this.action = IDLE;
        this.moveSpeed = PLAYER_SPEED;
        this.maxHealth = 100;

        loadAnimations();
        initHitbox(x, y, 12, 27);
        initAttackBox();
    }

    private void initAttackBox() {
        attackBox = new Rectangle2D.Float(x,y,(int)(20 * Game.SCALE),(int)(20 * Game.SCALE));
    }

    public void setSpawn(Point spawn) {
        this.x = spawn.x;
        this.y = spawn.y;
        hitbox.x = x;
        hitbox.y = y;
    }

    public void update() {
        updateHealthBar();
        updateAttackBox();
        updatePosition();
        if (moving)
            checkObjectTouched();
        if (attacking)
            checkAttack();
        updateAnimationTick();
        setAnimation();
    }

    private void checkAttack() {
        if(attackChecked || animIndex != 1)
            return;
        attacking = true;
        playing.checkEnemyHit(attackBox);
    }

    private void updateAttackBox() {
        if (right) {
            attackBox.x = hitbox.x + hitbox.width + (int)(Game.SCALE * 10);
        }
        else if(left) {
            attackBox.x = hitbox.x - (int)(hitbox.width * 3) - (int)(Game.SCALE * 10);
        }
        attackBox.y = hitbox.y + (int)(Game.SCALE * 10);
    }

    private void updateHealthBar() {
        healthWidth = (int)((currentHealth / (float)maxHealth) * healthBarWidth);
    }

    public void render(Graphics g, int xLvlOffset, int yLvlOffset) {

        g.drawImage(animations[action.getOrder()][animIndex],
                (int) (hitbox.x - xDrawOffset) - xLvlOffset + flipX,
                (int) (hitbox.y - yDrawOffset) - yLvlOffset,
                width * flipW,
                height,
                null);
        drawHitbox(g, xLvlOffset, yLvlOffset);
        drawUI(g);
        drawAttackBox(g, xLvlOffset, yLvlOffset);
    }

    private void drawAttackBox(Graphics g, int xLvlOffset, int yLvlOffset) {
        g.setColor(Color.RED);
        g.drawRect((int)(attackBox.x - xLvlOffset), (int)(attackBox.y - yLvlOffset), (int)attackBox.width, (int)attackBox.height);
    }

    private void drawUI(Graphics g) {
        g.drawImage(statusBarImg, statusBarX, statusBarY, statusBarWidth, statusBarHeight, null);
        g.setColor(Color.RED);
        g.fillRect(healtBarXStart + statusBarX, healtBarYStart + statusBarY, healthWidth, healthBarHeight);

    }

    private void checkObjectTouched() {
        playing.getObjectManager().checkObjectTouched(hitbox);
    }

    private void updateAnimationTick() {
        int animationSpeed = moving ? MOVING_SPEED : DEFAULT_SPEED;

        animTick++;
        if (animTick >= animationSpeed) {
            animTick = 0;
            animIndex++;
            if (animIndex >= action.getSpriteAmount()) {
                animIndex = 0;
                attacking = false;
            }
        }
    }

    private void setAnimation() {

        PlayerAction startAnim = action;

        if (moving) action = RUNNING;
        else action = IDLE;

        if (inAir) {
            if (airSpeed < 0) action = JUMP;
            else action = FALLING;
        }

        if (attacking) action = ATTACK;

        if (startAnim != action)
            // pokud se animace změnila, tak ji resetujeme, aby se mohla přehrát nová
            resetAnimTick();
    }

    private void resetAnimTick() {
        animTick = 0;
        animIndex = 0;
    }

    private void updatePosition() {
        moving = false;
        if (jump) jump();

        if (!inAir) if ((!left && !right) || (right && left)) return;

        float xSpeed = 0;

        if (left) {
            xSpeed -= moveSpeed;
            flipX = width;
            flipW = -1;
        }
        if (right) {
            xSpeed += moveSpeed;
            flipX = 0;
            flipW = 1;
        }

        if (!inAir)
            if (!isOnFloor(hitbox, lvlData))
                inAir = true;

        if (inAir) {
            if (canMoveTo(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvlData)) {
                hitbox.y += airSpeed;
                airSpeed += Constants.Entities.GRAVITY;
                updateXPos(xSpeed);
            } else {
                hitbox.y = getYUndRoofOrAboFloor(hitbox, airSpeed);
                if (airSpeed > 0) resetInAir();
                else airSpeed = fallSpeedAfterCollision;

                updateXPos(xSpeed);
            }
        } else
            updateXPos(xSpeed);
        moving = true;
    }

    private void jump() {
        if (inAir) return;

        inAir = true;
        airSpeed = jumpSpeed;
    }

    private void resetInAir() {
        inAir = false;
        airSpeed = 0;
    }

    private void updateXPos(float xSpeed) {
        if (canMoveTo(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData))
            hitbox.x += xSpeed;
        else
            hitbox.x = getXNextToWall(hitbox, xSpeed);

    }

    public void changeHealth(int value) {
        currentHealth += value;
        if(currentHealth <= 0) {
            currentHealth = 0;
            //GameOver()
        }
        else if( currentHealth >= maxHealth) {
            currentHealth = maxHealth;
        }
    }

    private void loadAnimations() {
        BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.PLAYER_ATLAS);

        animations = new BufferedImage[6][6];
        for (int j = 0; j < animations.length; j++)
            for (int i = 0; i < animations[j].length; i++)
                animations[j][i] = img.getSubimage(i * 48, j * 32, 48, 32);

        statusBarImg  = LoadSave.getSpriteAtlas(LoadSave.STATUS_BAR);
    }

    public void loadLevelData(int[][] lvlData) {
        this.lvlData = lvlData;
        if (!isOnFloor(hitbox, lvlData)) {
            inAir = true;
        }
    }

    public void cancelMovement() {
        left = false;
        right = false;
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

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }

    public void reset() {
        cancelMovement();
        inAir = false;
        attacking = false;
        moving = false;
        action = IDLE;

        hitbox.x = x;
        hitbox.y = y;

        if (!isOnFloor(hitbox, lvlData)) inAir = true;
    }
}
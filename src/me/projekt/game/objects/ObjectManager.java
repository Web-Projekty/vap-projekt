package me.projekt.game.objects;

import me.projekt.game.gamestates.Playing;
import me.projekt.game.levels.Level;
import me.projekt.game.objects.destroyable.GameContainer;
import me.projekt.game.objects.pickable.Potion;
import me.projekt.game.utils.LoadSave;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ObjectManager {

    private Playing playing;
    private BufferedImage[][] potionImg, soulImg, containerImg;
    private ArrayList<Potion> potions;
    private ArrayList<GameContainer> containers;

    public ObjectManager(Playing playing) {
        this.playing = playing;

        loadImages();
    }

    private void loadImages() {
        BufferedImage potionSprite = LoadSave.getSpriteAtlas(LoadSave.POTION_ATLAS);
        potionImg = new BufferedImage[2][7];

        for (int j = 0; j < potionImg.length; j++) {
            for (int i = 0; i < potionImg[j].length; i++) {
                potionImg[j][i] = potionSprite.getSubimage(12 * i, 16 * j, 12, 16);
            }
        }

        BufferedImage containerSprite = LoadSave.getSpriteAtlas(LoadSave.CONTAINER_ATLAS);
        containerImg = new BufferedImage[2][8];

        for (int j = 0; j < containerImg.length; j++) {
            for (int i = 0; i < containerImg[j].length; i++) {
                containerImg[j][i] = containerSprite.getSubimage(40 * i, 30 * j, 40, 30);
            }
        }

        BufferedImage soulSprite = LoadSave.getSpriteAtlas(LoadSave.SOUL_ATLAS);
        soulImg = new BufferedImage[1][4];

        for (int j = 0; j < soulImg.length; j++) {
            for (int i = 0; i < soulImg[j].length; i++) {
                soulImg[j][i] = soulSprite.getSubimage(40 * i, 30 * j, 40, 30);
            }
        }


    }

    public void checkObjectTouched(Rectangle2D.Float hitbox) {
        for (Potion p : potions) {
            if (p.isActive()) {
                if (hitbox.intersects(p.getHitbox())) {
                    p.setActive(false);
                    applyEffectToPlayer(p);
                }
            }
        }
        for (GameContainer gc : containers) {
            if (gc.isActive()) {
                if (gc.getHitbox().intersects(hitbox)) {
                    gc.setAnimation(true);
                    potions.add(new Potion((int) (gc.getHitbox().x + gc.getHitbox().width / 2), (int) (gc.getHitbox().y), ObjectType.RED_POTION));
                    return;
                }
            }
        }
    }

    public void applyEffectToPlayer(Potion potion) {
        if (potion.getObject() == ObjectType.RED_POTION) {
            // TODO add player health
        } else if (potion.getObject() == ObjectType.BLUE_POTION) {
            // TODO idk, for example power, speed...
        }
    }

    public void checkObjectHit(Rectangle2D.Float attackBox) {
        for (GameContainer gc : containers) {
            if (gc.isActive()) {
                if (gc.getHitbox().intersects(attackBox)) {
                    gc.setAnimation(true);
                    if (gc.getObject() == ObjectType.BARREL) {
                        potions.add(new Potion((int) (gc.getHitbox().x + gc.getHitbox().width / 2), (int) (gc.getHitbox().y + gc.getHitbox().height / 4), ObjectType.RED_POTION));
                        return;
                    }
                }
            }
        }
    }

    public void loadObjects(Level newLevel) {
        potions = newLevel.getPotions();
        containers = newLevel.getContainers();
    }

    public void update() {
        for (Potion p : potions) {
            if (p.isActive()) {
                p.update();
            }
        }
        for (GameContainer gc : containers) {
            if (gc.isActive()) {
                gc.update();
            }
        }
    }

    public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {
        drawPotions(g, xLvlOffset, yLvlOffset);
        drawContainers(g, xLvlOffset, yLvlOffset);
    }

    private void drawPotions(Graphics g, int xLvlOffset, int yLvlOffset) {
        for (Potion p : potions) {
            if (p.isActive()) {
                int type = p.getObject() == ObjectType.BLUE_POTION ? 0 : 1;

                g.drawImage(potionImg[type][p.getAnimIndex()],
                        (int) (p.getHitbox().x - p.getXDrawOffset() - xLvlOffset),
                        (int) (p.getHitbox().y - p.getYDrawOffset() - yLvlOffset),
                        p.getObject().getWidth(),
                        p.getObject().getHeight(),
                        null);

                //p.drawHitbox(g, xLvlOffset, yLvlOffset);
            }
        }
    }

    private void drawContainers(Graphics g, int xLvlOffset, int yLvlOffset) {
        for (GameContainer gc : containers) {
            if (gc.isActive()) {
                int type = gc.getObject() == ObjectType.BOX ? 0 : 1;

                g.drawImage(containerImg[type][gc.getAnimIndex()],
                        (int) (gc.getHitbox().x - gc.getXDrawOffset() - xLvlOffset),
                        (int) (gc.getHitbox().y - gc.getYDrawOffset() - yLvlOffset),
                        gc.getObject().getWidth(),
                        gc.getObject().getHeight(),
                        null);

                //gc.drawHitbox(g, xLvlOffset, yLvlOffset);
            }
        }
    }

    public void reset() {
        for (Potion p : potions) {
            p.reset();
        }
        for (GameContainer gc : containers) {
            gc.reset();
        }
    }
}

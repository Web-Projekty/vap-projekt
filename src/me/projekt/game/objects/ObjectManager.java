package me.projekt.game.objects;

import me.projekt.game.gamestates.Playing;
import me.projekt.game.levels.Level;
import me.projekt.game.objects.destroyable.Box;
import me.projekt.game.objects.pickable.PickableGameObject;
import me.projekt.game.objects.pickable.Potion;
import me.projekt.game.objects.pickable.Soul;
import me.projekt.game.utils.LoadSave;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ObjectManager {

    private Playing playing;
    private BufferedImage[][] potionImg, soulImg, containerImg, decorationsImg, levelDoorImg, deathZoneImg;

    private ArrayList<Potion> potions;
    private ArrayList<Soul> souls;
    private ArrayList<Box> boxes;
    private ArrayList<GameObject> decorations;
    private ArrayList<LevelDoor> levelDoors;
    private ArrayList<DeathZone> deathZones;

    private boolean inDoor;

    public ObjectManager(Playing playing) {
        this.playing = playing;

        loadImages();
    }

    public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {
        drawPotions(g, xLvlOffset, yLvlOffset);
        drawContainers(g, xLvlOffset, yLvlOffset);
        drawSouls(g, xLvlOffset, yLvlOffset);
        drawDecorations(g, xLvlOffset, yLvlOffset);
        drawDeathZones(g, xLvlOffset, yLvlOffset);
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

    private void drawSouls(Graphics g, int xLvlOffset, int yLvlOffset) {
        for (Soul soul : souls) {
            if (soul.isActive()) {
                g.drawImage(soulImg[0][soul.getAnimIndex()],
                        (int) (soul.getHitbox().x - soul.getXDrawOffset() - xLvlOffset),
                        (int) (soul.getHitbox().y - soul.getYDrawOffset() - yLvlOffset),
                        soul.getObject().getWidth(),
                        soul.getObject().getHeight(),
                        null);

                //p.drawHitbox(g, xLvlOffset, yLvlOffset);
            }
        }
    }

    private void drawContainers(Graphics g, int xLvlOffset, int yLvlOffset) {
        for (Box gc : boxes) {
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

    private void drawDecorations(Graphics g, int xLvlOffset, int yLvlOffset) {
        for (GameObject decoration : decorations) {
            if (decoration.isActive()) {

                g.drawImage(decorationsImg[6][decoration.getAnimIndex()],
                        (int) (decoration.getHitbox().x - decoration.getXDrawOffset() - xLvlOffset),
                        (int) (decoration.getHitbox().y - decoration.getYDrawOffset() - yLvlOffset),
                        decoration.getObject().getWidth(),
                        decoration.getObject().getHeight(),
                        null);

                //gc.drawHitbox(g, xLvlOffset, yLvlOffset);
            }
        }
    }

    public void drawDoors(Graphics g, int xLvlOffset, int yLvlOffset) {
        for (LevelDoor levelDoor : levelDoors) {
            if (levelDoor.isActive()) {

                g.drawImage(levelDoorImg[0][levelDoor.getAnimIndex()],
                        (int) (levelDoor.getHitbox().x - levelDoor.getXDrawOffset() - xLvlOffset),
                        (int) (levelDoor.getHitbox().y - levelDoor.getYDrawOffset() - yLvlOffset),
                        levelDoor.getObject().getWidth(),
                        levelDoor.getObject().getHeight(),
                        null);

                //gc.drawHitbox(g, xLvlOffset, yLvlOffset);
            }
        }
    }

    public void drawDeathZones(Graphics g, int xLvlOffset, int yLvlOffset) {
        for (DeathZone deathZone : deathZones) {
            if (deathZone.isActive()) {

                g.drawImage(deathZoneImg[0][deathZone.getAnimIndex()],
                        (int) (deathZone.getHitbox().x - deathZone.getXDrawOffset() - xLvlOffset),
                        (int) (deathZone.getHitbox().y - deathZone.getYDrawOffset() - yLvlOffset),
                        deathZone.getObject().getWidth(),
                        deathZone.getObject().getHeight(),
                        null);

                //gc.drawHitbox(g, xLvlOffset, yLvlOffset);
            }
        }
    }

    public void update() {
        for (Potion p : potions) {
            if (p.isActive()) {
                p.update();
            }
        }
        for (Box gc : boxes) {
            if (gc.isActive()) {
                gc.update();
            }
        }
        for (Soul soul : souls) {
            if (soul.isActive()) {
                soul.update();
            }
        }
        for (LevelDoor levelDoor : levelDoors) {
            if (levelDoor.isActive()) {
                levelDoor.update();
            }
        }
        for (GameObject decoration : decorations) {
            if (decoration.isActive()) {
                decoration.update();
            }
        }
        for (DeathZone deathZone : deathZones) {
            if (deathZone.isActive()) {
                deathZone.update();
            }
        }

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
                soulImg[j][i] = soulSprite.getSubimage(32 * i, 32 * j, 32, 32);
            }
        }

        BufferedImage decorationsSprite = LoadSave.getSpriteAtlas(LoadSave.DECORATIONS);
        decorationsImg = new BufferedImage[7][4];

        for (int j = 0; j < decorationsImg.length; j++) {
            for (int i = 0; i < decorationsImg[j].length; i++) {
                decorationsImg[j][i] = decorationsSprite.getSubimage(32 * i, 32 * j, 32, 32);
            }
        }

        BufferedImage levelDoorSprite = LoadSave.getSpriteAtlas(LoadSave.DOOR);
        levelDoorImg = new BufferedImage[1][2];

        for (int j = 0; j < levelDoorImg.length; j++) {
            for (int i = 0; i < levelDoorImg[j].length; i++) {
                levelDoorImg[j][i] = levelDoorSprite.getSubimage(32 * i, 32 * j, 32, 32);
            }
        }

        BufferedImage deathZoneSprite = LoadSave.getSpriteAtlas(LoadSave.SPIKES);
        deathZoneImg = new BufferedImage[1][1];

        for (int j = 0; j < deathZoneImg.length; j++) {
            for (int i = 0; i < deathZoneImg[j].length; i++) {
                deathZoneImg[j][i] = deathZoneSprite.getSubimage(32 * i, 32 * j, 32, 32);
            }
        }
    }
    public void loadObjects(Level newLevel) {
        potions = newLevel.getPotions();
        boxes = newLevel.getContainers();
        souls = newLevel.getSouls();
        decorations = newLevel.getDecorations();
        levelDoors = newLevel.getLevelDoors();
        deathZones = newLevel.getDeathZones();
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
        for (Soul soul : souls) {
            if (soul.isActive()) {
                if (hitbox.intersects(soul.getHitbox())) {
                    soul.setActive(false);
                    applyEffectToPlayer(soul);
                }
            }
        }
        for (Box gc : boxes) {
            if (gc.isActive()) {
                if (gc.getHitbox().intersects(hitbox)) {
                    gc.setAnimation(true);
                    potions.add(new Potion((int) (gc.getHitbox().x + gc.getHitbox().width / 2), (int) (gc.getHitbox().y), ObjectType.RED_POTION));
                    return;
                }
            }
        }
        for (LevelDoor levelDoor : levelDoors) {
            if (levelDoor.isActive()) {
                if (levelDoor.getHitbox().intersects(hitbox)) {
                    if (playing.getLevelManager().getCurrentLevel().getPickedSouls() >= playing.getLevelManager().getCurrentLevel().getNeededSouls()) {
                        setInDoor(true);
                        if (isInDoor()) {
                            System.out.println("Door is now opened!");
                            levelDoor.setAnimIndex(1);
                        }
                    } else {
                        System.out.println("You need to pick up more souls!");
                    }
                    return;
                } else {
                    setInDoor(false);
                }
            }
        }
        for (DeathZone dz : deathZones) {
            if (dz.isActive()) {
                if (dz.getHitbox().intersects(hitbox)) {
                    playing.reset();
                    return;
                }
            }
        }
    }

    public void applyEffectToPlayer(PickableGameObject pgo) {
        if (pgo.getObject() == ObjectType.RED_POTION) {
            // TODO playing.getPlayer().setHealth(playing.getPlayer().getHealth() + pgo.getObject().getValue());
        } else if (pgo.getObject() == ObjectType.BLUE_POTION) {
            // TODO idk, for example power, speed...
        } else if (pgo.getObject() == ObjectType.SOUL) {
            playing.getLevelManager().getCurrentLevel().pickSoul();
            System.out.println(playing.getLevelManager().getCurrentLevel().getPickedSouls() + " / " + playing.getLevelManager().getCurrentLevel().getNeededSouls());
        }
    }

    public void checkObjectHit(Rectangle2D.Float attackBox) {
        for (Box gc : boxes) {
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

    public void reset() {
        for (Potion p : potions) {
            p.reset();
        }
        for (Box b : boxes) {
            b.reset();
        }
        for (Soul soul : souls) {
            soul.reset();
        }
        for (GameObject decoration : decorations) {
            decoration.reset();
        }
        for (LevelDoor levelDoor : levelDoors) {
            levelDoor.reset();
        }
        setInDoor(false);
    }

    public void setInDoor(boolean inDoor) {
        this.inDoor = inDoor;
    }

    public boolean isInDoor() {
        return inDoor;
    }
}

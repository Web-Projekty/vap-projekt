package me.projekt.game.enemies;

import me.projekt.game.gamestates.Playing;
import me.projekt.game.levels.Level;
import me.projekt.game.player.Player;
import me.projekt.game.utils.LoadSave;
import me.projekt.game.enemies.Entity.*;

import static me.projekt.game.utils.Constants.EnemyConstants.*;


import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class EnemyManager {

    private Playing playing;
    private BufferedImage[][] crabbyArr;
    private ArrayList<Crabby> crabbies;


    public EnemyManager(Playing playing) {
        this.playing = playing;
        loadEnemyImgs();
    }

    public void loadEnemies(Level newLevel) {
        crabbies = newLevel.getCrabbies();
    }

    public void update(int[][] lvlData, Player player) {
        for (Crabby c : crabbies)
            if (c.isActive())
                c.update(lvlData, player);
    }

    public void draw(Graphics g, int xLvlOffset, int yLvlOffset) { drawCrabs(g, xLvlOffset, yLvlOffset); }

    private void drawCrabs(Graphics g, int xLvlOffset, int yLvlOffset) {
        for (Crabby c : crabbies)
            if (c.isActive()) {
                g.drawImage(crabbyArr[c.getEnemyState()][c.getAniIndex()],
                        (int) (c.getHitbox().x - xLvlOffset - CRABBY_DRAWOFFSETX_X + c.flipX()),
                        (int) (c.getHitbox().y - yLvlOffset), CRABBY_WIDTH * c.flipW(), CRABBY_HEIGHT, null);
                c.drawHitbox(g, xLvlOffset, yLvlOffset);
                c.drawAttackBox(g, xLvlOffset, yLvlOffset);
            }
    }

    private void loadEnemyImgs() {
        crabbyArr = new BufferedImage[5][9];
        BufferedImage temp = LoadSave.getSpriteAtlas(LoadSave.CRABBY_SPRITE);
        for (int j = 0; j < crabbyArr.length; j++)
            for (int i = 0; i < crabbyArr[j].length; i++)
                crabbyArr[j][i] = temp.getSubimage(i * CRABBY_WIDTH_DEFAULT, j * CRABBY_HEIGHT_DEFAULT, CRABBY_WIDTH_DEFAULT, CRABBY_HEIGHT_DEFAULT);
    }

    public void checkEnemyHit(Rectangle2D.Float attackBox) {
        for(Crabby c : crabbies)
            if(c.isActive())
                if(attackBox.intersects(c.getHitbox())) {
                    c.hit(1);
                    return;
                }
    }

    /*public void loadEnemies(Level level) {
        crabbies = Utils.getCrabsFromImage(img);
    }*/
}

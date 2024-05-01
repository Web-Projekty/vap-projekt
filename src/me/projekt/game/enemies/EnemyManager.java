package me.projekt.game.enemies;

import me.projekt.game.gamestates.Playing;
import me.projekt.game.levels.Level;
import me.projekt.game.utils.LoadSave;

import static me.projekt.game.utils.Constants.EnemyConstants.*;


import java.awt.*;
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

    public void update(int[][] lvlData) {
        for (Crabby c : crabbies)
            c.update(lvlData);
    }

    public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {
        drawCrabs(g, xLvlOffset, yLvlOffset);
    }

    private void drawCrabs(Graphics g, int xLvlOffset, int yLvlOffset) {
        for (Crabby c : crabbies)
            g.drawImage(crabbyArr[c.getEnemyState()][c.getAniIndex()], (int)c.getHitbox().x-xLvlOffset,(int)c.getHitbox().y-yLvlOffset, CRABBY_WIDTH, CRABBY_HEIGHT, null);
    }

    private void loadEnemyImgs() {
        crabbyArr = new BufferedImage[5][9];
        BufferedImage temp = LoadSave.getSpriteAtlas(LoadSave.CRABBY_SPRITE);
        for(int j = 0; j<crabbyArr.length; j++)
            for(int i = 0; i<crabbyArr[j].length; i++)
                crabbyArr[j][i] = temp.getSubimage(i * CRABBY_WIDTH_DEFAULT, j * CRABBY_HEIGHT_DEFAULT, CRABBY_WIDTH_DEFAULT, CRABBY_HEIGHT_DEFAULT);
    }

    /*public void loadEnemies(Level level) {
        crabbies = Utils.getCrabsFromImage(img);
    }*/
}

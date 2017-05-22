package com.mygdx.game.enemy;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.object.GameObject;
import com.mygdx.game.player.Player;
import com.mygdx.game.utility.Utility;

import java.util.Iterator;

public class EnemyManager {

    private Array<BaseEnemy> elist;
    private int maxEnemies = 3;

    BitmapFont font = new BitmapFont();

    public EnemyManager(){
        this.elist = new Array();
        font.getData().scale(1.5f);
    }

    public void update(float delta, Player player){
        //Check to delete
        Iterator<BaseEnemy> it = this.elist.iterator();
        while(it.hasNext()){
            BaseEnemy tmp = it.next();
            if(tmp.toDestroy){
                it.remove();
            }
        }
        //Update
        for(BaseEnemy be : this.elist){
            be.update(delta, player);
        }

        if(this.elist.size < maxEnemies){
            spawnZombie();
            int rand = MathUtils.random(100);
            if(rand <= 10)
                maxEnemies += 1;
        }
    }

    public void draw(SpriteBatch batch){
        for(BaseEnemy be : this.elist){
            be.draw(batch);
        }
        //font.draw(batch, "Current: " + this.elist.size, Utility.GAME_WORLD_WIDTH/2, Utility.GAME_WORLD_HEIGHT*39/40);
    }

    public void addEnemy(BaseEnemy e){
        this.elist.add(e);
    }

    public void spawnZombie(){
        float x = MathUtils.random(0, Utility.GAME_WORLD_WIDTH);
        float y = MathUtils.random(0, Utility.GAME_WORLD_HEIGHT);
        this.addEnemy(new Zombie(x,y));
    }

    public Array<BaseEnemy> getlist(){
        return this.elist;
    }
}

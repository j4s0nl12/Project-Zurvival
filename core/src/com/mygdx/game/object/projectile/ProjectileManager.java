package com.mygdx.game.object.projectile;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.enemy.BaseEnemy;
import com.mygdx.game.utility.Utility;

import java.util.Iterator;

public class ProjectileManager {

    private Array<BaseProjectile> plist;

    BitmapFont font = new BitmapFont();

    public int killCt;
    public int bulletsHit;
    public int bulletsFired;
    public float accuracy;

    public ProjectileManager(){
        this.plist = new Array();
        this.killCt = 0;
        this.bulletsHit = 0;
        this.bulletsFired = 0;
        this.accuracy = 0f;
        font.getData().scale(1.5f);
    }

    public void update(float delta){
        //Check to delete
        Iterator<BaseProjectile> it = this.plist.iterator();
        while(it.hasNext()){
            BaseProjectile tmp = it.next();
            if(tmp.toDestroy){
                if(tmp.hitObjects.size > 0)
                    this.bulletsHit += 1;
                it.remove();
            }
        }
        //Update
        for(BaseProjectile bp : this.plist){
            bp.update(delta);
        }
        if(this.bulletsFired != 0)
            this.accuracy = (float)this.bulletsHit/(float)this.bulletsFired;
    }

    public void checkCollision(Array<BaseEnemy> oList){
        for(BaseProjectile bp : this.plist) {
            for (BaseEnemy o : oList) {
                if(bp.checkCollision(o)){
                    o.hp -= bp.dmg;
                    bp.addHitObject(o);
                    if(o.hp <= 0){
                        this.killCt += 1;
                    }
                    if(bp.pierceCt > 0)
                        bp.pierceCt--;
                    else if(!bp.pierceInf)
                        bp.toDestroy = true;
                }
            }
        }
    }

    public void draw(SpriteBatch batch){
        for(BaseProjectile bp : this.plist){
            bp.draw(batch);
        }
        font.draw(batch, "Killed: " + this.killCt, Utility.GAME_WORLD_WIDTH/2, Utility.GAME_WORLD_HEIGHT*9/10);
        font.draw(batch, "Acc: " + (int)(this.accuracy*100) + "%",
                  Utility.GAME_WORLD_WIDTH*4/5, Utility.GAME_WORLD_HEIGHT*19/20);
    }

    public void addProjectile(BaseProjectile p){
        this.plist.add(p);
        this.bulletsFired += 1;
    }

    public Array<BaseProjectile> getProjectileList(){
        return this.plist;
    }
}

package com.mygdx.game.object.projectile;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.compression.lzma.Base;
import com.mygdx.game.object.GameObject;

import java.util.Iterator;

public class ProjectileManager {

    private Array<BaseProjectile> plist;

    public ProjectileManager(){
        this.plist = new Array();
    }

    public void update(float delta){
        //Check to delete
        Iterator<BaseProjectile> it = this.plist.iterator();
        while(it.hasNext()){
            BaseProjectile tmp = it.next();
            if(tmp.toDestroy){
                it.remove();
            }
        }
        //Update
        for(BaseProjectile bp : this.plist){
            bp.update(delta);
        }
    }

    public void checkCollision(Array<GameObject> oList){
        for(BaseProjectile bp : this.plist) {
            for (GameObject o : oList) {
                if(bp.checkCollision(o)){
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
    }

    public void addProjectile(BaseProjectile p){
        this.plist.add(p);
    }

    public Array<BaseProjectile> getProjectileList(){
        return this.plist;
    }
}

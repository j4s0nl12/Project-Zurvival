package com.mygdx.game.object.projectile;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.object.GameObject;

public class BaseProjectile extends GameObject {

    public int dmg;
    public int pierceCt;
    public boolean pierceInf;
    public Array<GameObject> hitObjects;

    public BaseProjectile(float posX, float posY, Vector2 dir, float spd, float angle) {
        super(posX, posY);
        this.vel = dir.scl(spd);
        this.angle = angle;
        this.pierceCt = 0;
        this.pierceInf = false;
        this.hitObjects = new Array();
    }

    @Override
    public void setSprite(String imgFile){
        super.setSprite(imgFile);
        this.sprite.setRotation(this.angle);
    }

    @Override
    public void update(float delta){
        super.update(delta);
        this.outOfBorder();
    }

    public void addHitObject(GameObject o){
        this.hitObjects.add(o);
    }

    public boolean checkCollision(GameObject o){
        if(this.sprite.getBoundingRectangle().overlaps(o.sprite.getBoundingRectangle()) &&
                !this.hitObjects.contains(o,true))
            return true;
        return false;
    }
}

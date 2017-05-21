package com.mygdx.game.object.projectile;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.object.GameObject;

public class Bullet extends BaseProjectile{

    public Bullet(float posX, float posY, Vector2 dir, float angle) {
        super(posX, posY, dir, .4f, angle);
        this.setSprite("Images/Objects/bulletTmp.png");
        this.dmg = 1;
        this.pierceCt = 3;
        this.hitObjects = new Array();
    }

    public void addHitObject(GameObject o){
        this.hitObjects.add(o);
    }

    public boolean checkIfHitObject(GameObject o){
        if(this.hitObjects.contains(o, true))
            return true;
        return false;
    }
}

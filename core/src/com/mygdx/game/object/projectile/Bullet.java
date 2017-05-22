package com.mygdx.game.object.projectile;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.object.GameObject;

public class Bullet extends BaseProjectile{

    public Bullet(float posX, float posY, Vector2 dir, float angle) {
        super(posX, posY, dir, .4f, angle);
        this.setSprite("Images/Objects/bulletTmp.png");
        this.dmg = 1;
        this.pierceCt = 1;
        this.hitObjects = new Array();
    }
}

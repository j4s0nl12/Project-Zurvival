package com.mygdx.game.enemy;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.object.GameObject;
import com.mygdx.game.player.Player;
import com.mygdx.game.utility.Utility;

public class BaseEnemy extends GameObject{

    float sightRad;
    Circle sightCircle;
    long lastWanderCall;
    long wanderDelay;
    int minRandom;
    int maxRandom;
    boolean playerInSight;
    Vector2 seekV;

    int dmg;
    long lastAttack;
    long attackDelay;
    boolean canAttack;

    public BaseEnemy(float posX, float posY) {
        super(posX, posY);
        this.dmg = 0;
        this.seekV = this.pos;
        this.maxAccel = 3f;
        this.accelIncr = this.maxAccel*3f;
        this.sightRad = 0;
        this.minRandom = 3000;
        this.maxRandom = 6000;
        this.wanderDelay = (long)MathUtils.random(this.minRandom, this.maxRandom);
        this.lastWanderCall = System.currentTimeMillis() - this.wanderDelay;
        this.playerInSight = false;

        this.canAttack = true;
        this.attackDelay = (long)MathUtils.random(1000,2000);
        this.lastAttack = System.currentTimeMillis() - this.attackDelay;
    }

    @Override
    public void setSprite(String imgFile){
        super.setSprite(imgFile);
        if(this.sightRad > 0) {
            Vector2 tmp = this.getPosWithOffset();
            this.sightCircle = new Circle(tmp.x, tmp.y, this.sightRad);
        }
    }

    public void update(float delta, Player player){
        super.update(delta);
        this.sightCircle.setPosition(this.getPosWithOffset());

        if(System.currentTimeMillis() >= this.lastAttack + this.attackDelay){
            this.canAttack = true;
        }

        if (this.sightCircle.overlaps(player.getBoundingCircle(1f))) {
            this.playerInSight = true;
        }
        else if(this.playerInSight && !this.sightCircle.overlaps(player.getBoundingCircle(1f))) {
            this.playerInSight = false;
            this.setWanderDelay();
        }

        if(this.canAttack) {
            if (!this.playerInSight) {
                if (System.currentTimeMillis() >= this.lastWanderCall + this.wanderDelay) {
                    wander(delta);
                }
                this.seek(this.seekV, delta);
            } else {
                this.seek(player.getPosWithOffset(), delta);
            }

            if (this.getBoundingCircle(1f).contains(player.getBoundingCircle(.5f))) {
                this.attack(player);
            }
        }

        this.simpleBorder();
        if(this.hp <= 0){
            this.toDestroy = true;
        }
    }

    public void attack(Player player){
        this.vel = new Vector2();
        player.hp -= this.dmg;
        this.lastAttack = System.currentTimeMillis();
        this.canAttack = false;
    }

    public void rotate(float x, float y){
        if(x != 0 && y != 0){
            this.angle = MathUtils.radiansToDegrees * MathUtils.atan2(x,y) * -1f;
            if(this.angle > 360)
                this.angle = 360;
        }
        this.sprite.setRotation(this.angle);
    }

    public void seek(Vector2 pos, float delta){
        //Get vector direction
        Vector2 dir = pos.cpy().sub(this.getPosWithOffset());
        dir.nor();
        //Accel in direction
        this.rotate(dir.x,dir.y);
        dir.scl(this.accelIncr);
        this.accel(dir, delta);
        //Stopping
        if(this.sprite.getBoundingRectangle().contains(new Circle(pos.x, pos.y, 5f))){
            this.vel = new Vector2();
        }
    }

    public void wander(float delta){
        float randX = MathUtils.random(0,Utility.GAME_WORLD_WIDTH);
        float randY = MathUtils.random(0,Utility.GAME_WORLD_HEIGHT);
        Vector2 rand = new Vector2(randX,randY);
        this.seekV = rand;
        this.setWanderDelay();
    }

    public void setWanderDelay(){
        this.lastWanderCall = System.currentTimeMillis();
        this.minRandom = MathUtils.random(4000,5500);
        this.maxRandom = MathUtils.random(6000,8000);
        this.wanderDelay = (long)MathUtils.random(this.minRandom, this.maxRandom);
    }
}

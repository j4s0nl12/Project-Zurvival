package com.mygdx.game.object;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.utility.Utility;

public class GameObject {

    public int maxHp;
    public int hp;
    public Vector2 pos;
    public Vector2 vel;
    public float maxAccel;
    public float accelIncr;
    public float angle;
    public boolean isMovingX;
    public boolean isMovingY;
    public Sprite sprite;//Main body for hit box

    public GameObject(float posX, float posY, String imgFile){
        this.pos = new Vector2(posX,posY);
        this.vel = new Vector2();
        this.isMovingX = false;
        this.isMovingY = false;
        this.sprite = new Sprite(new Texture(imgFile));
        this.sprite.setPosition(this.pos.x,this.pos.y);
    }

    public void update(float delta){
        this.pos.add(this.vel);
        this.sprite.setPosition(this.pos.x,this.pos.y);
    }

    public void draw(SpriteBatch batch){
        this.sprite.draw(batch);
    }

    public void accel(Vector2 acc, float delta){
        this.vel.add(acc.scl(delta));
        if(this.vel.dot(this.vel) > Math.pow(this.maxAccel,2))
            this.vel.nor().scl(this.maxAccel);
    }

    public void friction(float delta, float f){
        Vector2 old = this.vel.cpy();
        Vector2 tmp = this.vel.cpy();
        Vector2 frict = tmp.nor().scl(-f);
        this.vel.add(frict.scl(delta));
        if(this.vel.dot(old) < 0)
            this.vel = new Vector2();
    }

    public void simpleBorder() {
        if (this.pos.x < 0) {
            this.pos.x = 0;
            this.vel.x = 0;
        } else if (this.pos.x > Utility.GAME_WORLD_WIDTH - this.sprite.getWidth()){
            this.pos.x = Utility.GAME_WORLD_WIDTH - this.sprite.getWidth();
            this.vel.x = 0;
        }
        if(this.pos.y < 0) {
            this.pos.y = 0;
            this.vel.y = 0;
        }else if(this.pos.y > Utility.GAME_WORLD_HEIGHT - this.sprite.getHeight()) {
            this.pos.y = Utility.GAME_WORLD_HEIGHT - this.sprite.getHeight();
            this.vel.y = 0;
        }
        this.sprite.setPosition(this.pos.x, this.pos.y);
    }
}

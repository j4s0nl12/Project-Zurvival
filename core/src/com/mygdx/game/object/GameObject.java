package com.mygdx.game.object;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.utility.Utility;

public class GameObject {

    public int hp;
    public float x;
    public float y;
    public float accelX;
    public float accelY;
    public float maxAccel;
    public float accelIncr;
    public float angle;
    public boolean isMovingX;
    public boolean isMovingY;
    public Sprite sprite;//Main body for hit box

    public GameObject(float posX, float posY, String imgFile){
        this.x = posX;
        this.y = posY;
        this.isMovingX = false;
        this.isMovingY = false;
        this.sprite = new Sprite(new Texture(imgFile));
        this.sprite.setPosition(this.x,this.y);
    }

    public void update(float delta){
        this.x += this.accelX;
        this.y += this.accelY;
        this.sprite.setPosition(this.x,this.y);
    }

    public void draw(SpriteBatch batch){
        this.sprite.draw(batch);
    }

    public void simpleBorder() {
        if (this.x < 0) {
            this.x = 0;
            this.accelX = 0;
        } else if (this.x > Utility.GAME_WORLD_WIDTH - this.sprite.getWidth()){
            this.x = Utility.GAME_WORLD_WIDTH - this.sprite.getWidth();
            this.accelX = 0;
        }
        if(this.y < 0) {
            this.y = 0;
            this.accelY = 0;
        }else if(this.y > Utility.GAME_WORLD_HEIGHT - this.sprite.getHeight()) {
            this.y = Utility.GAME_WORLD_HEIGHT - this.sprite.getHeight();
            this.accelY = 0;
        }
        this.sprite.setPosition(this.x, this.y);
    }

    public void frictionX(float delta, float f){
        if(this.accelX > 0){
            this.accelX -= f * delta;
            if(this.accelX < 0)
                this.accelX = 0;
        }else if(this.accelX < 0){
            this.accelX += f * delta;
            if(this.accelX > 0)
                this.accelX = 0;
        }
    }

    public void frictionY(float delta, float f){
        if(this.accelY > 0){
            this.accelY -= f * delta;
            if(this.accelY < 0)
                this.accelY = 0;
        }else if(this.accelY < 0){
            this.accelY += f * delta;
            if(this.accelY > 0)
                this.accelY = 0;
        }
    }
}

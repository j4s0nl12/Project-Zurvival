package com.mygdx.game.object;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BulletParticle {

    private long ttl;
    private long spawnedTime;
    public boolean isAlive;
    public float alpha;

    public Sprite img;

    public BulletParticle(float x, float y) {
        this.img = new Sprite(new Texture("BulletDent.png"));
        this.img.setPosition(x - this.img.getWidth()/2, y - this.img.getHeight()/2);
        this.ttl = 1000;
        this.spawnedTime = System.currentTimeMillis();
        this.isAlive = true;
        this.alpha = 1f;
    }

    public void draw(SpriteBatch batch){
        this.img.draw(batch);
    }

    public void update(float delta){
        long time = System.currentTimeMillis();
        if((this.spawnedTime + this.ttl - time) <= 50){
            this.alpha -= 2f * delta;
            this.img.setAlpha(this.alpha);
        }
        if(this.alpha <= 0)
            this.isAlive = false;
    }
}

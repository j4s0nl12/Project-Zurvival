package com.mygdx.game.Object;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Image {

    public Sprite img;

    public boolean isButton;
    public boolean canBeTouched;

    public Image(float posX, float posY, float scale, String imgFile){
        this.isButton = false;
        this.canBeTouched = true;
        this.img = new Sprite(new Texture(imgFile));
        this.img.scale(scale);
        this.img.setPosition(posX - this.img.getWidth()/2, posY - this.img.getHeight()/2);
    }

    public void draw(SpriteBatch batch){
        this.img.draw(batch);
    }

    public boolean isTouched(int screenX, int screenY){
        if (this.isButton && this.canBeTouched)
            if (this.img.getBoundingRectangle().contains(screenX, screenY))
                return true;
        return false;
    }

    public void setIsButton(boolean t) {
        this.isButton = t;
        this.canBeTouched = t;
    }

    public void dispose(){
        this.img.getTexture().dispose();
    }
}

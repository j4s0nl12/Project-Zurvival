package com.mygdx.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad.TouchpadStyle;

public class CirclePad {

    public Touchpad tp;
    private TouchpadStyle style;
    private Skin skin;

    public CirclePad(float x, float y, float size){
        this.skin = new Skin(Gdx.files.internal("Skins/neon/skin/neon-ui.json"));
        this.skin.addRegions(new TextureAtlas("Skins/neon/skin/neon-ui.atlas"));
        this.tp = new Touchpad(10f,this.skin);
        this.tp.setOrigin(x,y);
        this.tp.setSize(size,size);
        this.tp.setPosition(x-this.tp.getWidth()/2,y-this.tp.getHeight()/2);
        //this.tp.setBounds(x-this.tp.getWidth()/2,y-this.tp.getHeight()/2,size,size);
    }

    public Touchpad getTouchpad(){
        return this.tp;
    }

    public float getPercentX(){
        return this.tp.getKnobPercentX();
    }

    public float getPercentY(){
        return this.tp.getKnobPercentY();
    }
}

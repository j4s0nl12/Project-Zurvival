package com.mygdx.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;

public class CirclePad {

    public Touchpad tp;
    private Skin skin;

    public CirclePad(float x, float y, float size){
        String dir = "Skins/neon/skin/";
        this.skin = new Skin(Gdx.files.internal(dir + "neon-ui.json"));
        this.skin.addRegions(new TextureAtlas(dir + "neon-ui.atlas"));
        this.tp = new Touchpad(10f,this.skin);
        this.tp.setOrigin(x,y);
        this.tp.setSize(size,size);
        this.tp.setPosition(x-this.tp.getWidth()/2,y-this.tp.getHeight()/2);
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

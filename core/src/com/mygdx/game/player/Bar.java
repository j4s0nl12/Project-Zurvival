package com.mygdx.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Bar {

    private ProgressBar bar;
    private Skin skin;

    public Bar(float x, float y, float width, float max){
        String dir = "Skins/star-soldier/skin/";
        this.skin = new Skin(Gdx.files.internal(dir + "star-soldier-ui.json"));
        this.skin.addRegions(new TextureAtlas(dir + "star-soldier-ui.atlas"));
        this.bar = new ProgressBar(0,max,1f,false,this.skin);
        this.bar.setWidth(width);
        this.bar.setPosition(x,y - this.bar.getHeight()/2);
    }

    public ProgressBar getProgressBar(){
        return this.bar;
    }

    public void setValue(float value){
        this.bar.setValue(value);
    }
}

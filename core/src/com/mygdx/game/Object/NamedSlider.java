package com.mygdx.game.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.main.ProjectZurvivalMain;

public class NamedSlider {

    private Slider slider;
    Sprite img;

    String key;

    public NamedSlider(String prefKey, String imgFile){
        this.key = prefKey;
        this.img = new Sprite(new Texture(imgFile));
    }

    public void init(final ProjectZurvivalMain game, float x, float y){
        Skin skin = new Skin(Gdx.files.internal("Skins/Slider/uiskin.json"));
        skin.addRegions(new TextureAtlas("Skins/Slider/uiskin.atlas"));
        this.slider = new Slider(0,1,0.01f,false,skin);
        this.slider.setValue(game.util.getPref().getFloat(this.key));
        this.slider.setWidth(game.util.GAME_WORLD_WIDTH*3/4);
        this.slider.getStyle().background.setMinHeight(40);
        this.slider.getStyle().knob.setMinHeight(60);
        this.slider.getStyle().knob.setMinWidth(40);
        this.slider.setPosition(x, y, 0);
        this.slider.setAnimateDuration(.1f);
        this.slider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.util.setValue(key, slider.getValue());
                game.util.getPref().putFloat(key,slider.getValue());
                game.util.getPref().flush();
            }
        });
        this.img.setScale(1f);
        this.img.setPosition(x - this.img.getWidth()/2,y - this.img.getHeight()/2 + game.util.GAME_WORLD_HEIGHT/20);
    }

    public void draw(SpriteBatch batch){
        this.img.draw(batch);
    }

    public Slider getSlider(){
        return this.slider;
    }
}

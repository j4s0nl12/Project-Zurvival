package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.main.ProjectZurvivalMain;
import com.mygdx.game.object.Image;
import com.mygdx.game.object.NamedSlider;

public class OptionsScreen extends BaseScreen {

    Stage stage;

    Image options;
    Image back;

    NamedSlider masterVol;
    NamedSlider bgmVol;
    NamedSlider sfxVol;

    public OptionsScreen(ProjectZurvivalMain gam) {
        super(gam);

        options = new Image(game.util.GAME_WORLD_WIDTH/2, game.util.GAME_WORLD_HEIGHT*9/10, .8f, "Images/MainMenu/Options.png");
        back = new Image(game.util.GAME_WORLD_WIDTH/2, game.util.GAME_WORLD_HEIGHT/10, .2f, "Images/MainMenu/Back.png");
        back.setIsButton(true);

        stage = new Stage(game.camera.viewport);

        masterVol = new NamedSlider(game.util.master_volume_str, "Images/Options/MasterVol.png");
        masterVol.init(game,game.util.GAME_WORLD_WIDTH/2, game.util.GAME_WORLD_HEIGHT*14/20);

        bgmVol = new NamedSlider(game.util.bgm_volume_str, "Images/Options/BGMVol.png");
        bgmVol.init(game,game.util.GAME_WORLD_WIDTH/2, game.util.GAME_WORLD_HEIGHT*11/20);

        sfxVol = new NamedSlider(game.util.sfx_volume_str, "Images/Options/SFXVol.png");
        sfxVol.init(game,game.util.GAME_WORLD_WIDTH/2, game.util.GAME_WORLD_HEIGHT*8/20);

        stage.addActor(masterVol.getSlider());
        stage.addActor(bgmVol.getSlider());
        stage.addActor(sfxVol.getSlider());
    }

    @Override
    public void render(float delta){
        super.render(delta);

        stage.act(delta);
        stage.draw();

        game.util.batch.begin();
        options.draw(game.util.batch);
        back.draw(game.util.batch);
        masterVol.draw(game.util.batch);
        bgmVol.draw(game.util.batch);
        sfxVol.draw(game.util.batch);
        game.util.batch.end();
    }

    @Override
    public void show(){
        InputMultiplexer iM = new InputMultiplexer();
        iM.addProcessor(stage);
        iM.addProcessor(this);
        Gdx.input.setInputProcessor(iM);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button){
        super.touchDown(screenX, screenY, pointer, button);
        Vector3 tmp = game.camera.getProjectAt(screenX, screenY);
        int x = (int)tmp.x;
        int y = (int)tmp.y;

        if(back.isTouched(x,y)){
            game.sm.goToScreen(game.sm.lastScreen);
        }

        return true;
    }
}

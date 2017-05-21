package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.math.Vector3;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.main.ProjectZurvivalMain;
import com.mygdx.game.player.Player;

public class GameScreen extends BaseScreen{

    public Player player;

    Stage stage;

    public GameScreen(ProjectZurvivalMain gam) {
        super(gam);
        this.setBG_Color(.1f,.1f,.1f,1);

        this.stage = new Stage(game.camera.viewport);
        player = new Player(game.util.GAME_WORLD_WIDTH/2, game.util.GAME_WORLD_HEIGHT/2);//, "Images/Objects/HumanTmp.png");
        this.stage.addActor(player.leftStick.getTouchpad());
        this.stage.addActor(player.rightStick.getTouchpad());
        this.stage.addActor(player.hpBar.getProgressBar());
    }

    @Override
    public void render(float delta){
        super.render(delta);

        game.util.batch.begin();
        player.draw(game.util.batch);
        for(Actor a : this.stage.getActors())
            a.draw(game.util.batch, .75f);
        game.util.batch.end();

        //stage.draw();

        stage.act(delta);
        player.update(delta);
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

        this.player.hp -= 10;

        return true;
    }
}

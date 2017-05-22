package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Vector3;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.enemy.EnemyManager;
import com.mygdx.game.enemy.Zombie;
import com.mygdx.game.main.ProjectZurvivalMain;
import com.mygdx.game.player.Player;
import com.mygdx.game.utility.Utility;

public class GameScreen extends BaseScreen{

    public Player player;
    public EnemyManager em;

    Stage stage;

    boolean isPaused;

    BitmapFont font = new BitmapFont();
    BitmapFont font2 = new BitmapFont();
    GlyphLayout layout = new GlyphLayout();

    public GameScreen(ProjectZurvivalMain gam) {
        super(gam);
        this.setBG_Color(.1f,.1f,.1f,1);

        this.stage = new Stage(game.camera.viewport);
        player = new Player(game.util.GAME_WORLD_WIDTH/2, game.util.GAME_WORLD_HEIGHT/2);//, "Images/Objects/HumanTmp.png");
        this.stage.addActor(player.leftStick.getTouchpad());
        this.stage.addActor(player.rightStick.getTouchpad());
        this.stage.addActor(player.hpBar.getProgressBar());

        this.isPaused = false;

        this.em = new EnemyManager();
        this.em.addEnemy(new Zombie(800,800));

        font.getData().scale(3);
        font2.getData().scale(1.5f);
    }

    @Override
    public void render(float delta){
        super.render(delta);

        game.util.batch.begin();
        this.em.draw(game.util.batch);
        player.draw(game.util.batch);
        for(Actor a : this.stage.getActors())
            a.draw(game.util.batch, .75f);
        font2.draw(game.util.batch, "Highscore: " + game.util.getPref().getInteger(game.util.highscore_str),
                  Utility.GAME_WORLD_WIDTH/2, Utility.GAME_WORLD_HEIGHT*19/20);
        if(this.isPaused){
            String text = "PAUSED";
            if(this.player.hp <= 0){
                text = "GAME OVER";
            }
            layout.setText(font, text);
            font.draw(game.util.batch, text, Utility.GAME_WORLD_WIDTH/2 - layout.width/2, Utility.GAME_WORLD_HEIGHT/2 - layout.height/2);
        }
        game.util.batch.end();

        if(!isPaused){
            stage.act(delta);
            player.update(delta);
            if(player.hp <= 0){
                this.isPaused = true;
            }
            this.em.update(delta, this.player);
            player.pm.checkCollision(this.em.getlist());
        }

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

        if(this.player.hp > 0)
            this.isPaused = !this.isPaused;
        else{
            if(this.player.pm.killCt > game.util.getPref().getInteger(game.util.highscore_str)) {
                game.util.getPref().putInteger(game.util.highscore_str, this.player.pm.killCt);
                game.util.getPref().flush();
            }
            game.sm.goToScreen(game.sm.MAINMENUSCREEN);
        }
        return true;
    }
}

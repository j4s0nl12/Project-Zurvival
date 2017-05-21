package com.mygdx.game.screens;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.particles.BulletParticle;
import com.mygdx.game.object.Image;
import com.mygdx.game.main.ProjectZurvivalMain;
import com.mygdx.game.utility.SoundManager;
import com.mygdx.game.utility.Utility;

public class MainMenuScreen extends BaseScreen{

    Image title;
    Image newgame;
    Image continuegame;
    Image options;
    Image stats;

    Array<BulletParticle> blist;
    long bulletDelay;

    public MainMenuScreen(ProjectZurvivalMain gam) {
        super(gam);
        this.setBG_Color(.15f,.2f,.15f,1);

        this.blist = new Array();bulletDelay = 80L;

        title = new Image(game.util.GAME_WORLD_WIDTH/2,game.util.GAME_WORLD_HEIGHT*3/4,.2f,"Images/MainMenu/Title.png");
        newgame = new Image(game.util.GAME_WORLD_WIDTH/2, game.util.GAME_WORLD_HEIGHT*9/20, .2f, "Images/MainMenu/New Game.png");
        newgame.setIsButton(true);
        continuegame = new Image(game.util.GAME_WORLD_WIDTH/2, game.util.GAME_WORLD_HEIGHT*9/20, .2f, "Images/MainMenu/Continue.png");
        continuegame.setIsButton(true);
        options = new Image(game.util.GAME_WORLD_WIDTH/2, game.util.GAME_WORLD_HEIGHT*6/20,.2f, "Images/MainMenu/Options.png");
        options.setIsButton(true);
        stats = new Image(game.util.GAME_WORLD_WIDTH/2, game.util.GAME_WORLD_HEIGHT*3/20, .2f, "Images/MainMenu/Statistics.png");
        stats.setIsButton(true);
    }

    @Override
    public void render(float delta){
        super.render(delta);

        for(int i = 0; i < blist.size; i++){
            blist.get(i).update(delta);
            if(!blist.get(i).isAlive)
                blist.removeIndex(i);
        }

        game.util.batch.begin();
        title.draw(game.util.batch);
        newgame.draw(game.util.batch);
        //continuegame.draw(game.util.batch);
        options.draw(game.util.batch);
        stats.draw(game.util.batch);
        for(BulletParticle bp : this.blist){
            bp.draw(game.util.batch);
        }
        game.util.batch.end();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button){
        super.touchDown(screenX, screenY, pointer, button);
        Vector3 tmp = game.camera.getProjectAt(screenX, screenY);
        int x = (int)tmp.x;
        int y = (int)tmp.y;
        fireBullet(x,y);

        if(newgame.isTouched(x,y)){
            game.sm.goToScreen(game.sm.GAMESCREEN);
        }

        if(options.isTouched(x,y)){
            game.sm.goToScreen(game.sm.OPTIONSSCREEN);
        }

        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer){
        super.touchDragged(screenX, screenY, pointer);
        Vector3 tmp = game.camera.getProjectAt(screenX, screenY);
        int x = (int)tmp.x;
        int y = (int)tmp.y;
        fireBullet(x,y);
        return true;
    }

    @Override
    public void hide(){
        blist.clear();
        super.hide();
    }

    public void fireBullet(float x, float y){
        if(this.curTime >= this.lastTouchedTime + bulletDelay){
            this.lastTouchedTime = System.currentTimeMillis();
            this.blist.add(new BulletParticle(x, y));
            SoundManager.srsSound.play(Utility.getSfx_Volume());
        }
    }
}

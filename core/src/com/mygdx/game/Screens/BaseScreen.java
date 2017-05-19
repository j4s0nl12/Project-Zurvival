package com.mygdx.game.screens;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.main.ProjectZurvivalMain;

public class BaseScreen extends InputAdapter implements Screen {

    final ProjectZurvivalMain game;
    final String TAG = this.getClass().getSimpleName();

    private boolean touchDownAvailable;
    private boolean touchDraggedAvailable;
    private Color bgColor;

    public boolean debug;

    public long curTime;
    public long lastTouchedTime;

    public BaseScreen(final ProjectZurvivalMain gam){
        game = gam;
        this.curTime = System.currentTimeMillis();
        this.lastTouchedTime = this.curTime;
        this.debug = false;
        this.setBG_Color(0,0,0,1);
        this.touchDownAvailable = true;
        this.touchDraggedAvailable = true;
    }

    public void setBG_Color(float r, float g, float b, float a) {
        this.bgColor = new Color(r, g, b, a);
    }

    private void GL_ClearColor(Color c){
        Gdx.gl.glClearColor(c.r, c.g, c.b, c.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button){
        if (this.touchDownAvailable && this.debug) {
            Vector3 tmp = game.camera.getProjectAt(screenX, screenY);
            Gdx.app.log(TAG, "touch down at (" + (int)tmp.x + ", " + (int)tmp.y + ")");
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (this.touchDraggedAvailable && this.debug) {
            Vector3 tmp = game.camera.getProjectAt(screenX, screenY);
            Gdx.app.log(TAG, "touch drag at (" + (int)tmp.x + ", " + (int)tmp.y + ")");
        }
        return true;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
        this.lastTouchedTime = System.currentTimeMillis();
    }

    @Override
    public void render(float delta) {
        this.curTime = System.currentTimeMillis();
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
            Gdx.app.exit();

        this.GL_ClearColor(this.bgColor);

        game.camera.update();
        game.util.batch.setProjectionMatrix(game.camera.combined());
    }

    @Override
    public void resize(int width, int height) {
        game.resize(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {

    }


}

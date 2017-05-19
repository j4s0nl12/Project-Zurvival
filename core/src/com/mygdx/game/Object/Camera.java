package com.mygdx.game.object;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import com.mygdx.game.utility.Utility;

public class Camera {

    public static OrthographicCamera camera;
    public static Viewport viewport;

    public static int w;
    public static int h;

    public Camera(Utility util){
        w = util.GAME_WORLD_WIDTH;
        h = util.GAME_WORLD_HEIGHT;
        camera = new OrthographicCamera();
        //viewport = new FitViewport(w,h,camera);
        viewport = new ExtendViewport(w, h, camera);
    }

    public void update(){
        camera.update();
    }

    public Matrix4 combined(){
        return camera.combined;
    }

    public void resize(int width, int height){
        viewport.update(width, height);
        camera.position.set(w/2, h/2, 0);
    }

    public Vector3 getProjectAt(int width, int height){
        return camera.unproject(new Vector3(width, height, 0));
    }

}

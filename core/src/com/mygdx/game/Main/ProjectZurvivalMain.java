package com.mygdx.game.Main;

import com.badlogic.gdx.Game;

import com.mygdx.game.Object.Camera;
import com.mygdx.game.Screens.ScreenManager;
import com.mygdx.game.Utility.Utility;

public class ProjectZurvivalMain extends Game {
	public static Utility util;
	public static Camera camera;
	public static ScreenManager sm;

	@Override
	public void create () {
		util = new Utility();
		camera = new Camera(util);
		sm = new ScreenManager(this);
		sm.goToScreen(sm.MAINMENUSCREEN);
	}

	public void resize(int width, int height){
		camera.resize(width, height);
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		util.dispose();
	}
}

package com.mygdx.game.screens;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.main.ProjectZurvivalMain;

public class ScreenManager {
    final ProjectZurvivalMain game;

    public final static int MAINMENUSCREEN = 0;
    public final static int GAMESCREEN = 1;
    public final static int OPTIONSSCREEN = 2;

    public Array<BaseScreen> screenList;
    public static int lastScreen;
    public static int currentScreen;

    public ScreenManager(final ProjectZurvivalMain gam){
        game = gam;
        screenList = new Array();
        init();
    }

    public void init(){
        screenList.add(new MainMenuScreen(game));
        screenList.add(new GameScreen(game));
        screenList.add(new OptionsScreen(game));

        lastScreen = MAINMENUSCREEN;
        currentScreen = MAINMENUSCREEN;
    }

    public void goToScreen(int screenIdx){
        lastScreen = currentScreen;
        game.setScreen(getScreen(screenIdx));
        currentScreen = screenIdx;
    }

    public void goToNewGameScreen(){
        this.screenList.removeIndex(GAMESCREEN);
        this.screenList.insert(GAMESCREEN,new GameScreen(game));
        this.goToScreen(GAMESCREEN);
    }

    public BaseScreen getScreen(int screenIdx){
        return screenList.get(screenIdx);
    }
}

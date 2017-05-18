package com.mygdx.game.Screens;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Main.ProjectZurvivalMain;

public class ScreenManager {
    final ProjectZurvivalMain game;

    public final static int MAINMENUSCREEN = 0;
    public final static int OPTIONSSCREEN = 1;

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
        screenList.add(new OptionsScreen(game));

        lastScreen = MAINMENUSCREEN;
        currentScreen = MAINMENUSCREEN;
    }

    public void goToScreen(int screenIdx){
        lastScreen = currentScreen;
        game.setScreen(getScreen(screenIdx));
        currentScreen = screenIdx;
    }

    public BaseScreen getScreen(int screenIdx){
        return screenList.get(screenIdx);
    }
}

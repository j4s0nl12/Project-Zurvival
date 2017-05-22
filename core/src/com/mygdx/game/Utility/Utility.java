package com.mygdx.game.utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Utility {
    /*********************GAME RELATED*****************************************/
    public static int GAME_WORLD_WIDTH = 1600;                              /**/
    public static int GAME_WORLD_HEIGHT = 900;                              /**/
    public static SpriteBatch batch;                                        /**/
   /**************************************************************************/

    /**********************PREFERENCES*****************************************/
    private static final String PREF_NAME = "mygame_pref";                  /**/
    private static Preferences pref;                                        /**/
    private static float volume_offset = 0.2f;                              /**/
                                                                            /**/
    public static float master_volume;                                      /**/
    public static String master_volume_str = "master_volume";               /**/
    public static float bgm_volume;                                         /**/
    public static String bgm_volume_str = "bgm_volume";                     /**/
    public static float sfx_volume;                                         /**/
    public static String sfx_volume_str = "sfx_volume";                     /**/
                                                                            /**/
    public static int highscore;                                            /**/
    public static String highscore_str = "highscore";                       /**/
    /**************************************************************************/

    public Utility() {
        init();
    }

    private static void init() {
        batch = new SpriteBatch();
        initPref();
    }

    public static void print(String TAG, String msg){
        Gdx.app.log(TAG, msg);
    }

    private static void initPref() {
        if (pref == null)
            pref = Gdx.app.getPreferences(PREF_NAME);

        //Master Volume
        if (!pref.contains(master_volume_str)) {
            master_volume = 1f;
            pref.putFloat(master_volume_str, master_volume);
            pref.flush();
        }else{
            master_volume = pref.getFloat(master_volume_str);
        }
        //Background Music Volume
        if (!pref.contains(bgm_volume_str)){
            bgm_volume = 0.5f;
            pref.putFloat(bgm_volume_str, bgm_volume);
            pref.flush();
        }else{
            bgm_volume = pref.getFloat(bgm_volume_str);
        }
        //Sound Effect Volume
        if (!pref.contains(sfx_volume_str)){
            sfx_volume = 0.5f;
            pref.putFloat(sfx_volume_str, sfx_volume);
            pref.flush();
        }else {
            sfx_volume = pref.getFloat(sfx_volume_str);
        }
        //HighScore
        if (!pref.contains(highscore_str)){
            highscore = 0;
            pref.putInteger(highscore_str, highscore);
            pref.flush();
        }else{
            highscore = pref.getInteger(highscore_str);
        }
    }

    public void setValue(String key, float value){
        if(key.equals(master_volume_str))
            master_volume = value;
        else if(key.equals(bgm_volume_str))
            bgm_volume = value;
        else if(key.equals(sfx_volume_str))
            sfx_volume = value;
        else
            Gdx.app.log("Utility", "setValue: " + key + " is invalid.");
    }

    public static float getBgm_Volume(){
        return master_volume * bgm_volume * volume_offset;
    }

    public static float getSfx_Volume(){
        return master_volume * sfx_volume * volume_offset;
    }

    public void dispose(){
        batch.dispose();
    }

    public Preferences getPref(){
        return pref;
    }
}
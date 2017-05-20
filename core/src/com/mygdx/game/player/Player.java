package com.mygdx.game.player;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.object.GameObject;
import com.mygdx.game.utility.Utility;

public class Player extends GameObject {

    public CirclePad leftStick;
    public CirclePad rightStick;

    public float angle;

    public Player(float posX, float posY, String imgFile){
        super(posX,posY,imgFile);
        this.hp = 100;
        this.maxAccel = 12f;
        this.accelIncr = this.maxAccel*3f;//Very reponsive, little sliding

        float size = 275f;
        float leftX = Utility.GAME_WORLD_WIDTH*4/30;
        float rightX = Utility.GAME_WORLD_WIDTH-leftX;
        float height = Utility.GAME_WORLD_HEIGHT*7/30;
        this.leftStick = new CirclePad(leftX, height, size);
        this.rightStick = new CirclePad(rightX, height, size);
    }

    public void update(float delta){
        //Movement (Left Stick)
        Vector2 tmp = new Vector2(this.leftStick.getPercentX(), this.leftStick.getPercentY());
        tmp.scl(this.accelIncr);
        this.accel(tmp,delta);

        //Rotation (RightStick)
        this.calcAngle(this.rightStick.getPercentX(), this.rightStick.getPercentY(), delta);

        super.update(delta);
        this.simpleBorder();
        if(!this.leftStick.getTouchpad().isTouched())
            this.friction(delta,this.accelIncr*2);
    }

    public void calcAngle(float x, float y, float delta){
        if(x != 0 && y != 0) {
            this.angle = MathUtils.radiansToDegrees * MathUtils.atan2(x, y) * -1;
            if (this.angle > 360)
                this.angle -= 360;
        }
        this.sprite.setRotation(this.angle);
    }
}

package com.mygdx.game.player;

import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.object.GameObject;
import com.mygdx.game.utility.Utility;

public class Player extends GameObject {

    public CirclePad leftStick;
    public CirclePad rightStick;

    public float angle;

    public Player(float posX, float posY, String imgFile){
        super(posX,posY,imgFile);
        this.hp = 100;
        this.maxAccel = 20f;
        this.accelIncr = 20f;

        float size = 275f;
        float leftX = Utility.GAME_WORLD_WIDTH*4/30;
        float rightX = Utility.GAME_WORLD_WIDTH-leftX;
        float height = Utility.GAME_WORLD_HEIGHT*7/30;
        this.leftStick = new CirclePad(leftX, height, size);
        this.rightStick = new CirclePad(rightX, height, size);
    }

    public void update(float delta){
        //Movement (Left Stick)
        this.accelX += this.leftStick.getPercentX()*this.accelIncr*delta;
        if(this.accelX > this.maxAccel)
            this.accelX = this.maxAccel;
        else if(this.accelX < -this.maxAccel)
            this.accelX = -this.maxAccel;

        this.accelY += this.leftStick.getPercentY()*this.accelIncr*delta;
        if(this.accelY > this.maxAccel)
            this.accelY = this.maxAccel;
        else if(this.accelY < -this.maxAccel)
            this.accelY = -this.maxAccel;

        //Rotation (RightStick)
        this.calcAngle(this.rightStick.getPercentX(), this.rightStick.getPercentY(), delta);

        super.update(delta);
        this.simpleBorder();
        this.handleFriciton(delta, this.accelIncr*2);
    }

    public void calcAngle(float x, float y, float delta){
        if(x != 0 && y != 0) {
            this.angle = MathUtils.radiansToDegrees * MathUtils.atan2(x, y) * -1;
            if (this.angle > 360)
                this.angle -= 360;
        }
        this.sprite.setRotation(this.angle);
    }

    public void handleFriciton(float delta, float f){
        if(this.leftStick.getPercentX() == 0)
            this.isMovingX = false;
        else
            this.isMovingX = true;

        if(this.leftStick.getPercentY() == 0)
            this.isMovingY = false;
        else
            this.isMovingY = true;

        if(!this.isMovingX)
            this.frictionX(delta, f);
        if(!this.isMovingY)
            this.frictionY(delta, f);
    }
}

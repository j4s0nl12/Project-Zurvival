package com.mygdx.game.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.object.GameObject;
import com.mygdx.game.object.projectile.Bullet;
import com.mygdx.game.object.projectile.ProjectileManager;
import com.mygdx.game.utility.SoundManager;
import com.mygdx.game.utility.Utility;

public class Player extends GameObject {

    public CirclePad leftStick;
    public CirclePad rightStick;
    public Bar hpBar;

    public ProjectileManager pm;

    public float angle;

    public long lastShot;//Temporary, move to a weapon class
    public long shotDelay;//Temporary, move to a weapon class

    public Player(float posX, float posY){
        super(posX,posY);
        this.setSprite("Images/Objects/HumanTmp.png");

        this.maxHp = 100;
        this.hp = this.maxHp;
        this.maxAccel = 6f;
        this.accelIncr = this.maxAccel*4f;//Very responsive, little sliding

        //Touchpad
        float tpSize = 275f;
        float leftX = Utility.GAME_WORLD_WIDTH*4/30;
        float rightX = Utility.GAME_WORLD_WIDTH-leftX;
        float height = Utility.GAME_WORLD_HEIGHT*7/30;
        this.leftStick = new CirclePad(leftX, height, tpSize);
        this.rightStick = new CirclePad(rightX, height, tpSize);

        //Health Bar
        this.hpBar = new Bar(Utility.GAME_WORLD_WIDTH/30,
                             Utility.GAME_WORLD_HEIGHT*19/20,
                             Utility.GAME_WORLD_WIDTH*2/5,
                             this.maxHp);
        this.hpBar.setValue(this.hp);

        //Shooting stuff
        this.pm = new ProjectileManager();
        this.shotDelay = 80L;
        this.lastShot = System.currentTimeMillis() - this.shotDelay;
    }

    @Override
    public void draw(SpriteBatch batch){
        super.draw(batch);
        this.pm.draw(batch);
    }

    public void update(float delta){
        this.pm.update(delta);

        //Movement (Left Stick)
        Vector2 tmp = new Vector2(this.leftStick.getPercentX(), this.leftStick.getPercentY());
        tmp.nor();
        tmp.scl(this.accelIncr);
        this.accel(tmp,delta);

        //Rotation (RightStick)
        if(this.rightStick.tp.isTouched()) {
            this.calcAngle(this.rightStick.getPercentX(), this.rightStick.getPercentY(), delta);
            if(this.rightStick.getPercentX() != 0 || this.rightStick.getPercentX() != 0)
                this.shoot(this.rightStick.getPercentX(), this.rightStick.getPercentY());
        }

        super.update(delta);
        this.hpBar.setValue(this.hp);
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

    public void shoot(float x, float y){
        if(System.currentTimeMillis() >= this.lastShot + this.shotDelay) {
            //Get Direction
            Vector2 dir = new Vector2(x, y);

            //Create slight offset (Looks better with spray)
            float offset = .08f;
            Vector2 randOffset = new Vector2(MathUtils.random(-offset,offset), MathUtils.random(-offset,offset));
            dir.add(randOffset);
            dir.nor();

            //Create bullet start position
            Vector2 pos = this.getPosWithOffset();
            pos.add(dir.scl(this.sprite.getHeight()*2/3));

            //Create bullet
            this.pm.addProjectile(new Bullet(pos.x, pos.y, dir, this.angle));

            //Play Sound
            SoundManager.srsSound.play(Utility.getSfx_Volume()/5);

            //Set shoot timer
            this.lastShot = System.currentTimeMillis();
        }
    }
}

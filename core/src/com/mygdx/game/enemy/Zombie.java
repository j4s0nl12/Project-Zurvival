package com.mygdx.game.enemy;

public class Zombie extends BaseEnemy{

    public Zombie(float posX, float posY) {
        super(posX, posY);
        this.dmg = 20;
        this.hp = 8;
        this.sightRad = 280f;
        this.setSprite("Images/Objects/Zombie.png");
    }
}

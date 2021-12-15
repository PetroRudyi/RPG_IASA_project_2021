package Objects.Entity;


import Objects.GameObject;
import Window.GamePanel;
import Window.Settings;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity extends GameObject implements LivingStat {
    GamePanel gp;
    public final int screenX;
    public final int screenY;
    public int HP;
    public int MaxHP;
    public int damage;
    public Rectangle solidArea;
    public boolean collisionOn = false;


    public BufferedImage im, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Entity(GamePanel gp,int spawnX, int spawnY,int screenX,int screenY, int MaxHP, int HP, int damage){
        this.screenX = screenX;
        this.screenY = screenY;
        this.damage = damage;
        this.gp = gp;
        this.HP = HP;
        this.MaxHP = MaxHP;

        worldX =spawnX*gp.tileSize;
        worldY =spawnY*gp.tileSize;
        Settings.speed = 12;//gp.tileSize;
    }

    public void setDefaultValues(){
        worldX =gp.tileSize * 23;
        worldY =gp.tileSize * 21;
        Settings.speed = gp.tileSize;
        direction = "down";
    }

    public void update(){
        int i = (int)(Math.random()*3);
        if (i==0 && !collisionOn) {
            worldY -= Settings.speed;
        } else if (i==1 && !collisionOn) {
            worldY += Settings.speed;
        } else if (i==2 && !collisionOn) {
            worldX -= Settings.speed;
        } else if (i==3 && !collisionOn) {
            worldX += Settings.speed;
        }
    }

    @Override
    public double getHP() {
        return 0;
    }

    @Override
    public double getMaxHP() {
        return 0;
    }

    @Override
    public void setHP(double HP) {

    }

    @Override
    public void setMaxHP(double maxHP) {

    }

}

package Objects.Entity;


import Objects.GameObject;
import Window.GamePanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Entity extends GameObject implements LivingStat {
    GamePanel gp;
    public final int screenX;
    public final int screenY;
    public int HP;
    public int MaxHP;
    public int speed;
    public int damage;

    public BufferedImage im;

    public Entity(GamePanel gp,int screenX,int screenY, int MaxHP, int HP, int damage){
        this.screenX = screenX;
        this.screenY = screenY;
        this.damage = damage;
        this.gp = gp;
        this.HP = HP;
        this.MaxHP = MaxHP;

        worldX =0;
        worldY =0;
        speed = gp.tileSize;
        //setDefaultValues();
    }

    public void setDefaultValues(){
        worldX =gp.tileSize * 23;
        worldY =gp.tileSize * 21;
        speed = gp.tileSize;
    }

    public void update(){
        int i = (int)(Math.random()*3);
        if (i==0) {
            worldY -= speed;
        } else if (i==1) {
            worldY += speed;
        } else if (i==2) {
            worldX -= speed;
        } else if (i==3) {
            worldX += speed;
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

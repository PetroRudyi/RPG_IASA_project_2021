package Objects.Entity;


import Collision.CollisionChecker;
import Objects.GameObject;
import Window.GamePanel;
import Window.Settings;

import java.awt.image.BufferedImage;

public class Entity extends GameObject implements LivingStat {
    public GamePanel gp;
    public final int screenX;
    public final int screenY;
    public int HP;
    public int MaxHP;
    public int damage;
    public int speed;
    //public Rectangle solidArea;   //(Дуже крута ідея ходьби)
    //public boolean collisionOn = false;


    public BufferedImage im, up2, down1, down2, left1, left2, right1, right2;
    //public String direction; //(Дуже крута ідея ходьби)

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
        speed = gp.tileSize;//12
    }

    public void setDefaultValues(){
        worldX =gp.tileSize * 23;
        worldY =gp.tileSize * 21;
        speed = gp.tileSize;
        //direction = "down"; //(Дуже крута ідея ходьби)
    }

    public void update(){
        /*int i = (int)(Math.random()*3); //(Дуже крута ідея ходьби)
        if (i==0 && !collisionOn) {
            worldY -= Settings.speed;
        } else if (i==1 && !collisionOn) {
            worldY += Settings.speed;
        } else if (i==2 && !collisionOn) {
            worldX -= Settings.speed;
        } else if (i==3 && !collisionOn) {
            worldX += Settings.speed;
        }*/
        int i = (int)(Math.random()*3);
        if(isEnemy(i)){
            attack(Settings.player);
        }
        else if(CollisionChecker.checkMoveMobs(this,i)) {
                move(i);
            }

    }

    private void move(int i){
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

    private boolean isEnemy (int m){
        int nextPosX=worldX,nextPosY = worldY;
        if (m==0) {
            nextPosY = worldY - speed;
        } else if (m==1) {
            nextPosY =worldY + speed;
        } else if (m==2) {
            nextPosX =worldX - speed;
        } else if (m==3) {
            nextPosX = worldX + speed;
        }

        return ((Settings.player.worldX == nextPosX) || (Settings.player.worldY == nextPosY));
    }

    public void attack(Entity entity){
        entity.applyDamage(damage);
    }

    private void applyDamage(int damage){
        if (HP>0){
            HP-=damage;
            if(HP<0){
                HP=0;
                dead();
            };
        }
        else{dead();};
    }

    private void dead(){

    }

    @Override
    public int getHP() {
        return 0;
    }

    @Override
    public int getMaxHP() {
        return 0;
    }

    @Override
    public void setHP(int HP) {

    }

    @Override
    public void setMaxHP(int maxHP) {

    }

}

package Objects.Entity;
import Collision.CollisionChecker;
import Window.GamePanel;
import Window.Settings;
import Window.Interface;
import Character.CharacterHandler;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Set;

import static Window.Settings.player;
import static Window.Settings.player_lives;

public class Slime extends Entity {

    public Slime(GamePanel gp) {
        super(gp, 0, 0, gp.screenWidth / 2 - (gp.tileSize / 2), gp.screenHeight / 2 - (gp.tileSize / 2), 40, 40, 10);
        int[] spawnCord = Settings.getSpawnCord();
        this.worldX=spawnCord[0]*gp.tileSize;
        this.worldY=spawnCord[1]*gp.tileSize;
        getSlimeImage();
        //solidArea = new Rectangle(gp.tileSize / 3, gp.tileSize /3, gp.tileSize / 3 + 1, gp.tileSize / 3+1);
    }

    @Override
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
        if (!isDead) {
            int i = (int) (Math.random() * (3 + 1));
            if (isEnemy(i)) {
                gp.inter.showMessage2("Hit!", gp.screenHeight/5 - gp.tileSize/2, gp.screenHeight/3 + 30, 40, Color.WHITE);
                attack(player);
            } else if (CollisionChecker.checkMoveMobs(this, i)) {
                move(i);
            }
            sayCord();
        }
    }

    public void sayCord(){
        System.out.println("Slime   x: " + worldX + "   Y: " + worldY);

    }

    public void getSlimeImage() {
        try {
            im = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/mobs/slime_1.png")));
            im2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/mobs/dead.png")));
      } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void applyDamage(int damage){

        if (HP>0){
             HP-=damage;
            if(HP<0){
                HP=0;
                dead();
            };
        }
        else{dead();};
        String text = "Slime with "+ HP+" xp has attacked: " ;
        if (player.HP == 0  && Settings.player_lives >1){
            text = "You've lost your life";
            Settings.player_lives --;
            player.HP = player.MaxHP;
        }else if(player.HP == 0 && Settings.player_lives == 1){
            text = "You were killed!";
            Interface.gameFinished = true;
        }
            gp.inter.showMessage(text, gp.screenHeight/5 - gp.tileSize/2, 150, 20, Interface.violetForDisplyingMessages);
        //+ "-"+damage+ "xp"
        gp.inter.showMessage2("-"+damage+ "xp to slime's life" , gp.screenWidth/2- gp.tileSize/2, gp.screenHeight/2 + 2*gp.tileSize, 10, Interface.malynovy);
        System.out.print("Slime has attacked: " + HP+"\n");
        }


    public void draw(Graphics2D g2) {
        BufferedImage image;
        if(!isDead){
             image = im;
        }
        else{image = im2;
        }
        g2.drawImage(image, screenX+worldX-Settings.PlayerX, screenY+worldY-Settings.PlayerY, gp.tileSize, gp.tileSize, null);
    }

    @Override
    public int getHP() {
        return HP;
    }

    @Override
    public int getMaxHP() {
        return MaxHP;
    }

    @Override
    public void setHP(int HP) {
        this.HP = HP;
    }

    @Override
    public void setMaxHP(int maxHP) {

    }
}

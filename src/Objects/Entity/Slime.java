package Objects.Entity;
import Collision.CollisionChecker;
import Window.GamePanel;
import Window.Settings;
import Character.CharacterHandler;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

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
                attack(Settings.player);
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
//            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/Slime/pl1.png"))); doght2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/Slime/pl1.png")));
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
        System.out.print("Slime has attacked: " + HP+"\n");
    }


    public void draw(Graphics2D g2) {
        BufferedImage image = im;
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

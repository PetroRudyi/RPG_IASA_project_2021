package Window;

import Objects.Entity.Entity;

import java.awt.*;

public class Interface  {
    GamePanel gp;
    Font arial20_font;
    Color newCol;
    public boolean messageOn = false;
    public String message = "";

    public Interface(GamePanel gp){
        this.gp = gp;
        arial20_font = new Font("Arial", Font.BOLD, 20);
        newCol = new Color(  234, 56, 1 ); // 158, 5, 52 malynovyy
    }//234, 56, 1 orange good  81, 0, 61 violate
    public void draw(Graphics2D g2){
        // Font.PLAIN, number - is the size of text
        g2.setFont(arial20_font);
        g2.setColor(newCol);
        g2.drawString("Coins: " + gp.player.coin, 25, 25);
        g2.drawString("x,y: (" + Settings.PlayerX +";"+ Settings.PlayerY +")", gp.screenWidth - 170, 25);
        g2.drawString("XP: " + gp.player.HP+"|" +gp.player.MaxHP, 25, 50);
    }
    public void showMessage(String text){
        message = text;
        messageOn = true;
    }
}

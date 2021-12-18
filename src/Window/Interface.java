package Window;

import java.awt.*;

public class Interface  {
    GamePanel gp;
    Font arial20_font;
    Color orangeForInter, violetForDisplyingMessages, malynovy;
    public boolean messageOn = false;
    public String message = "";
    int messageTime = 0;
    int message_x, message_y;
    int message_font_size;

    public Interface(GamePanel gp){
        this.gp = gp;
        arial20_font = new Font("Arial", Font.BOLD, 20);
        orangeForInter = new Color(  234, 56, 1 ); // 158, 5, 52 malynovyy
        violetForDisplyingMessages =  new Color(  81, 0, 61 );
        malynovy = new Color(  158, 5, 52 );

    }//234, 56, 1 orange good  81, 0, 61 violate
    public void draw(Graphics2D g2){
        // Font.PLAIN, number - is the size of text
        g2.setFont(arial20_font);
        g2.setColor(orangeForInter);
        g2.drawString("Coins: " + gp.player.coin, 25, 25);
        g2.drawString("x,y: (" + Settings.PlayerX +";"+ Settings.PlayerY +")", gp.screenWidth - 170, 25);
        g2.drawString("XP: " + gp.player.HP+"|" +gp.player.MaxHP, 25, 50);


        if (messageOn) {
            g2.setFont(g2.getFont().deriveFont(message_font_size));
            g2.setColor(violetForDisplyingMessages);
            g2.drawString(message, message_x, message_y);
            //gp.screenHeight/5 - gp.tileSize/2, gp.screenWidth/5 - gp.tileSize/2
            messageTime++;
            if(messageTime > 12){
                messageTime=0;
                messageOn=false;
            }
        }
        g2.setColor(orangeForInter);
    }
    public void showMessage(String text, int x, int y, int message_fs){
        message = text;
        messageOn = true;
        message_x = x;
        message_y = y;
        message_font_size = message_fs;
    }
}
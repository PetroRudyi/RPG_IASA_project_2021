package Window;

import java.awt.*;

public class Interface  {
    GamePanel gp;
    Font arial20_font;
    Color orangeForInter;
    public static Color violetForDisplyingMessages =  new Color(  81, 0, 61 );
    public static Color malynovy = new Color(  158, 5, 52 );
    public boolean messageOn = false, messageOn2 = false;
    public String message = "";
    public String message2 = "";
    int messageTime = 0, messageTime2 = 0;;
    int message_x, message_y, message_x2, message_y2;
    int message_font_size, message_font_size2;
    Color message_color, message_color2;

    public Interface(GamePanel gp){
        this.gp = gp;
        arial20_font = new Font("Arial", Font.BOLD, 20);
        orangeForInter = new Color(  234, 56, 1 ); // 158, 5, 52 malynovyy



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
            g2.setColor(message_color);
            g2.drawString(message, message_x, message_y);
            messageTime++;
            if(messageTime > 50){
                messageTime=0;
                messageOn=false;
            }
        }
        if (messageOn2) {
            g2.setFont(g2.getFont().deriveFont(message_font_size2));
            g2.setColor(message_color2);
            g2.drawString(message2, message_x2, message_y2);
            messageTime2++;
            if(messageTime2 > 50){
                messageTime2=0;
                messageOn2=false;
            }
        }
        g2.setColor(orangeForInter);
    }
    public void showMessage(String text, int x, int y, int message_fs, Color color){
        message = text;
        messageOn = true;
        message_x = x;
        message_y = y;
        message_font_size = message_fs;
        message_color = color;
    }
    public void showMessage2(String text, int x, int y, int message_fs, Color color){
        message2 = text;
        messageOn2 = true;
        message_x2 = x;
        message_y2 = y;
        message_font_size2 = message_fs;
        message_color2 = color;
    }
}
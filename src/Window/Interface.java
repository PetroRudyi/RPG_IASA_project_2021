package Window;

import Objects.Entity.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Interface {
    GamePanel gp;
    Font arial20_font, arial80_font;
    Color orangeForInter;
    public static Color violetForDisplyingMessages = new Color(81, 0, 61);
    public static Color malynovy = new Color(158, 5, 52);
    public boolean messageOn = false, messageOn2 = false;
    public String message = "";
    public String message2 = "";
    int messageTime = 0, messageTime2 = 0;
    ;
    int message_x, message_y, message_x2, message_y2;
    int message_font_size, message_font_size2;
    Color message_color, message_color2;
    public static boolean gameFinished = false;

    public Interface(GamePanel gp) {
        this.gp = gp;
        arial20_font = new Font("Arial", Font.BOLD, 20);
        orangeForInter = new Color(234, 56, 1); // 158, 5, 52 malynovyy
        arial80_font = new Font("Arial", Font.BOLD, 80);

    }//234, 56, 1 orange good  81, 0, 61 violate

    public void draw(Graphics2D g2) {
        if (gp.gameState == gp.menu) {
            drawTitleScreen(g2);
        }
        if (gp.gameState == gp.play) {

            // Font.PLAIN, number - is the size of text
            if (gameFinished) {
                String text = "You've found " + Settings.player.coin + " coins";
                int l, x, y = gp.screenHeight / 2;
                l = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
                x = gp.screenWidth / 2 - l / 2;
                g2.setFont(arial20_font);
                g2.setColor(malynovy);
                g2.drawString(text, x, y);

                l = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
                x = gp.screenWidth / 2 - l / 2;
                y = gp.screenHeight / 2 + gp.tileSize * 2;
                g2.setFont(arial80_font);
                g2.setColor(orangeForInter);
                g2.drawString("THE END", x, y);
                gp.gameThread = null;

            } else {
                g2.setFont(arial20_font);
                g2.setColor(orangeForInter);
                g2.drawString("Coins: " + gp.player.coin, 25, 25);
                g2.drawString("x,y: (" + Settings.PlayerX + ";" + Settings.PlayerY + ")", gp.screenWidth - 170, 25);
                g2.drawString("XP: " + gp.player.HP + "|" + gp.player.MaxHP, 25, 50);
                g2.drawString("Lives: " + Settings.player_lives, 25, 75);


                if (messageOn) {
                    g2.setFont(g2.getFont().deriveFont(message_font_size));
                    g2.setColor(message_color);
                    g2.drawString(message, message_x, message_y);
                    messageTime++;
                    if (messageTime > 50) {
                        messageTime = 0;
                        messageOn = false;
                    }
                }
                if (messageOn2) {
                    g2.setFont(g2.getFont().deriveFont(message_font_size2));
                    g2.setColor(message_color2);
                    g2.drawString(message2, message_x2, message_y2);
                    messageTime2++;
                    if (messageTime2 > 50) {
                        messageTime2 = 0;
                        messageOn2 = false;
                    }
                }
                g2.setColor(orangeForInter);
            }
        }
    }

    public void drawTitleScreen(Graphics2D g2) {
        BufferedImage im, im2;
        try {
            im = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/menu/game1.jpg")));
            im2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/menu/game2.jpg")));

            g2.drawImage(im2, gp.screenWidth / 3, gp.screenHeight / 3, gp.screenWidth / 2, gp.screenHeight / 2, Color.black, null);
            g2.drawImage(im, 100, 200, gp.screenWidth / 2-gp.tileSize-50, gp.screenHeight / 2-gp.tileSize, Color.black,null);

        } catch (IOException e) {
            e.printStackTrace();
        }
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "2D RPG Game";
        int l = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - l / 2;
        int y = gp.tileSize * 3;
        g2.setColor(Color.red);
        g2.drawString(text, x+2, y+2);
        g2.setColor(orangeForInter);
        g2.drawString(text, x, y);


        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 16F));

        String text2 = "Made by Sofia Shaposhnikova & Piter Rudyy";
        y = gp.screenHeight - gp.tileSize * 2;
        x = 25;

//        g2.setColor(Color.red);
//        g2.drawString(text2, x+1, y+1);
        g2.setColor(Color.WHITE);
        g2.drawString(text2, x, y);

        String  text3 = "Press Enter";
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 64F));
        int l2 = (int) g2.getFontMetrics().getStringBounds(text3, g2).getWidth();
        int x2 = gp.screenWidth / 2 - l2/ 2;
        int y2 = gp.screenHeight/ 2 ;

        g2.setColor(Color.red);
        g2.drawString(text3, x2+2, y2+2);
        g2.setColor(orangeForInter);
        g2.drawString(text3, x2, y2);

        String  text4 = "Start";
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 64F));
        int l3 = (int) g2.getFontMetrics().getStringBounds(text4, g2).getWidth();
        int x3 = gp.screenWidth / 2 - l3/ 2;
        int y3 = gp.screenHeight/ 2  + 80;

        g2.setColor(Color.red);
        g2.drawString(text4, x3+2, y3+2);
        g2.setColor(orangeForInter);
        g2.drawString(text4, x3, y3);
    }

    public void showMessage(String text, int x, int y, int message_fs, Color color) {
        message = text;
        messageOn = true;
        message_x = x;
        message_y = y;
        message_font_size = message_fs;
        message_color = color;
    }

    public void showMessage2(String text, int x, int y, int message_fs, Color color) {
        message2 = text;
        messageOn2 = true;
        message_x2 = x;
        message_y2 = y;
        message_font_size2 = message_fs;
        message_color2 = color;
    }
}
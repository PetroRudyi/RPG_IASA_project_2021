package Window;

import Objects.Build.Build;
import Objects.Entity.Entity;
import Objects.Entity.Player;
import Objects.Entity.Slime;

import java.awt.*;
import java.util.ArrayList;

public class Settings {
    public static int p=10;
    public static int k=99;



    //Player stats
    public static int PlayerX=99;
    public static int PlayerY=99;
    public static Player player;
    public static int player_lives = 3;
    //free space for spawn
    public static ArrayList<ArrayList<int[]>> xyisland;
    public static int[] rating;

    //world map
    public static int[][] mapTileNum;
    //character speed
    //public static int speed; //   //(Дуже крута ідея ходьби)

    public static ArrayList<Entity> Mobs=new ArrayList<>();

    public static ArrayList<Build> Builds=new ArrayList<>();

    public static void setCords(int X, int Y){
        PlayerX = X;
        PlayerY = Y;
        player.worldX=X;
        player.worldY=Y;
    }

    public static int[] getSpawnCord(){
        int i = rating[0];
        int k = (int)(Math.random()*xyisland.get(i).size());
        int[] a = new int[2];
        a = (xyisland.get(i).get(k));
        xyisland.get(i).remove(k);
        return a;
    }
}

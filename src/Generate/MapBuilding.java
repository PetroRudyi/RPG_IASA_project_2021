package Generate;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;


public class MapBuilding {



//k - это количество блоков, тоесть клеток по ширине/высоте (которіе кратніе k)
    public static int[][] pixilate(int weight,int height, int k, int[][] map) {
        int[][] g = new int[weight][height];
        for (int y = 0; y < height; y += k) {
            for (int x = 0; x < weight; x += k) {
                // Обрезка фото чтобы вытащить цвет пикселя
                int[][] pixelMap = getPixelMap(map, weight, height ,x, y, k, k);
                int dominantColor = getDominantColor(pixelMap, k,k);
                // Закрашиваем этот самый пиксель
                for (int yd = y; (yd < y + k) && (yd < height); yd++) {
                    for (int xd = x; (xd < x + k) && (xd < weight); xd++) {
                        g[xd][yd] = dominantColor;
                    }
                }
            }
        }
        return g;

    }

    public static int[][] getPixelMap(int[][] x, int weight,int height,int startx, int starty, int kw, int kh) {
        if (startx < 0) startx = 0;
        if (starty < 0) starty = 0;
        if (startx > weight) startx = weight;
        if (starty > height) starty = height;
        if (startx + kw > weight) kw = weight - startx;
        if (starty + kh > height) kh = height - starty;

        int[][] g = new int[kw][kw];
        for(int i = startx; i<(startx+kw);i++){
            for(int j = starty; j<(starty+kh);j++){
                g[i-startx][j-starty]=x[i][j];
            }
        }

        return g;
    }

    //Выделяем цвет. Записываем все в Хашмапу считая количество определенных цветов
    public static int getDominantColor(int[][] m, int weight, int height) {
        Map<Integer, Integer> colorCounter = new HashMap<>();
        for (int x = 0; x < weight; x++) {
            for (int y = 0; y < height; y++) {
                int currentRGB = m[x][y];
                int count = colorCounter.getOrDefault(currentRGB, 0);
                colorCounter.put(currentRGB, count + 1);
            }
        }
        return getDominantColor(colorCounter);
    }

    private static int getDominantColor(Map<Integer, Integer> colorCounter) {
        int dominantRGB = colorCounter.entrySet().stream()
                .max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1)// Stream API и Тернарная операция и лямбда выражение и https://metanit.com/java/tutorial/10.11.php  и вообще https://t.me/include_anime/27512 тут все обьяснено (Просите Петю, он перекинет вам)
                .get()
                .getKey();
        return dominantRGB;
    }

    public static int[][] setID(int[][] map,int weight, int height, byte set){
        int[][] g = new int[weight][height];
        switch(set){
            case (0):
                for(int x=0;x<weight;x++){
                    for (int y=0;y<height;y++){
                        //System.out.printf("%d%n", map[x][y]);
                        g[x][y]=setIDFirstSet(map[x][y]);
                        //System.out.printf("Цвет поменяли %d%n", g[x][y]);
                    }
                }
                break;
            case (1):
                for(int x=0;x<weight;x++){
                    for (int y=0;y<height;y++){
                        //System.out.printf("%d%n", map[x][y]);
                        g[x][y]=setIDSecondSet(map[x][y]);
                        //System.out.printf("Цвет поменяли %d%n", g[x][y]);
                    }
                }
                break;
        }
        return g;
    }

    public static int setIDFirstSet(int x) {
        int i=0;
        if (x < 50) {//коричневый (тип земля)
            i=  0;
        }
        else if(x<100){//песочный
            i=  1;
        }
        else if(x<150){//зеленый
            i=  2;
        }
        else if(x<200){//серый
            i=  3;
        }
        else i=  4;//черный
        /*if (i < 50) {//коричневый (тип земля)
            i=  65536 *139+256*69+19;
        }
        else if(i<100){//песочный
            i=  65536 *240+256*230+140;
        }
        else if(i<150){//зеленый
            i=  65536 *152+256*251+152;
        }
        else if(i<200){//серый
            i=  65536 *105+256*105+105;
        }
        else i=  65536 *0+256*0+0;*/
        return i;
    }
    public static int setIDSecondSet(int x) {
        int i=0;

        if (x < 70) {//коричневый (тип земля)
            i=  0;
        }
        else if(x<110){//песочный
            i=  1;
        }
        else if(x<170){//зеленый
            i=  2;
        }
        else if(x<210){//серый
            i=  3;
        }
        else i=  4;//черный
        return i;

    }

    public static int numIslands(int[][] grid, int p) {
        //ArrayList<ArrayList<Integer[][]>> xyisland = new ArrayList<ArrayList<Integer[][]>>();
        int m = grid.length;
        int n = grid[0].length;
        short island = 0;
        for (int i =0; i<m;i+=p){
            for (int j = 0; j < n; j+=p){
                if(checkWalk(grid[i][j])){
                    //xyisland.add(new ArrayList<Integer[][]>());
                    //xyisland.get(island).add(new Integer[1][1]);
                    island++;
                    checkIsland(grid, m, n, i, j,p);
                }
            }
        }
        return island;
    }

    static boolean checkWalk(int c){
        boolean g;
        switch (c) {
            case (3), (4) -> g = false;
            case (0), (1), (2) -> g = true;
            default -> throw new IllegalStateException("Unexpected value: " + c);
        }
        return g;
    }

    static void checkIsland(int[][] grid, int m, int n, int i, int j, int p){

        if ((i<0)||(j<0)||(i>=m)||(j>=n)||(!checkWalk(grid[i][j]))){
            return;
        }

        grid[i][j]=4;
        checkIsland(grid, m, n, i+p, j,p);
        checkIsland(grid, m, n, i, j+p,p);
        checkIsland(grid, m, n, i-p, j,p);
        checkIsland(grid, m, n, i, j-p,p);
    }


    public static void main(String[] args) {
        int a=0;
        String msg = "Петя лох ";

        while (true) {
            System.out.println(msg + ++a);
        }
    }
}

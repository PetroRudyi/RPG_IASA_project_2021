package Generate;
import java.awt.image.BufferedImage;
import java.util.Vector;
/*
Баги :noise выводит неполные значения, гдето до 240 из 255


*/

public class MyVectorNoise {


    int width;
    int height;
    double seed; // от 0 до 100 при генерации в конструкторе умолчания                    double: ±4.9*10-324 до ±1.8*10308
    double max;
    static int k;
    private static double[][] g;

    public MyVectorNoise(int w, int h) {
        width = w;
        height = h;
        max = 0;
        k=10;
        seed = Math.random() * 100;
        g = new double[1+(w/11)][1+(h/11)];
        for (int i=0;i<1+(w/11);i++){
            for (int j=0;j<1+(h/11);j++) {
                g[i][j] = -1;
            }
        }
        //image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }


    public MyVectorNoise(int w, int h, double s, int ki) {
        width = w;
        height = h;
        seed = s;
        max = 0;
        k=ki;
        g = new double[1+w/(k+1)][1+h/(k+1)];
        for (int i=0;i<1+w/(k+1);i++){
            for (int j=0;j<1+h/(k+1);j++) {
                g[i][j] = -1;
            }
        }
        //image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    public MyVectorNoise(int w, int h, int ki) {
        width = w;
        height = h;
        seed = Math.random() * 100;
        max = 0;
        k=ki;
        g = new double[1+w/(k+1)][1+h/(k+1)];
        for (int i=0;i<1+w/(k+1);i++){
            for (int j=0;j<1+h/(k+1);j++) {
                g[i][j] = -1;
            }
        }
        //image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    //public BufferedImage getNoiseImage() { //double[] getNoise(){
    public int[][] getNoise() { //double[] getNoise(){
        int[][] noiseMap = new int[width][height];
        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++) {
                //double dx = (double) x / width; //MainWindow.height было это
                //double dy = (double) y / height;
                //int frequency = 6; //частота(?) 6
                //double noise = noise((dx * frequency) + seed, (dy * frequency) + seed);
                double noise = noise (x,y);
                //noise = (-256) * (noise - 1) / (2 * 231);


                noise = ((-256)*(noise - 1) / (2*231))*((double)256/230);
                System.out.printf("%f%n", noise);
                if ((int)(noise * 0xFF) > max) {
                    max = (int)(noise * 0xFF);
                }

                /*int b = (int) (noise * 0xFF);
                System.out.printf("%d%n",b);
                int g = (b * 0x100);
                int r = (b * 0x10000);
                //System.out.printf("%d%n",r);*/



                noiseMap[x][y] = (int)(noise * 0xFF);
            }
        }

        System.out.println(max);
        return noiseMap;
    }

    private static double noise(int x, int y) {
/*
В чем смысл
Мы делим всю плоскость на квадраты со сторой k+1 и в матрицу g записываем вектора которые находятся на краях этих больших квадратов
Потом считаем градиент точки (x y) с каждым вектором
потом можно влепить сглаживание на основе x^3(x(x*6-15)+10)
и посчитать линейную интерполяцию

*/

        int xid = x/(k+1); // ПОЛУЧАЕМ ДЕЛЕНИЕ НА k+1
        int yid = y/(k+1); //узнаем какой именно это Большой квадрат

        int xi = x%(k+1); // кординаты точек внутри большого квадрата от 0 до k
        int yi = y%(k+1);
        double xd = ((double)xi)/(k); //так как у нас болшой квадрат условно 1 на 1, то все точки получают кординаты от 0 до 1)
        double yd = ((double)yi)/(k);

        double g1= vector(xid,yid); // углы еденичных! векторов относительно оси х
        double g2= vector(xid+1,yid);
        double g3= vector(xid,yid+1);
        double g4= vector(xid+1,yid+1);

//        double d1 = grad(xd,yd,g1);
//        double d2 = grad(xd,yd,g2);
//        double d3 = grad(xd,yd,g3);
//        double d4 = grad(xd,yd,g4);
        double d1 = grad(xd,yd,g1);
        double d2 = grad(xd-1,yd,g2);
        double d3 = grad(xd,yd-1,g3);
        double d4 = grad(xd-1,yd-1,g4);

        double u = fade(xd);
        double v = fade(yd);

        double x1Inter = inter(d1,d2,u);
        double x2Inter = inter(d3,d4,u);
        double yInter = inter(x1Inter,x2Inter,v);

        return yInter;
        }


    private static double vector(int x, int y){
        if(g[x][y]==-1){
            g[x][y] = Math.random() * 2*Math.PI;
        }
        return g[x][y];
    }

    private static double grad(double x, double y, double gi){
        double l = x*Math.cos(gi)+y*Math.sin(gi);
        if (l>1){
            return 1;
        }
        else if (l<-1){
            return -1;
        }
        else{
            return l;
        }
    }

    private static double fade(double x) {
        return  x * x * x * (x * (x * 6 - 15) + 10); //x * x * x * (x * (x * 6 - 15) + 10);   x*(x*(x*3-4)+2)
    }

    private static double inter(double g1, double g2, double u){
        return g1*(1-u)+g2*u;
    }

};
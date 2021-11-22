package Generate;
import java.awt.image.BufferedImage;
import java.util.Vector;

public class MyVectorNoice {


    int WIDTH;
    int HEIGHT;
    double seed; // от 0 до 100 при генерации в конструкторе умолчания                    double: ±4.9*10-324 до ±1.8*10308
    int max;
    private static int k;
    private static BufferedImage image;
    private static double[][] g;
    public MyVectorNoice(int w, int h) {
        WIDTH = w;
        HEIGHT = h;
        max = 0;
        k=0;
        seed = Math.random() * 100;
        double g[][] = new double[1+(w/10+1)][1+(h/10+1)];
        for (int i=0;i<1+(w/10);i++){
            for (int j=0;j<1+(h/10);j++) {
                g[i][j] = -1;
            }
        }
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    }


    public MyVectorNoice(int w, int h, double s, int k) {
        WIDTH = w;
        HEIGHT = h;
        seed = s;
        max = 0;
        double g[][] = new double[1+w/(k+1)][1+h/(k+1)];
        for (int i=0;i<1+w/(k+1);i++){
            for (int j=0;j<1+h/(k+1);j++) {
                g[i][j] = -1;
            }
        }
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    }

    public BufferedImage getNoiseImage() { //double[] getNoise(){
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                double dx = (double) x / WIDTH; //MainWindow.HEIGHT было это
                double dy = (double) y / HEIGHT;
                int frequency = 6; //частота(?) 6
                //double noise = noise((dx * frequency) + seed, (dy * frequency) + seed);
                double noise = noise (x,y);
                //noise = (-256) * (noise - 1) / (2 * 231);
                noise = (noise+1)*128;
                System.out.printf("%f%n", noise);


                /*int b = (int) (noise * 0xFF);
                System.out.printf("%d%n",b);
                int g = (b * 0x100);
                int r = (b * 0x10000);
                //System.out.printf("%d%n",r);*/

                if ((int) (noise * 0xFF) > max) {
                    max = (int) (noise * 0xFF);
                }
                int r = 65536 * (int) (noise * 0xFF) + 256 * (int) (noise * 0xFF) + (int) (noise * 0xFF);
                System.out.printf("%d%n", (int) (noise * 0xFF));
                int alpha = (r >> 24) & 0xff;

                int red = (r >> 16) & 0xff;

                int green = (r >> 8) & 0xff;

                int blue = (r) & 0xff;

                System.out.println("ARGB : " + alpha + ", " + red + ", " + green + ", " + blue);
                int finalValue = r;


                image.setRGB(x, y, finalValue);
            }
        }

        System.out.println(max);
        return image;
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

        double d1 = grad(xd,yd,g1);
        double d2 = grad(xd,yd,g2);
        double d3 = grad(xd,yd,g3);
        double d4 = grad(xd,yd,g4);

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
        return x*Math.cos(gi)+y*Math.sin(gi);
    }

    private static double fade(double x) {
        return x * x * x * (x * (x * 6 - 15) + 10);
    }

    private static double inter(double g1, double g2, double u){
        return g1*(1-u)+g2*u;
    }

};
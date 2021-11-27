package Generate;
import java.awt.image.BufferedImage;

public class Generate {
    int[][] map;
    int width;
    int height;


    public Generate(int w, int h, int k, int p){
        this.map = new int[w][h];
        this.width=w;
        this.height = h;

        MyVectorNoise(k);
        Pixelation(p);
        this.map=MapBuilding.setID(map,w,h,(byte)1);
    }

    void MyVectorNoise (int k){
        MyVectorNoise noise = new MyVectorNoise(this.width,this.height,k);
        this.map = noise.getNoise();
    }

    void Pixelation (int k){
        this.map = MapBuilding.pixilate(this.width,this.height,k,this.map);
    }

    public int[][] getMap(){
        return this.map;
    }

    public BufferedImage getMapImage() {
        //int max = 0;
        BufferedImage image = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {

                /*if (this.map[x][y] > max) {
                    max = this.map[x][y];
                }*/

                int finalValue = 65536 *this.map[x][y]+256*this.map[x][y]+this.map[x][y];
                /*int alpha = (r >> 24) & 0xff;

                int red = (r >> 16) & 0xff;

                int green = (r >> 8) & 0xff;

                int blue = (r) & 0xff;

                System.out.println("ARGB : " + alpha + ", " + red + ", " + green + ", " + blue);
                */
                image.setRGB(x, y, finalValue);
            }
        }
        //System.out.println(max);
        return image;
    }

    public BufferedImage getMapIDImage() {
        //int max = 0;
        BufferedImage image = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {

                /*if (this.map[x][y] > max) {
                    max = this.map[x][y];
                }*/
                System.out.printf("Цвет ID %d%n", map[x][y]);

                int finalValue = switch (this.map[x][y]) {
                    case (0) -> 65536 * 139 + 256 * 69 + 19;
                    case (1) -> 65536 * 240 + 256 * 230 + 140;
                    case (2) -> 65536 * 152 + 256 * 251 + 152;
                    case (3) -> 65536 * 105 + 256 * 105 + 105;
                    case (4) -> 0;
                    default -> 0;
                };

                //int finalValue = 65536 *this.map[x][y]+256*this.map[x][y]+this.map[x][y];
                /*int alpha = (r >> 24) & 0xff;

                int red = (r >> 16) & 0xff;

                int green = (r >> 8) & 0xff;

                int blue = (r) & 0xff;

                System.out.println("ARGB : " + alpha + ", " + red + ", " + green + ", " + blue);
                */
                image.setRGB(x, y, finalValue);
            }
        }
        //System.out.println(max);
        return image;
    }
}

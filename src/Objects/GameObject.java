package Objects;

import java.awt.*;
import java.io.IOException;

public abstract class GameObject {
    public int worldX, worldY;


    public abstract void update() throws IOException;

    //public abstract void draw(Graphics2D g2);
}

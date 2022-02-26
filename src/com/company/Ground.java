package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Ground {
    int ground_x, ground_y;
    BufferedImage grBufferImage;

    public Ground() {
        super();
        File grImage = new File("D:/ECLIPSEWORKSPACE/flyingbird/src/images/ground.png");
        try {
            grBufferImage = ImageIO.read(grImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ground_y = 500;
    }

    public void move() {
        ground_x--;
        if (ground_x < -110) ground_x = 0;
    }
}

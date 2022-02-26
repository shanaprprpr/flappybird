package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bird {
    int bird_x = 60, bird_y = 300;
    int bird_width, bird_height;
    double speed = 20;
    double g = 4;
    double s;
    double t = 0.3;
    BufferedImage biBufferImage;
    BufferedImage[] images = new BufferedImage[8];
    int bird_icon = 0;

    public Bird() {
        super();
        for (int i = 0; i < images.length; i++) {
            File biImage = new File("D:/ECLIPSEWORKSPACE/flyingbird/src/images/" + i + ".png");
            try {
                images[i] = ImageIO.read(biImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        biBufferImage = images[0];
        bird_width = biBufferImage.getWidth();
        bird_height = biBufferImage.getHeight();
    }

    int index = 0;

    public void change() {
        index++;
        biBufferImage = images[index / 3 % 8];
    }

    double ratation;

    public void move_go() {
        double v0 = speed;
        s = v0 * t - 0.5 * g * t * t;
        speed = v0 - g * t;
        bird_y = bird_y - (int) s;
        ratation = s / 16;
        if (bird_y <= bird_height / 2) bird_y = bird_height / 2;
    }

    public void refly() {
        speed = 20;
    }

    public boolean hit(Ground ground) {
        return bird_y + bird_height / 2 >= ground.ground_y;
    }

    public boolean hit(Column column) {
        int left_x = column.column_x - column.width / 2 - bird_width / 2;
        int right_x = column.column_x + column.width / 2 + bird_width / 2;
        int top_y = column.column_y - column.gap / 2 + bird_height / 2 - 5;
        int down_y = column.column_y + column.gap / 2 - bird_height / 2 + 5;
        if (bird_x > left_x && bird_x < right_x) {
            return bird_y <= top_y || bird_y >= down_y;
        } else {
            return false;
        }
    }
}

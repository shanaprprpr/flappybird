package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Column {
    int column_x, column_y;
    int width, height;
    int gap = 140;
    Random random = new Random();
    BufferedImage coBufferImage;
    public Column(int x){
        super();
        File coImage = new File("D:/ECLIPSEWORKSPACE/flyingbird/src/images/column.png");
        try{
            coBufferImage = ImageIO.read(coImage);
        }catch (IOException e){
            e.printStackTrace();
        }
        column_x = x;
        column_y = random.nextInt(180) + 150;
        width = coBufferImage.getWidth();
        height = coBufferImage.getHeight();
    }
    public void move(){
        column_x--;
        if (column_x < -width / 2){
            column_y = random.nextInt(180) + 150;
            column_x = 432 + width / 2;
        }
    }
}

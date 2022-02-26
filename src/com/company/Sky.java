package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serial;

public class Sky extends JPanel {
    @Serial
    private static final long serialVersionUID = 1L;
    BufferedImage bgBufferImage;
    Ground ground = new Ground(); // 地面
    Column column = new Column(350); // 钢管
    Column column2 = new Column(600); // 钢管
    static Bird bird = new Bird(); // 小鸟
    int score = 0; // 游戏得分
    BufferedImage startBufferImage; // 开始准备界面
    boolean isStart; // 是否开始游戏
    BufferedImage overBufferImage; // 游戏结束界面
    boolean isOver; // 游戏是否结束

    public Sky() {
        super();
        // 读取图片
        File bgImage = new File("D:/ECLIPSEWORKSPACE/flyingbird/src/images/bg.png");
        File starImage = new File("D:/ECLIPSEWORKSPACE/flyingbird/src/images/start.png");
        File overImage = new File("D:/ECLIPSEWORKSPACE/flyingbird/src/images/gameover.png");
        try {
            bgBufferImage = ImageIO.read(bgImage);
            startBufferImage = ImageIO.read(starImage);
            overBufferImage = ImageIO.read(overImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics graphics) {
        graphics.drawImage(bgBufferImage, 0, 0, null);
        Graphics2D gg = (Graphics2D) graphics;
        gg.rotate(-bird.ratation, bird.bird_x, bird.bird_y);
        graphics.drawImage(bird.biBufferImage, bird.bird_x - bird.bird_width / 2, bird.bird_y - bird.bird_height / 2, null);
        gg.rotate(bird.ratation, bird.bird_x, bird.bird_y);
        // 画钢管
        graphics.drawImage(column.coBufferImage, column.column_x - column.width / 2, column.column_y - column.height / 2, null);
        graphics.drawImage(column2.coBufferImage, column2.column_x - column2.width / 2, column2.column_y - column2.height / 2, null);
        // 画地面
        graphics.drawImage(ground.grBufferImage, ground.ground_x, ground.ground_y, null);
        graphics.setColor(Color.BLUE);
        graphics.setFont(new Font("楷体", Font.ITALIC, 30));
        graphics.drawString("分数：" + score, 100, 600);
        if (!isStart && !isOver) {
            graphics.drawImage(startBufferImage, 0, 0, null);
        }
        // 画结束界面
        if (isOver) {
            graphics.drawImage(overBufferImage, 0, 0, null);
        }
    }

    public void action() {
        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                checkIsOver();
            }
        };
        this.addMouseListener(adapter);
        // 添加键盘监听器
        KeyAdapter keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char charA = e.getKeyChar();
                if (charA == 'w') {
                    if (bird.bird_y > 20) {
                        bird.bird_y -= 20;
                    }
                } else if (charA == 's') {
                    if (bird.bird_y < 465) {
                        bird.bird_y += 20;
                    }
                } else if (charA == 'a') {
                    if (bird.bird_x > 20) {
                        bird.bird_x -= 20;
                    }
                } else if (charA == 'd') {
                    if (bird.bird_x < 395) {
                        bird.bird_x += 20;
                    }
                } else if (charA == ' ') {
                    /*
                     * 若游戏结束重新开始游戏,游戏恢复初始状态
                     * 若未结束:鸟飞起来
                     */
                    checkIsOver();
                }
                super.keyPressed(e);
            }
        };
        this.addKeyListener(keyAdapter);
        this.requestFocus();
        while (true) {
            if (isStart && !isOver) {
                ground.move();
                column.move();
                column2.move();
                bird.change();
                bird.move_go();
            }
            if (bird.bird_x - bird.bird_width / 2 == column.column_x
                    + column.width / 2
                    || bird.bird_x - bird.bird_width / 2 == column2.column_x
                    + column2.width / 2) {
                score++;
            }
            if (bird.hit(ground) || bird.hit(column) || bird.hit(column2)) {
                isStart = false;
                isOver = true;
            }
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            repaint();
        }
    }

    private void checkIsOver() {
        if (isOver) {
            bird = new Bird();
            ground = new Ground();
            column = new Column(350);
            column2 = new Column(600);
            score = 0;
            isOver = false;
            isStart = false;
        } else {
            bird.refly();
            isStart = true;
        }
    }
}

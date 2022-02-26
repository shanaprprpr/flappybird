package com.company;

import javax.swing.*;

public class Test {

    public static void main(String[] args) {
        JFrame jf = new JFrame("bird_game");
        jf.setSize(432, 674);
        jf.setAlwaysOnTop(false);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setResizable(false);
        Sky sky = new Sky();
        jf.add(sky);
        jf.setVisible(true);
        sky.action();
    }
}

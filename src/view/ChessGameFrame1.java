package view;

import controller.GameController;

import javax.swing.*;
import java.awt.*;

public class ChessGameFrame1 extends JFrame  {
    private final int WIDTH;
    private final int HEIGTH;
    public static int mode = 0;

    public ChessGameFrame1(int width, int height) {
        setTitle("CHESS GAME"); //设置标题
        this.WIDTH = width;
        this.HEIGTH = height;


        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);

        addLoadButton1();
        addLoadButton2();
        addLoadButton3();
        addLabel() ;
        addBackGround() ;
    }

    private void addLoadButton1() {
        JButton button = new JButton("BATTLE GAME");
        button.setLocation(WIDTH/3-20, HEIGTH/2 - 50 );
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
        button.addActionListener(e -> {
           setVisible(false) ;
           mode = 0;
           ChessGameFrame chessGame = new ChessGameFrame(1000, 760);
           chessGame.setVisible(true) ;
           dispose() ;
        });
    }

    private void addLoadButton2() {
        JButton button = new JButton("SIMPLE AI");
        button.setLocation(WIDTH/3-20, HEIGTH/2 + 50 );
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
        button.addActionListener(e -> {
            setVisible(false) ;
            mode = 1 ;
            ChessGameFrame chessGame = new ChessGameFrame(1000, 760);
            chessGame.setVisible(true) ;
            dispose() ;
        });
    }

    private void addLoadButton3() {
        JButton button = new JButton("GREEDY AI");
        button.setLocation(WIDTH/3-20, HEIGTH/2 + 150 );
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
        button.addActionListener(e -> {
            setVisible(false) ;
            mode = 2 ;
            ChessGameFrame chessGame = new ChessGameFrame(1000, 760);
            chessGame.setVisible(true) ;
            dispose() ;
        });
    }



    private void addLabel() {
        JLabel j = new JLabel("CHESS   GAME");
        j.setLocation(100, 150 );
        j.setSize(300, 90);
        j.setFont(new Font("Rockwell", Font.BOLD, 40));
        j.setForeground(Color.WHITE );
        add(j);
    }

    private void addBackGround(){
        ImageIcon img = new ImageIcon("./images/bj4.png");
        JLabel background = new JLabel(img) ;
        background.setBounds(-180, -80,1000,1000) ;
        add(background ) ;
    }

    public static int getMode() {
        return mode;
    }
}


package view;

import controller.GameController;

import javax.swing.*;
import java.awt.*;

public class ChessGameFrame1 extends JFrame  {
    private final int WIDTH;
    private final int HEIGTH;

    public ChessGameFrame1(int width, int height) {
        setTitle("CHESS GAME"); //设置标题
        this.WIDTH = width;
        this.HEIGTH = height;


        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);

        addLoadButton();
        addLabel() ;
        addBackGround() ;
    }

    private void addLoadButton() {
        JButton button = new JButton("START GAME");
        button.setLocation(WIDTH/3-20, HEIGTH/2 + 100 );
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
        button.addActionListener(e -> {
           setVisible(false) ;
           ChessGameFrame chessGame = new ChessGameFrame(1000, 760);
           chessGame.setVisible(true) ;
           dispose() ;
        });
    }

    private void addLabel() {
        JLabel j = new JLabel("CHESS   GAME");
        j.setLocation(100, 200 );
        j.setSize(300, 90);
        j.setFont(new Font("Rockwell", Font.BOLD, 40));
        j.setForeground(Color.WHITE );
        add(j);
    }

    private void addBackGround(){
        ImageIcon img = new ImageIcon("./images/bj2.png");
        JLabel background = new JLabel(img) ;
        background.setBounds(0, -100,1000,1000) ;
        add(background ) ;
    }
}


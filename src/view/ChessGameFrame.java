package view;

import controller.GameController;

import javax.swing.*;
import java.awt.*;

/**
 * 这个类表示游戏过程中的整个游戏界面，是一切的载体
 */
public class ChessGameFrame extends JFrame {
    //    public final Dimension FRAME_SIZE ;
    private final int WIDTH;
    private final int HEIGTH;
    public final int CHESSBOARD_SIZE;
    private GameController gameController;
    public static JLabel statusLabel;
    private Chessboard chessboard ;

    public ChessGameFrame(int width, int height) {
        setTitle("CHESS GAME"); //设置标题
        this.WIDTH = width;
        this.HEIGTH = height;
        this.CHESSBOARD_SIZE = HEIGTH * 4 / 5;
        this.chessboard = new Chessboard(CHESSBOARD_SIZE, CHESSBOARD_SIZE);

        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);


        addChessboard();
        addLabel();
        addHelloButton();
        addResettingButton();
        addLoadButton();
        addSaveButton() ;
        addBackGround() ;

    }


    /**
     * 在游戏面板中添加棋盘
     */
    private void addChessboard() {
        Chessboard chessboard1 = this.chessboard ;
        gameController = new GameController(chessboard);
        chessboard1.setLocation(HEIGTH / 10, HEIGTH / 10);
//        chessboard1.setVisible(false ) ;
        add(chessboard1);
    }

    private void addBackGround(){
        ImageIcon img = new ImageIcon("./images/bj2.png");
        JLabel background = new JLabel(img) ;
        background.setBounds(0, -100,1000,1000) ;
        add(background ) ;
    }

    /**
     * 在游戏面板中添加标签
     */
    private void addLabel() {
        statusLabel = new JLabel(gameController.getCurrentPlayer());
        statusLabel.setLocation(HEIGTH+50, HEIGTH / 10);
        statusLabel.setSize(300, 90);
        statusLabel.setFont(new Font("Rockwell", Font.BOLD, 30));
        statusLabel.setForeground(Color.WHITE );
        add(statusLabel);
    }


    /**
     * 在游戏面板中增加一个按钮，如果按下的话就会显示Hello, world!
     */

    private void addHelloButton() {
        JButton button = new JButton("Rescue Mom");
        button.addActionListener((e) -> JOptionPane.showMessageDialog(this, "Your mom is fucked by me, and she is gonna die!"));
        button.setLocation(HEIGTH, HEIGTH / 10 + 120);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }

    private void addLoadButton() {
        JButton button = new JButton("Load");
        button.setLocation(HEIGTH, HEIGTH / 10 + 240);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);

        button.addActionListener(e -> {
            System.out.println("Click load");
            String path = JOptionPane.showInputDialog(this,"Input Path here");
            gameController.loadGameFromFile(path);
        });
    }

    private void addSaveButton() {
        JButton button = new JButton("Save");
        button.setLocation(HEIGTH, HEIGTH / 10 + 360);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
        button.addActionListener(e -> {
            this.gameController.saveGameFromFile() ;
            JOptionPane.showMessageDialog(this, "Save Successfully!");
        });
    }
    private void addResettingButton() {
        JButton button = new JButton("Reset Game");
        button.addActionListener((e) -> gameController.initChessboard()  );
        button.setLocation(HEIGTH, HEIGTH / 10 + 480);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }



    public static JLabel getStatusLabel() {
        return statusLabel;
    }

    public static void setStatusLabel(String s) {
        ChessGameFrame.getStatusLabel().setText(s);
    }


}

package view;

import controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;



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
    public static JLabel timeLabel;
    private Chessboard chessboard ;
    public static Stack<List<String>> regret = new Stack<>() ;
    private final List<String> init;
    public static int num = 0;
    public static JLabel background;
    private static javax.swing.Timer timer;

    public ChessGameFrame(int width, int height) {
        setTitle("CHESS GAME"); //设置标题
        this.WIDTH = width;
        this.HEIGTH = height;
        this.CHESSBOARD_SIZE = HEIGTH * 4 / 5;
        this.chessboard = new Chessboard(CHESSBOARD_SIZE, CHESSBOARD_SIZE);
        init = new ArrayList<>();
        init.add("RNBQKBNR");
        init.add("PPPPPPPP");
        init.add("________");
        init.add("________");
        init.add("________");
        init.add("________");
        init.add("pppppppp");
        init.add("rnbqkbnr");
        init.add("w");
        regret.push(init) ;
        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);
        addChessboard();
        addLabel();
        addRegretButton();
        addResettingButton();
        addLoadButton();
        addSaveButton() ;
        addSwitchButton();
        addBackButton();
        addTimeLabel() ;
        addBackGround() ;
        timer = new Timer(1000, e -> {
            changeTime() ;
        });
        timer.start() ;
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
        ImageIcon img = new ImageIcon("./images/bj3.png");
        background = new JLabel(img) ;
        background.setBounds(0, -100,1000,1000) ;
        add(background ) ;
    }

    private void addBackGround2(){
        ImageIcon img = new ImageIcon("./images/bj2.png");
        background = new JLabel(img) ;
        background.setBounds(0, -100,1000,1000) ;
        add(background ) ;
    }

    /**
     * 在游戏面板中添加标签
     */
    private void addLabel() {
        statusLabel = new JLabel(gameController.getCurrentPlayer());
        statusLabel.setLocation(HEIGTH, HEIGTH / 10 - 30);
        statusLabel.setSize(300, 90);
        statusLabel.setFont(new Font("Rockwell", Font.BOLD, 30));
        statusLabel.setForeground(Color.BLACK  );
        add(statusLabel);
    }


    /**
     * 在游戏面板中增加一个按钮，如果按下的话就会显示Hello, world!
     */

    private void addRegretButton() {
        JButton button = new JButton("Regret");
        button.addActionListener(e -> {
            this.chessboard.loadGame(regret.pop()) ;
            timeLabel.setText("15") ;
            repaint() ;
        });
        button.setLocation(HEIGTH, HEIGTH / 10 + 160);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }
    private void addSwitchButton() {
        JButton button = new JButton("Switch");
        button.addActionListener(e -> {
            num++;
            if(num%2 == 1) {
                remove(background) ;
                addBackGround2() ;
                repaint() ;
            }
            else {
                remove(background) ;
                addBackGround() ;
                repaint() ;
            }
        });
        button.setLocation(HEIGTH, HEIGTH / 10 + 60 );
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }

    private void addLoadButton() {
        JButton button = new JButton("Load");
        button.setLocation(HEIGTH, HEIGTH / 10 + 260);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
        button.addActionListener(e -> {
            System.out.println("Click load");
            String path = JOptionPane.showInputDialog(this,"Input Path here");
            gameController.loadGameFromFile(path);
            timeLabel.setText("15") ;
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
        button.addActionListener(e -> {
            gameController.initChessboard();
            regret.init() ;
            timeLabel.setText("15") ;
        }  );
        button.setLocation(HEIGTH, HEIGTH / 10 + 460);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }

    private void addBackButton() {
        JButton button = new JButton("Back");
        button.addActionListener(e -> {
            setVisible(false) ;
            ChessGameFrame1 chessGame = new ChessGameFrame1(500, 750);
            chessGame.setVisible(true) ;
            timer.stop() ;
            dispose() ;
        }  );
        button.setLocation(HEIGTH , HEIGTH / 10 + 560);
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

    public static Stack<List<String>> getRegret() {
        return regret;
    }

    public static char ChooseF(){
        Object[] possibilities = {"Queen","Bishop", "Knight","Rook"};
        String s = String.valueOf(JOptionPane.showInputDialog(null, "Choose a chess", "CF", JOptionPane.QUESTION_MESSAGE, null, possibilities, possibilities[0]));
        switch (s){
            case "Queen": return 'Q';
            case "Bishop": return 'B';
            case "Rook": return 'R';
            case "Knight":return 'K';
        }
        return 0;
    }

    public void addTimeLabel(){
        timeLabel = new JLabel("15");
        timeLabel.setLocation(HEIGTH + 120,HEIGTH / 10 - 15) ;
        timeLabel.setSize(200,60) ;
        timeLabel.setFont(new Font("Rockwell", Font.BOLD ,30) ) ;
        add(timeLabel);
//        javax.swing.Timer timer=new Timer(1000, e -> {
//            changeTime() ;
//        });
//        timer.start() ;
    }

    public void changeTime(){
        Integer num = Integer.parseInt(timeLabel.getText())-1  ;
        timeLabel.setText(num.toString() ) ;
        if(num == -1){
            gameController.getChessboard().swapColor();
            num = 15;
            timeLabel.setText(num.toString() ) ;
        }
    }

    public static JLabel getTimeLabel() {
        return timeLabel;
    }
}

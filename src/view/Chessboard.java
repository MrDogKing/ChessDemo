package view;


import model.*;
import controller.ClickController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 这个类表示面板上的棋盘组件对象
 */
public class Chessboard extends JComponent {
    /**
     * CHESSBOARD_SIZE： 棋盘是8 * 8的
     * <br>
     * BACKGROUND_COLORS: 棋盘的两种背景颜色
     * <br>
     * chessListener：棋盘监听棋子的行动
     * <br>
     * chessboard: 表示8 * 8的棋盘
     * <br>
     * currentColor: 当前行棋方
     */
    private static final int CHESSBOARD_SIZE = 8;

    private final ChessComponent[][] chessComponents = new ChessComponent[CHESSBOARD_SIZE][CHESSBOARD_SIZE];
    private ChessColor currentColor = ChessColor.WHITE ;
    //all chessComponents in this chessboard are shared only one model controller
    private final ClickController clickController = new ClickController(this,ChessGameFrame1.getMode() );
    private final int CHESS_SIZE;
    private String winner = new String();


    public Chessboard(int width, int height) {
        setLayout(null); // Use absolute layout.
        setSize(width, height);
        CHESS_SIZE = width / 8;
        System.out.printf("chessboard size = %d, chess size = %d\n", width, CHESS_SIZE);

        initiateEmptyChessboard();
        // FIXME: Initialize chessboard for testing only.
        initRookOnBoard(0, 0, ChessColor.BLACK);
        initRookOnBoard(0, CHESSBOARD_SIZE - 1, ChessColor.BLACK);
        initRookOnBoard(CHESSBOARD_SIZE - 1, 0, ChessColor.WHITE);
        initRookOnBoard(CHESSBOARD_SIZE - 1, CHESSBOARD_SIZE - 1, ChessColor.WHITE);
        initBishopOnBoard(0, 2, ChessColor.BLACK);
        initBishopOnBoard(0, CHESSBOARD_SIZE - 3, ChessColor.BLACK);
        initBishopOnBoard(CHESSBOARD_SIZE - 1, 2, ChessColor.WHITE);
        initBishopOnBoard(CHESSBOARD_SIZE - 1, CHESSBOARD_SIZE - 3, ChessColor.WHITE);
        initKnightOnBoard(0, 1, ChessColor.BLACK) ;
        initKnightOnBoard(0, CHESSBOARD_SIZE - 2, ChessColor.BLACK);
        initKnightOnBoard(CHESSBOARD_SIZE - 1, 1, ChessColor.WHITE);
        initKnightOnBoard(CHESSBOARD_SIZE - 1, CHESSBOARD_SIZE - 2, ChessColor.WHITE);
        initQueenOnBoard(0, 3, ChessColor.BLACK);
        initQueenOnBoard(CHESSBOARD_SIZE - 1, 3, ChessColor.WHITE);
        initKingOnBoard(0, 4, ChessColor.BLACK);
        initKingOnBoard(CHESSBOARD_SIZE - 1, 4, ChessColor.WHITE);
        for (int i = 0; i < CHESSBOARD_SIZE ; i++) {
            initPawnOnBoard(1,i,ChessColor.BLACK) ;
        }
        for (int i = 0; i < CHESSBOARD_SIZE ; i++) {
            initPawnOnBoard(6,i,ChessColor.WHITE ) ;
        }
    }

    //初始化棋盘
    public void initBoard(){
        initiateEmptyChessboard();
        // FIXME: Initialize chessboard for testing only.
        initRookOnBoard(0, 0, ChessColor.BLACK);
        initRookOnBoard(0, CHESSBOARD_SIZE - 1, ChessColor.BLACK);
        initRookOnBoard(CHESSBOARD_SIZE - 1, 0, ChessColor.WHITE);
        initRookOnBoard(CHESSBOARD_SIZE - 1, CHESSBOARD_SIZE - 1, ChessColor.WHITE);
        initBishopOnBoard(0, 2, ChessColor.BLACK);
        initBishopOnBoard(0, CHESSBOARD_SIZE - 3, ChessColor.BLACK);
        initBishopOnBoard(CHESSBOARD_SIZE - 1, 2, ChessColor.WHITE);
        initBishopOnBoard(CHESSBOARD_SIZE - 1, CHESSBOARD_SIZE - 3, ChessColor.WHITE);
        initKnightOnBoard(0, 1, ChessColor.BLACK) ;
        initKnightOnBoard(0, CHESSBOARD_SIZE - 2, ChessColor.BLACK);
        initKnightOnBoard(CHESSBOARD_SIZE - 1, 1, ChessColor.WHITE);
        initKnightOnBoard(CHESSBOARD_SIZE - 1, CHESSBOARD_SIZE - 2, ChessColor.WHITE);
        initQueenOnBoard(0, 3, ChessColor.BLACK);
        initQueenOnBoard(CHESSBOARD_SIZE - 1, 3, ChessColor.WHITE);
        initKingOnBoard(0, 4, ChessColor.BLACK);
        initKingOnBoard(CHESSBOARD_SIZE - 1, 4, ChessColor.WHITE);
        for (int i = 0; i < CHESSBOARD_SIZE ; i++) {
            initPawnOnBoard(1,i,ChessColor.BLACK) ;
        }
        for (int i = 0; i < CHESSBOARD_SIZE ; i++) {
            initPawnOnBoard(6,i,ChessColor.WHITE ) ;
        }
        currentColor=ChessColor.WHITE;
        ChessGameFrame.setStatusLabel(getCurrentColor().getName()) ;
        repaint();
    }

    //兵的升变
    public void Pro(int x,int y,ChessColor color){
        char form = ChessGameFrame.ChooseF();
        remove(chessComponents[x][y]);
        if(form == 'Q') {
            ChessComponent chess = new QueenChessComponent(new ChessboardPoint(x,y),calculatePoint(x,y),color,clickController,CHESS_SIZE,this.chessComponents);
            chessComponents[x][y] = chess;
            add(chess);
            chess.repaint();
        }
        if(form == 'B') {
            ChessComponent chess = new BishopChessComponent(new ChessboardPoint(x,y),calculatePoint(x,y),color,clickController,CHESS_SIZE,this.chessComponents);
            chessComponents[x][y] = chess;
            add(chess);
            chess.repaint();
        }
        if(form == 'R') {
            ChessComponent chess = new RookChessComponent(new ChessboardPoint(x,y),calculatePoint(x,y),color,clickController,CHESS_SIZE,this.chessComponents);
            chessComponents[x][y] = chess;
            add(chess);
            chess.repaint();
        }
        if(form == 'K') {
            ChessComponent chess = new KingChessComponent(new ChessboardPoint(x,y),calculatePoint(x,y),color,clickController,CHESS_SIZE,this.chessComponents) ;
            chessComponents[x][y] = chess;
            add(chess);
            chess.repaint();
        }
    }

    public ChessComponent[][] getChessComponents() {
        return chessComponents;
    }

    public ChessColor getCurrentColor() {
        return currentColor;
    }

    public void putChessOnBoard(ChessComponent chessComponent) {
        int row = chessComponent.getChessboardPoint().getX(), col = chessComponent.getChessboardPoint().getY();

        if (chessComponents[row][col] != null) {
            remove(chessComponents[row][col]);
        }
        add(chessComponents[row][col] = chessComponent);
    }

    public void swapChessComponents(ChessComponent chess1, ChessComponent chess2) {
        // Note that chess1 has higher priority, 'destroys' chess2 if exists.
        if (!(chess2 instanceof EmptySlotComponent)) {
            remove(chess2);
            add(chess2 = new EmptySlotComponent(chess2.getChessboardPoint(), chess2.getLocation(), clickController, CHESS_SIZE,this.chessComponents ));
        }
        chess1.swapLocation(chess2);
        int row1 = chess1.getChessboardPoint().getX(), col1 = chess1.getChessboardPoint().getY();
        chessComponents[row1][col1] = chess1;
        int row2 = chess2.getChessboardPoint().getX(), col2 = chess2.getChessboardPoint().getY();
        chessComponents[row2][col2] = chess2;

        chess1.repaint();
        chess2.repaint();
    }

    public void initiateEmptyChessboard() {
        for (int i = 0; i < chessComponents.length; i++) {
            for (int j = 0; j < chessComponents[i].length; j++) {
                putChessOnBoard(new EmptySlotComponent(new ChessboardPoint(i, j), calculatePoint(i, j), clickController, CHESS_SIZE,this.chessComponents));
            }
        }
    }

    public void swapColor() {
        currentColor = currentColor == ChessColor.BLACK ? ChessColor.WHITE : ChessColor.BLACK;
        ChessGameFrame.getStatusLabel().setText(currentColor.getName() );
        ChessGameFrame.getRegret().push(getChessBoard()) ;
        ChessGameFrame.getTimeLabel().setText("15") ;
        //winner
        if(getWinner().equals("b") ){
            JOptionPane.showMessageDialog(null,String.format("%40s","Winner is Black!") ,"END",JOptionPane.PLAIN_MESSAGE );
            winner = "n";
            initBoard();
            ChessGameFrame.getRegret() .init() ;
            ChessGameFrame.getTimeLabel().setText("15") ;
        }
        else if(getWinner().equals("w") ){
            JOptionPane.showMessageDialog(null,String.format("%40s","Winner is White!"),"END",JOptionPane.PLAIN_MESSAGE ) ;
            winner = "n";
            initBoard();
            ChessGameFrame.getRegret() .init() ;
            ChessGameFrame.getTimeLabel().setText("15") ;
        }
        //peace
        for (int i = 0; i < ChessGameFrame.getRegret().getElements().size() ; i++) {
            List<String> list = ChessGameFrame.getRegret().getElements().get(i) ;
            int repeat = 0;
            for (int j = 0; j < ChessGameFrame.getRegret().getElements().size(); j++) {
                List<String> list1 = ChessGameFrame.getRegret().getElements().get(j) ;
                if(list.get(0).equals(list1.get(0)) && list.get(1).equals(list1.get(1)) && list.get(2).equals(list1.get(2)) &&
                        list.get(3).equals(list1.get(3)) && list.get(4).equals(list1.get(4)) && list.get(5).equals(list1.get(5)) &&
                        list.get(6).equals(list1.get(6)) && list.get(7).equals(list1.get(7)) ){
                    repeat ++;
                    if(repeat == 3){break;}
                }
            }
            if(repeat == 3){
                JOptionPane.showMessageDialog(null,String.format("%40s","Peace!"),"END",JOptionPane.PLAIN_MESSAGE ) ;
                initBoard();
                ChessGameFrame.getRegret() .init() ;
                ChessGameFrame.getTimeLabel().setText("15") ;
                break;
            }
        }
    }

    private void initRookOnBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new RookChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE,this.chessComponents);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }

    private void initBishopOnBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new BishopChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE,this.chessComponents );
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }

    private void initQueenOnBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new QueenChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE,this.chessComponents);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }

    private void initKingOnBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new KingChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE,this.chessComponents );
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }

    private void initKnightOnBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new KnightChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE,this.chessComponents);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }

    private void initPawnOnBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new PawnChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE,this.chessComponents);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }


    private Point calculatePoint(int row, int col) {
        return new Point(col * CHESS_SIZE, row * CHESS_SIZE);
    }

    public void loadGame(List<String> chessData) {
//        chessData.forEach(System.out::println);
        initiateEmptyChessboard() ;
        for (int i = 0; i < chessData.size() - 1 ; i++) {
            for (int j = 0; j < chessData.get(i).length() ; j++) {
                char a = chessData.get(i).charAt(j);
                if(a == 'R'){
                    initRookOnBoard(i,j, ChessColor.BLACK ) ;
                }
                else if(a == 'r'){
                    initRookOnBoard(i,j, ChessColor.WHITE  ) ;
                }
                else if(a == 'N'){
                    initKnightOnBoard(i,j, ChessColor.BLACK ) ;
                }
                else if(a == 'n'){
                    initKnightOnBoard(i,j, ChessColor.WHITE  ) ;
                }
                else if(a == 'B'){
                    initBishopOnBoard(i,j, ChessColor.BLACK ) ;
                }
                else if(a == 'b'){
                    initBishopOnBoard(i,j, ChessColor.WHITE  ) ;
                }
                else if(a == 'Q'){
                    initQueenOnBoard(i,j, ChessColor.BLACK ) ;
                }
                else if(a == 'q'){
                    initQueenOnBoard(i,j, ChessColor.WHITE  ) ;
                }
                else if(a == 'K'){
                    initKingOnBoard(i,j, ChessColor.BLACK ) ;
                }
                else if(a == 'k'){
                    initKingOnBoard(i,j, ChessColor.WHITE  ) ;
                }
                else if(a == 'P'){
                    initPawnOnBoard(i,j, ChessColor.BLACK ) ;
                }
                else if(a == 'p'){
                    initPawnOnBoard(i,j, ChessColor.WHITE  ) ;
                }
            }
        }
        if(chessData.get(8).equals("w") ){
            setCurrentColor(ChessColor.WHITE ) ;
        }
        else if(chessData.get(8).equals("b") ){
            setCurrentColor(ChessColor.BLACK ) ;
        }
        ChessGameFrame.setStatusLabel(getCurrentColor().getName()) ;
        repaint();
    }

    public List<String> getChessBoard() {
        List<String> result = new ArrayList<>() ;
        for (int i = 0; i < 8; i++) {
            StringBuilder  s = new StringBuilder();
            for (int j = 0; j < 8; j++) {
                ChessComponent chess = chessComponents[i][j];
                if(chess instanceof RookChessComponent ){
                    if(chess.getChessColor() == ChessColor.BLACK ){
                        s.append("R") ;
                    }
                    else if(chess.getChessColor() == ChessColor.WHITE ){
                        s.append("r") ;
                    }
                }
                else if(chess instanceof KnightChessComponent ){
                    if(chess.getChessColor() == ChessColor.BLACK ){
                        s.append("N") ;
                    }
                    else if(chess.getChessColor() == ChessColor.WHITE ){
                        s.append("n") ;
                    }
                }
                else if(chess instanceof BishopChessComponent ){
                    if(chess.getChessColor() == ChessColor.BLACK ){
                        s.append("B") ;
                    }
                    else if(chess.getChessColor() == ChessColor.WHITE ){
                        s.append("b") ;
                    }
                }
                else if(chess instanceof QueenChessComponent ){
                    if(chess.getChessColor() == ChessColor.BLACK ){
                        s.append("Q") ;
                    }
                    else if(chess.getChessColor() == ChessColor.WHITE ){
                        s.append("q") ;
                    }
                }
                else if(chess instanceof KingChessComponent ){
                    if(chess.getChessColor() == ChessColor.BLACK ){
                        s.append("K") ;
                    }
                    else if(chess.getChessColor() == ChessColor.WHITE ){
                        s.append("k") ;
                    }
                }
                else if(chess instanceof PawnChessComponent ){
                    if(chess.getChessColor() == ChessColor.BLACK ){
                        s.append("P") ;
                    }
                    else if(chess.getChessColor() == ChessColor.WHITE ){
                        s.append("p") ;
                    }
                }
                else if(chess instanceof EmptySlotComponent ){
                    s.append("_") ;
                }
            }
            result.add(s.toString() ) ;
        }
        if(currentColor == ChessColor.BLACK ){
            StringBuilder s  = new StringBuilder();
            s.append("b") ;
            result.add(s.toString() ) ;
        }else if(currentColor == ChessColor.WHITE  ){
            StringBuilder s  = new StringBuilder();
            s.append("w") ;
            result.add(s.toString() ) ;
        }
        return result ;
    }

    public void setCurrentColor(ChessColor currentColor) {
        this.currentColor = currentColor;
    }

    public String getWinner() {
        int c = 2;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.chessComponents[i][j] instanceof KingChessComponent) {
                    c--;
                }
            }
        }
        if (c == 1) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (this.chessComponents[i][j] instanceof KingChessComponent) {
                        if (this.chessComponents[i][j].getChessColor() == ChessColor.BLACK) {
                            winner = "b";
                        } else {
                            winner = "w";
                        }
                    }
                }
            }
        }
        return winner;
    }
}

package controller;


import model.ChessColor;
import model.ChessComponent;
import model.PawnChessComponent;
import view.ChessGameFrame;
import view.Chessboard;
import view.ChessboardPoint;
import view.Music;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class ClickController {
    private final Chessboard chessboard;
    private ChessComponent first;
    private int mode;

    public ClickController(Chessboard chessboard,int mode) {
        this.chessboard = chessboard;
        this.mode = mode ;
    }

    public void onClick(ChessComponent chessComponent) {
        if(mode == 0 ){
            if (first == null) {
                if (handleFirst(chessComponent)) {
                    //music
                    Music clickMusic = new Music("./Music/clickMusic.wav");
                    clickMusic.playMusic() ;
                    for (int i = 0; i < chessComponent.canMovePoints().size(); i++) {
                        int x = chessComponent.canMovePoints().get(i).getX();
                        int y = chessComponent.canMovePoints().get(i).getY();
                        chessComponent.getChessComponents()[x][y].setYs(1);
                        chessComponent.getChessComponents()[x][y].repaint();
                    }
                    chessComponent.setSelected(true);
                    first = chessComponent;
                    first.repaint();
                }
            } else {
                if (first == chessComponent) { // 再次点击取消选取
                    chessComponent.setSelected(false);
                    for (int i = 0; i < chessComponent.canMovePoints().size(); i++) {
                        int x = chessComponent.canMovePoints().get(i).getX();
                        int y = chessComponent.canMovePoints().get(i).getY();
                        chessComponent.getChessComponents()[x][y].setYs(0);
                        chessComponent.getChessComponents()[x][y].repaint();
                    }
                    ChessComponent recordFirst = first;
                    first = null;
                    recordFirst.repaint();
                } else if (handleSecond(chessComponent)) {
                    for (int i = 0; i < first.canMovePoints().size(); i++) {
                        int x = first.canMovePoints().get(i).getX();
                        int y = first.canMovePoints().get(i).getY();
                        first.getChessComponents()[x][y].setYs(0);
                        first.getChessComponents()[x][y].repaint();
                    }
                    //music
                    Music clickMusic = new Music("./Music/clickMusic.wav");
                    clickMusic.playMusic() ;
                    //huanyuan
                    chessboard.swapChessComponents(first, chessComponent);
                    if (first instanceof PawnChessComponent) {
                        if (first.getChessColor() == ChessColor.WHITE && chessComponent.getChessboardPoint().getX() == 0) {
                            chessboard.Pro(chessComponent.getChessboardPoint().getX(), chessComponent.getChessboardPoint().getY(), first.getChessColor());
                        }
                        if (first.getChessColor() == ChessColor.BLACK && chessComponent.getChessboardPoint().getX() == 7) {
                            chessboard.Pro(chessComponent.getChessboardPoint().getX(), chessComponent.getChessboardPoint().getY(), first.getChessColor());
                        }
                    }
                    chessboard.swapColor();
                    first.setSelected(false);
                    first = null;
                }
            }
        }
        //simple AI
        else if (mode == 1){
            if(chessboard.getCurrentColor() == ChessColor.WHITE ){
                if (first == null) {
                    if (handleFirst(chessComponent)) {
                        //music
                        Music clickMusic = new Music("./Music/clickMusic.wav");
                        clickMusic.playMusic() ;
                        for (int i = 0; i < chessComponent.canMovePoints().size(); i++) {
                            int x = chessComponent.canMovePoints().get(i).getX();
                            int y = chessComponent.canMovePoints().get(i).getY();
                            chessComponent.getChessComponents()[x][y].setYs(1);
                            chessComponent.getChessComponents()[x][y].repaint();
                        }
                        chessComponent.setSelected(true);
                        first = chessComponent;
                        first.repaint();
                    }
                } else {
                    if (first == chessComponent) { // 再次点击取消选取
                        chessComponent.setSelected(false);
                        for (int i = 0; i < chessComponent.canMovePoints().size(); i++) {
                            int x = chessComponent.canMovePoints().get(i).getX();
                            int y = chessComponent.canMovePoints().get(i).getY();
                            chessComponent.getChessComponents()[x][y].setYs(0);
                            chessComponent.getChessComponents()[x][y].repaint();
                        }
                        ChessComponent recordFirst = first;
                        first = null;
                        recordFirst.repaint();
                    } else if (handleSecond(chessComponent)) {
                        for (int i = 0; i < first.canMovePoints().size(); i++) {
                            int x = first.canMovePoints().get(i).getX();
                            int y = first.canMovePoints().get(i).getY();
                            first.getChessComponents()[x][y].setYs(0);
                            first.getChessComponents()[x][y].repaint();
                        }
                        //music
                        Music clickMusic = new Music("./Music/clickMusic.wav");
                        clickMusic.playMusic() ;
                        //huanyuan
                        chessboard.swapChessComponents(first, chessComponent);
                        if (first instanceof PawnChessComponent) {
                            if (first.getChessColor() == ChessColor.WHITE && chessComponent.getChessboardPoint().getX() == 0) {
                                chessboard.Pro(chessComponent.getChessboardPoint().getX(), chessComponent.getChessboardPoint().getY(), first.getChessColor());
                            }
                            if (first.getChessColor() == ChessColor.BLACK && chessComponent.getChessboardPoint().getX() == 7) {
                                chessboard.Pro(chessComponent.getChessboardPoint().getX(), chessComponent.getChessboardPoint().getY(), first.getChessColor());
                            }
                        }
                        chessboard.swapColor();
                        first.setSelected(false);
                        first = null;

                        int x = 0;
                        int y = 0;
                        while(true){
                            int x1 = new Random().nextInt(8) ;
                            int y1 = new Random().nextInt(8) ;
                            ChessComponent chess = chessboard.getChessComponents() [x1][y1];
                            if(chess.getChessColor() == ChessColor.BLACK && chess.canMovePoints().size() != 0){
                                x = x1;
                                y = y1;
                                break;
                            }
                        }
                        ChessComponent chess = chessboard.getChessComponents() [x][y];
                        int move = new Random().nextInt(chess.canMovePoints().size()) ;
                        int x1 = chess.canMovePoints().get(move).getX() ;
                        int y1 = chess.canMovePoints().get(move).getY() ;
                        ChessComponent chess1 = chessboard.getChessComponents() [x1][y1];
                        chessboard.swapChessComponents(chess, chess1);
                        chessboard.swapColor();
                    }
                }
            }
        }
        else if(mode == 2){
            if(chessboard.getCurrentColor() == ChessColor.WHITE ){
                if (first == null) {
                    if (handleFirst(chessComponent)) {
                        //music
                        Music clickMusic = new Music("./Music/clickMusic.wav");
                        clickMusic.playMusic() ;
                        for (int i = 0; i < chessComponent.canMovePoints().size(); i++) {
                            int x = chessComponent.canMovePoints().get(i).getX();
                            int y = chessComponent.canMovePoints().get(i).getY();
                            chessComponent.getChessComponents()[x][y].setYs(1);
                            chessComponent.getChessComponents()[x][y].repaint();
                        }
                        chessComponent.setSelected(true);
                        first = chessComponent;
                        first.repaint();
                    }
                } else {
                    if (first == chessComponent) { // 再次点击取消选取
                        chessComponent.setSelected(false);
                        for (int i = 0; i < chessComponent.canMovePoints().size(); i++) {
                            int x = chessComponent.canMovePoints().get(i).getX();
                            int y = chessComponent.canMovePoints().get(i).getY();
                            chessComponent.getChessComponents()[x][y].setYs(0);
                            chessComponent.getChessComponents()[x][y].repaint();
                        }
                        ChessComponent recordFirst = first;
                        first = null;
                        recordFirst.repaint();
                    } else if (handleSecond(chessComponent)) {
                        for (int i = 0; i < first.canMovePoints().size(); i++) {
                            int x = first.canMovePoints().get(i).getX();
                            int y = first.canMovePoints().get(i).getY();
                            first.getChessComponents()[x][y].setYs(0);
                            first.getChessComponents()[x][y].repaint();
                        }
                        //music
                        Music clickMusic = new Music("./Music/clickMusic.wav");
                        clickMusic.playMusic() ;
                        //huanyuan
                        chessboard.swapChessComponents(first, chessComponent);
                        if (first instanceof PawnChessComponent) {
                            if (first.getChessColor() == ChessColor.WHITE && chessComponent.getChessboardPoint().getX() == 0) {
                                chessboard.Pro(chessComponent.getChessboardPoint().getX(), chessComponent.getChessboardPoint().getY(), first.getChessColor());
                            }
                            if (first.getChessColor() == ChessColor.BLACK && chessComponent.getChessboardPoint().getX() == 7) {
                                chessboard.Pro(chessComponent.getChessboardPoint().getX(), chessComponent.getChessboardPoint().getY(), first.getChessColor());
                            }
                        }
                        chessboard.swapColor();
                        first.setSelected(false);
                        first = null;

                        //greedy
                        int x = 0;
                        int y = 0;
                        while(true){
                            int x1 = new Random().nextInt(8) ;
                            int y1 = new Random().nextInt(8) ;
                            ChessComponent chess = chessboard.getChessComponents() [x1][y1];
                            if(chess.getChessColor() == ChessColor.BLACK && chess.canMovePoints().size() != 0){
                                x = x1;
                                y = y1;
                                break;
                            }
                        }
                        ChessComponent chess = chessboard.getChessComponents() [x][y];
                        int move = new Random().nextInt(chess.canMovePoints().size()) ;
                        int x1 = chess.canMovePoints().get(move).getX() ;
                        int y1 = chess.canMovePoints().get(move).getY() ;
                        ChessComponent chess1 = chessboard.getChessComponents() [x1][y1];
                        chessboard.swapChessComponents(chess, chess1);
                        chessboard.swapColor();
                    }
                }
            }
        }
    }


    /**
     * @param chessComponent 目标选取的棋子
     * @return 目标选取的棋子是否与棋盘记录的当前行棋方颜色相同
     */

    private boolean handleFirst(ChessComponent chessComponent) {
        return chessComponent.getChessColor() == chessboard.getCurrentColor();
    }

    /**
     * @param chessComponent first棋子目标移动到的棋子second
     * @return first棋子是否能够移动到second棋子位置
     */

    private boolean handleSecond(ChessComponent chessComponent) {
        return chessComponent.getChessColor() != chessboard.getCurrentColor() &&
                first.canMoveTo(chessboard.getChessComponents(), chessComponent.getChessboardPoint());
    }


}

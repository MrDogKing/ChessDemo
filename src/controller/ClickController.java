package controller;


import model.*;
import view.ChessGameFrame;
import view.Chessboard;
import view.ChessboardPoint;
import view.Music;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
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

                        int x ;
                        int y ;
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
        //greedy AI
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
                        ChessComponent chess ;
                        ChessComponent chess1;
                        List <ChessComponent> canChess = new ArrayList<>();
                        List <ChessComponent> eatChess = new ArrayList<>();
                        //find can move chess
                        for (int i = 0; i < 8; i++) {
                            for (int j = 0; j < 8; j++) {
                                ChessComponent c = chessboard.getChessComponents() [i][j];
                                if( c.getChessColor() == ChessColor.BLACK && c.canMovePoints().size() != 0){
                                    canChess.add(c) ;
                                }
                            }
                        }
                        //find can eat chess
                        for (int i = 0; i < canChess.size() ; i++) {
                            for (int j = 0; j < canChess.get(i).canMovePoints().size() ; j++) {
                                int x = canChess.get(i).canMovePoints().get(j).getX() ;
                                int y = canChess.get(i).canMovePoints().get(j).getY() ;
                                if( chessboard.getChessComponents() [x][y].getChessColor() == ChessColor.WHITE ){
                                    eatChess.add(canChess.get(i)) ;
                                }
                            }
                        }
                        //choose best
                        if(choose(eatChess) != null ){
                            chess = choose(eatChess);
                            int x = choose1(chess).getX() ;
                            int y = choose1(chess).getY() ;
                            chess1 = chessboard.getChessComponents() [x][y];
                        }
                        else{
                            int i = new Random().nextInt(canChess.size()) ;
                            chess = canChess.get(i);
                            int j = new Random().nextInt(chess.canMovePoints().size()) ;
                            int x = chess.canMovePoints().get(j).getX() ;
                            int y = chess.canMovePoints().get(j).getY() ;
                            chess1 = chessboard.getChessComponents() [x][y];
                        }
                        chessboard.swapChessComponents(chess, chess1);
                        chessboard.swapColor();
                    }
                }
            }
        }
    }

    private ChessComponent choose(List<ChessComponent> can ){
        for (int i = 0; i < can.size() ; i++) {
            ChessComponent c = can.get(i);
            for (int j = 0; j < c.canMovePoints().size() ; j++) {
                int x = c.canMovePoints().get(j).getX() ;
                int y = c.canMovePoints().get(j).getY() ;
                if(chessboard.getChessComponents()[x][y] instanceof KingChessComponent ){
                    return c;
                }
            }
        }

        for (int i = 0; i < can.size() ; i++) {
            ChessComponent c = can.get(i);
            for (int j = 0; j < c.canMovePoints().size() ; j++) {
                int x = c.canMovePoints().get(j).getX() ;
                int y = c.canMovePoints().get(j).getY() ;
                if(chessboard.getChessComponents()[x][y] instanceof QueenChessComponent){
                    return c;
                }
            }
        }

        for (int i = 0; i < can.size() ; i++) {
            ChessComponent c = can.get(i);
            for (int j = 0; j < c.canMovePoints().size() ; j++) {
                int x = c.canMovePoints().get(j).getX() ;
                int y = c.canMovePoints().get(j).getY() ;
                if(chessboard.getChessComponents()[x][y] instanceof BishopChessComponent ){
                    return c;
                }
            }
        }

        for (int i = 0; i < can.size() ; i++) {
            ChessComponent c = can.get(i);
            for (int j = 0; j < c.canMovePoints().size() ; j++) {
                int x = c.canMovePoints().get(j).getX() ;
                int y = c.canMovePoints().get(j).getY() ;
                if(chessboard.getChessComponents()[x][y] instanceof KnightChessComponent  ){
                    return c;
                }
            }
        }

        for (int i = 0; i < can.size() ; i++) {
            ChessComponent c = can.get(i);
            for (int j = 0; j < c.canMovePoints().size() ; j++) {
                int x = c.canMovePoints().get(j).getX() ;
                int y = c.canMovePoints().get(j).getY() ;
                if(chessboard.getChessComponents()[x][y] instanceof RookChessComponent  ){
                    return c;
                }
            }
        }

        for (int i = 0; i < can.size() ; i++) {
            ChessComponent c = can.get(i);
            for (int j = 0; j < c.canMovePoints().size() ; j++) {
                int x = c.canMovePoints().get(j).getX() ;
                int y = c.canMovePoints().get(j).getY() ;
                if(chessboard.getChessComponents()[x][y] instanceof PawnChessComponent ){
                    return c;
                }
            }
        }
        return null;
    }

    private ChessboardPoint choose1(ChessComponent c){
        for (int j = 0; j < c.canMovePoints().size() ; j++) {
            int x = c.canMovePoints().get(j).getX() ;
            int y = c.canMovePoints().get(j).getY() ;
            if(chessboard.getChessComponents()[x][y] instanceof KingChessComponent ){
                return c.canMovePoints().get(j) ;
            }
        }

        for (int j = 0; j < c.canMovePoints().size() ; j++) {
            int x = c.canMovePoints().get(j).getX() ;
            int y = c.canMovePoints().get(j).getY() ;
            if(chessboard.getChessComponents()[x][y] instanceof QueenChessComponent ){
                return c.canMovePoints().get(j) ;
            }
        }

        for (int j = 0; j < c.canMovePoints().size() ; j++) {
            int x = c.canMovePoints().get(j).getX() ;
            int y = c.canMovePoints().get(j).getY() ;
            if(chessboard.getChessComponents()[x][y] instanceof BishopChessComponent ){
                return c.canMovePoints().get(j) ;
            }
        }

        for (int j = 0; j < c.canMovePoints().size() ; j++) {
            int x = c.canMovePoints().get(j).getX() ;
            int y = c.canMovePoints().get(j).getY() ;
            if(chessboard.getChessComponents()[x][y] instanceof KnightChessComponent  ){
                return c.canMovePoints().get(j) ;
            }
        }

        for (int j = 0; j < c.canMovePoints().size() ; j++) {
            int x = c.canMovePoints().get(j).getX() ;
            int y = c.canMovePoints().get(j).getY() ;
            if(chessboard.getChessComponents()[x][y] instanceof RookChessComponent ){
                return c.canMovePoints().get(j) ;
            }
        }

        for (int j = 0; j < c.canMovePoints().size() ; j++) {
            int x = c.canMovePoints().get(j).getX() ;
            int y = c.canMovePoints().get(j).getY() ;
            if(chessboard.getChessComponents()[x][y] instanceof PawnChessComponent ){
                return c.canMovePoints().get(j) ;
            }
        }

        return null;
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

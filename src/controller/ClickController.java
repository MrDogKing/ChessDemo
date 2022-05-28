package controller;


import model.ChessColor;
import model.ChessComponent;
import model.PawnChessComponent;
import view.ChessGameFrame;
import view.Chessboard;
import view.Music;

import java.awt.*;

public class ClickController {
    private final Chessboard chessboard;
    private ChessComponent first;

    public ClickController(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    public void onClick(ChessComponent chessComponent) {
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

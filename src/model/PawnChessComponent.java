package model;

import view.ChessboardPoint;
import controller.ClickController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class PawnChessComponent extends ChessComponent {
    private static Image PAWN_WHITE;
    private static Image PAWN_BLACK;
    private Image pawnImage;

    public void loadResource() throws IOException {
        if (PAWN_WHITE == null) {
            PAWN_WHITE = ImageIO.read(new File("./images/pawn-white11.png"));
        }

        if (PAWN_BLACK == null) {
            PAWN_BLACK = ImageIO.read(new File("./images/pawn-black11.png"));
        }
    }

    private void initiateKnightImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                pawnImage = PAWN_WHITE;
            } else if (color == ChessColor.BLACK) {
                pawnImage = PAWN_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PawnChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size,ChessComponent[][] chessComponents) {
        super(chessboardPoint, location, color, listener, size,chessComponents );
        initiateKnightImage(color);
    }

    @Override
    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination) {
        ChessboardPoint source = getChessboardPoint();
        int row = destination.getX() ;
        int col = destination.getY() ;
        if(getChessColor().getColor() == Color.BLACK ){
            if(source.getX() == 1){
                if(row == 2 && source.getY() == col && chessComponents[row][col] instanceof EmptySlotComponent){
                    return true;
                }
                else if(row == 3 && source.getY() == col && chessComponents[row][col] instanceof EmptySlotComponent
                && chessComponents[row-1][col] instanceof EmptySlotComponent){
                    return true;
                }
                else if(row == 2 && source.getY() + 1 == col && !(chessComponents[row][col] instanceof EmptySlotComponent)){
                    return true;
                }
                else if(row == 2 && source.getY() - 1 == col && !(chessComponents[row][col] instanceof EmptySlotComponent)){
                    return true ;
                }
            }
            else {
                if(row == source.getX() + 1 && source.getY() == col && chessComponents[row][col] instanceof EmptySlotComponent){
                    return true;
                }
                else if(row == source.getX() + 1 && source.getY() + 1 == col && !(chessComponents[row][col] instanceof EmptySlotComponent)){
                    return true;
                }
                else if(row == source.getX() + 1 && source.getY() - 1 == col && !(chessComponents[row][col] instanceof EmptySlotComponent)){
                    return true ;
                }
            }
        }
        else if(getChessColor().getColor() == Color.WHITE ){
            if(source.getX() == 6){
                if(row == 5 && source.getY() == col && chessComponents[row][col] instanceof EmptySlotComponent){
                    return true;
                }
                else if(row == 4 && source.getY() == col && chessComponents[row][col] instanceof EmptySlotComponent
                        && chessComponents[row+1][col] instanceof EmptySlotComponent){
                    return true;
                }
                else if(row == 5 && source.getY() + 1 == col && !(chessComponents[row][col] instanceof EmptySlotComponent)){
                    return true;
                }
                else if(row == 5 && source.getY() - 1 == col && !(chessComponents[row][col] instanceof EmptySlotComponent)){
                    return true ;
                }
            }
            else {
                if(row == source.getX() - 1 && source.getY() == col && chessComponents[row][col] instanceof EmptySlotComponent){
                    return true;
                }
                else if(row == source.getX() - 1 && source.getY() + 1 == col && !(chessComponents[row][col] instanceof EmptySlotComponent)){
                    return true;
                }
                else if(row == source.getX() - 1 && source.getY() - 1 == col && !(chessComponents[row][col] instanceof EmptySlotComponent)){
                    return true ;
                }
            }
        }
        return false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.drawImage(rookImage, 0, 0, getWidth() - 13, getHeight() - 20, this);
        g.drawImage(pawnImage, 0, 0, getWidth(), getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.YELLOW );
            g.drawOval(0, 0, getWidth(), getHeight());
        }
    }

    @Override
    public List<ChessboardPoint> canMovePoints() {
        ArrayList<ChessboardPoint> move = new ArrayList<>();
        if (this.getChessColor() == ChessColor.BLACK) {
            if (this.getChessboardPoint().getX() == 1) {
                if (this.getChessComponents()[getChessboardPoint().getX() + 1][getChessboardPoint().getY()] instanceof EmptySlotComponent ) {
                    move.add(this.getChessboardPoint().offset(1, 0));
                }
                if (this.getChessComponents()[getChessboardPoint().getX() + 2][getChessboardPoint().getY()] instanceof EmptySlotComponent ) {
                    move.add(this.getChessboardPoint().offset(2, 0));
                }
                if (this.getChessboardPoint().offset(1, 1) != null) {
                    if (getChessComponents()[this.getChessboardPoint().getX() + 1][this.getChessboardPoint().getY() + 1].getChessColor() != this.getChessColor() &&
                            getChessComponents()[this.getChessboardPoint().getX() + 1][this.getChessboardPoint().getY() + 1].getChessColor() != ChessColor.NONE) {
                        move.add(this.getChessboardPoint().offset(1, 1));
                    }
                }
                if (this.getChessboardPoint().offset(1, -1) != null) {
                    if (getChessComponents()[this.getChessboardPoint().getX() + 1][this.getChessboardPoint().getY() - 1].getChessColor() != this.getChessColor() &&
                            getChessComponents()[this.getChessboardPoint().getX() + 1][this.getChessboardPoint().getY() - 1].getChessColor() != ChessColor.NONE) {
                        move.add(this.getChessboardPoint().offset(1, -1));
                    }
                }
            } else {
                if (this.getChessboardPoint().offset(1, 0) != null) {
                    if (this.getChessComponents()[getChessboardPoint().getX() + 1][getChessboardPoint().getY()] instanceof EmptySlotComponent ) {
                        move.add(this.getChessboardPoint() .offset(1, 0));
                    }
                }
                if (this.getChessboardPoint().offset(1, 1) != null) {
                    if (getChessComponents()[this.getChessboardPoint().getX() + 1][this.getChessboardPoint().getY() + 1].getChessColor() != this.getChessColor() &&
                            getChessComponents()[this.getChessboardPoint().getX() + 1][this.getChessboardPoint().getY() + 1].getChessColor() != ChessColor.NONE) {
                        move.add(this.getChessboardPoint().offset(1, 1));
                    }
                }
                if (this.getChessboardPoint().offset(1, -1) != null) {
                    if (getChessComponents()[this.getChessboardPoint().getX() + 1][this.getChessboardPoint().getY() - 1].getChessColor() != this.getChessColor() &&
                            getChessComponents()[this.getChessboardPoint().getX() + 1][this.getChessboardPoint().getY() - 1].getChessColor() != ChessColor.NONE) {
                        move.add(this.getChessboardPoint().offset(1, -1));
                    }
                }
            }
        } else if (this.getChessColor() == ChessColor.WHITE) {
            if (this.getChessboardPoint().getX() == 6) {
                if (this.getChessComponents()[getChessboardPoint().getX() - 1][getChessboardPoint().getY()] instanceof EmptySlotComponent ) {
                    move.add(this.getChessboardPoint().offset(-1, 0));
                }
                if (this.getChessComponents()[getChessboardPoint().getX() - 2][getChessboardPoint().getY()]instanceof EmptySlotComponent ) {
                    move.add(this.getChessboardPoint().offset(-2, 0));
                }
                if (this.getChessboardPoint().offset(-1, 1) != null) {
                    if (getChessComponents()[this.getChessboardPoint().getX() - 1][this.getChessboardPoint().getY() + 1].getChessColor() != this.getChessColor() &&
                            getChessComponents()[this.getChessboardPoint().getX() - 1][this.getChessboardPoint().getY() + 1].getChessColor() != ChessColor.NONE) {
                        move.add(this.getChessboardPoint().offset(-1, 1));
                    }
                }
                if (this.getChessboardPoint().offset(-1, -1) != null) {
                    if (getChessComponents()[this.getChessboardPoint().getX() - 1][this.getChessboardPoint().getY() - 1].getChessColor() != this.getChessColor() &&
                            getChessComponents()[this.getChessboardPoint().getX() - 1][this.getChessboardPoint().getY() - 1].getChessColor() != ChessColor.NONE) {
                        move.add(this.getChessboardPoint().offset(-1, -1));
                    }
                }
            } else {
                if (this.getChessboardPoint().offset(-1, 0) != null) {
                    if (this.getChessComponents()[getChessboardPoint().getX() - 1][getChessboardPoint().getY()]instanceof EmptySlotComponent ) {
                        move.add(this.getChessboardPoint().offset(-1, 0));
                    }
                }
                if (this.getChessboardPoint().offset(-1, 1) != null) {
                    if (getChessComponents()[this.getChessboardPoint().getX() - 1][this.getChessboardPoint().getY() + 1].getChessColor() != this.getChessColor() &&
                            getChessComponents()[this.getChessboardPoint().getX() - 1][this.getChessboardPoint().getY() + 1].getChessColor() != ChessColor.NONE) {
                        move.add(this.getChessboardPoint().offset(-1, 1));
                    }
                }
                if (this.getChessboardPoint().offset(-1, -1) != null) {
                    if (getChessComponents()[this.getChessboardPoint().getX() - 1][this.getChessboardPoint().getY() - 1].getChessColor() != this.getChessColor() &&
                            getChessComponents()[this.getChessboardPoint().getX() - 1][this.getChessboardPoint().getY() - 1].getChessColor() != ChessColor.NONE) {
                        move.add(this.getChessboardPoint().offset(-1, -1));
                    }
                }
            }
        }
        return move;
    }
}
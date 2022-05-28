package model;

import view.Chessboard;
import view.ChessboardPoint;
import controller.ClickController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class KingChessComponent extends ChessComponent {
    private static Image KING_WHITE;
    private static Image KING_BLACK;
    private Image kingImage;

    public void loadResource() throws IOException {
        if (KING_WHITE == null) {
            KING_WHITE = ImageIO.read(new File("./images/king-white11.png"));
        }

        if (KING_BLACK == null) {
            KING_BLACK = ImageIO.read(new File("./images/king-black11.png"));
        }
    }

    private void initiateKingImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                kingImage = KING_WHITE;
            } else if (color == ChessColor.BLACK) {
                kingImage = KING_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public KingChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size, ChessComponent[][] chessComponents) {
        super(chessboardPoint, location, color, listener, size, chessComponents  );
        initiateKingImage(color);
    }

    @Override
    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination) {
        ChessboardPoint source = getChessboardPoint();
        if (source.getX() == destination.getX()) {
            if (source.getY() + 1 == destination.getY()) {
                return true;
            }
            if (source.getY() - 1 == destination.getY()) {
                return true;
            }
        } else if (source.getY() == destination.getY()) {
            if (source.getX() + 1 == destination.getX()) {
                return true;
            }
            if (source.getX() - 1 == destination.getX()) {
                return true;
            }
        } else if (source.getX() + 1 == destination.getX()) {
            if (source.getY() + 1 == destination.getY()) {
                return true;
            }
            if (source.getY() - 1 == destination.getY()) {
                return true;
            }
        } else if (source.getX() - 1 == destination.getX()) {
            if (source.getY() + 1 == destination.getY()) {
                return true;
            }
            if (source.getY() - 1 == destination.getY()) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.drawImage(rookImage, 0, 0, getWidth() - 13, getHeight() - 20, this);
        g.drawImage(kingImage, 0, 0, getWidth(), getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.YELLOW  );
            g.drawOval(0, 0, getWidth(), getHeight());
        }
    }
    @Override
    public List<ChessboardPoint> canMovePoints() {
        ArrayList<ChessboardPoint > move = new ArrayList<>();
        if( this.getChessboardPoint().offset(1,0) != null && this.getChessComponents()[this.getChessboardPoint().getX()+1][this.getChessboardPoint().getY()].getChessColor() != this.getChessColor()){
            move.add(this.getChessboardPoint().offset(1,0) ) ;
        }
        if(this.getChessboardPoint().offset(0,1) != null && this.getChessComponents()[this.getChessboardPoint().getX()][this.getChessboardPoint().getY()+1].getChessColor() != this.getChessColor()){
            move.add(this.getChessboardPoint().offset(0,1) ) ;
        }
        if( this.getChessboardPoint().offset(-1,0) != null && this.getChessComponents()[this.getChessboardPoint().getX()-1][this.getChessboardPoint().getY()].getChessColor() != this.getChessColor()){
            move.add(this.getChessboardPoint().offset(-1,0) ) ;
        }
        if( this.getChessboardPoint().offset(0,-1) != null && this.getChessComponents()[this.getChessboardPoint().getX()][this.getChessboardPoint().getY()-1].getChessColor() != this.getChessColor()){
            move.add(this.getChessboardPoint().offset(0,-1) ) ;
        }
        if( this.getChessboardPoint().offset(1,1) != null && this.getChessComponents()[this.getChessboardPoint().getX()+1][this.getChessboardPoint().getY()+1].getChessColor() != this.getChessColor()){
            move.add(this.getChessboardPoint().offset(1,1) ) ;
        }
        if(this.getChessboardPoint().offset(1,-1) != null && this.getChessComponents()[this.getChessboardPoint().getX()+1][this.getChessboardPoint().getY()-1].getChessColor() != this.getChessColor()){
            move.add(this.getChessboardPoint().offset(1,-1) ) ;
        }
        if( this.getChessboardPoint().offset(-1,1) != null && this.getChessComponents()[this.getChessboardPoint().getX()-1][this.getChessboardPoint().getY()+1].getChessColor() != this.getChessColor()){
            move.add(this.getChessboardPoint().offset(-1,1) ) ;
        }
        if( this.getChessboardPoint().offset(-1,-1) != null && this.getChessComponents()[this.getChessboardPoint().getX()-1][this.getChessboardPoint().getY()-1].getChessColor() != this.getChessColor()){
            move.add(this.getChessboardPoint().offset(-1,-1) ) ;
        }
        return move;
    }
}
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

public class BishopChessComponent extends ChessComponent {
    private static Image BISHOP_WHITE;
    private static Image BISHOP_BLACK;
    private Image bishopImage;

    public void loadResource() throws IOException {
        if (BISHOP_WHITE == null) {
            BISHOP_WHITE = ImageIO.read(new File("./images/bishop-white11.png"));
        }

        if (BISHOP_BLACK == null) {
            BISHOP_BLACK = ImageIO.read(new File("./images/bishop-black11.png"));
        }
    }

    private void initiateBishopImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                bishopImage = BISHOP_WHITE;
            } else if (color == ChessColor.BLACK) {
                bishopImage = BISHOP_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BishopChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size, ChessComponent[][] chessComponents) {
        super(chessboardPoint, location, color, listener, size,chessComponents  );

        initiateBishopImage(color);
    }

    @Override
    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination) {
        ChessboardPoint source = getChessboardPoint();
        if (source.getX() - destination.getX() == source.getY() - destination.getY() ) {
            int row = Math.min(source.getX(), destination.getX());
            for (int col = Math.min(source.getY(), destination.getY()) + 1;
                 col < Math.max(source.getY(), destination.getY()); col++) {
                row++;
                if (!(chessComponents[row][col] instanceof EmptySlotComponent)) {
                    return false;
                }
            }
        } else if (source.getX() - destination.getX() == destination.getY() - source.getY()) {
            int row = Math.min(source.getX(), destination.getX());
            for (int col = Math.max(source.getY(), destination.getY()) - 1;
                 col > Math.min(source.getY(), destination.getY()); col--) {
                row++;
                if (!(chessComponents[row][col] instanceof EmptySlotComponent)) {
                    return false;
                }
            }
        } else { // Not on the same row or the same column.
            return false;
        }
        return true;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.drawImage(rookImage, 0, 0, getWidth() - 13, getHeight() - 20, this);
        g.drawImage(bishopImage, 0, 0, getWidth() , getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.YELLOW  );
            g.drawOval(0, 0, getWidth() , getHeight());
        }
    }

    @Override
    public List<ChessboardPoint> canMovePoints() {
        ArrayList<ChessboardPoint > move = new ArrayList<>();
        // +x +y
        for (int i = 1; i < 8; i++) {
            if(this.getChessboardPoint().offset(i ,i) != null) {
                ChessColor j = this.getChessComponents()[this.getChessboardPoint().getX()+i][this.getChessboardPoint().getY()+i].getChessColor();
                if(j == this.getChessColor()){
                    break ;
                }
                else if(j != this.getChessColor() && j != ChessColor.NONE ){
                    move.add(this.getChessboardPoint().offset(i ,i)) ;
                    break;
                }
                else if(j == ChessColor.NONE ){
                    move.add(this.getChessboardPoint().offset(i ,i)) ;
                }
            }
            else {
                break;
            }
        }
        // -x -y
        for (int i = 1; i < 8; i++) {
            if(this.getChessboardPoint().offset(-i ,-i) != null) {
                ChessColor j = this.getChessComponents()[this.getChessboardPoint().getX()-i][this.getChessboardPoint().getY()-i].getChessColor();
                if(j == this.getChessColor()){
                    break ;
                }
                else if(j != this.getChessColor() && j != ChessColor.NONE ){
                    move.add(this.getChessboardPoint().offset(-i ,-i)) ;
                    break;
                }
                else if(j == ChessColor.NONE ){
                    move.add(this.getChessboardPoint().offset(-i ,-i)) ;
                }
            }
            else {
                break;
            }
        }
        // -x +y
        for (int i = 1; i < 8; i++) {
            if(this.getChessboardPoint().offset(-i ,+i) != null) {
                ChessColor j = this.getChessComponents()[this.getChessboardPoint().getX()-i][this.getChessboardPoint().getY()+i].getChessColor();
                if(j == this.getChessColor()){
                    break ;
                }
                else if(j != this.getChessColor() && j != ChessColor.NONE ){
                    move.add(this.getChessboardPoint().offset(-i ,+i)) ;
                    break;
                }
                else if(j == ChessColor.NONE ){
                    move.add(this.getChessboardPoint().offset(-i ,+i)) ;
                }
            }
            else {
                break;
            }
        }
        // +x -y
        for (int i = 1; i < 8; i++) {
            if(this.getChessboardPoint().offset(i ,-i) != null) {
                ChessColor j = this.getChessComponents()[this.getChessboardPoint().getX()+i][this.getChessboardPoint().getY()-i].getChessColor();
                if(j == this.getChessColor()){
                    break ;
                }
                else if(j != this.getChessColor() && j != ChessColor.NONE ){
                    move.add(this.getChessboardPoint().offset(i ,-i)) ;
                    break;
                }
                else if(j == ChessColor.NONE ){
                    move.add(this.getChessboardPoint().offset(i ,-i)) ;
                }
            }
            else {
                break;
            }
        }
        return move;
    }
}

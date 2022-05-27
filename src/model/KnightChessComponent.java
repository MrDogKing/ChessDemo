package model;

import view.ChessboardPoint;
import controller.ClickController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class KnightChessComponent extends ChessComponent {
    private static Image KNIGHT_WHITE;
    private static Image KNIGHT_BLACK;
    private Image knightImage;

    public void loadResource() throws IOException {
        if (KNIGHT_WHITE == null) {
            KNIGHT_WHITE = ImageIO.read(new File("./images/knight-white11.png"));
        }

        if (KNIGHT_BLACK == null) {
            KNIGHT_BLACK = ImageIO.read(new File("./images/knight-black11.png"));
        }
    }

    private void initiateKnightImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                knightImage = KNIGHT_WHITE;
            } else if (color == ChessColor.BLACK) {
                knightImage = KNIGHT_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public KnightChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size);
        initiateKnightImage(color);
    }

    @Override
    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination) {
        ChessboardPoint source = getChessboardPoint();
        //1，2
        if (source.getX() + 1 == destination.getX()) {
            if(source.getY() + 2 == destination.getY() ){
                return true;
            }
        }
        //1，-2
        if (source.getX() + 1 == destination.getX()) {
            if(source.getY() - 2 == destination.getY() ){
                return true;
            }
        }
        //-1，2
        if (source.getX() - 1 == destination.getX()) {
            if(source.getY() + 2 == destination.getY() ){
                return true;
            }
        }
        //-1，-2
        if (source.getX() - 1 == destination.getX()) {
            if(source.getY() - 2 == destination.getY() ){
                return true;
            }
        }
        //2，1
        if (source.getX() + 2 == destination.getX()) {
            if(source.getY() + 1 == destination.getY() ){
                return true;
            }
        }
        //2，-1
        if (source.getX() + 2 == destination.getX()) {
            if(source.getY() - 1 == destination.getY() ){
                return true;
            }
        }
        //-2，1
        if (source.getX() - 2 == destination.getX()) {
            if(source.getY() + 1 == destination.getY() ){
                return true;
            }
        }
        //-2，-1
        if (source.getX() - 2 == destination.getX()) {
            if(source.getY() - 1 == destination.getY() ){
                return true;
            }
        }
        return false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.drawImage(rookImage, 0, 0, getWidth() - 13, getHeight() - 20, this);
        g.drawImage(knightImage, 0, 0, getWidth(), getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.YELLOW );
            g.drawOval(0, 0, getWidth(), getHeight());
        }
    }
}
package controller;

import view.Chessboard;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class GameController {
    private Chessboard chessboard;

    public void initChessboard(){
        chessboard.initBoard();
    }

    public GameController(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    public String getCurrentPlayer(){
        return this.chessboard.getCurrentColor().getName() ;
    }

    public List<String> loadGameFromFile(String path) {
        try {
            List<String> chessData = Files.readAllLines(Path.of(path));
            chessboard.loadGame(chessData);
            return chessData;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

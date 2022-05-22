package controller;

import view.Chessboard;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    public List<String> saveGameFromFile() {
        int count = 1;
        while(true){
            try{
                String path = String.format("resource/save%d.txt", count);
                if (Files.exists(Paths.get(path))) {
                    count++;
                } else {
                    FileWriter writer = new FileWriter(path);
                    for (int i = 0; i < chessboard.getChessBoard() .size(); i++) {
                        writer.write(chessboard.getChessBoard() .get(i));
                        writer.write("\n");
                    }
                    writer.close();
                    break;
                }
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
        return this.chessboard.getChessBoard() ;
    }
}

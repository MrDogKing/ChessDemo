package controller;

import model.ChessComponent;
import view.Chessboard;

import javax.swing.*;
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
            //错误判断
            //104
            String[] arr = path.split("\\.");
            if(!(arr[1].equals("txt")) ){
                JOptionPane.showMessageDialog(null,String.format("%40s","Error: 104") ,"Not Valid",JOptionPane.PLAIN_MESSAGE );
                return null;
            }
            //101 103
            if(chessData.size() != 9){
                if(chessData.size() == 8){
                    for (int i = 0; i < 8; i++) {
                        if(chessData.get(i).length() != 8){
                            JOptionPane.showMessageDialog(null,String.format("%40s","Error: 101") ,"Not Valid",JOptionPane.PLAIN_MESSAGE );
                            return null;
                        }
                    }
                    JOptionPane.showMessageDialog(null,String.format("%40s","Error: 103") ,"Not Valid",JOptionPane.PLAIN_MESSAGE );
                    return null;
                }
                JOptionPane.showMessageDialog(null,String.format("%40s","Error: 101") ,"Not Valid",JOptionPane.PLAIN_MESSAGE );
                return null;
            }
            //102
            int cnt = 0;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    char chess = chessData.get(i).charAt(j) ;
                    if(chess == '_' ){
                        cnt++;
                    }
                    else if(chess == 'R'){
                        cnt++;
                    }
                    else if(chess == 'r'){
                        cnt++;
                    }
                    else if(chess == 'N'){
                        cnt++;
                    }
                    else if(chess == 'n'){
                        cnt++;
                    }
                    else if(chess == 'B'){
                        cnt++;
                    }
                    else if(chess == 'b'){
                        cnt++;
                    }
                    else if(chess == 'Q'){
                        cnt++;
                    }
                    else if(chess == 'q'){
                        cnt++;
                    }
                    else if(chess == 'K'){
                        cnt++;
                    }
                    else if(chess == 'k'){
                        cnt++;
                    }
                    else if(chess == 'P'){
                        cnt++;
                    }
                    else if(chess == 'p'){
                        cnt++;
                    }
                }
            }
                System.out.println(cnt);
                if(cnt != 64){
                    JOptionPane.showMessageDialog(null,String.format("%40s","Error: 102") ,"Not Valid",JOptionPane.PLAIN_MESSAGE );
                    return null;
                }
                cnt = 0;

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

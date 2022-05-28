import view.ChessGameFrame;
import view.ChessGameFrame1;
import view.Music;
import view.MusicBack;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChessGameFrame1 mainFrame = new ChessGameFrame1(500, 750);//窗体大小
            mainFrame.setVisible(true);
            MusicBack backMusic = new MusicBack("./Music/boardMusic.wav") ;
            backMusic.playMusic() ;
        });
    }
}

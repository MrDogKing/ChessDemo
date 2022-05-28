package view;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;
public class MusicBack{
    String path;
    Clip clip;

    public MusicBack(String path) {
        this.path = path ;
    }

    public void playMusic() {
        try {
            File musicPath = new File(path);
            if (musicPath.exists()) {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                System.out.println("Can't find file.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

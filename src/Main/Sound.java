package Main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    Clip clip;
    URL[] soundURL = new URL[30];

    public Sound() {

        soundURL[0] = getClass().getResource("/Res/sound/Background.wav");
        soundURL[1] = getClass().getResource("/Res/sound/coin.wav");
        soundURL[2] = getClass().getResource("/Res/sound/powerup.wav");
        soundURL[3] = getClass().getResource("/Res/sound/unlock.wav");
        soundURL[4] = getClass().getResource("/Res/sound/fanfare.wav");
        soundURL[5] = getClass().getResource("/Res/sound/hitmonster.wav");
        soundURL[6] = getClass().getResource("/Res/sound/receivedamage.wav");
        soundURL[7] = getClass().getResource("/Res/sound/swingweapon.wav");
        soundURL[8] = getClass().getResource("/Res/sound/levelup.wav");
    }

    public void setFile(int i) {

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void play() {

        clip.start();

    }
    public void loop() {

        clip.loop(Clip.LOOP_CONTINUOUSLY);

    }
    public void stop() {

        clip.stop();

    }
}

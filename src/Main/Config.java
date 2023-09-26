package Main;

import java.io.*;

public class Config {
    GamePanel gp;
    public Config(GamePanel gp) {
        this.gp = gp;
    }

    public void saveConfig() {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("./config.txt"));

//            // Full-screen
//            if (gp.fullScreenOn) {
//                bw.write("On");
//            }
//            if (!gp.fullScreenOn) {
//                bw.write("Off");
//            }
//            bw.newLine();

            // Music volume
            bw.write(String.valueOf(gp.music.volumeScale));
            bw.newLine();

            // Sound effect volume
            bw.write(String.valueOf(gp.se.volumeScale));
            bw.newLine();

            bw.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadConfig() {

        try {
            InputStream in = getClass().getResourceAsStream("/Res/config.txt");
            BufferedReader br = null;
            if (in != null) {
                br = new BufferedReader(new InputStreamReader(in));
            }

            String s = null;
            if (br != null) {
                s = br.readLine();
            }

//            // Full-screen
//            if (s.equals("On")) {
//                gp.fullScreenOn = true;
//            }
//            if (s.equals("Off")) {
//                gp.fullScreenOn = false;
//            }

            // Music volume
            if (br != null) {
                s = br.readLine();
            }
            if (s != null) {
                gp.music.volumeScale = Integer.parseInt(s);
            }

            // Sound effect volume
            if (br != null) {
                s = br.readLine();
            }
            if (s != null) {
                gp.se.volumeScale = Integer.parseInt(s);
            }

            if (br != null) {
                br.close();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}

package Main;

import object.OBJ_Key;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class UI {
    private Font pixelatedFont, pixelatedFont_80Bold;
    GamePanel gp;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageTimer = 0;
    public boolean gameFinished = false;
    double playTime;

    public UI() {
        try {
            Image keyImage1 = ImageIO.read(new File("/Res/objects/key.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public UI(GamePanel gp) {
        this.gp = gp;
        try {
            pixelatedFont = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(getClass().getResourceAsStream("/Res/fonts/PixelatedDisplay.ttf"))).deriveFont(40f);
            pixelatedFont_80Bold = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(getClass().getResourceAsStream("/Res/fonts/PixelatedDisplay.ttf"))).deriveFont(Font.BOLD, 80f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(pixelatedFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw (Graphics2D g2) {
        // Check game if finished
        if (gameFinished) {
            g2.setFont(pixelatedFont);
            g2.setColor(Color.WHITE);

            String text;
            int textLength;
            int x;
            int y;

            text = "You found the treasure!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.ScreenWidth / 2 - textLength / 2;
            y = gp.ScreenHeight / 2 - (gp.tileSize * 3);
            g2.drawString(text, x, y);

            g2.setFont(pixelatedFont_80Bold);
            g2.setColor(Color.YELLOW);
            text = "CONGRATULATIONS! ";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.ScreenWidth / 2 - textLength / 2;
            y = gp.ScreenHeight / 2 + (gp.tileSize * 2);
            g2.drawString(text, x, y);

            gp.gameThread = null;

        } else {
            g2.setFont(pixelatedFont);
            g2.setColor(Color.WHITE);

            int newWidth = keyImage.getWidth(null) * 2;
            int newHeight = keyImage.getHeight(null) * 2;
            int xPosition = 20;
            int yPosition = 50;

            int imageYPosition = yPosition - newHeight / 2 + g2.getFontMetrics().getAscent() / 2;

            g2.drawImage(keyImage, xPosition, imageYPosition, newWidth,newHeight, null);

            FontMetrics fm = g2.getFontMetrics();
            int textYPosition = yPosition + fm.getAscent() - (fm.getAscent() + fm.getDescent()) / 2;

            g2.drawString("x " + gp.player.hasKey, xPosition + newWidth + 8, textYPosition + 15);

            // Timer
            playTime += (double) 1/ 60;
            g2.drawString("Time: " + (int)playTime, gp.ScreenWidth - 200, 50);

            // Message
            if (messageOn) {
                g2.setFont(g2.getFont().deriveFont(30f));
                g2.drawString(message, gp.tileSize / 2, gp.tileSize * 5);
                messageTimer++;
                if (messageTimer > 120) {
                    messageOn = false;
                    messageTimer = 0;
                }
            }
        }


    }
}

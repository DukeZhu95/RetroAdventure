package Main;

import object.OBJ_Key;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Objects;

public class UI {
    private Font pixelatedFont, pixelatedFont_80Bold;
    GamePanel gp;
    Graphics2D g2;
    public boolean messageOn = false;
    public String message = "";

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
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw (Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(pixelatedFont);
        g2.setColor(Color.WHITE);

        if (gp.gameState == gp.playState) {
            // Do playState stuff later
        }
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }
    }

    public void drawPauseScreen() {
        g2.setFont(pixelatedFont_80Bold);
        String text = "Paused";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);
    }

    public int getXforCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return (gp.screenWidth / 2) - (length / 2);
    }
}

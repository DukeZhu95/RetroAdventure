package Main;

import Entity.Entity;
import object.OBJ_Heart;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class UI {
    private Font pixelatedFont, pixelatedFont_80Bold;
    GamePanel gp;
    Graphics2D g2;
    BufferedImage heart_full, heart_half, heart_empty;
    public boolean messageOn = false;
    public String message = "";
    public String currentDialogue = "";
    public int commandNum = 0;
    public int titleScreenState = 0; // 0 = the 1st screen, 1 = the 2nd screen

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

        // Create HUD objects
        Entity heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_empty = heart.image3;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw (Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(pixelatedFont);
        g2.setColor(Color.WHITE);

        // Title state
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

        // Play state
        if (gp.gameState == gp.playState) {
            drawPlayerLife();
        }
        // Pause state
        else
        if (gp.gameState == gp.pauseState) {
            drawPlayerLife();
            drawPauseScreen();
        }
        // Dialogue state
        if (gp.gameState == gp.dialogueState) {
            drawPlayerLife();
            drawDialogueScreen();
        }
        // Character state
        if (gp.gameState == gp.characterState) {
            drawCharacterScreen();
        }
    }

    public void drawPlayerLife() {
        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int i = 0;

        // Draw blank hearts
        while(i < gp.player.maxLife / 2) {
            g2.drawImage(heart_empty, x, y, null);
            i++;
            x += (int) (gp.tileSize * 0.5);
        }

        // Reset
        x = gp.tileSize / 2;
        y = gp.tileSize / 2;
        i = 0;

        // Draw current life
        while(i < gp.player.life) {
            g2.drawImage(heart_half, x, y, null);
            i++;
            if (i < gp.player.life) {
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += (int) (gp.tileSize * 0.5);
        }
    }

    public void drawTitleScreen() {
        if (titleScreenState == 0) {
            g2.setColor(new Color(0, 0, 0));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            // Title name
            g2.setFont(pixelatedFont_80Bold.deriveFont(Font.BOLD, 112f));
            String text = "Retro Adventure";
            int x = getXforCenteredText(text);
            int y = gp.tileSize * 3;

            // Shadow
            g2.setColor(Color.GRAY);
            g2.drawString(text, x + 6, y + 6);

            // Main color
            g2.setColor(Color.WHITE);
            g2.drawString(text, x, y);

            // Tigger image
            x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
            y += gp.tileSize;
            g2.drawImage(gp.npcTigger.down2, x, y, gp.tileSize * 2,gp.tileSize * 2,null);

            // Menu
            g2.setFont(new Font("Comic Sans MS", Font.BOLD, 48));

            text = "New Game";
            x = getXforCenteredText(text);
            y += gp.tileSize * 3;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "Load Game";
            x = getXforCenteredText(text);
            y += (int) (gp.tileSize * 0.75);
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "Exit";
            x = getXforCenteredText(text);
            y += (int) (gp.tileSize * 0.75);
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x - gp.tileSize, y);
            }
        }
        else if (titleScreenState == 1) {
            // Role selection
            g2.setColor(Color.WHITE);

            String text = "Select Your Gender";
            g2.setFont(pixelatedFont_80Bold.deriveFont(Font.PLAIN, 112f));
            int x = getXforCenteredText(text);
            int y = gp.tileSize * 3;
            g2.drawString(text, x, y);

            text = "Male";
            g2.setFont(new Font("Comic Sans MS", Font.BOLD, 48));
            x = getXforCenteredText(text);
            y += gp.tileSize * 3;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "Female";
            g2.setFont(new Font("Comic Sans MS", Font.BOLD, 48));
            x = getXforCenteredText(text);
            y += (int) (gp.tileSize * 0.75);
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "Back";
            g2.setFont(new Font("Comic Sans MS", Font.BOLD, 48));
            x = getXforCenteredText(text);
            y += (int) (gp.tileSize * 0.75);
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x - gp.tileSize, y);
            }
        }
    }

    public void drawPauseScreen() {
        g2.setFont(pixelatedFont_80Bold);
        String text = "Paused";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);
    }

    public void drawDialogueScreen() {
        // Window
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 3;

        drawSubWindow(x, y, width, height);

        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32f));
        x += gp.tileSize;
        y += gp.tileSize;

        for(String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }
    public void drawCharacterScreen() {

        // Create a frame
        final int frameX = gp.tileSize * 2;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize * 5;
        final int frameHeight = gp.tileSize * 10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // Text
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32f));
        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 32;

        // Names
        g2.drawString("Lv.", textX, textY);
        textY += lineHeight;
        g2.drawString("HP: ", textX, textY);
        textY += lineHeight;
        g2.drawString("Strength: ", textX, textY);
        textY += lineHeight;
        g2.drawString("Dexterity: ", textX, textY);
        textY += lineHeight;
        g2.drawString("Attack: ", textX, textY);
        textY += lineHeight;
        g2.drawString("Defense: ", textX, textY);
        textY += lineHeight;
        g2.drawString("EXP: ", textX, textY);
        textY += lineHeight;
        g2.drawString("Next Lv.", textX, textY);
        textY += lineHeight;
        g2.drawString("Gold: ", textX, textY);
        textY += lineHeight;
        g2.drawString("Weapon: ", textX, textY);
        textY += lineHeight;
        g2.drawString("Armor: ", textX, textY);
        textY += lineHeight;
    }

    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255 , 100);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.fillRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);


    }

    public int getXforCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return (gp.screenWidth / 2) - (length / 2);
    }
}

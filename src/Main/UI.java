package Main;

import Entity.Entity;
import object.OBJ_Heart;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class UI {
    private Font pixelatedFont, pixelatedFont_80Bold;
    GamePanel gp;
    Graphics2D g2;
    BufferedImage heart_full, heart_half, heart_empty;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public String currentDialogue = "";
    public int commandNum = 0;
    public int titleScreenState = 0; // 0 = the 1st screen, 1 = the 2nd screen
    public int slotColumn = 0;
    public int slotRow = 0;
    int subState = 0;

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

    public void addMessage(String text) {
        message.add(text);
        messageCounter.add(0);
    }

    public void draw (Graphics2D g2) {
//        System.out.println("UI draw method called. Current gameState: " + gp.gameState);
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
            drawMessage();
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
            drawInventory();
        }
        // Option state
        if (gp.gameState == gp.optionState) {
            drawOptionScreen();
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

    public void drawMessage() {
        int messageX = gp.tileSize;
        int messageY = gp.tileSize * 4;
        g2.setFont(pixelatedFont.deriveFont(Font.PLAIN, 32f));

        for (int i = 0; i < message.size(); i++) {
            if (message.get(i) != null) {
                g2.setColor(Color.BLACK);
                g2.drawString(message.get(i), messageX + 2, messageY + 2);
                g2.setColor(Color.WHITE);
                g2.drawString(message.get(i), messageX, messageY);

                int counter = messageCounter.get(i) + 1; // messageCounter++
                messageCounter.set(i, counter); // Set the counter to the array
                messageY += 50;

                if (messageCounter.get(i) > 180) {
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
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
        final int lineHeight = 35;

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
        textY += lineHeight + 50;
        g2.drawString("Weapon: ", textX, textY);
        textY += lineHeight + 40;
        g2.drawString("Armor: ", textX, textY);
        textY += lineHeight;

        // Values
        int tailX = frameX + frameWidth - 30;
        // Reset textY
        textY = frameY + gp.tileSize;
        String value;

        value = String.valueOf(gp.player.level);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.strength);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.dexterity);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.attack);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.defense);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.exp);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.nextLevelExp);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.coin);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY - 15, null);
        textY += gp.tileSize;

        g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY - 15, null);
        textY += gp.tileSize;
    }

    public void drawInventory() {
        // Frame
        int frameX = gp.tileSize * 9;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize * 6;
        int frameHeight = gp.tileSize * 5;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // Slot
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.tileSize + 12;

        // Draw player's items
        for (int i = 0; i < gp.player.inventory.size(); i++) {

            // Equip cursor
            if (gp.player.inventory.get(i) == gp.player.currentWeapon || gp.player.inventory.get(i) == gp.player.currentShield) {

                g2.setColor(new Color(240,190,90));
                g2.fillRoundRect(slotX, slotY, slotSize, slotSize, 10, 10);
            }
                g2.setColor(Color.BLACK);
                g2.setStroke(new BasicStroke(3));
                g2.drawRoundRect(slotX, slotY, slotSize, slotSize, 10, 10);
                g2.drawImage(gp.player.inventory.get(i).down1, slotX, slotY, null);
                slotX += slotSize;
                if (i == 4 || i == 9 || i == 14) {
                    slotX = slotXstart;
                    slotY += slotSize;
                }
        }

        // Cursor
        int cursorX = slotXstart + (slotSize * slotColumn);
        int cursorY = slotYstart + (slotSize * slotRow);
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;

        // Description frame
        int dFrameX = frameX;
        int dFrameY = frameY + frameHeight;
        int dFrameWidth = frameWidth;
        int dFrameHeight = gp.tileSize * 3;


        // Description text
        int textX = dFrameX + 20;
        int textY = dFrameY + gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28f));

        int itemIndex = getItemIndexOnSlot();

        if (itemIndex < gp.player.inventory.size()) {
            drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
            for (String line : gp.player.inventory.get(itemIndex).description.split("\n")) {
                g2.drawString(line, textX, textY);
                textY += 40;
            }
        }
        else {
            g2.drawString("Empty", textX, textY);
        }

        // Draw cursor
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
    }

    public void drawOptionScreen() {
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40f));

        // Sub window
        int frameX = gp.tileSize * 5 - 20;
        int frameY = (int) (gp.tileSize * 0.5);
        int frameWidth = gp.tileSize * 6 + 20;
        int frameHeight = gp.tileSize * 8;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        switch (subState) {
            case 0: option_top(frameX, frameY); break;
            case 1: option_control(frameX, frameY); break;
            case 2: option_quitGame(frameX, frameY); break;
        }

        gp.keyboardHandler.enterPressed = false;
    }

    public void option_top(int frameX, int frameY) {
        int textX;
        int textY;

        // Title
        String text = "Options";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

//        // Full-screen ON/OFF
//        text = "Full Screen";
//        textX = frameX + gp.tileSize;
//        textY += gp.tileSize * 2;
//        g2.drawString(text, textX, textY);
//        if (commandNum == 0) {
//            g2.drawString(">", textX - 32, textY);
//            if (gp.keyboardHandler.enterPressed) {
//                gp.fullScreenOn = !gp.fullScreenOn;
//
//                subState = 1;
//            }
//
//        }

        // Music
        text = "Music";
        textX = frameX + gp.tileSize;
        textY += gp.tileSize * 2;
        g2.drawString(text, textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 32, textY);
        }

        // SE
        text = "Sound Effect";
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX - 32, textY);
        }

        // Control
        text = "Control";
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if (commandNum == 2) {
            g2.drawString(">", textX - 32, textY);
            if (gp.keyboardHandler.enterPressed) {
                subState = 1;
                commandNum = 0;
            }
        }

        // Quit game
        text = "Quit Game";
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if (commandNum == 3) {
            g2.drawString(">", textX - 32, textY);
            if (gp.keyboardHandler.enterPressed) {
                subState = 2;
                commandNum = 0;
            }
        }

//        // Full-screen check box
//        textX = (int) (frameX + gp.tileSize * 4.5);
//        textY = frameY + gp.tileSize * 2 + 68;
//        g2.setStroke(new BasicStroke(3));
//        g2.drawRect(textX, textY, 24, 24);
//        if (gp.fullScreenOn) {
//            g2.fillRect(textX, textY, 24, 24);
//        }

        // Music volume
        textX = (int) (frameX + gp.tileSize * 4.5);
        textY = frameY + gp.tileSize * 2 + 68;
        g2.drawRect(textX, textY, 120, 24);
        int volumeWidth = 24 * gp.music.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);

        // SE volume
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, 24); // 120 = 24 * 5
        volumeWidth = 24 * gp.se.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);

        gp.config.saveConfig();
    }

    public void option_control(int frameX, int frameY) {
        int textX;
        int textY;

        // Title
        String text = "Keyboard Control Setting";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("Move", textX, textY); textY += gp.tileSize;
        g2.drawString("Attack", textX, textY); textY += gp.tileSize;
        g2.drawString("Interact", textX, textY); textY += gp.tileSize;
        g2.drawString("Inventory", textX, textY); textY += gp.tileSize;
        g2.drawString("Pause", textX, textY); textY += gp.tileSize;

        textX = frameX + gp.tileSize * 4;
        textY = frameY + gp.tileSize * 2;
        g2.drawString("WASD", textX, textY); textY += gp.tileSize;
        g2.drawString("J", textX, textY); textY += gp.tileSize;
        g2.drawString("Enter", textX, textY); textY += gp.tileSize;
        g2.drawString("C", textX, textY); textY += gp.tileSize;
        g2.drawString("Space", textX, textY); textY += gp.tileSize;

        // Back
        textX = frameX + gp.tileSize;
        textY = frameY + gp.tileSize * 7;
        g2.drawString("Back", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyboardHandler.enterPressed) {
                subState = 0;
                commandNum = 3;
            }
        }
    }

    private void option_quitGame(int frameX, int frameY) {
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize * 3;

        currentDialogue = "Are you sure you \nwant to quit the \ngame and return to \nthe main menu?";

        for (String line: currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        // Yes
        String text = "Yes";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize * 6;
        g2.drawString(text, textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyboardHandler.enterPressed) {
                subState = 0;
                gp.gameState = gp.titleState;
                titleScreenState = 0;
            }
        }

        // No
        text = "No";
        textX = getXforCenteredText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyboardHandler.enterPressed) {
                subState = 0;
                commandNum = 4;
            }
        }
    }

    public int getItemIndexOnSlot() {
        return slotColumn + (slotRow * 5);
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

    public int getXforAlignToRightText(String text, int tailX) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length;
        return x;
    }
}

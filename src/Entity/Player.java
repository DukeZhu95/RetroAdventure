package Entity;

import Main.GamePanel;
import Main.KeyboardHandler;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{
    KeyboardHandler KeyboardHandler;
    public final int screenX;
    public final int screenY;
    private boolean incrementing = true;

    public Player (GamePanel gp, KeyboardHandler KeyboardHandler) {
        super(gp);
        this.KeyboardHandler = KeyboardHandler;

        screenX = gp.screenWidth / 2 - gp.tileSize / 2;
        screenY = gp.screenHeight / 2 - gp.tileSize / 2;

        solidArea = new Rectangle();
        solidArea.x = 16;
        solidArea.y = 40;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 64;
        solidArea.height = 60;

        // Modify the solid area to make it smaller
        solidArea.x += 10;
        solidArea.y += 10;
        solidArea.width -= 5;
        solidArea.height -= 10;


        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 5;
        direction = "down";
        spriteNum = 2;
    }

    public void getPlayerImage() {
        // Male Player
        up1 = setup("/Res/player/Male_back_left");
        up2 = setup("/Res/player/Male_back_stand");
        up3 = setup("/Res/player/Male_back_right");
        down1 = setup("/Res/player/Male_forward_left");
        down2 = setup("/Res/player/Male_stand");
        down3 = setup("/Res/player/Male_forward_right");
        left1 = setup("/Res/player/Male_left_handforward");
        left2 = setup("/Res/player/Male_left_stand");
        left3 = setup("/Res/player/Male_left_handback");
        right1 = setup("/Res/player/Male_right_handback");
        right2 = setup("/Res/player/Male_right_stand");
        right3 = setup("/Res/player/Male_right_handforward");

        // Female Player
        up1 = setup("/Res/player/Female_back_left");
        up2 = setup("/Res/player/Female_back_stand");
        up3 = setup("/Res/player/Female_back_right");
        down1 = setup("/Res/player/Female_forward_left");
        down2 = setup("/Res/player/Female_stand");
        down3 = setup("/Res/player/Female_forward_right");
        left1 = setup("/Res/player/Female_left_handforward");
        left2 = setup("/Res/player/Female_left_stand");
        left3 = setup("/Res/player/Female_left_handback");
        right1 = setup("/Res/player/Female_right_handback");
        right2 = setup("/Res/player/Female_right_stand");
        right3 = setup("/Res/player/Female_right_handforward");
    }

    public void update() {
        if (KeyboardHandler.upPressed || KeyboardHandler.downPressed || KeyboardHandler.leftPressed || KeyboardHandler.rightPressed) {

            if (KeyboardHandler.upPressed) {
                direction = "up";
            }
            if (KeyboardHandler.downPressed) {
                direction = "down";
            }
            if (KeyboardHandler.leftPressed) {
                direction = "left";
            }
            if (KeyboardHandler.rightPressed) {
                direction = "right";
            }

            // Check for collision
            collisionOn = false;
            gp.collisionChecker.checkTile(this);

            // Check for collision with objects
            int objIndex = gp.collisionChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // Check for collision with NPC
            int npcIndex = gp.collisionChecker.chekcEntity(this, gp.npc);
            interactWithNpc(npcIndex);

            // If collision is not detected, move the player
            if (!collisionOn) {

                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }

            // Animate the player
            spriteCounter++;
            if (spriteCounter > 15) {
                if (incrementing) {
                    if (spriteNum == 1) {
                        spriteNum = 2;
                    } else if (spriteNum == 2) {
                        spriteNum = 3;
                    } else if (spriteNum == 3) {
                        spriteNum = 2;
                        incrementing = false;  // Start decrementing
                    }
                } else {
                    if (spriteNum == 2) {
                        spriteNum = 1;
                        incrementing = true;  // Start incrementing again
                    }
                }
                spriteCounter = 0;
            }
        } else {
            spriteNum = 2;  // cease movement
        }
    }

    public void pickUpObject(int i) {
        if (i != 999) {

        }
    }

    public void interactWithNpc(int i) {
        if (i != 999) {
            if (gp.keyboardHandler.enterPressed) {
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        }
        gp.keyboardHandler.enterPressed = false;
    }


    public void draw(Graphics2D g2) {
//        g2.setColor(Color.WHITE);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        switch (direction) {
            case "up" -> {
                if (spriteNum == 1) {
                    image = up1;
                } else if (spriteNum == 2) {
                    image = up2;
                } else if (spriteNum == 3) {
                    image = up3;
                }
            }
            case "down" -> {
                if (spriteNum == 1) {
                    image = down1;
                } else if (spriteNum == 2) {
                    image = down2;
                } else if (spriteNum == 3) {
                    image = down3;
                }
            }
            case "left" -> {
                if (spriteNum == 1) {
                    image = left1;
                } else if (spriteNum == 2) {
                    image = left2;
                } else if (spriteNum == 3) {
                    image = left3;
                }
            }
            case "right" -> {
                if (spriteNum == 1) {
                    image = right1;
                } else if (spriteNum == 2) {
                    image = right2;
                } else if (spriteNum == 3) {
                    image = right3;
                }
            }
        }

        g2.drawImage(image, screenX, screenY, null);

    }


}

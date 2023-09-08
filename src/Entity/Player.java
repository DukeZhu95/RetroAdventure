package Entity;

import Main.GamePanel;
import Main.KeyboardHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{
    GamePanel gp;
    KeyboardHandler KeyboardHandler;
    public final int screenX;
    public final int screenY;
    public int hasKey = 0;

    public Player (GamePanel gp, KeyboardHandler KeyboardHandler) {
        this.gp = gp;
        this.KeyboardHandler = KeyboardHandler;

        screenX = gp.ScreenWidth / 2 - gp.tileSize / 2;
        screenY = gp.ScreenHeight / 2 - gp.tileSize / 2;

        solidArea = new Rectangle();
        solidArea.x = 16;
        solidArea.y = 40;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 64;
        solidArea.height = 60;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 5;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Res/player/Male_back_left.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Res/player/Male_back_stand.png")));
            up3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Res/player/Male_back_right.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Res/player/Male_forward_left.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Res/player/Male_stand.png")));
            down3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Res/player/Male_forward_right.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Res/player/Male_left_handforward.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Res/player/Male_left_stand.png")));
            left3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Res/player/Male_left_handback.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Res/player/Male_right_handback.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Res/player/Male_right_stand.png")));
            right3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Res/player/Male_right_handforward.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
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

            String objectName = gp.obj[i].name;

            switch (objectName) {
                case "Key" -> {
                    gp.playSE(1);
                    gp.obj[i] = null;
                    gp.ui.showMessage("You picked up a key!");
                    hasKey++;
                }
                case "Door" -> {
                    if (hasKey > 0) {
                        gp.playSE(3);
                        gp.obj[i] = null;
                        gp.ui.showMessage("You used a key to open the door!");
                        hasKey--;
                    } else {
                        gp.ui.showMessage("You need a key to open this door!");
                    }

                }
                case "Boots" -> {
                    gp.playSE(2);
                    gp.obj[i] = null;
                    gp.ui.showMessage("You picked up boots! You can now move faster.");
                    speed = 7;
                }
                case "Chest" -> {
                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                    gp.playSE(4);

//                    gp.playSE(4);
//                    gp.obj[i] = null;
//                    gp.ui.showMessage("You opened the chest and found a key!");
                }
            }

//            String objectName = gp.obj[i].name;
//
//            switch (objectName) {
//                case "key" -> {
//                    gp.obj[i] = null;
//                    hasKey++;
//                }
//                case "door" -> {
//                    if (hasKey > 0) {
//                        gp.obj[i] = null;
//                        hasKey--;
//                    }
//                }
//
//            }
        }
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

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

        // Draw collision area for testing
        g2.setColor(Color.RED); // 设置碰撞矩形的颜色为红色
        g2.drawRect(worldX + solidArea.x, worldY + solidArea.y, solidArea.width, solidArea.height);
    }

    private boolean incrementing = true;
}

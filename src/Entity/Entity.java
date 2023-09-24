package Entity;

import Main.GamePanel;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Entity {
    GamePanel gp;
    public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
    public BufferedImage MaleAttackUp1, MaleAttackUp2, MaleAttackDown1, MaleAttackDown2, MaleAttackLeft1, MaleAttackLeft2, MaleAttackRight1, MaleAttackRight2;
    public BufferedImage FemaleAttackUp1, FemaleAttackUp2, FemaleAttackDown1, FemaleAttackDown2, FemaleAttackLeft1, FemaleAttackLeft2, FemaleAttackRight1, FemaleAttackRight2;
    public BufferedImage image, image2, image3;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public Rectangle attackArea = new Rectangle(0,0,0,0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false;
    String[] dialogues = new String[20];
    private boolean incrementing = true;

    // State
    public int worldX, worldY;
    public String direction = "down";
    public int spriteNum = 1;
    int dialogueIndex = 0;
    public boolean collisionOn = false;
    public boolean invincible = false;
    boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    boolean hpBarOn = false;

    // Counter
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    int dyingCounter = 0;
    int hpBarCounter = 0;

    // Character attributes
    public int type; // 0: Player, 1: NPC, 2: Monster
    public String name;
    public int speed;
    public int maxLife;
    public int life;
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defense;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public Entity currentWeapon;
    public Entity currentShield;

    // Item attributes
    public int attackValue;
    public int defenseValue;
    public String description = "";

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void setAction() {}
    public void damageReaction() {}
    public void speak() {
        if (dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        switch (gp.player.direction) {
            case "up" -> direction = "down";
            case "down" -> direction = "up";
            case "left" -> direction = "right";
            case "right" -> direction = "left";
        }
    }
    public void update() {

        setAction();

        collisionOn = false;
        gp.collisionChecker.checkTile(this);
        gp.collisionChecker.checkObject(this, false);
        gp.collisionChecker.checkEntity(this, gp.npc);
        gp.collisionChecker.checkEntity(this, gp.monster);
        boolean contactPlayer = gp.collisionChecker.checkPlayer(this);

        if (this.type == 2 && contactPlayer) {
            if (!gp.player.invincible) {
                // We can give damage
                gp.playSE(6);

                int damage = attack - gp.player.defense;
                if (damage < 0) {
                    damage = 0;
                }

                gp.player.life -= damage;
                gp.player.invincible = true;
            }
        }

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

        if(invincible){
            invincibleCounter++;
            if(invincibleCounter > 40){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            if (attacking) {
                System.out.println("Attacking!");
                image = switch (direction) {
                    case "up" ->
                            spriteNum == 1 ? (gp.isMale ? MaleAttackUp1 : FemaleAttackUp1) : (gp.isMale ? MaleAttackUp2 : FemaleAttackUp2);
                    case "down" ->
                            spriteNum == 1 ? (gp.isMale ? MaleAttackDown1 : FemaleAttackDown1) : (gp.isMale ? MaleAttackDown2 : FemaleAttackDown2);
                    case "left" ->
                            spriteNum == 1 ? (gp.isMale ? MaleAttackLeft1 : FemaleAttackLeft1) : (gp.isMale ? MaleAttackLeft2 : FemaleAttackLeft2);
                    case "right" ->
                            spriteNum == 1 ? (gp.isMale ? MaleAttackRight1 : FemaleAttackRight1) : (gp.isMale ? MaleAttackRight2 : FemaleAttackRight2);
                    default -> image;
                };
            } else {
                switch (direction) {
                    case "up":
                        if (spriteNum == 1) {image = up1;}
                        else if (spriteNum == 2) {image = up2;}
                        else if (spriteNum == 3) {image = up3;}
                        break;
                    case "down":
                        if (spriteNum == 1) {image = down1;}
                        else if (spriteNum == 2) {image = down2;}
                        else if (spriteNum == 3) {image = down3;}
                        break;
                    case "left":
                        if (spriteNum == 1) {image = left1;}
                        else if (spriteNum == 2) {image = left2;}
                        else if (spriteNum == 3) {image = left3;}
                        break;
                    case "right":
                        if (spriteNum == 1) {image = right1;}
                        else if (spriteNum == 2) {image = right2;}
                        else if (spriteNum == 3) {image = right3;}
                        break;
                }
            }

            // Monster HP bar
            if (type == 2 && hpBarOn) {
                g2.setColor(new Color(35, 35, 35));
                g2.fillRect(screenX - 1, screenY - 16, gp.tileSize + 2, 12);
                g2.setColor(new Color(255, 0, 30));
                g2.fillRect(screenX, screenY - 15, gp.tileSize, 10);
                g2.setColor(new Color(0, 255, 0));
                g2.fillRect(screenX, screenY - 15, (int) (gp.tileSize * ((double) life / (double) maxLife)), 10);

                hpBarCounter++;

                if (hpBarCounter > 600) {
                    hpBarOn = false;
                    hpBarCounter = 0;
                }
            }

            if (invincible) {
                hpBarOn = true;
                hpBarCounter = 0;
                changeAlpha(g2,0.4F);
            }
            if (dying) {
                dyingAnimation(g2);
            }
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            changeAlpha(g2,1F);
        }

    }

    public void dyingAnimation(Graphics2D g2) {
        dyingCounter++;
        int i = 5;
        if (dyingCounter <= i) {changeAlpha(g2, 0f);}
        if (dyingCounter > i && dyingCounter <= i * 2) {changeAlpha(g2, 1f);}
        if (dyingCounter > i * 2 && dyingCounter <= i * 3) {changeAlpha(g2, 0f);}
        if (dyingCounter > i * 3 && dyingCounter <= i * 4) {changeAlpha(g2, 1f);}
        if (dyingCounter > i * 4 && dyingCounter <= i * 5) {changeAlpha(g2, 0f);}
        if (dyingCounter > i * 5 && dyingCounter <= i * 6) {changeAlpha(g2, 1f);}
        if (dyingCounter > i * 6 && dyingCounter <= i * 7) {changeAlpha(g2, 0f);}
        if (dyingCounter > i * 7 && dyingCounter <= i * 8) {changeAlpha(g2, 1f);}
        if (dyingCounter > 40) {
            dying = false;
            alive = false;
        }
    }

    public void changeAlpha(Graphics2D g2, float alphaValue) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }

    public BufferedImage setup(String imagePath, int width, int height) {

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            image = uTool.scaleImage(image, width, height);
        }catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}

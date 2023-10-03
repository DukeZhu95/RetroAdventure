package Entity;

import Main.GamePanel;
import Main.KeyboardHandler;
import object.OBJ_Key;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Wood;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Player extends Entity{
    KeyboardHandler KeyboardHandler;
    public final int screenX;
    public final int screenY;
    private boolean incrementing = true;
    private int attackCounter = 0;
    boolean hasDamagedMonster = false;


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

//        attackArea.width = 70;
//        attackArea.height = 70;

        // Modify the solid area to make it smaller
        solidArea.x += 10;
        solidArea.y += 10;
        solidArea.width -= 5;
        solidArea.height -= 10;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
        setItems();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
//        worldX = gp.tileSize * 12;
//        worldY = gp.tileSize * 12;
        speed = 5;
        direction = "down";
        spriteNum = 2;

        // Character status
        level = 1;
        maxLife = 6;
        life = maxLife;
        strength = 1;
        dexterity = 1;
        exp = 0;
        nextLevelExp = 10;
        coin = 500;
//        coin = 0;
        currentWeapon = new OBJ_Sword_Wood(gp);
        currentShield = new OBJ_Shield_Wood(gp);
        attack = getAttack();
        defense = getDefense();
    }

    public void setItems() {
        inventory.clear();
        inventory.add(currentWeapon);
        inventory.add(currentShield);
        inventory.add(new OBJ_Key(gp));
    }

    public int getAttack() {
        attackArea = currentWeapon.attackArea;
        return attack = strength + currentWeapon.attackValue;
    }

    public int getDefense() {
        return defense = dexterity + currentShield.defenseValue;
    }

    public void getPlayerImage() {
        if (gp.isMale) {
            // Male Player
            up1 = setup("/Res/player/Male_back_left", gp.tileSize, gp.tileSize);
            up2 = setup("/Res/player/Male_back_stand", gp.tileSize, gp.tileSize);
            up3 = setup("/Res/player/Male_back_right", gp.tileSize, gp.tileSize);
            down1 = setup("/Res/player/Male_forward_left", gp.tileSize, gp.tileSize);
            down2 = setup("/Res/player/Male_stand", gp.tileSize, gp.tileSize);
            down3 = setup("/Res/player/Male_forward_right", gp.tileSize, gp.tileSize);
            left1 = setup("/Res/player/Male_left_handforward", gp.tileSize, gp.tileSize);
            left2 = setup("/Res/player/Male_left_stand", gp.tileSize, gp.tileSize);
            left3 = setup("/Res/player/Male_left_handback", gp.tileSize, gp.tileSize);
            right1 = setup("/Res/player/Male_right_handback", gp.tileSize, gp.tileSize);
            right2 = setup("/Res/player/Male_right_stand", gp.tileSize, gp.tileSize);
            right3 = setup("/Res/player/Male_right_handforward", gp.tileSize, gp.tileSize);
        } else {
            // Female Player
            up1 = setup("/Res/player/Female_back_left", gp.tileSize, gp.tileSize);
            up2 = setup("/Res/player/Female_back_stand", gp.tileSize, gp.tileSize);
            up3 = setup("/Res/player/Female_back_right", gp.tileSize, gp.tileSize);
            down1 = setup("/Res/player/Female_forward_left", gp.tileSize, gp.tileSize);
            down2 = setup("/Res/player/Female_stand", gp.tileSize, gp.tileSize);
            down3 = setup("/Res/player/Female_forward_right", gp.tileSize, gp.tileSize);
            left1 = setup("/Res/player/Female_left_handforward", gp.tileSize, gp.tileSize);
            left2 = setup("/Res/player/Female_left_stand", gp.tileSize, gp.tileSize);
            left3 = setup("/Res/player/Female_left_handback", gp.tileSize, gp.tileSize);
            right1 = setup("/Res/player/Female_right_handback", gp.tileSize, gp.tileSize);
            right2 = setup("/Res/player/Female_right_stand", gp.tileSize, gp.tileSize);
            right3 = setup("/Res/player/Female_right_handforward", gp.tileSize, gp.tileSize);
        }
    }

    public void getPlayerAttackImage() {
//        System.out.println("getPlayerAttackImage() called");
        if (gp.isMaleAttacking) {
            // Male Player Attack
            MaleAttackUp1 = setup("/Res/player/Male_attack_up1", gp.tileSize, gp.tileSize * 2);
            MaleAttackUp2 = setup("/Res/player/Male_attack_up2", gp.tileSize, gp.tileSize * 2);
            MaleAttackDown1 = setup("/Res/player/Male_attack_down1", gp.tileSize, gp.tileSize * 2);
            MaleAttackDown2 = setup("/Res/player/Male_attack_down2", gp.tileSize, gp.tileSize * 2);
            MaleAttackLeft1 = setup("/Res/player/Male_attack_left1", gp.tileSize * 2, gp.tileSize);
            MaleAttackLeft2 = setup("/Res/player/Male_attack_left2", gp.tileSize * 2, gp.tileSize);
            MaleAttackRight1 = setup("/Res/player/Male_attack_right1", gp.tileSize * 2, gp.tileSize);
            MaleAttackRight2 = setup("/Res/player/Male_attack_right2", gp.tileSize * 2, gp.tileSize);
        } else {
            // Female Player Attack
            FemaleAttackUp1 = setup("/Res/player/Female_attack_up1", gp.tileSize, gp.tileSize * 2);
            FemaleAttackUp2 = setup("/Res/player/Female_attack_up2", gp.tileSize, gp.tileSize * 2);
            FemaleAttackDown1 = setup("/Res/player/Female_attack_down1", gp.tileSize, gp.tileSize * 2);
            FemaleAttackDown2 = setup("/Res/player/Female_attack_down2", gp.tileSize, gp.tileSize * 2);
            FemaleAttackLeft1 = setup("/Res/player/Female_attack_left1", gp.tileSize * 2, gp.tileSize);
            FemaleAttackLeft2 = setup("/Res/player/Female_attack_left2", gp.tileSize * 2, gp.tileSize);
            FemaleAttackRight1 = setup("/Res/player/Female_attack_right1", gp.tileSize * 2, gp.tileSize);
            FemaleAttackRight2 = setup("/Res/player/Female_attack_right2", gp.tileSize * 2, gp.tileSize);
        }
//        System.out.println("getPlayerAttackImage() finished");
    }

    public void update() {
        if (attacking) {
            attacking();
        }

        if (KeyboardHandler.attackPressed) {
            attacking = true;
            gp.playSE(7);
            attacking();
            KeyboardHandler.attackPressed = false;
            attackCounter = 0;

        } else if (KeyboardHandler.upPressed || KeyboardHandler.downPressed || KeyboardHandler.leftPressed || KeyboardHandler.rightPressed || KeyboardHandler.enterPressed) {
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
            int npcIndex = gp.collisionChecker.checkEntity(this, gp.npc);
            interactWithNpc(npcIndex);

            // Check for collision with monster
            int monsterIndex = gp.collisionChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            // Check event
            gp.eventHandler.checkEvent();

            // If a collision is not detected, move the player
            if (!collisionOn && !KeyboardHandler.enterPressed) {

                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }
            gp.keyboardHandler.enterPressed = false;

            // Animate the player
            spriteCounter ++;
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

        // This needs to be outside key if statement
        if(invincible){
            invincibleCounter++;
            if(invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if (life > maxLife) {
            life = maxLife;
        }
        if (life <= 0) {
            gp.gameState = gp.gameOverState;
            gp.ui.commandNum = -1;
            gp.stopMusic();
            gp.playSE(10);
        }
    }

    public void attacking() {
        attackCounter += 3;
        if (attackCounter <= 15) {
            spriteNum = 1;
        }
        if (attackCounter > 15 && attackCounter <= 25) {
            spriteNum = 2;

            if (!hasDamagedMonster) {
                // Save the current worldX and worldY, and solidArea width and height
                int currentWorldX = worldX;
                int currentWorldY = worldY;
                int soildAreaWidth = solidArea.width;
                int soildAreaHeight = solidArea.height;

                // Adjust player`s worldX&Y for the attackArea
                switch (direction) {
                    case "up":
                        worldY -= attackArea.height;
                        break;
                    case "down":
                        worldY += attackArea.height;
                        break;
                    case "left":
                        worldX -= attackArea.width;
                        break;
                    case "right":
                        worldX += attackArea.width;
                        break;
                }

                // AttackArea becomes solidArea
                solidArea.width = attackArea.width;
                solidArea.height = attackArea.height;

                // Check for collision with monster with worldX and worldY, and solidArea
                int monsterIndex = gp.collisionChecker.checkEntity(this, gp.monster);
                damageMonster(monsterIndex);
                hasDamagedMonster = true;

                // After checking, restore the player`s worldX&Y and solidArea
                worldX = currentWorldX;
                worldY = currentWorldY;
                solidArea.width = soildAreaWidth;
                solidArea.height = soildAreaHeight;
            }
        }
        if (attackCounter > 25) {
            spriteNum = 1;
            attackCounter = 0;
            attacking = false;
            hasDamagedMonster = false;
        }

        // Gender-specific attack animation
        getPlayerAttackImage();
        gp.isMaleAttacking = gp.isMale;
        switch (direction) {
            case "up":
                if (gp.isMaleAttacking) {
                    image = spriteNum == 1 ? MaleAttackUp1 : MaleAttackUp2;
                } else {
                    image = spriteNum == 1 ? FemaleAttackUp1 : FemaleAttackUp2;
                }
                break;

            case "down":
                if (gp.isMaleAttacking) {
                    image = spriteNum == 1 ? MaleAttackDown1 : MaleAttackDown2;
                } else {
                    image = spriteNum == 1 ? FemaleAttackDown1 : FemaleAttackDown2;
                }
                break;

            case "left":
                if (gp.isMaleAttacking) {
                    image = spriteNum == 1 ? MaleAttackLeft1 : MaleAttackLeft2;
                } else {
                    image = spriteNum == 1 ? FemaleAttackLeft1 : FemaleAttackLeft2;
                }
                break;

            case "right":
                if (gp.isMaleAttacking) {
                    image = spriteNum == 1 ? MaleAttackRight1 : MaleAttackRight2;
                } else {
                    image = spriteNum == 1 ? FemaleAttackRight1 : FemaleAttackRight2;
                }
                break;
        }

    }

    public void pickUpObject(int i) {
        if (i != 999) {
            // Pickup only items
            if (gp.obj[gp.currentMap][i].type == type_pickupOnly) {
                gp.obj[gp.currentMap][i].use(this);
                gp.obj[gp.currentMap][i] = null;
            }
            // Inventory items
            else {
                String text;
                if (canObtainItem(gp.obj[gp.currentMap][i])) {
                    gp.playSE(1);
                    text = "You picked up a " + gp.obj[gp.currentMap][i].name + "!";
                } else {
                    text = "Your inventory is full!";
                }
                gp.ui.addMessage(text);
                gp.obj[gp.currentMap][i] = null;
            }
        }
    }

    public void interactWithNpc(int i) {
        if (gp.keyboardHandler.enterPressed) {
            if (i != 999) {
                gp.gameState = gp.dialogueState;
                gp.npc[gp.currentMap][i].speak();
            }
        }
    }

    public void contactMonster(int i) {
        if (i != 999) {
            if (!invincible && !gp.monster[gp.currentMap][i].dying) {
                gp.playSE(6);

                int damage = gp.monster[gp.currentMap][i].attack - defense;
                if (damage < 0) {
                    damage = 0;
                }
                life -= damage;
                invincible = true;
            }
        }
    }

    public void damageMonster(int i) {
//        System.out.println("damageMonster called");
        if (i != 999) {
            if (!gp.monster[gp.currentMap][i].invincible) {
                gp.playSE(5);

                int damage = attack - gp.monster[gp.currentMap][i].defense;
                if (damage < 0) {
                    damage = 0;
                }

                gp.monster[gp.currentMap][i].life -= damage;
                gp.ui.addMessage(damage + " damage!");

                gp.monster[gp.currentMap][i].invincible = true;
                gp.monster[gp.currentMap][i].damageReaction();
            }
            if (gp.monster[gp.currentMap][i].life <= 0) {
                gp.monster[gp.currentMap][i].dying = true;
                gp.ui.addMessage("killed the " + gp.monster[gp.currentMap][i].name + "!");
                gp.ui.addMessage("+ " + gp.monster[gp.currentMap][i].exp + " exp!");
                exp += gp.monster[gp.currentMap][i].exp;
                checkLevelUp();
            }
        }
    }

    public void checkLevelUp() {
        if (exp >= nextLevelExp) {
            level++;
            nextLevelExp += nextLevelExp * 2;
            maxLife += 2;
            life = maxLife;
            strength++;
            dexterity++;
            attack = getAttack();
            defense = getDefense();

            gp.playSE(8);
            gp.gameState = gp.dialogueState;
            gp.ui.currentDialogue = "You are Lv. " + level + " now!\n" + "You became stronger!";
        }
    }
    public void setDefaultPositions() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 5;
        direction = "down";
        spriteNum = 2;
    }

    public void restoreLife() {
        life = maxLife;
        invincible = false;
    }

    public void selectItem() {
        int itemIndex = gp.ui.getItemIndexOnSlot(gp.ui.playerSlotColumn, gp.ui.playerSlotRow);
        if (itemIndex < inventory.size()) {
            Entity selectedItem = inventory.get(itemIndex);
            if (selectedItem.type == type_sword || selectedItem.type == type_axe) {
                currentWeapon = selectedItem;
                attack = getAttack();
            }
            if (selectedItem.type == type_shield) {
                currentShield = selectedItem;
                defense = getDefense();
            }
            if (selectedItem.type == type_consumable) {
                selectedItem.use(this);
                if (selectedItem.amount > 1) {
                    selectedItem.amount --;
                } else {
                    inventory.remove(itemIndex);
                }
            }
        }
    }
    public int searchItemInInventory(String itemName) {
        int itemIndex = 999;
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).name.equals(itemName)) {
                itemIndex = i;
                break;
            }
        }
        return itemIndex;
    }

    public boolean canObtainItem(Entity item) {
        boolean canObtain = false;

        // Check if stackable
        if (item.stackable) {
            int index = searchItemInInventory(item.name);
            if (index != 999) {
                inventory.get(index).amount ++;
                canObtain = true;
            }
            else { // New item so need to check vacancy
                if (inventory.size() != maxInventorySize) {
                    inventory.add(item);
                    canObtain = true;
                }
            }
        }
        else { // Not stackable
            if (inventory.size() != maxInventorySize) {
                inventory.add(item);
                canObtain = true;
            }
        }
        return canObtain;
    }

    public void draw(Graphics2D g2) {
//        System.out.println("attacking: " + attacking);
//        System.out.println("spriteNum: " + spriteNum);
//        System.out.println("MaleAttackUp1: " + MaleAttackUp1);
//        System.out.println("isMaleAttacking: " + gp.isMaleAttacking);
        BufferedImage image = null;
        getPlayerAttackImage();

        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direction) {
            case "up":
                if (attacking) {
                    tempScreenY = screenY - gp.tileSize;
                    image = spriteNum == 1 ? (gp.isMaleAttacking ? MaleAttackUp1 : FemaleAttackUp1) : (gp.isMaleAttacking ? MaleAttackUp2 : FemaleAttackUp2);
                } else {
                    if (spriteNum == 1) {image = up1;}
                    else if (spriteNum == 2) {image = up2;}
                    else if (spriteNum == 3) {image = up3;}
                }
                break;
            case "down":
                if (attacking) {
                    image = spriteNum == 1 ? (gp.isMaleAttacking ? MaleAttackDown1 : FemaleAttackDown1) : (gp.isMaleAttacking ? MaleAttackDown2 : FemaleAttackDown2);
                } else {
                    if (spriteNum == 1) {image = down1;}
                    else if (spriteNum == 2) {image = down2;}
                    else if (spriteNum == 3) {image = down3;}
                }
                break;
            case "left":
                if (attacking) {
                    tempScreenX = screenX - gp.tileSize;
                    image = spriteNum == 1 ? (gp.isMaleAttacking ? MaleAttackLeft1 : FemaleAttackLeft1) : (gp.isMaleAttacking ? MaleAttackLeft2 : FemaleAttackLeft2);
                } else {
                    if (spriteNum == 1) {image = left1;}
                    else if (spriteNum == 2) {image = left2;}
                    else if (spriteNum == 3) {image = left3;}
                }
                break;
            case "right":
                if (attacking) {
                    image = spriteNum == 1 ? (gp.isMaleAttacking ? MaleAttackRight1 : FemaleAttackRight1) : (gp.isMaleAttacking ? MaleAttackRight2 : FemaleAttackRight2);
                } else {
                    if (spriteNum == 1) {image = right1;}
                    else if (spriteNum == 2) {image = right2;}
                    else if (spriteNum == 3) {image = right3;}
                }
                break;
        }

        if (invincible) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        g2.drawImage(image, tempScreenX, tempScreenY, null);

        // Reset AlphaComposite
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
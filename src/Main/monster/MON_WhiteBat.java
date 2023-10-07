package Main.monster;

import Entity.Entity;
import Main.GamePanel;
import object.OBJ_Coin;
import object.OBJ_Heart;

import java.util.Random;

public class MON_WhiteBat extends Entity {
    public MON_WhiteBat(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_monster;
        name = "WhiteBat";
        speed = 2;
        maxLife = 14;
        life = maxLife;
        attack = 6;
        defense = 0;
        exp = 5;

        solidArea.x = 6;
        solidArea.y = 30;
        solidArea.width = 84;
        solidArea.height = 40;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage() {
        up1 = setup("/Res/monsters/Bat_white_L1", gp.tileSize, gp.tileSize);
        up2 = setup("/Res/monsters/Bat_white_L2", gp.tileSize, gp.tileSize);
        up3 = setup("/Res/monsters/Bat_white_L3", gp.tileSize, gp.tileSize);
        down1 = setup("/Res/monsters/Bat_white_R1", gp.tileSize, gp.tileSize);
        down2 = setup("/Res/monsters/Bat_white_R2", gp.tileSize, gp.tileSize);
        down3 = setup("/Res/monsters/Bat_white_R3", gp.tileSize, gp.tileSize);
        left1 = setup("/Res/monsters/Bat_white_L4", gp.tileSize, gp.tileSize);
        left2 = setup("/Res/monsters/Bat_white_L5", gp.tileSize, gp.tileSize);
        left3 = setup("/Res/monsters/Bat_white_L6", gp.tileSize, gp.tileSize);
        right1 = setup("/Res/monsters/Bat_white_R4", gp.tileSize, gp.tileSize);
        right2 = setup("/Res/monsters/Bat_white_R5", gp.tileSize, gp.tileSize);
        right3 = setup("/Res/monsters/Bat_white_R6", gp.tileSize, gp.tileSize);

    }

    public void setAction() {
        actionLockCounter++;

        if (actionLockCounter == 120) {

            Random random = new Random();

            int i = random.nextInt(100) + 1;

            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75){
                direction = "right";
            }

            actionLockCounter = 0;
        }
    }

    public void damageReaction() {
        actionLockCounter = 0;

        // Calculate the differences in position between the player and the monster
        int diffX = gp.player.worldX - worldX;
        int diffY = gp.player.worldY - worldY;

        // Determine the direction based on the larger difference
        if (Math.abs(diffX) > Math.abs(diffY)) {
            if (diffX < 0) {
                direction = "left";
            } else {
                direction = "right";
            }
        } else {
            if (diffY < 0) {
                direction = "up";
            } else {
                direction = "down";
            }
        }
    }

    public void checkDrop() {
        // Cast a die
        int i = new Random().nextInt(100) + 1;

        // Set the drop item
        if (i < 75) {
            dropItem(new OBJ_Coin(gp));
        }
        if (i >= 75 && i < 100) {
            dropItem(new OBJ_Heart(gp));
        }
    }
}

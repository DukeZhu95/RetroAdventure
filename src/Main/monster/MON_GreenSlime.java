package Main.monster;

import Entity.Entity;
import Main.GamePanel;

import java.util.Random;

public class MON_GreenSlime extends Entity {
    public MON_GreenSlime(GamePanel gp) {
        super(gp);
        type = 2;
        name = "GreenSlime";
        speed = 1;
        maxLife = 4;
        life = maxLife;

        solidArea.x = 6;
        solidArea.y = 30;
        solidArea.width = 84;
        solidArea.height = 40;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage() {
        up1 = setup("/Res/monsters/Slime_green_L1");
        up2 = setup("/Res/monsters/Slime_green_L2");
        up3 = setup("/Res/monsters/Slime_green_L3");
        down1 = setup("/Res/monsters/Slime_green_R1");
        down2 = setup("/Res/monsters/Slime_green_R2");
        down3 = setup("/Res/monsters/Slime_green_R3");
        left1 = setup("/Res/monsters/Slime_green_L1");
        left2 = setup("/Res/monsters/Slime_green_L2");
        left3 = setup("/Res/monsters/Slime_green_L3");
        right1 = setup("/Res/monsters/Slime_green_R1");
        right2 = setup("/Res/monsters/Slime_green_R2");
        right3 = setup("/Res/monsters/Slime_green_R3");
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
}

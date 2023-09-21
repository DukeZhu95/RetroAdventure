package Entity;

import Main.GamePanel;

import java.util.Random;

public class NPC_Tigger extends Entity {

    public NPC_Tigger(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;

        getImage();
        setDialogue();
    }

    public void getImage() {

        up1 = setup("/Res/npc/tigger_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/Res/npc/tigger_up_2", gp.tileSize, gp.tileSize);
        up3 = setup("/Res/npc/tigger_up_3", gp.tileSize, gp.tileSize);
        down1 = setup("/Res/npc/tigger_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/Res/npc/tigger_down_2", gp.tileSize, gp.tileSize);
        down3 = setup("/Res/npc/tigger_down_3", gp.tileSize, gp.tileSize);
        left1 = setup("/Res/npc/tigger_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/Res/npc/tigger_left_2", gp.tileSize, gp.tileSize);
        left3 = setup("/Res/npc/tigger_left_3", gp.tileSize, gp.tileSize);
        right1 = setup("/Res/npc/tigger_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/Res/npc/tigger_right_2", gp.tileSize, gp.tileSize);
        right3 = setup("/Res/npc/tigger_right_3", gp.tileSize, gp.tileSize);
    }

    public void setDialogue() {
        dialogues[0] = "Hello, warrior, and welcome you here!";
        dialogues[1] = "As you can see, this land is filled with many monsters, some \nof them are even fatal...";
        dialogues[2] = "Defeat them and become a hero here. You can also meet \nmy people in some shops. They will have the items you \nneed for sale.";
        dialogues[3] = "Good luck!";
    }

    public void setAction() {

        actionLockCounter++;

        if (actionLockCounter == 120) {

            Random random = new Random();

            int i = random.nextInt(100) + 1; // Pick a random number between 1 and 100

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

    public void speak() {
        super.speak();
    }
}

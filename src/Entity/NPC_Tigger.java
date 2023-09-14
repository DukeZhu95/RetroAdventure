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

        up1 = setup("/Res/npc/tigger_up_1");
        up2 = setup("/Res/npc/tigger_up_2");
        up3 = setup("/Res/npc/tigger_up_3");
        down1 = setup("/Res/npc/tigger_down_1");
        down2 = setup("/Res/npc/tigger_down_2");
        down3 = setup("/Res/npc/tigger_down_3");
        left1 = setup("/Res/npc/tigger_left_1");
        left2 = setup("/Res/npc/tigger_left_2");
        left3 = setup("/Res/npc/tigger_left_3");
        right1 = setup("/Res/npc/tigger_right_1");
        right2 = setup("/Res/npc/tigger_right_2");
        right3 = setup("/Res/npc/tigger_right_3");
    }

    public void setDialogue() {
        dialogues[0] = "Hello, warrior, and welcome you here!";
        dialogues[1] = "As you can see, this land is filled with many \n monsters, some of which are even deadly...";
        dialogues[2] = "Defeat them and become a hero here. You can \n also meet my people in some shops. They will \n have the items you need for sale.";
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
}

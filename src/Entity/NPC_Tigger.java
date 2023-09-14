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
        dialogues[0] = "Hello, I am Tigger.";
        dialogues[1] = "I am a tiger.";
        dialogues[2] = "I am a tiger named Tigger.";
        dialogues[3] = "I am a tiger named Tigger who lives in the Hundred Acre Wood.";
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
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;
    }
}

package Entity;

import Main.GamePanel;

public class NPC_Tigger extends Entity {

    public NPC_Tigger(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;

        getImage();
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



}

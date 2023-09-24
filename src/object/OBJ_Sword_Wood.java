package object;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Sword_Wood extends Entity {

    public OBJ_Sword_Wood(GamePanel gp) {
        super(gp);

        type = type_sword;
        name = "Wooden Sword";
        down1 = setup("/Res/objects/sword_wood", gp.tileSize, gp.tileSize);
        attackValue = 1;
        attackArea.width = 64;
        attackArea.height = 64;
        description = "[" + name + "]\n" + "A cheap and ordinary weapon, at\n least it is more powerful than \na fist...";
    }
}

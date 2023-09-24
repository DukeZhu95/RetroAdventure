package object;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Sword_Normal extends Entity {

    public OBJ_Sword_Normal(GamePanel gp) {
        super(gp);

        name = "Iron Sword";
        down1 = setup("/Res/objects/sword_normal", gp.tileSize, gp.tileSize);
        attackValue = 1;
        description = "[" + name + "]\n" + "A cheap and ordinary weapon, at\n least it is more powerful than \na fist...";

    }
}

package object;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Sword_Normal extends Entity {

    public OBJ_Sword_Normal(GamePanel gp) {
        super(gp);

        name = "Normal Sword";
        down1 = setup("/Res/objects/sword_normal", gp.tileSize, gp.tileSize);
        attackValue = 1;
    }
}

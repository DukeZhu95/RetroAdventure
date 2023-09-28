package object;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Sword_Normal extends Entity {

    public OBJ_Sword_Normal(GamePanel gp) {
        super(gp);

        type = type_sword;
        name = "Iron Sword";
        down1 = setup("/Res/objects/sword_normal", gp.tileSize, gp.tileSize);
        attackValue = 2;
        attackArea.width = 68;
        attackArea.height = 68;
        description = "[" + name + "]\n" + "A sword made of iron are more \npowerful and relatively cheap....";
        price = 200;
    }
}

package object;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Shield_Normal extends Entity {

    public OBJ_Shield_Normal(GamePanel gp) {
        super(gp);

        type = type_shield;
        name = "Iron Shield";
        down1 = setup("/Res/objects/shield_normal", gp.tileSize, gp.tileSize);
        defenseValue = 1;
        description = "[" + name + "]\n" + "A slightly rusty iron shield. \nIt's quite practical except for being \na little heavy....";
        price = 220;
    }
}

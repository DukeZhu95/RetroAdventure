package object;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Shield_Wood extends Entity {

    public OBJ_Shield_Wood(GamePanel gp) {
        super(gp);

        type = type_shield;
        name = "Wooden Shield";
        down1 = setup("/Res/objects/shield_wood", gp.tileSize, gp.tileSize);
        defenseValue = 1;
        description = "[" + name + "]\n" + "A bit rotten wooden shield, it is \nbetter to have something than \nnothing...";
        price = 60;
    }
}

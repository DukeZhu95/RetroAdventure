package object;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Key extends Entity {

    public OBJ_Key(GamePanel gp) {
        super(gp);
        name = "Key";
        down1 = setup("/Res/objects/key", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\n" + "Just find a door, please...";
        price = 75;
        stackable = true;
    }
}

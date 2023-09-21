package object;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Heart extends Entity {

    public OBJ_Heart(GamePanel gp) {
        super(gp);
        name = "Heart";
        image = setup("/Res/objects/heart_full", gp.tileSize, gp.tileSize);
        image2 = setup("/Res/objects/heart_half", gp.tileSize, gp.tileSize);
        image3 = setup("/Res/objects/heart_empty", gp.tileSize, gp.tileSize);
    }
}

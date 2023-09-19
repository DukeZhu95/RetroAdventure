package object;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Heart extends Entity {

    public OBJ_Heart(GamePanel gp) {
        super(gp);
        name = "Heart";
        image = setup("/Res/objects/heart_full.png");
        image2 = setup("/Res/objects/heart_half.png");
        image3 = setup("/Res/objects/heart_empty.png");
    }
}

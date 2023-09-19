package object;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Door extends Entity {

    public OBJ_Door(GamePanel gp) {
        super(gp);
        name = "Door";
        down1 = setup("objects/door.png");
        collision = true;

        solidArea.x = 0;
        solidArea.y = 32;
        solidArea.width = 96;
        solidArea.height = 64;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}

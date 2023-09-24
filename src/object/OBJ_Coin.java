package object;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Coin extends Entity {
    GamePanel gp;
    public OBJ_Coin(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_pickupOnly;
        name = "Golden Coin";
        value = 1;
        down1 = setup("/Res/objects/coin", gp.tileSize, gp.tileSize);

    }

    public void use(Entity entity) {

        gp.playSE(1);
        gp.ui.addMessage("Coin + " + value + "!");
        gp.player.coin += value;
    }
}

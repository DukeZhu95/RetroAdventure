package object;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Potion_Red extends Entity {

    GamePanel gp;
    int value = 5;

    public OBJ_Potion_Red(GamePanel gp) {
        super(gp);

        type = type_consumable;
        name = "Life Potion";
        down1 = setup("/Res/objects/potion_red", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\n" + "Heals your life by " + value + ".";
    }

    public void use(Entity entity) {
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You have Drank a " + name + "!\n" + " and healed " + value + " life.";
        entity.life += value;

        if (gp.player.life > gp.player.maxLife) {
            gp.player.life = gp.player.maxLife;
        }

        gp.playSE(2);
    }
}

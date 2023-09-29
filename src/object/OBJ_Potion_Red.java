package object;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Potion_Red extends Entity {

    final GamePanel gp;
    public OBJ_Potion_Red(GamePanel gp) {
        super(gp);
        this.gp = gp;

        if (gp == null) {
//            System.out.println("GamePanel object is null in OBJ_Potion_Red constructor.");
        } else {
//            System.out.println("GamePanel object is not null in OBJ_Potion_Red constructor.");
        }

        type = type_consumable;
        name = "Life Potion";
        value = 5;
        down1 = setup("/Res/objects/potion_red", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\n" + "Heals your life by " + value + ".";
        price = 20;
    }

    public void use(Entity entity) {
        // Logging the start of the use method
        System.out.println("Starting the use method for OBJ_Potion_Red.");

        // Logging the received entity
        if (entity == null) {
            System.out.println("Received entity is null.");
            return; // Exit the method if entity is null
        } else {
            System.out.println("Received entity is not null.");
        }


        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You have Drank a " + name + "!\n" + " and healed " + value + " life.";
        entity.life += value;

        if (gp.player.life > gp.player.maxLife) {
            gp.player.life = gp.player.maxLife;
        }

        gp.playSE(2);
    }
}

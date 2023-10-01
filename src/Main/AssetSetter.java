package Main;

import Entity.NPC_Merchant;
import Entity.NPC_Tigger;
import Main.monster.MON_BlackGhost;
import Main.monster.MON_GreenSlime;
import Main.monster.MON_GreyBat;
import object.*;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        int mapNum = 0;
        int i = 0;
//        gp.obj[i] = new OBJ_Coin(gp);
//        gp.obj[i].worldX = gp.tileSize * 25;
//        gp.obj[i].worldY = gp.tileSize * 23;
//        i++;
//        gp.obj[i] = new OBJ_Coin(gp);
//        gp.obj[i].worldX = gp.tileSize * 21;
//        gp.obj[i].worldY = gp.tileSize * 19;
//        i++;
//        gp.obj[i] = new OBJ_Coin(gp);
//        gp.obj[i].worldX = gp.tileSize * 26;
//        gp.obj[i].worldY = gp.tileSize * 21;
//        i++;
//        gp.obj[i] = new OBJ_Sword_Normal(gp);
//        gp.obj[i].worldX = gp.tileSize * 33;
//        gp.obj[i].worldY = gp.tileSize * 21;
//        i++;
//        gp.obj[i] = new OBJ_Shield_Normal(gp);
//        gp.obj[i].worldX = gp.tileSize * 35;
//        gp.obj[i].worldY = gp.tileSize * 21;
//        i++;
//        gp.obj[i] = new OBJ_Potion_Red(gp);
//        gp.obj[i].worldX = gp.tileSize * 22;
//        gp.obj[i].worldY = gp.tileSize * 27;
//        i++;
//        gp.obj[i] = new OBJ_Heart(gp);
//        gp.obj[i].worldX = gp.tileSize * 22;
//        gp.obj[i].worldY = gp.tileSize * 29;

    }

    public void setNPC() {
        int mapNum = 0;
        int i = 0;

        // Map 0
        gp.npc[mapNum][i] = new NPC_Tigger(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 21;
        gp.npc[mapNum][i].worldY = gp.tileSize * 21;

        // Map 1
        mapNum = 1;
        gp.npc[mapNum][i] = new NPC_Merchant(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 12;
        gp.npc[mapNum][i].worldY = gp.tileSize * 7;
    }

    public void setMonster() {
        int mapNum = 0;
        int i = 0;

        // Slimes
        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 23;
        gp.monster[mapNum][i].worldY = gp.tileSize * 36;
        i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 23;
        gp.monster[mapNum][i].worldY = gp.tileSize * 37;
        i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 24;
        gp.monster[mapNum][i].worldY = gp.tileSize * 37;
        i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 34;
        gp.monster[mapNum][i].worldY = gp.tileSize * 42;
        i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 38;
        gp.monster[mapNum][i].worldY = gp.tileSize * 42;
        i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 40;
        gp.monster[mapNum][i].worldY = gp.tileSize * 40;

        // Bats
        gp.monster[mapNum][i] = new MON_GreyBat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 39;
        gp.monster[mapNum][i].worldY = gp.tileSize * 8;
        i++;
        gp.monster[mapNum][i] = new MON_GreyBat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 36;
        gp.monster[mapNum][i].worldY = gp.tileSize * 40;
        i++;
        gp.monster[mapNum][i] = new MON_GreyBat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 36;
        gp.monster[mapNum][i].worldY = gp.tileSize * 31;
        i++;
        gp.monster[mapNum][i] = new MON_GreyBat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 37;
        gp.monster[mapNum][i].worldY = gp.tileSize * 9;
        i++;
        gp.monster[mapNum][i] = new MON_GreyBat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 38;
        gp.monster[mapNum][i].worldY = gp.tileSize * 15;
        i++;
        gp.monster[mapNum][i] = new MON_GreyBat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 39;
        gp.monster[mapNum][i].worldY = gp.tileSize * 10;

        // Ghosts
        gp.monster[mapNum][i] = new MON_BlackGhost(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 10;
        gp.monster[mapNum][i].worldY = gp.tileSize * 30;
        i++;
        gp.monster[mapNum][i] = new MON_BlackGhost(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 11;
        gp.monster[mapNum][i].worldY = gp.tileSize * 29;
        i++;
        gp.monster[mapNum][i] = new MON_BlackGhost(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 14;
        gp.monster[mapNum][i].worldY = gp.tileSize * 32;
        i++;
        gp.monster[mapNum][i] = new MON_BlackGhost(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 15;
        gp.monster[mapNum][i].worldY = gp.tileSize * 33;
        i++;
        gp.monster[mapNum][i] = new MON_BlackGhost(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 24;
        gp.monster[mapNum][i].worldY = gp.tileSize * 36;
        i++;
        gp.monster[mapNum][i] = new MON_BlackGhost(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 39;
        gp.monster[mapNum][i].worldY = gp.tileSize * 39;


//        mapNum = 1;
//        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
//        gp.monster[mapNum][i].worldX = gp.tileSize * 39;
//        gp.monster[mapNum][i].worldY = gp.tileSize * 40;
//
//        mapNum = 2;
//        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
//        gp.monster[mapNum][i].worldX = gp.tileSize * 23;
//        gp.monster[mapNum][i].worldY = gp.tileSize * 36;
//        i++;
//        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
//        gp.monster[mapNum][i].worldX = gp.tileSize * 23;
//        gp.monster[mapNum][i].worldY = gp.tileSize * 37;
//        i++;
//        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
//        gp.monster[mapNum][i].worldX = gp.tileSize * 24;
//        gp.monster[mapNum][i].worldY = gp.tileSize * 37;
//        i++;
//        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
//        gp.monster[mapNum][i].worldX = gp.tileSize * 34;
//        gp.monster[mapNum][i].worldY = gp.tileSize * 42;
    }
}

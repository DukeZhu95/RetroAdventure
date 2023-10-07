package Data;

import Entity.Entity;
import Main.GamePanel;
import object.*;

import java.io.*;

public class SaveLoad {
    GamePanel gp;
    public SaveLoad(GamePanel gp) {
        this.gp = gp;
    }

    public Entity getObject(String itemName) {
        Entity obj = null;

        switch (itemName) {
            case "Wooden Sword": obj = new OBJ_Sword_Wood(gp); break;
            case "Wooden Shield": obj = new OBJ_Shield_Wood(gp); break;
            case "Iron Sword": obj = new OBJ_Sword_Normal(gp); break;
            case "Iron Shield": obj = new OBJ_Shield_Normal(gp); break;
            case "Life Potion": obj = new OBJ_Potion_Red(gp); break;
            case "Golden Coin": obj = new OBJ_Coin(gp); break;
            case "Key": obj = new OBJ_Key(gp); break;
            default: System.out.println("Error: Invalid item name " + itemName); break;
        }
        if (obj == null) {
            System.out.println("Error: getObject returned null for item name: " + itemName);
        }
        return obj;
    }

    public void save() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("save.dat"));
            DataStorage ds = new DataStorage();

            // Player Status
            ds.level = gp.player.level;
            ds.maxLife = gp.player.maxLife;
            ds.life = gp.player.life;
            ds.strength = gp.player.strength;
            ds.dexterity = gp.player.dexterity;
            ds.attack = gp.player.attack;
            ds.defense = gp.player.defense;
            ds.exp = gp.player.exp;
            ds.nextLevelExp = gp.player.nextLevelExp;
            ds.coin = gp.player.coin;

            // Player Inventory
            for (int i = 0; i < gp.player.inventory.size(); i++) {
                ds.itemNames.add(gp.player.inventory.get(i).name);
                ds.itemAmounts.add(gp.player.inventory.get(i).amount);
            }

            // Player Equipment
            ds.currentWeaponSlot = gp.player.getCurrentWeaponSlot();
            ds.currentShieldSlot = gp.player.getCurrentShieldSlot();

            // Object on map
            ds.mapObjectNames = new String[gp.maxMap][gp.obj[1].length];
            ds.mapObjectWorldX = new int[gp.maxMap][gp.obj[1].length];
            ds.mapObjectWorldY = new int[gp.maxMap][gp.obj[1].length];

            for (int mapNum = 0; mapNum < gp.obj[1].length; mapNum++) {
                for (int i = 0; i < gp.obj[1].length; i++) {
                    if (gp.obj[mapNum][i] != null) {
                        ds.mapObjectNames[mapNum][i] = "N/A";
                    }
                    else {
                        if (gp.obj[mapNum][i] != null) {
                            ds.mapObjectNames[mapNum][i] = gp.obj[mapNum][i].name;
                        }
                        if (gp.obj[mapNum][i] != null) {
                            ds.mapObjectWorldX[mapNum][i] = gp.obj[mapNum][i].worldX;
                        }
                        if (gp.obj[mapNum][i] != null) {
                            ds.mapObjectWorldY[mapNum][i] = gp.obj[mapNum][i].worldY;
                        }
                    }
                }

            }

            // Write the data storage object to the file
            oos.writeObject(ds);
        } catch (Exception e) {
            System.out.println("Save Exception!" + e);
        }

    }
    public void load() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));

            // Read the data storage object from the file
            DataStorage ds = (DataStorage) ois.readObject();
            gp.player.level = ds.level;
            gp.player.maxLife = ds.maxLife;
            gp.player.life = ds.life;
            gp.player.strength = ds.strength;
            gp.player.dexterity = ds.dexterity;
            gp.player.attack = ds.attack;
            gp.player.defense = ds.defense;
            gp.player.exp = ds.exp;
            gp.player.nextLevelExp = ds.nextLevelExp;
            gp.player.coin = ds.coin;

            // Player Inventory
            gp.player.inventory.clear();
            for (int i = 0; i < ds.itemNames.size(); i++) {
                gp.player.inventory.add(getObject(ds.itemNames.get(i)));
                gp.player.inventory.get(i).amount = ds.itemAmounts.get(i);
            }

            // Player Equipment
            gp.player.currentWeapon = gp.player.inventory.get(ds.currentWeaponSlot);
            gp.player.currentShield = gp.player.inventory.get(ds.currentShieldSlot);

            gp.player.getAttack();
            gp.player.getDefense();
            gp.player.getPlayerAttackImage();

            // Object on map
            for (int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
                for (int i = 0; i < gp.obj[1].length; i++) {
                    if (ds.mapObjectNames[mapNum][i].equals("N/A")) {
                        gp.obj[mapNum][i] = null;
                    }
                    else {
                        gp.obj[mapNum][i] = getObject(ds.mapObjectNames[mapNum][i]);
                        gp.obj[mapNum][i].worldX = ds.mapObjectWorldX[mapNum][i];
                        gp.obj[mapNum][i].worldY = ds.mapObjectWorldY[mapNum][i];
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Load Exception!" + e);
        }

    }
}

package Main;

import Entity.Entity;

public class EventHandler {
    GamePanel gp;
    EventRect[][][] eventRect;
    int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    int tempMap, tempCol, tempRow;

    public EventHandler(GamePanel gp) {
        this.gp = gp;

        eventRect = new EventRect[gp.maxMap][gp.maxWorldColumns][gp.maxWorldRows];

        int map = 0;
        int col = 0;
        int row = 0;
        while (map < gp.maxMap && col < gp.maxWorldColumns && row < gp.maxWorldRows) {
            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 46;
            eventRect[map][col][row].y = 46;
            eventRect[map][col][row].width = 4;
            eventRect[map][col][row].height = 4;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;

            col++;
            if (col == gp.maxWorldColumns) {
                col = 0;
                row++;

                if (row == gp.maxWorldRows) {
                    row = 0;
                    map++;
                }
            }
        }
    }

    public void checkEvent() {
        // Check if the character is > 1 tiles away from the last event
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if (distance > gp.tileSize) {
            canTouchEvent = true;
        }

        if (canTouchEvent) {
            if (hit(0,27, 16, "right")) {damagePit(gp.dialogueState);}
//            if (hit(0,23, 19, "any")) {damagePit(gp.dialogueState);}
            else if (hit(0,23, 12, "up")) {healingPool(gp.dialogueState);}
            else if (hit(0,10,39,"any")) {telePort1(1,12,13);}
            else if (hit(1,12,13,"any")) {telePort2(0,10,39);}
            else if (hit(1,12,9,"up")) {speak(gp.npc[1][0]);}
        }
    }

    public void telePort1(int map, int col, int row) {
        gp.gameState = gp.transitionState;
        tempMap = map;
        tempCol = col;
        tempRow = row;
        canTouchEvent = false;
//        gp.playSE(12);
    }

    public void telePort2(int map, int col, int row) {
        gp.gameState = gp.transitionState;
        tempMap = map;
        tempCol = col;
        tempRow = row;
        canTouchEvent = false;
//        gp.stopMusic();
//        System.out.println("Bgm12 has been stopped!");
//        gp.playSE(0);

    }

    public boolean hit(int map, int col, int row, String reqDirection) {
        boolean hit = false;

        if (map == gp.currentMap) {
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;

            if (gp.player.solidArea.intersects(eventRect[map][col][row]) && !eventRect[map][col][row].eventDone) {
                if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                    hit = true;

                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY;
                }

            }

            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
        }
        return hit;
    }

    private void speak(Entity entity) {
        if (gp.keyboardHandler.enterPressed) {
            gp.gameState = gp.dialogueState;
//            gp.player.attackCanceled = true;
            entity.speak();
        }
    }

    public void damagePit(int gameState) {
        gp.gameState = gameState;
        gp.ui.currentDialogue = "You fell into a pit!";
        gp.player.life -= 1;
//        eventRect[col][row].eventDone = true;
        canTouchEvent = false;
    }

    public void healingPool(int gameState) {
        if (gp.keyboardHandler.enterPressed) {
            gp.gameState = gameState;
            gp.ui.currentDialogue = "The Healing Pool has recovered your life!";
            gp.player.life = gp.player.maxLife;
            gp.aSetter.setMonster();
        }
    }
}
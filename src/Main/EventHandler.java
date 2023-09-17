package Main;

import java.awt.*;

public class EventHandler {
    GamePanel gp;
    EventRect[][] eventRect;

    public EventHandler(GamePanel gp) {
        this.gp = gp;

        eventRect = new EventRect[gp.maxWorldColumns][gp.maxWorldRows];

        int col = 0;
        int row = 0;
        while (col < gp.maxWorldColumns) {
            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 46;
            eventRect[col][row].y = 46;
            eventRect[col][row].width = 4;
            eventRect[col][row].height = 4;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;

            col++;
            if (col == gp.maxWorldColumns) {
                col = 0;
                row++;
            }
        }
    }

    public void checkEvent() {
        if (hit(27, 16, "right")) {damagePit(gp.dialogueState);}
        if (hit(23, 12, "up")) {healingPool(gp.dialogueState);}

    }

    public boolean hit(int col, int row, String reqDirection) {
        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect[col][row].x = col * gp.tileSize + eventRect[col][row].x;
        eventRect[col][row].y = row * gp.tileSize + eventRect[col][row].y;

        if (gp.player.solidArea.intersects(eventRect[col][row])) {
            if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;
            }

        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;

        return hit;
    }

    public void damagePit(int gameState) {
        gp.gameState = gameState;
        gp.ui.currentDialogue = "You fell into a pit!";
        gp.player.life -= 1;
    }

    public void healingPool(int gameState) {
        if (gp.keyboardHandler.enterPressed) {
            gp.gameState = gameState;
            gp.ui.currentDialogue = "The Healing Pool has recovered your life!";
            gp.player.life = gp.player.maxLife;
        }
    }
}

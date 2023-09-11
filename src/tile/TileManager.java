package tile;

import Main.GamePanel;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldColumns][gp.maxWorldRows];
        getTileImage();
        loadMap("/Res/maps/world01.txt");
    }

    public void getTileImage() {
//        try {
        setup(0, "Grass", false);
        setup(1, "Wall", true);
        setup(2, "Water", true);
        setup(3, "Earth", false);
        setup(4, "Tree", true);
        setup(5, "Sand", false);


//            tile[0] = new Tile();
//            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Res/tiles/Grass.png")));
//
//            tile[1] = new Tile();
//            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Res/tiles/Wall.png")));
//            tile[1].collision = true;
//
//            tile[2] = new Tile();
//            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Res/tiles/Water.png")));
//            tile[2].collision = true;
//
//            tile[3] = new Tile();
//            tile[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Res/tiles/Earth.png")));
//
//            tile[4] = new Tile();
//            tile[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Res/tiles/Tree.png")));
//            tile[4].collision = true;
//
//            tile[5] = new Tile();
//            tile[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Res/tiles/Sand.png")));

//        }catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public void setup(int index, String imageName, boolean collision) {

        UtilityTool uTool = new UtilityTool();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Res/tiles/" + imageName + ".png")));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = null;
            if (is != null) {
                br = new BufferedReader(new InputStreamReader(is));
            }

            int row = 0;
            int col = 0;

            while (col < gp.maxWorldColumns && row < gp.maxWorldRows) {
                String line = null;
                if (br != null) {
                    line = br.readLine();
                }

                while (col < gp.maxWorldColumns) {
                    String[] numbers = new String[0];
                    if (line != null) {
                        numbers = line.split(" ");
                    }
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldColumns) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        int worldColumns = 0;
        int worldRows = 0;

        while(worldColumns < gp.maxWorldColumns && worldRows < gp.maxWorldRows) {

            int tileNum = mapTileNum[worldColumns][worldRows];

            int worldX = worldColumns * gp.tileSize;
            int worldY = worldRows * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }
            worldColumns++;

            if (worldColumns == gp.maxWorldColumns) {
                worldColumns = 0;
                worldRows++;
            }

        }
    }
}

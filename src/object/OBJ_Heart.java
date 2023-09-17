package object;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.util.Objects;

public class OBJ_Heart extends SuperObject{
    GamePanel gp;
    public OBJ_Heart(GamePanel gp) {
        super();
        this.gp = gp;

        name = "Heart";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Res/objects/heart_full.png")));
            image2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Res/objects/heart_half.png")));
            image3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Res/objects/heart_empty.png")));
            image = uTool.scaleImage(image, gp.tileSize / 2, gp.tileSize / 2);
            image2 = uTool.scaleImage(image2, gp.tileSize / 2, gp.tileSize / 2);
            image3 = uTool.scaleImage(image3, gp.tileSize / 2, gp.tileSize / 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

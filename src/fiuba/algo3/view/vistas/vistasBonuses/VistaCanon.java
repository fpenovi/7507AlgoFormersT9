package fiuba.algo3.view.vistas.vistasBonuses;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class VistaCanon extends VistaBonus {

    public VistaCanon(Canvas canvasBonuses) {
        super(canvasBonuses);
        this.bonusResource =  "file:src/fiuba/algo3/view/resources/images/textures/canon.png";
        this.imageBonus = new Image(this.bonusResource);
    }
}

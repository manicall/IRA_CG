package sample;

import javafx.scene.canvas.Canvas;

public class Graphic2 implements Graphic {

    @Override
    public void draw(Canvas canvas, float k){
        Render render = new Render(canvas);
        render.secondCurve(k);
    }

}

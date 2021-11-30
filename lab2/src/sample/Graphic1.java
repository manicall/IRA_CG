package sample;

import javafx.scene.canvas.Canvas;

public class Graphic1 implements Graphic{

    @Override
    public void draw(Canvas canvas, float k){
        Render render = new Render(canvas);
        render.firstCurve(k);
    }

}

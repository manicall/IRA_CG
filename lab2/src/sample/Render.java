package sample;

import static java.lang.Math.pow;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class Render {
    Canvas canvas;
    int halfWidth;
    int halfHeight;

    int translateX;
    int translateY;

    Render(Canvas canvas) {
        this.canvas = canvas;

        halfWidth = (int) (canvas.getWidth() / 2);
        halfHeight = (int) (canvas.getHeight() / 2);

        translateX = halfWidth;
        translateY = halfHeight;

        clearCanvas();
        createAxes();
    }

    void clearCanvas() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    void createAxes() {
        line(new Point2D(halfWidth, 0), new Point2D(halfWidth, canvas.getHeight()));
        line(new Point2D(0, halfHeight), new Point2D(canvas.getWidth(), halfHeight));
    }

    void line(Point2D startPoint, Point2D endPoint) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.strokeLine(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
    }

    // y = ax^2, a > 0 (1)
    void firstCurve(float a) {
        int x = 0, y = 0;
        float delta = 0;

        while (2 * a * x < 1) {
            setBlackPoint(new Point2D(x, y)); //ставим точку с координатами (x,y)
            if (delta < 0) delta += a * (2 * x + 1); //положительное приращение
            else { //отрицательное приращение
                delta += a * (2 * x + 1) - 1;
                y++;
            }
            x++;
        }

        while (y < canvas.getHeight() / 2) {
            setBlackPoint(new Point2D(x, y));    //ставим точку с координатами (x,y)
            if (delta >= 0) delta += -1;    //отрицательное приращение
            else { //положительное приращение
                delta += a * (2 * x + 1) - 1;
                x++;
            }
            y++;
        }
    }

    // ay^3 - x = 0, a > 0 (1)
    void secondCurve(float a) {
        int x = 0, y = 0;
        float delta = 0;

        while (3 * a * pow(y, 2) < 1) {
            setBlackPoint(new Point2D(x, y)); // ставим точку с координатами (x,y)
            if (delta < 0) delta += 3 * a * pow(y, 2) + 3 * a * y + a; // положительное приращение
            else { // отрицательное приращение
                delta += 3 * a * pow(y, 2) + 3 * a * y + a - 1;
                x++;
            }
            y++;
        }

        while (x < canvas.getWidth() / 2) {
            setBlackPoint(new Point2D(x, y));    //ставим точку с координатами (x,y)
            if (delta >= 0) delta += -1;    //отрицательное приращение
            else { //положительное приращение
                delta += 3 * a * pow(y, 2) + 3 * a * y + a - 1;
                y++;
            }
            x++;
        }
    }

    void setBlackPoint(Point2D p) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        PixelWriter pw = gc.getPixelWriter();
        pw.setColor((int) p.getX() + translateX, (int) -p.getY() + translateY, Color.RED);
    }
}

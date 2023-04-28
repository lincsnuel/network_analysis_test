package nad.Analysis;

import javafx.scene.layout.HBox;
import javafx.scene.transform.Rotate;

public class ArrowCircle extends HBox {
    public void draw(double endX, String centerText, double time, double paneAngle,
                     int circleText) {
        DrawArrow arrow = new DrawArrow();
        arrow.drawArrow(endX,centerText, time);

        DrawCircle circle = new DrawCircle();
        circle.drawCircle(circleText, -1 * paneAngle);

        getTransforms().add(new Rotate(paneAngle, 0, 0, 0));
        setWidth(arrow.getWidth() * Math.cos(Math.toRadians(paneAngle)) + circle.getWidth());
        getChildren().addAll(arrow, circle);
    }
}

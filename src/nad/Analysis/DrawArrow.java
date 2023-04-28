package nad.Analysis;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class DrawArrow extends Pane {

    public void drawArrow(double endX, String centerText, double time) {
        Line line = new Line(0, 0, endX, 0);

        Text text = new Text(centerText + "(" + time + ")");
        text.setFont(new Font(15));
        text.setLayoutX(endX/2 - 20);
        text.setLayoutY(-5);

        Polygon triangle = new Polygon(line.getEndX(), line.getEndY(),
                line.getEndX() - 14, line.getEndY() + 7, line.getEndX() - 14,
                line.getEndY() - 7);

        setWidth(endX);
        getChildren().addAll(line, text, triangle);
    }
}

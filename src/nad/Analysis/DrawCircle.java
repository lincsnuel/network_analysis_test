package nad.Analysis;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;

public class DrawCircle extends Pane {
    public void drawCircle(int circleText, double cirAngle) {
        double cirRadius = 11;
        double strokeWidth = 1.2;

        VBox vBox = new VBox();
        StackPane stackPane = new StackPane();
        Circle circle = new Circle();
        circle.setRadius(cirRadius);
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(strokeWidth);
        Text text = new Text(String.valueOf(circleText));
        text.setFont(new Font(13));
        stackPane.getChildren().addAll(circle, text);

//        DrawRect rect = new DrawRect();
//        rect.drawRect(rectWidth, rectHeight, rectText1, rectText2);

        vBox.getChildren().addAll(stackPane);
        vBox.setLayoutY(getLayoutY() - cirRadius - strokeWidth);
        getTransforms().add(new Rotate(cirAngle, 0 , 0, 0));
        setWidth(2 * (cirRadius + strokeWidth));
        getChildren().add(vBox);
    }
}
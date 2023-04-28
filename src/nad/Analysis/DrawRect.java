package nad.Analysis;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class DrawRect extends VBox {
    public void drawRect(double width, double height,
                         int textValue1, int textValue2) {
        double strokeWidth = 1.2;
        StackPane stackPane1 = new StackPane();
        Rectangle rect1 = new Rectangle(width, height, Color.TRANSPARENT);
        rect1.setStroke(Color.BLACK);
        rect1.setStrokeWidth(strokeWidth);
        Text text1 = new Text(String.valueOf(textValue1));
        text1.setFont(new Font(13));
        stackPane1.getChildren().addAll(rect1, text1);

        StackPane stackPane2 = new StackPane();
        Rectangle rect2 = new Rectangle(width, height, Color.TRANSPARENT);
        rect2.setStroke(Color.BLACK);
        rect2.setStrokeWidth(strokeWidth);
        Text text2 = new Text(String.valueOf(textValue2));
        text2.setFont(new Font(13));
        stackPane2.getChildren().addAll(rect2, text2);

        getChildren().addAll(stackPane1, stackPane2);
    }
}

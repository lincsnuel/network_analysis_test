package nad.Analysis;

import javafx.scene.layout.AnchorPane;

public class CloseOperation {
    private final AnchorPane anchorPane;
    private final double hypWidth;
    public CloseOperation(AnchorPane anchorPane, double hypWidth) {
        this.anchorPane = anchorPane;
        this.hypWidth = hypWidth;
    }

    public void close(int count) {
        DrawCircle circle = new DrawCircle();
        circle.drawCircle(count, 0);
        circle.setLayoutX(hypWidth);
        circle.setLayoutY(500.0/2);
        anchorPane.getChildren().add(circle);
    }
}

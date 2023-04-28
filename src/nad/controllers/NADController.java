package nad.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import nad.Analysis.*;

public class NADController {

    @FXML
    private AnchorPane anchor;

    private final FileOperation file = new FileOperation();

    @FXML
    void initialize() {
        if (file.fileExist()) {
            file.read();

            drawInitCircle();
        }
    }

    private void drawInitCircle() {
        double posY = 500.0 / 2;

        DrawCircle circle = new DrawCircle();
        circle.drawCircle(1, 0);
        circle.setLayoutX(0);
        circle.setLayoutY(posY);
        anchor.getChildren().add(circle);

        new DrawActivities(anchor, file, circle.getWidth());
    }
}

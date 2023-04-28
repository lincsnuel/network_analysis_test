package nad.Analysis;

import javafx.scene.control.Button;

public class SetStyle {
    public void settingsStyle(Button homeButton, Button activitiesButton,
                              Button nadButton) {
        homeButton.setStyle("-fx-background-color: #2C2B53; -fx-border-width: 0px 0px 0px 3px;");
        activitiesButton.setStyle("-fx-background-color: #2C2B53; -fx-border-width: 0px 0px 0px 3px;");
        nadButton.setStyle("-fx-background-color: #2C2B53; -fx-border-width: 0px 0px 0px 3px;");
    }

    public void setButtonStyle(Button b1, Button b2, Button bNew) {
        b1.setStyle("-fx-background-color: #2C2B53; -fx-border-width: 0px 0px 0px 3px;");
        b2.setStyle("-fx-background-color: #2C2B53; -fx-border-width: 0px 0px 0px 3px;");
        bNew.setStyle("-fx-background-color: #1D1C37; -fx-border-width: 0px 0px 0px 0px;");
    }
}

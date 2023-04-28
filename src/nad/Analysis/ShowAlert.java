package nad.Analysis;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.StageStyle;

public class ShowAlert extends Alert {

    public ShowAlert(AlertType alertType, String s) {
        super(alertType, s);
        initStyle(StageStyle.UNDECORATED);
        showAndWait();
    }
}

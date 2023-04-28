package nad.Analysis;

import javafx.scene.layout.AnchorPane;

public class DrawActivities {
    private final FileOperation file;
    private final AnchorPane anchorPane;
    private final double cirWidth;

    public DrawActivities(AnchorPane anchorPane, FileOperation file, double cirWidth) {
        this.file = file;
        this.anchorPane = anchorPane;
        this.cirWidth = cirWidth;

        draw();
    }

    private void draw() {
        CheckHyphen occur = new CheckHyphen(file, anchorPane, cirWidth);
        occur.check("-");
    }
}

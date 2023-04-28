package nad.Analysis;

import javafx.scene.layout.AnchorPane;

public class DrawLetter {
    private final AnchorPane anchorPane;
    private final double hypWidth;
    private final double lineLen;

    public DrawLetter(AnchorPane anchorPane, double hypWidth) {
        this.anchorPane = anchorPane;
        this.hypWidth = hypWidth;

        lineLen = 100.0;
    }

    public void draw(String letter, double angle, double posAng, double time, double posY, int count) {
        double lenAngle = Math.cos(Math.toRadians(posAng));
        double len1Pow = Math.pow(lineLen, 2);
        double len2Pow = Math.pow(lineLen / lenAngle, 2);
        double lineLen2 = lineLen/lenAngle;
        int sign = 1;

        if (posAng > 0) sign = -1;

        ArrowCircle arrowCircle = new ArrowCircle();
        arrowCircle.draw(lineLen / Math.cos(Math.toRadians(angle)), letter, time,
                angle, count);
        arrowCircle.setLayoutX(hypWidth);
        arrowCircle.setLayoutY(posY - (sign * Math.sqrt((len1Pow + len2Pow)
                - (2 * lineLen * lineLen2 * lenAngle))));
        anchorPane.getChildren().add(arrowCircle);
    }
}

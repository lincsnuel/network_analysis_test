package nad.Analysis;

import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DrawHyphen {
    private final AnchorPane anchorPane;
    private final FileOperation file;
    private final double cirWidth;
    private final double lineLen;
    private final double posY;

    public DrawHyphen(AnchorPane anchorPane, FileOperation file, double cirWidth) {
        this.anchorPane = anchorPane;
        this.file = file;
        this.cirWidth = cirWidth;

        lineLen = 100.0;
        posY = 500.0/2;
    }

    public void draw(HashMap<String, HashMap<String, Float>> checkLetter) {
        int angle = -45;
        int increment = 0;
        double hypWidth = 0;
        int count = 2;
        ArrayList<Integer> posAngle = new ArrayList<>();
        HashMap<String, Float> checkTime;

        if (checkLetter.size() == 1) angle = 0;
        for (Map.Entry<String, HashMap<String, Float>> entry : checkLetter.entrySet()) {
            checkTime = entry.getValue();

            for (Map.Entry<String, Float> entry1 : checkTime.entrySet()) {
                ArrowCircle arrowCircle = new ArrowCircle();
                arrowCircle.draw(lineLen/Math.cos(Math.toRadians(angle + increment)),
                        entry.getKey(), entry1.getValue(), angle + increment,
                        count++);
                arrowCircle.setLayoutX(cirWidth);
                arrowCircle.setLayoutY(posY);

                posAngle.add(angle + increment);

                if (checkLetter.size() > 1)
                    increment += 90/(checkLetter.size()-1);

                hypWidth = cirWidth + arrowCircle.getWidth();
                //Correct value. New XLayout is 2*cirWidth + arrWidth

                anchorPane.getChildren().add(arrowCircle);
            }
        }

        CheckLetter letter = new CheckLetter(anchorPane, hypWidth);
        letter.check(file, checkLetter, posAngle, posY, count);
    }
}

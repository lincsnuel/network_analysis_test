package nad.Analysis;

import javafx.scene.layout.AnchorPane;

import java.util.HashMap;

public class CheckHyphen {
    private final AnchorPane anchorPane;
    private final FileOperation file;
    private final double cirWidth;
    private final HashMap<String, HashMap<String, Float>> checkLetter;

    public CheckHyphen(FileOperation file, AnchorPane anchorPane, double cirWidth) {
        this.file = file;
        this.anchorPane = anchorPane;
        this.cirWidth = cirWidth;

        checkLetter = new HashMap<>();
    }

    HashMap<String, Float> checkDuration = new HashMap<>();

    public void check(String letter) {
        for (int i=0; i<file.getIdCount(); i++) {
            if (file.getPredecessor().get(i).contains(letter)
                    && file.getPredecessor().get(i).length() < 2) {
                checkDuration.put(file.getPredecessor().get(i), file.getDuration().get(i));
                checkLetter.put(file.getActivity().get(i), checkDuration);
            }
        }

        DrawHyphen hyphen = new DrawHyphen(anchorPane, file, cirWidth);
        hyphen.draw(checkLetter);
    }
}

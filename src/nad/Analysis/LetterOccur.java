package nad.Analysis;

import javafx.scene.layout.AnchorPane;

import java.util.*;

public class LetterOccur {
    private final AnchorPane anchorPane;
    private final FileOperation file;
    private double hypWidth;
    private int posCount = 0;

    public LetterOccur(AnchorPane anchorPane, FileOperation file, double hypWidth) {
        this.anchorPane = anchorPane;
        this.file = file;
        this.hypWidth = hypWidth;
    }

    public void occurrence(HashMap<String, HashMap<String, Float>> letters,
                           ArrayList<String> letterCount, ArrayList<Integer> posAngle,
                           double posY, int count) {
        double angle = -30;
        double increment = 0;
        HashMap<String, Float> checkTime;

        int singleOccur = singleOccur(letters, letterCount);

        if (letterCount.size() > 0) {
            for (Map.Entry<String, HashMap<String, Float>> entry : letters.entrySet()) {
                checkTime = entry.getValue();

                for (Map.Entry<String, Float> entry1 : checkTime.entrySet()) {
                    if (entry1.getKey().equals(letterCount.get(posCount))) {
                        DrawLetter letter = new DrawLetter(anchorPane, hypWidth);
                        if (singleOccur == 1) {
                            angle = 0;
                            letter.draw(entry.getKey(), angle, posAngle.get(posCount),
                                    entry1.getValue(), posY, count++);
                        } else if (singleOccur == 2) {
                            letter.draw(entry.getKey(),
                                    angle + increment, posAngle.get(posCount),
                                    entry1.getValue(), posY, count++);
                            increment += 60;
                        } else {
                            letter.draw(entry.getKey(),
                                    angle + increment, posAngle.get(posCount),
                                    entry1.getValue(), posY, count++);
                            increment += 30;
                        }
                    }
                }
            }

            moveToNextPos(letters, letterCount, posAngle, posY, count);

            if (posCount == (letterCount.size() - 1)) {
                //set presets
                posCount = 0;
                hypWidth += 124.4;
                HashMap<String, HashMap<String, Float>> checkLetter = new HashMap<>();

                for (Map.Entry<String, HashMap<String, Float>> entry : letters.entrySet()) {
                    checkLetter.put(entry.getKey(), null);
                }

                new CheckLetter(anchorPane, hypWidth).check(file, checkLetter,
                        posAngle, posY, count);
            }
        }
    }

    private int singleOccur(HashMap<String, HashMap<String, Float>> sortedLetters,
                            ArrayList<String> letterCount) {
        int count = 0;
        HashMap<String, Float> checkTime;

        for (Map.Entry<String, HashMap<String, Float>> entry : sortedLetters.entrySet()) {
            checkTime = entry.getValue();

            for (Map.Entry<String, Float> entry1 : checkTime.entrySet()) {
                if (entry1.getKey().contains(letterCount.get(posCount))) {
                    count++;
                }
            }
        }

        return count;
    }

    private void moveToNextPos(HashMap<String, HashMap<String, Float>> sortedLetters,
                               ArrayList<String> letterCount, ArrayList<Integer> posAngle,
                               double posY, int count) {
        if (posCount < (letterCount.size() - 1)) {
            posCount += 1;
            //System.out.println("posCount: " + posCount);
            this.occurrence(sortedLetters, letterCount, posAngle, posY, count);
        }
    }
}

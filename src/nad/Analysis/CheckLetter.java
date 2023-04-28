package nad.Analysis;

import javafx.scene.layout.AnchorPane;

import java.util.*;

public class CheckLetter {
    private final AnchorPane anchorPane;
    private final double hypWidth;

    public CheckLetter(AnchorPane anchorPane, double hypWidth) {
        this.anchorPane = anchorPane;
        this.hypWidth = hypWidth;
    }

    public void check(FileOperation file, HashMap<String, HashMap<String, Float>> checkLetter,
                      ArrayList<Integer> posAngle, double posY, int count) {
        HashMap<String, HashMap<String, Float>> letters = new HashMap<>();
        ArrayList<String> letterCount = new ArrayList<>();
        HashMap<String, Float> checkDuration = new HashMap<>();

        ArrayList<Float> duration;
        ArrayList<String> predecessor = file.getPredecessor();
        ArrayList<String> activity = file.getActivity();
        for (Map.Entry<String, HashMap<String, Float>> entry : checkLetter.entrySet()) {
            for (int j = 0; j < file.getIdCount(); j++) {
                if (predecessor.get(j).contains(entry.getKey())) {
                    if (predecessor.get(j).length() < 2) {
                        duration = file.getDuration();
                        checkDuration.put(predecessor.get(j), duration.get(j));
                        letters.put(activity.get(j), checkDuration);

                        if (letterCount.isEmpty()) letterCount.add(predecessor.get(j));
                        else {
                            if ((letterCount.get(letterCount.size() - 1)
                                    .equals(predecessor.get(j)))) {
                                System.out.println(letterCount);
                            } else {
                                letterCount.add(predecessor.get(j));
                            }
                        }
                    } else new CloseOperation(anchorPane, hypWidth).close(count);
                }
            }
        }

        LetterOccur occur = new LetterOccur(anchorPane, file, hypWidth);
        occur.occurrence(letters, letterCount, posAngle, posY, count);
    }
}

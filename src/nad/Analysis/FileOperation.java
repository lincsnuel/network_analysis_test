package nad.Analysis;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class FileOperation {
    private final ArrayList<Integer> listId = new ArrayList<>();
    private final ArrayList<String> listActivity = new ArrayList<>();
    private final ArrayList<String> listPredecessor = new ArrayList<>();
    private final ArrayList<Float> listOptimistic = new ArrayList<>();
    private final ArrayList<Float> listMostLikely = new ArrayList<>();
    private final ArrayList<Float> listPessimistic = new ArrayList<>();
    private final ArrayList<Float> listDuration = new ArrayList<>();
    private final File tableFile = new File("src/", "tableInfo");
    private final ArrayList<String> rows = new ArrayList<>();
    private int idCount = 0;

    public void write(int id, String activity, String predecessor,
                      float optimistic, float mostLikely, float pessimistic,
                      float duration) {
        String details = id + "|" + activity + "|" + predecessor + "|" +
                optimistic + "|" + mostLikely + "|" + pessimistic + "|" + duration + "\n";

        doWrite(details);
    }

    public void write(int id, String activity, String predecessor, float duration) {
        String details = id + "|" + activity + "|" + predecessor + "|" + duration + "\n";

        doWrite(details);
    }

    //write to file
    private void doWrite(String details) {
        try {
            FileWriter fileWriter = new FileWriter(tableFile, true);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.write(details);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //delete file when necessary to avoid error
    public void deleteFile() {
        if (tableFile.exists()) {
            tableFile.delete();
        }
    }

    public boolean fileExist () {
        return tableFile.exists();
    }

    public void read() {
        //if tableFile exists, then remove the initial initialization of the arrayList
        if (tableFile.exists()) {
            try {
                //Then read the saved file
                FileReader fileReader = new FileReader(tableFile);
                BufferedReader reader = new BufferedReader(fileReader);

                String readLine;
                while ((readLine = reader.readLine()) != null) {
                    rows.add(readLine);
                    idCount++;
                }
                //System.out.println("Size: "+rows.size());
                reader.close();

                //separate tableinfo data into their respective values
                separateData();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void separateData() {
        for (String strLine : rows) {
            StringTokenizer result = new StringTokenizer(strLine, "|");
            while (result.hasMoreTokens()) {
                listId.add(Integer.parseInt(result.nextToken()));
                listActivity.add(result.nextToken());
                listPredecessor.add(result.nextToken());
                listOptimistic.add(Float.parseFloat(result.nextToken()));
                listMostLikely.add(Float.parseFloat(result.nextToken()));
                listPessimistic.add(Float.parseFloat(result.nextToken()));
                listDuration.add(Float.parseFloat(result.nextToken()));
            }
        }
    }

    public ArrayList<Integer> getId() {
        return listId;
    }

    public ArrayList<String> getActivity() {
        return listActivity;
    }

    public ArrayList<String> getPredecessor() {
        return listPredecessor;
    }

    public ArrayList<Float> getOptimistic() {
        return listOptimistic;
    }

    public ArrayList<Float> getMostLikely() {
        return listMostLikely;
    }

    public ArrayList<Float> getPessimistic() {
        return listPessimistic;
    }

    public ArrayList<Float> getDuration() {
        return listDuration;
    }

    public int getIdCount() {
        return idCount;
    }
}

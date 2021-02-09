package App;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface FileWorker {
    /**
     * Functie auxiliara pentru implementarea task 1. Se face split si se creeaza un obiect de tip MonitoredData
     */
    Function<String[], MonitoredData> read_aux = data -> {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String[] linie = data[0].split("\t\t");
        return new MonitoredData(LocalDateTime.parse(linie[0], formatter), LocalDateTime.parse(data[1], formatter), data[2]);
    };

    /**
     * Metoda ce implementeaza task 1
     * @param fileName
     * @return
     */
    static List<MonitoredData> read(String fileName) {
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            return stream
                    .map(
                            line -> line.split("\t\t"))
                    .map(
                            read_aux::apply)
                    .collect(
                            Collectors
                                    .toCollection(
                                            ArrayList::new));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Metoda de scriere in fisier dupa numarul cerintei. Parametrul i reprezinta numarul cerintei
     * @param i
     * @param timeline
     * @param distinctDays
     * @param activities
     * @param activitiesPerDay
     * @param activity
     * @throws IOException
     */
    static void writeFile(int i, List<MonitoredData> timeline, int distinctDays, Map<String, Integer> activities, Map<Integer, Map<String, Integer>> activitiesPerDay, List<String> activity) throws IOException {
        if(i == 1 && timeline != null) {
            File filename = new File("Task_" + i + ".txt");
            if (!filename.exists()) {
                filename.createNewFile();
            }

            FileWriter fw = new FileWriter(filename);
            BufferedWriter bw = new BufferedWriter(fw);

            for (MonitoredData m : timeline) {
                bw.append(m.getStart_time().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).replace("T", " ") + " -> " + m.getEnd_time().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).replace("T", " ") + " -> " + m.getActivity_label());
                bw.newLine();
            }

            bw.close();
        }

        else if(i == 2 && timeline == null) {
            File filename = new File("Task_" + i + ".txt");
            if (!filename.exists()) {
                filename.createNewFile();
            }

            FileWriter fw = new FileWriter(filename);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.append("The number of distinct days is " + distinctDays);
            bw.newLine();

            bw.close();
        }

        else if(i == 3 && timeline == null && activities != null  && activitiesPerDay == null) {
            File filename = new File("Task_" + i + ".txt");
            if (!filename.exists()) {
                filename.createNewFile();
            }

            FileWriter fw = new FileWriter(filename);
            BufferedWriter bw = new BufferedWriter(fw);

            for (Map.Entry<String, Integer> entry : activities.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                bw.append(key + " -> " + value);
                bw.newLine();
            }

            bw.close();
        }

        else if(i == 4 && timeline == null && activities == null && activitiesPerDay != null) {
            File filename = new File("Task_" + i + ".txt");
            if (!filename.exists()) {
                filename.createNewFile();
            }

            FileWriter fw = new FileWriter(filename);
            BufferedWriter bw = new BufferedWriter(fw);

            for (Map.Entry<Integer, Map<String, Integer>> entry : activitiesPerDay.entrySet()) {
                Integer key = entry.getKey();
                bw.append("Day " + key + ":");
                bw.newLine();
                Map<String, Integer> value = entry.getValue();
                for (Map.Entry<String, Integer> entry1 : value.entrySet()) {
                    String key1 = entry1.getKey();
                    Integer value1 = entry1.getValue();
                    bw.append(key1 + " -> " + value1);
                    bw.newLine();
                }
                bw.newLine();
            }

            bw.close();
        }

        else if(i == 5 && timeline == null && activities != null  && activitiesPerDay == null) {
            File filename = new File("Task_" + i + ".txt");
            if (!filename.exists()) {
                filename.createNewFile();
            }

            FileWriter fw = new FileWriter(filename);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.append("Total duration of every activity:");
            bw.newLine();

            for (Map.Entry<String, Integer> entry : activities.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                bw.append(key + " -> " + value / 3600 + "h " + (value % 3600) / 60 + "m");
                bw.newLine();
            }

            bw.close();
        }

        else if(i == 6 && timeline == null && activities == null && activitiesPerDay == null && activity != null) {
            File filename = new File("Task_" + i + ".txt");
            if (!filename.exists()) {
                filename.createNewFile();
            }

            FileWriter fw = new FileWriter(filename);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.append("Tasks with duration less than 5 min:");
            bw.newLine();

            for (String entry : activity) {
                bw.append(entry);
                bw.newLine();
            }

            bw.close();
        }
    }
}
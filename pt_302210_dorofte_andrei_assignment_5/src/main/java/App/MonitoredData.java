package App;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Nucleul aplicatiei, aici se rezolva toate cerintele proiectului
 */
public class MonitoredData implements FileWorker {
    private LocalDateTime start_time;
    private LocalDateTime end_time;
    private String activity_label;

    /**
     * Constructor fara parametri, pentru main
     */
    public MonitoredData() {}

    /**
     * Constructor cu parametri pentru crearea listei de obiecte
     * @param start_time
     * @param end_time
     * @param activity_label
     */
    public MonitoredData(LocalDateTime start_time, LocalDateTime end_time, String activity_label) {
        this.start_time = start_time;
        this.end_time = end_time;
        this.activity_label = activity_label;
    }

    public LocalDateTime getStart_time() {
        return start_time;
    }

    public LocalDateTime getEnd_time() {
        return end_time;
    }

    public String getActivity_label() {
        return activity_label;
    }

    /**
     * Metoda ce implementeaza task 2
     * @param timeline
     * @return
     */
    public int getDistinctDays(List<MonitoredData> timeline) {
        return (int) timeline
                .stream()
                .map(
                        m -> m.getStart_time().getDayOfYear())
                .distinct()
                .count();
    }

    /**
     * Metoda ce implementeaza task 3
     * @param timeline
     * @return
     */
    public Map<String, Integer> nrActivities(List<MonitoredData> timeline) {
        return timeline
                .stream()
                .map(
                        m -> m.getActivity_label())
                .collect(
                        Collectors.groupingBy(
                                m -> m,
                                Collectors
                                        .collectingAndThen(Collectors.counting(), Long::intValue)));
    }

    /**
     * Metoda ce implementeaza task 4
     * @param timeline
     * @return
     */
    public Map<Integer, Map<String, Integer>> activityPerDay(List<MonitoredData> timeline) {
        return timeline
                .stream()
                .collect(
                        Collectors
                                .groupingBy(
                                        m -> m.getStart_time().getDayOfYear(),
                                        Collectors
                                                .groupingBy(
                                                    m -> m.getActivity_label(),
                                                        Collectors
                                                            .collectingAndThen(Collectors.counting(), Long::intValue))));
    }

    /**
     * Metoda care calculeaza durata in secunde pe care a avut-o o activitate
     * @return
     */
    public int computeDuration() {
        return (int) Duration.between(getStart_time(), getEnd_time()).toSeconds();
    }

    /**
     * Metoda ce implementeaza task 5
     * @param timeline
     * @return
     */
    public Map<String, Integer> totalDuration(List<MonitoredData> timeline) {
        return timeline
                .stream()
                .collect(
                        Collectors
                                .groupingBy(
                                        m -> m.getActivity_label(),
                                        Collectors
                                                .summingInt(
                                                        MonitoredData::computeDuration)));
    }

    /**
     * Metoda ce implementeaza task 6
     * @param timeline
     * @return
     */
    public List<String> activityDuration(List<MonitoredData> timeline) {
        return timeline
                .stream()
                .collect(
                        Collectors
                                .groupingBy(
                                        MonitoredData::getActivity_label,
                                        Collectors
                                                .mapping(
                                                        MonitoredData::computeDuration,
                                                        Collectors
                                                                .averagingDouble(duration -> (duration / 60) < 5 ? 1 : 0))))
                .entrySet()
                .stream()
                .filter(md -> md.getValue() > 0.9)
                .collect(
                        Collectors
                                .toMap(
                                        md -> md.getKey(),
                                        md -> md.getValue()))
                .keySet()
                .stream()
                .collect(
                        Collectors
                                .toList());
    }

    /**
     * Metoda ce apeleaza metoda de scriere in fisier, conform numarului cerinei
     * @param timeline
     * @throws IOException
     */
    public void task_1_write(List<MonitoredData> timeline) throws IOException {
        FileWorker.writeFile(1, timeline, 0, null, null, null);
    }

    public void task_2_write(List<MonitoredData> timeline) throws IOException {
        FileWorker.writeFile(2, null, getDistinctDays(timeline), null, null, null);
    }

    public void task_3_write(List<MonitoredData> timeline) throws IOException {
        nrActivities(timeline);
        FileWorker.writeFile(3, null, 0, nrActivities(timeline), null, null);
    }

    public void task_4_write(List<MonitoredData> timeline) throws IOException {
        FileWorker.writeFile(4, null, 0, null, activityPerDay(timeline), null);
    }

    public void task_5_write(List<MonitoredData> timeline) throws IOException {
        FileWorker.writeFile(5, null, 0, totalDuration(timeline), null, null);
    }

    public void task_6_write(List<MonitoredData> timeline) throws IOException {
        FileWorker.writeFile(6, null, 0, null, null, activityDuration(timeline));
    }
}
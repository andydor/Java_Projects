package App;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<MonitoredData> timeline = FileWorker.read("Activities.txt");
        MonitoredData x = new MonitoredData();
        x.task_1_write(timeline);
        x.task_2_write(timeline);
        x.task_3_write(timeline);
        x.task_4_write(timeline);
        x.task_5_write(timeline);
        x.task_6_write(timeline);
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

class TargetData {
    private String date;
    private String time;
    private String action;

    TargetData(String date, String time, String action) {
        this.date = date;
        this.time = time;
        this.action = action;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getAction() {
        return action;
    }

    public boolean equals(TargetData target) {
        return this.date.equals(target.getDate()) && this.time.equals(target.getTime()) && this.action.equals(target.getAction());
    }

    @Override
    public String toString() {
        return this.date + " " + this.time + " " + this.action;
    }
}

class DataImporter {
    List<TargetData> importData(String path) throws IOException {
        Path pathToFile = Paths.get(path);
        List<TargetData> targets_data = new ArrayList<>();
        BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII);
        String row = br.readLine();
        while (row != null) {
            String[] attributes = row.split(",");
            TargetData target_data = new TargetData(attributes[0], attributes[1], attributes[2]);
            targets_data.add(target_data);

            row = br.readLine();
        }

        br.close();
        return targets_data;
    }
}

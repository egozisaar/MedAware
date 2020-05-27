import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ArgumentsReader argsReader = new ArgumentsReader();
        Map<String, String> params;
        try {
            params = argsReader.read(args);
        } catch (WrongArgumentException e) {
            e.printStackTrace();
            return;
        }

        System.out.println("Input path: " + params.get("i"));
        System.out.println("Output path: " + params.get("o"));

        System.out.println("Reading data...");
        DataImporter importer = new DataImporter();
        List<TargetData> data;
        try {
            data = importer.importData(params.get("i"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return;
        }
        System.out.println("Finished!");

        System.out.println("Grouping data...");
        Comparator comparator = new Comparator();
        List<SimilaritiesGroup> groups = comparator.getSimilaritiesGroups(data);
        System.out.println("Finished!");

        System.out.println("Writing groups to output file...");
        OutputGenerator generator = new OutputGenerator();
        try {
            generator.createOutputFile(groups, params.get("o"));
        } catch (IOException e) {
            System.out.println("An error occurred when trying to create or write to file");
            e.printStackTrace();
            return;
        }
        System.out.println("Finished!");


    }
}

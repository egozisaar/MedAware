import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

class OutputGenerator {

    private String fileName = "output/targets_groups.txt";

    void createOutputFile(List<SimilaritiesGroup> groups) throws IOException {
        createOutputFile(groups, this.fileName);
    }

    void createOutputFile(List<SimilaritiesGroup> groups, String fileName) throws IOException {

        File file = new File(fileName);
        if (file.createNewFile())
            System.out.println("Targets file created: " + file.getName());
        else
            System.out.println("File already exists.");

        FileWriter myWriter = new FileWriter(fileName);
        for (SimilaritiesGroup group : groups) {
            String str = group.toString();
            if (!str.isEmpty())
                myWriter.write(str + "\n\n");
        }
        myWriter.close();

    }
}

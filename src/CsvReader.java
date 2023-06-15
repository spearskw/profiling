import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CsvReader {

    public static List<String> readCsv() {
        try {
            URL csvUrl = CsvReader.class.getClassLoader().getResource("edges.csv");
            List<String> lines = Files.readAllLines(Path.of(csvUrl.toURI()));
            return lines;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVData {

    // method for reading the csv files, this throws an exception in case of an error 
    public static List<String[]> readCSV(String fileName) throws IOException {
        List<String[]> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                for (int i = 0; i < values.length; i++) {
                    values[i] = values[i].replaceAll("\"", "").trim();
                }

                data.add(values);
            }
        }

        return data;
    }
}

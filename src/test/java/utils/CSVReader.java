package utils;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    String[][] data;

    @DataProvider(name = "ContactUsData")
    public String[][] readData(){

        String path = "src/test/resources/ContactUsData.csv";

        List<String[]> rows = null;
        try {
            rows = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(path));

            // Skip the headers
            br.readLine();

            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                for (int i = 0; i < row.length; i++) {
                    row[i] = row[i].replaceAll("^\"|\"$", "").trim();
                }
                rows.add(row);
            }
            // Close the stream
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // Convert List<String[]> to String[][]
        String[][] result = new String[rows.size()][];
        for (int i = 0; i < rows.size(); i++) {
            result[i] = rows.get(i);
        }

//        System.out.println("CSV Data:");
//        for (String[] row : result) {
//            for (String cell : row) {
//                System.out.print(cell + " | ");
//            }
//            System.out.println();
//        }
        return result;
    }
}

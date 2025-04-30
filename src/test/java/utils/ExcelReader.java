package utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;

public class ExcelReader {

    File excelFile = new File("src/test/resources/testingData.xlsx");
    FileInputStream fis;
    String[][] data;

    // Read Data from the Excel sheet
    @DataProvider(name = "RegistrationData")
    public String[][] readData() {
        try {
            fis = new FileInputStream(excelFile);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheet("RegistrationData");

            int noOfRows = sheet.getPhysicalNumberOfRows();
            int noOfColumns = sheet.getRow(0).getLastCellNum();

            data = new String[noOfRows - 1][noOfColumns];

            for (int i = 0; i < noOfRows - 1; i++) {
                for (int j = 0; j < noOfColumns; j++) {
                    DataFormatter df = new DataFormatter();
                    data[i][j] = df.formatCellValue(sheet.getRow(i + 1).getCell(j));
                }
            }

            // Close the streams
            workbook.close();
            fis.close();

            // Printing data
//            for (String[] d : data) {
//                System.out.println(Arrays.toString(d));
//            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return data;
    }

}

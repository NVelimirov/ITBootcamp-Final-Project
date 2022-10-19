package utils;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ReadFromExcel {
    private FileInputStream inputStream;
    private XSSFWorkbook workBook;
    private XSSFSheet sheet;
    private String sheetName = "Sheet1";

    public String getSheetName() {
        return sheetName;
    }

    public void readFromExcelTable(String pathToFile){
        try {
            inputStream = new FileInputStream(pathToFile);
            workBook = new XSSFWorkbook(inputStream);
        } catch (IOException ioExc){
            ioExc.printStackTrace();
        }
    }

    public String getData(int row, int col) {
        sheet = workBook.getSheet(getSheetName());
        String data = sheet.getRow(row).getCell(col).getStringCellValue();
        return data;
    }

    public int getRowCount() {
        sheet = workBook.getSheet(getSheetName());
        return sheet.getLastRowNum()-sheet.getFirstRowNum();
    }
}

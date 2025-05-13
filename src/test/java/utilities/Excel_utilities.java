package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_utilities {
    // Declare all user-defined methods to perform different operations on Excel
    public static FileInputStream fi;
    public static FileOutputStream fo;
    public static XSSFWorkbook wb;
    public static XSSFSheet ws;
    public static XSSFRow row;
    public static XSSFCell cell;
    public static CellStyle style;
    String path;

    // Constructor to specify the Excel file path
    public Excel_utilities(String path) {
        this.path = path;
    }

    // Get the row count for the specified sheet
    public int getRowCount(String sheetName) throws IOException {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(sheetName);
        int rowcount = ws.getPhysicalNumberOfRows();
        wb.close();
        fi.close();
        return rowcount;
    }

    // Get the cell count for a specific row in a specified sheet
    public int getCellCount(String sheetName, int rownum) throws IOException {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(sheetName);
        row = ws.getRow(rownum);
        int cellcount = (row == null) ? 0 : row.getPhysicalNumberOfCells();
        wb.close();
        fi.close();
        return cellcount;
    }

    // Get the data from a specific cell
    public String getCellData(String sheetName, int rownum, int colnum) throws IOException {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(sheetName);
        row = ws.getRow(rownum);
        if (row == null) {
            wb.close();
            fi.close();
            return "";  // Row doesn't exist, return empty string
        }
        cell = row.getCell(colnum);
        if (cell == null) {
            wb.close();
            fi.close();
            return "";  // Cell doesn't exist, return empty string
        }

        String data;
        try {
            DataFormatter formatter = new DataFormatter();
            data = formatter.formatCellValue(cell); // Returns the formatted value of a cell as a String
        } catch (Exception e) {
            data = "";
        }
        wb.close();
        fi.close();
        return data;
    }

    // Set the data in a specific cell
    public void setCellData(String sheetName, int rownum, int celnum, String data) throws IOException {
        File xlfile = new File(path);
        if (!xlfile.exists()) { // If file doesn't exist, create a new file
            wb = new XSSFWorkbook();
            fo = new FileOutputStream(path);
            wb.write(fo);
        }

        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);

        if (wb.getSheetIndex(sheetName) == -1) { // If the sheet doesn't exist, create a new sheet
            wb.createSheet(sheetName);
        }
        ws = wb.getSheet(sheetName);

        if (ws.getRow(rownum) == null) { // If the row doesn't exist, create a new row
            ws.createRow(rownum);
        }
        row = ws.getRow(rownum);

        cell = row.createCell(celnum);
        cell.setCellValue(data);

        fo = new FileOutputStream(path);
        wb.write(fo);
        wb.close();
        fi.close();
        fo.close();
    }

    // Fill the cell with green color
    public static void fillGreenColor(String xlfile, String xlsheet, int rownum, int colnum) throws IOException {
        fi = new FileInputStream(xlfile);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(xlsheet);
        row = ws.getRow(rownum);
        if (row == null) {
            row = ws.createRow(rownum);
        }
        cell = row.getCell(colnum);
        if (cell == null) {
            cell = row.createCell(colnum);
        }

        style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell.setCellStyle(style);

        fo = new FileOutputStream(xlfile);
        wb.write(fo);
        wb.close();
        fi.close();
        fo.close();
    }

    // Fill the cell with red color
    public void fillRedColor(String sheetName, int rownum, int colnum) throws IOException {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(sheetName);
        row = ws.getRow(rownum);
        if (row == null) {
            row = ws.createRow(rownum);
        }
        cell = row.getCell(colnum);
        if (cell == null) {
            cell = row.createCell(colnum);
        }

        style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell.setCellStyle(style);

        fo = new FileOutputStream(path);
        wb.write(fo);
        wb.close();
        fi.close();
        fo.close();
    }

    public static void main(String[] args) throws IOException {
        // Example usage of the Excel_utilities class
        Excel_utilities excel = new Excel_utilities("example.xlsx");

        // Example of reading and writing data
        String data = excel.getCellData("Sheet1", 0, 0);
        System.out.println("Data from Sheet1, Row 0, Column 0: " + data);

        excel.setCellData("Sheet1", 1, 1, "Hello, Excel!");
        Excel_utilities.fillGreenColor("example.xlsx", "Sheet1", 1, 1);
        excel.fillRedColor("Sheet1", 2, 2);
    }

	public int getcellCount(String string, int i) {
		// TODO Auto-generated method stub
		return 0;
	}
}

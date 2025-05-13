package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
		
	@DataProvider(name = "Logindata")
	public String[][] getData() throws IOException {
	    String path = ".\\TestData\\OpernCart_LoginData.xlsx"; // Excel file path

	    Excel_utilities xlutil = new Excel_utilities(path); // Excel utility object

	    int totalrow = xlutil.getRowCount("sheet1");
	    int totalcol = xlutil.getCellCount("sheet1", 1);

	    String loginData[][] = new String[totalrow][totalcol]; // 2D array to store the data

	    // Loop to read data from Excel and store it in the 2D array
	    for (int i = 1; i <= totalrow; i++) { // i starts from 1 to skip the header row
	        for (int j = 0; j < totalcol; j++) { // Loop through columns
	            loginData[i - 1][j] = xlutil.getCellData("sheet1", i, j); // Storing data in the array
	        }
	    }

	    return loginData; // Return the 2D array
	}
	
}
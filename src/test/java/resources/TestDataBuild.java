package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pojo.AddEmployee;
import pojo.UpdateEmployee;

public class TestDataBuild {


	public AddEmployee addEmployee()
	{
		try {
			ArrayList<String> addEmp = getData("Create_Emp","Employee");
			AddEmployee em =new AddEmployee();
			em.setName(addEmp.get(1));
			em.setSalary(Integer.parseInt(addEmp.get(2)));
			em.setAge(Integer.parseInt(addEmp.get(3)));
			
			return em;
		} catch (IOException e) {
			
			e.printStackTrace();
		}
 return null;
		
	}
	
	public UpdateEmployee modifyEmployee()
	{
		try {
			ArrayList<String> addEmp = getData("Update_Emp","Employee");
			UpdateEmployee em =new UpdateEmployee();
			em.setName(addEmp.get(1));
			em.setSalary(Integer.parseInt(addEmp.get(2)));
			em.setAge(Integer.parseInt(addEmp.get(3)));
			
			return em;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 return null;
		
	}
	


	public ArrayList<String> getData(String testcaseName, String sheetName) throws IOException {
		// fileInputStream argument
		ArrayList<String> a = new ArrayList<String>();

		FileInputStream fis = new FileInputStream("C:\\Framework\\APIFramework\\src\\test\\java\\resources\\demodata.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		int sheets = workbook.getNumberOfSheets();
		for (int i = 0; i < sheets; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase(sheetName)) {
				XSSFSheet sheet = workbook.getSheetAt(i);
				// Identify Testcases coloum by scanning the entire 1st row

				Iterator<Row> rows = sheet.iterator();// sheet is collection of rows
				Row firstrow = rows.next();
				Iterator<Cell> ce = firstrow.cellIterator();// row is collection of cells
				int k = 0;
				int coloumn = 0;
				while (ce.hasNext()) {
					Cell value = ce.next();

					if (value.getStringCellValue().equalsIgnoreCase("TestCases")) {
						coloumn = k;

					}

					k++;
				}
				System.out.println(coloumn);

				//// once coloumn is identified then scan entire testcase coloum to identify
				//// purcjhase testcase row
				while (rows.hasNext()) {

					Row r = rows.next();

					if (r.getCell(coloumn).getStringCellValue().equalsIgnoreCase(testcaseName)) {

						//// after you grab purchase testcase row = pull all the data of that row and
						//// feed into test

						Iterator<Cell> cv = r.cellIterator();
						while (cv.hasNext()) {
							Cell c = cv.next();
							if (c.getCellTypeEnum() == CellType.STRING) {

								a.add(c.getStringCellValue());
							} else {

								a.add(NumberToTextConverter.toText(c.getNumericCellValue()));

							}
						}
					}

				}

			}
		}
		return a;

	}

}

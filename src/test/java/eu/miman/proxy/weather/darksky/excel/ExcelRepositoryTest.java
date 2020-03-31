package eu.miman.proxy.weather.darksky.excel;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import eu.miman.proxy.weather.darksky.rest.CommandController;

@RunWith(SpringRunner.class)
public class ExcelRepositoryTest {

    Logger log = Logger.getLogger("ExcelRepositoryTest");
    @Test
    public void test() {
        // given
        ExcelRepository class2test = new ExcelRepository();
		String path = "C:\\Temp";
		Workbook workbook = null;
        String fileLocation = path + "\\temp.xlsx";
        
        File currDir = new File(".");
        String path2 = currDir.getAbsolutePath();
        log.info("path2: " + path2);
        List<String> dateList = CommandController.getAllYearDates("2020");
        for (String date : dateList) {
            log.info(date);
        }

		try {
            workbook = class2test.readExcelFile(fileLocation);
            Sheet s = workbook.getSheetAt(0);
            log.info("Last row #: " + s.getLastRowNum());
            log.info("First row #: " + s.getFirstRowNum());
            log.info("getPhysicalNumberOfRows: " + s.getPhysicalNumberOfRows());
            assertEquals("", s.getLastRowNum(), 0);
//			class2test.addForecastRow(forecast, workbook);
//			class2test.storeWorkbook(workbook, fileLocation);
		} catch (FileNotFoundException e) {
			// File doesn't exist -> Create base (header row...)
//			workbook = class2test.createExcelFile(fileLocation);
		}

        // then
        
    }
}

package eu.miman.proxy.weather.darksky.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import tk.plogitech.darksky.forecast.model.DailyDataPoint;
import tk.plogitech.darksky.forecast.model.Forecast;

@Component
public class ExcelRepository {
	Logger log = Logger.getLogger(ExcelRepository.class.getName());

	private String subPath = "target//";

	public void storeDarkSkyReports(List<Forecast> forecasts, String locationDescription) throws IOException {
		Workbook workbook = null;
		String fileLocation = getFileLocation(locationDescription);
		try {
			workbook = readExcelFile(fileLocation);
		} catch (FileNotFoundException e) {
			// File doesn't exist -> Create base (header row...)
			workbook = createExcelFile(fileLocation);
		}
		for (Forecast forecast : forecasts) {
			addForecastRow(forecast, workbook);
		}
		storeWorkbook(workbook, fileLocation);
		log.info("storeDarkSkyReports: Info process & stored to folder: " + fileLocation);
	}

	public void storeDarkSkyReport(Forecast forecast, String locationDescription) throws IOException {
		Workbook workbook = null;
		String fileLocation = getFileLocation(locationDescription);
		try {
			workbook = readExcelFile(fileLocation);
		} catch (FileNotFoundException e) {
			// File doesn't exist -> Create base (header row...)
			workbook = createExcelFile(fileLocation);
		}
		addForecastRow(forecast, workbook);
		storeWorkbook(workbook, fileLocation);
		log.info("Info process & stored to folder: " + fileLocation);
	}
	
	private void addForecastRow(Forecast forecast, Workbook workbook) {
		Sheet sheet = workbook.getSheetAt(0);
		CellStyle style = workbook.createCellStyle();
		style.setWrapText(true);
		
		if (forecast.getDaily() == null || forecast.getDaily().getData() == null) {
			return;
		}

		for (DailyDataPoint dp : forecast.getDaily().getData()) {
			Row row = sheet.createRow(sheet.getPhysicalNumberOfRows() + 1);

			Cell cell;
			if (forecast.getLatitude() != null) {
				cell = row.createCell(ExcelColumn.Latitude.ordinal());
				cell.setCellValue(forecast.getLatitude().value());
				cell.setCellStyle(style);
			}

			if (forecast.getLongitude() != null) {
				cell = row.createCell(ExcelColumn.Longitude.ordinal());
				cell.setCellValue(forecast.getLongitude().value());
				cell.setCellStyle(style);
			}

			if (forecast.getDaily().getSummary() != null) {
				cell = row.createCell(ExcelColumn.summary.ordinal());
				cell.setCellValue(forecast.getDaily().getSummary());
				cell.setCellStyle(style);
			}

			if (dp.getTime() != null) {
				cell = row.createCell(ExcelColumn.Time.ordinal());
				cell.setCellValue(dp.getTime().toString());
				cell.setCellStyle(style);
			}

			if (dp.getTemperatureHigh() != null) {
				cell = row.createCell(ExcelColumn.temperatureHigh.ordinal());
				cell.setCellValue(dp.getTemperatureHigh());
				cell.setCellStyle(style);
			}

			if (dp.getTemperatureHighTime() != null) {
				cell = row.createCell(ExcelColumn.temperatureHighTime.ordinal());
				cell.setCellValue(dp.getTemperatureHighTime().toString());
				cell.setCellStyle(style);
			}

			if (dp.getTemperatureLow() != null) {
				cell = row.createCell(ExcelColumn.temperatureLow.ordinal());
				cell.setCellValue(dp.getTemperatureLow());
				cell.setCellStyle(style);
			}

			if (dp.getTemperatureLowTime() != null) {
				cell = row.createCell(ExcelColumn.temperatureLowTime.ordinal());
				cell.setCellValue(dp.getTemperatureLowTime().toString());
				cell.setCellStyle(style);
			}

			if (dp.getSunriseTime() != null) {
				cell = row.createCell(ExcelColumn.sunriseTime.ordinal());
				cell.setCellValue(dp.getSunriseTime().toString());
				cell.setCellStyle(style);
			}

			if (dp.getPrecipType() != null) {
				cell = row.createCell(ExcelColumn.precipType.ordinal());
				cell.setCellValue(dp.getPrecipType());
				cell.setCellStyle(style);
			}

			if (dp.getPrecipIntensity() != null) {
				cell = row.createCell(ExcelColumn.precipIntensity.ordinal());
				cell.setCellValue(dp.getPrecipIntensity());
				cell.setCellStyle(style);
			}
			 
			if (dp.getPrecipIntensityMax() != null) {
				cell = row.createCell(ExcelColumn.precipIntensityMax.ordinal());
				cell.setCellValue(dp.getPrecipIntensityMax());
				cell.setCellStyle(style);
			}
			 
			if (dp.getPrecipIntensityMaxTime() != null) {
				cell = row.createCell(ExcelColumn.precipIntensityMaxTime.ordinal());
				cell.setCellValue(dp.getPrecipIntensityMaxTime().toString());
				cell.setCellStyle(style);
			}			 
			if (dp.getPrecipAccumulation() != null) {
				cell = row.createCell(ExcelColumn.precipAccumulation.ordinal());
				cell.setCellValue(dp.getPrecipAccumulation());
				cell.setCellStyle(style);
			}
			 
			if (dp.getPrecipProbability() != null) {
				cell = row.createCell(ExcelColumn.precipProbability.ordinal());
				cell.setCellValue(dp.getPrecipProbability());
				cell.setCellStyle(style);
			}
			 
			if (dp.getDewPoint() != null) {
				cell = row.createCell(ExcelColumn.dewPoint.ordinal());
				cell.setCellValue(dp.getDewPoint());
				cell.setCellStyle(style);
			}
			 
			if (dp.getHumidity() != null) {
				cell = row.createCell(ExcelColumn.humidity.ordinal());
				cell.setCellValue(dp.getHumidity());
				cell.setCellStyle(style);
			}
			 
			if (dp.getPressure() != null) {
				cell = row.createCell(ExcelColumn.pressure.ordinal());
				cell.setCellValue(dp.getPressure());
				cell.setCellStyle(style);
			}
			 
			if (dp.getWindSpeed() != null) {
				cell = row.createCell(ExcelColumn.windSpeed.ordinal());
				cell.setCellValue(dp.getWindSpeed());
				cell.setCellStyle(style);
			}
			 
			if (dp.getWindGust() != null) {
				cell = row.createCell(ExcelColumn.windGust.ordinal());
				cell.setCellValue(dp.getWindGust());
				cell.setCellStyle(style);
			}
			 
			if (dp.getWindGustTime() != null) {
				cell = row.createCell(ExcelColumn.windGustTime.ordinal());
				cell.setCellValue(dp.getWindGustTime().toString());
				cell.setCellStyle(style);
			}
			 
			if (dp.getWindBearing() != null) {
				cell = row.createCell(ExcelColumn.windBearing.ordinal());
				cell.setCellValue(dp.getWindBearing());
				cell.setCellStyle(style);
			}
			 
			if (dp.getCloudCover() != null) {
				cell = row.createCell(ExcelColumn.cloudCover.ordinal());
				cell.setCellValue(dp.getCloudCover());
				cell.setCellStyle(style);
			}
			 
			if (dp.getUvIndex() != null) {
				cell = row.createCell(ExcelColumn.uvIndex.ordinal());
				cell.setCellValue(dp.getUvIndex());
				cell.setCellStyle(style);
			}
			 
			if (dp.getUvIndexTime() != null) {
				cell = row.createCell(ExcelColumn.uvIndexTime.ordinal());
				cell.setCellValue(dp.getUvIndexTime().toString());
				cell.setCellStyle(style);
			}
			 
			if (dp.getVisibility() != null) {
				cell = row.createCell(ExcelColumn.visibility.ordinal());
				cell.setCellValue(dp.getVisibility());
				cell.setCellStyle(style);
			}
			 
			if (dp.getOzone() != null) {
				cell = row.createCell(ExcelColumn.ozone.ordinal());
				cell.setCellValue(dp.getOzone());
				cell.setCellStyle(style);
			}
			 
			if (dp.getIcon() != null) {
				cell = row.createCell(ExcelColumn.icon.ordinal());
				cell.setCellValue(dp.getIcon());
				cell.setCellStyle(style);
			}
		}
	}

	private Workbook createExcelFile(String fileLocation) throws IOException {
		Workbook workbook = new XSSFWorkbook();
		 
		Sheet sheet = workbook.createSheet("Forecasts");
		sheet.setColumnWidth(ExcelColumn.Time.ordinal(), 5500);
		sheet.setColumnWidth(ExcelColumn.precipIntensityMaxTime.ordinal(), 5500);
		sheet.setColumnWidth(ExcelColumn.sunriseTime.ordinal(), 5500);
		sheet.setColumnWidth(ExcelColumn.sunsetTime.ordinal(), 5500);
		sheet.setColumnWidth(ExcelColumn.temperatureHighTime.ordinal(), 5500);
		sheet.setColumnWidth(ExcelColumn.temperatureLowTime.ordinal(), 5500);
		sheet.setColumnWidth(ExcelColumn.uvIndexTime.ordinal(), 5500);
		sheet.setColumnWidth(ExcelColumn.windGustTime.ordinal(), 5500);
		sheet.setColumnWidth(ExcelColumn.Latitude.ordinal(), 3500);
		sheet.setColumnWidth(ExcelColumn.Longitude.ordinal(), 3500);
		sheet.setColumnWidth(ExcelColumn.summary.ordinal(), 7500);
		sheet.setColumnWidth(ExcelColumn.precipAccumulation.ordinal(), 3500);
		sheet.setColumnWidth(ExcelColumn.precipIntensity.ordinal(), 3500);
		sheet.setColumnWidth(ExcelColumn.precipIntensityMax.ordinal(), 3500);
		sheet.setColumnWidth(ExcelColumn.precipProbability.ordinal(), 3500);
		sheet.setColumnWidth(ExcelColumn.precipType.ordinal(), 3500);
		sheet.setColumnWidth(ExcelColumn.pressure.ordinal(), 3500);
		sheet.setColumnWidth(ExcelColumn.cloudCover.ordinal(), 3500);
		sheet.setColumnWidth(ExcelColumn.humidity.ordinal(), 3500);
		sheet.setColumnWidth(ExcelColumn.icon.ordinal(), 3500);
		sheet.setColumnWidth(ExcelColumn.uvIndex.ordinal(), 3500);
		sheet.setColumnWidth(ExcelColumn.ozone.ordinal(), 3500);
		sheet.setColumnWidth(ExcelColumn.temperatureHigh.ordinal(), 3500);
		sheet.setColumnWidth(ExcelColumn.temperatureLow.ordinal(), 3500);
		sheet.setColumnWidth(ExcelColumn.visibility.ordinal(), 3500);
		sheet.setColumnWidth(ExcelColumn.windBearing.ordinal(), 3500);
		sheet.setColumnWidth(ExcelColumn.windGust.ordinal(), 3500);
		sheet.setColumnWidth(ExcelColumn.windSpeed.ordinal(), 3500);
		sheet.setColumnWidth(ExcelColumn.dewPoint.ordinal(), 3500);
		 
		Row header = sheet.createRow(0);
		 
		CellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		 
		XSSFFont font = ((XSSFWorkbook) workbook).createFont();
		font.setFontName("Arial");
		font.setFontHeightInPoints((short) 12);
		font.setBold(true);
		headerStyle.setFont(font);
		 
		int columnIndex = 0;
		Cell headerCell = header.createCell(columnIndex++);
		headerCell.setCellValue("Time");
		headerCell.setCellStyle(headerStyle);
		 
		headerCell = header.createCell(columnIndex++);
		headerCell.setCellValue("Latitude");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(columnIndex++);
		headerCell.setCellValue("Longitude");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = header.createCell(columnIndex++);
		headerCell.setCellValue("temperatureHigh");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = header.createCell(columnIndex++);
		headerCell.setCellValue("temperatureLow");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = header.createCell(columnIndex++);
		headerCell.setCellValue("temperatureHighTime");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = header.createCell(columnIndex++);
		headerCell.setCellValue("temperatureLowTime");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = header.createCell(columnIndex++);
		headerCell.setCellValue("precipType");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = header.createCell(columnIndex++);
		headerCell.setCellValue("precipIntensity");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = header.createCell(columnIndex++);
		headerCell.setCellValue("precipIntensityMax");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = header.createCell(columnIndex++);
		headerCell.setCellValue("precipProbability");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = header.createCell(columnIndex++);
		headerCell.setCellValue("precipIntensityMaxTime");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = header.createCell(columnIndex++);
		headerCell.setCellValue("precipAccumulation");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = header.createCell(columnIndex++);
		headerCell.setCellValue("sunriseTime");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = header.createCell(columnIndex++);
		headerCell.setCellValue("sunsetTime");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = header.createCell(columnIndex++);
		headerCell.setCellValue("dewPoint");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = header.createCell(columnIndex++);
		headerCell.setCellValue("humidity");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = header.createCell(columnIndex++);
		headerCell.setCellValue("pressure");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = header.createCell(columnIndex++);
		headerCell.setCellValue("windSpeed");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = header.createCell(columnIndex++);
		headerCell.setCellValue("windGust");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = header.createCell(columnIndex++);
		headerCell.setCellValue("windGustTime");
		headerCell.setCellStyle(headerStyle);
		
		
		headerCell = header.createCell(columnIndex++);
		headerCell.setCellValue("windBearing");
		headerCell.setCellStyle(headerStyle);
		
		
		headerCell = header.createCell(columnIndex++);
		headerCell.setCellValue("cloudCover");
		headerCell.setCellStyle(headerStyle);
		
		
		headerCell = header.createCell(columnIndex++);
		headerCell.setCellValue("uvIndex");
		headerCell.setCellStyle(headerStyle);
		
		
		headerCell = header.createCell(columnIndex++);
		headerCell.setCellValue("uvIndexTime");
		headerCell.setCellStyle(headerStyle);
		
		
		headerCell = header.createCell(columnIndex++);
		headerCell.setCellValue("visibility");
		headerCell.setCellStyle(headerStyle);
		
		
		headerCell = header.createCell(columnIndex++);
		headerCell.setCellValue("ozone");
		headerCell.setCellStyle(headerStyle);
		
		
		headerCell = header.createCell(columnIndex++);
		headerCell.setCellValue("icon");
		headerCell.setCellStyle(headerStyle);
		
		
		headerCell = header.createCell(columnIndex++);
		headerCell.setCellValue("summary");
		headerCell.setCellStyle(headerStyle);
		
//		storeWorkbook(workbook, fileLocation);
		
		return workbook;
	}
	
	/**
	 * Stores the given workbook into a file with the given name in Excel format
	 * @param workbook	The workbook to store
	 * @param fileLocation	The file locateion + name
	 * @throws IOException
	 */
	private void storeWorkbook(Workbook workbook, String fileLocation) throws IOException {
		FileOutputStream outputStream = new FileOutputStream(fileLocation);
		workbook.write(outputStream);
		workbook.close();		
	}

	public Workbook readExcelFile(String fileLocation) throws FileNotFoundException {
		try {
			FileInputStream file = new FileInputStream(new File(fileLocation));
			Workbook workbook = new XSSFWorkbook(file);
			return workbook;
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private String getFileLocation(String locationDescription) {
		File currDir = new File(".");
		String path = currDir.getAbsolutePath();
		String fileLocation = path.substring(0, path.length() - 1) + subPath + locationDescription + ".xlsx";
		return fileLocation;
	}
}

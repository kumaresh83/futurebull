package com.ycode.stock.utill;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.ycode.stock.daomodel.TradeInputs;
import com.ycode.stock.model.CompanyOpenIndex;
import com.ycode.stock.model.MonthData;
import com.ycode.stock.model.OpenIndexOutput;
import com.ycode.stock.model.SameDayData;

public class FileUtill {
	public static String saveFile(String userId, String fileName, MultipartFile multipartFile) throws IOException {
		Path uploadPath = Paths.get("StockFiles" + "-" + userId);
		String destinationFilname;

		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}

		try (InputStream inputStream = multipartFile.getInputStream()) {

			String fileCode = RandomStringUtils.randomAlphanumeric(8);
			destinationFilname = fileCode + "-" + fileName;
			Path filePath = uploadPath.resolve(destinationFilname);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException ioe) {
			throw new IOException("Could not save file: " + fileName, ioe);
		}

		return destinationFilname;
	}

	public static ArrayList<OpenIndexOutput> readFile(String userId, String fileName) throws IOException {

		File file = new File("StockFiles" + "-" + userId + "/" + fileName);
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		int sheetCount = workbook.getNumberOfSheets();
		ArrayList<OpenIndexOutput> openIndexList = new ArrayList<>();
		for (int i = 0; i < sheetCount; i++) {
			OpenIndexOutput output = new OpenIndexOutput();
			XSSFSheet daysheet = workbook.getSheetAt(i);

			output.setDate(daysheet.getSheetName());
			if (i == 0) {
				output.setCompanyOpenIndex(readSingleSheet(daysheet));
			} else {
				XSSFSheet previousDaysheet = workbook.getSheetAt(i - 1);
				output.setCompanyOpenIndex(readSequenceSheet(daysheet, previousDaysheet));

			}
			openIndexList.add(output);

		}
		return openIndexList;

	}

	public static ArrayList<CompanyOpenIndex> readSingleSheet(XSSFSheet sheet) {
		ArrayList<CompanyOpenIndex> companyOpenIndexList = new ArrayList<>();

		int rowCount = sheet.getLastRowNum();
		for (int i = 2; i < rowCount; i++) {
			CompanyOpenIndex companyOpenIndex = new CompanyOpenIndex();
			XSSFRow row = sheet.getRow(i);
			if (row != null && !row.getCell(0).toString().isEmpty()) {
				companyOpenIndex.setCompanyName(row.getCell(0).toString());
				companyOpenIndex.setTillDateCallNet(Integer
						.parseInt(row.getCell(5).toString().substring(0, row.getCell(5).toString().indexOf(".")))
						- Integer.parseInt(
								row.getCell(7).toString().substring(0, row.getCell(7).toString().indexOf("."))));
				companyOpenIndex.setTillDatePutNet(Integer
						.parseInt(row.getCell(6).toString().substring(0, row.getCell(6).toString().indexOf(".")))
						- Integer.parseInt(
								row.getCell(8).toString().substring(0, row.getCell(8).toString().indexOf("."))));
				companyOpenIndexList.add(companyOpenIndex);
			}
		}

		return companyOpenIndexList;

	}

	public static ArrayList<CompanyOpenIndex> readSequenceSheet(XSSFSheet daysheet, XSSFSheet previousDaysheet) {
		ArrayList<CompanyOpenIndex> companyOpenIndexList = new ArrayList<>();
		int rowCount = daysheet.getLastRowNum();
		for (int i = 2; i < rowCount; i++) {
			CompanyOpenIndex companyOpenIndex = new CompanyOpenIndex();
			XSSFRow row = daysheet.getRow(i);
			if (row != null && !row.getCell(0).toString().isEmpty()) {
				companyOpenIndex.setCompanyName(row.getCell(0).toString());
				companyOpenIndex.setTillDateCallNet(Integer
						.parseInt(row.getCell(5).toString().substring(0, row.getCell(5).toString().indexOf(".")))
						- Integer.parseInt(
								row.getCell(7).toString().substring(0, row.getCell(7).toString().indexOf("."))));
				companyOpenIndex.setTillDatePutNet(Integer
						.parseInt(row.getCell(6).toString().substring(0, row.getCell(6).toString().indexOf(".")))
						- Integer.parseInt(
								row.getCell(8).toString().substring(0, row.getCell(8).toString().indexOf("."))));
				XSSFRow previousDayRow = previousDaysheet.getRow(i);
				companyOpenIndex.setIntraDayCallNet((Integer
						.parseInt(row.getCell(5).toString().substring(0, row.getCell(5).toString().indexOf(".")))
						- Integer.parseInt(previousDayRow.getCell(5).toString().substring(0,
								previousDayRow.getCell(5).toString().indexOf("."))))
						- (Integer.parseInt(
								row.getCell(7).toString().substring(0, row.getCell(7).toString().indexOf(".")))
								- Integer.parseInt(previousDayRow.getCell(7).toString().substring(0,
										previousDayRow.getCell(7).toString().indexOf(".")))));
				companyOpenIndex.setIntraDayPutNet((Integer
						.parseInt(row.getCell(6).toString().substring(0, row.getCell(6).toString().indexOf(".")))
						- Integer.parseInt(previousDayRow.getCell(6).toString().substring(0,
								previousDayRow.getCell(6).toString().indexOf("."))))
						- (Integer.parseInt(
								row.getCell(8).toString().substring(0, row.getCell(8).toString().indexOf(".")))
								- Integer.parseInt(previousDayRow.getCell(8).toString().substring(0,
										previousDayRow.getCell(8).toString().indexOf(".")))));
				companyOpenIndexList.add(companyOpenIndex);
			}
		}

		return companyOpenIndexList;

	}

	public static ArrayList<TradeInputs> writeXlsFileToDB(MultipartFile multipartFile)
			throws IOException, ParseException {
		// TODO Auto-generated method stub
		ArrayList<TradeInputs> tradeInputList = new ArrayList<>();
		XSSFWorkbook workbook = new XSSFWorkbook(multipartFile.getInputStream());
		int sheetCount = workbook.getNumberOfSheets();
		for (int i = 0; i < sheetCount; i++) {
			
			XSSFSheet daysheet = workbook.getSheetAt(i);

			int rowCount = daysheet.getLastRowNum();
			for (int j = 2; j <=rowCount; j++) {
				XSSFRow row = daysheet.getRow(j);
				if (row != null && !row.getCell(0).toString().isEmpty()) {
					String tradeDate = daysheet.getSheetName();
					SimpleDateFormat sdf1 = new SimpleDateFormat("ddMMyyyy");
					java.util.Date date = sdf1.parse(tradeDate);
					java.sql.Date sqlTradeDate = new java.sql.Date(date.getTime());
					TradeInputs tradeInput = new TradeInputs();
					tradeInput.setTradeDate(sqlTradeDate);
					tradeInput.setClientType(row.getCell(0).toString());
					tradeInput.setFutureIndexLong(Integer.parseInt(row.getCell(1).toString().substring(0, row.getCell(1).toString().indexOf("."))));
					tradeInput.setFutureIndexShort(Integer.parseInt(row.getCell(2).toString().substring(0, row.getCell(2).toString().indexOf("."))));
					tradeInput.setFutureStockLong(Integer.parseInt(row.getCell(3).toString().substring(0, row.getCell(3).toString().indexOf("."))));
					tradeInput.setFutureStockShort(Integer.parseInt(row.getCell(4).toString().substring(0, row.getCell(4).toString().indexOf("."))));
					tradeInput.setOptionIndexCallLong(Integer.parseInt(row.getCell(5).toString().substring(0, row.getCell(5).toString().indexOf("."))));
					tradeInput.setOptionIndexPutLong(Integer.parseInt(row.getCell(6).toString().substring(0, row.getCell(6).toString().indexOf("."))));
					tradeInput.setOptionIndexCallShort(Integer.parseInt(row.getCell(7).toString().substring(0, row.getCell(7).toString().indexOf("."))));
					tradeInput.setOptionIndexPutShort(Integer.parseInt(row.getCell(8).toString().substring(0, row.getCell(8).toString().indexOf("."))));
					tradeInput.setOptionStockCallLong(Integer.parseInt(row.getCell(9).toString().substring(0, row.getCell(9).toString().indexOf("."))));
					tradeInput.setOptionStockPutLong(Integer.parseInt(row.getCell(10).toString().substring(0, row.getCell(10).toString().indexOf("."))));
					tradeInput.setOptionStockCallShort(Integer.parseInt(row.getCell(11).toString().substring(0, row.getCell(11).toString().indexOf("."))));
					tradeInput.setOptionStockPutShort(Integer.parseInt(row.getCell(12).toString().substring(0, row.getCell(12).toString().indexOf("."))));
					tradeInput.setTotalLongContracts(Integer.parseInt(row.getCell(13).toString().substring(0, row.getCell(13).toString().indexOf("."))));
					tradeInput.setTotalShortContracts(Integer.parseInt(row.getCell(14).toString().substring(0, row.getCell(14).toString().indexOf("."))));
					tradeInputList.add(tradeInput);
					
					
				}
			}

		}

		return tradeInputList;
	}

	public static HashMap<String, ArrayList<TradeInputs>> parseDBOutput(List<TradeInputs> tradeInputs) {
		// TODO Auto-generated method stub
		
		HashMap<String, ArrayList<TradeInputs>> DBtradeList = new HashMap<String, ArrayList<TradeInputs>>();
		
		for(TradeInputs tradeInput:tradeInputs)
		{
			String tradeDate=tradeInput.getTradeDate().toString();
			if(DBtradeList.containsKey(tradeDate))
			{
				ArrayList<TradeInputs> dateTradeList=DBtradeList.get(tradeDate);
				dateTradeList.add(tradeInput);
				DBtradeList.put(tradeDate, dateTradeList);
				
			}
			else
			{
				ArrayList<TradeInputs> newdateTradeList= new ArrayList<TradeInputs>();
				newdateTradeList.add(tradeInput);
				DBtradeList.put(tradeDate, newdateTradeList);
			}
			
		}
		return DBtradeList;
	}

	public static ArrayList<OpenIndexOutput> calculateOpenIndex(HashMap<String, ArrayList<TradeInputs>> dBtradeList) throws ParseException {
		// TODO Auto-generated method stub
		
		ArrayList<OpenIndexOutput> openIndexList = new ArrayList<>();
		Set<String> dateSet=dBtradeList.keySet();
		List<String> dateList = new ArrayList<>(dateSet);
		for (int i = 0; i < dateList.size(); i++) {
			OpenIndexOutput output = new OpenIndexOutput();
		
			String currentDate=dateList.get(i);
			output.setDate(currentDate);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date myDate = dateFormat.parse(currentDate);
			java.util.Date oneDayBefore = new Date(myDate.getTime() - 1);
			String Yesterday=dateFormat.format(oneDayBefore);
			if(dBtradeList.containsKey(Yesterday))
			{
				output.setCompanyOpenIndex(parseTwoDateOpenIndex(dBtradeList.get(currentDate), dBtradeList.get(Yesterday)));
			}
			else
			{
				output.setCompanyOpenIndex(parseSingleDateOpenIndex(dBtradeList.get(currentDate)));
			}
			openIndexList.add(output);
			
		}
		
		return openIndexList;
	}

	private static ArrayList<CompanyOpenIndex> parseSingleDateOpenIndex(ArrayList<TradeInputs> arrayList) {

		ArrayList<CompanyOpenIndex> companyOpenIndexList = new ArrayList<>();

		for(TradeInputs tradeinput:arrayList) 
		{
			CompanyOpenIndex companyOpenIndex = new CompanyOpenIndex();

				companyOpenIndex.setCompanyName(tradeinput.getClientType());
				companyOpenIndex.setTillDateCallNet(tradeinput.getOptionIndexCallLong()-tradeinput.getOptionIndexCallShort());
				companyOpenIndex.setTillDatePutNet(tradeinput.getOptionIndexPutLong()-tradeinput.getOptionIndexPutShort());
				companyOpenIndexList.add(companyOpenIndex);
			
		}

		return companyOpenIndexList;

	
	}

	private static ArrayList<CompanyOpenIndex> parseTwoDateOpenIndex(ArrayList<TradeInputs> currentDateList,
			ArrayList<TradeInputs> yesterdayList) {

		ArrayList<CompanyOpenIndex> companyOpenIndexList = new ArrayList<>();
		for(TradeInputs tradeinput:currentDateList)
		{
			CompanyOpenIndex companyOpenIndex = new CompanyOpenIndex();
		
			
				companyOpenIndex.setCompanyName(tradeinput.getClientType());
				companyOpenIndex.setTillDateCallNet(tradeinput.getOptionIndexCallLong()-tradeinput.getOptionIndexCallShort());
				companyOpenIndex.setTillDatePutNet(tradeinput.getOptionIndexPutLong()-tradeinput.getOptionIndexPutShort());
				
				HashMap<String,TradeInputs> yestrdayCompanyList = new HashMap<String, TradeInputs>();
				
				for(TradeInputs yesterdayInput:yesterdayList)
				{
					yestrdayCompanyList.put(yesterdayInput.getClientType(), yesterdayInput);
				}
				
				TradeInputs yesterdayInput=yestrdayCompanyList.get(tradeinput.getClientType());
				
				
				companyOpenIndex.setIntraDayCallNet((tradeinput.getOptionIndexCallLong()-yesterdayInput.getOptionIndexCallLong())-(tradeinput.getOptionIndexCallShort()-yesterdayInput.getOptionIndexCallShort()));
				companyOpenIndex.setIntraDayPutNet((tradeinput.getOptionIndexPutLong()-yesterdayInput.getOptionIndexPutLong())-(tradeinput.getOptionIndexPutShort()-yesterdayInput.getOptionIndexPutShort()));
				companyOpenIndexList.add(companyOpenIndex);
			
		}

		return companyOpenIndexList;

	
	}
	
	public static SameDayData parsesameDayData(List<TradeInputs> tradeInputs) {
		// TODO Auto-generated method stub
		
		SameDayData sameDayData= new SameDayData();
		if(!tradeInputs.isEmpty())
		{
		TradeInputs TradeInput = tradeInputs.get(0);
		sameDayData.setCompanyName(TradeInput.getClientType());
		sameDayData.setDate(TradeInput.getTradeDate().toString());
		sameDayData.setCallLong(TradeInput.getOptionIndexCallLong());
		sameDayData.setCallShort(TradeInput.getOptionIndexCallShort());
		sameDayData.setPutLong(TradeInput.getOptionIndexPutLong());
		sameDayData.setPutShort(TradeInput.getOptionIndexPutShort());
		sameDayData.setTillDateCallNet(TradeInput.getOptionIndexCallLong()-TradeInput.getOptionIndexCallShort());
		sameDayData.setTillDatePutNet(TradeInput.getOptionIndexPutLong()-TradeInput.getOptionIndexPutShort());
		
		
		
		}
		return sameDayData;
	}

	public static ArrayList<MonthData> parseMonthData(List<TradeInputs> monthTradeInputs) {
		// TODO Auto-generated method stub
		
		ArrayList<MonthData> monthDataList =new ArrayList<>();
		
		for(TradeInputs monthDateData:monthTradeInputs)
		{
			MonthData MonthData = new MonthData();
			MonthData.setCompanyName(monthDateData.getClientType());
			MonthData.setDate(monthDateData.getTradeDate().toString());
			MonthData.setTillDateCallNet(monthDateData.getOptionIndexCallLong()-monthDateData.getOptionIndexCallShort());
			MonthData.setTillDatePutNet(monthDateData.getOptionIndexPutLong()-monthDateData.getOptionIndexPutShort());
			monthDataList.add(MonthData);
		}
		
		return monthDataList;
	}
}

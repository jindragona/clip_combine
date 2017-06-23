/*
 *  Midas version 1.0
 *
 *  Copyright ⓒ 2015 kt corp. All rights reserved.
 *
 *  This is a proprietary software of kt corp, and you may not use this file except in
 *  compliance with license agreement with kt corp. Any redistribution or use of this
 *  software, with or without modification shall be strictly prohibited without prior written
 *  approval of kt corp, and the copyright notice above does not evidence any actual or
 *  intended publication of such software.
 */
package com.kt.midas.adm.common.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Jin
 */
public class ExcelUtils {
	private static final Logger LOG = LoggerFactory.getLogger(ExcelUtils.class);
    private static final String DEFUALT_EXCEL_FILENAME = "통계_엑셀";

    public static String getExcelFileName(String fileName) {
        String excelFileName = DEFUALT_EXCEL_FILENAME;
        try {
            excelFileName = URLEncoder.encode(fileName, "UTF-8") + "_" +
                    new SimpleDateFormat("yyyy.MM.dd_HH:mm:ss", Locale.KOREA).format(new Date());
        } catch (UnsupportedEncodingException ex) {
            if(LOG.isErrorEnabled()){
				LOG.error(ex.getMessage(), "URL ENCODEW ERROR :: ");
			}
        }
        return excelFileName;
    }

    public static String getSearchPeriodExcelHeader(HttpServletRequest request) {
        String searchPeriod = (String) request.getParameter("search_period");
        StringBuilder searchPeriodBuilder = new StringBuilder("기간 : ");

        String searchYear   = (String) request.getParameter("search_year") + "년 ";
        String searchMonth  = (String) request.getParameter("search_month") + "월 ";
        switch (searchPeriod) {
            case "monthly" : 
                searchPeriodBuilder.append("월별 ");
                searchPeriodBuilder.append( searchYear );
                break;
            case "daily" : 
                searchPeriodBuilder.append("일별 ");
                searchPeriodBuilder.append( searchYear );
                searchPeriodBuilder.append( searchMonth );
                break;
            case "custom" : 
                searchPeriodBuilder.append("직접 ");
                String sDate = (String) request.getParameter("sdate");
                String eDate = (String) request.getParameter("edate");
                searchPeriodBuilder.append( sDate );
                searchPeriodBuilder.append( " ~ " );
                searchPeriodBuilder.append( eDate );
                break;
            default :
        }
        return searchPeriodBuilder.toString();
    }
    
    @SuppressWarnings("resource")
	public static List<List<Object>> readExcelFile(File file){
    	List<List<Object>> list = new ArrayList<List<Object>>();
    	List<Object> cellData = null;
    	
    	XSSFSheet sheet    =  null;
		XSSFRow row     =  null;
		XSSFCell cell    =  null;
    	
    	try {
			XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file));
			
			FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
			String data = "";
			
			int sheetNum =  wb.getNumberOfSheets();
			
			for(int i=0;i<sheetNum;i++){//시트가 여러개 있을 경우
			    sheet = wb.getSheetAt(i);
			    
			    int lastRowNum = sheet.getLastRowNum();
			    for(int r=sheet.getFirstRowNum();r<=lastRowNum;r++){//row를 읽는다.
			    	row = sheet.getRow(r);
			        if(row == null) {
			        	continue;
			        }
			        
		    		int lastCellNum = row.getLastCellNum();
			        cellData = new ArrayList<Object>();
			        for(int c=row.getFirstCellNum();c<=lastCellNum;c++){//cell을 읽는다.
				        cell   =  row.getCell(c);
				        //if(cell== null) continue;
				        if(cell== null) {cellData.add(""); continue;}
				        
				        switch(cell.getCellType()){
					        case HSSFCell.CELL_TYPE_NUMERIC:
//					        	cellData.add(cell.getNumericCellValue());
					        	cellData.add(new BigDecimal(cell.getNumericCellValue()).toPlainString());
					        	break;
					        case HSSFCell.CELL_TYPE_STRING:
					        	cellData.add(cell.getStringCellValue());
					        	break;
					        case HSSFCell.CELL_TYPE_FORMULA :
					        	//cellData.add(cell.getCellFormula());
					        	if(!(cell.toString().equalsIgnoreCase("")) ){
					        		if(evaluator.evaluateFormulaCell(cell)==HSSFCell.CELL_TYPE_NUMERIC){
					        			double fddata = cell.getNumericCellValue();
					        			DecimalFormat df = new DecimalFormat();
					        			data = df.format(fddata);
					        		}else if(evaluator.evaluateFormulaCell(cell)==HSSFCell.CELL_TYPE_STRING){
					        			data = cell.getStringCellValue();
					        		}else if(evaluator.evaluateFormulaCell(cell)==HSSFCell.CELL_TYPE_BOOLEAN){
					        			boolean fbdata = cell.getBooleanCellValue();         
					        			data = String.valueOf(fbdata);         
					        		}
					        		cellData.add(data);
					        	}
					        	break;
					        default:
					        	cellData.add("");
				       }
			        }
			        list.add(cellData);
			    }
				
			}
			
		} catch (IOException e) {
			if(LOG.isErrorEnabled()){
				LOG.error("ExcelUtils readExcelFile error", e);
			}
		}
    	
    	
    	return list;
    }
    
    @SuppressWarnings("resource")
	public static List<List<Object>> readExcelFile(MultipartFile mfile){
    	List<List<Object>> list = new ArrayList<List<Object>>();
    	List<Object> cellData = null;
    	
    	XSSFSheet sheet    =  null;
		XSSFRow row     =  null;
		XSSFCell cell    =  null;
    	
    	try {
			XSSFWorkbook wb = new XSSFWorkbook(mfile.getInputStream());
			
			FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
			String data = "";
			
			int sheetNum =  wb.getNumberOfSheets();
			
			for(int i=0;i<sheetNum;i++){//시트가 여러개 있을 경우
			    sheet = wb.getSheetAt(i);
			    
			    int lastRowNum = sheet.getLastRowNum();
			    for(int r=sheet.getFirstRowNum();r<=lastRowNum;r++){//row를 읽는다.
			    	row = sheet.getRow(r);
			        if(row== null) continue;
			        
		    		int lastCellNum = row.getLastCellNum();
			        cellData = new ArrayList<Object>();
			        for(int c=row.getFirstCellNum();c<=lastCellNum;c++){//cell을 읽는다.
				        cell   =  row.getCell(c);
				        //if(cell== null) continue;
				        if(cell== null) {cellData.add(""); continue;}
				        
				        switch(cell.getCellType()){
					        case HSSFCell.CELL_TYPE_NUMERIC:
//					        	cellData.add(cell.getNumericCellValue());
					        	cellData.add(new BigDecimal(cell.getNumericCellValue()).toPlainString());
					        	break;
					        case HSSFCell.CELL_TYPE_STRING:
					        	cellData.add(cell.getStringCellValue());
					        	break;
					        case HSSFCell.CELL_TYPE_FORMULA :
					        	//cellData.add(cell.getCellFormula());
					        	if(!(cell.toString().equalsIgnoreCase("")) ){
					        		if(evaluator.evaluateFormulaCell(cell)==HSSFCell.CELL_TYPE_NUMERIC){
					        			double fddata = cell.getNumericCellValue();
					        			DecimalFormat df = new DecimalFormat();
					        			data = df.format(fddata);
					        		}else if(evaluator.evaluateFormulaCell(cell)==HSSFCell.CELL_TYPE_STRING){
					        			data = cell.getStringCellValue();
					        		}else if(evaluator.evaluateFormulaCell(cell)==HSSFCell.CELL_TYPE_BOOLEAN){
					        			boolean fbdata = cell.getBooleanCellValue();         
					        			data = String.valueOf(fbdata);         
					        		}
					        		cellData.add(data);
					        	}
					        	break;
					        default:
					        	cellData.add("");
				       }
			        }
			        list.add(cellData);
			    }
				
			}
			
		} catch (IOException e) {
			if(LOG.isErrorEnabled()){
				LOG.error("ExcelUtils readExcelFile error", e);
			}
		}
    	
    	
    	return list;
    }
}

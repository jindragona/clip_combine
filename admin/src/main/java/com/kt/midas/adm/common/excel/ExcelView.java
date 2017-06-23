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

import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.AbstractView;

/**
 *
 * @author Jin
 */
public class ExcelView extends AbstractView {

	private static final Logger LOG = LoggerFactory.getLogger(ExcelView.class);
	private static final String CONTENT_TYPE = "application/vnd.ms-excel";

	public ExcelView() {
		setContentType(CONTENT_TYPE);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = (XSSFSheet) workbook.createSheet("sheet1");

			Map<String, Object> map = (Map<String, Object>) model.get("excelMap");
			String excelGubun = (String) map.get("excelGubun");

			if (excelGubun.equals("temp")) {

				buildExcelTempDocument(model, workbook, request, response, sheet);

			} else if (excelGubun.equals("lftCalExcel")) {

			} else if (excelGubun.equals("bannCalExcel")) {

			} else if (excelGubun.equals("mcardCalExcel")) {

			}

			ServletOutputStream out = response.getOutputStream();
			workbook.write(out);

			if (out != null) {
				out.close();
			}
		} catch (Exception e) {
			if (LOG.isErrorEnabled()) {
				LOG.error("ExcelView renderMergedOutputModel error", e);
			}
		}
	}

	@SuppressWarnings("unchecked")
	protected void buildExcelTempDocument(Map<String, Object> map, Workbook workbook, HttpServletRequest req,
			HttpServletResponse resp, XSSFSheet sheet) {
		try {
			XSSFRow row = null;
			XSSFCell cell = null;

			Map<String, Object> model = (Map<String, Object>) map.get("excelMap");
			String ctnOrCust = (String) model.get("ctnOrCust");
			String fileName = "";

			if (ctnOrCust.equals("CTN")) {
				row = sheet.createRow(0);
				cell = row.createCell(0);
				cell.setCellValue("CTN_ID");

				cell = row.createCell(1);
				cell.setCellValue("TYPE");

				row = sheet.createRow(1);
				cell = row.createCell(0);
				cell.setCellValue("01012345678");

				cell = row.createCell(1);
				cell.setCellValue("2");

				row = sheet.createRow(2);
				cell = row.createCell(0);
				cell.setCellValue("01099998888");

				cell = row.createCell(1);
				cell.setCellValue("2");

				fileName = "전단_CTNID_업로드_Template.xlsx";
			} else {
				row = sheet.createRow(0);
				cell = row.createCell(0);
				cell.setCellValue("CUST_ID");

				cell = row.createCell(1);
				cell.setCellValue("TYPE");

				row = sheet.createRow(1);
				cell = row.createCell(0);
				cell.setCellValue("4321");

				cell = row.createCell(1);
				cell.setCellValue("2");

				row = sheet.createRow(2);
				cell = row.createCell(0);
				cell.setCellValue("5375");

				cell = row.createCell(1);
				cell.setCellValue("2");

				fileName = "전단_CUSTID_업로드_Template.xlsx";
			}

			fileName = new String(fileName.getBytes("euc-kr"), "8859_1");
			resp.setHeader("Content-Disposition", "attachment; fileName=\"" + fileName + "\";");
			resp.setHeader("Content-Transfer-Encoding", "binary");

		} catch (Exception e) {
			if (LOG.isErrorEnabled()) {
				LOG.error(">>> ExcelView.buildExcelTempDocument Exception ", e);
			}
		}
	}
}

/*
 *  MIDAS version 1.0
 *
 *  Copyright ⓒ 2017 kt corp. All rights reserved.
 *
 *  This is a proprietary software of kt corp, and you may not use this file except in
 *  compliance with license agreement with kt corp. Any redistribution or use of this
 *  software, with or without modification shall be strictly prohibited without prior written
 *  approval of kt corp, and the copyright notice above does not evidence any actual or
 *  intended publication of such software.
 */
package com.kt.midas.adm.common.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.kt.midas.adm.common.vo.FileVO;

/**
 *
 * @author HK
 */
public class FileUtil {

	private static final Logger LOG = LoggerFactory.getLogger(FileUtil.class);
	
    public static FileVO fileSave(MultipartFile mFile, String id, String filePath, String pageOrder, String sidePath, String imgServerUrl){
    	FileVO fileVO = new FileVO();
    	StringBuilder sb = new StringBuilder();
    	
    	Calendar cal = Calendar.getInstance();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
    	
    	String imgPath = "";
    	String orgFileName = "";
    	String tempFileName = "";
    	String ext = "";
    	
    	sb.append(filePath).append("/").append(sdf.format(cal.getTime())).append("/").append(id).append("/");
    	
    	orgFileName = mFile.getOriginalFilename();
    	imgPath = sb.toString();
    	
    	try{
    	
	    	int pos = orgFileName.lastIndexOf( "." );
	    	ext = orgFileName.substring( pos + 1 );
	    	
	    	tempFileName = System.currentTimeMillis() + "_" + pageOrder + "." + ext;
	    	
	    	File fileDir = new File(imgPath);
	    	
	    	if(!fileDir.exists()){
	    		fileDir.mkdirs();
	    	}
	    	
	    	mFile.transferTo(new File(imgPath + tempFileName));
	    	
	    	fileVO.setSaveFileName(tempFileName);
	    	fileVO.setOrgFielName(orgFileName);
	    	fileVO.setImgPath(imgPath);
	    	fileVO.setImgUrl(imgServerUrl+"/"+sidePath+"/"+sdf.format(cal.getTime())+"/"+id+"/");
	    	
    	}catch (Exception e) {
    		if(LOG.isErrorEnabled()){
				LOG.error("FileUtil.fileSave error", e); 
			}
		}
    	return fileVO;
    }

	
	/**
	 * 
	 * @param mFile
	 * @param gubun I : 이미지, E : 엑셀
	 * @return
	 */
	public static boolean fileExtCheck(MultipartFile mFile, String gubun){
		boolean flag = false;
		String orgFileName = mFile.getOriginalFilename();
		int pos = orgFileName.lastIndexOf( "." );
		String ext = orgFileName.substring( pos + 1 );
		int len = 0;
		
		String[] imgExt  = {"gif", "png", "jpg", "jpeg", "bmp"};
		String[] excelExt = {"xlsx"};
		
		if(gubun.equals("E")){
			len = excelExt.length;
			for(int i=0;i<len;i++){
				if (ext.equalsIgnoreCase(excelExt[i])) {
					flag = true;
					break;
				}
			}
		}else{
			len = imgExt.length;
			for(int i=0;i<len;i++){
				if (ext.equalsIgnoreCase(imgExt[i])) {
					flag = true;
					break;
				}
			}
		}
		return flag;
	}
}

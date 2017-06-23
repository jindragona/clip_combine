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
package com.kt.midas.adm.web.main.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kt.midas.adm.common.ResultEnum;
import com.kt.midas.adm.common.service.BaseServiceImpl;
import com.kt.midas.adm.common.vo.JsonVO;
import com.kt.midas.adm.common.vo.UserInfoVO;
import com.kt.midas.adm.web.main.dao.MainDao;
import com.kt.midas.adm.web.main.vo.AuthVO;
import com.kt.midas.adm.web.main.vo.MainVO;
import com.kt.midas.adm.web.main.vo.PwdModTO;
import com.kt.midas.frw.transaction.TxMain;
import com.kt.midas.frw.transaction.TxRequired;

/**
 *
 * @author hwang
 */
@Service
@TxMain
public class MainServiceImpl extends BaseServiceImpl implements MainService{
	
	private static final Logger LOG = LoggerFactory.getLogger(MainServiceImpl.class);
	
    @Autowired
    private MainDao mainDao;
    
    @Value("${pwd.mdf.date}")
    private String pwdMdfDate;
    
	@Override
	@TxRequired
	public JsonVO pwdMod(PwdModTO to){
		JsonVO jsonVO = new JsonVO();
    	Calendar cal = Calendar.getInstance();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
		
		try{
			UserInfoVO info = getUserName();
			to.setUserId(info.getUserId());
			String oldPasswd = to.getOldPwd();
			String encPwd = oldPasswd;
			
			to.setEncOldPwd(encPwd);
			
			int cnt = mainDao.searchUserIdPwd(to);
			
			if(cnt < 1){
				jsonVO.setCode(ResultEnum.LOGIN_OLD_PWD.result);
				return jsonVO;
			}
	    	
			String newPasswd = to.getNewPwd();
			to.setEncNewPwd(newPasswd);
			
			cal.add(Calendar.DATE, Integer.parseInt(pwdMdfDate));
			to.setPwdModDate(sdf.format(cal.getTime()));
			
			mainDao.pwdMod(to);
			
			jsonVO.setCode(ResultEnum.SUCCESS.result);
		}catch (Exception e) {
			if(LOG.isErrorEnabled()){
				LOG.error("[user add Exception] {}", e.getMessage(), e);
			}
		}
		
    	return jsonVO;
	}

	@Override
	public List<MainVO> getLftAge(MainVO mainVO) {
		List<MainVO> lftAgeList = mainDao.getLftAge(mainVO);
		return lftAgeList;
	}

	@Override
	public List<MainVO> getLftGdr(MainVO mainVO) {
		List<MainVO> lftGdrList = mainDao.getLftGdr(mainVO);
		return lftGdrList;
	}

	@Override
	public List<MainVO> getLftSndMonthStat(MainVO mainVO) {
		List<MainVO> lftMonth = mainDao.getLftSndMonthStat(mainVO);
		return lftMonth;
	}

	@Override
	public List<MainVO> getBannAge(MainVO mainVO) {
		List<MainVO> bannAgeList = mainDao.getBannAge(mainVO);
		return bannAgeList;
	}

	@Override
	public List<MainVO> getBannSndMonthStat(MainVO mainVO) {
		List<MainVO> bannMonth = mainDao.getBannSndMonthStat(mainVO);
		return bannMonth;
	}

	@Override
	public List<MainVO> getBannGdr(MainVO mainVO) {
		List<MainVO> bannGdr = mainDao.getBannGdr(mainVO);
		return bannGdr;
	}

	@Override
	public List<MainVO> getMcrdAge(MainVO mainVO) {
		List<MainVO> mcrdAge = mainDao.getMcrdAge(mainVO);
		return mcrdAge;
	}

	@Override
	public List<MainVO> getMcrdGdr(MainVO mainVO) {
		List<MainVO> mcrdGdr = mainDao.getMcrdGdr(mainVO);
		return mcrdGdr;
	}

	@Override
	public List<MainVO> getMcrdArea(MainVO mainVO) {
		List<MainVO> mcrdArea = mainDao.getMcrdArea(mainVO);
		return mcrdArea;
	}

	@Override
	@TxRequired
	public JsonVO updateAuthNo(AuthVO authVO) {
		JsonVO jsonVO = new JsonVO();
		if("Y".equals(authVO.getAuthStat())){
			Random random = new Random();
			String authNo = String.valueOf(random.nextInt(999999)); //문자 전송 로직으로 대체(테스트용, 난수발생코드)
			authVO.setAuthNo(authNo);
		}
		int udt = mainDao.updateAuthNo(authVO);
		if(udt > 0){
			jsonVO.setCode(ResultEnum.SUCCESS.result);
		}else{
			jsonVO.setCode(ResultEnum.FAIL.result);
		}
		return jsonVO;
	}

}

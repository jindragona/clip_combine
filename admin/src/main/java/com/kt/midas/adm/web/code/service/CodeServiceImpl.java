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
package com.kt.midas.adm.web.code.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kt.midas.adm.common.ResultEnum;
import com.kt.midas.adm.common.service.BaseServiceImpl;
import com.kt.midas.adm.common.vo.JsonVO;
import com.kt.midas.adm.common.vo.UserInfoVO;
import com.kt.midas.adm.web.code.dao.CodeDao;
import com.kt.midas.adm.web.code.vo.CodeSearchTO;
import com.kt.midas.adm.web.code.vo.CodeTO;
import com.kt.midas.adm.web.code.vo.CodeVO;
import com.kt.midas.adm.web.code.vo.CommonCodeVO;
import com.kt.midas.frw.transaction.TxMain;
import com.kt.midas.frw.transaction.TxRequired;

/**
 *
 * @author hwang
 */
@Service
@TxMain
public class CodeServiceImpl extends BaseServiceImpl implements CodeService{
    
    @Autowired
    private CodeDao codeDao;
    
    @Override
    public int getlistDataCnt(CodeSearchTO to){
    	return codeDao.findTotalRecordsBySearch(to);
    }
    
    @Override
    public List<CodeVO> getlistData(CodeSearchTO to) {
    	return codeDao.findBySearch(to);
    }
    
    @Override
    @TxRequired
    public JsonVO addRow(CodeTO to) {
    	JsonVO jsonVO = new JsonVO();
    	
    	UserInfoVO info = getUserName();
		to.setInsUser(info.getUserId());
		to.setInsUserName(info.getUserName());
    	
		codeDao.insert(to);
		jsonVO.setCode(ResultEnum.SUCCESS.result);
    	
    	return jsonVO;
    }
    
    @Override
    @TxRequired
    public JsonVO modRow(CodeTO to) {
    	JsonVO jsonVO = new JsonVO();
    	
    	UserInfoVO info = getUserName();
		to.setInsUser(info.getUserId());
		to.setInsUserName(info.getUserName());
    	
		codeDao.update(to);
		jsonVO.setCode(ResultEnum.SUCCESS.result);
    	
    	return jsonVO;
    }
    
    @Override
    public CodeVO getCodeDetail(String id){
    	return codeDao.getCodeDetail(id);
    }
    
    @Override
    public List<CommonCodeVO> commonCodeList(CodeSearchTO to){
    	return codeDao.commonCodeList(to);
    }
    
    @Override
    public JsonVO checkCodeId(final String CodeId){
    	JsonVO jsonVO = new JsonVO();
    	int cnt = codeDao.checkCodeId(CodeId);
		if(cnt > 0){
			jsonVO.setCode(ResultEnum.DUPLICATED.result);
		}else{
			jsonVO.setCode(ResultEnum.SUCCESS.result);    			
		}
    	return jsonVO;
    }
}

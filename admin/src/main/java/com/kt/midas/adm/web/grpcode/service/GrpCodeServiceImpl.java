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
package com.kt.midas.adm.web.grpcode.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kt.midas.adm.common.ResultEnum;
import com.kt.midas.adm.common.service.BaseServiceImpl;
import com.kt.midas.adm.common.vo.JsonVO;
import com.kt.midas.adm.web.grpcode.dao.GrpCodeDao;
import com.kt.midas.adm.web.grpcode.vo.GrpCodeSearchTO;
import com.kt.midas.adm.web.grpcode.vo.GrpCodeTO;
import com.kt.midas.adm.web.grpcode.vo.GrpCodeVO;
import com.kt.midas.frw.transaction.TxMain;
import com.kt.midas.frw.transaction.TxRequired;

/**
 *
 * @author hwang
 */
@Service
@TxMain
public class GrpCodeServiceImpl extends BaseServiceImpl implements GrpCodeService{
    
    @Autowired
    private GrpCodeDao grpCodeDao;
    
    @Override
    public int getlistDataCnt(GrpCodeSearchTO to){
    	return grpCodeDao.findTotalRecordsBySearch(to);
    }
    
    @Override
    public List<GrpCodeVO> getlistData(GrpCodeSearchTO to) {
    	return grpCodeDao.findBySearch(to);
    }
    
    @Override
    @TxRequired
    public JsonVO addRow(GrpCodeTO to) {
    	JsonVO jsonVO = new JsonVO();
    	
		grpCodeDao.insert(to);
		jsonVO.setCode(ResultEnum.SUCCESS.result);
    	
    	return jsonVO;
    }
    
    @Override
    @TxRequired
    public JsonVO modRow(GrpCodeTO to) {
    	JsonVO jsonVO = new JsonVO();
    	
    	grpCodeDao.update(to);
		jsonVO.setCode(ResultEnum.SUCCESS.result);
    	
    	return jsonVO;
    }
    
    @Override
    public GrpCodeVO getGrpCodeDetail(String id){
    	return grpCodeDao.getGrpCodeDetail(id);
    }
    
    @Override
    public List<GrpCodeVO> getGrpCodeList(){
    	return grpCodeDao.getGrpCodeList();
    }
}

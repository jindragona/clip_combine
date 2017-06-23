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
package com.kt.midas.adm.web.userauth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kt.midas.adm.common.ResultEnum;
import com.kt.midas.adm.common.service.BaseServiceImpl;
import com.kt.midas.adm.common.vo.JsonVO;
import com.kt.midas.adm.common.vo.UserInfoVO;
import com.kt.midas.adm.web.userauth.dao.UserAuthDao;
import com.kt.midas.adm.web.userauth.vo.UserAuthSearchTO;
import com.kt.midas.adm.web.userauth.vo.UserAuthTO;
import com.kt.midas.adm.web.userauth.vo.UserAuthVO;
import com.kt.midas.frw.transaction.TxMain;
import com.kt.midas.frw.transaction.TxRequired;

/**
 *
 * @author hwang
 */
@Service
@TxMain
public class UserAuthServiceImpl extends BaseServiceImpl implements UserAuthService{
    
    @Autowired
    private UserAuthDao userAuthDao;
    
    @Override
    public List<UserAuthVO> getlistData(UserAuthSearchTO userAuthSearchTO) {
    	List<UserAuthVO> notiList = userAuthDao.findBySearch(userAuthSearchTO);
        return notiList;
    }
    
    @Override
    public int getlistDataCnt(UserAuthSearchTO userAuthSearchTO) {
    	int cnt = userAuthDao.findTotalRecordsBySearch(userAuthSearchTO);
        return cnt;
    }
    
    @Override
    public UserAuthVO getUserAuthDetail(String rolId){
    	return userAuthDao.getUserAuthDetail(Integer.parseInt(rolId));
    }
    
    @Override
    @TxRequired
    public JsonVO addRow(UserAuthTO to){
    	JsonVO jsonVO = new JsonVO();
    	
    	UserInfoVO info = getUserName();
    	to.setInsUser(info.getUserId());
    	to.setInsUserName(info.getUserName());
    	
    	userAuthDao.insert(to);
		jsonVO.setCode(ResultEnum.SUCCESS.result);
    	return jsonVO;
    }
    
    @Override
    @TxRequired
    public JsonVO modRow(UserAuthTO to){
    	JsonVO jsonVO = new JsonVO();
    	userAuthDao.update(to);
		jsonVO.setCode(ResultEnum.SUCCESS.result);
    	return jsonVO;
    }
    
    @Override
    @TxRequired
    public JsonVO removeAuthMenu(String rolId){
    	JsonVO jsonVO = new JsonVO();
    	
    	// 역할 사용자 정보 삭제
    	userAuthDao.removeAllUserAuth(Integer.parseInt(rolId));
    	
    	// 역할 메뉴 삭제
    	userAuthDao.removeAllMenuAuth(Integer.parseInt(rolId));
    	
    	// 해당 역할 삭제 
    	userAuthDao.removeAuth(Integer.parseInt(rolId));
    	
		jsonVO.setCode(ResultEnum.SUCCESS.result);
    	return jsonVO;
    }
}

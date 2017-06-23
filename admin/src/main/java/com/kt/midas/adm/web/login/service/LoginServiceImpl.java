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
package com.kt.midas.adm.web.login.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kt.midas.adm.common.service.BaseServiceImpl;
import com.kt.midas.adm.common.vo.CommonManagerVO;
import com.kt.midas.adm.web.login.dao.LoginDao;
import com.kt.midas.adm.web.login.vo.UserAuthMenuSearchTO;
import com.kt.midas.adm.web.menu.vo.MenuTreeVO;
import com.kt.midas.frw.transaction.TxMain;
import com.kt.midas.frw.transaction.TxRequired;

/**
 * TBL_TLAB_MANAGER 테이블 서비스 클래스
 *
 * @author bitcore
 *
 */
@Service
@TxMain
public class LoginServiceImpl extends BaseServiceImpl implements LoginService<CommonManagerVO, String> {
    
    @Autowired
    private LoginDao logindao;

    @Override
    public CommonManagerVO selectByPK(String pk) {
        return logindao.selectByPK(pk);
    }

    @Override
    public CommonManagerVO selectByLoginId(String loginId) {
        return logindao.selectByPK(loginId);
    }

    @Override
    public CommonManagerVO getWhereCondition(CommonManagerVO tm) {
        
        CommonManagerVO tmp = null;
        
        if (tm == null) {
            tmp = new CommonManagerVO();
        } else {
            tmp = new CommonManagerVO();
            tmp = tm;
            
            
        }
        return tmp;
    }
    
    @Override
    @TxRequired
    public void insertLoginHist(CommonManagerVO commonManager){
    	logindao.insertLoginHist(commonManager);
    }
    
    @Override
    @TxRequired
    public void updateLoginHist(CommonManagerVO commonManager){
    	logindao.updateLoginHist(commonManager);
    }

	@Override
	@TxRequired
	public void updateByPK(CommonManagerVO commonManager) {
		logindao.updateByPK(commonManager);
	}

	@Override
	public List<MenuTreeVO> selectAuthMenuList(String pk){
		List<MenuTreeVO> upperMenuList = logindao.selectAuthUppMenuList(pk);
		upperMenuList = menuFactorial(upperMenuList, pk);
		return upperMenuList;
	}
	
	public List<MenuTreeVO> menuFactorial(List<MenuTreeVO> list, String pk){
		UserAuthMenuSearchTO to = null;
    	for(MenuTreeVO vo : list){
    		to = new UserAuthMenuSearchTO();
    		to.setUserId(pk);
    		to.setMenuId(Integer.parseInt(vo.getMenuId()));
    		
    		List<MenuTreeVO> childList = logindao.selectAuthMenuList(to);
    		childList = menuFactorial(childList, pk);
        	vo.setChildren(childList);
        	
        	to = null;
        }
    	return list;
    }
}

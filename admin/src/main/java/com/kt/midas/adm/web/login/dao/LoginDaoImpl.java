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
package com.kt.midas.adm.web.login.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kt.midas.adm.common.vo.CommonManagerVO;
import com.kt.midas.adm.web.login.vo.UserAuthMenuSearchTO;
import com.kt.midas.adm.web.menu.vo.MenuTreeVO;
import com.kt.midas.frw.base.AbstractBaseDAO;

@Repository
public class LoginDaoImpl extends AbstractBaseDAO implements LoginDao{

	private static final String SQL_NAMESPACE = "midas.adm.web.login.dao.LoginDao";
	
	@Override
	public CommonManagerVO selectByPK(String userId){
		return getSqlSession().selectOne(genSqlId(SQL_NAMESPACE, "selectByPK"), userId);
	}
	
	@Override
	public void insertLoginHist(CommonManagerVO commonManager){
		getSqlSession().insert(genSqlId(SQL_NAMESPACE, "insertLoginHist"), commonManager);
	}
	
	@Override
	public void updateLoginHist(CommonManagerVO commonManager){
		getSqlSession().update(genSqlId(SQL_NAMESPACE, "updateLoginHist"), commonManager);
	}
	
	@Override
	public void updateByPK(CommonManagerVO commonManager){
		getSqlSession().update(genSqlId(SQL_NAMESPACE, "updateByPK"), commonManager);
	}
	
	@Override
	public List<MenuTreeVO> selectAuthUppMenuList(String userId){
		return getSqlSession().selectList(genSqlId(SQL_NAMESPACE, "selectAuthUppMenuList"), userId);
	}
	
	@Override
	public List<MenuTreeVO> selectAuthMenuList(UserAuthMenuSearchTO to){
		return getSqlSession().selectList(genSqlId(SQL_NAMESPACE, "selectAuthMenuList"), to);
	}
}

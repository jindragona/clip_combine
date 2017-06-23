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
package com.kt.midas.adm.web.userauth.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kt.midas.adm.web.userauth.vo.UserAuthSearchTO;
import com.kt.midas.adm.web.userauth.vo.UserAuthTO;
import com.kt.midas.adm.web.userauth.vo.UserAuthVO;
import com.kt.midas.frw.base.AbstractBaseDAO;

@Repository
public class UserAuthDaoImpl extends AbstractBaseDAO implements UserAuthDao {

	private static final String SQL_NAMESPACE = "midas.adm.web.userauth.dao.UserAuthDao";
	
	@Override
	public List<UserAuthVO> findBySearch(UserAuthSearchTO to){
		return getSqlSession().selectList(genSqlId(SQL_NAMESPACE, "findBySearch"), to);
	}
	
	@Override
	public int findTotalRecordsBySearch(UserAuthSearchTO to){
		return getSqlSession().selectOne(genSqlId(SQL_NAMESPACE, "findTotalRecordsBySearch"), to);
	}
	
	@Override
	public UserAuthVO getUserAuthDetail(int rolId){
		return getSqlSession().selectOne(genSqlId(SQL_NAMESPACE, "getUserAuthDetail"), rolId);
	}
	
	@Override
	public void insert(UserAuthTO to){
		getSqlSession().insert(genSqlId(SQL_NAMESPACE, "insert"), to);
	}
	
	@Override
	public void update(UserAuthTO to){
		getSqlSession().update(genSqlId(SQL_NAMESPACE, "update"), to);
	}
	
	@Override
	public void removeAllUserAuth(int rolId){
		getSqlSession().delete(genSqlId(SQL_NAMESPACE, "removeAllUserAuth"), rolId);
	}
	
	@Override
	public void removeAllMenuAuth(int rolId){
		getSqlSession().delete(genSqlId(SQL_NAMESPACE, "removeAllMenuAuth"), rolId);
	}
	
	@Override
	public void removeAuth(int rolId){
		getSqlSession().delete(genSqlId(SQL_NAMESPACE, "removeAuth"), rolId);
	}
}

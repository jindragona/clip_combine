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
package com.kt.midas.adm.web.user.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kt.midas.adm.web.user.vo.AuthIpVO;
import com.kt.midas.adm.web.user.vo.UserAuthIpTO;
import com.kt.midas.adm.web.user.vo.UserSearchTO;
import com.kt.midas.adm.web.user.vo.UserTO;
import com.kt.midas.adm.web.user.vo.UserVO;
import com.kt.midas.adm.web.userauth.vo.UserMenuAuthTO;
import com.kt.midas.frw.base.AbstractBaseDAO;

@Repository
public class UserDaoImpl extends AbstractBaseDAO implements UserDao {

	private static final String SQL_NAMESPACE = "midas.adm.web.user.dao.UserDao";
	
	@Override
	public List<UserVO> findBySearch(UserSearchTO to){
		return getSqlSession().selectList(genSqlId(SQL_NAMESPACE, "findBySearch"), to);
	}
	
	@Override
	public int findTotalRecordsBySearch(UserSearchTO to){
		return getSqlSession().selectOne(genSqlId(SQL_NAMESPACE, "findTotalRecordsBySearch"), to);
	}
	
	@Override
	public UserVO getUserDetail(String userId){
		return getSqlSession().selectOne(genSqlId(SQL_NAMESPACE, "getUserDetail"), Integer.parseInt(userId));
	}
	
	@Override
	public int checkLogingId(String userId){
		return getSqlSession().selectOne(genSqlId(SQL_NAMESPACE, "checkLogingId"), userId);
	}
	
	@Override
	public List<UserVO> getUserList(int rolId){
		return getSqlSession().selectList(genSqlId(SQL_NAMESPACE, "getUserList"), rolId);
	}
	
	@Override
	public List<UserVO> notMappingUser(int rolId){
		return getSqlSession().selectList(genSqlId(SQL_NAMESPACE, "notMappingUser"), rolId);
	}
	
	@Override
	public void insert(UserTO to){
		getSqlSession().insert(genSqlId(SQL_NAMESPACE, "insert"), to);
	}
	
	@Override
	public void update(UserTO to){
		getSqlSession().update(genSqlId(SQL_NAMESPACE, "update"), to);
	}
	
	@Override
	public void modAdmRow(UserTO to){
		getSqlSession().update(genSqlId(SQL_NAMESPACE, "modAdmRow"), to);
	}
	
	@Override
	public void pwdInit(UserTO to){
		getSqlSession().update(genSqlId(SQL_NAMESPACE, "pwdInit"), to);
	}
	
	@Override
	public void pwdErrorCntInit(String userId){
		getSqlSession().update(genSqlId(SQL_NAMESPACE, "pwdErrorCntInit"), userId);
	}
	
	@Override
	public void mappingUser(UserMenuAuthTO to){
		getSqlSession().insert(genSqlId(SQL_NAMESPACE, "mappingUser"), to);
	}
	
	@Override
	public void mappingCloseUser(UserMenuAuthTO to){
		getSqlSession().delete(genSqlId(SQL_NAMESPACE, "mappingCloseUser"), to);
	}
	
	@Override
	public void stopUser(String userId){
		getSqlSession().update(genSqlId(SQL_NAMESPACE, "stopUser"), userId);
	}
	
	@Override
    public void useUser(String userId){
		getSqlSession().update(genSqlId(SQL_NAMESPACE, "useUser"), userId);
	}
	
	@Override
	public List<AuthIpVO> getAuthIpList(UserTO userTO){
		return getSqlSession().selectList(genSqlId(SQL_NAMESPACE, "getAuthIpList"), userTO);
	}
	
	@Override
	public void userIpAuthAdd(UserAuthIpTO to){
		getSqlSession().insert(genSqlId(SQL_NAMESPACE, "userIpAuthAdd"), to);
	}
	
	@Override
	public void userIpAuthRemove(UserAuthIpTO to){
		getSqlSession().delete(genSqlId(SQL_NAMESPACE, "userIpAuthRemove"), to);
	}
	
	@Override
	public int userIdPwdCheck(UserTO userTO){
		return getSqlSession().selectOne(genSqlId(SQL_NAMESPACE, "userIdPwdCheck"), userTO);
	}
}

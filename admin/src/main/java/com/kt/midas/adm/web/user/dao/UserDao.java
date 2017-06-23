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

import com.kt.midas.adm.web.user.vo.AuthIpVO;
import com.kt.midas.adm.web.user.vo.UserAuthIpTO;
import com.kt.midas.adm.web.user.vo.UserSearchTO;
import com.kt.midas.adm.web.user.vo.UserTO;
import com.kt.midas.adm.web.user.vo.UserVO;
import com.kt.midas.adm.web.userauth.vo.UserMenuAuthTO;

public interface UserDao {

	public List<UserVO> findBySearch(UserSearchTO to);
	public int findTotalRecordsBySearch(UserSearchTO to);
	public UserVO getUserDetail(String userId);
	public int checkLogingId(String userId);
	public List<UserVO> getUserList(int rolId);
	public List<UserVO> notMappingUser(int rolId);
	
	public List<AuthIpVO> getAuthIpList(UserTO userTO);
	
	public void insert(UserTO to);
	public void update(UserTO to);
	public void modAdmRow(UserTO to);
	public void pwdInit(UserTO to);
	public void pwdErrorCntInit(String userId);
	public void mappingUser(UserMenuAuthTO to);
	public void mappingCloseUser(UserMenuAuthTO to);
	
	public void stopUser(String userId);
    public void useUser(String userId);
    public void userIpAuthAdd(UserAuthIpTO to);
    public void userIpAuthRemove(UserAuthIpTO to);
    
    public int userIdPwdCheck(UserTO userTO);
    
}

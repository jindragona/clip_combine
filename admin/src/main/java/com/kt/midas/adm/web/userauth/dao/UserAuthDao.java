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

import com.kt.midas.adm.web.userauth.vo.UserAuthSearchTO;
import com.kt.midas.adm.web.userauth.vo.UserAuthTO;
import com.kt.midas.adm.web.userauth.vo.UserAuthVO;

public interface UserAuthDao {

	public List<UserAuthVO> findBySearch(UserAuthSearchTO to);
	public int findTotalRecordsBySearch(UserAuthSearchTO to);
	public UserAuthVO getUserAuthDetail(int rolId);
	
	public void insert(UserAuthTO to);
	public void update(UserAuthTO to);
	
	public void removeAllUserAuth(int rolId);
	public void removeAllMenuAuth(int rolId);
	public void removeAuth(int rolId);
}

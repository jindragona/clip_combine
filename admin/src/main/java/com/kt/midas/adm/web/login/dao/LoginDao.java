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

import com.kt.midas.adm.common.vo.CommonManagerVO;
import com.kt.midas.adm.web.login.vo.UserAuthMenuSearchTO;
import com.kt.midas.adm.web.menu.vo.MenuTreeVO;

public interface LoginDao {

	public CommonManagerVO selectByPK(String userId);
	public void insertLoginHist(CommonManagerVO commonManager);
	public void updateLoginHist(CommonManagerVO commonManager);
	public void updateByPK(CommonManagerVO commonManager);
	
	public List<MenuTreeVO> selectAuthUppMenuList(String userId);
	public List<MenuTreeVO> selectAuthMenuList(UserAuthMenuSearchTO to);
}

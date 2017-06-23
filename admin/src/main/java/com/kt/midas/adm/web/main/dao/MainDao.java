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
package com.kt.midas.adm.web.main.dao;

import java.util.List;

import com.kt.midas.adm.web.main.vo.AuthVO;
import com.kt.midas.adm.web.main.vo.MainVO;
import com.kt.midas.adm.web.main.vo.PwdModTO;

public interface MainDao {

	List<MainVO> getLftSndStat(MainVO mainVO);

	List<MainVO> getLftSndMonthStat(MainVO mainVO);

	List<MainVO> getLftAge(MainVO mainVO);
	
	List<MainVO> getLftGdr(MainVO mainVO);
	
	List<MainVO> getBannAge(MainVO mainVO);
	
	List<MainVO> getBannGdr(MainVO mainVO);

	List<MainVO> getBannSndMonthStat(MainVO mainVO);
	
	List<MainVO> getMcrdAge(MainVO mainVO);
	
	List<MainVO> getMcrdGdr(MainVO mainVO);
	
	public int searchUserIdPwd(PwdModTO to);
	
	public void pwdMod(PwdModTO to);

	List<MainVO> getMcrdArea(MainVO mainVO);

	public int updateAuthNo(AuthVO authVO);

}

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
package com.kt.midas.adm.web.main.service;

import java.util.List;

import com.kt.midas.adm.common.vo.JsonVO;
import com.kt.midas.adm.web.main.vo.AuthVO;
import com.kt.midas.adm.web.main.vo.MainVO;
import com.kt.midas.adm.web.main.vo.PwdModTO;

/**
 *
 * @author hwang
 */
public interface MainService {

	public JsonVO pwdMod(PwdModTO to);
	public List<MainVO> getLftAge(MainVO mainVO);
	public List<MainVO> getLftGdr(MainVO mainVO);
	public List<MainVO> getLftSndMonthStat(MainVO mainVO);
	public List<MainVO> getBannAge(MainVO mainVO);
	public List<MainVO> getBannSndMonthStat(MainVO mainVO);
	public List<MainVO> getBannGdr(MainVO mainVO);
	public List<MainVO> getMcrdAge(MainVO mainVO);
	public List<MainVO> getMcrdGdr(MainVO mainVO);
	public List<MainVO> getMcrdArea(MainVO mainVO);
	public JsonVO updateAuthNo(AuthVO authVO);
}

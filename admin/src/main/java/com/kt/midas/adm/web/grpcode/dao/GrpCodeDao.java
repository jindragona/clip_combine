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
package com.kt.midas.adm.web.grpcode.dao;

import java.util.List;

import com.kt.midas.adm.web.grpcode.vo.GrpCodeSearchTO;
import com.kt.midas.adm.web.grpcode.vo.GrpCodeTO;
import com.kt.midas.adm.web.grpcode.vo.GrpCodeVO;

public interface GrpCodeDao {
	public int findTotalRecordsBySearch(GrpCodeSearchTO to);
	public List<GrpCodeVO> findBySearch(GrpCodeSearchTO to);
	public GrpCodeVO getGrpCodeDetail(String id);
	
	public void insert(GrpCodeTO to);
	public void update(GrpCodeTO to);
	
	public List<GrpCodeVO> getGrpCodeList();
	
}

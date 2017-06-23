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
package com.kt.midas.adm.web.code.dao;

import java.util.List;

import com.kt.midas.adm.web.code.vo.CodeSearchTO;
import com.kt.midas.adm.web.code.vo.CodeTO;
import com.kt.midas.adm.web.code.vo.CodeVO;
import com.kt.midas.adm.web.code.vo.CommonCodeVO;

public interface CodeDao {

	public int findTotalRecordsBySearch(CodeSearchTO codeSearchTO);
	public List<CodeVO> findBySearch(CodeSearchTO codeSearchTO);
	public CodeVO getCodeDetail(String id);
	public List<CommonCodeVO> commonCodeList(CodeSearchTO to);
	
	public void insert(CodeTO to);
	public void update(CodeTO to);
	public int checkCodeId(String codeId);
}

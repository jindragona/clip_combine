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

import org.springframework.stereotype.Repository;

import com.kt.midas.adm.web.code.vo.CodeSearchTO;
import com.kt.midas.adm.web.code.vo.CodeTO;
import com.kt.midas.adm.web.code.vo.CodeVO;
import com.kt.midas.adm.web.code.vo.CommonCodeVO;
import com.kt.midas.frw.base.AbstractBaseDAO;

@Repository
public class CodeDaoImpl extends AbstractBaseDAO implements CodeDao{

	private static final String SQL_NAMESPACE = "midas.adm.web.code.dao.CodeDao";
	
	@Override
	public int findTotalRecordsBySearch(CodeSearchTO codeSearchTO){
		return getSqlSession().selectOne(genSqlId(SQL_NAMESPACE, "findTotalRecordsBySearch"), codeSearchTO);
	}
	
	@Override
	public List<CodeVO> findBySearch(CodeSearchTO codeSearchTO){
		return getSqlSession().selectList(genSqlId(SQL_NAMESPACE, "findBySearch"), codeSearchTO);
	}
	
	@Override
	public CodeVO getCodeDetail(String id){
		return getSqlSession().selectOne(genSqlId(SQL_NAMESPACE, "getCodeDetail"), id);
	}
	
	@Override
	public List<CommonCodeVO> commonCodeList(CodeSearchTO to){
		return getSqlSession().selectList(genSqlId(SQL_NAMESPACE, "commonCodeList"), to);
	}
	
	@Override
	public void insert(CodeTO to){
		getSqlSession().insert(genSqlId(SQL_NAMESPACE, "insert"), to);
	}
	
	@Override
	public void update(CodeTO to){
		getSqlSession().update(genSqlId(SQL_NAMESPACE, "update"), to);
	}
	
	@Override
	public int checkCodeId(String codeId){
		return getSqlSession().selectOne(genSqlId(SQL_NAMESPACE, "checkCodeId"), codeId);
	}
}

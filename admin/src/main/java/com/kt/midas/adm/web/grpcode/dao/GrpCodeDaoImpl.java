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

import org.springframework.stereotype.Repository;

import com.kt.midas.adm.web.grpcode.vo.GrpCodeSearchTO;
import com.kt.midas.adm.web.grpcode.vo.GrpCodeTO;
import com.kt.midas.adm.web.grpcode.vo.GrpCodeVO;
import com.kt.midas.frw.base.AbstractBaseDAO;

@Repository
public class GrpCodeDaoImpl extends AbstractBaseDAO implements GrpCodeDao{

	private static final String SQL_NAMESPACE = "midas.adm.web.grpcode.dao.GrpCodeDao";
	
	@Override
	public int findTotalRecordsBySearch(GrpCodeSearchTO to){
		return getSqlSession().selectOne(genSqlId(SQL_NAMESPACE, "findTotalRecordsBySearch"), to);
	}
	
	@Override
	public List<GrpCodeVO> findBySearch(GrpCodeSearchTO to){
		return getSqlSession().selectList(genSqlId(SQL_NAMESPACE, "findBySearch"), to);
	}
	
	@Override
	public GrpCodeVO getGrpCodeDetail(String id){
		return getSqlSession().selectOne(genSqlId(SQL_NAMESPACE, "getGrpCodeDetail"), id);
	}
	
	@Override
	public void insert(GrpCodeTO to){
		getSqlSession().insert(genSqlId(SQL_NAMESPACE, "insert"), to);
	}
	
	@Override
	public void update(GrpCodeTO to){
		getSqlSession().update(genSqlId(SQL_NAMESPACE, "update"), to);
	}
	
	@Override
	public List<GrpCodeVO> getGrpCodeList(){
		return getSqlSession().selectList(genSqlId(SQL_NAMESPACE, "getGrpCodeList"));
	}
}

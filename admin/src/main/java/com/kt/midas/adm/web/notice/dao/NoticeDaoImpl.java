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
package com.kt.midas.adm.web.notice.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kt.midas.adm.web.notice.vo.NotiSearchTO;
import com.kt.midas.adm.web.notice.vo.NotiTO;
import com.kt.midas.adm.web.notice.vo.NotiVO;
import com.kt.midas.frw.base.AbstractBaseDAO;

@Repository
public class NoticeDaoImpl extends AbstractBaseDAO implements NoticeDao {

	private static final String SQL_NAMESPACE = "midas.adm.web.notice.dao.NoticeDao";
	
	@Override
	public List<NotiVO> findBySearch(NotiSearchTO to){
		return getSqlSession().selectList(genSqlId(SQL_NAMESPACE, "findBySearch"), to);
	}
	
	@Override
	public int findTotalRecordsBySearch(NotiSearchTO to){
		return getSqlSession().selectOne(genSqlId(SQL_NAMESPACE, "findTotalRecordsBySearch"), to);
	}
	
	@Override
	public NotiVO getNotiDetail(int id){
		return getSqlSession().selectOne(genSqlId(SQL_NAMESPACE, "getNotiDetail"), id);
	}
	
	@Override
	public void insert(NotiTO to){
		getSqlSession().insert(genSqlId(SQL_NAMESPACE, "insert"), to);
	}
	
	@Override
	public void update(NotiTO to){
		getSqlSession().update(genSqlId(SQL_NAMESPACE, "update"), to);
	}
}

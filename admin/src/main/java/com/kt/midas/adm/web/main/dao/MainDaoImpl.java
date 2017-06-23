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

import org.springframework.stereotype.Repository;

import com.kt.midas.adm.web.main.vo.AuthVO;
import com.kt.midas.adm.web.main.vo.MainVO;
import com.kt.midas.adm.web.main.vo.PwdModTO;
import com.kt.midas.frw.base.AbstractBaseDAO;

@Repository
public class MainDaoImpl extends AbstractBaseDAO implements MainDao{

	private static final String SQL_NAMESPACE = "midas.adm.web.main.dao.MainDao";

	@Override
	public List<MainVO> getLftSndStat(MainVO mainVO) {
		return getSqlSession().selectList(genSqlId(SQL_NAMESPACE,"getLftSndStat"), mainVO);
	}

	@Override
	public List<MainVO> getLftSndMonthStat(MainVO mainVO) {
		return getSqlSession().selectList(genSqlId(SQL_NAMESPACE,"getLftSndMonthStat"), mainVO);
	}

	@Override
	public List<MainVO> getLftAge(MainVO mainVO) {
		return getSqlSession().selectList(genSqlId(SQL_NAMESPACE,"getLftAge"), mainVO);
	}

	@Override
	public List<MainVO> getLftGdr(MainVO mainVO) {
		return getSqlSession().selectList(genSqlId(SQL_NAMESPACE,"getLftGdr"), mainVO);
	}

	@Override
	public List<MainVO> getBannAge(MainVO mainVO) {
		return getSqlSession().selectList(genSqlId(SQL_NAMESPACE,"getBannAge"), mainVO);
	}

	@Override
	public List<MainVO> getBannGdr(MainVO mainVO) {
		return getSqlSession().selectList(genSqlId(SQL_NAMESPACE,"getBannGdr"), mainVO);
	}

	@Override
	public List<MainVO> getBannSndMonthStat(MainVO mainVO) {
		return getSqlSession().selectList(genSqlId(SQL_NAMESPACE,"getBannSndMonthStat"), mainVO);
	}

	@Override
	public List<MainVO> getMcrdAge(MainVO mainVO) {
		return getSqlSession().selectList(genSqlId(SQL_NAMESPACE,"getMcrdAge"), mainVO);
	}

	@Override
	public List<MainVO> getMcrdGdr(MainVO mainVO) {
		return getSqlSession().selectList(genSqlId(SQL_NAMESPACE,"getMcrdGdr"), mainVO);
	}
	
	@Override
	public int searchUserIdPwd(PwdModTO to){
		return getSqlSession().selectOne(genSqlId(SQL_NAMESPACE, "searchUserIdPwd"), to);
	}
	
	@Override
	public void pwdMod(PwdModTO to){
		getSqlSession().update(genSqlId(SQL_NAMESPACE, "pwdMod"), to);
	}

	@Override
	public List<MainVO> getMcrdArea(MainVO mainVO) {
		return getSqlSession().selectList(genSqlId(SQL_NAMESPACE,"getMcrdArea"), mainVO);
	}
	
	@Override
	public int updateAuthNo(AuthVO authVO) {
		return getSqlSession().update(genSqlId(SQL_NAMESPACE, "updateAuthNo"), authVO);
	}

}

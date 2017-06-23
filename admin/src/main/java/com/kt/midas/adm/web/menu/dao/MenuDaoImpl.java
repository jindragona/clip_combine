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
package com.kt.midas.adm.web.menu.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kt.midas.adm.web.menu.vo.MenuTO;
import com.kt.midas.adm.web.menu.vo.MenuTreeVO;
import com.kt.midas.adm.web.menu.vo.MenuVO;
import com.kt.midas.adm.web.userauth.vo.UserMenuAuthTO;
import com.kt.midas.frw.base.AbstractBaseDAO;

@Repository
public class MenuDaoImpl extends AbstractBaseDAO implements MenuDao {

	private static final String SQL_NAMESPACE = "midas.adm.web.menu.dao.MenuDao";
	
	@Override
	public List<MenuTreeVO> getUpperMenuTreeList(){
		return getSqlSession().selectList(genSqlId(SQL_NAMESPACE, "getUpperMenuTreeList"));
	}
	
	@Override
	public MenuVO getMenuDetail(int menuId){
		return getSqlSession().selectOne(genSqlId(SQL_NAMESPACE, "getMenuDetail"), menuId);
	}
	
	@Override
	public List<MenuVO> getMenuList(int rolId){
		return getSqlSession().selectList(genSqlId(SQL_NAMESPACE, "getMenuList"), rolId);
	}
	
	@Override
	public List<MenuVO> notMappingMenu(int rolId){
		return getSqlSession().selectList(genSqlId(SQL_NAMESPACE, "notMappingMenu"), rolId);
	}
	
	@Override
	public List<MenuTreeVO> getMenuTreeList(int menuId){
		return getSqlSession().selectList(genSqlId(SQL_NAMESPACE, "getMenuTreeList"), menuId);
	}
	
	@Override
	public void mappingMenu(UserMenuAuthTO to){
		getSqlSession().insert(genSqlId(SQL_NAMESPACE, "mappingMenu"), to);
	}
	
	@Override
	public void mappingCloseMenu(UserMenuAuthTO to){
		getSqlSession().delete(genSqlId(SQL_NAMESPACE, "mappingCloseMenu"), to);
	}
	
	@Override
	public void insert(MenuTO to){
		getSqlSession().insert(genSqlId(SQL_NAMESPACE, "insert"), to);
	}
	
	@Override
	public void update(MenuTO to){
		getSqlSession().update(genSqlId(SQL_NAMESPACE, "update"), to);
	}
	
	@Override
	public MenuVO getMenuIdOrUpperMenuId(String url){
		return getSqlSession().selectOne(genSqlId(SQL_NAMESPACE, "getMenuIdOrUpperMenuId"), url);
	}
	
	@Override
	public void removeAuthMenu(String menuId){
		getSqlSession().delete(genSqlId(SQL_NAMESPACE, "removeAuthMenu"), Integer.parseInt(menuId));
	}
	
	@Override
	public void removeUpperMenu(String menuId){
		getSqlSession().delete(genSqlId(SQL_NAMESPACE, "removeUpperMenu"), Integer.parseInt(menuId));
	}
	
	@Override
	public void removeMenu(String menuId){
		getSqlSession().delete(genSqlId(SQL_NAMESPACE, "removeMenu"), Integer.parseInt(menuId));
	}
}

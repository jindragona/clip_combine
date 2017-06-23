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
package com.kt.midas.adm.web.menu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.kt.midas.adm.common.ResultEnum;
import com.kt.midas.adm.common.service.BaseServiceImpl;
import com.kt.midas.adm.common.vo.JsonVO;
import com.kt.midas.adm.common.vo.UserInfoVO;
import com.kt.midas.adm.web.menu.dao.MenuDao;
import com.kt.midas.adm.web.menu.vo.MenuTO;
import com.kt.midas.adm.web.menu.vo.MenuTreeVO;
import com.kt.midas.adm.web.menu.vo.MenuVO;
import com.kt.midas.adm.web.userauth.vo.UserMenuAuthTO;
import com.kt.midas.frw.transaction.TxMain;
import com.kt.midas.frw.transaction.TxRequired;

/**
 *
 * @author hwang
 */
@Service
@TxMain
public class MenuServiceImpl extends BaseServiceImpl implements MenuService{
    
    @Autowired
    private MenuDao menuDao;
    
    @Override
    public JsonVO getlistData() {
    	JsonVO jsonVO = new JsonVO();
    	
        List<MenuTreeVO> upperMenuList = menuDao.getUpperMenuTreeList();
        upperMenuList = menuFactorial(upperMenuList);
        
        jsonVO.setData(upperMenuList);
        jsonVO.setCode(ResultEnum.SUCCESS.result);
        
        return jsonVO;
    }
    
    @Override
    @TxRequired
    public JsonVO addRow(MenuTO to) {
    	JsonVO jsonVO = new JsonVO();
    	
    	UserInfoVO info = getUserName();
    	to.setInsUser(info.getUserId());
    	to.setInsUserName(info.getUserName());
    	
    	menuDao.insert(to);
		jsonVO.setCode(ResultEnum.SUCCESS.result);
    	return jsonVO;
    }
    
    @Override
    @TxRequired
    public JsonVO modRow(MenuTO to) {
    	JsonVO jsonVO = new JsonVO();
    	menuDao.update(to);
		jsonVO.setCode(ResultEnum.SUCCESS.result);
    	return jsonVO;
    }
    
    @Override
    public JsonVO getMenuDetail(String id){
    	JsonVO jsonVO = new JsonVO();
    	MenuVO menuVO = menuDao.getMenuDetail(Integer.parseInt(id));
		jsonVO.setData(menuVO);
		jsonVO.setCode(ResultEnum.SUCCESS.result);
    	return jsonVO;
    }
    
    @Override
    public List<MenuVO> getMenuList(String rolId){
    	return menuDao.getMenuList(Integer.parseInt(rolId));
    }
    
    @Override
    public JsonVO notMappingMenu(String rolId){
    	JsonVO jsonVO = new JsonVO();
    	jsonVO.setCode(ResultEnum.SUCCESS.result);
    	jsonVO.setData(menuDao.notMappingMenu(Integer.parseInt(rolId)));
    	return jsonVO;
    }
    
    @Override
    @TxRequired
    public JsonVO mappingMenu(UserMenuAuthTO userMenuAuthTO){
    	JsonVO jsonVO = new JsonVO();
    	
    	String[] tempMenuId = null;
    	
    	if(userMenuAuthTO.getMenuIdArr().indexOf(",") != -1){
    		tempMenuId = userMenuAuthTO.getMenuIdArr().split(",");
    		
    		for(int i=0;i<tempMenuId.length;i++){
    			UserMenuAuthTO to = new UserMenuAuthTO();
    			to.setMenuId(Integer.parseInt(tempMenuId[i]));
    			to.setRolId(userMenuAuthTO.getRolId());
    			menuDao.mappingMenu(to);
    		}
    	}else{
    		userMenuAuthTO.setMenuId(Integer.parseInt(userMenuAuthTO.getMenuIdArr()));
    		menuDao.mappingMenu(userMenuAuthTO);
    	}
    	
    	jsonVO.setCode(ResultEnum.SUCCESS.result);
    	return jsonVO;
    }
    
    @Override
    @TxRequired
    public JsonVO mappingCloseMenu(UserMenuAuthTO userMenuAuthTO){
    	JsonVO jsonVO = new JsonVO();
    	
    	String[] tempMenuId = null;
    	
    	if(userMenuAuthTO.getMenuIdArr().indexOf(",") != -1){
    		tempMenuId = userMenuAuthTO.getMenuIdArr().split(",");
    		
    		for(int i=0;i<tempMenuId.length;i++){
    			UserMenuAuthTO to = new UserMenuAuthTO();
    			to.setMenuId(Integer.parseInt(tempMenuId[i]));
    			to.setRolId(userMenuAuthTO.getRolId());
    			menuDao.mappingCloseMenu(to);
    		}
    		
    	}else{
    		userMenuAuthTO.setMenuId(Integer.parseInt(userMenuAuthTO.getMenuIdArr()));
    		menuDao.mappingCloseMenu(userMenuAuthTO);
    	}
    	
    	jsonVO.setCode(ResultEnum.SUCCESS.result);
    	return jsonVO;
    }
    
    @Cacheable("menuAuthCache")
    @Override
    public MenuVO getMenuIdOrUpperMenuId(String url){
    	return menuDao.getMenuIdOrUpperMenuId(url);
    }
    
    @Override
    @TxRequired
    public JsonVO removeMenun(String menuId){
    	JsonVO jsonVO = new JsonVO();
    	
    	// 권한 테이블 삭제
    	menuDao.removeAuthMenu(menuId);
    	
    	// 상위 메뉴 삭제
    	menuDao.removeUpperMenu(menuId);
    	
    	// 선택 된 메뉴 삭제
    	menuDao.removeMenu(menuId);
    	
    	jsonVO.setCode(ResultEnum.SUCCESS.result);
    	return jsonVO;
    }
    
    public List<MenuTreeVO> menuFactorial(List<MenuTreeVO> list){
    	for(MenuTreeVO vo : list){
    		List<MenuTreeVO> childList = menuDao.getMenuTreeList(Integer.parseInt(vo.getMenuId()));
    		childList = menuFactorial(childList);
        	vo.setChildren(childList);
        }
    	
    	return list;
    }
}

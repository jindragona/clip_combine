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
package com.kt.midas.adm.common.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.kt.midas.adm.common.Constants;
import com.kt.midas.adm.common.excel.ExcelView;
import com.kt.midas.adm.web.login.service.LoginSecurityService;
import com.kt.midas.adm.web.login.vo.LoginDetailVO;
import com.kt.midas.adm.web.menu.service.MenuService;
import com.kt.midas.adm.web.menu.vo.MenuTreeVO;
import com.kt.midas.adm.web.menu.vo.MenuVO;

public class UrlAuthInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger LOG = LoggerFactory.getLogger(ExcelView.class);
	
	@Autowired
    private LoginSecurityService loginSecurityService;
	
	@Autowired
    private MenuService menuService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
		boolean masterFlag = true;
		
		try{
			String uri = request.getRequestURI();
			String[] temp = uri.split("/");
			String pageNav = "";
			
			MenuVO menuVO = menuService.getMenuIdOrUpperMenuId(uri);
			
			if(menuVO != null){
				loginSecurityService.getAuthUser().setUpperMenuId(menuVO.getUpperMenuId());
				loginSecurityService.getAuthUser().setMenuId(menuVO.getMenuId());			
			}
			
			loginSecurityService.getAuthUser().setPageUri(temp[2]);
			
			switch (uri) {
				case "/office/main":
					pageNav = Constants.MAIN;
					break;
				case "/office/pwdMdf":
					pageNav = Constants.PWDMOD;
					break;
				case "/office/user/list":
					pageNav = Constants.USER_LIST;
					break;
				case "/office/user/addRow":
					pageNav = Constants.USER_ADD;
					break;
				case "/office/user/modRow":
					pageNav = Constants.USER_MOD;
					break;
				case "/office/auth/list":
					pageNav = Constants.AUTH_LIST;
					break;
				case "/office/auth/addRow":
					pageNav = Constants.AUTH_ADD;
					break;
				case "/office/auth/modRow":
					pageNav = Constants.AUTH_MOD;
					break;
				case "/office/menu/list":
					pageNav = Constants.MENU_LIST;
					break;
				case "/office/mclnkHist/list":
					pageNav = Constants.MC_HIST_LIST;
					break;
				case "/office/batchhist/list":
					pageNav = Constants.BATCH_HIST_LIST;
					break;
				case "/office/user/userIpAuth":
					pageNav = Constants.CONN_LIST;
					break;
				case "/office/code/list":
					pageNav = Constants.CODE_LIST;
					break;
				case "/office/code/addRow":
					pageNav = Constants.CODE_ADD;
					break;
				case "/office/code/modRow":
					pageNav = Constants.CODE_MOD;
					break;
				case "/office/grpcode/list":
					pageNav = Constants.CGRP_LIST;
					break;
				case "/office/grpcode/addRow":
					pageNav = Constants.CGRP_ADD;
					break;
				case "/office/grpcode/modRow":
					pageNav = Constants.CGRP_MOD;
					break;
				case "/office/noti/list":
					pageNav = Constants.NOTI_LIST;
					break;
				case "/office/noti/addRow":
					pageNav = Constants.NOTI_ADD;
					break;
				case "/office/noti/modRow":
					pageNav = Constants.NOTI_MOD;
					break;
				case "/office/brancard/list":
					pageNav = Constants.BRAN_CARD_LIST;
					break;
				case "/office/brancard/modRow":
					pageNav = Constants.BRAN_CARD_MOD;
					break;
				case "/office/moblft/list":
					pageNav = Constants.LFT_LIST;
					break;
				case "/office/moblft/complate":
					pageNav = Constants.LFT_COMPLATE;
					break;
				case "/office/moblft/baseInfo":
					pageNav = Constants.LFT_BASE;
					break;
				case "/office/moblft/lftImg":
					pageNav = Constants.LFT_IMG;
					break;
				case "/office/moblft/targetMethod":
					pageNav = Constants.LFT_TARGET_METHOD;
					break;
				case "/office/moblft/sendWay":
					pageNav = Constants.LFT_SEND_WAY;
					break;
				case "/office/mobcard/list":
					pageNav = Constants.MOBILE_CARD_LIST;
					break;
				case "/office/mobcard/add":
					pageNav = Constants.MOBILE_CARD_ADD;
					break;
				case "/office/mobcard/edit":
					pageNav = Constants.MOBILE_CARD_MOD;
					break;
				case "/office/mobcard/trm/list":
					pageNav = Constants.TERM_LIST;
					break;
				case "/office/mobcard/trm/add":
					pageNav = Constants.TERM_ADD;
					break;
				case "/office/mobcard/trm/edit":
					pageNav = Constants.TERM_MOD;
					break;
				case "/office/mobcard/trm/item/list":
					pageNav = Constants.TERM_ITEM_LIST;
					break;
				case "/office/mobcard/trm/item/form":
					pageNav = Constants.TERM_ITEM_FORM;
					break;
				case "/office/mobcard/trm/item/itemVersionAdd":
					pageNav = Constants.TERM_ITEM_VERSION;
					break;
				case "/office/bantgt/tgtBannerList":
					pageNav = Constants.BANNER_LIST;
					break;	
				case "/office/bantgt/formBannTarget":
					pageNav = Constants.BANNER_TARGET;
					break;
				case "/office/arch/lft/lftArchList":
					pageNav = Constants.ARCH_LFT_LIST;
					break;
				case "/office/arch/lft/lftArchDetail":
					pageNav = Constants.ARCH_LFT_DETAL;
					break;
				case "/office/arch/bann/bannArchList":
					pageNav = Constants.ARCH_BANNER_LIST;
					break;
				case "/office/arch/bann/bannArchDetail":
					pageNav = Constants.ARCH_BANNER_DETAL;
					break;
				case "/office/arch/mcard/mcardArchList":
					pageNav = Constants.ARCH_CARD_LIST;
					break;
				case "/office/arch/mcard/mcardArchDetail":
					pageNav = Constants.ARCH_CARD_DETAL;
					break;
				case "/office/cal/lft/lftCalList":
					pageNav = Constants.CAL_LFT_LIST;
					break;
				case "/office/cal/lft/lftCalDetail":
					pageNav = Constants.CAL_LFT_DETAL;
					break;
				case "/office/cal/bann/bannCalList":
					pageNav = Constants.CAL_BANNER_LIST;
					break;
				case "/office/cal/bann/bannCalDetail":
					pageNav = Constants.CAL_BANNER_DETAL;
					break;
				case "/office/cal/mcard/mcardCalList":
					pageNav = Constants.CAL_CARD_LIST;
					break;
				case "/office/cal/mcard/mcardCalDetail":
					pageNav = Constants.CAL_CARD_DETAL;
					break;
					
				default:
					break;
			}
			
			loginSecurityService.getAuthUser().setPageNav(pageNav);
			
			LoginDetailVO vo = loginSecurityService.getAuthUser();
			List<MenuTreeVO> treeList = vo.getAuthMenuList();
			
			if(uri.equals("/office/main") || uri.equals("/office/pwdMdf")){
				return true;
			}
			
			String secondepthUri = temp[1] + "/" + temp[2];
			
			masterFlag = checkURI(treeList, secondepthUri);
			
			if(!masterFlag && !uri.equals("/office/user/modRow")){
				response.sendRedirect(request.getContextPath()+"/error/401.jsp");					
			}
			
			masterFlag = true;
		}catch (Exception e) {
			if(LOG.isErrorEnabled()){
    			LOG.error("preHandle error", e); 
			}
		}
		
		return masterFlag;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView){
		try{
			super.postHandle(request, response, handler, modelAndView);
		}catch (Exception e) {
			if(LOG.isErrorEnabled()){
				LOG.error("postHandle error", e); 
			}
		}
	}
	 
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex){
		try{
			super.afterCompletion(request, response, handler, ex);
		}catch (Exception e) {
			if(LOG.isErrorEnabled()){
				LOG.error("afterCompletion error", e); 
			}
		}
	} 
	
	public boolean checkURI(List<MenuTreeVO> treeList, String uri){
		boolean flag = false;
		
		for(MenuTreeVO treeVO : treeList){
			List<MenuTreeVO> childTreeList = treeVO.getChildren();
			
			if(childTreeList != null && childTreeList.size() > 0){
				flag = checkURI(childTreeList, uri);
				
				if(flag){
					break;
				}
			}
			
			if(treeVO.getMenuUrl().indexOf(uri) != -1){
				flag = true;
				break;
			}
		}
		
		return flag;
	}
}

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
package com.kt.midas.adm.web.user.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kt.midas.adm.common.util.Pager;
import com.kt.midas.adm.common.vo.JsonVO;
import com.kt.midas.adm.web.code.service.CodeService;
import com.kt.midas.adm.web.code.vo.CodeSearchTO;
import com.kt.midas.adm.web.code.vo.CommonCodeVO;
import com.kt.midas.adm.web.user.service.UserService;
import com.kt.midas.adm.web.user.vo.AuthIpVO;
import com.kt.midas.adm.web.user.vo.UserAuthIpTO;
import com.kt.midas.adm.web.user.vo.UserSearchTO;
import com.kt.midas.adm.web.user.vo.UserTO;
import com.kt.midas.adm.web.user.vo.UserVO;

@Controller
@RequestMapping(value = "/office/user/*")
public class UserController {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
	
    @Autowired
    private UserService userService;
    
    @Autowired
    private CodeService codeService;
    
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public String list(@ModelAttribute("userSearchTO") UserSearchTO userSearchTO, Model model) {
    	Pager pager = new Pager();
    	
    	try{
    		if(userSearchTO != null && userSearchTO.getSearchType() != null && userSearchTO.getSearchType().equals("NAME")){
    			String encWord = userSearchTO.getSearchWord();
        		userSearchTO.setEncWord(encWord);
    		}else if(userSearchTO != null && userSearchTO.getSearchWord() != null){
    			userSearchTO.setEncWord(userSearchTO.getSearchWord());
    		}
    	}catch (Exception e) {
    		if(LOG.isErrorEnabled()){
    			LOG.error("UserService list error", e);
			}
		}
    	
    	int totalCnt = userService.getlistDataCnt(userSearchTO);
    	userSearchTO.setTotalPageByTotalRecords(totalCnt);
    	List<UserVO> notiList = userService.getlistData(userSearchTO);
    	
    	pager.setAllRowCount(totalCnt);
    	pager.setCurrentPage(userSearchTO.getPageNo());
    	
    	model.addAttribute("paginationInfo", pager);
    	model.addAttribute("totalCnt", totalCnt);
    	model.addAttribute("list", notiList);
    	
    	CodeSearchTO to = new CodeSearchTO();
    	to.setCodeGrpId("SYS006");
    	List<CommonCodeVO> codeList = codeService.commonCodeList(to);
    	model.addAttribute("codeList", codeList);
    	
        return "user/list";
    }
    
    @RequestMapping(value = "addRow", method = RequestMethod.POST)
    public String addRow(Model model)  {
    	CodeSearchTO to = new CodeSearchTO();
    	to.setCodeGrpId("SYS006");
    	List<CommonCodeVO> codeList = codeService.commonCodeList(to);
    	model.addAttribute("codeList", codeList);
    	return "user/addForm";
    }
    
    @RequestMapping(value = "modRow", method = RequestMethod.POST)
    public String modRow(
    		@RequestParam(value = "userSeq", required = true) String userSeq, 
    		@RequestParam(value = "mode", required = false) String mode, 
    		Model model)  {
    	CodeSearchTO to = new CodeSearchTO();
    	to.setCodeGrpId("SYS006");
    	List<CommonCodeVO> codeList = codeService.commonCodeList(to);
    	
    	model.addAttribute("codeList", codeList);
    	
    	UserVO userVO = userService.getUserDetail(userSeq);
    	model.addAttribute("detail", userVO);
    	
    	if(mode == null || mode.equals("")){
    		return "user/modAdminForm";
    	}else{
    		return "user/modForm";
    	}
    }
    
    @RequestMapping(value = "userIpAuth", method = RequestMethod.POST)
    public String userIpAuth(@ModelAttribute("userTO") UserTO userTO, Model model) {
    	List<AuthIpVO> ipList = userService.getAuthIpList(userTO);
    	model.addAttribute("ipList", ipList);
    	model.addAttribute("userSeq", userTO.getUserSeq());
    	return "user/ipAuth";
    }
    
    @RequestMapping(value = "proc", method = RequestMethod.POST)
    public @ResponseBody JsonVO proc(@ModelAttribute("userTO") UserTO userTO, BindingResult bindingResult) {
    	String mode = userTO.getMode();
    	
    	if(mode.equals("addRow")){
    		return userService.addRow(userTO);
    	}else{
    		return userService.modRow(userTO);
    	}
    }
    
    @RequestMapping(value = "admProc", method = RequestMethod.POST)
    public @ResponseBody JsonVO admProc(@ModelAttribute("userTO") UserTO userTO, BindingResult bindingResult) {
    	return userService.modAdmRow(userTO);
    }
    
    @RequestMapping(value = "checkLogingId", method = RequestMethod.POST)
    public @ResponseBody JsonVO checkLogingId(@RequestParam(value = "userId", required = false) String userId) {
        return userService.checkLogingId(userId);
    }
    
    @RequestMapping(value = "pwdInit", method = RequestMethod.POST)
    public @ResponseBody JsonVO pwdInit(@RequestParam(value = "userSeq", required = true) String userSeq) {
        return userService.pwdInit(userSeq);
    }
    
    @RequestMapping(value = "pwdErrorCntInit", method = RequestMethod.POST)
    public @ResponseBody JsonVO pwdErrorCntInit(@RequestParam(value = "userSeq", required = true) String userSeq) {
        return userService.pwdErrorCntInit(userSeq);
    }
    
    @RequestMapping(value = "stopUser", method = RequestMethod.POST)
    public @ResponseBody JsonVO stopUser(@RequestParam(value = "userSeq", required = true) String userSeq) {
        return userService.stopUser(userSeq);
    }
    
    @RequestMapping(value = "useUser", method = RequestMethod.POST)
    public @ResponseBody JsonVO useUser(@RequestParam(value = "userSeq", required = true) String userSeq) {
        return userService.useUser(userSeq);
    }
    
    @RequestMapping(value = "userIpAuthAdd", method = RequestMethod.POST)
    public @ResponseBody JsonVO userIpAuthAdd(@ModelAttribute("userAuthIpTO") UserAuthIpTO userAuthIpTO) {
        return userService.userIpAuthAdd(userAuthIpTO);
    }
    
    @RequestMapping(value = "userIpAuthRemove", method = RequestMethod.POST)
    public @ResponseBody JsonVO userIpAuthRemove(@ModelAttribute("userAuthIpTO") UserAuthIpTO userAuthIpTO) {
        return userService.userIpAuthRemove(userAuthIpTO);
    }
    
}

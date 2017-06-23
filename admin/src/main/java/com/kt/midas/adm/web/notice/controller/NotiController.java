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
package com.kt.midas.adm.web.notice.controller;

import java.util.List;

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
import com.kt.midas.adm.web.notice.service.NotiService;
import com.kt.midas.adm.web.notice.vo.NotiSearchTO;
import com.kt.midas.adm.web.notice.vo.NotiTO;
import com.kt.midas.adm.web.notice.vo.NotiVO;

@Controller
@RequestMapping(value = "/office/noti/*")
public class NotiController {
    
    @Autowired
    private NotiService notiService;
    
    @Autowired
    private CodeService codeService;
    
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public String list(@ModelAttribute("notiSearchTO") NotiSearchTO notiSearchTO, Model model)  {
    	Pager pager = new Pager();
    	int totalCnt = notiService.getlistDataCnt(notiSearchTO);
    	List<NotiVO> notiList = notiService.getlistData(notiSearchTO);
    	
    	notiSearchTO.setTotalPageByTotalRecords(totalCnt);
    	
    	pager.setAllRowCount(totalCnt);
    	pager.setCurrentPage(notiSearchTO.getPageNo());
    	
    	CodeSearchTO to = new CodeSearchTO();
    	to.setCodeGrpId("SYS006");
    	List<CommonCodeVO> codeList = codeService.commonCodeList(to);
    	
    	model.addAttribute("codeList", codeList);
    	model.addAttribute("paginationInfo", pager);
    	model.addAttribute("totalCnt", totalCnt);
    	model.addAttribute("list", notiList);
        return "noti/list";
    }
    
    @RequestMapping(value = "addRow", method = RequestMethod.POST)
    public String addRow(Model model)  {
    	CodeSearchTO to = new CodeSearchTO();
    	to.setCodeGrpId("SYS006");
    	List<CommonCodeVO> codeList = codeService.commonCodeList(to);
    	model.addAttribute("codeList", codeList);
    	return "noti/addForm";
    }
    
    @RequestMapping(value = "modRow", method = RequestMethod.POST)
    public String modRow(@RequestParam(value = "notiId", required = true) String notiId, Model model)  {
    	model.addAttribute("detail", notiService.getNotiDetail(notiId));
    	
    	CodeSearchTO to = new CodeSearchTO();
    	to.setCodeGrpId("SYS006");
    	List<CommonCodeVO> codeList = codeService.commonCodeList(to);
    	model.addAttribute("codeList", codeList);
    	
    	return "noti/modForm";
    }
    
    @RequestMapping(value = "proc", method = RequestMethod.POST)
    public @ResponseBody JsonVO proc(@ModelAttribute("notiTO") NotiTO notiTO, BindingResult bindingResult) {
    	String mode = notiTO.getMode();
    	
    	if(mode.equals("addRow")){
    		return notiService.addRow(notiTO);
    	}else{
    		return notiService.modRow(notiTO);
    	}
    }
}

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
package com.kt.midas.adm.web.code.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kt.midas.adm.common.util.Pager;
import com.kt.midas.adm.common.vo.JsonVO;
import com.kt.midas.adm.web.code.service.CodeService;
import com.kt.midas.adm.web.code.vo.CodeSearchTO;
import com.kt.midas.adm.web.code.vo.CodeTO;
import com.kt.midas.adm.web.code.vo.CodeVO;
import com.kt.midas.adm.web.grpcode.service.GrpCodeService;
import com.kt.midas.adm.web.grpcode.vo.GrpCodeVO;

@Controller
@RequestMapping(value = "/office/code/*")
public class CodeController {
    
    @Autowired
    private CodeService codeService;
    
    @Autowired
    private GrpCodeService grpCodeService;
    
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public String list(@ModelAttribute("codeSearchTO") CodeSearchTO codeSearchTO, Model model)  {
    	Pager pager = new Pager();
    	int totalCnt = codeService.getlistDataCnt(codeSearchTO);
    	List<CodeVO> list = codeService.getlistData(codeSearchTO);
    	
    	codeSearchTO.setTotalPageByTotalRecords(totalCnt);
    	
    	pager.setAllRowCount(totalCnt);
    	pager.setCurrentPage(codeSearchTO.getPageNo());
    	
    	model.addAttribute("paginationInfo", pager);
    	model.addAttribute("totalCnt", totalCnt);
    	model.addAttribute("list", list);
    	
    	List<GrpCodeVO> grpList = grpCodeService.getGrpCodeList();
    	model.addAttribute("grpList", grpList);
    	
        return "code/list";
    }
    
    @RequestMapping(value = "addRow", method = RequestMethod.POST)
    public String addRow(Model model)  {
    	List<GrpCodeVO> grpList = grpCodeService.getGrpCodeList();
    	model.addAttribute("grpList", grpList);
    	return "code/addForm";
    }
    
    @RequestMapping(value = "modRow", method = RequestMethod.POST)
    public String modRow(@RequestParam(value = "codeId", required = true) String codeId, Model model)  {
    	List<GrpCodeVO> grpList = grpCodeService.getGrpCodeList();
    	model.addAttribute("detail", codeService.getCodeDetail(codeId));
    	model.addAttribute("grpList", grpList);
    	return "code/modForm";
    }
    
    @RequestMapping(value = "proc", method = RequestMethod.POST)
    public @ResponseBody JsonVO proc(@ModelAttribute("codeTO") CodeTO codeTO) {
    	String mode = codeTO.getMode();
        
        if(mode.equals("addRow")){// test
    		return codeService.addRow(codeTO);
    	}else{
    		return codeService.modRow(codeTO);
    	}
    }
    
    @RequestMapping(value = "checkCodeId", method = RequestMethod.POST)
    public @ResponseBody JsonVO checkCodeId(@RequestParam(value = "codeId", required = false) String codeId) {
        return codeService.checkCodeId(codeId);
    }
}

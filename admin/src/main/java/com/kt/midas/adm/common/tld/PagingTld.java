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
package com.kt.midas.adm.common.tld;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.kt.midas.adm.common.util.Pager;

public class PagingTld extends TagSupport {
	
	private static final long serialVersionUID = 1L;
	private Pager paginationInfo;
	private String jsFunction;
	private String jsRowFunction;
	
	public int doEndTag() throws JspException {
		try {
			JspWriter out = this.pageContext.getOut();
			String contents = renderPagination(this.paginationInfo, this.jsFunction, this.jsRowFunction);

			out.println(contents);

			return 6;
		} catch (IOException e) {
			throw new JspException();
		}
	}

	public void setPaginationInfo(Pager paginationInfo) {
		this.paginationInfo = paginationInfo;
	}
	
	public void setJsFunction(String jsFunction) {
		this.jsFunction = jsFunction;
	}
	
	public void setJsRowFunction(String jsRowFunction) {
		this.jsRowFunction = jsRowFunction;
	}
	
	/* logic of paging */
	public String renderPagination(Pager paginationInfo, String jsFunction, String jsRowFunction) {
		paginationInfo.setCallFun(jsFunction);
		return paginationInfo.toString();
	}
}

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
package com.kt.midas.adm.common.vo;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author seulee
 */
public class SearchTO{
	
    private Integer totalPage;
    private Integer pageNo;
    private Integer rowPerPage;
    private Integer startRowNo;
    private Integer endRowNo;
    private Integer totalRecordsCount;

    public SearchTO() {
        this.pageNo = 1;
        this.rowPerPage = 10;
        calcPagingRow();
    }

    public SearchTO(HttpServletRequest request) {
    	if(request.getParameter("page") == null || request.getParameter("page").equals("")){
    		this.pageNo = 1;
    	}else{
    		this.pageNo = Integer.parseInt(request.getParameter("page"));
    	}
    	
    	if(request.getParameter("rows") == null || request.getParameter("rows").equals("")){
    		this.rowPerPage = 10;
    	}else{
    		this.rowPerPage = Integer.parseInt(request.getParameter("rows"));
    	}
    }
    
    /**
     * @param pageNo
     * @param rowPerPage
     * @param startRowNo
     * @param endRowNo
     */
    public SearchTO(HttpServletRequest request, Integer totalRecords) {
    	if(request.getParameter("page") == null || request.getParameter("page").equals("")){
    		this.pageNo = 1;
    	}else{
    		this.pageNo = Integer.parseInt(request.getParameter("page"));
    	}
    	
    	if(request.getParameter("rows") == null || request.getParameter("rows").equals("")){
    		this.rowPerPage = 10;
    	}else{
    		this.rowPerPage = Integer.parseInt(request.getParameter("rows"));
    	}
        
        this.totalPage = (totalRecords % rowPerPage == 0?(totalRecords/rowPerPage):(totalRecords/rowPerPage+1));
        calcPagingRow();
    }

    public void setTotalPageByTotalRecords(int totalRecords) {
        this.totalPage = totalRecords % rowPerPage == 0 ? (totalRecords/rowPerPage) : (totalRecords/rowPerPage)+1 ;
        calcPagingRow();
    }
    
    public void calcPagingRow() {
        this.startRowNo = ((this.pageNo - 1) * this.rowPerPage) + 1;
        this.endRowNo = this.startRowNo + rowPerPage -1;
    }

    /**
    * @return the totalPage
    */
    public Integer getTotalPage() {
        return totalPage;
    }

    /**
     * @param totalPage the totalPage to set
     */
    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
        calcPagingRow();
    }

    public Integer getRowPerPage() {
        return rowPerPage;
    }

    public void setRowPerPage(Integer rowPerPage) {
        this.rowPerPage = rowPerPage;
        calcPagingRow();
    }

    public Integer getStartRowNo() {
        return startRowNo;
    }

    public void setStartRowNo(Integer startRowNo) {
        this.startRowNo = startRowNo;
    }

    public Integer getEndRowNo() {
        return endRowNo;
    }

    public void setEndRowNo(Integer endRowNo) {
        this.endRowNo = endRowNo;
    }

    /**
     * @return the totalRecordsCount
     */
    public Integer getTotalRecordsCount() {
        return totalRecordsCount;
    }

    /**
     * @param totalRecordsCount the totalRecordsCount to set
     */
    public void setTotalRecordsCount(Integer totalRecordsCount) {
        this.totalRecordsCount = totalRecordsCount;
    }
}

<!-- 
   MIDAS version 1.0
 
   Copyright ⓒ 2017 kt corp. All rights reserved.
 
   This is a proprietary software of kt corp, and you may not use this file except in
   compliance with license agreement with kt corp. Any redistribution or use of this
   software, with or without modification shall be strictly prohibited without prior written
   approval of kt corp, and the copyright notice above does not evidence any actual or
   intended publication of such software.
-->
<%@page import="java.util.Date"%>
<%@page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="midas" uri="/WEB-INF/tld/midas.tld" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>권한 관리</title>
<script type="text/javascript">
	$(function(){
		$('input').keypress(function(e) {
	        if (e.keyCode == 13){
	        	fn_search();
	        }        
	    });
	});
	
	function fn_search(){
		var frm = document.form;
		frm.pageNo.value = "1";
    	frm.action = "./list";
    	frm.submit();
	}
	
	function fn_addRow(){
		var frm = document.form;
    	frm.action = "./addRow";
    	frm.submit();
	}
	
	function fn_detail(id){
		var frm = document.form;
		frm.rolId.value = id;
		frm.action = "./modRow";
    	frm.submit();
	}
	
	function fn_search_init_old(){
		$("#form").each(function() {  
            this.reset();  
         });
		
		$('select').selectric('refresh');
	}
	
	function fn_search_init(){
        
		var frm = document.form; 
        
		frm.stime.value = "";
		frm.etime.value = "";
        frm.searchType.options[0].selected = true;
		frm.searchWord.value = "";
		
		$('select').selectric('refresh');
	}
	
	function onListPage(pageNum) {
        document.form.pageNo.value = pageNum;
        document.form.submit();
    }
</script>

</head>
<body>
	<!--컨텐츠 영역-->
	<div class="content ml_40">
		<div class="cont_title">
			<h2 class="ml_10">권한 관리</h2>
		</div>

		<!--조회-->
		<div class="cont_inWrap mt_20">
			<h3 class="ml_10">조회조건</h3>
			<form id="form" name="form" method="post">
			<input type="hidden" id="rolId" name="rolId">
			<input type="hidden" name="pageNo" id="pageNo" value="<c:out value="${userAuthSearchTO.pageNo}" />">
				<div class="inputWrap mt_25">
					<table cellpadding="0" cellspacing="0">
					<colgroup>
						<col style="width:110px;">
						<col>							
					</colgroup>

					<tr>
						<td><label  class="input_label">기간</label></td>
						<td>
							<div class="fl mr_5">
								<input type="text" name="stime" id="sdate" style="width:110px;" value="<c:out value="${userAuthSearchTO.stime}" />" readonly="readonly">
							</div>
							
							<span class="fl txt_wave"> ~ </span>
							
							<div class="fl mr_5">
								<input type="text" name="etime" id="edate" style="width:110px;" value="<c:out value="${userAuthSearchTO.etime}" />" readonly="readonly">
							</div>

						</td>
					</tr>

					<tr>
						<td><label  class="input_label">구분</label></td>
						<td>
							<div class="fl bg_input mr_10" style="width:140px;">
								<select id="searchType" name="searchType">
								<option value="KEY" <c:if test="${userAuthSearchTO.searchType eq 'KEY'}">selected="selected"</c:if>>역할명</option>
								<option value="NAME" <c:if test="${userAuthSearchTO.searchType eq 'NAME'}">selected="selected"</c:if>>등록자명</option>
								</select>
							</div>
							<input type="text" id="searchWord" name="searchWord" style="width:200px;" value="<c:out value="${userAuthSearchTO.searchWord}" />">
						</td>
					</tr>
					</table>
				</div>
			
				<div class="inputBtnWrap mt_30">						
					<input type="button" value="조회" class="btn01_red mr_10" onclick="fn_search(); return false;">
					<input type="button" value="초기화" class="btn01_white" onclick="fn_search_init(); return false;">
				</div>
			</form>
		<!--리스트-->
		<div class="clearfix mt_20">
		
			<p class="fl list_head_txt ml_10 mt_30">역할 목록 (<fmt:formatNumber value="${totalCnt }" pattern="#,###" /> 건)</p>
			<div class="fr">
				<button class="btn01_red mr_10" onclick="fn_addRow(); return false;">등록</button>
			</div>
		</div>

		<table class="tbl_list01 mt_25">
		<colgroup>
			<col style="width:90px;">
			<col style="width:370px;">				
			<col style="width:170px;">
			<col style="width:270px;">
			<col>
		</colgroup>

		<thead>
		<tr class="thead_txt">
			<th>권한 번호</th>
			<th>역할명</th>
			<th>사용 여부</th>
			<th>등록일시</th>
			<th>등록자</th>
		</tr>
		</thead>
		
		<tbody>		
			<c:choose>
				<c:when test="${fn:length(list) > 0}">
					<c:forEach items="${list}" var="result" varStatus="status">
		<tr>
			<td><c:out value="${result.rolId}" /></td>
			<td><a href="#" class="a_hover" onclick="fn_detail('<c:out value="${result.rolId}" />'); return false;"><c:out value="${result.rolName}" /></a></td>
			<td><c:out value="${result.ueYnName}"/></td>
			<td>
       			<fmt:parseDate value="${result.insDt}" var="dateFmt" pattern="yyyy-MM-dd HH:mm:ss"/>
       			<fmt:formatDate value="${dateFmt}" pattern="yyyy-MM-dd HH:mm:ss"/>
       		</td>
       		<td><c:out value="${result.insUserNameTemp}" /></td>
		</tr>	
					</c:forEach>
				</c:when>
				<c:otherwise>
		<tr>
			<td colspan="5">데이터가 없습니다.</td>
		</tr>				
				</c:otherwise>
			</c:choose>
		</tbody>
		</table>
		
		<div class="page01 mt_25 tac" >
			<midas:paging paginationInfo="${paginationInfo}" jsFunction="onListPage" />
		</div>
			
		</div>
	</div>
</body>
</html>

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
<title>공지 관리</title>
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
		frm.notiId.value = id;
		frm.action = "./modRow";
    	frm.submit();
	}
	
	function fn_allCheck(){
		if($("input:checkbox[id='allCheck']").is(":checked")){
			$(".checkClass").prop('checked', true);
		}else{
			$(".checkClass").prop('checked', false);
		}
	}
	
	function onListPage(pageNum) {
        document.form.pageNo.value = pageNum;
        document.form.submit();
    }
	
	function fn_search_init(){
		var frm = document.form;
		frm.notiName.value = "";
		frm.usrType.value = "";
		frm.searchType.value = "startDate";
		frm.sdate.value = "";
		frm.edate.value = "";
		
		$('select').selectric('refresh');
	}
</script>

</head>
<body>
	<!--컨텐츠 영역-->
	<div class="content ml_40">
		<div class="cont_title">
			<h2 class="ml_10">공지 관리</h2>
		</div>

		<!--조회-->
		<form id="form" name="form" method="post">
		<input type="hidden" name="notiId" id="notiId"/>
		<input type="hidden" name="pageNo" id="pageNo" value="<c:out value="${notiSearchTO.pageNo}" />">
		<div class="cont_inWrap mt_20">
			<h3 class="ml_10">조회조건</h3>
			<div class="inputWrap mt_25">
				<table cellpadding="0" cellspacing="0">
				<colgroup>
					<col style="width:110px;">
					<col style="width:350px;">							
					<col style="width:110px;">
					<col>							
				</colgroup>

				<tr>
					<td><label class="input_label">공지명</label></td>
					<td><input type="text" placeholder="공지명" id="notiName" name="notiName" value="<c:out value="${notiSearchTO.notiName}" />" style="width:300px;"></td>
					<td><label class="input_label">사용자 유형</label></td>
					<td>
						<div class="fl bg_input mr_10" style="width:300px;">
							<select id="usrType" name="usrType">
								<option value="">전체</option>
								<c:forEach var="code" items="${codeList}" varStatus="status">
								<option value="<c:out value="${code.codeId}" />" <c:if test="${notiSearchTO.usrType eq code.codeId}"> selected</c:if>><c:out value="${code.codeName}" /></option>
								</c:forEach>
							</select>
						</div>
					</td>
				</tr>
				<tr>
					<td><label  class="input_label">기간</label></td>
					<td colspan="3">
						<div class="fl bg_input mr_10" style="width:140px;">
							<select id="searchType" name="searchType">
								<option value="startDate" <c:if test="${notiSearchTO.searchType eq 'startDate'}">selected="selected"</c:if>>시작일자</option>
								<option value="endDate" <c:if test="${notiSearchTO.searchType eq 'endDate'}">selected="selected"</c:if>>종료일자</option>
							</select>
						</div>

						<div class="fl mr_5">
							<input type="text" id="sdate" style="width:110px;" name="sdate" value="<c:out value="${notiSearchTO.sdate}" />">
						</div>
						
						<span class="fl txt_wave"> ~ </span>
						
						<div class="fl mr_5">
							<input type="text" id="edate" style="width:110px;" name="edate" value="<c:out value="${notiSearchTO.edate}" />">
						</div>

					</td>
				</tr>
				</table>
			</div>

			<div class="inputBtnWrap mt_30">
				<input type="button" value="조회" class="btn01_red mr_10" onclick="fn_search(); return false;">
				<input type="button" value="초기화" class="btn01_white" onclick="fn_search_init(); return false;">
			</div>
		</div>
		</form>
		
		<!--리스트-->
		<div class="clearfix mt_20">
			<p class="fl list_head_txt ml_10 mt_30">공지 목록 (<fmt:formatNumber value="${totalCnt }" pattern="#,###" /> 건)</p>
			
			<div class="fr">
				<button class="btn01_red mr_10" onclick="fn_addRow(); return false;">등록</button>
			</div>

		</div>

		<table class="tbl_list01 mt_25">
		<colgroup>
			<col style="width:90px;">
			<col style="width:200px;">
			<col>
			<col style="width:150px;">
			<col style="width:170px;">
			<col style="width:110px;">
		</colgroup>

		<thead>
		<tr class="thead_txt">
			<th>공지 ID</th>
			<th>공지 명</th>
			<th>공지 기간</th>
			<th>사용자 유형</th>
			<th>등록 일시</th>
			<th>등록자</th>
		</tr>
		</thead>
		
		<tbody>
			<c:choose>
				<c:when test="${fn:length(list) > 0}">
					<c:forEach items="${list}" var="result" varStatus="status">
			<tr>
				<td><c:out value="${result.notiId}" /></td>
				<td><a href="#" class="a_hover" onclick="fn_detail('<c:out value="${result.notiId}" />'); return false;"><c:out value="${result.notiName}" /></a></td>
				<td>
					<fmt:parseDate value="${result.stime}" var="stime" pattern="yyyyMMdd"/>
					<fmt:parseDate value="${result.etime}" var="etime" pattern="yyyyMMdd"/>
					<fmt:formatDate value="${stime}" pattern="yyyy-MM-dd"/>
					~
					<fmt:formatDate value="${etime}" pattern="yyyy-MM-dd"/>
				</td>
				<td><c:out value="${result.usrTypeName}" /></td>
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
				<td colspan="6">데이터가 없습니다.</td>
			</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
		</table>
		<div class="page01 mt_25 tac" >
			<midas:paging paginationInfo="${paginationInfo}" jsFunction="onListPage" />
		</div>
	</div>
</body>
</html>

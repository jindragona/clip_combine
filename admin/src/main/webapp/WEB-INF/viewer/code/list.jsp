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
<title>코드 관리</title>
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
		frm.codeId.value = id;
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
		frm.codeName.value = "";
		frm.codeGrpId.value = "";
		
		$('select').selectric('refresh');
	}
</script>

</head>
<body>
	<!--컨텐츠 영역-->
	<div class="content ml_40">
		<div class="cont_title">
			<h2 class="ml_10">코드 관리</h2>
		</div>

		<!--조회-->
		<form id="form" name="form" method="post">
		<input type="hidden" name="codeId" id="codeId"/>
		<input type="hidden" name="pageNo" id="pageNo" value="<c:out value="${codeSearchTO.pageNo}" />">
		<div class="cont_inWrap mt_20">
			<h3 class="ml_10">조회조건</h3>
			<div class="inputWrap mt_25">
				<table cellpadding="0" cellspacing="0">
				<colgroup>
					<col style="width:110px;">
					<col style="width:350px;">							
					<col style="width:80px;">
					<col>							
				</colgroup>

				<tr>
					<td><label class="input_label">코드명</label></td>
					<td><input type="text" placeholder="코드명" id="codeName" name="codeName" value="<c:out value="${codeSearchTO.codeName}" />" style="width:300px;"></td>
					<td><label class="input_label">코드그룹</label></td>
					<td>
						<div class="fl bg_input mr_10" style="width:300px;">
							<select id="codeGrpId" name="codeGrpId">
								<option value="">전체</option>
								<c:forEach var="code" items="${grpList}" varStatus="status">
								<option value="<c:out value="${code.codeGrpId}" />" <c:if test="${codeSearchTO.codeGrpId eq code.codeGrpId}"> selected</c:if>><c:out value="${code.codeGrpName}" /></option>
								</c:forEach>
							</select>
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
			<p class="fl list_head_txt ml_10 mt_30">코드 목록 (<fmt:formatNumber value="${totalCnt }" pattern="#,###" /> 건)</p>
			
			<div class="fr">
				<button class="btn01_red mr_10" onclick="fn_addRow(); return false;">등록</button>
			</div>

		</div>

		<table class="tbl_list01 mt_25">
		<colgroup>
			<col style="width:150px;">
			<col style="width:130px;">
			<col style="width:240px;">
			<col style="width:100px;">
			<col style="width:190px;">
			<col>
		</colgroup>

		<thead>
		<tr class="thead_txt">
			<th>코드 ID</th>
			<th>그룹 코드 ID</th>
			<th>코드 명</th>
			<th>사용 유무</th>
			<th>등록 일시</th>
			<th>등록자</th>
		</tr>
		</thead>
		
		<tbody>
			<c:choose>
				<c:when test="${fn:length(list) > 0}">
					<c:forEach items="${list}" var="result" varStatus="status">
			<tr>
				<td><c:out value="${result.codeId}" /></td>
				<td><c:out value="${result.codeGrpId}" /></td>
				<td><a href="#" class="a_hover" onclick="fn_detail('<c:out value="${result.codeId}" />'); return false;"><c:out value="${result.codeName}" /></a></td>
           		<td><c:out value="${result.useYnName}" /></td>
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

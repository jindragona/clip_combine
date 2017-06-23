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
<title>사용자 관리</title>
<script type="text/javascript">
	$(function(){
		//최상단 체크박스 클릭
	    $("#chk01_all").click(function(){		
	        //클릭되었으면
	        if($(this).prop("checked")){			
	            //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 true로 정의
	            $('input[name=check1]').prop("checked",true);
	            //클릭이 안되있으면
	        }else{
	            //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 false로 정의
	            $('input[name=check1]').prop("checked",false);
	        }
	    });
		
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
		frm.userSeq.value = id;
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
        
		frm.teamName.value = "";
        frm.userType.options[0].selected = true;
        frm.dateType.options[0].selected = true;
		frm.stime.value = "";
		frm.etime.value = "";
        frm.searchType.options[0].selected = true;
		frm.searchWord.value = "";
		
		$('select').selectric('refresh');
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
</script>

</head>
<body>
	<!--컨텐츠 영역-->
	<div class="content ml_40">
		<div class="cont_title">
			<h2 class="ml_10">사용자 관리</h2>
		</div>

		<!--조회-->
		<form id="form" name="form" method="post">
		<input type="hidden" name="userSeq" id="userSeq" value="addRow"/>
		<input type="hidden" name="pageNo" id="pageNo" value="<c:out value="${userSearchTO.pageNo}" />">
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
					<td><label class="input_label">팀명</label></td>
					<td><input type="text" placeholder="팀이름" id="teamName" name="teamName" value="<c:out value="${userSearchTO.teamName}" />" style="width:300px;"></td>
					<td><label class="input_label">사용자</label></td>
					<td>
						<div class="fl bg_input mr_10" style="width:140px;">
							<select id="userType" name="userType">
								<option value="" <c:if test="${userSearchTO.dateType eq ''}">selected="selected"</c:if>>전체</option>
								<c:forEach var="code" items="${codeList}" varStatus="status">
								<option value="<c:out value="${code.codeId}" />" <c:if test="${userSearchTO.userType eq code.codeId}"> selected</c:if>><c:out value="${code.codeName}" /></option>
								</c:forEach>
							</select>
						</div>

					</td>
				</tr>
				
				<tr>
					<td><label  class="input_label">기간</label></td>
					<td colspan="3">
						<div class="fl bg_input mr_10" style="width:140px;">
							<select id="dateType" name="dateType">
								<option value="REG" <c:if test="${userSearchTO.dateType eq 'REG'}">selected="selected"</c:if>>등록일자</option>
								<option value="LOG" <c:if test="${userSearchTO.dateType eq 'LOG'}">selected="selected"</c:if>>최종로그인일자</option>
							</select>
						</div>

						<div class="fl mr_5">
							<input type="text" id="sdate" name="stime" readonly="readonly" style="width:110px;" value="<c:out value="${userSearchTO.stime}" />">
						</div>
						
						<span class="fl txt_wave"> ~ </span>
						
						<div class="fl mr_5">
							<input type="text" id="edate" name="etime" readonly="readonly" style="width:110px;" value="<c:out value="${userSearchTO.etime}" />">
						</div>

					</td>
				</tr>

				<tr>
					<td><label  class="input_label">구분</label></td>
					<td colspan="3">
						<div class="fl bg_input mr_10" style="width:140px;">
							<select name="searchType" id="searchType">
								<option value="KEY" <c:if test="${userSearchTO.searchType eq 'KEY'}">selected="selected"</c:if>>사용자 ID</option>
								<option value="NAME" <c:if test="${userSearchTO.searchType eq 'NAME'}">selected="selected"</c:if>>사용자 명</option>
							</select>
						</div>
						<input type="text" style="width:200px;" id="searchWord" name="searchWord" value="<c:out value="${userSearchTO.searchWord}" />">
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
			<p class="fl list_head_txt ml_10 mt_30">사용자 목록 (<fmt:formatNumber value="${totalCnt }" pattern="#,###" /> 건)</p>
			
			<div class="fr">
				<button class="btn01_red mr_10" onclick="fn_addRow(); return false;">등록</button>
			</div>

		</div>

		<table class="tbl_list01 mt_25">
		<colgroup>
			<col style="width:140px;">
			<col span="2" style="width:120px;">
			<col style="width:200px;">
			<col style="width:90px;">
			<col style="width:190px;">
			<col>
		</colgroup>

		<thead>
		<tr class="thead_txt">
			<th>사용자 ID</th>
			<th>사용자 명</th>
			<th>사용자 유형</th>
			<th>휴대폰 번호</th>
			<th>상태</th>
			<th>등록 일시</th>
			<th>등록자</th>
		</tr>
		</thead>
		
		<tbody>
			<c:choose>
				<c:when test="${fn:length(list) > 0}">
					<c:forEach items="${list}" var="result" varStatus="status">
			<tr>
				<td><a href="#" class="a_hover" onclick="fn_detail('<c:out value="${result.userSeq}" />'); return false;"><c:out value="${result.userIdTemp}" /></a></td>
				<td><a href="#" class="a_hover" onclick="fn_detail('<c:out value="${result.userSeq}" />'); return false;"><c:out value="${result.userNameTemp}" /></a></td>
				<td>
           		<c:forEach var="code" items="${codeList}" varStatus="status">
           			<c:if test="${result.userType eq code.codeId}"><c:out value="${code.codeName}" /></c:if>
           		</c:forEach>
           		</td>
           		<td><c:out value="${result.phoneNumTemp}" /></td>
           		<td><c:out value="${result.usrStusName}" /></td>
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
				<td colspan="8">데이터가 없습니다.</td>
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

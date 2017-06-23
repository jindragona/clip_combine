<!-- 
   MIDAS version 1.0
 
   Copyright ⓒ 2017 kt corp. All rights reserved.
 
   This is a proprietary software of kt corp, and you may not use this file except in
   compliance with license agreement with kt corp. Any redistribution or use of this
   software, with or without modification shall be strictly prohibited without prior written
   approval of kt corp, and the copyright notice above does not evidence any actual or
   intended publication of such software.
-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>접속 IP 관리</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bo/validator/user_validator.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$(".onlyNum").keyup(function(){$(this).val( $(this).val().replace(/[^0-9]/g,"") );} );
		$(".onlyNum").keypress(function(){$(this).val( $(this).val().replace(/[^0-9]/g,"") );} );
		$(".onlyNum").blur(function(){$(this).val( $(this).val().replace(/[^0-9]/g,"") );} );
	});
	
	function fn_addRow(){
		var postData = $('#frm').serialize();
        
		if(!fn_authip_validation(frm)){
			return;
		}
		
		if(confirm("접속 가능 IP 정보를 등록 하시겠습니까?")){
			$.ajax({
	     		type : "POST",
	     		url : "./userIpAuthAdd",
	     		dataType:"JSON",
	     		data: postData,
	     		success : function(data) {
	     			alert("접속 가능 IP 정보가 추가 되었습니다.");
	     			fn_ip_manage();
				},
				error : function(xhr, status, error) {
					alert("에러발생");
				}
			});
		}
	}
	
	function fn_reset(){
		$("#frm").each(function() {  
			this.reset();  
		});
		
		$('select').selectric('refresh');
	}
	
	function fn_ip_manage(){
		var frm = document.frm;
		frm.action = "./userIpAuth";
    	frm.submit();
	}
	
	function fn_auth_ip_remove(ipId){
		var frm = document.authForm;
		frm.sysAccIpId.value = ipId;
		
		var postData = $('#authForm').serialize();
        
		if(confirm("접속 가능 IP 정보를 삭제 하시겠습니까?")){
			$.ajax({
	     		type : "POST",
	     		url : "./userIpAuthRemove",
	     		dataType:"JSON",
	     		data: postData,
	     		success : function(data) {
	     			alert("접속 가능 IP 정보가 삭제 되었습니다.");
	     			fn_ip_manage();
				},
				error : function(xhr, status, error) {
					alert("에러발생");
				}
			});
		}
	}
	
	function fn_change_ip_type(e){
		if(e.value == '1'){
			$("#single").show();
			$("#double").hide();
		}else{
			$("#single").hide();
			$("#double").show();
		}
	}
	
	function fn_detail(){
		var frm = document.frm;
		frm.action = "./modRow";
    	frm.submit();
	}
</script>
</head>
<body>
	<!--컨텐츠 영역-->
	
	<div class="content ml_40">
		<div class="cont_title">
			<h2 class="ml_10">접속 IP 관리</h2>
		</div>

		<!--조회-->
		<div class="cont_inWrap mt_20">
			<h3 class="ml_10">시스템 접속 IP 등록</h3>
			<form id="frm" name="frm" method="post">
			<input type="hidden" name="userSeq" id="userSeq" value="<c:out value="${userSeq}" />">
			<div class="inputWrap mt_25">
				<table cellpadding="0" cellspacing="0">
				<colgroup>
					<col style="width:170px;">
					<col>
				</colgroup>

				<tr>
					<td><label class="input_label">*시스템 접속 IP 명 </label></td>
					<td><input type="text" id="sysAccIpNm" name="sysAccIpNm" style="width:290px;"></td>
				</tr>

				<tr>
					<td><label class="input_label">*IP 유형</label></td>
					<td>
						<div class="fl bg_input mr_10" style="width:140px;">
							<select id="ipType" name="ipType" onchange="fn_change_ip_type(this);">
								<option value="1">단일</option>
								<option value="2">범위</option>
							</select>
						</div>
					</td>
				</tr>
				
				<tr>
					<td><label class="input_label">*사용 여부</label></td>
					<td>
						<div class="fl bg_input mr_10" style="width:140px;">
							<select id="useYn" name="useYn">
								<option value="Y">사용</option>
								<option value="N">미사용</option>
							</select>
						</div>
					</td>
				</tr>

				<tr id="single">
					<td><label class="input_label">*IP 주소</label></td>
					<td>
						<input type="text" id="ipAdr1" name="ipAdr1" style="width:50px;" maxlength="3" class="onlyNum"> . 
						<input type="text" id="ipAdr2" name="ipAdr2" style="width:50px;" maxlength="3" class="onlyNum"> . 
						<input type="text" id="ipAdr3" name="ipAdr3" style="width:50px;" maxlength="3" class="onlyNum"> .
						<input type="text" id="ipAdr4" name="ipAdr4" style="width:50px;" maxlength="3" class="onlyNum">
					</td>
				</tr>
				<tr id="double" style="display: none">
					<td><label class="input_label">*IP 주소</label></td>
					<td>
						<input type="text" id="ipAdr5" name="ipAdr5" style="width:50px;" maxlength="3" class="onlyNum"> . 
						<input type="text" id="ipAdr6" name="ipAdr6" style="width:50px;" maxlength="3" class="onlyNum"> . 
						<input type="text" id="ipAdr7" name="ipAdr7" style="width:50px;" maxlength="3" class="onlyNum"> .
						<input type="text" id="ipAdr8" name="ipAdr8" style="width:50px;" maxlength="3" class="onlyNum">
						&nbsp;~&nbsp;
						<input type="text" id="ipAdr9" name="ipAdr9" style="width:50px;" maxlength="3" class="onlyNum"> . 
						<input type="text" id="ipAdr10" name="ipAdr10" style="width:50px;" maxlength="3" class="onlyNum"> . 
						<input type="text" id="ipAdr11" name="ipAdr11" style="width:50px;" maxlength="3" class="onlyNum"> .
						<input type="text" id="ipAdr12" name="ipAdr12" style="width:50px;" maxlength="3" class="onlyNum">
					</td>
				</tr>
				</table>
			</div>
			</form>
			<div class="inputBtnWrap mt_30">
				<input type="button" value="등록" class="btn01_white mr_10" onclick="fn_addRow(); return false;">
				<input type="button" value="초기화" class="btn01_white mr_10" onclick="fn_reset(); return false;">
				<input type="button" value="취소" class="btn01_white" onclick="fn_detail(); return false;">
			</div>
			
			<form id="authForm" name="authForm" method="post">
			<input type="hidden" id="sysAccIpId" name="sysAccIpId">
			</form>
			
			<div class="clearfix  mt_50">
				<div class="inputWrap05 mr_20">
					<div class="clearfix mt_20">
						<p class="fl list_head_txt ml_10 mt_10">접속 가능 IP 목록 (<c:out value="${fn:length(ipList)} " /> 건)</p>
					</div>

					<table class="tbl_list01 mt_10">
					<colgroup>
						<col style="width:180px;">
						<col style="width:220px;">
						<col style="width:90px;">
						<col style="width:90px;">
						<col>
						<col style="width:120px;">
						<col style="width:80px;">
					</colgroup>

					<thead>
					<tr class="thead_txt">
						<th>접속 IP명</th>
						<th>IP 주소</th>
						<th>IP 유형</th>
						<th>사용여부</th>
						<th>등록 일시</th>
						<th>등록자</th>
						<th>삭제</th>
					</tr>
					</thead>
					
					<tbody>		
						<c:choose>
							<c:when test="${fn:length(ipList) > 0}">
								<c:forEach items="${ipList}" var="ipResult" varStatus="status">
					<tr>
						<td><c:out value="${ipResult.sysAccIpNm}" /></td>
						<c:choose>
							<c:when test="${ipResult.ipType eq '1'}">
						<td><c:out value="${ipResult.addr1}" />.<c:out value="${ipResult.addr2}" />.<c:out value="${ipResult.addr3}" />.<c:out value="${ipResult.addr4}" /></td>
							</c:when>
							<c:otherwise>
						<td>
							<c:out value="${ipResult.addr1}" />.<c:out value="${ipResult.addr2}" />.<c:out value="${ipResult.addr3}" />.<c:out value="${ipResult.addr4}" />
							~
							<c:out value="${ipResult.addr5}" />.<c:out value="${ipResult.addr6}" />.<c:out value="${ipResult.addr7}" />.<c:out value="${ipResult.addr8}" />
						</td>
							</c:otherwise>
						</c:choose>
						
						<td><c:out value="${ipResult.ipTypeName}" /></td>
						<td><c:out value="${ipResult.useYnName}" /></td>
						<td><c:out value="${ipResult.inpDtt}" /></td>
						<td><c:out value="${ipResult.inprNmTemp}" /></td>
						<td><a href="#" onclick="fn_auth_ip_remove('<c:out value="${ipResult.sysAccIpId}" />');">삭제</a></td>
					</tr>
								</c:forEach>
							</c:when>
						</c:choose>
					</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
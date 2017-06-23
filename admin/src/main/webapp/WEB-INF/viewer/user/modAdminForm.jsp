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
<jsp:useBean class="java.util.Date" id="nowDate" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>사용자 관리</title>
<script type="text/javascript">
	$(document).ready(function(){
		$(".onlyNum").keyup(function(){$(this).val( $(this).val().replace(/[^0-9]/g,"") );} );
		$(".onlyNum").keypress(function(){$(this).val( $(this).val().replace(/[^0-9]/g,"") );} );
		$(".onlyNum").blur(function(){$(this).val( $(this).val().replace(/[^0-9]/g,"") );} );
	});
	var checkLoginId = false;
	     
	function fn_modRow(){
		var frm = document.frm;
		frm.mode.value="modRow";
		var postData = $('#frm').serialize();
	     
		if(confirm("사용자의 정보를 수정 하시겠습니까?")){
			$.ajax({
		 		type : "POST",
		 		url : "./admProc",
		 		dataType:"JSON",
		 		data: postData,
		 		success : function(data) {
		 			if(data.code == '0000'){
		 				alert("수정내역이 정상 반영 되었습니다.");
		 				fn_list();
		 			}else{
		 				alert("수정에 실패하였습니다.");
		 			}
				},
				error : function(xhr, status, error) {
					alert("에러발생");
				}
			});
		}
		
	}
	     
	function fn_list(){
		var frm = document.listForm;
		frm.action = "./list";
		frm.submit();
	}
	     
	function fn_checkLoginIdFalse(){
		checkLoginId = false;
	}
	
	function fn_pwd_init(){
		var postData = $('#frm').serialize();
		
		if(confirm("해당 사용자의 비밀 번호를 초기화 하시겠습니까?")){
			$.ajax({
		   		type : "POST",
		   		url : "./pwdInit",
		   		dataType:"JSON",
		   		data: postData,
		   		success : function(data) {
		   			alert("해당 사용자의 비밀번호가 초기화 되었습니다.");
				},
				error : function(xhr, status, error) {
					alert("에러발생");
				}
			});
		}
		
	}
	
	function fn_pwd_err_init(){
		var postData = $('#frm').serialize();
		
		if(confirm("해당 사용자의 비밀 번호 오류 횟수를 초기화 하시겠습니까?")){
			$.ajax({
		   		type : "POST",
		   		url : "./pwdErrorCntInit",
		   		dataType:"JSON",
		   		data: postData,
		   		success : function(data) {
		   			alert("해당 사용자의 로그인 오류 횟수가 초기화 되었습니다.");
		   			$("#pwdErrCnt").val("0");
				},
				error : function(xhr, status, error) {
					alert("에러발생");
				}
			});
		}
		
	}
	
	function fn_stop_user(){
		var postData = $('#frm').serialize();
		
		if(confirm("사용을 중단 시키겠습니까?")){
			$.ajax({
		 		type : "POST",
		 		url : "./stopUser",
		 		dataType:"JSON",
		 		data: postData,
		 		success : function(data) {
		 			alert("해당 사용자의 사용을 중단 시켰습니다.");
		 			fn_detail();
				},
				error : function(xhr, status, error) {
					alert("에러발생");
				}
			});
		}
	}
	
	function fn_use_user(){
		var postData = $('#frm').serialize();
		
		if(confirm("사용 할 수 있도록 변경 하시겠습니까?")){
			$.ajax({
		 		type : "POST",
		 		url : "./useUser",
		 		dataType:"JSON",
		 		data: postData,
		 		success : function(data) {
		 			alert("재사용 할 수 있도록 변경 하였습니다.");
		 			fn_detail();
				},
				error : function(xhr, status, error) {
					alert("에러발생");
				}
			});
		}
	}
	
	function fn_detail(){
		var frm = document.frm;
		frm.action = "./modRow";
    	frm.submit();
	}
	
	function fn_ip_manage(){
		var frm = document.frm;
		frm.action = "./userIpAuth";
    	frm.submit();
	}
</script>
</head>
<body>
	<!--컨텐츠 영역-->
	<form id="frm" name="frm" method="post">
	<input type="hidden" name="contNum" value="contNum">
	<input type="hidden" name="mode">
	<input type="hidden" name="userSeq" value="<c:out value="${detail.userSeq}" />">
	
	<div class="content ml_40">
		<div class="cont_title">
			<h2 class="ml_10">사용자 관리</h2>
		</div>

		<!--조회-->
		<div class="cont_inWrap mt_20">
			<h3 class="ml_10">사용자 상세 조회</h3>
			<div class="clearfix mt_25">
				<div class="inputWrap03">
					<table cellpadding="0" cellspacing="0">
					<colgroup>
						<col style="width:170px;">
						<col>
					</colgroup>
					
					<tr>
						<td></td>
					</tr>
					
					<tr>
						<td><label class="input_label">팀명</label></td>
						<td><input type="text" id="teamName" name="teamName" style="width:290px;" value="<c:out value="${detail.teamName}" />"></td>
					</tr>

					<tr>
						<td><label class="input_label">사용자 ID</label></td>
						<td><input type="text" style="width:290px;" class="input_text" readonly="readonly" value="<c:out value="${detail.userIdTemp}" />"></td>
					</tr>

					<tr>
						<td><label class="input_label">휴대폰 번호</label></td>
						<td><input type="text" style="width:290px;" class="input_text" readonly="readonly" value="<c:out value="${detail.phoneNumTemp}" />"></td>
					</tr>

					<tr>
						<td><label class="input_label">등록자</label></td>
						<td><span class="txt_gray_14 ml_8"><c:out value="${detail.insUserNameTemp}" /></span></td>
					</tr>
					</table>
				</div>
				
				<div class="inputWrap03 ml_1m">
					<table cellpadding="0" cellspacing="0">
					<colgroup>
						<col style="width:170px;">
						<col>
					</colgroup>

					<tr>
						<td><label class="input_label">본부명</label></td>
						<td><input type="text" id="hofcName" name="hofcName" style="width:290px;" value="<c:out value="${detail.hofcName}" />"></td>
					</tr>

					<tr>
						<td><label  class="input_label">사용자 유형</label></td>
						<td>
							<div class="fl bg_input mr_10" style="width:140px;">
								<select id="userType" name="userType">
									<c:forEach var="code" items="${codeList}" varStatus="status">
									<option value="<c:out value="${code.codeId}" />" <c:if test="${detail.userType eq code.codeId}"> selected</c:if>><c:out value="${code.codeName}" /></option>
									</c:forEach>
								</select>
							</div>	
						</td>
					</tr>

					<tr>
						<td><label class="input_label">사용자명</label></td>
						<td><input type="text" id="userName" name="userName" style="width:140px;" value="<c:out value="${detail.userName}" />"></td>
					</tr>

					<tr>
						<td><label class="input_label">비밀번호 오류 횟수</label></td>
						<td><input type="text" id="pwdErrCnt" name="pwdErrCnt" style="width:40px;" value="<c:out value="${detail.pwdErrCnt}" />" class="fl input_text" readonly="readonly">
							<input type="button" value="초기화" class="fl btn02_gray ml_10" style="width:80px;" onclick="fn_pwd_err_init();">
						</td>
					</tr>

					<tr>
						<td><label class="input_label">등록 일시</label></td>
						<td><span class="txt_gray_14 ml_8">
							<fmt:parseDate value="${detail.insDt}" var="dateFmt" pattern="yyyy-MM-dd HH:mm:ss"/>
							<fmt:formatDate value="${dateFmt}" pattern="yyyy-MM-dd HH:mm:ss"/>	
						</span></td>
					</tr>
					</table>
				</div>
			</div>
			
			<div class="inputBtnWrap mt_30">
			
				<c:choose>
					<c:when test="${detail.usrStus eq 'A'}">
				<input type="button" value="중단" class="btn01_white mr_10" onclick="fn_stop_user(); return false;">
					</c:when>
					<c:otherwise>
				<input type="button" value="사용" class="btn01_white mr_10" onclick="fn_use_user(); return false;">	
					</c:otherwise>
				</c:choose>
				<input type="button" value="접속 IP" class="btn01_white mr_10" onclick="fn_ip_manage(); return false;">
				<input type="button" value="수정" class="btn01_white mr_10" onclick="fn_modRow(); return false;">
				<input type="button" value="취소" class="btn01_white" onclick="fn_list(); return false;">
			</div>
		</div>
	</div>
	</form>
	
	<form id="listForm" name="listForm" method="post">
	</form>
</body>
</html>
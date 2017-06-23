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
<title>사용자 관리</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bo/validator/user_validator.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$(".onlyNum").keyup(function(){$(this).val( $(this).val().replace(/[^0-9]/g,"") );} );
		$(".onlyNum").keypress(function(){$(this).val( $(this).val().replace(/[^0-9]/g,"") );} );
		$(".onlyNum").blur(function(){$(this).val( $(this).val().replace(/[^0-9]/g,"") );} );
	});
	var checkLoginId = false;
         
	function fn_modRow(){
		var frm = document.frm;
		
		if(!fn_user_mod_validator(frm)){
			return;
		}
		
		var postData = $('#frm').serialize();
        
		if(confirm("사용자 정보를 수정 하시겠습니까?")){
			$.ajax({
	     		type : "POST",
	     		url : "./proc",
	     		dataType:"JSON",
	     		data: postData,
	     		success : function(data) {
	     			if(data.code == '0000'){
	     				alert("개인 정보가 정상적으로 수정 되었습니다.");
	     				goMain();
	     			}else if(data.code == '8002'){
	     				alert("기존 비밀번호가 틀립니다.");
	     			}
				},
				error : function(xhr, status, error) {
					alert("에러발생");
				}
			});
		}
	}
	
	function goMain(){
		var frm = document.frm;
		frm.action = '/office/main';
		frm.submit();
	}
</script>
</head>
<body>
	<!--컨텐츠 영역-->
	<form id="frm" name="frm" method="post">
	<input type="hidden" name="mode" value="modRow">
	<input type="hidden" name="phoneNum" value="phoneNum">
	<input type="hidden" name="contNum" value="contNum">
	<input type="hidden" name="userSeq" value="<c:out value="${detail.userSeq}" />">
	<div class="content ml_40">
		<div class="cont_title">
			<h2 class="ml_10">개인 정보 수정</h2>
		</div>

		<!--수정-->
		<div class="cont_inWrap mt_20">
			<h3 class="ml_10">사용자 상세 조회</h3>
			<div class="inputWrap mt_25">
				<table cellpadding="0" cellspacing="0">
				<colgroup>
					<col style="width:110px;">
					<col>
				</colgroup>

				<tr>
					<td><label class="input_label">사용자 ID</label></td>
					<td><input type="text" style="width:290px;" value="<c:out value="${detail.userIdTemp}" />" class="input_text" readonly></td>
				</tr>
				
				<tr>
					<td><label class="input_label">사용자명</label></td>
					<td><input type="text" style="width:290px;" value="<c:out value="${detail.userNameTemp}" />" class="input_text" readonly></td>
				</tr>

				<tr>
					<td><label class="input_label">팀명</label></td>
					<td><input type="text" id="teamName" name="teamName" style="width:290px;" value="<c:out value="${detail.teamName}" />"></td>
				</tr>
				
				<tr>
					<td><label class="input_label">본부명</label></td>
					<td><input type="text" id="hofcName" name="hofcName" style="width:290px;" value="<c:out value="${detail.hofcName}" />"></td>
				</tr>

				<tr>
					<td><label class="input_label">휴대폰 번호</label></td>
					<td>
						<div class="fl bg_input mr_10" style="width:80px;">
							<select id="phone1" name="phone1">
								<option value="010" <c:if test="${detail.phone1 eq '010'}"> selected</c:if>>010</option>
								<option value="011" <c:if test="${detail.phone1 eq '011'}"> selected</c:if>>011</option>
								<option value="016" <c:if test="${detail.phone1 eq '016'}"> selected</c:if>>016</option>
								<option value="018" <c:if test="${detail.phone1 eq '018'}"> selected</c:if>>018</option>
								<option value="019" <c:if test="${detail.phone1 eq '019'}"> selected</c:if>>019</option>
							</select>
						</div>									
						<input type="text" id="phone2" name="phone2" style="width:90px;" class="fl onlyNum" maxlength="4" value="<c:out value="${detail.phone2}" />">
						<span class="fl txt_hyphen"> - </span>
						<input type="text" id="phone3" name="phone3" class="fl onlyNum" style="width:90px;" maxlength="4" value="<c:out value="${detail.phone3}" />">
					</td>
				</tr>
				
				<tr>
					<td><label class="input_label">기존 비밀번호</label></td>
					<td><input type="password" id="userOldPwd" name="userOldPwd" style="width:180px;"></td>
				</tr>
				
				<tr>
					<td><label class="input_label">신규 비밀번호</label></td>
					<td><input type="password" id="userNewPwd" name="userNewPwd" style="width:180px;" class="mr_25">
						<label class="input_label mr_25">*비밀번호 확인</label>
						<input type="password" id="userNewPwdConfirm" name="userNewPwdConfirm" style="width:180px;">
					</td>
				</tr>
				<tr>
					<td colspan="2"><label class="input_label" style="color: red">비밀번호는 영문 (대/소문자), 숫자, 특수문자 조합 10자 이상 입력 하셔야 합니다.</label></td>
				</tr>
				</table>
			</div>

			<div class="inputBtnWrap mt_30">						
				<input type="button" value="수정" class="btn01_white" onclick="fn_modRow(); return false;">
			</div>
		</div>
	</div>
	</form>
	<form id="listForm" name="listForm" method="post">
	</form>
</body>
</html>
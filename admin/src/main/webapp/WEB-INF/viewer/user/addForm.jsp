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
         
	function fn_addRow(){
		var frm = document.frm;
		frm.phoneNum.value = frm.phone1.value + "-" + frm.phone2.value + "-" + frm.phone3.value;
		
		if(!fn_user_add_validator(frm)){
			return;
		}
		
		var postData = $('#frm').serialize();
        
		if(confirm("사용자를 등록 하시겠습니까?")){
			$.ajax({
	     		type : "POST",
	     		url : "./proc",
	     		dataType:"JSON",
	     		data: postData,
	     		success : function(data) {
	     			if(data.code == '0000'){
	     				alert("사용자 등록이 완료 되었습니다.");
	     				fn_list();
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
         
	function fn_checkVal(val){
		var userId = $("#"+val).val();
       	
		if(!userId){
       		alert("사용자 로그인 ID 를 입력 해 주세요.");
       		return false;
       	}
		
		if(userId.length < 6){
			alert("사용자 로그인 ID 는 6자리 이상 입력해 주세요.");
			return false;
		}
       	
       	$.ajax({
       		type : "POST",
       		url : "./checkLogingId",
       		dataType:"JSON",
       		data: $('#frm').serialize(),
       		success : function(info) {
       			
       			if(info.code == '0000'){
       				alert("사용 가능한 ID 입니다.");
       				checkLoginId = true;
       			}else{
       				alert("이미 사용중인 ID 정보입니다.");
       				$('#userId').focus();
       			}
       			
			},
			error : function(xhr, status, error) {
				alert("에러발생");
			}
		});
	}
	
	function fn_checkLoginIdFalse(){
		checkLoginId = false;
	}
	
	function fn_reset(){
		$("#frm").each(function() {  
			this.reset();  
		});
		
		$('select').selectric('refresh');
	}
	
</script>
</head>
<body>
	
	<!--컨텐츠 영역-->
	<form id="frm" name="frm" action="./proc" method="post">
	<input type="hidden" name="mode" value="addRow">
	<input type="hidden" name="phoneNum" value="phoneNum">
	<input type="hidden" name="contNum" value="contNum">
	<div class="content ml_40">
		<div class="cont_title">
			<h2 class="ml_10">사용자 관리</h2>
		</div>

		<!--조회-->
		<div class="cont_inWrap mt_20">
			<h3 class="ml_10">사용자 등록</h3>
			<div class="inputWrap mt_25">
				<table cellpadding="0" cellspacing="0">
				<colgroup>
					<col style="width:170px;">
					<col>
				</colgroup>

				<tr>
					<td><label class="input_label">사용자 유형</label></td>
					<td>
						<div class="fl bg_input mr_10" style="width:140px;">
							<select id="userType" name="userType">
								<c:forEach var="code" items="${codeList}" varStatus="status">
								<option value="<c:out value="${code.codeId}" />"><c:out value="${code.codeName}" /></option>
								</c:forEach>
							</select>
						</div>	
					</td>
				</tr>

				<tr>
					<td><label class="input_label">본부명</label></td>
					<td><input type="text" id="hofcName" name="hofcName" style="width:290px;" placeholder="본부명"></td>
				</tr>

				<tr>
					<td><label class="input_label">팀명</label></td>
					<td><input type="text" id="teamName" name="teamName" style="width:290px;" placeholder="팀명"></td>
				</tr>

				<tr>
					<td><label class="input_label">*사용자 명</label></td>
					<td><input type="text" id="userName" name="userName" style="width:290px;" placeholder="홍길동" maxlength="10">									
					</td>
				</tr>

				<tr>
					<td><label class="input_label">*사용자 로그인 ID</label></td>
					<td><input type="text" id="userId" name="userId" style="width:290px;" class="fl" onkeypress="fn_checkLoginIdFalse();" onkeyup="fn_checkLoginIdFalse();">
						<input type="button" value="중복확인" class="fl btn02_gray ml_10" onclick="fn_checkVal('userId');">
					</td>
				</tr>

				<tr>
					<td><label class="input_label">*사용자 비밀번호</label></td>
					<td><input type="password" id="userPwd" name="userPwd" style="width:180px;" class="mr_25">
						<label class="input_label mr_25">*비밀번호 확인</label>
						<input type="password" id="userPwdConfirm" name="userPwdConfirm" style="width:180px;" >
						
					</td>
				</tr>
				<tr>
					<td colspan="2"><label class="input_label" style="color: red">비밀번호는 영문 (대/소문자), 숫자, 특수문자 조합 10자 이상 입력 하셔야 합니다.</label></td>
				</tr>
				<tr>
					<td><label class="input_label">*휴대폰 번호</label></td>
					<td>
						<div class="fl bg_input mr_10" style="width:80px;">
							<select id="phone1" name="phone1">
								<option value="010">010</option>
								<option value="011">011</option>
								<option value="016">016</option>
								<option value="018">018</option>
								<option value="019">019</option>
							</select>
						</div>									
						<input type="text" id="phone2" name="phone2" style="width:90px;" class="fl onlyNum" maxlength="4">
						<span class="fl txt_hyphen"> - </span>
						<input type="text" id="phone3" name="phone3" class="fl onlyNum" style="width:90px;" maxlength="4">
					</td>
				</tr>
				</table>
			</div>
			<div class="inputBtnWrap mt_30">
				<input type="button" value="등록" class="btn01_red mr_10" onclick="fn_addRow(); return false;">
				<input type="button" value="초기화" class="btn01_white mr_10" onclick="fn_reset(); return false;">
				<input type="button" value="취소" class="btn01_white" onclick="fn_list(); return false;">
			</div>
		</div>
	</div>
	</form>
	<form id="listForm" name="listForm" method="post">
	</form>
</body>
</html>
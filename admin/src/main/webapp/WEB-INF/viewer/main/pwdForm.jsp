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
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bo/validator/user_validator.js"></script>
<title>비밀번호 변경</title>
<script type="text/javascript">
	$(function(){
	});
	
	function fn_main_move(){
		var frm = document.form;
		frm.action = "./main";
    	frm.submit();
	}
	
	function fn_pwd_mod(){
    	var postData = $('#form').serialize();
    	var frm = document.form;
    	
    	if(!fn_pwd_mod_validator(frm)){
			return;
		}
    	
    	$.ajax({
    		type : "POST",
    		url : "./pwdMod",
    		dataType:"JSON",
    		data: postData,
    		success : function(data) {
    			if(data.code == '8002'){
    				alert("기존에 사용하던 비밀번호가 틀립니다. 다시 확인해 주시기 바랍니다.");
    				frm.oldPwd.focus();
    			}else{
    				alert("비밀번호가 정상적으로 변경 되었습니다.");
    				fn_main_move();
    			}
			},
			error : function(xhr, status, error) {
				alert("에러발생");
			}
    	});
	}
	
</script>
</head>
<body>
	<!--컨텐츠 영역-->
	<div class="content ml_40">
		<div class="cont_title">
			<h2 class="ml_10">비밀 번호 변경</h2>
		</div>
		
		<!--등록-->
		<form id="form" name="form" method="post">
		<div class="cont_inWrap mt_20">
			
			<div class="inputWrap mt_25">
				<table cellpadding="0" cellspacing="0">
				<colgroup>
					<col style="width:160px;">
					<col>							
				</colgroup>						
				<tr>
					<td><label class="input_label">기존 비밀 번호</label></td>
					<td><input type="password" name="oldPwd" id="oldPwd" style="width:380px;"></td>
				</tr>
				<tr>
					<td><label class="input_label">신규 비밀 번호</label></td>
					<td><input type="password" name="newPwd" id="newPwd" style="width:380px;"></td>
				</tr>
				<tr>
					<td><label class="input_label">신규 비밀 번호 확인</label></td>
					<td><input type="password" name="newPwdConfirm" id="newPwdConfirm" style="width:380px;"></td>
				</tr>
				</table>
			</div>

			<div class="inputBtnWrap mt_30">
				<input type="button" value="변경" class="btn01_red mr_10" onclick="fn_pwd_mod(); return false;">
				<input type="button" value="취소" class="btn01_white" onclick="fn_main_move(); return false;">
			</div>
		</div>
		</form>
	</div>
</body>
</html>

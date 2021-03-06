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
<title>코드 관리</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bo/validator/code_validator.js"></script>
<script type="text/javascript">
	var checkLoginId = false;

	$(document).ready(function(){
		$(".onlyNum").keyup(function(){$(this).val( $(this).val().replace(/[^0-9]/g,"") );} );
		$(".onlyNum").keypress(function(){$(this).val( $(this).val().replace(/[^0-9]/g,"") );} );
		$(".onlyNum").blur(function(){$(this).val( $(this).val().replace(/[^0-9]/g,"") );} );
	});
       
	function fn_addRow(){
		var frm = document.frm;
		var postData = $('#frm').serialize();
       	
		if(!fn_code_add_validator(frm)){
			return;
		}
		
		if(confirm("기본 정보를 등록 하시겠습니까?")){
			$.ajax({
	       		type : "POST",
	       		url : "./proc",
	       		dataType:"JSON",
	       		data: postData,
	       		success : function(data) {
	       			if(data.code == '0000'){
	       				alert("코드 정보가 등록 되었습니다.");
	       				fn_list();
	       			}else{
	       				alert("코드 정보 등록 중 오류가 발생 하였습니다.");
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
		var codeId = $("#"+val).val();
       	
		if(!codeId){
       		alert("코드 ID 를 입력 해 주세요.");
       		return false;
       	}
       	
       	$.ajax({
       		type : "POST",
       		url : "./checkCodeId",
       		dataType:"JSON",
       		data: $('#frm').serialize(),
       		success : function(info) {
       			if(info.code == '0000'){
       				alert("사용 가능한 ID 입니다.");
       				checkLoginId = true;
       			}else{
       				alert("이미 사용중인 ID 정보입니다.");
       				$('#codeId').focus();
       			}
       			
			},
			error : function(xhr, status, error) {
				alert("에러발생");
			}
		});
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
	<form id="frm" name="frm" method="post">
	<input type="hidden" name="mode" value="addRow">
	<div class="content ml_40">
		<div class="cont_title">
			<h2 class="ml_10">코드 관리</h2>
		</div>

		<!--조회-->
		<div class="cont_inWrap mt_20">
			<h3 class="ml_10">코드 등록</h3>
			<div class="inputWrap mt_25">
				<table cellpadding="0" cellspacing="0">
				<colgroup>
					<col style="width:170px;">
					<col>
				</colgroup>

				<tr>
					<td><label class="input_label">코드 ID</label></td>
					<td>
						<input type="text" id="codeId" name="codeId" style="width:290px;" class="fl" placeholder="코드ID">
						<input type="button" value="중복확인" class="fl btn02_gray ml_10" onclick="fn_checkVal('codeId');">
					</td>
				</tr>

				<tr>
					<td><label class="input_label">코드 그룹 ID</label></td>
					<td>
						<div class="fl bg_input mr_10" style="width:290px;">
							<select id="codeGrpId" name="codeGrpId">
								<c:forEach var="code" items="${grpList}" varStatus="status">
								<option value="<c:out value="${code.codeGrpId}" />"><c:out value="${code.codeGrpName}" /></option>
								</c:forEach>
							</select>
						</div>	
					</td>
				</tr>

				<tr>
					<td><label class="input_label">코드명</label></td>
					<td><input type="text" id="codeName" name="codeName" style="width:290px;" placeholder="코드명"></td>
				</tr>

				<tr>
					<td><label class="input_label">코드순서</label></td>
					<td><input type="text" id="codeOdr" name="codeOdr" style="width:100px;" class="onlyNum" placeholder="코드순서" maxlength="2"></td>
				</tr>
				
				<tr>
					<td><label class="input_label">사용여부</label></td>
					<td>
						<div class="fl bg_input mr_10" style="width:140px;">
							<select id="useYn" name="useYn">
								<option value="Y">사용</option>
								<option value="N">미사용</option>
							</select>
						</div>
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
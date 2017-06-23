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
<title>코드 그룹 관리</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bo/validator/code_grp_validator.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
	});
       
	function fn_addRow(){
		var frm = document.frm;
		var postData = $('#frm').serialize();
       	
		if(!fn_code_grp_add_validator(frm)){
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
	       				alert("코드 그룹 정보가 등록 되었습니다.");
	       				fn_list();
	       			}else{
	       				alert("코드 그룹 정보 등록 중 오류가 발생 하였습니다.");
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
			<h2 class="ml_10">코드 그룹 관리</h2>
		</div>

		<!--조회-->
		<div class="cont_inWrap mt_20">
			<h3 class="ml_10">코드 그룹 등록</h3>
			<div class="inputWrap mt_25">
				<table cellpadding="0" cellspacing="0">
				<colgroup>
					<col style="width:170px;">
					<col>
				</colgroup>

				<tr>
					<td><label class="input_label">코드 그룹 ID</label></td>
					<td>
						<input type="text" id="grpCodeId" name="grpCodeId" style="width:290px;" class="fl" placeholder="코드 그룹 ID">
					</td>
				</tr>

				<tr>
					<td><label class="input_label">코드 그룹 명</label></td>
					<td><input type="text" id="grpCodeName" name="grpCodeName" style="width:290px;" placeholder="코드 그룹 명"></td>
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
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
<title>권한 관리</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bo/validator/auth_validator.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
	});
	var checkLoginId = false;
         
	function fn_modRow(){
		var frm = document.frm;
		
		if(!fn_auth_add_validator(frm)){
			return;
		}
		
		var postData = $('#frm').serialize();
		if(confirm("사용자 권한 정보를 수정 하시겠습니까?")){
			$.ajax({
	     		type : "POST",
	     		url : "./proc",
	     		dataType:"JSON",
	     		data: postData,
	     		success : function(data) {
	     			if(data.code == '0000'){
	     				alert("해당 권한이 수정 되었습니다.");
	     				fn_reload();
	     			}
				},
				error : function(xhr, status, error) {
					alert("에러발생");
				}
			});
		}
		
	}
         
	function fn_list(){
		var frm = document.frm;
		frm.action = "./list";
		frm.submit();
	}
	
	function fn_user_appoint_pop(){
		var authForm = document.authForm;
		var frm = document.frm;
		authForm.rolId.value = frm.rolId.value;
		
		var postData = $('#authForm').serialize();
		
		$.ajax({
     		type : "POST",
     		url : "./notMappingUser",
     		dataType:"JSON",
     		data: postData,
     		success : function(data) {
     			fn_draw_user(data.data);
			},
			error : function(xhr, status, error) {
				alert("에러발생");
			}
		});
	}
	
	function fn_draw_user(data){
		$("#userLayer").empty();
		var html = "";
		
		html += "<colgroup>";
		html += "<col style=\"width:60px;\">";
		html += "<col style=\"width:130px;\">";
		html += "<col>";
		html += "</colgroup>";
		
		html += "<thead>";
		html += "<tr class=\"thead_txt\">";
		html += "<th><input type=\"checkbox\" name=\"notUserAllCheck\" id=\"notUserAllCheck\" onclick=\"fn_not_user_all_check();\"><label class=\"input_label ml_10\" for=\"notUserAllCheck\"></th>";
		html += "<th>사용자명</th>";
		html += "<th>팀명</th>";
		html += "</tr>";
		html += "</thead>";
		
		html += "<tbody>";
		for(var i=0;i<data.length;i++){
			html += "<tr>";
			html += "<td><input type=\"checkbox\" name=\"notUserCheck\" class=\"notUserChkbox\" id='notUserCheck_"+i+"' value='"+data[i].userSeq+"'><label class=\"input_label ml_10\" for='notUserCheck_"+i+"'></label></td>";
			html += "<td>"+data[i].userNameTemp+"</td>";
			html += "<td>"+data[i].teamName;+"</td>";
			html += "</tr>";
		}
		
		html += "</tbody>";
		
		$("#userLayer").append(html);
		popup_open('.popup05');
	}
	
	function fn_not_user_all_check(){
		if($("input:checkbox[id='notUserAllCheck']").is(":checked")){
			$(".notUserChkbox").prop('checked', true);
		}else{
			$(".notUserChkbox").prop('checked', false);
		}
	}
	
	function fn_auth_user_set(){
		var authForm = document.authForm;
		var frm = document.frm;
		
		var checkUser = document.getElementsByName("notUserCheck");
		var userIdArr = "";
		
		for(var i=0;i<checkUser.length;i++){
			if(checkUser[i].checked){
				userIdArr = userIdArr + "," + checkUser[i].value;
			}
    	}
		
		userIdArr = userIdArr.substring(1, userIdArr.length);
		
		authForm.userId.value = userIdArr;
		authForm.rolId.value = frm.rolId.value;
		
		var postData = $('#authForm').serialize();
		
		if(confirm("해당 권한의 사용자 정보를 등록 하시겠습니까?")){
			$.ajax({
	     		type : "POST",
	     		url : "./mappingUser",
	     		dataType:"JSON",
	     		data: postData,
	     		success : function(data) {
	     			fn_reload();
				},
				error : function(xhr, status, error) {
					alert("에러발생");
				}
			});
		}
		
		
	}
	
	function fn_auth_user_close(){
		$("#userLayer").empty();
	}
	
	function fn_user_close_pop(){
		var authForm = document.authForm;
		var frm = document.frm;
		
		var checkUser = document.getElementsByName("userCheck");
		var userIdArr = "";
		
		for(var i=0;i<checkUser.length;i++){
			if(checkUser[i].checked){
				userIdArr = userIdArr + "," + checkUser[i].value;
			}
    	}
		
		if(userIdArr == null || userIdArr == ''){
			alert("해지할 사용자를 선택해 주세요.");
			return;
		}
		
		userIdArr = userIdArr.substring(1, userIdArr.length);
		
		authForm.userId.value = userIdArr;
		authForm.rolId.value = frm.rolId.value;
		
		var postData = $('#authForm').serialize();
		
		if(confirm("해당 권한의 사용자 정보를 삭제 하시겠습니까?")){
			$.ajax({
	     		type : "POST",
	     		url : "./mappingCloseUser",
	     		dataType:"JSON",
	     		data: postData,
	     		success : function(data) {
	     			fn_reload();
				},
				error : function(xhr, status, error) {
					alert("에러발생");
				}
			});
		}
	}
	
	function fn_menu_appoint_pop(){
		var authForm = document.authForm;
		var frm = document.frm;
		authForm.rolId.value = frm.rolId.value;
		
		var postData = $('#authForm').serialize();
		
		$.ajax({
     		type : "POST",
     		url : "./notMappingMenu",
     		dataType:"JSON",
     		data: postData,
     		success : function(data) {
     			fn_draw_menu(data.data);
			},
			error : function(xhr, status, error) {
				alert("에러발생");
			}
		});
	}
	
	function fn_draw_menu(data){
		$("#menuLayer").empty();
		var html = "";
		
		html += "<colgroup>";
		html += "<col style=\"width:90px;\">";
		html += "<col style=\"width:180px;\">";
		html += "<col >";
		html += "</colgroup>";
		
		html += "<thead>";
		html += "<tr class=\"thead_txt\">";
		html += "<th><input type=\"checkbox\" name=\"notMenuAllCheck\" id=\"notMenuAllCheck\" onclick=\"fn_not_menu_all_check();\"><label class=\"input_label ml_10\" for=\"notMenuAllCheck\"></label></th>";
		html += "<th>메뉴</th>";
		html += "<th>경로</th>";
		html += "</tr>";
		html += "</thead>";
		
		html += "<tbody>";
		for(var i=0;i<data.length;i++){
			var path = data[i].pathName;
			
			path = path.replace("ROOT > ", "");
			
			html += "<tr>";
			html += "<td><input type=\"checkbox\" name=\"notMenuCheck\" class=\"menuChkbox\" id='notMenuCheck_"+i+"' value='"+data[i].menuId+"'><label class=\"input_label ml_10\" for='notMenuCheck_"+i+"'></label></td>";
			
			if(data[i].upperMenuId == '0'){
				html += "<td class=\"menu01_txt\">"+data[i].menuName+"</td>";
			}else{
				html += "<td class=\"menu02_txt\">"+data[i].menuName+"</td>";
			}
			html += "<td class=\"menu_root_txt\">"+path+"</td>";
			html += "</tr>";
		}
		
		html += "</tbody>";
		
		$("#menuLayer").append(html);
		popup_open('.popup06');
	}
	
	function fn_not_menu_all_check(){
		if($("input:checkbox[id='notMenuAllCheck']").is(":checked")){
			$(".menuChkbox").prop('checked', true);
		}else{
			$(".menuChkbox").prop('checked', false);
		}
	}
	
	function fn_auth_menu_set(){
		var authForm = document.authForm;
		var frm = document.frm;
		
		var checkMenu = document.getElementsByName("notMenuCheck");
		var menuIdArr = "";
		
		for(var i=0;i<checkMenu.length;i++){
			if(checkMenu[i].checked){
				menuIdArr = menuIdArr + "," + checkMenu[i].value;
			}
    	}
		
		menuIdArr = menuIdArr.substring(1, menuIdArr.length);
		
		authForm.menuIdArr.value = menuIdArr;
		authForm.rolId.value = frm.rolId.value;
		
		var postData = $('#authForm').serialize();
		
		if(confirm("해당 권한의 메뉴 정보를 추가 하시겠습니까?")){
			$.ajax({
	     		type : "POST",
	     		url : "./mappingMenu",
	     		dataType:"JSON",
	     		data: postData,
	     		success : function(data) {
	     			fn_reload();
				},
				error : function(xhr, status, error) {
					alert("에러발생");
				}
			});
		}
		
	}
	
	function fn_auth_menu_close(){
		$("#menuLayer").empty();
	}
	
	function fn_menu_close_pop(){
		var authForm = document.authForm;
		var frm = document.frm;
		
		var checkMenu = document.getElementsByName("menuCheck");
		var menuIdArr = "";
		
		for(var i=0;i<checkMenu.length;i++){
			if(checkMenu[i].checked){
				menuIdArr = menuIdArr + "," + checkMenu[i].value;
			}
    	}
		
		if(menuIdArr == null || menuIdArr == ''){
			alert("해지할 메뉴를 선택해 주세요");
			return;
		}
		
		menuIdArr = menuIdArr.substring(1, menuIdArr.length);
		
		authForm.menuIdArr.value = menuIdArr;
		authForm.rolId.value = frm.rolId.value;
		
		var postData = $('#authForm').serialize();
		
		if(confirm("해당 권한의 메뉴 정보를 삭제 하시겠습니까?")){
			$.ajax({
	     		type : "POST",
	     		url : "./mappingCloseMenu",
	     		dataType:"JSON",
	     		data: postData,
	     		success : function(data) {
	     			fn_reload();
				},
				error : function(xhr, status, error) {
					alert("에러발생");
				}
			});
		}
		
	}
	
	function fn_menu_all_check(){
		if($("input:checkbox[id='menuAllCheck']").is(":checked")){
			$(".menuCheck").prop('checked', true);
		}else{
			$(".menuCheck").prop('checked', false);
		}
	}
	
	function fn_reload(){
		var frm = document.frm;
		frm.action = "./modRow";
    	frm.submit();
	}
	
	function fn_userAllCheck(){
		if($("input:checkbox[id='userAllCheck']").is(":checked")){
			$(".userCheck").prop('checked', true);
		}else{
			$(".userCheck").prop('checked', false);
		}
	}
	
	function fn_del_row(){
		var postData = $('#frm').serialize();
		
		if(confirm("해당 권한을 삭제 하시겠습니까?")){
			$.ajax({
	     		type : "POST",
	     		url : "./removeAuthMenu",
	     		dataType:"JSON",
	     		data: postData,
	     		success : function(data) {
	     			fn_list();
				},
				error : function(xhr, status, error) {
					alert("에러발생");
				}
			});
		}
	}
</script>
</head>
<body>
	<!--컨텐츠 영역-->
	
	<div class="content ml_40">
		<div class="cont_title">
			<h2 class="ml_10">권한 관리</h2>
		</div>

		<!--조회-->
		<div class="cont_inWrap mt_20">
			<h3 class="ml_10">역할 상세 조회</h3>
			<form id="frm" name="frm" method="post">
			<input type="hidden" name="mode" value="modRow">
			<input type="hidden" name="rolId" value="<c:out value="${detail.rolId}" />">
			<div class="inputWrap mt_25">
				<table cellpadding="0" cellspacing="0">
				<colgroup>
					<col style="width:170px;">
					<col>
				</colgroup>

				<tr>
					<td><label class="input_label">역할명</label></td>
					<td><input type="text" id="rolName" name="rolName" style="width:290px;" value="<c:out value="${detail.rolName}" />"></td>
				</tr>

				<tr>
					<td><label class="input_label">사용여부</label></td>
					<td>
						<div class="fl bg_input mr_10" style="width:140px;">
							<select id="useYn" name="useYn">
								<option value="Y" <c:if test="${detail.useYn eq 'Y'}">selected="selected"</c:if>>사용</option>
								<option value="N" <c:if test="${detail.useYn eq 'N'}">selected="selected"</c:if>>미사용</option>
							</select>
						</div>
					</td>
				</tr>

				<tr style="height:115px;">
					<td class="input_label_vt"><label class="input_label">역할 설명</label></td>
					<td><textarea style="width:805px;height:100px" id="rolDesc" name="rolDesc"><c:out value="${detail.rolDesc}" escapeXml="false"/></textarea></td>
				</tr>

				<tr>
					<td><label class="input_label">등록자</label></td>
					<td><span class="txt_gray_14 ml_8"><c:out value="${detail.insUserNameTemp}" /></span></td>
				</tr>

				<tr>
					<td><label class="input_label">등록일</label></td>
					<td><span class="txt_gray_14 ml_8">
						<fmt:parseDate value="${detail.insDt}" var="dateFmt" pattern="yyyy-MM-dd HH:mm:ss"/>
						<fmt:formatDate value="${dateFmt}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</span></td>
				</tr>
				</table>
			</div>
			</form>
			<div class="inputBtnWrap mt_30">
				<input type="button" value="수정" class="btn01_white mr_10" onclick="fn_modRow(); return false;">
				<input type="button" value="삭제" class="btn01_white mr_10" onclick="fn_del_row(); return false;">
				<input type="button" value="취소" class="btn01_white" onclick="fn_list(); return false;">
			</div>
			
			<form id="authForm" name="authForm" method="post">
			<input type="hidden" id="userId" name="userId">
			<input type="hidden" id="menuIdArr" name="menuIdArr">
			<input type="hidden" id="rolId" name="rolId">
			</form>
			
			<div class="clearfix  mt_50">
				<div class="inputWrap04 mr_20">
					<div class="clearfix mt_20">
						<p class="fl list_head_txt ml_10 mt_10">역할 사용자 목록 (<c:out value="${fn:length(userList)} " /> 건)</p>
				
						<div class="fr">
							<button class="btn02_gray mr_10" onclick="fn_user_appoint_pop();">지정</button>
							<button class="btn02_gray" onclick="fn_user_close_pop();">해지</button>
						</div>
					</div>

					<table class="tbl_list01 mt_10">
					<colgroup>
						<col style="width:90px;">
						<col style="width:180px;">
						<col>
					</colgroup>

					<thead>
					<tr class="thead_txt">
						<th><input type="checkbox" name="userAllCheck" id="userAllCheck" onclick="fn_userAllCheck();"><label class="input_label ml_10" for="userAllCheck"></label></th>
						<th>사용자</th>
						<th>팀명</th>
					</tr>
					</thead>
					
					<tbody>		
						<c:choose>
							<c:when test="${fn:length(userList) > 0}">
								<c:forEach items="${userList}" var="userResult" varStatus="status">
					<tr>
						<td><input type="checkbox" class="userCheck" name="userCheck" id="userCheck_${status.count}" value="<c:out value="${userResult.userSeq}" />"><label class="input_label ml_10" for="userCheck_${status.count}"></label></td>
						<td><c:out value="${userResult.userNameTemp}" /></td>
                   		<td><c:out value="${userResult.teamName}"/></td>
					</tr>
								</c:forEach>
							</c:when>
						</c:choose>
					</tbody>
					</table>
				</div>

				<div class="inputWrap04">
					<div class="clearfix mt_20">
						<p class="fl list_head_txt ml_10 mt_10">역할 지정 메뉴</p>
				
						<div class="fr">
							<button class="btn02_gray mr_10" onclick="fn_menu_appoint_pop();">지정</button>
							<button class="btn02_gray" onclick="fn_menu_close_pop()">해지</button>
						</div>
					</div>

					<table class="tbl_list01 mt_10">
					<colgroup>
						<col style="width:90px;">
						<col style="width:180px;">
						<col>
					</colgroup>

					<thead>
					<tr class="thead_txt">
						<th><input type="checkbox" name="menuAllCheck" id="menuAllCheck" onclick="fn_menu_all_check();"><label class="input_label ml_10" for="menuAllCheck"></label></th>
						<th>메뉴</th>
						<th>경로</th>
					</tr>
					</thead>
					
					<tbody>		
						<c:choose>
							<c:when test="${fn:length(menuList) > 0}">
								<c:forEach items="${menuList}" var="menuResult" varStatus="status">
						<tr>
							<td><input type="checkbox" class="menuCheck" name="menuCheck" id="menuCheck_${status.count}" value="<c:out value="${menuResult.menuId}" />"><label class="input_label ml_10" for="menuCheck_${status.count}"></label></td>
							<c:choose>
								<c:when test="${menuResult.upperMenuId eq 0}">
							<td class="menu01_txt"><c:out value="${menuResult.menuName}" /></td>
								</c:when>
								<c:otherwise>
							<td class="menu02_txt"><c:out value="${menuResult.menuName}" /></td>
								</c:otherwise>
							</c:choose>
	                   		<td class="menu_root_txt"><c:out value="${menuResult.pathName}"/></td>
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
	
	<div class="bg_popup"></div>

	<!--권한그룹팝업 레이어팝업창 -->
	<div class="popup05 popup_cont">
		<h3 class="ml_10 mt_10">역할 사용자 목록</h3>
		<table class="tbl_list01 mt_25" id="userLayer">
		</table>
	
		<div class="mt_20 tac">
			<input type="button" value="지정" class="btn02_gray mr_10" onclick="fn_auth_user_set();">
			<input type="button" value="취소" class="btn_cancle btn02_white" onclick="fn_auth_user_close();">
		</div>
		
	</div>
	
	<!--권한메뉴선택 레이어팝업창 -->
	<div class="popup06 popup_cont">
		<h3 class="ml_10 mt_10">역할 상세 조회</h3>
		<table class="tbl_list01 mt_25" id="menuLayer">
		</table>
		
		<div class="mt_20 tac">
			<input type="button" value="지정" class="btn02_gray mr_10" onclick="fn_auth_menu_set();">
			<input type="button" value="취소" class="btn_cancle btn02_white" onclick="fn_auth_menu_close();">
		</div>
	</div>
</body>
</html>
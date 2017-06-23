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
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>메뉴 관리</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bo/validator/menu_validator.js"></script>
<script type="text/javascript">
	var clickMenuId;
	var menuData;
	
	$(function(){
		$.ajax({
    		type : "POST",
    		url : "./data",
    		dataType:"JSON",
    		success : function(info) {
    			fn_tree_draw(info.data);
    			fn_listener();
			},
			error : function(xhr, status, error) {
				alert("에러발생");
			}
    	});
		
	});
	
	function fn_listener(){
		$('.mtree_cont_l ul>li>span>a').on('click',function(e) {
			e.preventDefault();
			
			var temp_el = $(this).parent().next('ul');
			if ((temp_el).hasClass('on')) {
				temp_el.slideUp();
				temp_el.removeClass('on').addClass('off');		
				$(this).css("background","url('/resources/images/icon_plus.png') no-repeat left center");
			} else if ((temp_el).hasClass('off')) {
				temp_el.slideDown();
				temp_el.removeClass('off').addClass('on');
				$(this).css("background","url('/resources/images/icon_minus.png') no-repeat left center");
			}
		});
	}
	
	function fn_num_listener(){
		$(".onlyNum").keyup(function(){
			$(this).val( $(this).val().replace(/[^0-9]/g,"") );
		});
		$(".onlyNum").keypress(function(){
			$(this).val( $(this).val().replace(/[^0-9]/g,"") );
		});
		$(".onlyNum").blur(function(){
			$(this).val( $(this).val().replace(/[^0-9]/g,"") );
		});
	}
	
	function fn_set_menu(val){
		clickMenuId = val;
    	$("#tableData").empty();
    	
    	if(clickMenuId == '0'){
    		return;
    	}
    	
    	var frm = document.form;
    	frm.menuId.value = val;
    	
    	$.ajax({
    		type : "POST",
    		url : "./detail",
    		dataType:"JSON",
    		data: $('#form').serialize(),
    		success : function(info) {
    			menuData = info.data;
    			fn_menu_draw(info.data);
    			fn_num_listener();
    			$("#tableData").show();
			},
			error : function(xhr, status, error) {
				alert("에러발생");
			}
    	});
	}
	
	function fn_tree_draw(rows){
		$("#tree1").empty();
		var html = "";
		
		html += '<ul class="mtree01 off">';
		for(var i=0;i<rows.length;i++){
			html += '<li>';
			html += '<span><a href="#" onclick=\'fn_set_menu("'+rows[i].menuId+'");\'>'+rows[i].label+'</a></span>';
			html += '<ul class="mtree02 off">';
			for(var j=0;j<rows[i].children.length;j++){
				var jRows = rows[i].children;
				html += '<li>';
				html += '<span><a href="#" onclick=\'fn_set_menu("'+jRows[j].menuId+'");\'>'+jRows[j].label+'</a></span>';
				html += '<ul class="mtree03 off">';
				for(var k=0;k<jRows[j].children.length;k++){
					var kRows = jRows[j].children;
					html += '<li class="lastMenu"><span><a href="#" onclick=\'fn_set_menu("'+kRows[k].menuId+'");\'>'+kRows[k].label+'</a></span></li>';
				}
				html += '</ul>';
				html += '</li>';
			}
			html += '</ul>';
			html += '</li>';
		}
		
		html += '</ul>';
		$("#tree1").append(html);
	}
	
	function fn_menu_draw(menu_data){
		var html = "";
		html += '<div class="mtree_titleWrap">메뉴 상세 조회</div>';
		html += '<div>';
		html += '<div class="mtree_cont_r">';
		html += '<table cellpadding="0" cellspacing="0">';
		html += '<colgroup>';
		html += '<col style="width:167px;">';
		html += '<col>';
		html += '</colgroup>';
		
		html += '<tr>';
		html += '<td><label class="input_label">메뉴번호</label></td>';
		html += '<td><span class="txt_gray_14 ml_8">'+menu_data.menuId+'</span></td>';
		html += '</tr>';
		
		html += '<tr>';
		html += '<td><label class="input_label">메뉴 유형</label></td>';
		html += '<td>';
		html += '<div class="fl bg_input mr_10" style="width:140px;">';
		html += '<select id="menuType" name="menuType">';
		if(menu_data.menuType == 'N'){
			html += "<option value='N' selected='selected'>노드</option>";
			html += "<option value='R'>리프</option>";
		}else{
			html += "<option value='N'>노드</option>";
			html += "<option value='R' selected='selected'>리프</option>";
		}
		html += '</select>';
		html += '</div>';
		html += '</td>';
		html += '</tr>';
		
		html += '<tr>';
		html += '<td><label class="input_label">상위메뉴 명</label></td>';
		html += '<td><span class="txt_gray_14 ml_8">'+menu_data.upperMenuName+'</span></td>';
		html += '</tr>';
		
		html += '<tr>';
		html += '<td><label class="input_label">메뉴 명</label></td>';
		html += '<td><input type="text" id="menuName" name="menuName" style="width:370px;" value="'+menu_data.menuName+'"></td>';
		html += '</tr>';
		
		html += '<tr>';
		html += '<td><label class="input_label">메뉴 순서</label></td>';
		html += '<td><input type="text" class="onlyNum" id="menuOrder" name="menuOrder" style="width:140px;" value="'+menu_data.menuOrder+'" maxlength="2"></td>';
		html += '</tr>';
		
		html += '<tr>';
		html += '<td><label class="input_label">사용 여부</label></td>';
		html += '<td>';
		html += '<div class="fl bg_input mr_10" style="width:140px;">';
		html += '<select id="useYn" name="useYn">';
		if(menu_data.useYn == 'Y'){
			html += "<option value='Y' selected='selected'>사용</option>";
			html += "<option value='N'>미사용</option>";
		}else{
			html += "<option value='Y'>사용</option>";
			html += "<option value='N' selected='selected'>미사용</option>";
		}
		html += '</select>';
		html += '</div>';
		html += '</td>';
		html += '</tr>';
		
		html += '<tr>';
		html += '<td><label class="input_label">수행 URL</label></td>';
		if(menu_data.menuUrl == null || menu_data.menuUrl == ''){
			menu_data.menuUrl = "";
		}
		html += '<td><input type="text" id="menuUrl" name="menuUrl" style="width:510px;" value="'+menu_data.menuUrl+'"></td>';
		html += '</tr>';
		
		html += '<tr style="height:115px;">';
		html += '<td class="input_label_vt"><label class="input_label">메뉴 설명</label></td>';
		if(menu_data.menuDesc == null || menu_data.menuDesc == ''){
			menu_data.menuDesc = "";
		}
		html += '<td><textarea id="menuDesc" name="menuDesc" style="width:510px;height:100px">'+menu_data.menuDesc+'</textarea></td>';
		html += '</tr>';
		
		html += '<tr>';
		html += '<td><label class="input_label">등록자</label></td>';
		html += '<td><span class="txt_gray_14 ml_8">'+menu_data.insUserNameTemp+'</span></td>';
		html += '</tr>';
		
		html += '<tr>';
		html += '<td><label class="input_label">등록일</label></td>';
		html += '<td><span class="txt_gray_14 ml_8">'+menu_data.insDt+'</span></td>';
		html += '</tr>';
		html += '</table>';
		html += '</div>';
		
		html += '<div class="tac mt_10">';
		html += '<input type="button" value="수정" class="btn01_white mr_10" onclick="fn_modRow(); return false;">';
		html += '<input type="button" value="삭제" class="btn01_white mr_10" onclick="fn_del_row(); return false;">';
		html += '<input type="button" value="초기화" class="btn01_white" onclick="fn_reset(); return false;">';
		html += '</div>';
		
		$("#tableData").html(html);
		
		$("#useYn").selectric('refresh');
		$("#menuType").selectric('refresh');
	}
	
	function fn_addMenu(){
		var upperMenuName = '';
		if(!clickMenuId){
			alert("메뉴를 선택해 주세요.");
			return false;
		}
		if(clickMenuId == '0'){
			upperMenuName = "ROOT";
		}
		
		$("#tableData").empty();
		var html = "";
		
		html += '<div class="mtree_titleWrap">메뉴 등록</div>';
		html += '<div>';
		html += '<div class="mtree_cont_r">';
		html += '<table cellpadding="0" cellspacing="0">';
		html += '<colgroup>';
		html += '<col style="width:167px;">';
		html += '<col>';
		html += '</colgroup>';
		
		html += '<tr>';
		html += '<td><label class="input_label">메뉴 유형</label></td>';
		html += '<td>';
		html += '<div class="fl bg_input mr_10" style="width:140px;">';
		html += '<select id="menuType" name="menuType">';
		html += "<option value='N'>노드</option>";
		html += "<option value='R'>리프</option>";
		html += '</select>';
		html += '</div>';
		html += '</td>';
		html += '</tr>';
		
		html += '<tr>';
		html += '<td><label class="input_label">상위메뉴 명</label></td>';
		if(menuData){
			html += '<td><span class="txt_gray_14 ml_8">'+menuData.menuName+'</span></td>';
		}else{
			html += '<td><span class="txt_gray_14 ml_8">'+upperMenuName+'</span></td>';
		}
		
		html += '</tr>';
		
		html += '<tr>';
		html += '<td><label class="input_label">메뉴 명</label></td>';
		html += '<td><input type="text" id="menuName" name="menuName" style="width:370px;"></td>';
		html += '</tr>';

		html += '<tr>';
		html += '<td><label class="input_label">메뉴 순서</label></td>';
		html += '<td><input type="text" class="onlyNum" id="menuOrder" name="menuOrder" style="width:140px;" maxlength="2"></td>';
		html += '</tr>';
		
		html += '<tr>';
		html += '<td><label class="input_label">사용 여부</label></td>';
		html += '<td>';
		html += '<div class="fl bg_input mr_10" style="width:140px;">';
		html += '<select id="useYn" name="useYn">';
		html += "<option value='Y'>사용</option>";
		html += "<option value='N'>미사용</option>";
		html += '</select>';
		html += '</div>';
		html += '</td>';
		html += '</tr>';
		
		html += '<tr>';
		html += '<td><label class="input_label">수행 URL</label></td>';
		html += '<td><input type="text" id="menuUrl" name="menuUrl" style="width:510px;"></td>';
		html += '</tr>';

		html += '<tr style="height:115px;">';
		html += '<td class="input_label_vt"><label class="input_label">메뉴 설명</label></td>';
		html += '<td><textarea id="menuDesc" name="menuDesc" style="width:510px;height:100px"></textarea></td>';
		html += '</tr>';
		html += '</table>';
		html += '</div>';
		
		html += '<div class="tac mt_10">';
		html += '<input type="button" value="등록" class="btn01_white mr_10" onclick="fn_addRow(); return false;">';
		html += '<input type="button" value="초기화" class="btn01_white mr_10" onclick="fn_reset(); return false;">';
		html += '</div>';
		
		$("#tableData").html(html);
		$("#useYn").selectric('refresh');
		$("#menuType").selectric('refresh');
		$("#tableData").show();
		
		fn_num_listener();
	}
	
	function fn_addRow(){
		var frm = document.form;
		var level;
		
		if(menuData){
			level = Number(menuData.menuLevel)+1;			
		}else{
			level = 1;
		}
		
		$('#mode').val("addRow");
		$('#upperMenuId').val(clickMenuId);
		$('#menuLevel').val(level);
		var postData = $('#form').serialize();
		
		if(!fn_menu_add_validator(frm)){
			return;
		}
		
		if(confirm("입력한 내용의 메뉴를 등록 하시겠습니까?")){
			$.ajax({
	    		type : "POST",
	    		url : "./proc",
	    		dataType:"JSON",
	    		data: postData,
	    		success : function(info) {
	    			alert("메뉴가 정상적으로 등록 되었습니다.");
	    			fn_list();
				},
				error : function(xhr, status, error) {
					alert("에러발생");
				}
	    	});
		}
		
	}
	
	function fn_modRow(){
		var frm = document.form;
		$('#mode').val("modRow");
		var postData = $('#form').serialize();
		
		if(!fn_menu_add_validator(frm)){
			return;
		}
		
		if(confirm("입력한 내용의 메뉴를 수정 하시겠습니까?")){
			$.ajax({
	    		type : "POST",
	    		url : "./proc",
	    		dataType:"JSON",
	    		data: postData,
	    		success : function(info) {
	    			alert("메뉴가 정상적으로 수정 되었습니다.");
	    			fn_list();
				},
				error : function(xhr, status, error) {
					alert("에러발생");
				}
	    	});
		}
	}
	
	function fn_del_row(){
		var postData = $('#form').serialize();
		
		if(confirm("해당 메뉴를 삭제 하시겠습니까?\n\n메뉴 삭제 시 하위메뉴도 같이 삭제 됩니다.")){
			$.ajax({
	    		type : "POST",
	    		url : "./removeMenun",
	    		dataType:"JSON",
	    		data: postData,
	    		success : function(info) {
	    			alert("메뉴가 정상적으로 수정 되었습니다.");
	    			fn_list();
				},
				error : function(xhr, status, error) {
					alert("에러발생");
				}
	    	});
		}
		
	}
	
	function fn_list(){
		var frm = document.form;
		frm.action = "./list";
		frm.submit();
	}
	
	function fn_reset(){
		$("#form").each(function() {  
			this.reset();  
		});
		
		$('select').selectric('refresh');
	}
</script>

</head>
<body>
	
	<div class="content ml_40">
		<div class="cont_title">
			<h2 class="ml_10">메뉴 관리</h2>
		</div>
		<h3 class="ml_10 mt_20">메뉴 트리</h3>
		
		<div class="clearfix mt_20" style="height:760px;">
			<div class="inputWrap05_1 mr_20">
				<div class="mtree_titleWrap tar">
					<button class="btn02_gray mr_5" onclick="fn_addMenu();">등록</button>
				</div>
				<div class="mtree_cont_l" id="tree1">
					
				</div>
			</div>
			
			<form id="form" name="form" method="post">
			<input type="hidden" name="upperMenuId" id="upperMenuId">
			<input type="hidden" name="menuId" id="menuId">
			<input type="hidden" name="menuLevel" id="menuLevel">
			<input type="hidden" name="mode" id="mode">
			<div class="inputWrap05_2" id="tableData" style="display: none;">
			</div>
			</form>
		</div>
	</div>
</body>
</html>

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
<title>접속 IP 관리</title>
<script type="text/javascript">
	var clickMenuId;
	var menuData;

	/*
	$(function(){
		$.ajax({
    		type : "POST",
    		url : "./data",
    		dataType:"JSON",
    		success : function(info) {
    			fn_tree_draw(info.data);
			},
			error : function(xhr, status, error) {
				alert("에러발생");
			}
    	});
		
	});
	*/
	function fn_tree_draw(rows){
		var $tree = $("#tree1");
		
		$tree.tree({
	        data: rows,
	        onCreateLi: function(node, $li) {
	            // Add 'icon' span before title
	            $li.find('.jqtree-title').before('<span class="icon"></span>');
	        },
	        autoOpen: true,
	        usecontextmenu: true,
	        onCanSelectNode: function(node){
	        	clickMenuId = node.menuId;
	        	$("#tableData").empty();
	        	
	        	if(clickMenuId == '0'){
	        		return;
	        	}
	        	
	        	var frm = document.form;
	        	frm.menuId.value = node.menuId;
	        	
	        	$.ajax({
	        		type : "POST",
	        		url : "./detail",
	        		dataType:"JSON",
	        		data: $('#form').serialize(),
	        		success : function(info) {
	        			menuData = info.data;
	        			fn_menu_draw(info.data);
	        			$("#tableData").show();
	    			},
	    			error : function(xhr, status, error) {
	    				alert("에러발생");
	    			}
	        	});
	        }
	    });
	}
	
	function fn_menu_draw(menu_data){
		var html = "";
		
		html += "<tr>";
		html += "<th>메뉴 번호</th>";
		html += "<td colspan='3'>"+menu_data.menuId+"</td>";
		html += "</tr>";
		html += "<tr>";
		html += "<th>메뉴 유형</th>";
		html += "<td colspan='3'>";
		html += "<select id='menuType' name='menuType'>";
		if(menu_data.menuType == 'N'){
			html += "<option value='N' selected='selected'>노드</option>";
			html += "<option value='R'>리프</option>";
		}else{
			html += "<option value='N'>노드</option>";
			html += "<option value='R' selected='selected'>리프</option>";
		}
		html += "</select>";
		html += "</td>";
		html += "</tr>";
		html += "<tr>";
		html += "<th>상위 메뉴 명</th>";
		html += "<td colspan='3'>"+menu_data.upperMenuName+"</td>";
		html += "</tr>";
		html += "<tr>";
		html += "<th>메뉴 명</th>";
		html += "<td colspan='3'><input type='text' name='menuName' id='menuName' value='"+menu_data.menuName+"'></td>";
		html += "</tr>";
		html += "<tr>";
		html += "<th>메뉴 순서</th>";
		html += "<td colspan='3'><input type='text' name='menuOrder' id='menuOrder' value='"+menu_data.menuOrder+"'></td>";
		html += "</tr>";
		html += "<tr>";
		html += "<th>사용 여부</th>";
		html += "<td colspan='3'>";
		html += "<select id='useYn' name='useYn'>";
		if(menu_data.useYn == 'Y'){
			html += "<option value='Y' selected='selected'>사용</option>";
			html += "<option value='N'>미사용</option>";
		}else{
			html += "<option value='Y'>사용</option>";
			html += "<option value='N' selected='selected'>미사용</option>";
		}
		html += "</select>";
		html += "</td>";
		html += "</tr>";
		html += "<tr>";
		html += "<th>수행 URL</th>";
		html += "<td colspan='3'><input type='text' name='menuUrl' id='menuUrl' value='"+menu_data.menuUrl+"'></td>";
		html += "</tr>";
		html += "<tr>";
		html += "<th>메뉴 설명</th>";
		html += "<td colspan='3'><textarea name='menuDesc' id='menuDesc'>"+menu_data.menuDesc+"</textarea></td>";
		html += "</tr>";
		html += "<tr>";
		html += "<th>등록자</th>";
		html += "<td>"+menu_data.insUserName+"</td>";
		html += "<th>등록 일시</th>";
		html += "<td>"+menu_data.insDt+"</td>";
		html += "</tr>";
		$("#tableData").html(html);
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
		
		html += "<tr>";
		html += "<th>메뉴 유형</th>";
		html += "<td colspan='3'>";
		html += "<select id='menuType' name='menuType'>";
		html += "<option value='N'>노드</option>";
		html += "<option value='R'>리프</option>";
		html += "</select>";
		html += "</td>";
		html += "</tr>";
		html += "<tr>";
		html += "<th>상위 메뉴 명</th>";
		html += "<td colspan='3'>"+menuData.menuName+"</td>";
		html += "</tr>";
		html += "<tr>";
		html += "<th>메뉴 명</th>";
		html += "<td colspan='3'><input type='text' name='menuName' id='menuName'></td>";
		html += "</tr>";
		html += "<tr>";
		html += "<th>메뉴 순서</th>";
		html += "<td colspan='3'><input type='text' name='menuOrder' id='menuOrder'></td>";
		html += "</tr>";
		html += "<tr>";
		html += "<th>사용 여부</th>";
		html += "<td colspan='3'>";
		html += "<select id='useYn' name='useYn'>";
		html += "<option value='Y'>사용</option>";
		html += "<option value='N'>미사용</option>";
		html += "</select>";
		html += "</td>";
		html += "</tr>";
		html += "<tr>";
		html += "<th>수행 URL</th>";
		html += "<td colspan='3'><input type='text' name='menuUrl' id='menuUrl'></td>";
		html += "</tr>";
		html += "<tr>";
		html += "<th>메뉴 설명</th>";
		html += "<td colspan='3'><textarea name='menuDesc' id='menuDesc'></textarea></td>";
		html += "</tr>";
		html += "<tr>";
		html += "<td colspan='4'><input type='button' value='등록' onclick='fn_addRow(); return false;'><input type='button' value='취소'></td>";
		html += "</tr>";
		$("#tableData").html(html);
		$("#tableData").show();
	}
	
	function fn_addRow(){
		var frm = document.frm;
    	frm.action = "./addRow";
    	frm.submit();
	}
	
	function menuLevelSet(){
		if(clickMenuId == '0'){
			
		}
	}
</script>

</head>
<body>
	<h1 class="title mb0"> ■ 접속 IP 관리</h1>
	<h2 class="sub-title">조회</h2>
	<table>
		<tr>
			<td>
			    <input type="button" value="등록" onclick="fn_addRow(); return false;">
			    <input type="button" value="삭제">
			</td>
		</tr>
	</table>

	<form id="frm" name="frm" class="form-inline" method="post">
		<div class="white-box">
			<div class="container">
				<div class="row">
					<div class="form-group">
						<label for="notiName" class="control-label">· 접속 IP명</label> <input name="notiName" class="form-control ml10" value="<c:out value="${testSearchTO.notiName}" />"/>
					</div>
					<div class="form-group ml25">
						<label for="notiYn" class="control-label">· 사용 여부</label> 
						<select name="notiYn" class="form-control input-sm ml10">
							<option value="" <c:if test="${testSearchTO.notiYn eq ''}">selected="selected"</c:if>>전체</option>
							<option value="Y" <c:if test="${testSearchTO.notiYn eq 'Y'}">selected="selected"</c:if>>사용</option>
							<option value="N" <c:if test="${testSearchTO.notiYn eq 'N'}">selected="selected"</c:if>>미사용</option>
						</select>
					</div>
				</div>
			</div>
		</div>
		<div class="row mt10 mb10 ml25">
			<div class="col-xs-offset-4 col-xs-4 col-sm-offset-5 col-sm-4">
				<button class="btn btn-default search" onclick="fn_search(); return false;">조회</button>
				<button class="btn btn-default search" onclick="fn_addRow(); return false;">등록</button>
			</div>
		</div>

		<div id="constrainer">
			<div class="list-scroll-table">
				<c:set var="numColspan" value="6" />
				<table id="widget-grid" border="1">
					<colgroup>
						<col width="10%"/>
                        <col width="15%"/>
                        <col width="20%"/>
                        <col width="15%"/>
                        <col width="10%"/>
                        <col width="10%"/>
                        <col width="10%"/>
                        <col width="10%"/>
					</colgroup>
					<thead>
						<tr>
							<th><input type="checkbox" name="allCheck" id="allCheck"></th>
							<th>IP ID</th>
							<th>접속 IP명</th>
							<th>IP 주소</th>
							<th>등록일시</th>
							<th>등록자</th>
						</tr>
                    </thead>
                    <tbody>
                    	<c:choose>
                    		<c:when test="${fn:length(list) > 0}">
                    			<c:forEach items="${list}" var="result" varStatus="status">
                    				<tr>
			                    		<td><input type="checkbox" name="check" id="check_${status.count}"></td>
			                    		<td><c:out value="${result.sysAccIpId}" /></td>
			                    		<td><a href="#" onclick="fn_detail('<c:out value="${result.sysAccIpId}" />'); return false;"><c:out value="${result.sysAccIpNm}" /></a></td>
			                    		<td><c:out value="${result.ipAdr}" /></td>
			                    		<td><c:out value="${result.inpDtt}" /></td>
			                    		<td><c:out value="${result.inprNm}" /></td>
			                    	</tr>
                    			</c:forEach>
                    		</c:when>
                    		<c:otherwise>
                    			<tr>
		                    		<td colspan="${numColspan}">자료가 없습니다.</td>
		                    	</tr>
                    		</c:otherwise>
                    	</c:choose>
                    </tbody>
				</table>
				<div id="pager"></div>
			</div>
		</div>
	</form>
</body>
</html>
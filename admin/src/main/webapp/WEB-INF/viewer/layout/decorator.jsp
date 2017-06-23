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
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <title><sitemesh:write property="title"/></title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="context-path" content="${pageContext.request.contextPath}" >
        <meta name="viewport" content="width=device-width, initial-scale=1,user-scalable=no">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jqtree.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery-ui.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/swiper.min.css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bo/jquery-1.11.0.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bo/jquery.serializejson.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bo/jquery-ui.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bo/jquery.form.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bo/tree.jquery.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bo/commonUtil.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bo/jqTreeContextMenu.js"></script>
        
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bo/jquery.selectric.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bo/layout.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bo/moment.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bo/swiper.min.js"></script>
    <sitemesh:write property='head'/>
    
    <sec:authorize access="isAuthenticated()">
    	<sec:authentication var="loginUserNameTemp" property="principal.userNameTemp"/>
        <sec:authentication var="loginUserName" property="principal.username"/>
        <sec:authentication var="loginUserSeq" property="principal.userseq"/>
        <sec:authentication var="userMenu" property="principal.authMenuList"/>
        <sec:authentication var="pageUri" property="principal.pageUri"/>
        <sec:authentication var="upperMenuId" property="principal.upperMenuId"/>
        <sec:authentication var="menuId" property="principal.menuId"/>
        <sec:authentication var="pageNav" property="principal.pageNav"/>
        <sec:authentication var="lastLoginDtt" property="principal.lastLoginDtt"/>
    </sec:authorize>
    
<script type="text/javascript">
	var str = '${upperMenuId}';
	var context = '${pageContext.request.contextPath}';

	$(document).ready(function(){
		
	});
	
	
	function fn_page_move(url){
		var frm = document.moveForm;
		frm.action = url;
		frm.submit();
	}
	
	function fn_move_user(userId){
		var frm = document.moveForm;
		frm.userSeq.value = userId;
		frm.mode.value = "U";
		frm.action = context + "/office/user/modRow";
		frm.submit();
	}
	
	function goMain(){
		var frm = document.moveForm;
		frm.action = context + "/office/main";
		frm.submit();
	}
</script>
</head>
<body>
	<form id="moveForm" name="moveForm" method="post">
		<input type="hidden" name="userSeq">
		<input type="hidden" name="mode">
	</form>
    <!-- header -->
    <div class="wrap">
    	<div class="aside">
			<h1><a href="#" onclick="goMain(); return false;"><img src="${pageContext.request.contextPath}/resources/images/logo.png" alt="MIDAS"></a></h1>
			
			<div id="nav" class="nav">
				<ul class="dep1Menu">
					<c:set var="cls" value="mn1" />
					<c:forEach items="${userMenu}" var="result" varStatus="status">
					<c:choose>
						<c:when test="${result.menuName eq '상품등록'}">
							<c:set var="cls" value="mn1" />
						</c:when>
						<c:when test="${result.menuName eq '제휴사성과'}">
							<c:set var="cls" value="mn2" />
						</c:when>
						<c:when test="${result.menuName eq '정산'}">
							<c:set var="cls" value="mn3" />
						</c:when>
						<c:when test="${result.menuName eq '시스템관리'}">
							<c:set var="cls" value="mn4" />
						</c:when>
						<c:when test="${result.menuName eq '설정관리'}">
							<c:set var="cls" value="mn5" />
						</c:when>
					</c:choose>
					<li>
						<c:choose>
							<c:when test="${upperMenuId eq result.menuId}">
						<a href="#" class="<c:out value="${cls}" /> on"><c:out value="${result.menuName}" /></a>	
							</c:when>
							<c:otherwise>
						<a href="#" class="<c:out value="${cls}" />"><c:out value="${result.menuName}" /></a>	
							</c:otherwise>
						</c:choose>
						
						<c:if test="${fn:length(result.children) > 0}">
						<ul class="dep2Menu">
							<c:forEach items="${result.children}" var="info" varStatus="status">
							<c:choose>
								<c:when test="${menuId eq info.menuId}">
							<li><a href="#" class="on" onclick="fn_page_move('<c:out value="${info.menuUrl}" />')"><c:out value="${info.menuName}" /></a></li>	
								</c:when>
								<c:otherwise>
							<li><a href="#" onclick="fn_page_move('<c:out value="${info.menuUrl}" />')"><c:out value="${info.menuName}" /></a></li>	
								</c:otherwise>
							</c:choose>
							</c:forEach>
						</ul>
						</c:if>
					</li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<div class="container">
	    	<div class="util">
				<div class="location">
					<span id="location"><c:out value="${pageNav}" /></span> 
				</div>
				
				<div class="login_info">
					<span class="remain_time">마지막 로그인 일시 : <c:out value="${lastLoginDtt}" /></span>
					<span class="user_name"><span class="fnt_blue" onclick="fn_move_user('<c:out value="${loginUserSeq}" />');" style="cursor: pointer;"><c:out value="${loginUserNameTemp}" /></span> 님 안녕하세요</span>
					<a href="${pageContext.request.contextPath}/logoutCustom" title="로그아웃" class="btn_logout">로그아웃</a>
				</div>
			</div>
			<sitemesh:write property='body'/>
	    </div>
    </div>
</body>
</html>

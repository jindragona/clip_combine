<!-- 
   MIDAS version 1.0
 
   Copyright ⓒ 2017 kt corp. All rights reserved.
 
   This is a proprietary software of kt corp, and you may not use this file except in
   compliance with license agreement with kt corp. Any redistribution or use of this
   software, with or without modification shall be strictly prohibited without prior written
   approval of kt corp, and the copyright notice above does not evidence any actual or
   intended publication of such software.
-->
<%@ page language="java" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="keywords" content="">
<meta name="description" lang="ko" content="">
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0">
<title>MIDAS</title> 
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery-ui.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bo/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bo/jquery-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bo/moment.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bo/jquery.selectric.js"></script>
<script type="text/javascript">
	var context = '${pageContext.request.contextPath}';
	
	function goMain(){
		var frm = document.moveForm;
		frm.action = context + "/office/main";
		frm.submit();
	}
</script>
</head>

<body>
<div class="wrap">
	<form id="form" name="form" method="post">
	</form>
	<div class="error">
		<div class="logo">
			<h1><a href="#" onclick="goMain(); return false;"><img src="${pageContext.request.contextPath}/resources/images/logo02.png" alt="MIDAS"></a></h1>
		</div>
		<div class="errorWrap e01">
			<p class="emsg1">페이지 관리 권한이 없습니다.</p>
			<p class="emsg3">관리자에게 문의하세요.</p>
		</div>
		<div class="errorBtnWrap">
			<button class="mr_10" onclick="goMain(); return false;">메인으로</button>
		</div>

	</div>
</div>
</body>
</html>

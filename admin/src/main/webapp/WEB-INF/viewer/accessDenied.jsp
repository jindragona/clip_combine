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
<!DOCTYPE html>
<html>
<head>
    <title><sitemesh:write property="title"/></title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1,user-scalable=no">
    <meta name="context-path" content="${pageContext.request.contextPath}" >
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin.css">
    <title>Back Office</title>
    <script>
    	$(document).ready(function () {
    		$('#nav').hide();
    		$('#contents').css('padding', 0);
    		$('#footer').css('padding-left', '10px');
    		
    		$('button.log-in-btn').on('click', function () {
    			document.location.href = '${pageContext.request.contextPath}/logout';
    		});
    	});
    	
    </script>
</head>
<body>
	<div style="text-align: center; padding-top: 60px; font-size: 16px;">
		<p>사용권한이 없습니다.</p>
		<p>사용이 가능한계정으로 접근하여 주시기 바랍니다.</p>
		<br/>
		<button type="button" class="btn btn-default log-in-btn">로그인 페이지 가기</button>
	</div>
	
</body>
</html>
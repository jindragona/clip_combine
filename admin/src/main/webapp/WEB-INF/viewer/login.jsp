<!-- 
   MIDAS version 1.0
 
   Copyright ⓒ 2017 kt corp. All rights reserved.
 
   This is a proprietary software of kt corp, and you may not use this file except in
   compliance with license agreement with kt corp. Any redistribution or use of this
   software, with or without modification shall be strictly prohibited without prior written
   approval of kt corp, and the copyright notice above does not evidence any actual or
   intended publication of such software.
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE>
<html id="login_html">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1,user-scalable=no">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/common.css">
        <title>Login</title>
        <script src="${pageContext.request.contextPath}/resources/js/bo/jquery-1.11.0.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/bo/jquery-ui.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bo/jquery.selectric.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/bo/jquery.isloading.min.js"></script>        
        <script src="${pageContext.request.contextPath}/resources/js/bo/commonUtil.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bo/layout.js"></script>
        <script type="text/javascript">
        	var contextPath = '${pageContext.request.contextPath}';
        	
        	$(document).ready(function () {
        		$("#auth_phone").attr("type","hidden");
    			$("#auth_no").attr("type","hidden");
    			$("#authReqBtn").attr("type","hidden");
        		$("#timer").hide();
    			
        		$.ajax({
                    url: contextPath + '/checkSession',
                    type: 'post',
                    success: function (data) {
                    	if(data.result == '0001'){
                    		fn_move_main();
                    	}
                    },
                    error : function(xhr, status, error) {
                    	console.log(error);
                    }
                });
            });
        	
    		
        	function fn_login(){
        		if(validate("login")){
        			var postData = $('#loginForm').serialize();
            		console.log($("#ignoreDuplicated").val());
            		
                    $.ajax({
                        url: contextPath + '/gt/loginAction',
                        type: 'post',
                        data: postData,
                        beforeSend: function () {
                            $.isLoading({text: '<div style="width: 100%; border: 1px solid black; text-align: center;">처리중..</div>', position: 'inside'});
                        },
                        success: function (data) {
                        	if(data.result == '8000'){
                        		alert(data.message);
                        		$("#login_id").val('');
                        		$("#login_pass").val('');
                        	} else if(data.result == '0000') {
                        		fn_login_success_move();
                        	} else if(data.result == '0100') {
                        		fn_pwd_mdf_move();
                        	} else if(data.result == '8001'){
                        		alert("비밀 번호 5회 실패로 인해 로그인이 제한됩니다.\n\n관리자에게 문의 하시기 바랍니다.");
                        		location.href = '/logoutCustom';
                        	}else if(data.result == '9000'){
                        		if(confirm("이미 다른 브라우져에서 사용중입니다.\n\n기존 연결을 끊고 로그인하시겠습니까?")){
                        			var frm = document.loginForm;
                        			frm.ignoreDuplicated.value = "Y";
                        			fn_login();
                        		}
                        	}else if(data.result == '8003'){
                        		alert("사용이 중단된 사용자 정보입니다.\n\n관리자에게 문의 하시기 바랍니다.");
                        		location.href = '/logoutCustom';
                        	}else if(data.result == '8004'){
                        		alert("등록되지 않은 IP 사용자입니다.\n\n관리자에게 문의 하시기 바랍니다.");
                        		location.href = '/logoutCustom';
                        	}else if(data.result == '8006'){
                        		alert("비밀번호 만료일이 지났습니다.\n\n해당 사이트에서 비밀 번호를 변경 후 다시 로그인 해 주시기 바랍니다.");
                        		$("#login_id").val('');
                        		$("#login_pass").val('');
                        		fn_pwd_mod_pop();
                        	}else if(data.result == '8007'){
                        		alert("LDAP 인증에 실패하였습니다.\n\n관리자에게 문의하시기 바랍니다.");
                        		location.href = '/logoutCustom';
                        	}
                        },
                        error : function(xhr, status, error) {
                        	console.log(error);
                        },
                        complete: function () {
                            $.isLoading('hide');
                        }
                    });
        		}
        	}
        	
        	function validate(str){
        		if( $("#login_id").val() == null || $.trim($("#login_id").val()) == "" ){
            		alert("사용자 ID를 입력해 주세요.");
            		return false;
            	}
        		
        		if( $("#login_pass").val() == null || $.trim($("#login_pass").val()) == "" ){
            		alert("사용자 PASSWORD를 입력해 주세요.");
            		return false;
            	}
        		
        		
        		return true;
        	}
        	
        	function fn_login_success_move(){
        		var frm = document.loginForm;
        		frm.action = "/office/main";
        		frm.submit();
        	}
        	
        	function fn_pwd_mdf_move(){
        		var frm = document.loginForm;
        		frm.action = "./office/pwdMdf";
        		frm.submit();
        	}
        	
        	function fn_move_main(){
        		var frm = document.loginForm;
        		frm.action = "./office/main";
        		frm.submit();
        	}
        	
        	function fn_pwd_mod_pop(){
        		var url = "http://nsso.kt.com/ssokt/pwdTab.html" 
        		winOpen(url, "520", "590", "N");
        	}
        </script>
    </head>
    <body id="login_body" >
    	<div class="wrap">
			<!-- 로그인 영역 -->
			<div class="login clearfix">
				<div class="logo">				
					<h1><img src="${pageContext.request.contextPath}/resources/images/logo_login.png" alt="로그인" /></h1>
				</div>
				<form id="loginForm" name="loginForm" method="post" class="form" role="form" autocomplete="off">	
				<input type="hidden" id="ignoreDuplicated" name="ignoreDuplicated" />
				<input type="hidden" name="authPhone" id="authPhone" />
				<input type="hidden" name="authStat" id="authStat" value="N" />
				<input type="hidden" name="userId" id="userId" />
				<div class="input_login">
					<h3>MIDAS 로그인</h3>
					<div class="fl mr_10">
						<input type="text" name="login_id" class="form-control input-lg" id="login_id" placeholder="아이디" value="">
                    	<input type="password" name="login_pass" class="form-control input-lg" id="login_pass" placeholder="비밀번호" value="" onkeypress="if(event.keyCode==13){fn_login(); return false;}">
					</div>
					<div class="fr">
						<input type="button" value="로그인" class="btn_login" onclick="fn_login(); return false;"/>			
					</div>
				</div>
				</form>	
				<table>
					<tr><td><span>  ※Explore 11 또는 구글 크롬을 통해 이용해 주시기 바랍니다.</span></td></tr>
					<tr><td><span>  ※아이디와 비밀번호, 휴대폰번호를 입력한 후 인증요청을 진행하시고 인증번호를 입력하셔야 로그인이 진행됩니다.</span></td></tr>
				</table>
				
				
			</div>
		</div>
    </body>
</html>
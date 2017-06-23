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
<jsp:useBean class="java.util.Date" id="nowDate" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>접속 IP 관리</title>
        <script type="text/javascript">
            jQuery.browser = {}; //msie에 대한 함수가 deprecated 되어 오류가 나는데 해당 코드를 넣어줘야 정상 작동
            (function () {
                jQuery.browser.msie = false;
                jQuery.browser.version = 0;
                if (navigator.userAgent.match(/MSIE ([0-9]+)\./)) {
                    jQuery.browser.msie = true;
                    jQuery.browser.version = RegExp.$1;
                }
            })();
		</script>
		<script type="text/javascript">
            $(document).ready(function(){
            });
            
            function fn_addRow(){
            	var frm = document.frm;
            	frm.useYn.value = $("input:radio[id='noti']").val();
            	var postData = $('#frm').serialize();
            	
            	$.ajax({
            		type : "POST",
            		url : "./proc",
            		dataType:"JSON",
            		data: postData,
            		success : function(data) {
            			if(data.code == '0000'){
            				alert("등록 되었습니다.");
            				fn_list();
            			}else{
            				alert("오류가 발생 하였습니다.");
            				fn_list();
            			}
					},
					error : function(xhr, status, error) {
						alert("에러발생");
					}
            	});
            }
            
            function fn_list(){
            	var frm = document.listForm;
            	frm.action = "./list";
            	frm.submit();
            }
		</script>
    </head>
    <body>
        <h1 class="title" style="margin-bottom: 0;">접속 IP 관리 </h1>
        <form id="frm" name="frm" action="./proc" method="post">
        <c:choose>
        	<c:when test="${id eq 'addRow'}">
        <input type="hidden" name="mode" value="addRow">
        	</c:when>
        	<c:otherwise>
        <input type="hidden" name="mode" value="modRow">
        	</c:otherwise>
        </c:choose>
        <input type="hidden" name="useYn" id="useYn">
        <input type="hidden" name="notiId" id="notiId" value="<c:out value="${detail.notiId}" />">
        <table border="1">
			<tr>
				<th>접속 IP명</th>
				<td><input type="text" name="sysAccIpNm" maxlength="30" value="<c:out value="${detail.sysAccIpNm}" />"></td>
			</tr>
			<tr>
				<th>IP 유형</th>
				<td>
			        <input type="radio" name="ipType" value="1" checked="checked"> 단일
					<input type="radio" name="ipType" value="2"> 범위
				</td>
			</tr>
			<tr>
				<th>접속 IP 주소</th>
                <td><input type="text" name="ipAdr" maxlength="30" value="<c:out value="${detail.ipAdr}" />"></td>
			</tr>
			<tr>
				<th>사용 여부</th>
				<td>
					<c:choose>
			        	<c:when test="${id eq 'addRow'}">
			        <input type="radio" name="noti" id="noti" value="Y" checked="checked"> 사용
					<input type="radio" name="noti" id="noti" value="N"> 비사용
			        	</c:when>
			        	<c:otherwise>
			        <input type="radio" name="noti" id="noti" value="Y" <c:if test="${detail.notiYn eq 'Y' or empty detail.notiYn}">checked="checked"</c:if>> 사용
					<input type="radio" name="noti" id="noti" value="N" <c:if test="${detail.notiYn eq 'N'}">checked="checked"</c:if>> 비사용
			        	</c:otherwise>
			        </c:choose>
					
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<button class="btn btn-default search" onclick="fn_addRow(); return false;">등록</button>
					<button class="btn btn-default search" onclick="fn_list(); return false;">목록</button>
				</td>
			</tr>
        </table>
        </form>
        <form id="listForm" name="listForm" method="post">
        </form>
	</body>
</html>

var img_ext = ["gif", "png", "jpg", "jpeg", "bmp"];

/**
 * Layer 페이징 처리 함수
 * @param totalCnt 전체 ROW 수
 * @param pageSize 전체 페이지 사이즈
 * @param pageNum 현재 페이지
 * @param pageDivId 페이지 Layer
 * @param funcName 사용한 function 명
 * @returns
 */
function makePagingDiv( totalCnt, pageSize, pageNum, pageDivId, funcName){
	//페이징 DIV내용삭제
	$('#'+pageDivId).html("");
	
	var maxPage = Math.ceil(totalCnt / pageSize);			//마지막페이지
	var startPage = Math.ceil(pageNum / 10)*10 - 10 + 1;	//시작페이지
	var endPage = startPage + 9;							//종료페이지
	
	//종료페이지가 마지막페이지를 넘을경우
	if( maxPage < endPage ){
		endPage = maxPage;
	}
	
	if( maxPage == 0 ){
		endPage = 1;
	}

	//첫페이지
	if(1 != pageNum){
		$('#'+pageDivId).append("<a href=\"javascript:"+funcName+"(1);\" class=\"imgPage firstPage\">처음</a>");
	}
	
	//이전 10페이지가 존재할때
	if( pageNum > 10 ){
		$('#'+pageDivId).append("<a href=\"javascript:"+funcName+"("+(startPage-10)+");\" class=\"imgPage prevPage\">이전</a>");
	}
	
	var firstChk = 1;
	//시작페이지부터 종료페이지까이 반복
	for( var i = startPage; i <= endPage; i++ ){
		var active = '';
		
		if( pageNum == i ){
			var str = "<a href=\"javascript:void(0);\" class=\"on a_hover\">"+i+"</a>";
			$('#'+pageDivId).append(str);
		}else {
			$('#'+pageDivId).append("<a href=\"javascript:"+funcName+"("+(i)+");\" class=\"a_hover\">"+i+"</a>");
		}
		
		firstChk++;
	}
	
	//다음10페이지가 존재할때
	if( endPage < maxPage ){
		$('#'+pageDivId).append("<a href=\"javascript:"+funcName+"("+(endPage+1)+");\" class=\"imgPage nextPage\">다음</a>");
		
	}
	
	//마지막페이지
	if(pageNum != maxPage && maxPage > 0){
		$('#'+pageDivId).append("<a href=\"javascript:"+funcName+"("+maxPage+");\" class=\"imgPage lastPage\">마지막</a>");
	}
}

function fc_chk_byte2(obj, max_byte,objname){
	var objStr = obj.value; 
	var byteLength = 0;

	for(var i=0; i < objStr.length; i++){
		var oneChar = escape(objStr.charAt(i));
		if( oneChar.length == 1 )
			byteLength ++;
		else if(oneChar.indexOf("%u") != -1)
			byteLength += 2;
		else if(oneChar.indexOf("%") != -1)
			byteLength += oneChar.length/3;
		else
			console.log("anthor");
	}
	// 전체길이를 초과하면
	if(byteLength > max_byte){		// 입력된 > 맥스
		alert(objname+" "+ max_byte + " 바이트를 초과 입력할수 없습니다. 현재 " + byteLength + "Byte입니다.");
		obj.focus();
		return false;
	}
	return true;
}

function fc_chk_byte3(val, max_byte,objname){
	var objStr = val;
	var byteLength = 0;

	for(var i=0; i < objStr.length; i++){
		var oneChar = escape(objStr.charAt(i));
		if( oneChar.length == 1 )
			byteLength ++;
		else if(oneChar.indexOf("%u") != -1)
			byteLength += 2;
		else if(oneChar.indexOf("%") != -1)
			byteLength += oneChar.length/3;
		else
			console.log("anthor");
	}
	// 전체길이를 초과하면
	if(byteLength > max_byte){		// 입력된 > 맥스
		alert(objname+" "+ max_byte + " 바이트를 초과 입력할수 없습니다. 현재 " + byteLength + "Byte입니다.");
		obj.focus();
		return false;
	}
	return true;
}

//숫자만 입력 가능
//사용방법 onkeyup="javascript:fncDigit(this);"
function fncDigit( obj ) {
var inText = obj.value;
var alpha_num_Str = "0123456789";
for ( var i = 0; inText.length >= i; i++ ) {
   var substr = inText.substring(i, i + 1);
   if (alpha_num_Str.indexOf(substr) < 0) {
       alert("숫자만 입력가능합니다.!");
       obj.value = "";
       obj.focus();
       return false;
   }
}
}

function autoHypenPhone(str){
	str = str.replace(/[^0-9]/g, '');
	var tmp = '';
	if( str.length < 4){
		return str;
	}else if(str.length < 7){
		tmp += str.substr(0, 3);
		tmp += '-';
		tmp += str.substr(3);
		return tmp;
	}else if(str.length < 11){
		tmp += str.substr(0, 3);
		tmp += '-';
		tmp += str.substr(3, 3);
		tmp += '-';
		tmp += str.substr(6);
		return tmp;
	}else{				
		tmp += str.substr(0, 3);
		tmp += '-';
		tmp += str.substr(3, 4);
		tmp += '-';
		tmp += str.substr(7);
		return tmp;
	}
	return str;
}
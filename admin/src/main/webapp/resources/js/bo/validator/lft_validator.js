function fn_base_info_validator(frm){
	if(!frm.compName.value){
		alert("제휴사 정보를 입력해 주세요.");
		frm.compName.focus();
		return false;
	}
	
	if(!frm.lftName.value){
		alert("전단명 정보를 입력해 주세요.");
		frm.lftName.focus();
		return false;
	}
	
	if(!fc_chk_byte2(frm.lftName, 100, "전단명은")){
		return false;
	}
	
	if(!frm.savPoint.value){
		alert("포인트 정보를 입력해 주세요.");
		frm.savPoint.focus();
		return false;
	}
	return true;
}

function fn_img_validator(frm){
	for(var i=1;i<=10;i++){
		var img = $("#saveImgShow_"+i);
		if(!img.attr("src")){
			if($("input:radio[name='lftButtonType"+i+"']:checked").val()){
				alert(i+"번째 이미지가 등록 되지 않았습니다.\n\n이미지를 등록 하고 부가 정보를 입력해 주세요.");
				return false;
			}
		}else{
			if(!$("input:radio[name='lftButtonType"+i+"']:checked").val()){
				alert(i+"번째 이미지가 등록 되었습니다.\n\n"+i+"번째 부가 정보를 입력해 주세요.");
				return false;
			}
			
			if($("input:radio[name='lftButtonType"+i+"']:checked").val() == 'M'){
				if(!$("#clipMove_"+i).val()){
					alert(i+"번째 페이지 URL을 입력해 주시기 바랍니다.");
					return false;
				}
				
				if(!$("#btn_"+i).val()){
					alert(i+"번째 버튼명을 입력해 주시기 바랍니다.");
					return false;
				}
				
				if(!fc_chk_byte3($("#clipMove_"+i).val(), 200, "페이지 URL은")){
					return false;
				}
				
				if(!fc_chk_byte3($("#btn_"+i).val(), 20, "버튼명은")){
					return false;
				}
				
				var moveUrl = $("#clipMove_"+i).val();
				
				if(!moveUrl.match("KTolleh00114://")){
					moveUrl = moveUrl.toLowerCase();
					if(!moveUrl.match("http://") && !moveUrl.match("https://")){
						alert(i+"번째 페이지 URL의 스키마 정보를 입력해 주세요.");
						return false;
					}
				}
			}
		}
	}
	
	return true;
}

function fn_send_way_validator(frm){
	var type = $("input:radio[name='smsType']:checked").val();
	
	if($("input:radio[name='pushType']:checked").val() != 'Y' && type != 'S' && type != 'L' && type != 'M'){
		alert("PUSH 나 문자 발송 중 하나는 선택이 되어야 합니다.");
		return false;
	}
	
	if($("input:radio[name='pushType']:checked").val() == 'Y'){
		if(!frm.pushLftTitle.value){
			alert("PUSH 발송 제목를 입력 해 주세요.");
			frm.pushLftTitle.focus();
			return false;
		}
		
		if(!frm.pushLftCont.value){
			alert("PUSH 발송 문구를 입력 해 주세요.");
			frm.pushLftCont.focus();
			return false;
		}
		
		if(!fc_chk_byte2(form.pushLftTitle, 60, "PUSH 발송 제목은")){
			return false;
		}
		
		if(!fc_chk_byte2(form.pushLftCont, 2000, "PUSH 발송 문구는")){
			return false;
		}
	}
	
	var pushImgFileName = $("#pushFile").val();
	if(pushImgFileName){
		var pushExt = pushImgFileName.split('.').pop().toLowerCase();
		
		if($.inArray(pushExt, img_ext) == -1) {
			alert("PUSH 이미지에 이미지가 아닌 파일을 올리셨습니다.");
			return false;
		}
		
		var pushFileSize = $("#pushFile")[0].files[0].size;
		if(pushFileSize > 1024 * 50){
			alert("PUSH 이미지 용량이 너무 큽니다. \n\n50KB 이내의 이미지로 변경해 주시기 바랍니다.");
			return false;
		}
	}
	
	if(type == 'S' || type == 'L' || type == 'M'){
		if(!frm.smsLftCont.value){
			if(type == 'S'){
				alert("SMS 발송 문구를 입력 해 주세요.");
				frm.smsLftCont.focus();
				return false;
			}else if(type == 'L'){
				alert("LMS 발송 문구를 입력 해 주세요.");
				frm.smsLftCont.focus();
				return false;
			}else{
				alert("MMS 발송 문구를 입력 해 주세요.");
				frm.smsLftCont.focus();
				return false;
			}
		}
		
		if(type == 'S'){
			if(!fc_chk_byte2(form.smsLftCont, 80, "SMS 발송 문구는")){
				return false;
			}
		}else if(type == 'L'){
			if(!fc_chk_byte2(form.smsLftCont, 2000, "LMS 발송 문구는")){
				return false;
			}
		}else{
			if(!fc_chk_byte2(form.smsLftCont, 4000, "MMS 발송 문구는")){
				return false;
			}
		}
	}
	
	var mmsImgFileName = $("#mmsFile").val();
	if(mmsImgFileName){
		var mmsExt = mmsImgFileName.split('.').pop().toLowerCase();
		
		if($.inArray(mmsExt, img_ext) == -1) {
			alert("MMS 이미지에 이미지가 아닌 파일을 올리셨습니다.");
			return false;
		}
		
		var mmsFileSize = $("#mmsFile")[0].files[0].size;
		if(mmsFileSize > 1024 * 50){
			alert("MMS 이미지 용량이 너무 큽니다. \n\n50KB 이내의 이미지로 변경해 주시기 바랍니다.");
			return false;
		}
	}
	
	return true;
}
function fn_noti_add_validator(frm){
	if(!frm.notiName.value){
		alert("공지 명을 입력해 주세요.");
		frm.notiName.focus();
		return false;
	}
	
	if(!fc_chk_byte2(frm.notiName, 40, "공지 명는")){
		return false;
	}
	
	if(!frm.stime.value){
		alert("공지 시작일을 입력해 주세요.");
		frm.stime.focus();
		return false;
	}
	
	if(!frm.etime.value){
		alert("공지 종료일을 입력해 주세요.");
		frm.etime.focus();
		return false;
	}
	
	if(!fc_chk_byte2(frm.notiContent, 2000, "공지 설명은")){
		return false;
	}
	
	return true;
}

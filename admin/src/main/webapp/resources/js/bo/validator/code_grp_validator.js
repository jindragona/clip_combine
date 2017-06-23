function fn_code_grp_add_validator(frm){
	if(!frm.grpCodeId.value){
		alert("코드 그룹 ID 값을 입력해 주세요.");
		frm.grpCodeId.focus();
		return false;
	}
	
	if(!fc_chk_byte2(frm.grpCodeId, 6, "코드 그룹 ID는")){
		return false;
	}
	
	if(!frm.grpCodeName.value){
		alert("코드 그룹 명을 입력해 주세요.");
		frm.grpCodeName.focus();
		return false;
	}
	
	if(!fc_chk_byte2(frm.grpCodeName, 40, "코드 그룹 명은")){
		return false;
	}
	
	return true;
}

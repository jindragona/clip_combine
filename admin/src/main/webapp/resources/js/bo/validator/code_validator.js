function fn_code_add_validator(frm){
	if(!frm.codeId.value){
		alert("코드ID 값을 입력해 주세요.");
		frm.codeId.focus();
		return false;
	}
	
	if(!fc_chk_byte2(frm.codeId, 20, "코드 ID는")){
		return false;
	}
	
	if(!frm.codeName.value){
		alert("코드 명을 입력해 주세요.");
		frm.codeName.focus();
		return false;
	}
	
	if(!fc_chk_byte2(frm.codeName, 40, "코드 명은")){
		return false;
	}
	
	if(!frm.codeOdr.value){
		alert("코드 순서를 입력해 주세요.");
		frm.codeOdr.focus();
		return false;
	}
	
	return true;
}

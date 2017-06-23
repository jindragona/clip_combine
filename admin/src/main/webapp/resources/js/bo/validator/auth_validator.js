function fn_auth_add_validator(frm){
	
	if(!frm.rolName.value){
		alert("역할명을 입력해 주세요.");
		frm.rolName.focus();
		return false;
	}
	
	if(!fc_chk_byte2(frm.rolName, 40, "역할명은")){
		frm.rolName.focus();
		return false;
	}
	
	if(!fc_chk_byte2(frm.rolDesc, 200, "역할설명은")){
		frm.rolDesc.focus();
		return false;
	}
    
	return true;
}

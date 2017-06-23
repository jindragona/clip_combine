function fn_menu_add_validator(frm){
	if(!frm.menuName.value){
		alert("메뉴 명을 입력해 주세요.");
		frm.menuName.focus();
		return false;
	}
	
	if(!fc_chk_byte2(frm.menuName, 40, "메뉴명은")){
		return false;
	}
	
	if(!frm.menuOrder.value){
		alert("메뉴 순서를 입력해 주세요.");
		frm.menuOrder.focus();
		return false;
	}
	
	if(frm.menuUrl.value){
		if(!fc_chk_byte2(frm.menuUrl, 200, "수행 URL은")){
			return false;
		}
	}
	
	if(frm.menuDesc.value){
		if(!fc_chk_byte2(frm.menuDesc, 2000, "메뉴 설명")){
			return false;
		}
	}
	
	return true;
}

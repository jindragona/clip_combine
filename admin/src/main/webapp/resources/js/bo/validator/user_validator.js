function fn_user_add_validator(frm){
	
	var userId = frm.userId.value;
    var userPwd = frm.userPwd.value;
    var userPwdConfirm = frm.userPwdConfirm.value;
    
	if(!frm.userName.value){
		alert("사용자명을 입력해 주세요.");
		frm.userName.focus();
		return false;
	}
	
	if(!fc_chk_byte2(frm.userName, 20, "사용자명은")){
		frm.userName.focus();
		return false;
	}
	
	if(!fc_chk_byte2(frm.hofcName, 30, "본부명은")){
		frm.hofcName.focus();
		return false;
	}
	
	if(!fc_chk_byte2(frm.teamName, 30, "팀명은")){
		frm.teamName.focus();
		return false;
	}
	
	if(!frm.userId.value){
		alert("사용자 ID을 입력해 주세요.");
		frm.userId.focus();
		return false;
	}
	
	if(userId.length < 6){
		alert("사용자 로그인 ID 는 6자리 이상 입력해 주세요.");
		frm.userId.focus();
		return false;
	}
	
	if(!fc_chk_byte2(frm.userId, 20, "USER ID 는")){
		frm.userId.focus();
		return false;
	}
	
	var check = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
	if(check.test(frm.userId.value)){
		alert("USER ID 값에 한글이 들어 갈 수 없습니다.");
		frm.userId.focus();
		return false;
	}
	
	var pattern = /\s/g;
	if(pattern.test(frm.userId.value)){
		alert("USER ID 값에 공백이 들어 갈 수 없습니다.");
		frm.userId.focus();
		return false;
	}
	
	if(!frm.userPwd.value){
		alert("사용자 비밀번호를 입력해 주세요.");
		frm.userPwd.focus();
		return false;
	}
	
	if(!frm.userPwd.value){
		alert("사용자 비밀번호를 입력해 주세요.");
		frm.userPwd.focus();
		return false;
	}
	
	if (userPwd != userPwdConfirm) {
		alert("입력한 두 개의 비밀번호가 서로  일치하지 않습니다.");
		frm.userPwd.focus();
		return false;
	}
	
	if(!/^[a-zA-Z0-9!@#$%^&*()?_~]{10,20}$/.test(userPwd)){ 
        alert("비밀번호는 숫자, 영문, 특수문자 조합으로 10~20자리를 사용해야 합니다.");
        frm.userPwd.focus();
        return false;
    }
	
	var chk = 0;
    if(userPwd.search(/[0-9]/g) != -1 ){
    	chk ++;
    }
    
    if(userPwd.search(/[a-z]/ig)  != -1 ){
    	chk ++;
    }
    
    if(userPwd.search(/[!@#$%^&*()?_~]/g)  != -1  ){
    	chk ++;
    }
    
    if(chk < 3){
        alert("비밀번호는 숫자, 영문, 특수문자를 세가지이상 혼용하여야 합니다."); 
        frm.userPwd.focus();
        return false;
    }
    
    if(/(\w)\1\1\1/.test(userPwd) || isContinuedValue(userPwd)){
        alert("비밀번호에 4자 이상의 연속 또는 반복 문자 및 숫자를 사용하실 수 없습니다."); 
        frm.userPwd.focus();
        return false;
    }
    
    if(userPwd.search(userId)>-1){
        alert("ID가 포함된 비밀번호는 사용하실 수 없습니다."); 
        frm.userPwd.focus();
        return false;
    }
    
    frm.phoneNum.value = frm.phone1.value + "-" + frm.phone2.value + "-" + frm.phone3.value;
    
    var  phone1 = frm.phone1.value;	
    var  phone2 = frm.phone2.value;	
    var  phone3 = frm.phone3.value;
    
	if(phone2.length < 3 ){
        alert('가운데 자리 핸드폰번호는 3자 이상 입력해주세요.');
        frm.phone2.focus();
        return false;
    }
	
	if(phone3.length != 4 ){
		alert('마지막 자리 핸드폰번호는 4자를  입력해주세요.');
		frm.phone3.focus();
		return false;
	}
    
	return true;
}

function fn_user_mod_validator(frm){
	var userId = frm.userId.value;
    var userOldPwd = frm.userOldPwd.value;
    var userNewPwd = frm.userNewPwd.value;
    var userNewPwdConfirm = frm.userNewPwdConfirm.value;
	
	if(!fc_chk_byte2(frm.hofcName, 30, "본부명은")){
		frm.hofcName.focus();
		return false;
	}
	
	if(!fc_chk_byte2(frm.teamName, 30, "팀명은")){
		frm.teamName.focus();
		return false;
	}
	
	if(!frm.userOldPwd.value){
		alert("기존 사용자 비밀번호를 입력해 주세요.");
		frm.userOldPwd.focus();
		return false;
	}
	
	if(!frm.userNewPwd.value){
		alert("신규 사용자 비밀번호를 입력해 주세요.");
		frm.userNewPwd.focus();
		return false;
	}
	
	if (userNewPwd != userNewPwdConfirm) {
		alert("입력한 두 개의 비밀번호가 서로  일치하지 않습니다.");
		frm.userNewPwd.focus();
		return false;
	}
	
	if(!/^[a-zA-Z0-9!@#$%^&*()?_~]{10,20}$/.test(userNewPwd)){ 
        alert("비밀번호는 숫자, 영문, 특수문자 조합으로 10~20자리를 사용해야 합니다.");
        frm.userNewPwd.focus();
        return false;
    }
	
	var chk = 0;
    if(userNewPwd.search(/[0-9]/g) != -1 ){
    	chk ++;
    }
    
    if(userNewPwd.search(/[a-z]/ig)  != -1 ){
    	chk ++;
    }
    
    if(userNewPwd.search(/[!@#$%^&*()?_~]/g)  != -1  ){
    	chk ++;
    }
    
    if(chk < 3){
        alert("비밀번호는 숫자, 영문, 특수문자를 세가지이상 혼용하여야 합니다."); 
        frm.userNewPwd.focus();
        return false;
    }
    
    if(/(\w)\1\1\1/.test(userNewPwd) || isContinuedValue(userNewPwd)){
        alert("비밀번호에 4자 이상의 연속 또는 반복 문자 및 숫자를 사용하실 수 없습니다."); 
        frm.userNewPwd.focus();
        return false;
    }
    
    if(userNewPwd.search(userId)>-1){
        alert("ID가 포함된 비밀번호는 사용하실 수 없습니다."); 
        frm.userNewPwd.focus();
        return false;
    }
    
    frm.phoneNum.value = frm.phone1.value + "-" + frm.phone2.value + "-" + frm.phone3.value;
    
    var  phone1 = frm.phone1.value;	
    var  phone2 = frm.phone2.value;	
    var  phone3 = frm.phone3.value;
    
	if(phone2.length < 3 ){
        alert('가운데 자리 핸드폰번호는 3자 이상 입력해주세요.');
        frm.phone2.focus();
        return false;
    }
	
	if(phone3.length != 4 ){
		alert('마지막 자리 핸드폰번호는 4자를  입력해주세요.');
		frm.phone3.focus();
		return false;
	}
    
	return true;
}

function fn_pwd_mod_validator(frm){
	
    var userOldPwd = frm.oldPwd.value;
    var userNewPwd = frm.newPwd.value;
    var userNewPwdConfirm = frm.newPwdConfirm.value;
	
	if(!frm.oldPwd.value){
		alert("기존 사용자 비밀번호를 입력해 주세요.");
		frm.oldPwd.focus();
		return false;
	}
	
	if(!frm.newPwd.value){
		alert("신규 사용자 비밀번호를 입력해 주세요.");
		frm.newPwd.focus();
		return false;
	}
	
	if(!frm.newPwdConfirm.value){
		alert("신규 비밀번호 확인 정보를 입력해 주세요.");
		frm.newPwdConfirm.focus();
		return false;
	}
	
	if (userNewPwd != userNewPwdConfirm) {
		alert("입력한 두 개의 비밀번호가 서로  일치하지 않습니다.");
		frm.newPwd.focus();
		return false;
	}
	
	if(!/^[a-zA-Z0-9!@#$%^&*()?_~]{10,20}$/.test(userNewPwd)){ 
        alert("비밀번호는 숫자, 영문, 특수문자 조합으로 10~20자리를 사용해야 합니다.");
        frm.newPwd.focus();
        return false;
    }
	
	var chk = 0;
    if(userNewPwd.search(/[0-9]/g) != -1 ){
    	chk ++;
    }
    
    if(userNewPwd.search(/[a-z]/ig)  != -1 ){
    	chk ++;
    }
    
    if(userNewPwd.search(/[!@#$%^&*()?_~]/g)  != -1  ){
    	chk ++;
    }
    
    if(chk < 3){
        alert("비밀번호는 숫자, 영문, 특수문자를 세가지이상 혼용하여야 합니다."); 
        frm.newPwd.focus();
        return false;
    }
    
    if(/(\w)\1\1\1/.test(userNewPwd) || isContinuedValue(userNewPwd)){
        alert("비밀번호에 4자 이상의 연속 또는 반복 문자 및 숫자를 사용하실 수 없습니다."); 
        frm.newPwd.focus();
        return false;
    }
    
    return true;
}

function fn_authip_validation(frm){
	if(!frm.sysAccIpNm.value){
		alert("시스템 접속 IP 명을 입력해 주세요.");
		frm.sysAccIpNm.focus();
		return false;
	}
	
	if(frm.ipType.value == '1'){
		if(!frm.ipAdr1.value){
			alert("단일 IP 주소 첫번쨰 값을 입력해 주세요.");
			frm.ipAdr1.focus();
			return false;
		}
		
		if(!frm.ipAdr2.value){
			alert("단일 IP 주소 두번쨰 값을 입력해 주세요.");
			frm.ipAdr2.focus();
			return false;
		}
		
		if(!frm.ipAdr3.value){
			alert("단일 IP 주소 세번쨰 값을 입력해 주세요.");
			frm.ipAdr3.focus();
			return false;
		}
		
		if(!frm.ipAdr4.value){
			alert("단일 IP 주소 네번쨰 값을 입력해 주세요.");
			frm.ipAdr4.focus();
			return false;
		}
	}else if(frm.ipType.value == '2'){
		if(!frm.ipAdr5.value){
			alert("범위 IP 앞자리 주소 첫번쨰 값을 입력해 주세요.");
			frm.ipAdr5.focus();
			return false;
		}
		
		if(!frm.ipAdr6.value){
			alert("범위 IP 앞자리 주소 두번쨰 값을 입력해 주세요.");
			frm.ipAdr6.focus();
			return false;
		}
		
		if(!frm.ipAdr7.value){
			alert("범위 IP 앞자리 주소 세번쨰 값을 입력해 주세요.");
			frm.ipAdr7.focus();
			return false;
		}
		
		if(!frm.ipAdr8.value){
			alert("범위 IP 앞자리 주소 네번쨰 값을 입력해 주세요.");
			frm.ipAdr8.focus();
			return false;
		}
		
		if(!frm.ipAdr9.value){
			alert("범위 IP 뒷자리 주소 첫번쨰 값을 입력해 주세요.");
			frm.ipAdr9.focus();
			return false;
		}
		
		if(!frm.ipAdr10.value){
			alert("범위 IP 뒷자리 주소 두번쨰 값을 입력해 주세요.");
			frm.ipAdr10.focus();
			return false;
		}
		
		if(!frm.ipAdr11.value){
			alert("범위 IP 뒷자리 주소 세번쨰 값을 입력해 주세요.");
			frm.ipAdr11.focus();
			return false;
		}
		
		if(!frm.ipAdr12.value){
			alert("범위 IP 뒷자리 주소 네번쨰 값을 입력해 주세요.");
			frm.ipAdr12.focus();
			return false;
		}
		
		if(Number(frm.ipAdr5.value) > Number(frm.ipAdr9.value)){
			alert("앞자리 IP 주소의 첫번째 값이 뒷자리 IP 주소의 첫번째 값보다 작을 수 없습니다.");
			frm.ipAdr5.focus();
			return false;
		}
	}
	
	return true;
}

function isContinuedValue(value) {
    var intCnt1 = 0;
    var intCnt2 = 0;
    var temp0 = "";
    var temp1 = "";
    var temp2 = "";
    var temp3 = "";

    for (var i = 0; i < value.length-3; i++) {
        temp0 = value.charAt(i);
        temp1 = value.charAt(i + 1);
        temp2 = value.charAt(i + 2);
        temp3 = value.charAt(i + 3);

        if (temp0.charCodeAt(0) - temp1.charCodeAt(0) == 1
                && temp1.charCodeAt(0) - temp2.charCodeAt(0) == 1
                && temp2.charCodeAt(0) - temp3.charCodeAt(0) == 1) {
            intCnt1 = intCnt1 + 1;
        }

        if (temp0.charCodeAt(0) - temp1.charCodeAt(0) == -1
                && temp1.charCodeAt(0) - temp2.charCodeAt(0) == -1
                && temp2.charCodeAt(0) - temp3.charCodeAt(0) == -1) {
            intCnt2 = intCnt2 + 1;
        }
    }
    return (intCnt1 > 0 || intCnt2 > 0);
}
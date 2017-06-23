
function  lftImgSet(){
	$('#imgFind_1').on('click',function(e) {
		e.preventDefault();
		$('#lftImg_1').click();
	});

	$("#lftImg_1").on('change', function(){
		if(window.FileReader){
			readURL(this);
		}else{
			readURLIE(this);
		}
	});

	$('#imgFind_2').on('click',function(e) {
		e.preventDefault();
		$('#lftImg_2').click();
	});

	$("#lftImg_2").on('change', function(){
		if(window.FileReader){
			readURL(this);
		}else{
			readURLIE(this);
		}
	});

	$('#imgFind_3').on('click',function(e) {
		e.preventDefault();
		$('#lftImg_3').click();
	});

	$("#lftImg_3").on('change', function(){
		if(window.FileReader){
			readURL(this);
		}else{
			readURLIE(this);
		}
	});

	$('#imgFind_4').on('click',function(e) {
		e.preventDefault();
		$('#lftImg_4').click();
	});

	$("#lftImg_4").on('change', function(){
		if(window.FileReader){
			readURL(this);
		}else{
			readURLIE(this);
		}
	});

	$('#imgFind_5').on('click',function(e) {
		e.preventDefault();
		$('#lftImg_5').click();
	});

	$("#lftImg_5").on('change', function(){
		if(window.FileReader){
			readURL(this);
		}else{
			readURLIE(this);
		}
	});

	$('#imgFind_6').on('click',function(e) {
		e.preventDefault();
		$('#lftImg_6').click();
	});

	$("#lftImg_6").on('change', function(){
		if(window.FileReader){
			readURL(this);
		}else{
			readURLIE(this);
		}
	});

	$('#imgFind_7').on('click',function(e) {
		e.preventDefault();
		$('#lftImg_7').click();
	});

	$("#lftImg_7").on('change', function(){
		if(window.FileReader){
			readURL(this);
		}else{
			readURLIE(this);
		}
	});

	$('#imgFind_8').on('click',function(e) {
		e.preventDefault();
		$('#lftImg_8').click();
	});

	$("#lftImg_8").on('change', function(){
		if(window.FileReader){
			readURL(this);
		}else{
			readURLIE(this);
		}
	});

	$('#imgFind_9').on('click',function(e) {
		e.preventDefault();
		$('#lftImg_9').click();
	});

	$("#lftImg_9").on('change', function(){
		if(window.FileReader){
			readURL(this);
		}else{
			readURLIE(this);
		}
	});

	$('#imgFind_10').on('click',function(e) {
		e.preventDefault();
		$('#lftImg_10').click();
	});

	$("#lftImg_10").on('change', function(){
		if(window.FileReader){
			readURL(this);
		}else{
			readURLIE(this);
		}
	});
}

function readURL(input) {
    if (input.files && input.files[0]) {
    	
    	if(!fileExtCheck(input.files[0].name)){
    		alert("이미지가 아닌 파일을 올리셨습니다.");
    		return;
    	}
    	
    	var fileId = input.id;
    	var temp = fileId.split("_");
    	
    	$("#uploadSize_"+temp[1]).empty();
    	
        var reader = new FileReader();

        reader.onload = function (e) {
        	$('#upImgShow_'+temp[1]).empty();
			$('#upImgShow_'+temp[1]).append("<img id='target_"+temp[1]+"' src='"+e.target.result+"' width='178'>");
		}

		reader.readAsDataURL(input.files[0]);
    }else{
    	alert("파일을 입력해 주세요.")
    	return;
    }
}

function readURLIE(e){
	var img_path = "";
	var fileId = e.id;
	var temp = fileId.split("_");
	$("#uploadSize_"+temp[1]).empty();
	$('#upImgShow_'+temp[1]).empty();
	$('#upImgShow_'+temp[1]).append("<img id='target_"+temp[1]+"' width='178'>");
	
	var preViewer = $("#target_"+temp[1]);
	
	if (e.value.indexOf("\\fakepath\\") < 0) {
		img_path = e.value;
	}else{
		e.select();
		var selectionRange = document.selection.createRange();
		img_path = selectionRange.text.toString();
		e.blur();
	}
	
	if(!fileExtCheck(img_path)){
		alert("이미지가 아닌 파일을 올리셨습니다.");
		return;
	}
	
	$(preViewer).css('filter',"progid:DXImageTransform.Microsoft.AlphaImageLoader(src='fi" + "le://" + img_path + "', sizingMethod='scale')").show();
}

function fileExtCheck(fileName){
	var tmp = fileName.split(".");
	var ext = tmp[1].toLowerCase();
	if($.inArray(ext, img_ext) == -1){
		return false;
	}
	
	return true;
}

function fileVolumeCheck(){
	if (navigator.userAgent.match(/MSIE ([0-9]+)\./)) {
        var myFso = new ActiveXObject("Scripting FileSystemObject");
        var filepath = $("#lftImg_"+num).val();
        var theFile = myFso.getFile(filepath);
        var size = theFile.size;
    }else{
    	
    }
}
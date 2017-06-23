$(function(){
	// 수정시작, 2017.02.24
	//.wrap의 높이 설정
	getHeight();
	
	//창높이가 변경되면 wrap의 높이값도 변경해준다.
	$( window ).resize(function() {
		getHeight();
	});
	// 수정끝, 2017.02.24
	
	//왼쪽 aside 메뉴 중, 선택된 메뉴의 2depth 메뉴를 보여줌
	$(".dep1Menu>li").each(function(index) {
		if ($(this).children("a").hasClass("on")) {
			$(this).children("a").next(".dep2Menu").show();
		} else {
			$(this).children("a").next(".dep2Menu").hide();
		}
	})

	//왼쪽 aside 메뉴의 1depth 메뉴 클릭하면 클릭된 하위 2depth 메뉴를 보여줌
	$(".dep1Menu>li>a").on('click',function() {
	//$(".dep1Menu>li>a").on('mouseenter',function() {
		if ($(this).hasClass("on")) {
			$(this).removeClass("on")
			$(this).next(".dep2Menu").slideUp();
		} else {
			$(".dep1Menu>li>a").removeClass("on");
			$(".dep2Menu").slideUp();
			$(this).addClass('on');
			$(this).next().slideDown();
		}
	})

	//-----------datepicker 달력 초기화-----------
	//2017.02.14수정 (달력에 글자 한글로 표기함.)
    $( "#datepickerSday, #datepickerEday" ).datepicker({
		dateFormat: 'yy-mm-dd',
		prevText: '이전 달',
		nextText: '다음 달',
		monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		dayNames: ['일','월','화','수','목','금','토'],
		dayNamesShort: ['일','월','화','수','목','금','토'],
		dayNamesMin: ['일','월','화','수','목','금','토'],
		showMonthAfterYear: true,
		//changeMonth: true,
		//changeYear: true,
		yearSuffix: '년',
		minDate: 0,
		showOn:"button",buttonImage: "/resources/images/icon_calendar.png",buttonImageOnly:true,
	});
	
	$('#datepickerSday').datepicker();
    $('#datepickerSday').datepicker("option", "maxDate", $("#datepickerEday").val());
    $('#datepickerSday').datepicker("option", "onClose", function ( selectedDate ) {
        $("#datepickerEday").datepicker( "option", "minDate", selectedDate );
    });
 
    $('#datepickerEday').datepicker();
    $('#datepickerEday').datepicker("option", "minDate", $("#datepickerSday").val());
    $('#datepickerEday').datepicker("option", "onClose", function ( selectedDate ) {
        $("#datepickerSday").datepicker( "option", "maxDate", selectedDate );
    });
    
    $( "#datepickerTarget" ).datepicker({
		dateFormat: 'yy-mm-dd',
		prevText: '이전 달',
		nextText: '다음 달',
		monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		dayNames: ['일','월','화','수','목','금','토'],
		dayNamesShort: ['일','월','화','수','목','금','토'],
		dayNamesMin: ['일','월','화','수','목','금','토'],
		showMonthAfterYear: true,
		//changeMonth: true,
		//changeYear: true,
		yearSuffix: '년',
		showOn:"button",buttonImage: "/resources/images/icon_calendar.png",buttonImageOnly:true,
	});
    
    $('#datepickerTarget').datepicker();
    
	$( "#sdate, #edate" ).datepicker({
		dateFormat: 'yy-mm-dd',
		prevText: '이전 달',
		nextText: '다음 달',
		monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		dayNames: ['일','월','화','수','목','금','토'],
		dayNamesShort: ['일','월','화','수','목','금','토'],
		dayNamesMin: ['일','월','화','수','목','금','토'],
		showMonthAfterYear: true,
		//changeMonth: true,
		//changeYear: true,
		yearSuffix: '년',
		showOn:"button",buttonImage: "/resources/images/icon_calendar.png",buttonImageOnly:true,
	});
	
	$('#sdate').datepicker();
    $('#sdate').datepicker("option", "maxDate", $("#edate").val());
    $('#sdate').datepicker("option", "onClose", function ( selectedDate ) {
        $("#edate").datepicker( "option", "minDate", selectedDate );
    });
 
    $('#edate').datepicker();
    $('#edate').datepicker("option", "minDate", $("#sdate").val());
    $('#edate').datepicker("option", "onClose", function ( selectedDate ) {
        $("#sdate").datepicker( "option", "maxDate", selectedDate );
    });

	//$('#datepickerSday').val(moment().add('month',-1).add('day',1).format('YYYY-MM-DD'));
	//$('#datepickerEday').val(moment().format('YYYY-MM-DD'));

	//---------select 박스 스타일 적용-----------
    
    if($('select')){
    	$('select').selectric();
    }
    
	/*------------Layer popup---------------*/
//	$('.pop_search').on('click',function(e) {	
//		e.preventDefault();
//		popup_open('.popup01');
//	})

	/*------------Layer popup---------------*/
//	$('.pop_sendsms').on('click',function(e) {	
	//	e.preventDefault();
		//popup_open('.popup02');		
	//})
	
	$('.btn_cancle').on('click',function() {
		$('.bg_popup').fadeOut();
		$('.popup_cont').hide();
	})

	
	/* 툴팁 도움말*/	
	$('.tooltipWrap>img').on('click',function(e) {
		e.preventDefault(); 
		$('.tooltipTxt').fadeOut(); //다른 도움말 창이 열려있는경우, 닫아준다.
		$(this).next('.tooltipTxt').fadeIn();
	})
	
	$('.tooltipWrap>span').on('click',function(e) {
		e.preventDefault(); 
		$('.tooltipTxt').fadeOut(); //다른 도움말 창이 열려있는경우, 닫아준다.
		$(this).next('.tooltipTxt').fadeIn();
	})
	$('.tooltip_x').on('click',function(e) {
		e.preventDefault();
		$(this).parent().fadeOut();
	})

});

// 수정, 2017.02.24
function getHeight() {
	var wHeight = $(window).height();
	var bHeight = $('.wrap').prop("scrollHeight")

	if (wHeight > bHeight) { bHeight = wHeight;}	
	$('.wrap').css('height',bHeight);
}


function popup_open(pop_nm) {
	//팝업창을 중앙에 띄우기 위해서 너비 계산
	var pop_w = ( $(window).scrollLeft() + ($(window).width() - $(pop_nm).outerWidth() ) / 2);	
	var pop_h = ( $(window).scrollTop() + ($(window).height() - $(pop_nm).outerHeight() ) / 2);			
	$('.popup_cont').css({'top':pop_h,'left':pop_w});
	
	//지난내역 창 띄둘때, 메인 페이지 disable시키기
	$('.bg_popup').fadeIn();
	$(pop_nm).show();	
}
function fnc_changeLayer(layer01,layer02) {
		$(layer01).show()
		$(layer02).hide()
}

function winOpen(popUrl,popw,poph,pops){
	
	if (popw === undefined) { popw = 970; }	//전단 스케쥴러의 사이즈. 값이 없을땐, 기본이 "전단 스케쥴러" 사이즈
	
	if (poph === undefined) { poph = 725; }
	else if ( poph > $(window).height()) { poph = $(window).height(); } //팝업창 높이가 윈도우 높이보다 길면 윈도우 높이로 수정
	
	if (pops == 'scroll') {pops = 'yes'; } 
	else {pops = 'no';}
	
	var popOption = "width="+popw+", height="+poph+", resizable=no, scrollbars="+pops+", status=no;";
	window.open(popUrl,"",popOption);
}
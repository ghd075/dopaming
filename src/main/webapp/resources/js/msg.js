/*
 **쪽지(msg) script
 */



//전역변수
var oldCnt = 0
var state = 0
var check_r = 0
var check_s = 0
var check_k = 0
var check_t = 0

//최초 함수 호출
$(document).ready(function () {
	if(id == "") {
		return false;
	}
	msgCnt("0");	
	setInterval(msgCnt, 1000);
		
	msgReceive();
		
	msgSent();
	
	msgWrite();
	
	msgKeep();
	
	msgTrash();
	
	//쪽지버튼 클릭
	$("#chk_msg").click(function(){
		$("#open_msg").modal();		
		msgOpen();		
	});
});

//쪽지 최초 열기		
function msgOpen() {
	$.ajax({
		url:context+'/msg_receive',
		type:'GET',
		contentType:'application/json;charset=utf-8',
		dataType:'json',
		success:msgReceiveResult
	});
}



/*
 * 
 * Receive
 * 
 */

//읽지않은 쪽지 수
function msgCnt(count) {
	$.ajax({
		url:context+'/cnt',
		type:'GET',
		contentType:'application/json;charset=utf-8',
		dataType:'json',
		success: function (result) {
			$("#cnt").html(result.cnt)
			if(count != "0" && oldCnt < result.cnt)
				alert("새로운 쪽지를 받았습니다.")			
			oldCnt = result.cnt	
		}
	});
}

//페이징 처리_전체목록
function goList_r(p) {
	form_body.page.value=p;
	$("#receive_msg").click();
}

//페이징 처리_보낸이 그룹
function goList2_r(p) {
	check_r = 0
	form_body.page.value=p;
	msgSenderReceive();
}

//페이징 처리_미확인 쪽지
function goList3_r(p) {
	state = 0
	form_body.page.value=p;
	msgUnselect();
}

//받은쪽지_버튼
function msgReceive() {
	$("#receive_msg").click(function(){
	check_r = 0
		$.ajax({
			url:context+'/msg_receive',
			type:'GET',
			data: $("#form_body").serialize(),
			dataType:'json',
			success:msgReceiveResult
		});
	});
}

//받은쪽지_목록
function msgReceiveResult(data) {
	$("#wrap").remove();
	var $tag = "<div id='wrap'>"
	$tag += "<div class='head_receive'>" + "Receive" + "</div>"
	$tag += "<button type='button' class='btn_unselect' onclick='msgUnselect()'>"
	$tag += "<img src='"+context+"/resources/images/ho/icon_unselect.png' width='22p' height='24px'>"
	$tag += "</button>"
	$tag += "<button type='button' class='btn_keeping' onclick='msgKeeping()'>"
	$tag += "<img src='"+context+"/resources/images/ho/icon_keeping.png' width='22px' height='24px'>"
	$tag += "</button>"
	$tag += "<button type='button' class='btn_trashing' onclick='msgReceiveTrashing()'>"
	$tag += "<img src='"+context+"/resources/images/ho/icon_trashing.png' width='22px' height='24px'>"
	$tag += "</button>"
	$tag += "<p>" 
	$tag += "<span class='sender_bar_r'>" + "sender" + "</span>"
	$tag += "<span class='title_bar_r'>" + "title" + "</span>"
	$tag += "</p>"
	$tag += "<div id='list'>" + "</div>"
	$tag += "<div id='page_num' class='btn_page'>" + "</div>"
	$tag += "</div>"
	$("#form_body").append($tag);
	$("#list").find("span").remove();
	var color;	
	$.each(data.list,function(idx,item) {
		if(item.message_check == "N" || item.message_check == "NSD") {
			color = "color:red;"
		} else
			color = ""			
		$('<p>')
		.append($('<span class="sender_receive" onclick="msgSenderReceive('+item.message_no+')">').html(item.sender_id))
		.append($('<span class="title_receive" onclick="msgSelect('+item.message_no+'); msgChange('+item.message_no+')" style="'+color+'">').html(item.message_title))
		.append($('<input type="checkbox" id="c'+idx+'" name="ck" value="'+item.message_no+'">'))
		.append($('<label id="ck_receive" for="c'+idx+'" />'))
		.appendTo("#list");
	});
	if(check_r == 0) {
		for(i=data.paging.startPage; i <= data.paging.endPage; i++) {
			$('<span>')
			.append('<button type="button" class="btn_page_r" onclick="goList_r('+i+')">'+i+'</button>')
			.appendTo("#page_num");

		}
	} 
	else if(check_r == 1) {
		for(i=data.paging.startPage; i <= data.paging.endPage; i++) {
			$('<span>')
			.append('<button type="button" class="btn_page_r" onclick="goList2_r('+i+')">'+i+'</button>')
			.appendTo("#page_num");
		}
	}
	else {
		for(i=data.paging.startPage; i <= data.paging.endPage; i++) {
			$('<span>')
			.append('<button type="button" class="btn_page_r" onclick="goList3_r('+i+')">'+i+'</button>')
			.appendTo("#page_num");
		}
	}
}

//받는쪽지 보낸이 그룹
function msgSenderReceive(no) {
	if(no) {
		form_body.message_no.value=no;
		form_body.page.value=1;
	} 
	
	if(check_r == 0) {
		$.ajax({
			url:context+'/msg_sender_r',		
			type:'GET',
			data: $("#form_body").serialize(),
			dataType:'json',
			success:msgReceiveResult
		});
	check_r = 1
	} else {
		$("#receive_msg").click();
	check_r = 0
	}
}

//쪽지읽음_상태변경
function msgChange(no) {
	$.ajax({
		url:context+'/msg_changing',		
		type:'GET',
		data: {message_no: no},
		dataType:'json',
	});
}

//쪽지읽기_선택 
function msgSelect(no) {
	$.ajax({
		url:context+'/msg_select',		
		type:'GET',
		data: {message_no: no},
		dataType:'json',
		success:msgSelectResult
	});
}

//쪽지읽기_내용확인
function msgSelectResult(result) {
	$("#wrap").remove();
	var $tag= "<div id='wrap'>"
	$tag += "<div class='head_receive'>" + "Read" + "</div>"
	$tag += "<p>"
	$tag += "<span class='sender_tag_r'>" + "보낸이" + "</span>"
	$tag += "<span class='sender_r'>" + result.sender_id + "</span>"
	$tag += "<span class='date_r'>" + result.message_date + "</span>"
	$tag += "</p>"
	$tag += "<textarea class='content_r'>" + result.message_content + "</textarea>"
	$tag += "</div>"
	$("#form_body").append($tag);	
}

//읽지않은쪽지_버튼
function msgUnselect() {
	if(state == 0) {
		$.ajax({
			url:context+'/msg_unselect',
			type:'GET',
			data: $("#form_body").serialize(),
			dataType:'json',
			success:msgReceiveResult			
		});
	check_r = 2
	state = 1
	} else {
		$("#receive_msg").click();
	check_r = 0
	state = 0
	}
}

//쪽지보관_버튼	
function msgKeeping() {
	var message_check = $('input:checkbox[name="ck"]').is(":checked")
	if(message_check == false) {
		alert("보관함에 보관할 쪽지를 선택해주세요.")
	} else {
		$.ajax({
			url:context+'/msg_checking',
			type:'GET',
			contentType:'application/json;charset=utf-8',
			data: $("#form_body").serialize(),
			dataType:'json',
			success:msgKeepingResult
		});
	}
}	

//쪽지보관_수행
function msgKeepingResult(data) {
	$.each(data,function(idx,item) {
		if(item.message_check == 'N') {
			alert("읽지 않은 쪽지는 보관함으로 이동할 수 없습니다.")
		} else
			$.ajax({
				url:context+'/msg_keeping',
				type:'GET',
				data: {message_no: item.message_no},
				dataType:'json',			
				success:function() { $("#receive_msg").click(); }
			});			
	});
}

//받은쪽지 휴지통_버튼
function msgReceiveTrashing() {
	var message_check = $('input:checkbox[name="ck"]').is(":checked")
	if(message_check == false) {
		alert("휴지통으로 버릴 쪽지를 선택해주세요.")
	} else {
		$.ajax({
			url:context+'/msg_checking',
			type:'GET',
			contentType:'application/json;charset=utf-8',
			data: $("#form_body").serialize(),
			dataType:'json',
			success:msgReceiveTrashingResult
		});
	}
}	

//받은쪽지 휴지통_수행
function msgReceiveTrashingResult(data) {
	$.each(data,function(idx,item) {
		if(item.message_check == 'N') {
			alert("읽지 않은 쪽지는 휴지통으로 이동할 수 없습니다.")
		} else
			$.ajax({
				url:context+'/msg_traching_r',
				type:'GET',
				data: {message_no: item.message_no},
				dataType:'json',			
				success:function() { $("#receive_msg").click(); }
			});			
	});
}



/*
 * 
 * Sent
 * 
 */

//페이징 처리_전체목록
function goList_s(p) {
	form_body.page.value=p;
	$("#sent_msg").click();
}

//페이징 처리_받은이 그룹
function goList2_s(p) {
	check_s = 0
	form_body.page.value=p;
	msgReceiverSent();
}

//보낸쪽지_버튼	
function msgSent() {	
	$("#sent_msg").click(function(){
	check_s = 0
		$.ajax({
			url:context+'/msg_sent',
			type:'GET',
			data: $("#form_body").serialize(),
			dataType:'json',
			success:msgSentResult
		});
	});
}

//보낸쪽지_목록
function msgSentResult(data) {
	$("#wrap").remove();
	var $tag = "<div id='wrap'>"
	$tag += "<div class='head_sent'>" + "Sent" +"</div>"
	$tag += "<h5 style='color:red'>" + '빨간색: 수신자가 읽지 않은 쪽지' + "</h5>" 
	$tag += "<button type='button' class='btn_trashing' onclick=' msgSentTrashing()'>"
	$tag += "<img src='"+context+"/resources/images/ho/icon_trashing.png' width='22px' height='24px'>"
	$tag += "</button>"
	$tag += "<p>" 
	$tag += "<span class='receiver_bar'>" + "receiver" + "</span>"
	$tag += "<span class='title_bar_s'>" + "title" + "</span>"
	$tag += "</p>"
	$tag += "<div id='list'>" + "</div>"
	$tag += "<div id='page_num' class='btn_page'>" + "</div>"
	$tag += "</div>"
	$tag += "</div>"
	$("#form_body").append($tag);
	$("#list").find("span").remove();
	$.each(data.list,function(idx,item){
		if(item.message_check == "N") {
			var color = "color:red;"
		} else var color = ""
		$('<p>')
		.append($('<span class="receiver_sent" onclick="msgReceiverSent('+item.message_no+')">').html(item.receiver_id)) 
		.append($('<span class="title_sent" onclick="msgSelectSent('+item.message_no+')" style="'+color+'">').html(item.message_title))
		.append($('<input type="checkbox" id="c'+idx+'" name="ck" value="'+item.message_no+'">'))
		.append($('<label id="ck_sent" for="c'+idx+'" />'))
		.appendTo("#list");
	});
	if(check_s == 0) {
		for(i=data.paging.startPage; i <= data.paging.endPage; i++) {
			$('<span>')
			.append('<button type="button" class="btn_page_s" onclick="goList_s('+i+')">'+i+'</button>')
			.appendTo("#page_num");

		}
	} else {
		for(i=data.paging.startPage; i <= data.paging.endPage; i++) {
			$('<span>')
			.append('<button type="button" class="btn_page_s" onclick="goList2_s('+i+')">'+i+'</button>')
			.appendTo("#page_num");
		}
	}
}


//보낸쪽지 받은이 그룹
function msgReceiverSent(no) {
	if(no) {
		form_body.message_no.value=no;
		form_body.page.value=1;
	} 
		
	if(check_s == 0) {
		$.ajax({
			url:context+'/msg_receiver',		
			type:'GET',
			data: $("#form_body").serialize(),
			dataType:'json',
			success:msgSentResult
		});
	check_s = 1	
	} else {
		$("#sent_msg").click();
	check_s = 0	
	}
}

//보낸쪽지읽기_선택 
function msgSelectSent(no) {
	$.ajax({
		url:context+'/msg_select_s',		
		type:'GET',
		data: {message_no: no},
		dataType:'json',
		success:msgSelectSentResult
	});
}

//보낸쪽지읽기_내용확인
function msgSelectSentResult(result) {
	$("#wrap").remove();
	var $tag= "<div id='wrap'>"
	$tag += "<div class='head_sent'>" + "Read" + "</div>"
	$tag += "<p>"
	$tag += "<span class='receiver_tag_s'>" + "받은이" + "</span>"
	$tag += "<span class='receiver_s'>" + result.receiver_id + "</span>"
	$tag += "<span class='date_s'>" + result.message_date + "</span>"
	$tag += "</p>"
	$tag += "<div class='content_s'>" + result.message_content + "</div>"
	$tag += "</div>"
	$("#form_body").append($tag);	
}

//보낸쪽지 휴지통_버튼
function msgSentTrashing() {
	var message_check = $('input:checkbox[name="ck"]').is(":checked")
	if(message_check == false) {
		alert("휴지통으로 버릴 쪽지를 선택해주세요.")
	} else {
		if(confirm("보낸 쪽지의 삭제는 복원이 불가합니다. 완전히 삭제 하시겠습니까?") == true) {
			$.ajax({
				url:context+'/msg_traching_s',
				type:'GET',
				dataType:'json',
				data: $("#form_body").serialize(),
				success:function() { $("#sent_msg").click(); }
			});
		} else {
			return false;
		}
	}
}



/*
 * 
 * Write
 * 
 */

//쪽지쓰기
function msgWrite() {
	$("#write_msg").click(function(){			
		$("#wrap").remove();
		var $tag = "<div id='wrap'>"
		$tag += "<div class='head_write'>" + "Write" + "</div>"
		$tag += "<button type='button' class='btn_sending' onclick='msgSending()'>"
		$tag += "<img src='"+context+"/resources/images/ho/icon_sending.png' width='22px' height='24px'>"
		$tag += "</button>"
		$tag += "<div class='content_position'>"
		$tag += "<input type='text' class='receiver_w' name='receiver_id' placeholder='받는이'>"
		$tag += "<input type='text' class='title_w' name='message_title' placeholder='제목'>"
		$tag += "<textarea class='content_w' name='message_content' onKeyUp='checkByte(this.form)' placeholder='내용은 2000byte 이내로 작성해주세요.(한글, 약600자 내외)'>" + "</textarea>"
		$tag += "<div class='byte_position'>"  		
		$tag += "<input type='text' class='lenght_w' name='message_byte' value='0' maxlength='3' readonly>" + " / 2000 byte"
		$tag += "</div>"		
		$tag += "</div>"
		$tag += "</div>"
		$("#form_body").append($tag);
	});
}

//작성내용 Byte 계산
function checkByte(frm) {
	var totalByte = 0;
	var limitByte = 2000;
	var content = frm.message_content.value;
	
	for(var i=0; i < content.length; i++) {
			var currentByte = content.charCodeAt(i);
			if(currentByte > 128) totalByte +=3;
		else totalByte++;
	}
	frm.message_byte.value = totalByte;	
	
	if(totalByte > limitByte) {
			alert(limitByte + "Byte까지 전송 가능합니다.");
		frm.message_content.value = content.substring(0,limitByte);
	}	
}

//쪽지발송
function msgSending() {
	var receiver_id = $('input:text[name="receiver_id"]').val();
	var message_title = $('input:text[name="message_title"]').val();
	var message_content = $('textarea[name="message_content"]').val();
	if(receiver_id == "") {
		alert("받는이 id를 입력하여 주세요.")
	} else if(message_title == "") {
		alert("쪽지 제목을 입력하여 주세요.") 
	} else if(message_content == "") {
		alert("쪽지 내용을 입력하여 주세요.")
	} else {
		$.ajax({
			url:context+'/msg_write',
			type:'POST',
			data:JSON.stringify({ receiver_id:receiver_id, message_title:message_title, message_content:message_content }),
			dataType:'json',			
			contentType: 'application/json',
			mimeType: 'application/json',
			success:function() { $("#sent_msg").click(); }
		});
	}	
}



/*
 * 
 * Keep
 * 
 */

//페이징 처리_전체목록
function goList_k(p) {
	form_body.page.value=p;
	$("#keep_msg").click();
}

//페이징 처리_보낸이 그룹
function goList2_k(p) {
	check_k = 0
	form_body.page.value=p;
	msgSenderKeep();
}

//쪽지보관함_버튼
function msgKeep() {
	$("#keep_msg").click(function(){
	check_k = 0
		$.ajax({
			url:context+'/msg_keep',
			type:'GET',
			data: $("#form_body").serialize(),
			dataType:'json',
			success:msgKeepResult
		});
	});
}

//쪽지보관함_목록
function msgKeepResult(data) {
	$("#wrap").remove();
	var $tag = "<div id='wrap'>"
	$tag += "<div class='head_keep'>" + "Keep" + "</div>"
	$tag += "<button type='button' class='btn_back' onclick='msgKeepReturning()'>"
	$tag += "<img src='"+context+"/resources/images/ho/icon_back.png' width='22p' height='24px'>"
	$tag += "</button>"
	$tag += "<button type='button' class='btn_trashing' onclick='msgKeepTrashing()'>"
	$tag += "<img src='"+context+"/resources/images/ho/icon_trashing.png' width='22px' height='24px'>"
	$tag += "</button>"
	$tag += "<p>" 
	$tag += "<span class='sender_bar_k'>" + "sender" + "</span>"
	$tag += "<span class='title_bar_k'>" + "title" + "</span>"
	$tag += "</p>"
	$tag += "<div id='list'>" + "</div>"
	$tag += "<div id='page_num' class='btn_page'>" + "</div>"
	$tag += "</div>"
	$tag += "</div>"
	$("#form_body").append($tag);	
	$("#list").find("span").remove();	
	$.each(data.list,function(idx,item) {
		$('<p>')
		.append($('<span class="sender_keep" onclick="msgSenderKeep('+item.message_no+')">').html(item.sender_id))
		.append($('<span class="title_keep" onclick="msgSelectKeep('+item.message_no+')">').html(item.message_title))
		.append($('<input type="checkbox" id="c'+idx+'" name="ck" value="'+item.message_no+'">'))
		.append($('<label id="ck_keep" for="c'+idx+'" />'))
		.appendTo("#list");
	});
	if(check_k == 0) {
		for(i=data.paging.startPage; i <= data.paging.endPage; i++) {
			$('<span>')
			.append('<button type="button" class="btn_page_k" onclick="goList_k('+i+')">'+i+'</button>')
			.appendTo("#page_num");

		}
	} else {
		for(i=data.paging.startPage; i <= data.paging.endPage; i++) {
			$('<span>')
			.append('<button type="button" class="btn_page_k" onclick="goList2_k('+i+')">'+i+'</button>')
			.appendTo("#page_num");
		}
	}
}

//보관함 보낸이 그룹
function msgSenderKeep(no) {
	if(no) {
		form_body.message_no.value=no;
		form_body.page.value=1;
	} 
	
	if(check_k == 0) {
		$.ajax({
			url:context+'/msg_sender_k',		
			type:'GET',
			data: $("#form_body").serialize(),
			dataType:'json',
			success:msgKeepResult
		});
	check_k = 1
	} else {
		$("#keep_msg").click();
	check_k = 0
	}
}

//쪽지읽기_선택 
function msgSelectKeep(no) {
	$.ajax({
		url:context+'/msg_select',		
		type:'GET',
		data: {message_no: no},
		dataType:'json',
		success:msgSelectKeepResult
	});
}

//쪽지읽기_내용확인
function msgSelectKeepResult(result) {
	$("#wrap").remove();
	var $tag= "<div id='wrap'>"
	$tag += "<div class='head_keep'>" + "Read" + "</div>"
	$tag += "<p>"
	$tag += "<span class='sender_tag_k'>" + "보낸이" + "</span>"
	$tag += "<span class='sender_k'>" + result.sender_id + "</span>"
	$tag += "<span class='date_k'>" + result.message_date + "</span>"
	$tag += "</p>"
	$tag += "<div class='content_k'>" + result.message_content + "</div>"
	$tag += "</div>"
	$("#form_body").append($tag);	
}

//보관함 이전으로 복원	
function msgKeepReturning() {
	var message_check = $('input:checkbox[name="ck"]').is(":checked")
	if(message_check == false) {
		alert("이전으로 복원할 쪽지를 선택해주세요.")
	} else {
		$.ajax({
			url:context+'/msg_returning_k',
			type:'GET',
			data: $("#form_body").serialize(),
			dataType:'json',			
			success:function() { $("#keep_msg").click(); }
		});
	}
}

//보관함 휴지통_버튼
function msgKeepTrashing() {
	var message_check = $('input:checkbox[name="ck"]').is(":checked")
	if(message_check == false) {
		alert("휴지통으로 버릴 쪽지를 선택해주세요.")
	} else {
		$.ajax({
			url:context+'/msg_traching_k',
			type:'GET',
			dataType:'json',
			data: $("#form_body").serialize(),
			success:function() { $("#keep_msg").click(); }
		});
	}
}



/*
 * 
 * Trach
 * 
 */
 
//페이징 처리_전체목록
function goList_t(p) {
	form_body.page.value=p;
	$("#trash_msg").click();
}

//페이징 처리_보낸이 그룹
function goList2_t(p) {
	check_t = 0
	form_body.page.value=p;
	msgSenderTrash();
}

 //휴지통_버튼
function msgTrash() {
	$("#trash_msg").click(function(){
	check_t = 0
		$.ajax({
			url:context+'/msg_trash',
			type:'GET',
			data: $("#form_body").serialize(),
			dataType:'json',
			success:msgTrashResult
		});
	});
}

//휴지통_목록
function msgTrashResult(data) {
	$("#wrap").remove();
	var $tag = "<div id='wrap'>"
	$tag += "<div class='head_trash'>" + "Trash" + "</div>"
	$tag += "<button type='button' class='btn_back' onclick='msgTrashReturning()'>"
	$tag += "<img src='"+context+"/resources/images/ho/icon_back.png' width='22p' height='24px'>"
	$tag += "</button>"
	$tag += "<button type='button' class='btn_trashing' onclick='msgDelete()'>"
	$tag += "<img src='"+context+"/resources/images/ho/icon_trashing.png' width='22px' height='24px'>"
	$tag += "</button>"
	$tag += "<p>" 
	$tag += "<span class='sender_bar_t'>" + "sender" + "</span>"
	$tag += "<span class='title_bar_t'>" + "title" + "</span>"
	$tag += "</p>"
	$tag += "<div id='list'>" + "</div>"
	$tag += "<div id='page_num' class='btn_page'>" + "</div>"
	$tag += "</div>"
	$tag += "</div>"
	$("#form_body").append($tag);	
	$("#list").find("span").remove();	
	$.each(data.list,function(idx,item) {
		$('<p>')
		.append($('<span class="sender_trash" onclick="msgSenderTrash('+item.message_no+')">').html(item.sender_id))
		.append($('<span class="title_trash" onclick="msgSelectTrash('+item.message_no+')">').html(item.message_title))
		.append($('<input type="checkbox" id="c'+idx+'" name="ck" value="'+item.message_no+'">'))
		.append($('<label id="ck_trash" for="c'+idx+'" />'))
		.appendTo("#list");
	});
	if(check_t == 0) {
		for(i=data.paging.startPage; i <= data.paging.endPage; i++) {
			$('<span>')
			.append('<button type="button" class="btn_page_t" onclick="goList_t('+i+')">'+i+'</button>')
			.appendTo("#page_num");

		}
	} else {
		for(i=data.paging.startPage; i <= data.paging.endPage; i++) {
			$('<span>')
			.append('<button type="button" class="btn_page_t" onclick="goList2_t('+i+')">'+i+'</button>')
			.appendTo("#page_num");
		}
	}
}

//휴지통 보낸이 그룹
function msgSenderTrash(no) {
	if(no) {
		form_body.message_no.value=no;
		form_body.page.value=1;
	}
	
	if(check_t == 0) {
		$.ajax({
			url:context+'/msg_sender_t',		
			type:'GET',
			data: $("#form_body").serialize(),
			dataType:'json',
			success:msgTrashResult
		});
	check_t = 1
	} else {
		$("#trash_msg").click();
	check_t = 0
	}
}

//쪽지읽기_선택 
function msgSelectTrash(no) {
	$.ajax({
		url:context+'/msg_select',		
		type:'GET',
		data: {message_no: no},
		dataType:'json',
		success:msgSelectTrashResult
	});
}

//쪽지읽기_내용확인
function msgSelectTrashResult(result) {
	$("#wrap").remove();
	var $tag= "<div id='wrap'>"
	$tag += "<div class='head_trash'>" + "Read" + "</div>"
	$tag += "<p>"
	$tag += "<span class='sender_tag_t'>" + "보낸이" + "</span>"
	$tag += "<span class='sender_t'>" + result.sender_id + "</span>"
	$tag += "<span class='date_t'>" + result.message_date + "</span>"
	$tag += "</p>"
	$tag += "<div class='content_t'>" + result.message_content + "</div>"
	$tag += "</div>"
	$("#form_body").append($tag);	
}

//휴지통 이전으로 복원
function msgTrashReturning() {
	var message_check = $('input:checkbox[name="ck"]').is(":checked")
	if(message_check == false) {
		alert("이전으로 복원할 쪽지를 선택해주세요.")
	} else {
		$.ajax({
			url:context+'/msg_returning_t',
			type:'GET',
			data: $("#form_body").serialize(),
			dataType:'json',			
			success:function() { $("#trash_msg").click(); }
		});
	}
}

//휴지통 비우기_버튼
function msgDelete() {
	var message_check = $('input:checkbox[name="ck"]').is(":checked")
	if(message_check == false) {
		alert("휴지통에서 비울 쪽지를 선택해주세요.")
	} else {
		if(confirm("휴지통을 비우면 복원이 불가합니다. 완전히 삭제 하시겠습니까?") == true) {
			$.ajax({
				url:context+'/msg_delete',
				type:'GET',
				dataType:'json',
				data: $("#form_body").serialize(),
				success:function() { $("#trash_msg").click(); }
			});
		} else {
			return false;
		}
	}
}
 
 
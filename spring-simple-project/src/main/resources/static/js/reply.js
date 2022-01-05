
//답글 버튼을 클릭하면 해당 댓글의 위치 아래에 답글폼 생성
var replyForm = function(data){	
	var flag = document.getElementById(data).style.display;
 	if(flag=='none'){
        document.getElementById(data).style.display='block';
    } else if(flag == 'block'){
        document.getElementById(data).style.display='none';
    } 
}

//댓글,답글 작성시 비동기 통신을 통해 해당 댓글 리스트만 따로 갱신
//페이징 처리라던가 등을 위해서 좀 더 통신해야할 것이 필요
var reply = function (data){
var form = $('#'+data);
$.ajax({
        url: "/reply/save",
        data: form.serialize(),
        type:"POST",
    }).done(function (fragment) {
        $("#commentSpace").replaceWith(fragment);
 });
}




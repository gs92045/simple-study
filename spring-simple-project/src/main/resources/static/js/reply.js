var replyForm = function(data){	
	
	var flag = document.getElementById(data).style.display;
 	if(flag=='none'){
        document.getElementById(data).style.display='block';
    } else if(flag == 'block'){
        document.getElementById(data).style.display='none';
    } 
}



var reply = function (data){
var form = $('#'+data);

$.ajax({
        url: "/reply/save",
        data: form.serialize(),
        type:"POST",
        success : function(){
        	alert(data);
        }
    }).done(function (fragment) {
        $("#commentSpace").replaceWith(fragment);
 });
}


	
	/*
		댓글 등록하기
			- ajax 통신을 이용하여
	*/
	var form = document.getElementById('commentRegForm');
	
	form.addEventListener('submit', (event)=>{
		//json타입 설정
		var headers = new Headers();
		headers.set('Accept','appication/json');
		
		//form 객체 안의 값들을 formData안에 넣기
		var formData = new FormData();
		for(var i=0;i<form.length;i++){
			formData.append(form[i].name,form[i].value);
		}
			
		var url = '/reply/save';
		var fetchOptions = {
				method: 'POST',
				headers,
				body: formData
		};
		
		
		//댓글 등록 후 갱신을 위해 응답을 받음
		var responsePromise = fetch(url,fetchOptions);
		responsePromise
		  .then(function(response){
			  return response.json();
		  })
		  .then(function(jsonData){
			var list = document.getElementById('commentSpace');
			console.log(jsonData);
			list.innerHTML = " ";
				
			for(var i=0;i<jsonData.length;i++){
				var data = jsonData[i];
				list.innerHTML +=
				"<div id='commentList' class='d-flex mb-4'>"+ 
				//user Image
				"<div class='flex-shrink-0'>"+
					"<img class='rounded-circle' src='https://dummyimage.com/50x50/ced4da/6c757d.jpg' alt='...' />"+
				"</div>"+
				//comment contents
              	"<div class='ms-3'>"+
				//userId
                "<div class='fw-bold'>"+data.userId+"</div>"+
				"<p>"+data.contents+"</p>"+
				"</div>"+"</div>";
				
			}
		});		
		event.preventDefault();
	});
	
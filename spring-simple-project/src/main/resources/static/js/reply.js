

/* var form = document.getElementById("commentRegForm");

form.addEventListener('submit', (event) => {
	//var headers = new Headers();
	//headers.set('Accept', 'appication/json');

	var commentRegForm = new FormData();
	for (var i = 0; i < form.length; i++) {
		commentRegForm.append(form[i].name, form[i].value);
	}
	
	var url = '/reply/save';
	var fetchOptions = {
		method: 'post',
		body: commentRegForm
	};
	
	var responsePromise = fetch(url,fetchOptions);
		responsePromise
		 .then(function(fragment){
		 		console.log(fragment);
				var listSpace = document.getElementById('test');
		  		listSpace.replaceWith(fragment);	
			});		

	event.preventDefault();
}); */


function reply(){

var form = $("#commentRegForm").serialize();

 $.ajax({
        url: "/reply/save",
        data: form,
        type:"POST",
    }).done(function (fragment) {
        $("#commentSpace").replaceWith(fragment);
    });
}


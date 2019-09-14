
$('#signin_btn').off().on('click',function(){

	let people = {};
	people.id=$('#id').val();
	people.password=$('#password').val();

	$.ajax({
		url:'/signin',
		type:'POST',
		contentType: 'application/json; charset=UTF-8',
		dataType:'json',
		data: JSON.stringify(people),
		success:function(data){
			if(data.code==1){
				location.href='/';
			}else{
				alert(data.message);
			}
		},
		error:function(){

		}

	});
});

//Login Enter key 적용 by choiseongjun
var input = document.getElementById("password");
input.addEventListener("keyup", function(event) {
  if (event.keyCode === 13) {
   event.preventDefault();
   document.getElementById("signin_btn").click();
  }
});
//Login Enter key 적용
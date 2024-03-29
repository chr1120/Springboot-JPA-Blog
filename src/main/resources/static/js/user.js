let index={
init: function(){
	$("#btn-save").on("click",()=>{ // 첫번째: 이벤트(click), 두번째: 이벤트 발생시 무엇을 할것인가 
		this.save();
		});
		
	$("#btn-update").on("click",()=>{ // 첫번째: 이벤트(click), 두번째: 이벤트 발생시 무엇을 할것인가 
		this.update();
		});	
		
	},
	save: function(){
		//alert("user의 save함수 호출됨");
		let data={
			// username, password, email을 아이디값(#)으로 찾기
			// 찾아서 id가 들고 있는 값을 변수에 바이딩
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};
		
		//console.log(data);
		
		// ajax 호출시 default가 비동기 호출
		// ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!
		// ajax가 통신을 성공하고 서버가 json을 리턴해주면 '자동으로' 자바 오브젝트로 변환해준다.
		$.ajax({
		// 회원가입 수행 요청 
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data), // http body 데이터
			contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지(MIME)
			dataType: "json" // 요청을 서버로 해서 응답이 왔을 때 기본적으로 모든 것이 버퍼로 오기 때문에 String(문자열)인데, 생긴 형태가 json이라면, javascript 오브젝트로 변경
		}).done(function(resp){
			if(resp.status === 500) {
				alert("회원가입에 실패하였습니다.");
			}else{
				alert("회원가입이 완료되었습니다.");
				location.href="/";
			}
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
	},
	
	update: function(){
		let data={
			id: $("#id").val(),
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};
		
		$.ajax({
			type: "PUT",
			url: "/user",
			data: JSON.stringify(data), // http body 데이터
			contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지(MIME)
			dataType: "json" // 요청을 서버로 해서 응답이 왔을 때 기본적으로 모든 것이 버퍼로 오기 때문에 String(문자열)인데, 생긴 형태가 json이라면, javascript 오브젝트로 변경
		}).done(function(resp){
			alert("회원수정이 완료되었습니다.");
			location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
		
	}
}

index.init();
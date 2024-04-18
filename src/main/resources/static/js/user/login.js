$(document).ready(function(){
	
	// 로그인 시도
	$("#loginForm").on("submit", function(e){
		e.preventDefault();
		let id = $("#idInput").val();
		let password = $("#passwordInput").val();
		if(id == ""){ // 아이디 미입력 시
			alert("아이디를 입력하세요.");
			return;
		}
		if(password == ""){ // 패스워드 미입력 시
			alert("패스워드를 입력하세요.");
			return;
		}
		$.ajax({ // 로그인 요청
			url: "/user/login"
			, type: "post"
			, data: {"loginId":id
					, "password":password
			}
			, success: function(data){
				if(data.result == "success"){
					location.href = "/blog/home-page";
				}else{
					alert("일치하는 계정 정보가 없습니다.");
				}
			}
			, error: function(){
				alert("로그인 오류");
			}
		});
	});
});
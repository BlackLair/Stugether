
$(document).ready(function(){
	let idFormat = /^[a-z0-9]{8,16}$/; // 8~16자의 영문 또는 숫자 아이디 정규식
	let passwordFormat = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,16}$/;  // 8~16자의 영문/숫자 조합 패스워드 정규식
	let emailFormat = /^[a-z0-9]+@[a-z]+\.[a-z]{2,3}$/; // 이메일 정규식
	let nicknameFormat = /^[a-z0-9가-힣]{2,10}$/; // 2~10글자의 영문/숫자/한글 닉네임 정규식
	
	// 회원가입 버튼
	$("#joinBtn").on("click", function(){
		let id = $("#idInput").val();
		let password = $("#passwordInput").val();
		let nickname = $("#nicknameInput").val();
		let email = $("#emailInput").val();
		$.ajax({
			url: "/user/join"
			, type: "post"
			, data: {"loginId":id
					, "password":password
					, "nickname":nickname
					, "email":email}
			, success: function(data){
				if(data.result == "success"){
					alert("회원가입 성공");
					location.href = "/user/login-page";
				}else{
					alert("회원가입 실패");
				}
			}
			, error: function(data){
				alert("회원가입 에러");
			}
		});
	});
	
	// 이메일 유효성 검사
	$("#emailInput").on("input", function(){
		let email = $(this).val();
		isValidEmail = false;
		if(email == ""){
			setValid($(this), "none");
		}else if(!emailFormat.test(email)){
			setValid($(this), "invalid");
		}else{
			isValidEmail = true;
			setValid($(this), "valid");
		}
	});
	
	// 닉네임 중복 여부 검사
	$("#duplicatedNicknameBtn").on("click", function(){
		let nickname = $("#nicknameInput").val();
		$.ajax({
			url: "/user/duplicated-nickname"
			, type: "get"
			, data: {"nickname":nickname}
			, context: this
			, success:function(data){
				if(data.duplicated){
					alert("이미 사용중인 닉네임입니다.");
					isValidNickname = false;
					setValid($("#nicknameInput"), "invalid");
					$(this).attr("disabled", true);
				}else{
					alert("사용 가능한 닉네임입니다.");
					isValidNickname = true;
					setValid($("#nicknameInput"), "valid");
				}
			}
		});
	});
	
	// 닉네임 유효성 검사
	$("#nicknameInput").on("input", function(){
		let nickname = $(this).val();
		isValidNickname = false;
		$("#duplicatedNicknameBtn").attr("disabled", true);
		if(nickname != "" && !nicknameFormat.test(nickname)){
			setValid($(this), "invalid");
		}else{
			setValid($(this), "none");
			if(nickname != "")
				$("#duplicatedNicknameBtn").attr("disabled", false);
		}
	});
	
	// 패스워드 확인 유효성 검사
	$("#confirmPasswordInput").on("input", function(){
		let confirmPassword = $(this).val();
		let password = $("#passwordInput").val();
		isValidConfirmPassword = false;
		if(confirmPassword == ""){
			setValid($(this), "none");
		}else if(password != confirmPassword){
			setValid($(this), "invalid");
		}else{
			isValidConfirmPassword = true;
			setValid($(this), "valid");
		}
	});
	
	// 패스워드 유효성 검사
	$("#passwordInput").on("input", function(){
		let confirmPassword = $("#confirmPasswordInput").val();
		let password = $(this).val();
		isValidPassword = false;
		if(password == ""){
			setValid($(this), "none");
			return;
		}else if(!passwordFormat.test(password)){
			setValid($(this), "invalid");
		}else{
			isValidPassword = true;
			setValid($(this), "valid");
		}
		if(confirmPassword != ""){ // 패스워드 확인 유효성 검사
			if(password == confirmPassword){
				isValidConfirmPassword = true;
				setValid($("#confirmPasswordInput"), "valid");
			}else{
				isValidConfirmPassword = false;
				setValid($("#confirmPasswordInput"), "invalid");
			}	
		}
	});
	
	// 아이디 유효성 검사
	$("#idInput").on("input", function(){
		let id = $(this).val();
		isValidId = false;
		$("#duplicatedIdBtn").attr("disabled", true);
		if(!idFormat.test(id) && id != ""){
			setValid($(this), "invalid");
		}else{
			setValid($(this), "none");
			if(id != ""){
				$("#duplicatedIdBtn").attr("disabled", false);
			}
		}
	});
	
	// 아이디 중복 여부 검사
	$("#duplicatedIdBtn").on("click", function(){
		let id = $("#idInput").val();
		$.ajax({
			url:"/user/duplicated-id"
			, type:"get"
			, data:{"loginId":id}
			, context: this
			, success:function(data){
				if(data.duplicated){
					alert("이미 사용중인 아이디입니다.");
					isValidId = false;
					setValid($("#idInput"), "invalid");
					$(this).attr("disabled", true);
				}else{
					alert("사용 가능한 아이디입니다.");
					isValidId = true;
					setValid($("#idInput"), "valid");
				}
			}
			, error:function(){
				alert("아이디 중복 확인 에러");
			}
		});
	});
});
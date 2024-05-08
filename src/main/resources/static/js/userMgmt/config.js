	$(document).ready(function(){
		let passwordFormat = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,16}$/;  // 8~16자의 영문/숫자 조합 패스워드 정규식
		let emailFormat = /^[a-z0-9]+@[a-z]+\.[a-z]{2,3}$/; // 이메일 정규식
		let nicknameFormat = /^[a-z0-9가-힣]{2,10}$/; // 2~10글자의 영문/숫자/한글 닉네임 정규식
		
		function editAccount(type, value){
			let data = {};
			data[type] = value;
			$.ajax({
				url: "/account/edit-account"
				, type: "PATCH"
				, data: data
				, success:function(data, textStatus, xhr){
					if(data.result == "success"){
						if(type == "password"){
							alert("패스워드가 변경되었습니다. 다시 로그인해주세요.");
						}else{
							alert("정보가 수정되었습니다.");
						}
						location.reload();
					}else{
						alert("정보 수정에 실패했습니다.");
					}
				}
				, error:function(request, status, error){
					alert("정보 수정 에러");
					location.reload();
				}
			});
		}
		
		// 회원 탈퇴 메시지
		$("#resignInput").on("input", function(){
			let msg = $(this).val();
			if(msg == ""){
				setValid($("#resignBtn"), "none");
			}else if(msg == $(this).data("login-id") + " 회원탈퇴하겠습니다."){
				setValid($("#resignBtn"), "valid");
				$("#resignBtn").attr("disabled", false);
			}else{
				setValid($("#resignBtn"), "invalid");
				$("#resignBtn").attr("disabled", true);
			}
			
		});
		
		// 닉네임 변경 버튼
		$("#nicknameBtn").on("click", function(){
			let type = "nickname";
			let value = $("#nicknameInput").val();
			editAccount(type, value);
		});
		// 이메일 변경 버튼
		$("#emailBtn").on("click", function(){
			let type = "email";
			let value = $("#emailInput").val();
			editAccount(type, value);
		});
		// 패스워드 변경 버튼
		$("#passwordBtn").on("click", function(){
			let type = "password";
			let value = $("#passwordInput").val();
			editAccount(type, value);
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
		
	});
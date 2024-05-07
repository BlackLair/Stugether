let isValidId = false;
let isValidPassword = false;
let isValidConfirmPassword = false;
let isValidNickname = false;
let isValidEmail = false;

function checkAllValid(){
	if(isValidId && isValidPassword && isValidConfirmPassword && isValidNickname && isValidEmail){
		$("#joinBtn").attr("disabled", false);
	}else{
		$("#joinBtn").attr("disabled", true);
	}
	if(isValidPassword && isValidConfirmPassword){
		$("#passwordBtn").attr("disabled", false);
	}else{
		$("#passwordBtn").attr("disabled", true);
	}
	if(isValidEmail){
		$("#emailBtn").attr("disabled", false);
	}else{
		$("#emailBtn").attr("disabled", true);
	}
	if(isValidNickname){
		$("#nicknameBtn").attr("disabled", false);
	}else{
		$("#nicknameBtn").attr("disabled", true);
	}
}

function setValid(tag, status){
	if(status == "valid"){
		tag.removeClass("is-invalid");
		tag.addClass("is-valid");
	}else if(status == "invalid"){
		tag.removeClass("is-valid");
		tag.addClass("is-invalid");
	}else{                         // none : 공란이거나 id의 중복 확인이 되기 전 상태
		tag.removeClass("is-valid");
		tag.removeClass("is-invalid");
	}
	checkAllValid();
}
$(document).ready(function(){
	$(".user-nickname").on("click", function(){
		let userId = Number($(this).data("user-id"));
		location.href = "/blog/list-page?userId=" + userId;
	});
});
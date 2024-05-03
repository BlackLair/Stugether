function addCategoryUIEvent(){
	$("#addCategoryBtn").on("click", function(){
		let name = $("#newCategoryInput").val();
		if(name == ""){
			alert("추가할 카테고리 이름을 입력하세요.");
			return;
		}
		if(name.length > 10){
			alert("카테고리 이름은 10글자 이하입니다.");
			return;
		}
		$.ajax({
			url: "/blog/category/add"
			, type: "POST"
			, data: {"name": name}
			, success:function(data){
				if(data.result == "success"){
					alert("카테고리가 추가되었습니다.");
					location.reload();
				}else if(data.result == "already exist"){
					alert("이미 존재하는 카테고리입니다.");
				}
			}
			, error:function(){
				alert("카테고리 추가 에러");
			}
		});
	});
	$(".removeCategoryBtn").on("click", function(){
		let categoryId = $(this).data("category-id");
		let categoryName = $(this).data("category-name");
		if(prompt("카테고리를 삭제하면 포함된 모든 글이 삭제됩니다. 삭제하려면 아래 문구를 입력하세요."
				+ "\n " + categoryName + "를 삭제하겠습니다.") == categoryName + "를 삭제하겠습니다."){
			$.ajax({
				url: "/blog/category/remove"
				, type: "DELETE"
				, data: {"categoryId": categoryId}
				, success:function(data){
					if(data.result == "success"){
						alert("카테고리가 삭제되었습니다.");
						location.reload();
					}else if(data.result == "default category"){
						alert("기본 카테고리는 삭제할 수 없습니다.");
					}else{
						alert("카테고리 삭제 실패");
					}
				}
				, error:function(data){
					alert("카테고리 삭제 오류");
				}
			});
		}
		else{
			alert("삭제를 취소했습니다.");
		}
	});
}
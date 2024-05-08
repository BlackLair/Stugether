	$(document).ready(function(){
		let prevType = $("#prevType").data("type");
		if(prevType != "")
			$("#searchTypeSelect").val(prevType).attr("selected", "selected");
		$("#searchForm").on("submit", function(e){
			e.preventDefault();
			let type = $("#searchTypeSelect").val();
			let search = $("#searchInput").val();
			if(search == ""){
				alert("검색어를 입력하세요.");
				return;
			}
			if(type == "workbookId" && isNaN(search)){
				alert("숫자만 입력할 수 있습니다.");
				return;
			}
			location.href = "/problem-bank/search-workbook-page?type=" + type + "&search=" + search; 
		});
		
		$(".btn-favorite").on("click", function(){
			let icon = $(this).children("i");
			let isLiked = icon.hasClass("bi-star-fill");
			let workbookId = $(this).val();
			if(isLiked){
				$.ajax({
					url: "/problem-bank/remove-favorite-workbook"
					, type: "DELETE"
					, data:{"workbookId":workbookId}
					, success:function(data){
						if(data.result == "success"){
							icon.removeClass("bi-star-fill");
							icon.addClass("bi-star");
						}
						else{
							alert("즐겨찾기 삭제 실패");
						}
					}
					, error:function(){
						alert("즐겨찾기 삭제 에러");
					}
				});
			} else{
				$.ajax({
					url: "/problem-bank/add-favorite-workbook"
					, type: "POST"
					, data:{"workbookId":workbookId}
					, success:function(data){
						if(data.result == "success"){
							icon.removeClass("bi-star");
							icon.addClass("bi-star-fill");
						}
						else{
							alert("즐겨찾기 추가 실패");
						}
					}
					, error:function(){
						alert("즐겨찾기 추가 에러");
					}
				});
			}
			
		});
	});
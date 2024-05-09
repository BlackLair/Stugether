function addSortableEvent(){
			// 메모 삭제 버튼
		$(".delete-memo-btn").on("click", function(){
			let memoId = $(this).data("id");
			$.ajax({
				url: "/blog/memo/remove"
				, type: "DELETE"
				, data: {"memoId":memoId}
				, success:function(data){
					if(data.result == "success"){
						location.reload();
					}else{
						alert("메모 삭제 실패");
					}
				}
				, error:function(){
					alert("메모 삭제 오류");
				}
			})
		});
		
		// 메모 작성
		$("#memoForm").on("submit", function(e){
			e.preventDefault();
			let content = $("#memoInput").val();
			if(content == ""){
				alert("메묘 내용을 입력하세요.");
			}
			$.ajax({
				url: "/blog/memo/upload"
				, type: "POST"
				, data: {"content":content}
				, success:function(data){
					if(data.result == "success"){
						location.reload();
					}else{
						alert("메모 작성 실패");
					}
				}
				, error:function(){
					alert("메모 작성 에러");
				}
			});
		});
	
	// 메모 순서 변경 이벤트
	$(function() {
	    $( "#sortable" ).sortable({
	    	update: function(event, ui){
	    		let memoIdList = [];
	    		$(".ui-state-default").each(function(index, item){
	    			memoIdList[index] = Number($(this).data("id"));
	    		});
	    		memoIdList.reverse();
	    		$.ajax({
	    			url: "/blog/memo/sort"
	    			, type: "PUT"
	    			, data: {"memoIdList": memoIdList}
	    			, success:function(data){
	    				if(data.result == "failure"){
	    					alert("메모 정렬 실패");
	    				}
	    			}
	    			, error:function(){
	    				alert("메모 정렬 에러");
	    			}
	    		});
	    	}
	    });
	});
}
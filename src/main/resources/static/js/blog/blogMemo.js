function addSortableEvent(){
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
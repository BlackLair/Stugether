	$(document).ready(function(){
		
		function leaveGroup(groupId, assignee){
			let result;
			let info;
			if(assignee == null){
				info = {"groupId":groupId};
			}else{
				info = {"groupId":groupId, "assignee":assignee};
			}
			$.ajax({
				url: "/group/leave"
				, type: "DELETE"
				, data: info
				, async: false
				, success:function(data){
					result = data.result;
				}
				, error:function(){
					result = "error";
				}
			});
			return result;
		}
		
		
		$(".leave-btn").on("click", function(){
			if(!confirm("정말 그룹을 탈퇴하시겠습니까?"
						+ "\n그룹 내에 작성한 게시물, 댓글이 모두 삭제됩니다.")){
				return;
			}
			let groupId = $(this).val();
			let result = leaveGroup(groupId, null);
			if(result == "assignee not exist"){
				let assignee = prompt("그룹장은 탈퇴 시 그룹장을 위임할 사용자를 선택해야 합니다."
									+ "위임할 사용자의 닉네임을 입력하세요.");
				if(assignee == null || assignee == ""){
					return;
				}
				result = leaveGroup(groupId, assignee);
				if(result == "assignee not exist"){
					alert("그룹에 존재하지 않는 닉네임입니다.");
					return;
				}else if(result == "self id"){
					alert("탈퇴 시 자기 자신에게 위임할 수 없습니다.");
					return;
				}
			}
			if(result == "success"){
				alert("그룹에서 탈퇴하였습니다.");
				location.reload();
			}else{
				alert("그룹 탈퇴에 실패했습니다. : " + result);
			}
				
		});
		
		$(".join-btn").on("click", function(){
			if(confirm("그룹에 가입하시겠습니까?")){
				let groupId = Number($(this).val());
				$.ajax({
					url: "/group/join"
					, type: "POST"
					, data: {"groupId":groupId}
					, success:function(data){
						if(data.result == "success"){
							alert("그룹에 가입되었습니다.");
						}else if(data.result == "joined"){
							alert("이미 가입한 그룹입니다.");
						}else{
							alert("그룹 가입에 실패했습니다.");
						}
						location.reload();
					}
					, error:function(){
						alert("그룹 가입 오류");
					}
				});
			}
		});
		$("#searchForm").on("submit", function(e){
			e.preventDefault();
			let search = $("#searchInput").val();
			location.href = "/group/search-group-page?page=1&search=" + search;
		});
	});
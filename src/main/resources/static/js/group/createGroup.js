	$(document).ready(function(){
		let isAvailableName = false;
		let categoryList = [];
		let categoryCount = 1;
		
		$("#createGroupBtn").on("click", function(){
			if(!isAvailableName){
				alert("그룹명을 확인하세요.");
				return;
			}
			let groupName = $("#groupNameInput").val();
			let description = $("#descriptionInput").val();
			$.ajax({
				url: "/group/create"
				, type: "POST"
				, data: {
					"groupName":groupName
					, "description":description
					, "categoryList":categoryList
				}
				, success:function(data){
					if(data.result == "success"){
						alert("그룹 생성 성공 : " + data.groupId);
					}else{
						alert("그룹 생성 실패");
					}
				}
				, error:function(){
					alert("그룹 생성 오류");
				}
			});
		});
		
		$("#addCategoryForm").on("submit", function(e){
			e.preventDefault();
			let categoryName = $("#categoryNameInput").val();
			if(categoryName == ""){
				alert("카테고리명을 입력하세요.");
				return;
			}
			if(categoryList.indexOf(categoryName) != -1 || categoryName == "자유게시판"){
				alert("중복된 카테고리명입니다.");
				return;
			}
			$("#categoryNameInput").val("");
			categoryCount++;
			categoryList.push(categoryName);
			$("#category-tbody").append(`
					<tr class="category-tr" data-category-name="` + categoryName + `" data-category-num="` + categoryCount + `">
						<td class="category-num">` + categoryCount + `</td>
						<td>` + categoryName + `</td>
						<td>
							<button data-category-num="` + categoryCount + `" type="button" class="btn-delete-category form-control btn-danger mx-2 btn-mini"><i class="bi bi-x"></i></button>
						</td>
					</tr>
					`);
			$(".btn-delete-category").each(function(index, item){
				$(this).off("click");
				$(this).on("click", function(){
					let target = $(this).parent().parent();
					let targetIndex = categoryList.indexOf(target.data("category-name"));
					categoryList.splice(targetIndex, targetIndex + 1);
					target.remove();
					categoryCount--;
					$(".category-num").each(function(index, item){
						$(this).html(index + 2);
					});
				});
			});
		});
		
		$("#checkDuplicatedBtn").on("click", function(){
			let groupName = $("#groupNameInput").val();
			if(groupName == ""){
				alert("그룹명을 입력하세요.");
				$("#groupNameInput").addClass("is-invalid");
				return;
			}
			$.ajax({
				url: "/group/name-duplicated"
				, type: "GET"
				, data: {"groupName":groupName}
				, success:function(data){
					if(data.duplicated == true){
						alert("이미 사용중인 그룹명입니다.");
						$("#groupNameInput").removeClass("is-valid");
						$("#groupNameInput").addClass("is-invalid");
					}else{
						alert("사용 가능한 그룹명입니다.");
						$("#groupNameInput").removeClass("is-invalid");
						$("#groupNameInput").addClass("is-valid");
						isAvailableName = true;
					}
				}
			});
		});
		
		$("#groupNameInput").on("input", function(){
			$(this).removeClass("is-valid");
			$(this).removeClass("is-invalid");
			isAvailableName = false;
		});
	});
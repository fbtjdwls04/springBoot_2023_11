<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<c:set var="pageTitle" value="ARTICLE DETAIL" />
	
	<%@ include file="../common/head.jsp" %>
  	
  	<script>
  		let checkNum = ${checked};
  	
  		function increaseRecommend() {
			$.ajax({
				url: "doIncreaseRecommend",
				method: "get",
				data: {"id": ${article.id}, "memberId": ${rq.getLoginedMemberId()}},
				dataType: "json",
				success: function(data) {
					$("#increaseRecommend").html(data.data);
				},
				error: function(xhr, status, error) {
					console.error("ERROR : " + status + " - " + error);
				}
			})
			if(checkNum == 0){
				$('.fa-heart').removeClass('fa-regular');
				$('.fa-heart').addClass('fa-solid text-[red]');
				checkNum = 1;				
			}else{
				$('.fa-heart').removeClass('fa-solid text-[red]');
				$('.fa-heart').addClass('fa-regular');
				checkNum = 0;	
			}
		}
  	</script>
  	
	<section class="flex justify-center ">
		<div class="container">
			<table class="table">
				<tr>
					<th class="min-w-[100px]">번호</th>
					<td>${article.id}</td>
				</tr>
				<tr>
					<th>작성일</th>
					<td>${article.regDate.substring(2,16)}</td>
				</tr>
				<tr>
					<th>수정일</th>
					<td>${article.updateDate.substring(2,16) }</td>
				</tr>
				<tr>
					<th>조회수</th>
					<td>${article.hitCount }</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${article.writerName}</td>
				</tr>
				<tr>
					<th>추천</th>
					<td>
						<c:if test="${rq.getLoginedMemberId() != 0 }">
							<button onclick="increaseRecommend();">
								<c:if test="${checked == 0 }">
									<i class="fa-heart fa-regular"></i>
								</c:if>
								<c:if test="${checked == 1 }">
									<i class="fa-heart fa-solid text-[red]"></i>
								</c:if>
							</button>
						</c:if>
							<span>좋아요 : </span>
							<span id="increaseRecommend">${article.point}</span>
					</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>${article.title }</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>${article.body }</td>
				</tr>
			</table>
			<hr />
			<!-- 수정 삭제버튼 -->
			<div class="flex mt-[20px]">
				<div class="flex-grow"></div>
				<c:if test="${loginedMemberId == article.memberId }">
					<button class="btn btn-primary mr-4"><a href="modify?id=${article.id }">수정</a></button>
					<button class="btn btn-error" onclick="if(confirm('${article.id }번 글을 삭제하시겠습니까?')) location.replace('doDelete?id=${article.id}');">삭제</button>
				</c:if>
			</div>
		</div>
	</section>
<%@ include file="../common/foot.jsp" %>
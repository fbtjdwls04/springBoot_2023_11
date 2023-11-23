<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<c:set var="pageTitle" value="ARTICLE DETAIL" />
	
	<%@ include file="../common/head.jsp" %>
  	
  	<script>
		$(document).ready(function(){
			getRecommendPoint();
			
			$('#recommendBtn').click(function(){
				
				let recommendBtn = $('#recommendBtn');
				
				if (recommendBtn.hasClass('btn-active')) {
					$.ajax({
						url: "../recommendPoint/deleteRecommendPoint",
						method: "get",
						data: {
								"relTypeCode" : "article",
								"relId" : ${article.id }
							},
						dataType: "text",
						success: function(data) {
							console.log(data);
						},
						error: function(xhr, status, error) {
							console.error("ERROR : " + status + " - " + error);
						}
					})
				} else {
					$.ajax({
						url: "../recommendPoint/insertRecommendPoint",
						method: "get",
						data: {
								"relTypeCode" : "article",
								"relId" : ${article.id }
							},
						dataType: "text",
						success: function(data) {
							console.log(data);
						},
						error: function(xhr, status, error) {
							console.error("ERROR : " + status + " - " + error);
						}
					})
				}
				
				location.reload();
			})
		})
		
		const getRecommendPoint = function(){
				$.ajax({
					url: "../recommendPoint/getRecommendPoint",
					method: "get",
					data: {
							"relTypeCode" : "article",
							"relId" : ${article.id }
						},
					dataType: "json",
					success: function(data) {
						if (data.success) {
							$('#recommendBtn').addClass('btn-active');
						}
					},
					error: function(xhr, status, error) {
						console.error("ERROR : " + status + " - " + error);
					}
				})
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
							<button id="recommendBtn" class="mr-8 btn-text-color btn btn-outline btn-xs">좋아요👍</button>
						</c:if>
						<span>${article.point}</span>
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
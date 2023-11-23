<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<c:set var="pageTitle" value="ARTICLE DETAIL" />
	
	<%@ include file="../common/head.jsp" %>
  	
  	<script>
		$(document).ready(function(){
			getRecommendPoint();
			
			$('#recommendBtn').click(function(){
				
				let recommendBtn = $('#recommendBtn').hasClass('btn-active');
				
				$.ajax({
					url: "../recommendPoint/doRecommendPoint",
					method: "get",
					data: {
							"relTypeCode" : "article",
							"relId" : ${article.id },
							"recommendBtn" : recommendBtn
						},
					dataType: "text",
					success: function(data) {
						console.log(data);
					},
					error: function(xhr, status, error) {
						console.error("ERROR : " + status + " - " + error);
					}
				})
				
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
			<!-- 게시물 -->
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
					<td class="whitespace-pre-wrap">${article.body }</td>
				</tr>
			</table>
			<hr />
			<!-- 수정 삭제버튼 시작 -->
			<div class="flex mt-[20px]">
				<button class="btn btn-outline btn-s" onclick="history.back()">뒤로가기</button>
				<c:if test="${loginedMemberId == article.memberId }">
					<button class="btn btn-outline btn-s ml-2"><a href="modify?id=${article.id }">수정</a></button>
					<button class="btn btn-outline btn-s ml-2" onclick="if(confirm('${article.id }번 글을 삭제하시겠습니까?')) location.replace('doDelete?id=${article.id}');">삭제</button>
				</c:if>
			</div>
			<!-- 수정 삭제버튼 끝 -->
		</div>
	</section>
	
	<script>
		function replySubmit(e) {
			const body = e.body;
			
			if(body.value.trim().length == 0){
				alert('내용을 입력해주세요');
				body.focus();
			}
			
			e.submit();
		}
	</script>
	<!-- 댓글 -->
	<section class="flex justify-center my-4">
		<div class="container">
			<h2 class="text-[20px] p-4">댓글 ${replies.size() }</h2>
			<div class=" border p-10 rounded-[10px]">
				<!-- 댓글 입력창 -->
				<form action="/usr/reply/doWrite" onsubmit="replySubmit(this); return false;">
					<input name="relId" value="${article.id }" type="hidden" />
					<input name="relTypeCode" value="article" type="hidden" />
					<div class="font-semibold ml-2 mb-2">${loginedMemberName}</div>
					<div class="flex">
						<textarea name="body" rows="1" class="textarea textarea-bordered w-full" placeholder="댓글을 입력해주세요"></textarea>
						<button class="btn">작성</button>
					</div>
				</form>
				
				<!-- 댓글 리스트 -->
				<div class="mt-8">
					<c:forEach var="reply" items="${replies }">
						<div class="flex mt-4 border-b-2">
							<div class="w-[50px]"></div>
							<div>
								<div class="font-semibold">${reply.writerName }</div>
								<div class="whitespace-pre-wrap ml-2">${reply.body}</div>
								<div class="ml-2">${reply.updateDate}</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</section>
<%@ include file="../common/foot.jsp" %>
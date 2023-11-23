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
		
		function replySubmit(e) {
			const body = e.body;
			
			if(body.value.trim().length == 0){
				alert('내용을 입력해주세요');
				body.focus();
			}
			
			e.submit();
		}
	</script>
  	
	<section class="flex justify-center ">
		<div class="container">
			<!-- 수정 삭제버튼 시작 -->
			<div class="flex mt-[20px]">
				<div class="flex-grow"></div>
				<c:if test="${loginedMemberId == article.memberId }">
					<button class="btn btn-success mr-4"><a href="modify?id=${article.id }">수정</a></button>
					<button class="btn btn-error" onclick="if(confirm('${article.id }번 글을 삭제하시겠습니까?')) location.replace('doDelete?id=${article.id}');">삭제</button>
				</c:if>
			</div>
			<!-- 수정 삭제버튼 끝 -->
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
		</div>
	</section>
	
	<!-- 댓글 -->
	<section class="flex justify-center mt-4">
		<div class="container">
			<h2 class="text-[20px] p-4">댓글 ${replys.size() }</h2>
			<div class=" border-2 p-10 rounded-[10px]">
				<!-- 댓글 입력창 -->
				<form action="/usr/reply/doWrite" onsubmit="replySubmit(this); return false;">
					<input name="relId" value="${article.id }" type="hidden" />
					<input name="relTypeCode" value="article" type="hidden" />
					<div class="flex">
						<textarea name="body" rows="1" class="textarea textarea-bordered w-full" placeholder="댓글을 입력해주세요"></textarea>
						<button class="btn">작성</button>
					</div>
				</form>
				
				<!-- 댓글 리스트 -->
				<table class="table">
					<tr>
						<th class="min-w-[100px]"><span>닉네임</span></th>
						<td>내용</td>
						<td width="200">작성일</td>
						<td width="200">수정일</td>
					</tr>
					<c:forEach var="reply" items="${replys }">
						<tr>
							<th>${reply.writerName }</th>
							<td class="whitespace-pre-wrap">${reply.body}</td>
							<td>${reply.regDate}</td>
							<td>${reply.updateDate}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</section>
<%@ include file="../common/foot.jsp" %>
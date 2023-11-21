<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<c:set var="pageTitle" value="ARTICLE DETAIL" />
	
	<%@ include file="../common/head.jsp" %>
	
	<script>
		const articleDetail_increaseHitCount = function() {
			$.ajax({
				url: "doIncreaseHitCount",
				method: "get",
				data: {"id": parseInt('${param.id }')},
				dataType: "json",
				success: function(data) {
					$("#increaseHitCount").html(data.data);
				},
				error: function(xhr, status, error) {
					console.error("ERROR : " + status + " - " + error);
				}
			})
		}
		
		articleDetail_increaseHitCount();
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
					<td><span id="increaseHitCount">${article.hitCount }</span></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${article.writerName}</td>
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